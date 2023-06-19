package com.example.noticesboard.adapter;


import com.example.noticesboard.entity.Member;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;

@Getter
public class MemberAdapter extends User {

    private Member member;

    public MemberAdapter(Member member) {
        super(member.getUsername(), member.getPassword(), Arrays.asList(new SimpleGrantedAuthority(member.getRole())));
        this.member = member;
    }

}
