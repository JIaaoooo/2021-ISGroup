package NettyWebSocket.service.MoveServiceImp;

import NettyWebSocket.Mapper.UserSignMapper;
import NettyWebSocket.pojo.User;
import NettyWebSocket.service.MoveService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component("2")
@Slf4j
public class SignInImp implements MoveService {

    @Autowired
    UserSignMapper userSignMapper;

    @Override
    public String doOperation(Map<String, Object> map) {
        log.debug((String) map.get("phone"));
        User user = userSignMapper.select(map.get("phone"));
        Map<String,String> result = new HashMap<>();
        if(user == null){
            map.put("result","false");
            return JSON.toJSONString(map);
        }
        else if(user.getPassword() .equals(map.get("password")) ) {
            map.put("name",user.getName());
            map.put("result","success");
            return JSON.toJSONString(map);
        }
        else{
            map.put("result","flase");
            log.debug(map.toString());
            return JSON.toJSONString(map);
        }

    }
}
