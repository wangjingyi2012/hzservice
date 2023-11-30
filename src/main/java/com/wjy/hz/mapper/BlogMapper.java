package com.wjy.hz.mapper;

import com.wjy.hz.model.entity.BlogEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlogMapper {

    @Insert("INSERT INTO blogs (title, content, pubtime, author, authname, anymous, anyname, avator, realavator) VALUES (#{title}, #{content}, #{pubtime}, #{author}, #{authname}, #{anymous}, #{anyname}, #{avator}, #{realavator})")
    int addBlog(BlogEntity blogEntity);

    @Select("select * from blogs order by id desc")
    List<BlogEntity> allBlogs();

    @Select("select * from blogs where id = #{bid}")
    BlogEntity findById(long bid);
}
