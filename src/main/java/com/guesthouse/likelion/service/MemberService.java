package com.guesthouse.likelion.service;

import com.guesthouse.likelion.entity.Board;
import com.guesthouse.likelion.entity.Member;
import com.guesthouse.likelion.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j //log 찍어볼 수 있는 어노테이션
public class MemberService {

    private final MemberRepository memberRepository;

    //들어온 아이디와 일치하는 row 가져오기
    public Member getMember(String memberId) {
        return memberRepository.findByEmail(memberId);
    }

}
