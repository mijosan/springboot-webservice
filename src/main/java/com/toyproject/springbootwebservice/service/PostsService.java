package com.toyproject.springbootwebservice.service;

import javax.transaction.Transactional;

import com.toyproject.springbootwebservice.domain.posts.Posts;
import com.toyproject.springbootwebservice.domain.posts.PostsRepository;
import com.toyproject.springbootwebservice.web.dto.PostsResponseDto;
import com.toyproject.springbootwebservice.web.dto.PostsSaveRequestDto;
import com.toyproject.springbootwebservice.web.dto.PostsUpdateRequestDto;

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

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        
        posts.update(requestDto.getTitle(), requestDto.getContent());
        
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
