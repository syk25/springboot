package com.kim0han.springboot.sevice;

import com.kim0han.springboot.domain.Member;
import com.kim0han.springboot.repository.MemberRepository;
import com.kim0han.springboot.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 비즈니스 로직 작성 영역
@Service
@Transactional
public class MemberService {

    // 저장소를 먼저 생성
    private final MemberRepository memberRepository;

    /* 비즈니스 기능
     * 1. 회원 등록
     * 2. 회원 조회*/

    // 1. 회원 등록 기능

    // DI : 생성자로 외부에서 의존성을 주입케 함
    @Autowired // 스프링 컨테이너에서 빈들을 연결시켜줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) { // 동작 자체는 의미론적으로 작성하기 - 동일 준위
        validateDuplicateMember(member); // 로직 속의 로직은 별개로 뽑아서 저장함
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { // 연쇄 로직이 존재하는 경우 별개의 메소드로 뽑기
        memberRepository.findByName(member.getName())
                .ifPresent(m ->
                        {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        }
                );
    }

    // 2. 회원 조회 기능

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
