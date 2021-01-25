package com.muyu.bms.controller;

import com.muyu.bms.service.DocuService;
import com.muyu.bms.vo.Docu;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DocumentController {

	@Autowired
	DocuService docuService;

	@RequestMapping("queryDocument")
	@ResponseBody
	public String queryDocument(@RequestParam("documentName") String documentName){
		System.out.println(documentName);
		List<Docu> list = docuService.queryDocu(documentName);
		String s = JSONArray.fromObject(list).toString();
		return s;
	}
}
