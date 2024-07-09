package com.ohgiraffers.section04.function;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

public class GroupFunctionTests {

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
     * JPQL의 그룹함수는 COUNT, MAX, MIN, AVG, SUM로 SQL의 그룹함수와 별반 차이가 없다.
     * 단 몇가지 주의사항이 있다.
     * 1. 그룹함수의 반환 타입은 결과 값이 정수면 LONG, 실수면 DOUBLE이다.
     * 2. 값이 없는 상태에서는 count를 제외한 그룹 함수는 null이 되고 count만 0이 된다..
     * 따라서 반환 값으 담기 위해 선언하는 변수 타입을 기본자료형을 하게 되면, 조회결과를 언박싱 할때 npe가 발생한다.
     */

    @Test
    public void 특정_카테고리의_등록된_메뉴_수_조회() {
        int categoryCodeParam = 4;

        String  jpql = "SELECT COUNT(m.menuPrice) FROM menu_section04 m WHERE m.categoryCode = :categoryCode";
        long countOfMenu = em.createQuery(jpql, Long.class)
                .setParameter("categoryCode", categoryCodeParam)
                .getSingleResult();

        Assertions.assertTrue(countOfMenu >= 0);
        System.out.println(countOfMenu);
    }

    @Test
    public void count를_제외한_다른_그룹함수의_조회결과가_없는_경우_테스트() {

        //given
        int categoryCodeParameter = 2;

        //when
        String jpql = "SELECT SUM(m.menuPrice) FROM menu_section04 m WHERE m.categoryCode = :categoryCode";

//        long sum = em.createQuery(jpql, Long.class)
//                .setParameter("categoryCode", categoryCodeParameter)
//                .getSingleResult();
//
//        System.out.println(sum);
        // null인 값을 long에 담을수 없다

        //then
        Assertions.assertThrows(NullPointerException.class, () -> {
            /* 반환 값을 담을 변수의 타입을 기본 자료형으로 하는 경우 Wrapper 타입을 언박싱 하는 과정에서 NPE이 발생하게 된다. */
            long sumOfPrice = em.createQuery(jpql, Long.class)
                    .setParameter("categoryCode", categoryCodeParameter)
                    .getSingleResult();

        });

        Assertions.assertDoesNotThrow(() -> {
            /* 반환 값을 담는 변수를 Wrapper 타입으로 선언해야 null 값이 반환 되어도 NPE가 발생하지 않는다. */
            Long sumOfPrice = em.createQuery(jpql, Long.class)
                    .setParameter("categoryCode", categoryCodeParameter)
                    .getSingleResult();
        });
    }

    @Test
    public void groupby절과_having절을_사용한_조회_테스트() {

        //given
        int minPrice = 50000;
        Long minPriceLong = 50000L;

        //when
        String jpql = "SELECT m.categoryCode, SUM(m.menuPrice)"
                + " FROM menu_section04 m"
                + " GROUP BY m.categoryCode"
                + " HAVING SUM(m.menuPrice) >= :minPrice";

        List<Object[]> sumPriceOfCategoryList = em.createQuery(jpql, Object[].class)
                .setParameter("minPrice", minPrice)
                .getResultList();

        List<Object[]> sumPriceOfCategoryListToLong = em.createQuery(jpql, Object[].class)
                .setParameter("minPrice", minPriceLong)
                .getResultList();

        // spring boot 2.8? 2월 3.x.x

        //then
        Assertions.assertNotNull(sumPriceOfCategoryList);
        sumPriceOfCategoryList.forEach(row -> {
            Arrays.stream(row).forEach(System.out::println);
        });

        Assertions.assertNotNull(sumPriceOfCategoryList);
        sumPriceOfCategoryListToLong.forEach(row -> {
            Arrays.stream(row).forEach(System.out::println);
        });
    }
}
