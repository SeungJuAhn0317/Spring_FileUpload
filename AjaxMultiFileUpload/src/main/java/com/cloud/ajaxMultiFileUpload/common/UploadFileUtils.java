package com.cloud.ajaxMultiFileUpload.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);

	public static String uploadFile(String saveDir,
			String originalFileName, byte[] fileData) throws Exception{		
		String savedFileName = UUID.randomUUID().toString() +"_"+originalFileName;    
		String savedPath = calcPath(saveDir);    
		File target = new File(saveDir+savedPath,savedFileName);    
		FileCopyUtils.copy(fileData, target);    
		String formatName =  originalFileName.substring(originalFileName.lastIndexOf(".")+1);    
		String uploadedFileName = null;

		if(MediaUtils.getMediaType(formatName) != null){
			uploadedFileName = makeThumbnail(saveDir, savedPath, savedFileName);
		}else{
			uploadedFileName = makeIcon(savedPath, savedFileName);
		}	 	  
		return uploadedFileName;	
	}

	private static  String makeThumbnail(String saveDir, 
			String savedPath,String savedFileName)throws Exception{           
		BufferedImage sourceImg = ImageIO.read(new File(saveDir + savedPath, savedFileName));

		BufferedImage destImg = Scalr.resize(sourceImg, 
				Scalr.Method.AUTOMATIC, 
				Scalr.Mode.FIT_TO_HEIGHT,100);   
		String thumbnailName = saveDir + savedPath + File.separator +"s_"+ savedFileName;   
		File newFile = new File(thumbnailName);
		String formatName =	savedFileName.substring(savedFileName.lastIndexOf(".")+1);  

		ImageIO.write(destImg, formatName.toUpperCase(), newFile);	   
		return thumbnailName.substring(saveDir.length()).replace(File.separatorChar, '/');
	} 

	private static  String makeIcon(String savedPath,String savedFileName) throws Exception{	
		String iconName = savedPath +File.separator+ savedFileName;    
		return iconName.replace(File.separatorChar, '/');
	}  

	private static String calcPath(String uploadPath){    
		Calendar cal = Calendar.getInstance();    
		String yearPath = File.separator+cal.get(Calendar.YEAR);    
		String monthPath=yearPath+File.separator+
				new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);

		String datePath = monthPath + File.separator + 
				new DecimalFormat("00").format(cal.get(Calendar.DATE));

		makeDir(uploadPath, yearPath,monthPath,datePath);    
		logger.info(datePath);

		return datePath;
	}

	private static void makeDir(String uploadPath, String... paths){    
		if(new File(uploadPath+paths[paths.length-1]).exists()){
			System.out.println("Fsdfs");
			return;
		}

		for (String path : paths) {      
			File dirPath = new File(uploadPath + path);      
			if(! dirPath.exists() ){
				dirPath.mkdir();
			} 
		}
	}



}
