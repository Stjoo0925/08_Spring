package com.ohgiraffers.section02.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class A_EntityManagerCRUDTests {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @AfterAll
    public static void closeManager() {
        entityManager.close();
    }

    @Test
    public void 메뉴코드로_메뉴_조회_테스트() {
        
        int menuCode = 2;

        Menu foundMenu = entityManager.find(Menu.class, menuCode);

        Assertions.assertNotNull(foundMenu);
        Assertions.assertEquals(menuCode, foundMenu.getMenuCode());
    }

}
