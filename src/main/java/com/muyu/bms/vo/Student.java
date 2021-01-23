package com.muyu.bms.vo;

public class Student {
	private int sid;
	private String sname;
	private String grade;
	private String faculty;
	private String studentBorrowStatus;

	public String getStudentBorrowStatus() {
		return studentBorrowStatus;
	}

	public void setStudentBorrowStatus(String studentBorrowStatus) {
		this.studentBorrowStatus = studentBorrowStatus;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	@Override
	public String toString() {
		return "Student{" +
				"sid=" + sid +
				", sname='" + sname + '\'' +
				", grade='" + grade + '\'' +
				", faculty='" + faculty + '\'' +
				'}';
	}
}
