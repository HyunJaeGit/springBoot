package com.devbig.spring.web.dto;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

// gradle 버전이 5이상이면 에러가 발생함 -> gradle 버전을 4.10.2로 다운그레이드
public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloControllerDto dto = new HelloControllerDto(name,amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
        /*
            1. assertThat()
            - assertj라는 테스트 검증 라이브러리의 검증 메소드
            - 검증하고 싶은 대상을 메소드 인자로 받음
            - 메소드 체이닝이 지원되어 isEqualTo()와 같이 메소드를 이어서 사용가능
            - import 해줘야함

            2. isEqualTo()
            - assertj의 동등 비교 메소드
            - assertThat에 있는 값과 isEqualTo()의 값을 비교해서 같을 때만 성공

        * */

    }

}
