package com.toyproject.springbootwebservice.web;

import com.toyproject.springbootwebservice.service.PostsService;
import com.toyproject.springbootwebservice.web.dto.PostsSaveRequestDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        
        return postsService.save(requestDto);
    }

}
