package com.example.demo.dto.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostRequestMemberCreate {

    @NotBlank
    @Size(min=1, max= 50)
    private String member_identifier;

    @NotBlank
    @Size(min=1, max = 50)
    private String password;

    @NotBlank
    @Size(min = 1, max =50)
    private String nickname;

    @NotBlank
    @Size(min =1, max=50)
    private String name;

    @NotBlank
    @Size(min = 1, max=50)
    private String phone;

    @NotBlank
    @Size(min = 1, max=50)
    private String member_type;
}
