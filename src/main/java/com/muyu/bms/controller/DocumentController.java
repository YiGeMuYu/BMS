package com.muyu.bms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DocumentController {

	@RequestMapping("queryDocument")
	@ResponseBody
	public String queryDocument(@RequestParam("documentName") String documentName){

		return "";
	}
}
