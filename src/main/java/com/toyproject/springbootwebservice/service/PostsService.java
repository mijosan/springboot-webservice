package com.toyproject.springbootwebservice.service;

import javax.transaction.Transactional;

import com.toyproject.springbootwebservice.domain.posts.PostsRepository;
import com.toyproject.springbootwebservice.web.dto.PostsSaveRequestDto;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {
    
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        
        return postsRepository.save(requestDto.toEntity()).getId();
    }

}
