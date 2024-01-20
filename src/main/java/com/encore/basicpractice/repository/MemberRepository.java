package com.encore.basicpractice.repository;

import com.encore.basicpractice.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    List<Member> findAll();
    Member save(Member member);
    Optional<Member> findById(int id);
    void delete(Member member);
}
