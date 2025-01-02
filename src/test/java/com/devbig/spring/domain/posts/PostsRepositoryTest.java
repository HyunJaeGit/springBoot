package com.devbig.spring.domain.posts;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


// - save, findAll 기능 테스트
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After                                                       // After
    public void cleanup() {
        postsRepository.deleteAll();
}   // - Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 정
                                             // - 배포 전 전체 테스트를 수행할때 테스트간 데이터 침범을 막기위해
                                             // - 여러 테스트가 동시에 수행되면 테스트용 DB인 H2에 데이터가 남아서 실패할 수 있음
    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                                    .title(title)
                                    .content(content)
                                    .author("jojoldu@gmail.com")
                                    .build()
                            );
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }


    @Test       // JPA Auditing Test code
    public void BaseTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                        .title("title")
                        .content("content")
                        .author("author")
                        .build());
        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>> createDate=" + posts.getCreateDate() + ", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreateDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }

}
