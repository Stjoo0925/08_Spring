package com.ohgiraffers.section01.simple;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*; // Assertions 내부의 static 메서드를 모두 임포트 하겠다는 뜻


import java.util.List;

public class SimpleJPQLTests {

    /**
     * jpql(java persistence query language)
     * jpql은 엔티티 객체를 중심으로 개발할 수 있는 객체 지향 쿼리이다
     * sql 보다 간결하며 특정 DBMS에 의존하지 않는다.
     * 방언을 통해 해당 DBMS에 맞는 sql을 실행하게 된다.
     */

    private static EntityManagerFactory emf;
    private EntityManager em;

    @BeforeAll
    public static void intiFactory() {
        emf = Persistence.createEntityManagerFactory("jpatest");
        // persistence-unit
    }

    @BeforeEach
    public void initManager() {
        em = emf.createEntityManager();
    }

    @AfterEach
    public void closeManager() {
        em.close();
    }

    @AfterAll
    public static void closeFactory() {
        emf.close();
    }

    /**
     * jpql의 기본 문법
     * select, update, delete 등의 키워드 사용은 sql과 동일하다.
     * insert 는 persist() 메소드를 사용한다.
     * 키워드는 대소문자를 구분하지 않지만, 엔티티와 속성은 대소문자를 구분함에 유의한다.
     * 엔티티 별칭은 필수로 작성해야 하며 별칭 없이 작성하면 에러가 발생한다.
     */

    /**
     * jpql 사용 방법은 다음과 같다.
     * 1. 작성한 jpql(문자열)을 'entityManager.createQuery()' 메소드를 통해 쿼리 객체로 만든다.
     * 2. 쿼리 객체는 'TypedQuery' 또는 'Query' 로 두가지가 있다.
     * 2-1 TypedQuery:  반환할 타입을 명확하게 지정하는 방식일 떄 사용하면 쿼리 객체의 메서드 실행을 결과로 지정하는 타입이 반환된다.
     * 2-2 Query: 반환할 타입을 명확하게 지정하지 않는 방식일 때 사용한다 실행결과로 object,object[] 타입을 반환한다.
     *
     * 3. 쿼리 객체 에서 제공하는 메서드 'getSingleResult()' 또는 'getResultList()'를 통해 쿼리를 실행하고 데이터 베이스를 조회한다.
     * 3-1 getSingleResult() : 결과가 정확히 한 행일경우 사용하며 없거나 많으면 예외가 발생한다.
     * 3-2 getResultList() : 결과가 2행 이상 일 경우 사용하며 결과가 없어도 빈 리스트를 반환한다.
     */

    @Test
    public void TypedQuery를_이용한_단일메뉴_조회_테스트() {
        String jpql = "SELECT m.menuName FROM menu_section01 as m WHERE m.menuCode = 7";
        TypedQuery<String> query = em.createQuery(jpql, String.class);
        String resultMenuName = query.getSingleResult();
        Assertions.assertEquals("민트미역국", resultMenuName);
    }

    @Test
    public void Query를_이용한_단일메뉴_조회_테스트() {
        String jpql = "SELECT m.menuName FROM menu_section01 as m WHERE m.menuCode = 6";
        Query query = em.createQuery(jpql);
        Object resultMenuName = query.getSingleResult();
        Assertions.assertTrue(resultMenuName instanceof String); // 조회한 타입이 같은지 검증하는 테스트
        Assertions.assertEquals("생마늘샐러드", resultMenuName);
    }

    @Test
    public void TypedQuery를_이용한_단일행_조회_테스트() {

        //when
        String jpql = "SELECT m FROM menu_section01 as m WHERE m.menuCode = 7";
        TypedQuery<Menu> query = em.createQuery(jpql, Menu.class);   //반환 타입을 row와 매핑할 엔티티 타입으로 설정

        Menu foundMenu = query.getSingleResult();

        //then
        Assertions.assertEquals(7, foundMenu.getMenuCode());
        System.out.println(foundMenu);
    }

    @Test
    public void TypedQuery를_이용한_다중행_조회_테스트() {

        //when
        String jpql = "SELECT m FROM menu_section01 as m";
        TypedQuery<Menu> query = em.createQuery(jpql, Menu.class);   //반환 타입을 row와 매핑할 엔티티 타입으로 설정

        List<Menu> foundMenuList = query.getResultList(); // n개의 결과를 리스트로 반환

        //then
        assertNotNull(foundMenuList);
        foundMenuList.forEach(System.out::println);
        // foundMenuList.forEach(System.out::println); == for(Menu m : foundMenuList) { System.out.println(m); }
        // (System.out::println) == (m -> System.out.println(m))
    }

    @Test
    public void distinct를_활용한_중복제거_여러_행_조회_테스트() {

        //when
        String jpql = "SELECT DISTINCT m.categoryCode FROM menu_section01 m"; // DISTINCT 키워드를 사용하여 중복을 제거
        TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);
        List<Integer> categoryCodeList = query.getResultList();

        //then
        assertNotNull(categoryCodeList);
        categoryCodeList.forEach(System.out::println);
    }

    @Test
    public void in_연산자를_활용한_조회_테스트() {
        /* categoryCode가 6, 10 인 메뉴 목록 조회 출력 */

        //when
        String jpql = "SELECT m FROM menu_section01 m WHERE m.categoryCode IN (6, 10)"; // 결과 값은 N개 - 결과를 알수 없기 때문에
        List<Menu> menuList = em.createQuery(jpql, Menu.class).getResultList();

        //then
        assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }

    @Test
    public void like_연산자를_활용한_조회_테스트() {
        /* 마늘이 들어가는 메뉴명을 가진 메뉴 목록 조회 출력 */

        //when
        String jpql = "SELECT m FROM menu_section01 m WHERE m.menuName LIKE '%마늘%'"; // 결과 값은 N개
        List<Menu> menuList = em.createQuery(jpql, Menu.class).getResultList();

        //then
        assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }
}
