package com.blogging.project.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blogging.project.services.FileService;

@Service
public class FileServiceImpl implements FileService  {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		//File Name
		String name= file.getOriginalFilename();
		//abc.png
		
		//random name generated file
		
		String randomId=UUID.randomUUID().toString();
		String fileName1=randomId.concat(name.substring(name.lastIndexOf(".")));
		
		//Fullpath
		
		String filepath=path + File.separator +fileName1;
		
		//create folder if not created
		 
		File f= new File (path);
		if(!f.exists()) {
			f.mkdir(); 
		}
		
		
		//file copy
		Files.copy(file.getInputStream(), Paths.get(filepath));
		
		
		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//db logic to return inputstream
		String fullpath=path+File.separator+fileName;
		InputStream is= new FileInputStream(fullpath);
		
		return is;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
