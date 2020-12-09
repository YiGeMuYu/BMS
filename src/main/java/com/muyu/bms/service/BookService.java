package com.muyu.bms.service;

import com.muyu.bms.vo.Book;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {
	boolean addBook(Book book, MultipartFile pic);

	List<Book> queryAllBook();

	Book queryBookById(int bid);
}
