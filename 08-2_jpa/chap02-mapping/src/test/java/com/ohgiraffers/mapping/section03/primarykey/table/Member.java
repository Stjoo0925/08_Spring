package com.ohgiraffers.mapping.section03.primarykey.table;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "member_section03_table")
@Table(name = "tbl_member_section03_table")
@TableGenerator(
        name = "member_seq_table_generator",
        table = "tbl_my_sequences",
        pkColumnValue = "my_seq_member_no"
)
public class Member {

    @Id
    @Column(name = "memeber_no")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "member_seq_table_generator")
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
