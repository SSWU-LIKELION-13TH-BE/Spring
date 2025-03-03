package com.guesthouse.likelion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "board")
public class Board {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "board_id", unique = true, nullable = false)
        private Long boardId;

        @Column(length = 15, nullable = false)
        private String title;

        @Column(length = 255, nullable = false)
        private String content;

        @Column(length = 15, nullable = false)
        private String writer;

        @Temporal(TemporalType.DATE) //date 타입 형식 결정하는 어노테이션 DATE 는 날짜만 표시 시간 x
        private Date postDate;


        @PrePersist //jpa 의 콜백 메서드 엔티티가 처음 저장되기 직전에 실행 즉 새로운 row 생성시 날짜 현재시간으로 저장해줌
        protected void onCreate() {
                this.postDate = new Date();
        }

}
