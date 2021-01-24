package com.muyu.bms.mapper;

import com.muyu.bms.vo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {
	Student queryStudentBySid(@Param("sid") int sid);

	List<Student> queryAllStudent();
}
