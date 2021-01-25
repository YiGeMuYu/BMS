package com.muyu.bms.vo;

public class Docu {
	private String title;
	private String realName;
	private String path;
	private String publishedInformation;
	private String discription;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPublishedInformation() {
		return publishedInformation;
	}

	public void setPublishedInformation(String publishedInformation) {
		this.publishedInformation = publishedInformation;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Docu{" +
				"title='" + title + '\'' +
				", realName='" + realName + '\'' +
				", path='" + path + '\'' +
				", publishedInformation='" + publishedInformation + '\'' +
				", discription='" + discription + '\'' +
				'}';
	}

}
