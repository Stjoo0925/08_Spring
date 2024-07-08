package com.ohgiraffers.section02.parameter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

public class ParameterBindingTests {

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

    @Test
    public void 이름_기준_파라미터_바인딩_메뉴_목록_조회_테스트() {
        String menuName = "한우딸기국밥";
        String jpql = "SELECT m FROM menu_section02 m WHERE m.menuName = :menuName";

        List<Menu> menuList = em.createQuery(jpql, Menu.class)
                .setParameter("menuName", menuName)
                .getResultList();

        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }

    @Test
    public void 위치_기준_파라미터_바인딩_메뉴_목록_조회_테스트() {

        // 가능한 위의 방식을 이용하는 것이 유지보수에 좋다

        //given
        String menuNameParameter = "한우딸기국밥";

        //when
        String jpql = "SELECT m FROM menu_section02 m WHERE m.menuName = ?1"; // ?1 : ? 의 인덱스 위치를 잡아주는것

        List<Menu> menuList = em.createQuery(jpql, Menu.class)
                .setParameter(1, menuNameParameter)
                .getResultList();

        //then
        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }
}
