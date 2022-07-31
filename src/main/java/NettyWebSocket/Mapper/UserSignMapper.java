package NettyWebSocket.Mapper;


import NettyWebSocket.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserSignMapper {

    //添加
     void add(User user);

    //删除
     void del(@Param("phone") Object phone);

    //查询

     User select(@Param("phone") Object phone);

}
