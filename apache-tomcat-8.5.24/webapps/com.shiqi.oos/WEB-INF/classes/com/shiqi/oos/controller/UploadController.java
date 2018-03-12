package com.shiqi.oos.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shiqi.oos.controller.base.BaseController;
import com.shiqi.oos.utils.FileUploadUtils;

/**
 * @ClassName UploadController
 * @Description 
 * @Author 修罗
 * @Date 2018年3月7日 下午8:59:14
 */
@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController {

	@Value("${UPLOAD_FILE_PATH}")
	private String fileUploadPath;
	
	/**
	 * 上传文件
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/uploadFile",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> uploadFile(MultipartFile file)
	{
		Map<String, String> retMap = new HashMap<String,String>();
		
		try {
			FileUploadUtils fileUploadUtils = new FileUploadUtils(fileUploadPath);
			String path = fileUploadUtils.uploadFile(file);
			
			if (StringUtils.isNotBlank(path)) {
				retMap.put("success", "true");
				retMap.put("message", "保存成功");
				retMap.put("url", path);
			}else {
				retMap.put("success", "false");
				retMap.put("message", "保存失败");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retMap;
		
	}
	
}

