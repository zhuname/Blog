package com.cz.mts.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import com.cz.mts.frame.util.ReturnDatas;

/**
 * Servlet implementation class FileUpload
 */
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//前端自定义文件目录
	private static final String filepathdir = "filepathdir";
	
	//保存文件的文件夹名称
	private static final String uploadDirName="mts";
	private static final String realpath="/webdata/app/images/";
	private static final String realfilepath= realpath+uploadDirName;
	//http地址
//	private static final String httppath="http://106.15.60.65:22222/images/";
	private static final String httppath="http://114.55.4.234:33333/images/";
	private static final String httpfilepath=httppath+uploadDirName;
	//callback的url的key
	private static final String callbackurlName="callbackurl";
	

	// 1. 创建工厂类
	DiskFileItemFactory factory = new DiskFileItemFactory();
	// 2. 创建FileUpload对象
	ServletFileUpload upload = new ServletFileUpload(factory);

	public FileUpload() {
		super();
		
		 //设置上传文件的最大值
		//upload. setFileSizeMax(1024000);
		
		// 上传进度
		upload.setProgressListener(new ProgressListener() {
			long num = 0;
			public void update(long bytesRead, long contentLength, int items) {
				long progress = bytesRead * 100 / contentLength;
				if (progress == num)
					return;
				num = progress;
				System.out.println("上传进度:" + progress + "%");
				// request.getSession().setAttribute("progress", progress);
			}
		});

	}

	/**
	 * 上传文件必须为POST方法
	 */
	@SuppressWarnings({ "static-access", "deprecation" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Integer userId = 0 ;
		if(request.getParameter("userId")!=null){
			userId = Integer.parseInt(request.getParameter("userId").toString());
		}
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		List<String> urlList = new ArrayList<String>();
		// 3. 判断是否是上传表单
		boolean b = upload.isMultipartContent(request);
		if (!b) { // 不是文件上传
			return;
		}

		// 4. 解析request，获得FileItem项
		List<FileItem> fileitems = null;
		try {
			fileitems = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		if (fileitems == null) {
			return ;
		}
		// 创建文件
//		ServletContext context = getServletContext();
//		String dir = context.getRealPath("/")+"\\"+uploadDirName+"\\"+userId;
		String dir = realfilepath+"/"+userId;
		String dirpath = null;
	    String httppath=httpfilepath;
	    String callbackurl=null;

		// 5. 遍历集合,获取项目路径
		for (FileItem item : fileitems) {
			if(!item.isFormField()){
				continue;
			}
			if (callbackurlName.equals(item.getFieldName())) {//需要跳转的url
				callbackurl=item.getString();
			}else if (filepathdir.equals(item.getFieldName())) {
				if (item.getString().contains("\\.")) {// 包含路径.
					continue;
				}
				dirpath = item.getString();
			}
		}
		
//		if(callbackurl==null){
//			return;
//		}
		
		
		String allhttpfile="";

		if (dirpath != null) {
			dir = dir + "/" + dirpath;
			httppath=httppath+"/"+dirpath;
		}

		// 5. 遍历集合
		for (FileItem item : fileitems) {
			if (item.isFormField()) {// 普通字段
				continue;
			}
			// 获得文件名
			String filename = item.getName();
			String prefix=filename.substring(filename.lastIndexOf(".")+1);
			filename = UUID.randomUUID().toString()+"."+prefix;
			
			File f_dir=new File(dir);
			if(!f_dir.exists()){
				f_dir.mkdirs();
			}
			
			File file = new File(dir+"/"+ filename);
			if(!file.exists()){
				file.createNewFile();
			}
		
//			allhttpfile=allhttpfile+httppath+"/"+userId+"/"+filename+";";
			urlList.add(httppath+"/"+userId+"/"+filename);
			// 获得流，读取数据写入文件
			InputStream in = item.getInputStream();
			FileOutputStream fos = new FileOutputStream(file);

			int len;
			byte[] buffer = new byte[1024];
			while ((len = in.read(buffer)) > 0){
				fos.write(buffer, 0, len);
			}
			
			fos.close();
			in.close();
			item.delete(); // 删除临时文件
		}
		
//		if(allhttpfile.endsWith(";")){
//			allhttpfile=allhttpfile.substring(0, allhttpfile.length()-1);
//		}
//		  out.print(allhttpfile);
//		returnObject.setData(allhttpfile);
//		JSONObject json = JSONObject.fromObject(returnObject);//将java对象转换为json对象
//		String str = json.toString();//将json对象转换为字符串
//		out.print(str);
//		out.print("{" +allhttpfile+"}");
		JSONObject json = new JSONObject();
		json.put("url",urlList);
		out.print(json.toString());
	    System.out.println(json.toString());
	}
	
	

}
