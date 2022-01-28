package gw.gwrehabwebservice.service;

import gw.gwrehabwebservice.domain.Posts;
import gw.gwrehabwebservice.dto.PostsListResponseDto;
import gw.gwrehabwebservice.dto.PostsResponseDto;
import gw.gwrehabwebservice.dto.PostsSaveRequestDto;
import gw.gwrehabwebservice.dto.PostsUpdateRequestDto;
import gw.gwrehabwebservice.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 사용자가 없습니다. id = " + id)
                );
        posts.update(requestDto.getTitle(), requestDto.getContent());  //`dirtyChecking`을 통해 Transaction 끝나는 시점에 update쿼리가 실행됨.
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 사용자가 없습니다. id = " + id)
                );
        return new PostsResponseDto(entity);
    }

    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .stream().map(posts -> new PostsListResponseDto(posts))
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        // 엔티티를 파라미터로 삭제할 수도 있고, deleteById 메소드를 이용하면 id로 삭제할 수도 있다.
        // 존재하는 Posts 인지 확인을 위해 엔티티 조회 후 그대로 삭제
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 사용자가 없습니다. id = " + id)
                );
        postsRepository.delete(posts);
    }
}