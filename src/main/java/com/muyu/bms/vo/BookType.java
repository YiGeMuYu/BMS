package com.muyu.bms.vo;

public class BookType {
	private String tid;
	private String type;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "BookType{" +
				"tid='" + tid + '\'' +
				", type='" + type + '\'' +
				'}';
	}
}
