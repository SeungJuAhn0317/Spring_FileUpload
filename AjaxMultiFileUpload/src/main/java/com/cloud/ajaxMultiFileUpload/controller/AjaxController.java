package com.cloud.ajaxMultiFileUpload.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.ajaxMultiFileUpload.common.UploadFileUtils;
import com.cloud.ajaxMultiFileUpload.service.AjaxService;

@Controller
public class AjaxController {
	
	@Resource(name="saveDir")
	String saveDir;
	
	@Autowired
	private AjaxService ajaxService;
	
	
	@GetMapping("/main.bbs")
	public String main() {
		return "uploadAjax";
	}
	
	@PostMapping("/uploadAjax.bbs")
	@ResponseBody
	public List<String> uploadAjax(@RequestPart("multiFile") List<MultipartFile> mFile) throws Exception{
		
		List<String> fileList= new ArrayList<>();
		
		for(MultipartFile file : mFile){			
			fileList.add(UploadFileUtils.uploadFile(saveDir,file.getOriginalFilename(),file.getBytes()));
		}
		return fileList;
	}
	
	
}
