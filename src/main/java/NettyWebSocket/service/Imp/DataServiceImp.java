package NettyWebSocket.service.Imp;

import NettyWebSocket.Controller.Netty.TCPHandler;
import NettyWebSocket.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="DataService")
public class DataServiceImp implements DataService {

    @Autowired
    TCPHandler tcpHandler;

    @Override
    public String DataGet() {
        String message = tcpHandler.getMessage();
        System.out.println(message);
        return message;
    }
}
