package com.ohgiraffers.mapping.section01.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;


/**
 * @Entity 어노테이션은 jpa에서 사용되는 엔티티 클래스임을 표시한다.
 * 이 어노테이션을 사용하면 해당 클래스가 데이터베이스와 테이블과 매핑된다.
 * @Entity 어노테이션은 클래스 선언 위에 위치해야한다.
 * 또한, name 속성을 사용하여 엔티티의 이름을 지정해줄 수 있으며
 * 생략하면 자동으로 클래스 이름을 엔티티명으로 사용한다.
 *
 * - 프로젝트 내에 다른 패키지에도 동일한 엔티티가 존재하는 경우 해당 엔티티를 식별하기 위한 중복되지 않은
 *   name 을 지정해 주어야 한다.
 * - 기본생성자는 필수로 작성해야한다.
 * - final 클래스, enum, interface, inner class에서는 사용할 수 없다.
 * - 저장할 필드에 final을 사용하면 안된다.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "member_section01")
@Table(name = "tbl_member_section01")
public class Member {

    @Id
    @Column(name = "memeber_no")
    private int memberNo;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "member_password")
    private String memberPwd;

    @Column(name = "member_nickname")
    private String Nickname;

    @Column(name = "member_phone")
    private String phone;

    @Column(name = "member_address")
    private String address;

    @Column(name = "member-enrol")
    private Date enrollDate;

    @Column(name = "member_role")
    private String memberRole;

    @Column(name = "status")
    private String status;
}
