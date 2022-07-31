package NettyWebSocket.service.Imp;

import NettyWebSocket.Mapper.UserControlMapper;
import NettyWebSocket.Mapper.UserSignMapper;
import NettyWebSocket.pojo.Hardware;
import NettyWebSocket.pojo.User;
import NettyWebSocket.service.MoveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class MoveServiceImp implements MoveService {
    @Override
    public String doOperation(Map<String, Object> map) {
        return null;
    }
    /*
        @Resource
        UserSignMapper userSignMapper;

        @Autowired
        UserControlMapper userControlMapper;


        public String signIn(String phone,String password){
            log.debug("1");
            User user = userSignMapper.select(phone);
            if(user == null){
                return "false";
            }
            else if(user.getPassword() .equals(password) ) return "success";
            else return "success";
        }

        public String register(String phone,String password){
            User user = userSignMapper.select(phone);
            if(user != null){
                return "false";
            }else{
                userSignMapper.add(new User(phone,password));
                return "success";
            }
        }


        @Override
        public Map<Object, Object> getCurrent(int index_num) {
            Date d = new Date();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("kkmmdd");
            Map<Object,Object> map = new HashMap<>();
            String table = "qgDevice2"+sdf1.format(d);
            Integer date = Integer.valueOf(sdf2.format(d));

            log.debug("4");

            map = userControlMapper.monitor(table,date,index_num);

            return map;
        }


        /**
         * 查看某天的各个时段用电量
         * @param  date 要查询的日期
         * @return  返回各个时段的用电量
         */
    /*@Override
    public Map<Integer, Double> getPower(Date date,int index_num) {


        //获取与当前时间的相差天数
            Date nowDate = new Date();
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(date);
        long timeInMillis1 = c1.getTimeInMillis();
        c2.setTime(nowDate);
        long timeInMillis2 = c2.getTimeInMillis();
        double value = Math.floor((timeInMillis2 - timeInMillis1) / (1000 * 60 * 60 * 24));
        int days = (int) Math.abs(value);


        //获取要查询的表
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE,days);
            String time = sdf1.format(c.getTime());
            String table = "qdDevice2"+time;

        //一天分成8个时间段，返回用电量
        int[] part = new int[9];
        part[0] = 0;
        for (int i = 1; i < 9; i++) {
            part[i]= part[i-1] +30000;
        }
        part[8] = part[8]-1;

        Double powerLast = null;
        Map<Integer,Double> map = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            Double power = userControlMapper.getPower(table, part[i], index_num);
            if(powerLast!=null){
                map.put(i+1,power-powerLast);
            }
            powerLast = power;
        }
        return map;
    }
*/


}
