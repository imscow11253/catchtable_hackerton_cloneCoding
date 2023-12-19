package com.example.demo.dao;

import com.example.demo.dto.member.PostRequestMemberCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Slf4j
@Repository
public class MemberDao {
    private final JdbcTemplate jdbcTemplate;
    private long userId;

    public MemberDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.userId = 1;
    }

    public boolean hasDuplicateIdentifier(String memberIdentifier) {
        log.info("MemberDao.hasDuplicateIdentifier");

        String sql = "SELECT EXISTS(SELECT member_indentifier FROM `member` WHERE member_indentifier=?)";
        return Boolean.TRUE.equals(this.jdbcTemplate.queryForObject(sql, boolean.class, memberIdentifier));
    }

    public boolean hasDuplicatePhone(String phone) {
        log.info("MemberDao.hasDuplicatePhone");

        String sql = "SELECT EXISTS(SELECT phone FROM `member` WHERE phone=?)";
        return Boolean.TRUE.equals(this.jdbcTemplate.queryForObject(sql, boolean.class, phone));
    }

    public long createMember(PostRequestMemberCreate postRequestMemberCreate) {
        log.info("MemberDao.createMember");

        String sql = "INSERT INTO `member` (`member_id`, `member_indentifier`, `password`, `nickname`, `name`, `phone`, `member_type`) VALUES (?,?,?,?,?,?,?)";
        String identifier = postRequestMemberCreate.getMember_identifier();
        String password = postRequestMemberCreate.getPassword();
        String nickname = postRequestMemberCreate.getNickname();
        String name = postRequestMemberCreate.getName();
        String phone = postRequestMemberCreate.getPhone();
        String memberType = postRequestMemberCreate.getMember_type();

        System.out.println(identifier);

        this.jdbcTemplate.update(sql, ++this.userId, identifier, password,nickname,name,phone,memberType);

        return this.userId;
    }

    public String getMemberIdentifierById(long memberId) {
        log.info("MemberDao.getMemberIdentifierById");

        String sql = "SELECT member_indentifier FROM `member` WHERE member_id=?";
        return this.jdbcTemplate.queryForObject(sql, String.class, memberId);
    }

    public String getMemberNicknameById(long memberId) {
        log.info("MemberDao.getMemberNicknameById");

        String sql = "SELECT nickname FROM `member` WHERE member_id=?";
        return this.jdbcTemplate.queryForObject(sql, String.class, memberId);
    }

    public String getMemberTypeById(long memberId) {
        log.info("MemberDao.getMemberTypeById");

        String sql = "SELECT member_type FROM `member` WHERE member_id=?";
        return this.jdbcTemplate.queryForObject(sql, String.class, memberId);
    }


    public boolean hasDuplicatePassword(String password) {
        log.info("MemberDao.hasDuplicatePassword");

        String sql = "SELECT EXISTS(SELECT password FROM `member` WHERE password=?)";
        return Boolean.TRUE.equals(this.jdbcTemplate.queryForObject(sql, boolean.class, password));
    }

    public long getMemberIdByIdentifier(String memberIdentifier) {
        log.info("MemberDao.getMemberIdByIdentifier");

        String sql = "SELECT member_id FROM `member` WHERE member_indentifier=?";
        return this.jdbcTemplate.queryForObject(sql, long.class, memberIdentifier);
    }
}

