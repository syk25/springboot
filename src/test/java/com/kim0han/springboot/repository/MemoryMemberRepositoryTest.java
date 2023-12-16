package com.kim0han.springboot.repository;

import com.kim0han.springboot.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save() {
        // given
        Member member = new Member();
        member.setName("Spring");

        // when
        repository.save(member);

        // then
        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
        repository.clearStore();
    }

    @Test
    public void findById() {
        // given
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        // when
        Member result = repository.findById(member1.getId()).get();

        // then
        assertThat(result).isEqualTo(member1);
        repository.clearStore();
    }

    @Test
    public void findByName() {
        // given
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);
        // when
        Member result = repository.findByName(member2.getName()).get();
        // then
        assertThat(result).isEqualTo(member2);
        repository.clearStore();
    }

    @Test
    void findAll() {
        // given
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);
        // when
        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}