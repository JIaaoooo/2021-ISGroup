package NettyWebSocket.Mapper;


import NettyWebSocket.pojo.Hardware;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface UserControlMapper {

    //存储数据
    void save(Hardware hardware);

    /*//修改数据
    void update(@Param("name") String name,@Param("table")String table,@Param("oldName")String oleName);
*/
    //监控硬件的数据，并返回
     Map<Object,Object> monitor(@Param("table")String table,@Param("date")Integer date,@Param("index_num")String index_num);

     void createTable(@Param("table") String table);

     Double getPower(@Param("table")String table , @Param("id")int id);

     Map<Object,Object> getMaxMessage(@Param("table")String table,@Param("index_num")String index_num,@Param("time") String time,@Param("name") String name );


     String getName(@Param("table")String table , @Param("index_num")String index_num,@Param("time") String time);

}
