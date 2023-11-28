package com.wjy.hz.mapper;

import com.wjy.hz.model.entity.GradeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GradeMapper {

    @Select("select * from grades where id = #{id}")
    GradeEntity selectById(long id);

}
