package com.kim0han.springboot.controller;

import com.kim0han.springboot.sevice.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // Bean 끼리 연결 시켜줌 -> Dependency Injection
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


}
