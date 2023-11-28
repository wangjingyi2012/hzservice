package com.wjy.hz.mapper;

import com.wjy.hz.model.entity.AnswerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnswerMapper {

    @Select("select * from answers where stem = #{stem}")
    List<AnswerEntity> stemAnswers(@Param("stem") long stemId);

    @Select("select * from answers where `stem` = #{stem} and correct = 1 limit 1")
    AnswerEntity radirAndJudgeAnswer(@Param("stem") long stemId);

    @Select("select * from answers where stem = #{stem} and correct = 1")
    List<AnswerEntity> multiAnswers(@Param("stem") long stemId);


}
