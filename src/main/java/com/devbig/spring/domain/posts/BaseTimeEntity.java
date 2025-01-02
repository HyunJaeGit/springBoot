package com.devbig.spring.domain.posts;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


// JPA Auditing 기능을 사용해서 수정,조회 할때 시간 기록을 남기는 방식
// 1. Auditing 기능을 포함한 추상화 클래스를 생성
// 2. Posts 클래스가 BaseTimeEntity를 상속받게 extend
// 3. Application 메인 클래스에 @EnableJpaAuditing 어노테이션으로 Auditing 기능을 활성화

@Getter
@MappedSuperclass   // JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드(createdDate, modifiedDate)도 칼럼으로 인식함
@EntityListeners(AuditingEntityListener.class)  // 이 클래스에 Auditing 기능을 포함 시킴
public abstract class BaseTimeEntity {

    @CreatedDate    // Entity가 생성되어 저장될 때 시간이 자동 저장됨
    private LocalDateTime createDate;

    @LastModifiedDate   // 조회한 Entity의 값을 변경할 때 시간이 자동 저장됨
    private  LocalDateTime modifiedDate;

}

