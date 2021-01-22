package com.muyu.bms.mapper;

import com.muyu.bms.vo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentMapper {
	Student queryStudentBySid(@Param("sid") int sid);
}
