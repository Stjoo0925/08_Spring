package com.ohgiraffers.section03.properties.subSection01.properties;

import com.ohgiraffers.common.Berverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Date;

@Configuration
// resources 하위의 경로롤 기술한다.
@PropertySource("section03/properties/subSection01/properties/product-info.properties")
public class ContextConfiguration {

    @Value("${bread.carpBread.name:팥붕어빵}")
    private String carpBreadName;

    @Value("${bread.carpBread.price:0}")
    private int carpBreadPrice;

    @Value("${berverage.milk.name:걍우유}")
    private String berverageMilkName;

    @Value("${berverage.milk.price:0}")
    private int berverageMilkPrice;

    @Value("${berverage.milk.capacity:0}")
    private int berverageMilkCapacity;

    @Bean
    public Product carpBread() {
        return new Bread(carpBreadName,carpBreadPrice,new Date());
    }

    @Bean
    public Product milk() {
        return new Berverage(berverageMilkName,berverageMilkPrice,berverageMilkCapacity);
    }

    @Bean
    public Product water(@Value("${berverage.water.name:물}") String waterName,
                         @Value("${berverage.water.price:0}") int waterPrice,
                         @Value("${berverage.water.capacity:0}") int waterCapacity)
    {
        return new Berverage(waterName,waterPrice,waterCapacity);
    }

    @Bean
    @Scope("prototype") // 하나의 빈을 공유 해서 사용하겠다는 의미
    public ShoppingCart cart() {
        return new ShoppingCart();
    }


}
