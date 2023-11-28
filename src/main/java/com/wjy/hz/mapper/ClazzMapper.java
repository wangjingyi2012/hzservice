package com.wjy.hz.mapper;

import com.wjy.hz.model.entity.ClazzEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClazzMapper {

    @Select("select * from clazzs where id = #{id}")
    ClazzEntity selectById(long id);

}
