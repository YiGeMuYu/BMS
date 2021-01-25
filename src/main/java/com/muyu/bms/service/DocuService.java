package com.muyu.bms.service;

import com.muyu.bms.vo.Docu;

import java.util.List;

public interface DocuService {
	List<Docu> queryDocu(String documentName);
}
