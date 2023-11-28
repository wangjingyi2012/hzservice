package com.wjy.hz.mapper;

import com.wjy.hz.model.entity.SectionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SectionMapper {

    @Select("select * from sections where course = #{courseId}")
    List<SectionEntity> courseSections(@Param("courseId") long courseId);

    @Select("select * from sections where id = #{id}")
    SectionEntity byId(@Param("id") long id);



}
