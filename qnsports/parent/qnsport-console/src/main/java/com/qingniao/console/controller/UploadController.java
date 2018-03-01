package com.qingniao.console.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.qingniao.core.utils.FastDFSClient;
import com.qingniao.core.utils.IDUtils;
import com.qingniao.core.utils.SERVER_URL;

import net.fckeditor.response.UploadResponse;

@Controller
public class UploadController {
	//普通图片上传
	@ResponseBody
	@RequestMapping("/upload/uploadImg.do")
	public String imgUpload(@RequestParam MultipartFile picfile, HttpServletRequest request) throws IOException {
		//获取图片名称
		String originalFilename = picfile.getOriginalFilename();
		//获取图片扩展名
		String extraName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
		//生成图片的名字
		String newImgName = IDUtils.genImageName()+extraName;
		//生成图片路径
		String path = "/imgs/"+newImgName;
		//获取服务器的绝对路径
		String url = request.getSession().getServletContext().getRealPath("")+path;
		InputStream is = null;
		OutputStream out = null;
		try {
			is = picfile.getInputStream();
			out = new FileOutputStream(new File(url));
			byte[] b = new byte[1024];
			int count = 0;
			while ((count = is.read(b)) != -1) {
				out.write(b, 0, count);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
			is.close();
		}
		JSONObject json = new JSONObject();
		json.put("path", path);
		return json.toString();
	}
	//图片上传到fastDFS
	@ResponseBody
	@RequestMapping("/upload/imgUploadToFastDFS.do")
	public String imgUploadToFastDFS(@RequestParam MultipartFile picfile) throws Exception {
		FastDFSClient fastDFSClient = new FastDFSClient("classpath:properties/fastDFS.properties");
		String fileName = picfile.getOriginalFilename();
		String extraName = fileName.substring(fileName.lastIndexOf(".")+1);
		String path = fastDFSClient.uploadFile(picfile.getBytes(), extraName);//保存到数据库
		String url = SERVER_URL.IMG_URL+path;//提供显示
		JSONObject json = new JSONObject();
		json.put("path", path);
		json.put("url", url);
		return json.toString();
	}
	//fck图片上传
	@RequestMapping("/upload/productDesc.do")
	public void fckImgUplod(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//创建FastDFSClient
		FastDFSClient fastDFSClient = new FastDFSClient("classpath:properties/fastDFS.properties");
		//包装request成为MultipartRequest
		MultipartRequest multipartRequest = (MultipartRequest)request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		Set<Entry<String, MultipartFile>> fileEntrySet = fileMap.entrySet();
		//遍历集合上传图片并返回给浏览器
		for (Entry<String, MultipartFile> entry : fileEntrySet) {
			MultipartFile picFile = entry.getValue();
			String path = fastDFSClient.uploadFile(picFile.getBytes(), FilenameUtils.getExtension(picFile.getName()));
			String url = SERVER_URL.IMG_URL+path;
			UploadResponse ok = UploadResponse.getOK(url);
			response.getWriter().print(ok);
		}
	}
}
