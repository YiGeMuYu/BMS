package com.muyu.bms.mapper;

import com.muyu.bms.vo.Book;
import com.muyu.bms.vo.BookType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {
	int addBook(@Param("book") Book book);

	List<Book> queryAllBook();

	Book queryBookById(@Param("bid") String bid);

	boolean updateBookById(@Param("book") Book book);

	boolean updateBookStatusById(@Param("bid")String bid);

	List<Book> queryBookByLikeBookName(@Param("bookName") String bookName);

	List<BookType> queryBookType();

	BookType queryBookTypeByTid(@Param("tid") String tid);

	int queryBookNumByType(@Param("type") String type);

	List<Book> queryBookByKeyword(@Param("selectType")String selectType, @Param("keyword")String keyword);

	int updateBookInventoryAndBorrowNumById(@Param("bid") String bid);

}
