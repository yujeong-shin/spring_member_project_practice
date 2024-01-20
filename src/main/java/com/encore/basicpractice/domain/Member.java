package com.encore.basicpractice.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Member {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    private String name;
    @Column(nullable = false, length = 50)
    private String email;
    @Setter
    private String password;
    @Setter
    @Column(name = "created_time")
    @CreationTimestamp
    private LocalDateTime create_time;
    public Member(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public void updateMember(String name, String password){
        this.name = name;
        this.password = password;
    }
}
