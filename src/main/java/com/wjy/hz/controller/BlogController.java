package com.wjy.hz.controller;

import com.wjy.hz.model.api.ApiResponse;
import com.wjy.hz.model.dto.BlogInputDto;
import com.wjy.hz.model.dto.CommentInputDto;
import com.wjy.hz.service.BlogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class BlogController {

    @Resource
    BlogService blogService;

    @GetMapping("/api/blog/blog-list")
    public String blogList() {
        return ApiResponse.ok(blogService.allBlogs());
    }

    @GetMapping("/api/blog/blog-comments")
    public String commentList(@RequestParam("blogId") long blogId) {
        return ApiResponse.ok(blogService.blogComments(blogId));
    }

    @PostMapping("/api/blog/post-blog")
    public String postBlog(@RequestBody BlogInputDto blogInputDto) {
        return blogService.postBlog(blogInputDto) > 1 ? ApiResponse.ok("ok") : ApiResponse.intError();
    }

    @PostMapping("/api/blog/post-comment")
    public String postComment(@RequestBody CommentInputDto commentInputDto) {
        return blogService.postComment(commentInputDto) > 1 ? ApiResponse.ok("ok") : ApiResponse.intError();
    }

}
