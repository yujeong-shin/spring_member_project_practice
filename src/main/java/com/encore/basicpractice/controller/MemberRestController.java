package com.encore.basicpractice.controller;

import com.encore.basicpractice.domain.MemberRequestDto;
import com.encore.basicpractice.domain.MemberResponseDto;
import com.encore.basicpractice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@RestController // 모든 메서드에 @ResponseBody
@RequestMapping("/rest")
public class MemberRestController {
    private final MemberService memberService;
    @Autowired
    public MemberRestController(MemberService memberService){
        this.memberService = memberService;
    }

    //회원 목록 조회
    @GetMapping("members")
    public List<MemberResponseDto> members(Model model){
        return memberService.findAll();
    }

    //회원 가입
    @PostMapping("members/create")
    public String create(MemberRequestDto memberRequestDto){
        memberService.createMember(memberRequestDto);
        return "ok";
    }

    //홈화면
    @GetMapping("/")
    public String home(){
        return "member/header";
    }

    //회원 상세 조회
    @GetMapping("member/find/{id}")
    public ResponseEntity<Map<String, Object>> findMember(@PathVariable int id){
        try{
            MemberResponseDto memberResponseDto = memberService.findById(id);
            return ResponseEntityController.responseMessage(HttpStatus.OK, memberResponseDto);
        }catch(EntityNotFoundException e){
            e.printStackTrace();
            return ResponseEntityController.errResponseMessage(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }

    //회원 삭제
    @DeleteMapping("member/delete/{id}")
    public String deleteMember(@PathVariable int id){
        memberService.deleteMember(id);
        return "ok";
    }

    //회원 수정
    @PatchMapping("member/update")
    public MemberResponseDto updateMember(MemberRequestDto memberRequestDto){
        memberService.updateMember(memberRequestDto);
        return memberService.findById(memberRequestDto.getId());
    }
}
