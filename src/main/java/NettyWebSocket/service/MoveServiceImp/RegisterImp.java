package NettyWebSocket.service.MoveServiceImp;

import NettyWebSocket.Mapper.UserSignMapper;
import NettyWebSocket.pojo.User;
import NettyWebSocket.service.MoveService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;


@Component("3")
@Slf4j
public class RegisterImp implements MoveService {

    @Autowired
    UserSignMapper userSignMapper;

    @Override
    public String doOperation(Map<String, Object> map) {
        User user = userSignMapper.select(map.get("phone"));
        if(user != null){
            map.put("result","false");
            return JSON.toJSONString(map);
        }else{
            userSignMapper.add(new User((String) map.get("phone"), (String) map.get("password"), (String) map.get("name")));
            map.put("result","success");
            return JSON.toJSONString(map);
        }
    }
}
