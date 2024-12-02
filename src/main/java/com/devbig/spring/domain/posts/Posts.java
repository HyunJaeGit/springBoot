package com.devbig.spring.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// spring 때의 생성자 페이지와 테이블을 연결하는걸 클래스로 한번에 해결할 수 있음
@Getter                 // 1. Getter : 클래스 내 모든 필드의 Getter method를 자동 생성
@NoArgsConstructor      // 2. NoArgsConstructor : 기본 생성자 자동 추가. ex) public Posts() {} 와 같은 효과
@Entity                 // 3. Entity : 테이블과 링크될 클래스임을 나타 냄.
                        //          - 기본값으로 글래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭
public class Posts {

    @Id                                                     // 1. ID : 해당 테이블의 PK 필드를 나타냅니다
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 2. GeneratedValue : PK의 생성 규칙, spring boot 2.0 이상부터
    private Long Id;                                        // - GenerationType.IDENTITY 옵션을 추가해야한 auto_increment가 됨

    @Column(length = 500, nullable = false)                 // 3. Column : 테이블의 칼럼을 나타냄, 기본값 외에 추가로 변경된 옵션이 있으면 사용
    private String title;                                   // - 문자열의 경우 VARCHAR(255)가 기본값, 사이즈를 500으로 늘리고 싶거나(ex.title)
                                                            // - 타입을 TEXT로 변경하고 싶거나 (ex: content) 등의 경우에 사용 됩니다.

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder                                                        // 4. Builder : 해당 클래스의 빌더 패턴 클래스 생성
    public Posts(String title, String content, String author) {     // - 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
        this.title = title;
        this.content = content;
        this.author = author;

    }


}
