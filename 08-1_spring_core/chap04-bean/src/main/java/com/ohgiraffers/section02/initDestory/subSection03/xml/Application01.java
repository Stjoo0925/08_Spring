package com.ohgiraffers.section02.initDestory.subSection03.xml;

import com.ohgiraffers.common.Berverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application01 {

    /**
     * bean scope란 스프링 빈이 생성될 때 생성되는 인스턴스의 범위를 의미한다. 스프링에서는 다양한 bean scope를 제공한다.
     * singleton 하나의 인스턴스만 생성하고, 모든 빈이 해당 인스턴스를 공유하며 사용된다.
     * prototype 매번 새로운 인스턴스를 생성한다.
     * request http 요청을 처리할 때마다 새로운 인스턴스를 생성하고, 요청 처리가 끝나면 인스턴스를 폐기한다. 웹 애플리케이션 컨텍스트만 해당된다.
     * globalSession 전역 세션 당 하나의 인스턴스를 생성하고, 전역 세션이 종료되면 인스턴스를 폐기한다. 포털 애플리케이션 컨텍스트에만 해당한다.
     */

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext();
        String[] beanNames = context.getBeanDefinitionNames();
        for (String bean : beanNames) {
            System.out.println("beanName : " + bean);
        }
        // 붕어빵, 딸기우유, 지리산 암반수 등의 빈 객체를 반환한다.
        Product carpBread = context.getBean("carpBread", Bread.class);
        Product milk = context.getBean("milk", Berverage.class);
        Product water = context.getBean("water", Berverage.class);

        ShoppingCart cart1 = (ShoppingCart) context.getBean("cart");
        cart1.addItem(carpBread);
        cart1.addItem(milk);

        System.out.println("cart1에 담긴 내용 : " + cart1.getItems());

        ShoppingCart cart2 = context.getBean("cart",ShoppingCart.class);
        cart2.addItem(water);

        System.out.println("cart2에 담긴 내용 : " + cart2.getItems());

        System.out.println("======= cart1과 cart2 비교하기 =======");
        System.out.println("cart1에 담긴 내용 : " + cart1.getItems());
        System.out.println("cart2에 담긴 내용 : " + cart2.getItems());

        ((AnnotationConfigApplicationContext) context).close();
    }
}
