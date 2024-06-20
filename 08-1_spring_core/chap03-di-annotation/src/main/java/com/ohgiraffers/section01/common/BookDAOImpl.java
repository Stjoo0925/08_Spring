package com.ohgiraffers.section01.common;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("bookDAO")
public class BookDAOImpl implements BookDAO{

    private Map<Integer, BookDTO> bookList;

    public BookDAOImpl() {
        this.bookList = new HashMap<>();
        bookList.put(1, new BookDTO(1,123,"자바의정석","남궁성","도우출판", new Date()));
        bookList.put(2, new BookDTO(2,123,"자바의정석","남궁성","도우출판", new Date()));
    }

    @Override
    public List<BookDTO> selectBookList() {
        return new ArrayList<>(bookList.values());
    }

    @Override
    public BookDTO selectOneBook(int sequence) {
        return bookList.get(sequence);
    }
}
