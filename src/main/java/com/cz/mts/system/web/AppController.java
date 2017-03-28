package com.cz.mts.system.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cz.mts.frame.annotation.SecurityApi;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.entity.AppUser;

@Controller
@RequestMapping(value="/app/appuser")
public class AppController extends AppUserController {

	@SecurityApi
	@Override
	public ReturnDatas listjson(HttpServletRequest request, Model model,
			AppUser appUser) throws Exception {
		// TODO Auto-generated method stub
		return super.listjson(request, model, appUser);
	}

	@SecurityApi
	@Override
	public ReturnDatas lookjson(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return super.lookjson(model, request, response);
	}

	
	
	
}
