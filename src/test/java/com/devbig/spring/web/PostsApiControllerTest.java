package com.devbig.spring.web;


import com.devbig.spring.domain.posts.Posts;
import com.devbig.spring.domain.posts.PostsRepository;
import com.devbig.spring.web.dto.PostsResponseDto;
import com.devbig.spring.web.dto.PostsSaveRequestDto;
import com.devbig.spring.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// 등록 기능 테스트
// 수정/조회 기능 테스트

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    //======================= 등록 기능 조회 =============================
    @Test
    public void Posts_등록된다() throws Exception {

        //given
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    //======================= 수정, 조회 기능 테스트 =============================

    @Test
    public void Posts_수정된다() {

        // given
        Posts savedPosts = postsRepository.save(
                Posts.builder()
                        .title("title")
                        .content("content")
                        .author("author")
                        .build());
        // JPA의 save() 메서드를 사용하여 Posts 엔티티 객체를 데이터베이스에 저장하는 코드 (아래 설명)
        /*
            1. Posts.builder()
                @Builder 어노테이션을 사용한 빌더 패턴.
                Posts 객체를 생성할 때, 생성자 대신 체인 형태로 메서드 호출을 통해 객체를 직관적으로 구성할 수 있게 해줍니다.
            2. .title()
                title() 메서드는 Posts 엔티티의 title 필드 값을 설정합니다.
                예를 들어, title("My Title")을 호출하면 해당 엔티티의 title 필드에 "My Title"이 저장됩니다.
            3. .content()
                content() 메서드는 Posts 엔티티의 content 필드 값을 설정합니다.
                예를 들어, content("My Content")을 호출하면 해당 엔티티의 content 필드에 "My Content"이 저장됩니다.
            4. .author()
                author() 메서드는 Posts 엔티티의 author 필드 값을 설정합니다.
                예를 들어, author("John Doe")를 호출하면 해당 엔티티의 author 필드에 "John Doe"가 저장됩니다.
            5. .build()
                최종적으로, 빌더 패턴을 사용해 설정된 데이터를 가지고 Posts 객체를 생성합니다.
                생성된 객체는 아래와 같은 값이 포함된 상태입니다:
                    Posts {
                        title: "My Title",
                        content: "My Content",
                        author: "John Doe"
                    }
        */

        // 테스트 코드에서 하드코딩된 문자열을 사용하는 이유는 코드의 동작을 일관되게 검증하기 위해
        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
        /*
            1. url = ""
            테스트를 위한 요청 URL을 생성합니다.
            port는 테스트 실행 시 랜덤으로 설정된 포트를 사용합니다.
            updateId는 수정할 게시물의 ID입니다.
            http://localhost:8080/api/v1/posts/id

            2. HttpEntity<> :  Spring에서 HTTP 요청/응답을 나타내는 클래스
            HTTP 요청의 엔티티(Body)를 나타냅니다.
            requestDto를 HTTP 요청의 바디로 설정합니다.
            Spring의 REST 테스트에서 요청 데이터를 서버로 전송할 때 사용됩니다.
            추가적으로 HTTP 헤더를 설정할 수도 있습니다.

            3. 위 코드의 진행순서
                1) savedPosts.getId()로 수정할 게시물의 ID를 가져옵니다.
                2) 수정 후 기대되는 값(expectedTitle, expectedContent)을 설정합니다.
                3) 수정 데이터를 담은 PostsUpdateRequestDto 객체를 생성합니다.
                4) 수정 요청을 보낼 URL을 생성합니다.
                5) HTTP 요청 엔티티를 생성하여 요청 데이터를 준비합니다.
         */


        // when
        // Spring의 RestTemplate 을 사용하여 HTTP 요청을 보내는 부분
        // ResponseEntity<Long> : HTTP 요청에 대한 응답을 저장하는 객체
        // .exchange() : RestTemplate 객체의 메서드로, HTTP 요청을 전송하고 응답을 받는 데 사용
        // = exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType)
        ResponseEntity<Long> responseEntity = restTemplate
                .exchange(url, HttpMethod.PUT, requestEntity, Long.class);


        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
        /*
            1. assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
            설명:
                responseEntity.getStatusCode()는 HTTP 응답의 상태 코드를 가져옵니다.
                HttpMethod.OK는 HTTP 200 OK 상태 코드를 의미하는데, HttpMethod.OK는 HttpStatus.OK와 동일합니다.
                이 부분은 서버 응답 상태가 200 OK인지 확인하는 테스트입니다.
            예시:
                서버가 정상적으로 요청을 처리했다면, HTTP 상태 코드 200이 응답으로 오게 됩니다.

            2. List<Posts> all = postsRepository.findAll();
            설명:
                postsRepository.findAll()은 데이터베이스에 저장된 모든 게시물을 조회하는 코드입니다.
                postsRepository는 JpaRepository를 상속한 리포지토리 인터페이스입니다.
                findAll()은 모든 레코드를 리스트로 반환합니다.

            3. assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
            설명:
                all.get(0)은 데이터베이스에 저장된 게시물 중 첫 번째 게시물을 가져옵니다.
                getTitle()은 Posts 엔티티 객체의 title 속성을 가져옵니다.
                isEqualTo(expectedTitle)는 첫 번째 게시물의 제목이 expectedTitle과 일치하는지 확인하는 부분입니다.
            용도:
                게시물 이 제대로 수정되었는지 확인하는 부분입니다. expectedTitle은 수정 후 기대되는 제목입니다.

         */


    }



}
