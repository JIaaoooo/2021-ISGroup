package NettyWebSocket.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aopalliance.intercept.Interceptor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Hardware {

//        private  HashMap<String,Object> map;
        private String table;

        private String option;
        private String name;
        private double current;         //电流
        private double voltage;         //电压
        private double power;           //功率
        private int date;
        private int index_num;       //串口号
        private double power_factor;    //功率系数
        private double frequency;       //频率
        private double cumulative_power;        //累计用电量


        @Override
        public String toString() {
                return "{" +
                        "name='" + name + '\'' +
                        ", current='" + current + '\'' +
                        ", voltage='" + voltage + '\'' +
                        ", power='" + power + '\'' +
                        ", date='" + date + '\'' +
                        ", index_num='" + index_num + '\'' +
                        ", power_factor='" + power_factor + '\'' +
                        ", frequency='" + frequency + '\'' +
                        ", cumulative_power='" + cumulative_power + '\'' +
                        '}';
        }
}
