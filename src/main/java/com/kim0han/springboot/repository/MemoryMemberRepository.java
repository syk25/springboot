package com.kim0han.springboot.repository;

import com.kim0han.springboot.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    /*순서
    * 1. 메서드의 구현
    * 2. 테스트 케이스 작성 -> 테스트 코드로 이동해서 작성하기*/

    // 전제: 동시성 문제를 배제함
    private static Map<Long, Member> store = new HashMap<>(); // 실제 저장하는 공간
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null이 반환되어도 감쌈
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values()
                .stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store = new HashMap<Long, Member>();
    }
}
