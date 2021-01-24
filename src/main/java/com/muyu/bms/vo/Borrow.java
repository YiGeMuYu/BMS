package com.muyu.bms.vo;

public class Borrow {
	private int borrowId;
	private int sid;
	private String bid;
	private String borrowTime;
	private String returnTime;
	//借书的状态，0：已还，1已借，2逾期
	private String borrowStatus;
	private Book book;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(int borrowId) {
		this.borrowId = borrowId;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public String getBorrowStatu() {
		return borrowStatus;
	}

	public void setBorrowStatu(String borrowStatu) {
		this.borrowStatus = borrowStatu;
	}

	@Override
	public String toString() {
		return "Borrow{" +
				"borrowId=" + borrowId +
				", sid=" + sid +
				", bid='" + bid + '\'' +
				", borrowTime='" + borrowTime + '\'' +
				", returnTime='" + returnTime + '\'' +
				", borrowStatus='" + borrowStatus + '\'' +
				", book=" + book +
				'}';
	}
}
