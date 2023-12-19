package com.example.demo.service;

import com.example.demo.dao.MemberDao;
import com.example.demo.dto.member.PostRequestMemberCreate;
import com.example.demo.dto.member.PostRequestMemberLogin;
import com.example.demo.dto.member.PostResponseMemberCreate;
import com.example.demo.exception.member.MemberException;
import com.example.demo.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.demo.response.status.BaseExceptionResponseStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDao memberDao;
    private final JwtProvider jwtProvider;

    public PostResponseMemberCreate createMember(PostRequestMemberCreate postRequestMemberCreate) {

        log.info("MemberService.createMember");

        this.validateIdentifier(postRequestMemberCreate.getMember_identifier());
        this.validatePhoneNumber(postRequestMemberCreate.getPhone());
        this.validateMemberType(postRequestMemberCreate.getMember_type());

        long memberId = this.memberDao.createMember(postRequestMemberCreate);
        String memberIdentifier = this.memberDao.getMemberIdentifierById(memberId);
        String memberNickname = this.memberDao.getMemberNicknameById(memberId);
        String memberType = this.memberDao.getMemberTypeById(memberId);
        String jwt = this.jwtProvider.createToken(memberIdentifier,memberId);

        return new PostResponseMemberCreate(memberIdentifier, memberNickname,memberType, jwt);
    }

    public PostResponseMemberCreate loginMember(PostRequestMemberLogin postRequestMemberLogin) {
        log.info("MemberService.loginMember");

        this.checkIdentifier(postRequestMemberLogin.getMember_identifier());
        this.checkPassword(postRequestMemberLogin.getPassword());

        long memberId = this.memberDao.getMemberIdByIdentifier(postRequestMemberLogin.getMember_identifier());
        String memberNickname = this.memberDao.getMemberNicknameById(memberId);
        String memberType = this.memberDao.getMemberTypeById(memberId);
        String jwt = this.jwtProvider.createToken(postRequestMemberLogin.getMember_identifier(),memberId);

        return new PostResponseMemberCreate(postRequestMemberLogin.getMember_identifier(), memberNickname, memberType, jwt);
    }

    private void checkPassword(String password) {
        if(!this.memberDao.hasDuplicatePassword(password)){
            throw new MemberException(NO_EXIST_PASSWORD);
        }
    }

    private void checkIdentifier(String memberIdentifier) {
        if(!this.memberDao.hasDuplicateIdentifier(memberIdentifier)){
            throw new MemberException(NO_EXIST_IDENTIFIER);
        }
    }

    private void validateMemberType(String memberType) {
        if(!memberType.equals("customer") && !memberType.equals("owner")){
            throw new MemberException(INVALID_MEMBER_TYPE);
        }
    }

    private void validateIdentifier(String memberIdentifier){
        if(this.memberDao.hasDuplicateIdentifier(memberIdentifier)){
            throw new MemberException(DUPLICATE_MEMBER_IDENTIFIER);
        }
    }

    private void validatePhoneNumber(String phone){
        if(this.memberDao.hasDuplicatePhone(phone)){
            throw new MemberException(DUPLICATED_MEMBER_PHONENUMBER);
        }
    }

}
