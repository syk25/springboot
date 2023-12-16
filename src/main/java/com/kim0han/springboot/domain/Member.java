package com.kim0han.springboot.domain;

import jakarta.persistence.*;

@SuppressWarnings("SpellCheckingInspection")
@Entity // Jpa 가 관리하는 Entity임을 표기
public class Member {
    // 비즈니스 요구사항 반영
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 식별자 표기, db에서 자동 부여
    private Long id; // 데이터 식별자 - 시스템이 저장함


    private String name; // 회원이름

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
