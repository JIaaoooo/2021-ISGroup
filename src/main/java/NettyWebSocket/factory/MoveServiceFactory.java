package NettyWebSocket.factory;


import NettyWebSocket.service.MoveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@Slf4j
public class MoveServiceFactory {
    @Autowired
    Map<String, MoveService> MoveService = new ConcurrentHashMap<String, MoveService>();

        public MoveService get(Object key) {
            log.debug(MoveService.toString());
            MoveService moveService = MoveService.get(key);
            if(moveService == null){
                throw new RuntimeException("no moveService defined");
            }
            return moveService;
        }
}
