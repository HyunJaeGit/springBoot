package com.devbig.spring.web;

import com.devbig.spring.service.posts.PostsService;
import com.devbig.spring.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {

        // Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있음
        // - 여기서는 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달
        model.addAttribute("posts", postsService.findAllDesc());

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {

        PostsResponseDto dto = postsService.findById(id);   // 해당 게시글 ID를 기준으로 DB에서 게시글을 찾아 dto에 담음
        model.addAttribute("posts", dto);                // 조회한 게시글 데이터를 posts라는 이름으로 뷰에 전달
                                            // URL에 포함된 ID 값으로 DB에서 게시글을 찾고, 그 결과를 posts라는 이름으로 전달
        return "posts-update";
        /*  서버가 posts-update.mustache 템플릿 파일을
            서버 안에서 데이터를 채워 넣고(렌더링)
            완성된 HTML을 클라이언트(브라우저)에 응답으로 보낸다
            Spring에서는 .mustache, .jsp, .html 등이 View
        */
    }

}
