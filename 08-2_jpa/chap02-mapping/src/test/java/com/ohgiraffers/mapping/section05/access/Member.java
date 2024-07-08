package com.ohgiraffers.mapping.section05.access;

import jakarta.persistence.*;
import lombok.*;

/**
 * 필드 접근이 기본 값이므로 해당 설정은 제거해도 동일하게 동작한다.
 * 또한 필드 레벨과 프로퍼티 레벨을 모두 선언하면 프로퍼티 레벨을 우선으로 한다.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "member_section05")
@Table(name = "tbl_member_section05")
// 1. 클래스 레벨 : 모든 필드에 대해서 필드 접근 방식을 적용한다.
@Access(AccessType.FIELD)
public class Member {

    // 2. 필드 레벨 : 해당 필드에 대해서 필드 접근 방식을 적용한다.
    @Id
    @Column(name = "memeber_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.FIELD)
    private int memberNo;

    @Column(name = "member_id")
    @Access(AccessType.FIELD)
    private String memberId;

    @Column(name = "member_password")
    @Access(AccessType.FIELD)
    private String memberPwd;

    @Column(name = "member_nickname")
    @Transient
    @Access(AccessType.FIELD)
    private String Nickname;
}
