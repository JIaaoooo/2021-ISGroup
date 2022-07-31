package NettyWebSocket.service;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public interface MoveService {

    String doOperation(Map<String,Object> map);
}
