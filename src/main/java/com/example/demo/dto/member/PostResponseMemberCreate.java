package com.example.demo.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostResponseMemberCreate {
    private String member_identifier;
    private String nickname;
    private String member_type;
    private String Authorization;
}
