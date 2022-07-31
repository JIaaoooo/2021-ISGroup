package NettyWebSocket.Controller.UserControl;


import NettyWebSocket.Mapper.UserControlMapper;
import NettyWebSocket.Mapper.UserSignMapper;
import NettyWebSocket.pojo.Hardware;
import NettyWebSocket.service.DataService;
import NettyWebSocket.service.Imp.DataServiceImp;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.handler.codec.http.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 主要职责：将原嵌入式的数据传到人工智能组
 * 并且将人工智能处理后的信息重新获取，保存到数据库中
 */
@RestController
@Slf4j
@RequestMapping("/aaa")
public class DataController {


    @Autowired
    Hardware hardware;

    @Autowired
    UserControlMapper userControlMapper;


    @Autowired
    UserSignMapper userSignMapper;

    @Autowired
    RestTemplate restTemplate;


    private Hardware json ;




    @GetMapping
    public String DataBack(String str){
        String url = "http://qgailab.com/getdata/{str}";
        //String str = "{\"name\":\"PRINTER\",\"current\":0.03233,\"voltage\":220.20500,\"power\":3.13005,\"index_num\":1,\"power_factor\":0.44042,\"frequency\":50,\"cumulative_power\":36.12300}";
        log.debug(str);
        String template = restTemplate.getForObject(url, String.class,str);
        return template;
    }


}
