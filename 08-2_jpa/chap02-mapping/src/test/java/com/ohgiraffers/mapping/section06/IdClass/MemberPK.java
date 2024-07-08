package com.ohgiraffers.mapping.section06.IdClass;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MemberPK implements Serializable {


    private int memberNo;


    private String memberId;

}
