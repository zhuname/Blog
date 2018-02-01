package com.cz.mts.system.web;

import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.aliyun.PrintScreen4DJNativeSwingUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Michael on 2018/1/29.
 */
@Controller
@RequestMapping("/system/createScreen")
public class CreateScreen extends BaseController {

    @RequestMapping("/create/json")
    public @ResponseBody
    ReturnDatas create(HttpServletRequest request) throws Exception{
        ReturnDatas returnDatas = new ReturnDatas(ReturnDatas.SUCCESS) ;
        boolean result =  PrintScreen4DJNativeSwingUtils.printUrlScreen2jpg("d:/hello-world.jpg", "http://localhost:8080/mts/appWeb/index/index.jsp", 0, 0);

        return returnDatas ;
    }
}
