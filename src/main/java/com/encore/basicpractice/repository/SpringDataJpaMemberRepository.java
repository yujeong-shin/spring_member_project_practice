package com.encore.basicpractice.repository;

import com.encore.basicpractice.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data Jpa의 기본 기능을 쓰기 위해서는 JpaRepository를 상속해야함
// 상속 시 Entity명과 해당 Entitiy의 PK 타입을 명시
public interface SpringDataJpaMemberRepository extends MemberRepository, JpaRepository<Member, Integer> {
}
