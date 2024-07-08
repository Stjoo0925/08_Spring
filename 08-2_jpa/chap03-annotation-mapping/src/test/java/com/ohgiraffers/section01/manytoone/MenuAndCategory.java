package com.ohgiraffers.section01.manytoone;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

/**
 * @JoinColumn은 다대일 연관 관계에서 사용되는 어노테이션이다.
 * @JoinColumn에서 사용할 수 있는 속성들은 다음과 괕다.
 *
 * -name : 참조하는 테이블의 컬러명을 지정한다.
 * -referencedColumnName : 참조되는 테이블의 컬럼명을 지정한다.
 * -nullable : 참조하는 테이블의 컬럼에 null을 허용할지 여부를 지정한다.
 * -insertable : 새로운 엔티티가 저장될 떄, 이 참조하는 컬럼이 sql query에 포함되어야 하는지 여부를 지정한다.
 * -updatable : 엔티티가 업데이트될 때, 이 참조하는 컬럼이 sql query에 포함되어야 하는지 여부를 지정한다.
 * -table : 참조하는 테이블을 지정한다.
 * -foreignKey : 참조하는 테이블의 외래키에 다한 추가 정보를 지정한다.
 *
 * @ManyToOne은 다대일 연관 관계에서 사용되는 어노테이션이다.
 * @ManyToOne에서 사용할 수 있는 속성들은 다음과 같다.
 * -casecade : 연관된 엔티티에 대한 영속성 전이 전략을 지정한다.
 * -fetch : 연관된 엔티티를 로딩하는 전략을 지정한다.
 * -optional : 연관된 엔티티가 필수인지 선택적인지 지정한다.
 */

@Data
@Entity(name = "menu_and_category")
@Table(name = "tbl_menu")
public class MenuAndCategory {

    @Id
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private Integer refCategoryCode;


}
