package com.muyu.bms.mapper;

import com.muyu.bms.vo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {
	int addBook(@Param("book") Book book);

	List<Book> queryAllBook();

	Book queryBookById(@Param("bid") int bid);

	boolean updateBookById(@Param("book") Book book);

	boolean updateBookStatusById(@Param("bid")int bid);
}
