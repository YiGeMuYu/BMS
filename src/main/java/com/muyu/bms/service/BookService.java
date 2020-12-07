package com.muyu.bms.service;

import com.muyu.bms.vo.Book;

import java.util.List;

public interface BookService {
	boolean addBook(Book book);

	List<Book> queryAllBook();
}
