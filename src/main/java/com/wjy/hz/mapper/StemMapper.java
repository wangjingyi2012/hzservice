package com.wjy.hz.mapper;

import com.wjy.hz.model.entity.StemEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StemMapper {

    @Insert("insert into stems (`content`, type, op) VALUES (#{content}, #{type}, #{op})")
    int addStem(@Param("content") String content, @Param("type") String type, @Param("op") int op);

    @Select("select * from stems where id = #{id}")
    StemEntity byId(long id);

}
