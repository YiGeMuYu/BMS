package com.muyu.bms.mapper;

import com.muyu.bms.vo.Borrow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BorrowMapper {
	int borrowBook(@Param("borrow")Borrow borrow);
}
