package com.ohgiraffers.chap02handler;


/**
 * DTO를 작성할 떄 커맨드객체로 이용하기 위해서는 Form의 name과 필드가 일치하게 만들어야 한다.
 */

public class MenuDTO {
    private String name;
    private int price;
    private int category;
    private String orderableStatus;
    // 기본 생ㅅ
    public MenuDTO() {
    }

    public MenuDTO(String name, int price, int category, String orderableStatus) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.orderableStatus = orderableStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
