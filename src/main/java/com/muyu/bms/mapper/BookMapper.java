package com.muyu.bms.mapper;

import com.muyu.bms.vo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {
	int addBook(@Param("book") Book book);

	List<Book> queryAllBook();
}
