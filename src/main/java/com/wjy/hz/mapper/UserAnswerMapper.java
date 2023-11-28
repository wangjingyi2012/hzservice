package com.wjy.hz.mapper;

import com.wjy.hz.model.entity.UserAnswerEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserAnswerMapper {


    @Insert("INSERT INTO user_answers(sid, stem, correct, input, ranswer, exam) VALUES(#{sid}, #{stem}, #{correct}, #{input}, #{ranswer}, #{exam})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(UserAnswerEntity userAnswerEntity);

    @Select("select * from user_answers where `exam` = #{exam} and sid = #{sid}")
    List<UserAnswerEntity> userAnswers(@Param("exam") long examId, @Param("sid") long studentId);

}
