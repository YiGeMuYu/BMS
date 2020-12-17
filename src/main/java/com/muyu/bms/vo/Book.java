package com.muyu.bms.vo;

public class Book {
	private int bid;
	private String bname;
	private String type;
	private String author;
	private String picture;
	//库存
	private int inventory;
	//借出去的图书数量
	private int borrowNum;

	private int bookStatus;

	public int getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(int bookStatus) {
		this.bookStatus = bookStatus;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public int getBorrowNum() {
		return borrowNum;
	}

	public void setBorrowNum(int borrowNum) {
		this.borrowNum = borrowNum;
	}

	@Override
	public String toString() {
		return "Book{" +
				"bid=" + bid +
				", bname='" + bname + '\'' +
				", type='" + type + '\'' +
				", author='" + author + '\'' +
				", picture='" + picture + '\'' +
				", inventory=" + inventory +
				", borrowNum=" + borrowNum +
				'}';
	}
}
