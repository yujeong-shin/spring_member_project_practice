package com.encore.basicpractice.domain;

import lombok.Data;

@Data
public class MemberRequestDto {
    private int id;
    private String name;
    private String email;
    private String password;
}
