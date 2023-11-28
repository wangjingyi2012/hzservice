package com.wjy.hz.mapper;

import com.wjy.hz.model.entity.PaperStemEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PaperStemMapper {

    @Insert("insert into paper_stems (paper, stem) VALUES (#{paper}, #{stem})")
    int addPaperStem(@Param("paper") long paperId, @Param("stem") long stemId);

    @Select("select * from paper_stems where paper = #{paper}")
    List<PaperStemEntity> paperStems(@Param("paper") long paperId);

}
