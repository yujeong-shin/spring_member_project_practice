package com.encore.basicpractice.service;

import com.encore.basicpractice.domain.Member;
import com.encore.basicpractice.domain.MemberRequestDto;
import com.encore.basicpractice.domain.MemberResponseDto;
import com.encore.basicpractice.repository.MemberRepository;
import com.encore.basicpractice.repository.SpringDataJpaMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(SpringDataJpaMemberRepository springDataJpaMemberRepository){
        this.memberRepository = springDataJpaMemberRepository;
    }

    // 회원 목록 조회
    public List<MemberResponseDto> findAll(){
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();
        for(Member member : members){
            MemberResponseDto memberResponseDto = new MemberResponseDto();
            memberResponseDto.setId(member.getId());
            memberResponseDto.setName(member.getName());
            memberResponseDto.setEmail(member.getEmail());
            memberResponseDto.setPassword(member.getPassword());
            memberResponseDto.setCreate_time(member.getCreate_time());
            memberResponseDtos.add(memberResponseDto);
        }
        return memberResponseDtos;
    }

    // 회원 가입
    public void createMember(MemberRequestDto memberRequestDto) throws IllegalArgumentException{
        Member member = new Member(memberRequestDto.getName(), memberRequestDto.getEmail(), memberRequestDto.getPassword());
        memberRepository.save(member);
    }

    // 회원 상세 조회
    // Optional, 예외처리 디테일 챙기기
    public MemberResponseDto findById(int id) throws EntityNotFoundException {
        Member member = memberRepository.findById(id).orElseThrow(()->new EntityNotFoundException("검색하신 ID의 Member가 없습니다."));
        MemberResponseDto memberResponseDto = new MemberResponseDto();
        memberResponseDto.setId(member.getId());
        memberResponseDto.setName(member.getName());
        memberResponseDto.setEmail(member.getEmail());
        memberResponseDto.setPassword(member.getPassword());
        memberResponseDto.setCreate_time(member.getCreate_time());
        return memberResponseDto;
    }

    // 회원 삭제
    public void deleteMember(int id) throws EntityNotFoundException{
        Member member = memberRepository.findById(id).orElseThrow(()->new EntityNotFoundException("검색하신 ID의 Member가 없습니다."));
        memberRepository.delete(member);
    }

    // 회원 수정
     public void updateMember(MemberRequestDto memberRequestDto) throws EntityNotFoundException{
        Member member = memberRepository.findById(memberRequestDto.getId()).orElseThrow(()->new EntityNotFoundException("검색하신 ID의 Member가 없습니다."));
         //set 방식보다는 update 함수를 생성하는 방식을 주로 사용함
//        member.setName(memberRequestDto.getName());
//        member.setPassword(memberRequestDto.getPassword());
         member.updateMember(memberRequestDto.getName(), memberRequestDto.getPassword());
         memberRepository.save(member);
    }

}
