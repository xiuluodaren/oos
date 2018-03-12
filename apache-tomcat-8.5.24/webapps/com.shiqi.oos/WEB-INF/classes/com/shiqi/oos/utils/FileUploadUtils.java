package com.shiqi.oos.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传工具类
 * @author keiwu
 *
 */
@Component
public class FileUploadUtils {

	private String fileUploadPath;

	public FileUploadUtils(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
	}
	
	/**
	 * 文件上传
	 * Controller方法参数例子：@RequestParam("uploadFile") MultipartFile file
	 * 页面form参数加  enctype="multipart/form-data"
	 * @param file
	 * @return 返回保存的文件名 （如为null则为保存失败）
	 */
	public String uploadFile(MultipartFile file) {
		if (file.isEmpty()) {
			return null;
		}
		// 获取文件名
		String fileName = file.getOriginalFilename();
		String fileHeadName = fileName.substring(0, fileName.lastIndexOf("."));
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		String uuid = UUID.randomUUID().toString();
		
		StringBuffer sb = new StringBuffer();
		sb.append(fileUploadPath);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		sb.append("/" + format.format(new Date()) + "/");
		sb.append(uuid);
		sb.append(suffixName);
		
		
		StringBuffer returnFileName = new StringBuffer();
		returnFileName.append("/" + format.format(new Date()) + "/");
		returnFileName.append(uuid);
		returnFileName.append(suffixName);
		File dest = new File(sb.toString());
		// 检测是否存在目录
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
			return returnFileName.toString();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getFileUploadPath() {
		return fileUploadPath;
	}

	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
	}
	
}
