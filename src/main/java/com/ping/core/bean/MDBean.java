package com.ping.core.bean;

import java.io.File;
import java.io.IOException;

import com.ping.core.Constant;
import org.springframework.web.multipart.MultipartFile;

/**
 * 基于异步接收消息的MDB
 * @author tianym
 * @version 0.1
 */
public class MDBean {

	public void uploadImageSuccess(MultipartFile imageFile) {
		System.out.println(imageFile + "+++++++");
		String location = Constant.UPLOAD_IMAGE_LOCATION + "/" + imageFile.getOriginalFilename();
		try {
			imageFile.transferTo(new File(location));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
