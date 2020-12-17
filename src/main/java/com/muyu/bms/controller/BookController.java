package com.muyu.bms.controller;

import com.muyu.bms.service.BookService;
import com.muyu.bms.vo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class BookController {

	@Autowired
	BookService bs;
	@RequestMapping("addBook")
	public String addBook(Book book, MultipartFile pic){
		if(bs.addBook(book, pic)){
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

	@RequestMapping("toUpdateBookPage")
	public String toUpdatePage(@RequestParam("bid") int bid, Model model){
		Book book = bs.queryBookById(bid);
		model.addAttribute("book",book);
		return "book/updateBook";
	}

	@RequestMapping("updateBook")
	public String updateBook(Book book ,MultipartFile pic){
		if(bs.updateBook(book,pic)){
			return "redirect:queryAllBookToIndex";
		}
		return "redirect:queryAllBookToIndex";
	}

	@RequestMapping("soldBook")
	public String soldBook(@RequestParam("bid") Integer bid){
		if(bs.soldBook(bid)) {
			return "redirect:queryAllBookToIndex";
		}else{
			System.out.println("下架失败");
			return "redirect:queryAllBookToIndex";
		}
	}
}
