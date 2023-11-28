package com.wjy.hz.mapper;

import com.wjy.hz.model.entity.PaperEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PaperMapper {

    @Insert("insert into paper (`title`, `profile`) values (#{title}, #{profile})")
    int addPaper(@Param("title") String title, @Param("profile") String profile);

    @Select("select * from paper where id = #{id}")
    PaperEntity byId(long id);

    @Select("select count(*) from paper_stems where paper = #{paper}")
    int stemsCount(@Param("paper") long id);

}
