package com.qingniao.fastDFS.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import com.qingniao.core.utils.FastDFSClient;

//测试图片上传
public class FastDFSTest {
	@Test
	public void demo1() throws FileNotFoundException, IOException, MyException {
		//读取配置文件
		ClientGlobal.init("D:/myproject/qnsports/parent/qnsport-console/src/main/resources/properties/fastDFS.properties");
		//编码创建1.TrackerClient 2.通过TrackerClient获取TrackerServer 3.StorageServer 4.创建StorageClient 5.上传图片
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		StorageServer storageServer = null;
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		//上传图片
		String[] upload_file = storageClient.upload_file("C:/Users/Administrator/Desktop/img/3.jpg", "jpg", null);
		for (String string : upload_file) {
			System.out.println(string);
		}
	}
	@Test
	public void demo2() throws Exception {
		FastDFSClient fastDFSClient = new FastDFSClient("D:/myproject/qnsports/parent/qnsport-console/src/main/resources/properties/fastDFS.properties");
		String uploadFile = fastDFSClient.uploadFile("C:/Users/Administrator/Desktop/img/3.jpg");
		System.out.println(uploadFile);
	}
}
