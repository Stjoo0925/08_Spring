package com.ohgiraffers.section05.join;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.stream.Stream;

public class JoinTests {

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
     * 조인의 종류
     * 1. 일반 조인 : 일반적인 sql 조인을 의미 (내부 조인, 외부 조인, 컬렉션 조인, 세타 조인)
     * 2. 페치 조인 : jpql에서 성능 최적화를 위해 제공하는 기능으로 연관된 엔티티나 컬렉션을 한 번에 조회 할 수 있다.
     * 지연 로딩이 아닌 즉시 로딩을 수행하며 join fetch를 사용한다.
     */

    @Test
    public void 내부조인을_이용한_조회_테스트() {
        String jpql = "SELECT m FROM menu_section05 m JOIN m.categoryCode c";

        List<Menu> menuList = em.createQuery(jpql, Menu.class).getResultList();

        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }

    @Test
    public void 외부조인을_이용한_조회_테스트() {
        // 연관 관계가 맺어진 엔티티들에 대한 LEFT  or RIGHT outer 조인을 말한다.
        String jpql = "SELECT m.menuName, c.categoryName FROM menu_section05 m RIGHT JOIN m.categoryCode c " +
                "ORDER BY m.categoryCode.categoryCode";

        List<Object[]> menuList = em.createQuery(jpql, Object[].class).getResultList();

        Assertions.assertNotNull(menuList);

        menuList.forEach(row -> {
            Stream.of(row).forEach(col -> System.out.println(col + ""));
            System.out.println();
        });

//        축약식
//        for (Object[] objs : menuList) {
//            for (Object obj : objs) {
//                System.out.println(obj);
//                System.out.println();
//            }
//            System.out.println();
//        }
    }

    @Test
    public void 세타조인을_이용한_조회_테스트() {
        // 세타조인은 조인 되는 모든 경우의 수를 다 반환하는 크로스 조인과 같다.
        // 엔티티들에 대한 조인을 말함
        String jpql = "SELECT c.categoryName, m.menuName FROM category_section05 c, menu_section05 m";

        List<Object[]> categoryList = em.createQuery(jpql, Object[].class).getResultList();

        Assertions.assertNotNull(categoryList);

        categoryList.forEach(row -> {
            Stream.of(row).forEach(col -> System.out.println(col + ""));
            System.out.println();
        });

//        축약식
//        for (Object[] objs : categoryList) {
//            for (Object obj : objs) {
//                System.out.println(obj);
//                System.out.println();
//            }
//            System.out.println();
//        }
    }

    @Test
    public void 페치조인을_이용한_조회_테스트() {
        // 페치 조인을 이용하면 처음 sql 실행 후 로딩을 할 때 조인 결과를 다 조회한 뒤에 사용하는 방식이기 때문에 쿼리 실행 횟수가 줄어든다.
        String jpql = "SELECT m FROM menu_section05 m JOIN FETCH m.categoryCode c";
        List<Menu> menuList = em.createQuery(jpql, Menu.class).getResultList();

        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);

        for (Menu m : menuList) {
            System.out.println(m);
        }
    }
}
