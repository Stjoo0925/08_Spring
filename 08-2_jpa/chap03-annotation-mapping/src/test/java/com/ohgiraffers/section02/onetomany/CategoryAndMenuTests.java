package com.ohgiraffers.section02.onetomany;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

public class CategoryAndMenuTests {

    private static EntityManagerFactory emf;

    private EntityManager em;

    @BeforeAll
    public static void initFactory() { emf = Persistence.createEntityManagerFactory("jpatest"); }
    @BeforeEach
    public void initManager() { this.em = emf.createEntityManager(); }
    @AfterEach
    public void closeManager() { this.em.close(); }
    @AfterAll
    public static void closeFactory() { emf.close(); }

    @Test
    public void 일대다_연관관계_객체_그래프_탐색을_이용한_조회_테스트() {
        int categoryCode = 10;
        CategoryAndMenu categoryAndMenu = em.find(CategoryAndMenu.class, categoryCode);
        Assertions.assertNotNull(categoryAndMenu);
        System.out.println(categoryAndMenu);
    }

    @Test
    void 일대다_연관관계_객체_삽입_테스트() {
        CategoryAndMenu categoryAndMenu = new CategoryAndMenu();
        categoryAndMenu.setCategoryCode(888);
        categoryAndMenu.setCategoryName("일대다 추가 카테고리");
        categoryAndMenu.setRefCategoryCode(null);

        List<Menu> menuList = new ArrayList<>();
        Menu menu = new Menu();
        menu.setMenuCode(888);
        menu.setMenuName("일대다 아이스크림2");
        menu.setMenuPrice(80000);
        menu.setOrderableStatus("Y");
        menu.setCategoryCode(categoryAndMenu.getCategoryCode());

        menuList.add(menu);
        categoryAndMenu.setMenuList(menuList);

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(categoryAndMenu);
        tx.commit();

        CategoryAndMenu foundCategoryAndMenu = em.find(CategoryAndMenu.class, 888);
        System.out.println(foundCategoryAndMenu);

    }
}
