package com.cz.mts.system.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.ContentCachingResponseWrapper;

public class Object2JsonStrFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//对返回数据进行处理
		ContentCachingResponseWrapper myResponse=new ContentCachingResponseWrapper((HttpServletResponse)response);
		chain.doFilter(request, myResponse);
		byte[] output = myResponse.getContentAsByteArray() ;
//		if(!output.contains("<html>") && !output.contains("<HTML>")){
//			output=encodeTime(output);
//		}
		PrintWriter out=response.getWriter();  
		out.write(output.toString());  
        out.close(); 
	}

}
