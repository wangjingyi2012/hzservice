package com.wjy.hz.mapper;

import com.wjy.hz.model.entity.CommentEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("INSERT INTO comments (content, pubtime, author, authname, anymous, anyname, blog) VALUES (#{content}, #{pubtime}, #{author}, #{authname}, #{aynmous}, #{anyname}, #{blog})")
    int addComment(CommentEntity commentEntity);

    @Select("select * from comments where blog = #{bid}")
    List<CommentEntity> blogComments(@Param("bid") long blogId);

    @Select("select coun(*)t from comments where blog = #{bid}")
    int blogCommentsCount(@Param("bid") long blogId);

}

