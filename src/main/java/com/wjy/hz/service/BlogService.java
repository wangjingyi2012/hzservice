package com.wjy.hz.service;

import com.wjy.hz.model.dto.BlogDto;
import com.wjy.hz.model.dto.BlogInputDto;
import com.wjy.hz.model.dto.CommentDto;
import com.wjy.hz.model.dto.CommentInputDto;

import java.util.List;

public interface BlogService {

    List<BlogDto> allBlogs();

    List<CommentDto> blogComments(long blogId);

    int postBlog(BlogInputDto input);

    int postComment(CommentInputDto input);

}
