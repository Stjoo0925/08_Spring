package com.ohgiraffers.mapping.section06.compositekey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class MemberPK implements Serializable {

    @Column(name = "member_no")
    private int memberNo;

    @Column(name = "member_id")
    private String memberId;

    // 해시 함수 또는 해시 알고리즘 또는 해시 함수 알고리즘은 임의의 길이의 데이터를 고정된 길이의 데이터로 매핑하는 함수이다.
    @Override
    public int hashCode() {
        return Objects.hash(memberNo, memberId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberPK memberPK = (MemberPK) o;
        return memberNo == memberPK.memberNo && Objects.equals(memberId, memberPK.memberId);
    }
}
