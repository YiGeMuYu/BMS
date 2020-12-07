package com.muyu.bms.service.impl;

import com.muyu.bms.mapper.BookMapper;
import com.muyu.bms.service.BookService;
import com.muyu.bms.vo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookMapper bm;
	@Override
	public boolean addBook(Book book) {
		int i = bm.addBook(book);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public List<Book> queryAllBook() {
		return bm.queryAllBook();
	}
}
