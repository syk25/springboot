package com.kim0han.springboot.config;

import com.kim0han.springboot.repository.*;
import com.kim0han.springboot.sevice.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    // 1. Jdbc Template 을 이용하는 경우
    /*private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }*/

    // 2. Jpa를 이용한 환경 설정
/*
    private final DataSource dataSource;
    private final EntityManager em;

    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }
*/

    // 3. SpringJpa를 이용하는 경우
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*@Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }


    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
    }*/

    // SpringJpa 를 쓰는 경우
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }
}
