package NettyWebSocket.service.MoveServiceImp;

import NettyWebSocket.Mapper.UserControlMapper;
import NettyWebSocket.service.MoveService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component("5")
@Slf4j
public class getPowerImp implements MoveService {

    @Autowired
    UserControlMapper userControlMapper;
    @Override
    public String doOperation(Map<String, Object> map) {
        //获取要查询的时间，String转换为Date类型
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date selectDate = null ;
        try {
            selectDate = simpleDateFormat.parse((String) map.get("date"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }



        //获取与当前时间的相差天数
        Date nowDate = new Date();
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(selectDate);
        long timeInMillis1 = c1.getTimeInMillis();
        log.debug("时间"+timeInMillis1);
        c2.setTime(nowDate);
        long timeInMillis2 = c2.getTimeInMillis();
        int days = (int)Math.floor(( timeInMillis1 - timeInMillis2 ) / (1000 * 60 * 60 * 24));

        log.debug("相隔日期"+days);
        //获取要查询的表
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,days);
        String time = sdf1.format(c.getTime());
        String table = time;

        int count = 6;
        if(days==0){
            SimpleDateFormat s = new SimpleDateFormat("HH");
             count = Integer.valueOf(s.format(nowDate)) / 4;
        }
        log.debug("分段"+count);
//一天分成8个时间段，返回用电量


        Map<Object,Object> result = new HashMap<>();
        for (int i = 1; i < count+1; i++) {
            Double power = userControlMapper.getPower(table, i);
            result.put(String.valueOf(i),power);
            log.debug("1");
            log.debug(result.toString());
        }
        result.put("label","5");
        return JSON.toJSONString(result,true);
    }
}
