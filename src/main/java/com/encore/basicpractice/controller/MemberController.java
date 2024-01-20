package com.encore.basicpractice.controller;

public class MemberController {
    // MemberService와의 연결성 -> 생성자 주입 방식

    //회원 목록 조회
    // Get - members
    // String members(Model, model)   return member/memberList

    //회원 가입
    // Get - members/create
    // String createMember()     return member/member-create-screen
    // Post - members/create
    // String create(MemberRequestDto memberRequestDto)     return redirect:/members

    //홈화면
    // Get - /
    // String home()     return member/header

    //회원 상세 조회
    // Get - member/find
    // String findMember(@RequestParam("id") int id, Model model)
    // return member/member-detail    에러 발생 시 return 404-error-page

    //회원 삭제
    // Get - member/delete
    // String deleteMember(@RequestParam("id") int id)
    // return redirect:/members    에러 발생 시 return 404-error-page

    //회원 수정
    // Post - member/update
    // String updateMember(MemberRequestDto memberRequestDto, Model model)
    // return redirect:/member/find?id= + memberRequestDto.getId();    에러 발생 시 return 404-error-page
}
