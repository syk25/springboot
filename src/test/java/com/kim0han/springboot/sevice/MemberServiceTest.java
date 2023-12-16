package com.kim0han.springboot.sevice;

import com.kim0han.springboot.domain.Member;
import com.kim0han.springboot.repository.MemberRepository;
import com.kim0han.springboot.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given : 주어진 상황
        Member member = new Member();
        member.setName("hello");

        // when : 동작이 실행이 됨
        Long saveId = memberService.join(member);
        // then : 결과 비교
        Member findMember = memberService.findOne(member.getId()).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원예외() {
        // given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    public void 전체회원조회() {
        // given
        Member member1 = new Member();
        member1.setName("Spring1");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        memberService.join(member2);
        // when
        List<Member> result = memberService.findMembers();
        // then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void 회원1명조회() {
        // given
        Member member1 = new Member();
        member1.setName("Spring1");
        memberService.join(member1);
        // when
        Member result = memberService.findOne(member1.getId()).get();
        // then
        assertThat(result).isEqualTo(member1);
    }
}