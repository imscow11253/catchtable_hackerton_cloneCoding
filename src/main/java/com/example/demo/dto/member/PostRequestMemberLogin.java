package com.example.demo.dto.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostRequestMemberLogin {

    @NotBlank
    @Size(min = 1, max =50)
    private String member_identifier;

    @NotBlank
    @Size(min = 1, max =50)
    private String password;
}
