package com.example.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadDownload
{
	private static final String UPLOAD_LOCATION = "e://uploaded_contentss/";
/*
	@RequestMapping("/show")
	public ModelAndView index(){
		System.out.println("indside index");
		return new ModelAndView("uploadIndex");
	}
*/
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String uploadContents(@RequestParam MultipartFile file,Model map) {
		System.out.println("in upload conts " + file.getContentType() + " "
				+ file.getSize());
		try {
			file.transferTo(new File(UPLOAD_LOCATION, file
					.getOriginalFilename()));
		}  catch (Exception e) {
			map.addAttribute("mesg", "Content Upload failed "+e.getMessage());
			return "upload_error";
		}
		map.addAttribute("mesg", file.getOriginalFilename()+" uploaded successfully , content type "+file.getContentType());
		return "upload_succ"+file.getOriginalFilename();
	}

	/*Download Function*/
	@RequestMapping(value="/downloadList",method = RequestMethod.GET, produces="application/JSON")
	public File[] showAllFiles() {
		File directory = new File(UPLOAD_LOCATION);
		File[] fList = directory.listFiles();
		for(File f:fList)
			System.out.println(f.getName());
		return fList;
	}



	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void downloadFile(@RequestParam String type,HttpServletResponse response) throws Exception{

		File file = null;
		System.out.println(type + " is the file info");
		file = new File(type);

		if(!file.exists()){
			String errorMsg = "Sorry, no such file available";
			System.out.println(errorMsg);
			return;
		}
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName()+"\"");
		response.setContentType("application/force-download");
		response.setContentLength((int) file.length());
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		FileCopyUtils.copy(inputStream, response.getOutputStream());


	}
}