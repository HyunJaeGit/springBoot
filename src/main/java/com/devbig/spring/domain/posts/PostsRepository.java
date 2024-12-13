package com.devbig.spring.domain.posts;

// interface : 추상화 클래스
// - Posts class로 Database를 접근하게 해주는 JPA(Java Persistence API) Repository를 생성 (=Mybatis의 Dao)
// - 자바 객체를 관계형 데이터 베이스에 영속적으로 저장하고 조회할 수 있는 ORM 기술에 대한 표준 명세를 의미
// - SQL 쿼리를 작성하지 않고도(JDBC 코드없이) DB를 접근 객체지향적 코드 작성(유지 보수)
// - Entity class는 기본 Repository 없이는 제대로 역할할 수 가 없음 = domain 패키지로 함께 관리

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
                                // extends JpaRepository<Entity 클래스, PK타입> 을 상속하면
                                // - 기본적인 CRUD method가 자동으로 생성된다 >> PostsRepositoryTest 클래스에서
                                // - >> 테스트 할때 상속받지 않으면 CRUD 메소드를 찾을 수 없음



}
