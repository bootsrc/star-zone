package org.flylib.mall.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.flylib.config.GlobalCustomConfig;
import org.flylib.mall.common.util.SnowflakeIdWorker;
import org.flylib.mall.shop.entity.jpa.FileResource;
import org.flylib.mall.shop.service.FileResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("resourceapi")
public class ResourceController {
	final Logger logger = LoggerFactory.getLogger(ResourceController.class);
	private static final String URL_PATH_MIDDLE = "star-sign";

	@Resource
	private FileResourceService fileResourceService;

	@Resource
	private GlobalCustomConfig globalCustomConfig;

	@Resource
    private SnowflakeIdWorker snowflakeIdWorker;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(
            value = "/**",
            method = RequestMethod.OPTIONS
    )
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @ResponseBody
	@RequestMapping(value = "upload", method = RequestMethod.POST)
    public JSONObject upload(HttpServletRequest request,
                            @RequestParam("creater") String creater,
                            @RequestParam("upfile") MultipartFile multipartFile) {
		FileResource res = new FileResource();
		try {
//			byte[] packageFile = multipartFile.getBytes();
			String originFileName = multipartFile.getOriginalFilename();
			res.setCreater(creater);
			
			res.setFileName(originFileName);
			// 定义上传路径
			String path = globalCustomConfig.getResourcePath() + "/" + URL_PATH_MIDDLE ;
			File parentFile = new File(path);
			if (!parentFile.exists()) {
				parentFile.mkdirs();
			}
			
			if (originFileName.trim() != "") {
				long fileId = snowflakeIdWorker.nextId();
				// 文件后缀
				String type = originFileName.substring(originFileName.lastIndexOf("."));
				// 重命名上传后的文件名
				String newName = fileId + type;
				res.setFileId(fileId);
                res.setFileName(originFileName);
				res.setFileType(type);
				res.setCreateDate(new Date());
				res.setUrl("/" + URL_PATH_MIDDLE + "/" + newName);
				// 插入数据库
				fileResourceService.insert(res);
				// 本地的新文件
				File localFile = new File(path, newName);
				// 将上传文件写入本地文件
				multipartFile.transferTo(localFile);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JSONObject obj = new JSONObject();
		obj.put("success", true);
		obj.put("msg", "上传成功!");
		obj.put("url", res.getUrl());
		
		return obj;
	}

	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		return "{\"msg\":\"ok\"}";
	}
}
