package com.muyu.bms.service.impl;

import com.muyu.bms.service.DocuService;
import com.muyu.bms.vo.Docu;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DocuServiceImpl implements DocuService {
	@Override
	public Docu queryDocu(String documentName) {
		String url = "http://oar.nstl.gov.cn/Paper/Search?searchKey="+documentName+"&x=0&y=0";
		Document document = Jsoup.parse(url);
		document.getElementById("");
		return null;
	}
}
