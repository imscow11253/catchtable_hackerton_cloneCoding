package com.example.demo.controller;

import com.example.demo.dto.member.PostRequestMemberCreate;
import com.example.demo.dto.member.PostRequestMemberLogin;
import com.example.demo.dto.member.PostResponseMemberCreate;
import com.example.demo.exception.member.MemberException;
import com.example.demo.response.BaseResponse;
import com.example.demo.service.MemberService;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.response.status.BaseExceptionResponseStatus.INVALID_MEMBER_VALUE;
import static com.example.demo.util.BindingResultUtils.getErrorMessages;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("")
    public BaseResponse<PostResponseMemberCreate> createMember(@Validated @RequestBody PostRequestMemberCreate postRequestMemberCreate, BindingResult bindingResult){
        log.info("MemberController.createMember");

        if(bindingResult.hasErrors()){
            throw new MemberException(INVALID_MEMBER_VALUE, getErrorMessages(bindingResult));
        }

        return new BaseResponse<>(this.memberService.createMember(postRequestMemberCreate));
    }

    @PostMapping("/login")
    public BaseResponse<PostResponseMemberCreate> loginMember(@Validated @RequestBody PostRequestMemberLogin postRequestMemberLogin, BindingResult bindingResult){
        log.info("MemberController.loginMember");

        if(bindingResult.hasErrors()){
            throw new MemberException(INVALID_MEMBER_VALUE, getErrorMessages(bindingResult));
        }

        return new BaseResponse<>(this.memberService.loginMember(postRequestMemberLogin));

    }
}
