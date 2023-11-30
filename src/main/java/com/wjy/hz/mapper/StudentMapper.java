package com.wjy.hz.mapper;

import com.wjy.hz.model.entity.ExamEntity;
import com.wjy.hz.model.entity.StudentEntity;
import com.wjy.hz.model.entity.UserExamEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("select * from students where number = #{number} and password = #{password}")
    StudentEntity login(@Param("number") String number, @Param("password") String password);

    @Select("select * from students where id = #{id}")
    StudentEntity selectById(long id);

    @Update("update students set username = #{username} where id = #{id}")
    int saveUsername(@Param("username") String username, @Param("id") long id);

    @Update("update students set avator = #{avator} where id = #{sid}")
    int updateAvator(@Param("sid") long sid, @Param("avator") String avator);
}
