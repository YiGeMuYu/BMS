package com.muyu.bms.controller;

import com.muyu.bms.service.BookService;
import com.muyu.bms.service.BorrowService;
import com.muyu.bms.vo.Book;
import com.muyu.bms.vo.Borrow;
import com.muyu.bms.vo.Student;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BorrowController {

	@Autowired
	BookService bookService;
	@Autowired
	BorrowService borrowService;
	/**
	 * 通过bid查询书籍，进入借书页面
	 * @return
	 */
	@RequestMapping("/queryBookToBorrowPage")
	public String queryBookToBorrowPage(@RequestParam("bid") String bid,Model model){
		if("query".equals(bid)){
			System.out.println("需要查询");
		}else{
			Book book = bookService.queryBookById(bid);
			System.out.println(book);
			model.addAttribute("book",book);
		}
		return "borrow/borrowBook";
	}

	//ajax异步查询学生信息
	@RequestMapping("/queryStudentBySid")
	@ResponseBody
	public String queryStudentBySid(@RequestParam("sid") Integer sid){
		Student student = borrowService.queryStudentAndStudentBorrowStatusBySid(sid);
		String s = JSONArray.fromObject(student).toString();
		return s;
	}

	//ajax异步查询学生信息
	@RequestMapping("/queryStudentAndBorrowBookBySid")
	@ResponseBody
	public String queryStudentAndBorrowBookBySid(@RequestParam("sid") Integer sid){
		List<Borrow> borrows = borrowService.queryBorrowBookListBySidAndBorrowStatus(sid);
		String s = JSONArray.fromObject(borrows).toString();
		return s;
	}


	//借书
	@RequestMapping("/borrowBook")
	@ResponseBody
	public String borrowBook(Borrow borrow){
		if(borrowService.borrowBook(borrow)){
			return "success";
		}else{
			return "false";
		}
	}

	//还书
	@RequestMapping("/returnBook")
	@ResponseBody
	public String returnBook(@RequestParam("borrowId") int borrowId,@RequestParam("bid") String bid){
		if(borrowService.returnBook(borrowId,bid)){
			return "还书成功";
		}else{
			return "还书失败";
		}
	}


	@RequestMapping("/toBookRank")
	public String toBookRank(Model model){
		List<Book> books = borrowService.queryBookRank();
		model.addAttribute("books",books);
		return "borrow/bookRank";
	}
}
