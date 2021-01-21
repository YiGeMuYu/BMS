package com.muyu.bms.controller;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.muyu.bms.service.BookService;
import com.muyu.bms.vo.Book;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class BookController {

	@Autowired
	BookService bs;

	//添加图书
	@RequestMapping("addBook")
	public String addBook(Book book, MultipartFile pic){
		if(bs.addBook(book, pic)){
			return "redirect:queryAllBookToIndex";
		}
		return "addBook";
	}

	//查询所有图书并且返回到index页面
	@RequestMapping("queryAllBookToIndex")
	public String queryAllBookToIndex(Model model){
		List<Book> books = bs.queryAllBook();
		model.addAttribute("books",books);
		return "index";
	}

	//进入修改图书的页面
	@RequestMapping("toUpdateBookPage")
	public String toUpdatePage(@RequestParam("bid") int bid, Model model){
		Book book = bs.queryBookById(bid);
		System.out.println(book);
		model.addAttribute("book",book);
		return "book/updateBook";
	}

	//更新图书信息
	@RequestMapping("updateBook")
	public String updateBook(Book book ,MultipartFile pic){
		if(bs.updateBook(book,pic)){
			return "redirect:queryAllBookToIndex";
		}
		return "redirect:queryAllBookToIndex";
	}

	//下架图书
	@RequestMapping("soldBook")
	public String soldBook(@RequestParam("bid") Integer bid){
		if(bs.soldBook(bid)) {
			return "redirect:queryAllBookToIndex";
		}else{
			System.out.println("下架失败");
			return "redirect:queryAllBookToIndex";
		}
	}

	//通过书籍名称like查询书籍，并返回JSON格式的数据
	@RequestMapping("selectBookByLikeBookName")
	@ResponseBody
	public String selectBookByLikeBookName(String bookName){
		List<Book> books = bs.queryBookByLikeBookName(bookName);
		String s = JSONArray.fromObject(books).toString();
		return s;
	}
}
