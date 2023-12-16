package com.kim0han.springboot.repository;

import com.kim0han.springboot.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface MemberRepository {
    Member save(Member member); // 회원정보 저장

    // 아이디, 이름으로 회원정보 조회
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);

    // Optional
    /* null 객체를 대응하는 방법 중에 하나
    * */

    // 회원 전체를 조회
    List<Member> findAll();
}
