package com.ohgiraffers.section03.projection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

public class ProjectionTests {

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
     * 프로젝션(projection)
     * select 절에 조회할 대상을 지정하는 것
     * (select {프로젝션 대상} from)
     *
     * 프로젝션 대상은 4가지 방식이 있다.
     * 1. 엔티티 프로젝션
     * 원하는 객체를 바로 조회할 수 있다.
     * 조회된 엔티티는 연속성 컨텍스트에서 관리된다.
     */

    // 1. 엔티티 프로젝션
    @Test
    public void 단일_엔티티_프로젝션_테스트() {
        String jpql = "SELECT m FROM menu_section03 m";
        List<Menu> menuList = em.createQuery(jpql, Menu.class).getResultList();
        System.out.println(menuList.get(1));
        Assertions.assertNotNull(menuList);
    }

    @Test
    public void 양방향_연관관계_엔티티_프로젝션_테스트() {
        int menuCodeParameter = 3;
        String jpql = "SELECT m.categoryCode FROM bidirection_menu m WHERE m.menuCode = :menuCode";
        BIDirectionCategory categoryOfMenu = em.createQuery(jpql, BIDirectionCategory.class)
                .setParameter("menuCode", menuCodeParameter)
                .getSingleResult();

        Assertions.assertNotNull(categoryOfMenu);
        System.out.println(categoryOfMenu);
        Assertions.assertNotNull(categoryOfMenu.getMenuList());
        categoryOfMenu.getMenuList().forEach(System.out::println);
    }

    @Test
    public void 임베디드_타입_프로젝션_테스트() {

        //when
        String jpql = "SELECT m.menuInfo FROM embbeded_menu m";
        List<MenuInfo> menuInfoList = em.createQuery(jpql, MenuInfo.class).getResultList();

        //then
        Assertions.assertNotNull(menuInfoList);
        menuInfoList.forEach(System.out::println);

    }

}
