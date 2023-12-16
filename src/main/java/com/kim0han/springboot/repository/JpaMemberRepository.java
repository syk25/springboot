package com.kim0han.springboot.repository;

import com.kim0han.springboot.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SpellCheckingInspection")
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em; // 스프링부트에서 데이터베이스 관련정보를 매핑해서 전달해줌


    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // 멤버클래스의 id를 보고 해당 멤버 객체를 반환
        return Optional.ofNullable(member); // null 처리 위해 optional로 감싸서 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result =
                em.createQuery("select m from Member m where m.name = :name", Member.class)
                        .setParameter("name", name)
                        .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
