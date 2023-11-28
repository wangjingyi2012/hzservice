package com.wjy.hz.mapper;

import com.wjy.hz.model.entity.CourseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {

    @Select("select * from courses")
    List<CourseEntity> courses();

}
