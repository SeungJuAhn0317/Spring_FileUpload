package com.cloud.ajaxMultiFileUpload.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.ajaxMultiFileUpload.dao.AjaxDao;

@Service
public class AjaxServiceImpl implements AjaxService {
	
	@Autowired
	private AjaxDao ajaxDao;
}
