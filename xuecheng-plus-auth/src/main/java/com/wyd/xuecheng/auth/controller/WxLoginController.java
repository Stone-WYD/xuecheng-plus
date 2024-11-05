package com.wyd.xuecheng.auth.controller;

import com.wyd.xuecheng.ucenter.model.po.XcUser;
import com.wyd.xuecheng.ucenter.service.WxAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * @author xh
 * @date 2024-10-29
 * @Description:
 */
@Slf4j
@Controller
public class WxLoginController {

    @Autowired
    private WxAuthService wxAuthService;

    /**
     * 前端页面嵌入微信二维码，用户扫码授权后将携带微信返回参数调用该接口
     * */
    @RequestMapping("/wxLogin")
    public String wxLogin(String code, String state) throws IOException {
        log.debug("微信扫码回调,code:{},state:{}",code,state);
        //请求微信申请令牌，拿到令牌查询用户信息，将用户信息写入本项目数据库
        XcUser xcUser = wxAuthService.wxAuth(code);
        if(xcUser==null){
            return "redirect:http://www.51xuecheng.cn/error.html";
        }
        String username = xcUser.getUsername();
        return "redirect:http://www.51xuecheng.cn/sign.html?username="+username+"&authType=wx";
    }

}
