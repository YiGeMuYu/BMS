package com.muyu.bms.mapper;

import com.muyu.bms.vo.Borrow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BorrowMapper {
	int borrowBook(@Param("borrow")Borrow borrow);

	List<Borrow> queryBorrowBookListBySid(@Param("sid") Integer sid);

	int returnBook(@Param("borrow")Borrow borrow);

	List<Borrow> queryBorrowBySid(@Param("sid") Integer sid);

	List<Borrow> queryBorrowBookListBySidAndBorrowStatus(@Param("sid") Integer sid);
}
