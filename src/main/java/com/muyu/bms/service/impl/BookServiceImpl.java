package com.muyu.bms.service.impl;

import com.muyu.bms.mapper.BookMapper;
import com.muyu.bms.service.BookService;
import com.muyu.bms.vo.Book;
import com.muyu.bms.vo.BookType;
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
	BookMapper bookMapper;
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

	/**
	 * 添加图书
	 * 要求自动创建一个图书的编号，
	 * 编号由图书类型和图书个数构成
	 * @param book
	 * @param pic
	 * @return
	 */
	@Override
	public boolean addBook(Book book, MultipartFile pic) {
		transerterPictureFile(pic,book);
		//目前的type其实是bms_bookType中的tid
		String type = book.getType();
		//将tid转换成type
		String realType = bookMapper.queryBookTypeByTid(type).getType();
		book.setType(realType);
		//查询当前类型有几本书,并加一成为新书的id的一部分
		int num = bookMapper.queryBookNumByType(realType)+1;
		//将字符串拼接，最后的结果类似于HISTORY03
		book.setBid(type+num);
		System.out.println(type+num);
		int i = bookMapper.addBook(book);
		if(i>0){
			return true;
		}
		return false;
	}

	/**
	 * 查询所有的书籍
	 * 并将查询出来的书籍的所有图片的picture属性由绝对路径裁剪成相对路径
	 * @return
	 */
	@Override
	public List<Book> queryAllBook() {
		List<Book> books = bookMapper.queryAllBook();
		for (Book book : books) {
			String picture = book.getPicture();
			book.setPicture(pictureUrlTransverter(picture));
		}
		return books;
	}

	/**
	 * 从index页面进入修改图书界面的时候调用此方法
	 * 此方法可以通过图书的bid查询书籍
	 * @param bid
	 * @return
	 */
	@Override
	public Book queryBookById(String bid) {
		Book book = bookMapper.queryBookById(bid);
		book.setPicture(pictureUrlTransverter(book.getPicture()));
		return book;
	}

	/**
	 * 修改图书，首先判断图片是否为空，非空就上传图片到upload文件夹
	 * 并且获取原来图片的相对路径，经过拼接，成为绝对路径之后，将其删除
	 * @param book
	 * @param pic
	 * @return
	 */
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
		if(bookMapper.updateBookById(book)) {
			return true;
		}
		return false;
	}

	/**
	 * 下架图书，通过bid属性 使图书的bookstatus为0
	 * @param bid
	 * @return
	 */
	@Override
	public boolean soldBook(String bid) {
		return bookMapper.updateBookStatusById(bid);
	}

	/**
	 * 这是修改书籍的模糊查询，通过like模糊查询书籍名称
	 * @param bookName
	 * @return
	 */
	@Override
	public List<Book> queryBookByLikeBookName(String bookName) {
		List<Book> books = bookMapper.queryBookByLikeBookName(bookName);
		return books;
	}

	@Override
	public List<BookType> queryBookType() {
		return bookMapper.queryBookType();
	}

	/**
	 * 通过关键字查询书籍
	 * 查询到之后，用pictureUrlTransverter()改变图片的路径，使之为相对路径
	 * @param selectType
	 * @param keyword
	 * @return
	 */
	@Override
	public List<Book> queryBookByKeyword(String selectType, String keyword) {
		List<Book> books = bookMapper.queryBookByKeyword(selectType, keyword);
		for (Book book : books) {
			String picture = book.getPicture();
			book.setPicture(pictureUrlTransverter(picture));
		}
		return books;
	}
}
