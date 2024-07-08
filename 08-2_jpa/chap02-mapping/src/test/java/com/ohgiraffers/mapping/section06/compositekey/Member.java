package com.ohgiraffers.mapping.section06.compositekey;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity(name = "member_section06")
@Table(name = "tbl_member_section06") // 어떤 테이블과 매핑할건지
public class Member {

    // 두가지의 값이 조인되어 생성될 때?
    // PK가 한 테이블에 두가지 일 경우나 PK가 없을때 값을 식별할 경우
    @EmbeddedId
    private MemberPK memberNo;

    @Column(name = "member_pwd")
    private String memberPwd;

    @Column(name = "nickName")
    private String nickName;
}
