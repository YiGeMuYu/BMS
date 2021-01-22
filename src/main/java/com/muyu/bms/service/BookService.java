package com.muyu.bms.service;

import com.muyu.bms.vo.Book;
import com.muyu.bms.vo.BookType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {
	boolean addBook(Book book, MultipartFile pic);

	List<Book> queryAllBook();

	Book queryBookById(String bid);

	boolean updateBook(Book book ,MultipartFile pic);

	boolean soldBook(String bid);

	List<Book> queryBookByLikeBookName(String bookName);

	List<BookType> queryBookType();

	List<Book> queryBookByKeyword(String selectType, String keyword);


}
