package com.multi.spring2.member.controller;

import com.multi.spring2.member.domain.MemberVO;
import com.multi.spring2.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @GetMapping("/idduechk/{id}")
    public ResponseEntity<Void> idduechk(@PathVariable("id") String id){
        boolean result = memberService.idduechk(id);
        if(result){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(400).build();
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> insert(@ModelAttribute MemberVO memberVO){
        memberService.insert(memberVO);
        return ResponseEntity.ok().build(); // HTTP 200 OK 응답 반환
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@ModelAttribute MemberVO memberVO, HttpSession session){
        MemberVO user = memberService.one(memberVO.getId());
        if(user == null){
            return ResponseEntity.status(401).build();
        }else{
            System.out.println(user.getPwd() + " " + memberVO.getPwd());
            if(!user.getPwd().equals(memberVO.getPwd())){
                return ResponseEntity.status(402).build();
            }
        }
        session.setAttribute("user", user);

        return ResponseEntity.ok().build();
    }
    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session){
        if(session.getAttribute("user") == null){
            return ResponseEntity.status(401).build();
        }
        session.invalidate();
        return ResponseEntity.ok().build();
    }
}