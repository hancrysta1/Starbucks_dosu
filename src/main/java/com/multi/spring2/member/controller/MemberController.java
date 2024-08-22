package com.multi.spring2.member.controller;

import com.multi.spring2.member.domain.MemberVO;
import com.multi.spring2.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member")
public class MemberController {

    private MemberService memberService;

    public MemberController(){
        System.out.println("MemberController created");
    }

    @Autowired
    public MemberController(MemberService memberService) {
        System.out.println("MemberController created");
        this.memberService = memberService;
    }

    @GetMapping
    public String member(){
        return "member/member";
    }

    @PostMapping("/insert")
    public ModelAndView insert(MemberVO memberVO){
        memberService.insert(memberVO);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("member/insert");
        modelAndView.addObject("memberVO", memberVO);
        return modelAndView;
    }
}