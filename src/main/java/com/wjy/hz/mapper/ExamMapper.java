package com.wjy.hz.mapper;

import com.wjy.hz.model.entity.ExamEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ExamMapper {

    @Insert("insert into exams (`name`, profile, start, end, paper, course) VALUES (#{name}, #{profile}, #{start}, #{end}, #{paper}, #{course})")
    int addExam(@Param("name") String name, @Param("profile") String profile, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("paper") long paper, @Param("course") long course);

    @Select("select * from exams where id = #{id}")
    ExamEntity byId(long id);

}
