package com.devbig.spring.service.posts;


import com.devbig.spring.domain.posts.Posts;
import com.devbig.spring.domain.posts.PostsRepository;
import com.devbig.spring.web.dto.PostsListResponseDto;
import com.devbig.spring.web.dto.PostsResponseDto;
import com.devbig.spring.web.dto.PostsSaveRequestDto;
import com.devbig.spring.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    // save 기능 서비스
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {

        return postsRepository.save(requestDto.toEntity()).getId();
    }

    // update 기능 서비스
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository
                .findById(id)
                .orElseThrow(
                    () -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id)
                );

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    // findById 기능 (조회) 서비스
    @Transactional
    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id)
                );

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

}
