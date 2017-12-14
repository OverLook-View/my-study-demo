package com.sy.study.springboot.demo.mapper;

import com.sy.study.springboot.demo.bean.Demo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DemoMapper {
    @Insert("insert into demo(name) values(#{demo.name})")
    int insert(@Param("demo") Demo demo);

    @Update("update demo set name=#{demo.name} where id=#{demo.id}")
    int update(@Param("demo") Demo demo);

    @Delete("delete from demo where id=#{id}")
    int delete(@Param("id") int id);

    @Select("select * from demo where id=#{id}")
    Demo selectOne(@Param("id") int id);

    @Select("select * from demo where name like concat('%',#{name},'%')")
    List<Demo> select(@Param("name") String name);
}
