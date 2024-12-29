package com.devbig.spring.domain.posts;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   // JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드(createdDate, modifiedDate)도 칼럼으로 인식함
@EntityListeners(AuditingEntityListener.class)  // 이 클래스에 Auditing 기능을 포함 시킴
public abstract class BaseTimeEntity {

    @CreatedDate    // Entity가 생성되어 저장될 때 시간이 자동 저장됨
    private LocalDateTime createDate;

    @LastModifiedDate   // 조회한 Entity의 값을 변경할 때 시간이 자동 저장됨
    private  LocalDateTime modifiedDate;

    // 마지막으로 JPA Auditing 어노테이션들을 모두 활성화할 수 있도록 Application class에 활성화 어노테이션을 하나 추가함
}

