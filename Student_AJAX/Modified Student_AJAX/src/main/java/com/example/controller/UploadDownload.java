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
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UploadDownload
{
	private static final String UPLOAD_LOCATION = "e://uploaded_contentss/";

	@RequestMapping("/show")
	public ModelAndView index(){
		System.out.println("indside index");
		return new ModelAndView("uploadIndex");
	}

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
		//		System.out.println("getting request "+type);
		file = new File(type);
		//		if(type.equalsIgnoreCase("internal")){
		//			file = new File(type);
		//			System.out.println(file);
		//		}else{
		//			file = new File(EXTERNAL_FILE_PATH+type);
		//			System.out.println(file);
		//		}

		if(!file.exists()){
			String errorMsg = "Sorry, no such file available";
			System.out.println(errorMsg);
			return;
		}
		response.setHeader("Content-Disposition", "attachment; filename=\"" + "file.getName\"");
		response.setContentType("application/force-download");
		//		response.setHeader("Content-Disposition",String.format("attachment; filename=\\'"+file.getName()+"'"));
		response.setContentLength((int) file.length());
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		FileCopyUtils.copy(inputStream, response.getOutputStream());


	}


	/*
	@RequestMapping(value="/download/{fileName}", method=RequestMethod.GET)
	public File downloadFile(@PathVariable String fileName) throws Exception{
		File directory = new File(UPLOAD_LOCATION);
		File[] flist = directory.listFiles();
		for(File f:flist)
			if(f.getName().equals(fileName)){
				try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))){
					return f;
				}
			}
		return null;
	}*/

	/*public static HashMap<String, Item> restoreItems() throws Exception {
		HashMap<String, Item> hm = new HashMap<>();
		File f1 = new File("items.ser");
		if (f1.exists() && f1.isFile() && f1.canRead()) {
			try (ObjectInputStream in = new ObjectInputStream(
					new FileInputStream(f1))) {
				hm = (HashMap<String, Item>) in.readObject();
			}
		}
		return hm;
	}*/

	/*
	 * Working Properly, saving inside project
	 * 
	 * @RequestMapping(value="/upload", method=RequestMethod.POST)
	public @ResponseBody String handleFileUpload( 
			@RequestParam() MultipartFile file){
		System.out.println(file + "is the file name.");
		String name = new File(file.getOriginalFilename()).getName();
		System.out.println(name + "is the name of the converted file name.");
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = 
						new BufferedOutputStream(new FileOutputStream(new File(name + " -uploaded")));
				stream.write(bytes);
				stream.close();

				return "You successfully uploaded " + name + " into " + name + " -uploaded !";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name + " because the file was empty.";
		}
	}*/

	/*	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
		@FormDataParam("file") InputStream uploadedInputStream,
		@FormDataParam("file") FormDataContentDisposition fileDetail) {
		System.out.println("inside upload");

		String uploadedFileLocation = "d://uploaded/" + fileDetail.getFileName();

		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);

		String output = "File uploaded to : " + uploadedFileLocation;

		return Response.status(200).entity(output).build();

	}

	private void writeToFile(InputStream uploadedInputStream,
			String uploadedFileLocation) {
		System.out.println("inside writeToFile");

			try {
				OutputStream out = new FileOutputStream(new File(
						uploadedFileLocation));
				int read = 0;
				byte[] bytes = new byte[1024];

				out = new FileOutputStream(new File(uploadedFileLocation));
				while ((read = uploadedInputStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				out.flush();
				out.close();
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
	 */
}