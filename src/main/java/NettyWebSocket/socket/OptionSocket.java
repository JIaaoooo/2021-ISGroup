package NettyWebSocket.socket;


import NettyWebSocket.Controller.Netty.TCPHandler;
import NettyWebSocket.Controller.Netty.WebsocketHandler;
import NettyWebSocket.Controller.UserControl.DataController;
import NettyWebSocket.Mapper.UserControlMapper;
import NettyWebSocket.Mapper.UserSignMapper;
import NettyWebSocket.factory.MoveServiceFactory;
import NettyWebSocket.pojo.EmbMessage;
import NettyWebSocket.pojo.Hardware;
import NettyWebSocket.pojo.MoveMessage;
import NettyWebSocket.pojo.User;
import NettyWebSocket.service.Imp.MoveServiceImp;
import NettyWebSocket.service.MoveService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
@Slf4j
public class OptionSocket {
    @Autowired
    UserControlMapper userControlMapper;
    @Autowired
    EmbMessage EmbMessage;

    @Autowired
    UserSignMapper UserSignMapper;

    @Autowired
    MoveServiceFactory moveServiceFactory;

    @Autowired
    RestTemplate RestTemplate;

    private static RestTemplate restTemplate;
    private static OptionSocket optionSocket;

    private static UserControlMapper userControl;


    private static EmbMessage embMessage;
    @PostConstruct
    public void init(){
        userControl = userControlMapper;
        embMessage = EmbMessage;
        restTemplate = RestTemplate;
        optionSocket = this;
        optionSocket.moveServiceFactory  = this.moveServiceFactory;
    }





    /**
     * 一个前端处理的十字路口
     * @param str 前端传来的未处理的啥消息
     * * 分两种情况：
     *
     *    1.硬件未连接
     *     2.硬件已连接
     *
     */
    public static void MoveOption(String str){

        //MoveMessage message = JSON.parseObject(str, MoveMessage.class);
        Map<String,Object> map = JSON.parseObject(str, Map.class);
        if(map.get("option")!=null){
            if(map.get("option").equals("1")){
                log.debug(str);

                sendToEmb(str);
            }
            else {
                String result = optionSocket.moveServiceFactory.get(map.get("option")).doOperation(map);
                log.debug(result);
                sendToMove(result);
        }

        }


    }






    private static Hardware hardware;
    public static void EmbOption(String str){

        log.debug(str);
        embMessage  = JSON.parseObject(str, EmbMessage.class);

        switch (embMessage.getOption()){

            //一为报警信号
            case ("1"):
                sendToMove(str);
                break;
            case ("2"):

                //将信息分析他的名字
                hardware = JSON.parseObject(str, Hardware.class);
                log.debug(hardware.toString());
               /* DataController dataController = new DataController();
                String name = dataController.DataBack(hardware.toString());*/
                String url = "http://qgailab.com/getdata/{str}";
                String s = JSON.toJSONString(hardware);
                String name = restTemplate.getForObject(url, String.class,s);
                log.debug(name);
                hardware.setName(name);
                //存储信息
                save(hardware);
                break;

        }
    }

    /**
     * 移动端发送操作后，立刻执行给硬件处理

     */
    public static void sendToEmb(String message){
        //返回信息给硬件
        TCPHandler.sendMessage(message);
    }

    public static void sendToMove(String message){
        //返回信息给移动
        WebsocketHandler.sendMessage(message);
    }


    /**
     * 硬件发送消息，将数据存入数据库中
     */


    public static void save(Hardware hard){

        Date d = new Date();

        //不同一天的数据存放在不同的表中
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("kkmmss");
        hard.setDate(Integer.valueOf(sdf2.format(d)));
        hard.setTable("qgDevice"+sdf1.format(d));
        userControl.createTable(hard.getTable());
        userControl.save(hard);
    }






}
