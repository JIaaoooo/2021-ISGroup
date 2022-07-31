package NettyWebSocket.service.MoveServiceImp;

import NettyWebSocket.Mapper.UserControlMapper;
import NettyWebSocket.service.MoveService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component("4")
@Slf4j
public class getCurrentImp implements MoveService {

    @Autowired
    UserControlMapper userControlMapper;
    @Override
    public String doOperation(Map<String, Object> map) {
        Date d = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("kkmmdd");
        Map<Object,Object> result = new HashMap<>();

        String table = "qgDevice"+sdf1.format(d);
        Integer date = Integer.valueOf(sdf2.format(d));

        result = userControlMapper.monitor(table,date, (String) map.get("index_num"));
        result.put("label","4");
        return JSON.toJSONString(result);
    }
}
