package NettyWebSocket;




import NettyWebSocket.Controller.Netty.server;
import NettyWebSocket.Controller.UserControl.DataController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SpringNettyApplication {

        @Autowired
        server server;

        public static void main(String[] args) throws Exception {
            SpringApplication.run(SpringNettyApplication.class, args);
            new server().run();
        }
}
