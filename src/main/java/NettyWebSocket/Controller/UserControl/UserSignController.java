package NettyWebSocket.Controller.UserControl;


import NettyWebSocket.Mapper.UserSignMapper;
import NettyWebSocket.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserSignController {

    @Autowired
    private UserSignMapper userSignMapper;

    @GetMapping("/user/{name}")
    public User select(@PathVariable("name") String name){
        return userSignMapper.select(name);
    }
}
