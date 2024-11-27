package com.devbig.spring.web;

import com.devbig.spring.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController     // 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 줍니다
                    // @ResponseBody를 메소드마다 선언했었던걸 한번에 할수있게 해줌
public class HelloController {

    @GetMapping("/hello")   //HTTP Method인 GET의 요청을 받을 수 있는 API를 만들어 줍니다
                            // 예전에는 RequestMapping(method = RequestMethod.GET)으로 사용
    public String hello()   {
        return "hello";
    }

    @GetMapping("/hello/dto")   // RequestParam : 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name,amount);
    }
}
