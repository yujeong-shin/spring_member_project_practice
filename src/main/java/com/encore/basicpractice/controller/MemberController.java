package com.encore.basicpractice.controller;

import com.encore.basicpractice.domain.MemberRequestDto;
import com.encore.basicpractice.domain.MemberResponseDto;
import com.encore.basicpractice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;

@Controller
public class MemberController {
    // MemberService와의 연결성 -> 생성자 주입 방식
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    //회원 목록 조회
    @GetMapping("members")
    public String members(Model model){

        model.addAttribute("members", memberService.findAll());
        return "member/memberList";
    }

    //회원 가입
    @GetMapping("members/create")
    public String createMember(){
        return "member/member-create-screen";
    }
    @PostMapping("members/create")
    public String create(MemberRequestDto memberRequestDto){
        memberService.createMember(memberRequestDto);
        return "redirect:/members";
    }

    //홈화면
    @GetMapping("/")
    public String home(){
        return "member/header";
    }

    //회원 상세 조회
    @GetMapping("member/find")
    public String findMember(@RequestParam("id") int id, Model model){
        try{
            MemberResponseDto memberResponseDto = memberService.findById(id);
            model.addAttribute("member", memberResponseDto);
            return "member/member-detail";
        }catch(EntityNotFoundException e){
            e.printStackTrace();
            return "404-error-page";
        }

    }

    //회원 삭제
    @GetMapping("member/delete")
    public String deleteMember(@RequestParam("id") int id){
        try{
            memberService.deleteMember(id);
            return "redirect:/members";
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return "404-error-page";
        }

    }

    //회원 수정
    @PostMapping("member/update")
    public String updateMember(MemberRequestDto memberRequestDto, Model model){
        try{
            memberService.updateMember(memberRequestDto);
            model.addAttribute("member", memberRequestDto);
            return "redirect:/member/find?id=" + memberRequestDto.getId();
        }catch(EntityNotFoundException e){
            e.printStackTrace();
            return "404-error-page";
        }
    }
}
