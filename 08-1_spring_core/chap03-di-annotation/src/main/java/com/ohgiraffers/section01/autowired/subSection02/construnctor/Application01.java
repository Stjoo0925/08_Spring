package com.ohgiraffers.section01.autowired.subSection02.construnctor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application01 {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("com.ohgiraffers.section01");

        BookService bookService = context.getBean("bookServiceConstructor", BookService.class);

        bookService.selectAllBooks().forEach(System.out::println);

        System.out.println(bookService.searchBookBySequence(1));
        System.out.println(bookService.searchBookBySequence(2));
    }
}
