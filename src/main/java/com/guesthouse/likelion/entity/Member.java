package com.guesthouse.likelion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter //영속성 보장을 위해 getter 만 가짐
@NoArgsConstructor
@Entity //데이터베이스 테이블과 맵핑되는 class 임을 명시
@Table(name = "member")
public class Member {
        //필드
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment 표시, 식별자표시
        @Column(name = "member_id", unique = true, nullable = false) //name 명시하면 데이터베이스 칼럼 이름 지정 가능 미지정시 필드명으로
        private Long memberId;

        @Column(length = 15, nullable = false) //length 로 최대 글자수 지정가능, null 가능여부 지정가능
        private String name;

        @Column(length = 100, nullable = false)
        private String password;

        @Column(length = 50, nullable = false)
        private String email;

        @Column(length = 20, nullable = false)
        private String contact;

}
