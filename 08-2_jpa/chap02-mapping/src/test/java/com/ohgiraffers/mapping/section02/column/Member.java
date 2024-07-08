package com.ohgiraffers.mapping.section02.column;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "member_section02")
@Table(name = "tbl_member_section02")
public class Member {

    @Id
    @Column(name = "memeber_no")
    private int memberNo;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "member_password")
    private String memberPwd;

    @Column(name = "member_nickname")
    @Transient
    private String Nickname;

    @Column(name = "member_phone", columnDefinition = "varchar(200) default '010-0000-0000'")
    private String phone;

    @Column(name = "member_address", unique = true)
    private String address;

    @Column(name = "member-enrol")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enrollDate;

    @Column(name = "member_role", nullable = false)
    private String memberRole;

    @Column(name = "status")
    private String status;
}
