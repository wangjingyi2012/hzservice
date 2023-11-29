package com.wjy.hz.service.impl;

import com.wjy.hz.mapper.BlogMapper;
import com.wjy.hz.mapper.CommentMapper;
import com.wjy.hz.mapper.StudentMapper;
import com.wjy.hz.model.dto.BlogDto;
import com.wjy.hz.model.dto.BlogInputDto;
import com.wjy.hz.model.dto.CommentDto;
import com.wjy.hz.model.dto.CommentInputDto;
import com.wjy.hz.model.entity.BlogEntity;
import com.wjy.hz.model.entity.CommentEntity;
import com.wjy.hz.model.entity.StudentEntity;
import com.wjy.hz.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    BlogMapper blogMapper;

    @Resource
    CommentMapper commentMapper;

    @Resource
    StudentMapper studentMapper;

    @Override
    public List<BlogDto> allBlogs() {
        List<BlogEntity> enties = blogMapper.allBlogs();
        List<BlogDto> dtos = new ArrayList<>();
        enties.forEach(entity -> {
            BlogDto dto = new BlogDto();
            dto.setId(entity.getId());
            dto.setTitle(entity.getTitle());
            dto.setContent(entity.getContent());
            dto.setPubtime(entity.getPubtime());
            if (entity.getAnymous() == 1) {
                dto.setUsername(entity.getAnyname());
            } else {
                dto.setUsername(entity.getAuthname());
            }
            dto.setCommentCount(commentMapper.blogCommentsCount(entity.getId()));
            dtos.add(dto);
        });
        return dtos;
    }

    @Override
    public List<CommentDto> blogComments(long blogId) {
        List<CommentDto> dtos = new ArrayList<>();
        BlogEntity blog = blogMapper.findById(blogId);
        if (blog == null) {
            return dtos;
        }
        List<CommentEntity> comments = commentMapper.blogComments(blogId);
        comments.forEach(comment -> {
            CommentDto dto = new CommentDto();
            dto.setId(comment.getId());
            dto.setContent(comment.getContent());
            dto.setPubtime(comment.getPubtime());
            if (comment.getAnymous() == 1) {
                dto.setUsername(comment.getAnyname());
            } else {
                dto.setUsername(comment.getAuthname());
            }
            dtos.add(dto);
        });
        return dtos;
    }

    @Override
    public int postBlog(BlogInputDto input) {
        BlogEntity entity = new BlogEntity();
        entity.setTitle(input.getTitle());
        entity.setContent(input.getContent());
        entity.setAuthor(input.getSid());
        entity.setPubtime(LocalDateTime.now());
        StudentEntity student = studentMapper.selectById(input.getSid());
        if (student == null) {
            return 0;
        }
        entity.setAuthor(student.getId());
        entity.setAuthname(student.getRealname());
        if (input.getAnymous().equals("anymous")) {
            entity.setAnymous(1);
            entity.setAnyname(student.getUsername());
        } else {
            entity.setAnymous(0);
            entity.setAnyname(student.getRealname());
        }
        return blogMapper.addBlog(entity);
    }

    @Override
    public int postComment(CommentInputDto input) {
        CommentEntity entity = new CommentEntity();
        entity.setContent(input.getContent());
        entity.setPubtime(LocalDateTime.now());
        entity.setAuthor(input.getSid());
        entity.setBlog(input.getBlogId());
        StudentEntity student = studentMapper.selectById(input.getSid());
        if (student == null) {
            return 0;
        }
        entity.setAuthname(student.getRealname());
        if (input.getAnymous().equals("anymous")) {
            entity.setAnymous(1);
            entity.setAnyname(student.getUsername());
        } else {
            entity.setAnymous(0);
            entity.setAnyname(student.getRealname());
        }
        return commentMapper.addComment(entity);
    }

    @Override
    public BlogDto findById(long id) {
        BlogEntity blog = blogMapper.findById(id);
        if (blog == null) {
            return null;
        }
        BlogDto dto = new BlogDto();
        dto.setId(blog.getId());
        dto.setTitle(blog.getTitle());
        dto.setPubtime(blog.getPubtime());
        dto.setContent(blog.getContent());
        if (blog.getAnymous() == 1) {
            dto.setUsername(blog.getAnyname());
        } else {
            dto.setUsername(blog.getAuthname());
        }
        return dto;
    }
}
