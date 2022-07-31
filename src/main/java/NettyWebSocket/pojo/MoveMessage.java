package NettyWebSocket.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoveMessage {

    //option用于判断选择的功能
    //phone password用于登录注册
    //key value用于控制硬件

    private Integer option;
    private Integer index_num;
    private String phone;
    private String password;
    private String key;
    private String value;

    private Date date;

    @Override
    public String toString() {


            return "{" +" 'key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    '}';


    }
}
