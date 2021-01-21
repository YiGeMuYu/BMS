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
	/*
	工具方法 start
	*/
	/**
	 * 将图片读取的路径剪辑成可访问图片的路径，然后存到数据库
	 * @param picture
	 * @return
	 */
	public String pictureUrlTransverter(String picture){
		String str = picture.substring(picture.lastIndexOf("upload"));
		return str;
	}

	/**
	 * 将图片上传到upload文件夹
	 * @param pic MultipartFile 将图片信息传入
	 * @param book 将book传入，直接修改book信息中的picture
	 * @return
	 */
	public void transerterPictureFile(MultipartFile pic,Book book){
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
	}

	/**
	 *删除文件
	 * @param fileName
	 * @return
	 */
	public boolean deleteFile(String fileName){
		File file = new File(fileName);
		if(!file.exists()){
			System.out.println("删除文件失败："+fileName+"不存在！");
			return false;
		}else{
			if(file.isFile()){
				return file.delete();
			}
		}
		return false;
	}
	/*
	工具方法end
	 */
	@Override
	public boolean addBook(Book book, MultipartFile pic) {
		transerterPictureFile(pic,book);
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
	@Override
	public boolean updateBook(Book book, MultipartFile pic) {
		if(pic.getSize()>0) {
			transerterPictureFile(pic, book);
			System.out.println("------------修改后的图片------------");
			System.out.println(book.getPicture());
			System.out.println("------------删除------------");
			String fileName="E:/code/IDEA code/BMS/src/main/resources/static/"+book.getBeforePicture();
			if(deleteFile(fileName)){
				System.out.println("删除"+fileName+"成功！");
			}
		}
		if(bm.updateBookById(book)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean soldBook(Integer bid) {
		return bm.updateBookStatusById(bid);
	}

	@Override
	public List<Book> queryBookByLikeBookName(String bookName) {
		List<Book> books = bm.queryBookByLikeBookName(bookName);
		return books;
	}



}
