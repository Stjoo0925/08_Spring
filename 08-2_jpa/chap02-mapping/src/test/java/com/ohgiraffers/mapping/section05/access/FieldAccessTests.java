package com.ohgiraffers.mapping.section05.access;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class FieldAccessTests {

    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    public void closeManager() {
        this.entityManager.close();
    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @Test
    public void 필드_접근_테스트() {
        Member member = new Member(0, "홍길동", "pass01", "use01");

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(member);
        transaction.commit();

        Member foundMember = entityManager.find(Member.class, 1);

        Assertions.assertEquals(member, foundMember);
    }

    @Test
    public void fieldAccessTests() {
        Member fieldMember = entityManager.find(Member.class, 1);

        Assertions.assertNotNull(fieldMember);
        System.out.println(fieldMember);
    }
}
