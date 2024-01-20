package com.encore.basicpractice.service;

import java.util.List;

public class MemberService {
    // MemberRepository>SpringDataJpaMemberRepository와의 연결성 -> 생성자 주입 방식

    // 회원 목록 조회
    // List<MemberResponseDto> findAll()

    // 회원 가입
    // void createMember(MemberRequestDto memberRequestDto) throws ??Exception

    // 회원 상세 조회
    // Optional, 예외처리 디테일 챙기기
    // MemberResponseDto findById(int id) throws ??Exception

    // 회원 삭제
    // void deleteMember(int id) throws ??Exception

    // 회원 수정
    // void updateMember(MemberRequestDto memberRequestDto) throws ??Exception

}
