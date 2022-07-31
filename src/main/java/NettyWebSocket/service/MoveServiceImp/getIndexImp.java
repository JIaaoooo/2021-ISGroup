package NettyWebSocket.service.MoveServiceImp;

import NettyWebSocket.Mapper.UserControlMapper;
import NettyWebSocket.service.MoveService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("6")
@Slf4j
public class getIndexImp implements MoveService {
    @Autowired
    UserControlMapper userControlMapper;

    @Override
    public String doOperation(Map<String, Object> map) {
        Date nowDate = new Date();
        //小时分钟秒
        SimpleDateFormat sdf = new SimpleDateFormat("kkmmss");
        String time = sdf.format(nowDate); //"94314";
        //小时
        SimpleDateFormat sdf2 = new SimpleDateFormat("kk");
        String hour = sdf2.format(nowDate);
        //年月日
        String date = (String) map.get("date");
        //根据当前小时，判断阶段
        int stage = (Integer.valueOf(hour) / 4)+1 ;
        //获取端口用电量
        Double init = userControlMapper.getPower(date, 1);
        Double power = init;
        for (int i = 2; i < stage+1; i++) {
            Double currentPower = userControlMapper.getPower(date, i);
            power = power + currentPower;
        }


        String table = "qgDevice" + date;
        Map<Object,Object> result = new HashMap<>();
        for (int i = 1; i < 4; i++) {
            String name = userControlMapper.getName(table,String.valueOf(i),time);
            if(name!=null){
                result.put("name"+i,name);
                Map<Object, Object> maxMessage = userControlMapper.getMaxMessage(table, String.valueOf(i), time,name);
                log.debug(maxMessage.toString());
                result.put("index_num"+i,maxMessage);
                log.debug(result.toString());
            }else{
                result.put("name"+i,"UNKNOW");
            }
        }
        result.put("label","3");
        DecimalFormat df = new DecimalFormat("#0.000000");
        result.put("usepower",df.format(power));
        log.debug(result.toString());
        return JSON.toJSONString(result);
    }
}
