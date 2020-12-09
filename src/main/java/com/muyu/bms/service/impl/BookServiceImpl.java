package com.muyu.bms.service.impl;

import com.muyu.bms.mapper.BookMapper;
import com.muyu.bms.service.BookService;
import com.muyu.bms.vo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookMapper bm;
	@Override
	public boolean addBook(Book book, MultipartFile pic) {
		//获取图片本来的名字
		String originalFileName = pic.getOriginalFilename();
		//获取图片类型（获取扩展名）
		String name = originalFileName.substring(originalFileName.lastIndexOf("."));
		//设置随机名字uuid
		UUID uuid = UUID.randomUUID();
		//设置存放的文件夹
		String bookImage = "E:/code/IDEA code/BMS/src/main/resources/static/upload";
		String url = bookImage+"/"+uuid+name;
		if(pic.getSize()>0){
			File realPath = new File(url);
			book.setPicture(url);
			try {
				pic.transferTo(realPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		int i = bm.addBook(book);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public List<Book> queryAllBook() {
		List<Book> books = bm.queryAllBook();
		for (Book book : books) {
			String picture = book.getPicture();
			book.setPicture(pictureUrlTransverter(picture));
		}
		return books;
	}

	@Override
	public Book queryBookById(int bid) {
		Book book = bm.queryBookById(bid);
		book.setPicture(pictureUrlTransverter(book.getPicture()));
		return book;
	}

	public String pictureUrlTransverter(String picture){
		String str = picture.substring(picture.lastIndexOf("upload"));
		return str;
	}
}
