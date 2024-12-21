package com.devbig.spring.web;


import com.devbig.spring.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private  final PostsService postsService;

    @PostMapping()
    public Long save() {

    }


}
