package com.guesthouse.likelion.repository;

import com.guesthouse.likelion.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long > {
    //회원정보 가져오기
    Member findByEmail(String memberId);
}
