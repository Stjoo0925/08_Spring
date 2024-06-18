package com.ohgiraffers.chap07thymeleaf.model.dto;

public class MemberDTO {
    private String Name;
    private int age;
    private char gender;
    private String address;

    public MemberDTO() {
    }

    public MemberDTO(String name, int age, char gender, String address) {
        Name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "Name='" + Name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                '}';
    }
}

