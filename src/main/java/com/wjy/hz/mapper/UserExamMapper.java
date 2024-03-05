package com.wjy.hz.mapper;

import com.wjy.hz.model.entity.UserExamEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserExamMapper {

    @Insert("insert into user_exams (sid, srealname, exam, clazz) values (#{sid}, #{srealname}, #{eid}, #{clazz})")
    int joinExam(@Param("sid") long sid, @Param("eid") long eid, @Param("srealname") String srealname, @Param("clazz") long clazz);

    @Select("select * from user_exams where sid = #{sid}")
    List<UserExamEntity> studentExamIds(long sid);

    @Insert("insert into user_exams (sid, srealname, exam, clazz, score) values (#{sid}, #{srealname}, #{eid}, #{clazz}, #{score})")
    int score(@Param("sid") long sid, @Param("eid") long eid, @Param("srealname") String srealname, @Param("clazz") long clazz, @Param("score") int score);

    @Update("update user_exams set srealname=#{srealname}, clazz=#{clazz}, score=#{score} where sid=#{sid} and exam=#{eid}")
    int updateScore(@Param("sid") long sid, @Param("eid") long eid, @Param("srealname") String srealname, @Param("clazz") long clazz, @Param("score") int score);


    @Select("select count(*) from user_answers where `exam` = #{exam} and `sid` = #{sid}")
    int isJoined(@Param("exam") long examId, @Param("sid") long sid);

}
