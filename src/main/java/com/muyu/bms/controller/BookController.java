package com.muyu.bms.controller;

import com.muyu.bms.service.BookService;
import com.muyu.bms.vo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BookController {

	@Autowired
	BookService bs;
	@RequestMapping("addBook")
	public String addBook(Book book){
		if(bs.addBook(book)){
			return "redirect:queryAllBookToIndex";
		}
		return "addBook";
	}

	@RequestMapping("queryAllBookToIndex")
	public String queryAllBookToIndex(Model model){
		List<Book> books = bs.queryAllBook();
		model.addAttribute("books",books);
		return "index";
	}
}
