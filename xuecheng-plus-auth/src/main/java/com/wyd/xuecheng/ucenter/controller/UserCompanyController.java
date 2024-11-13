package com.wyd.xuecheng.ucenter.controller;

import com.wyd.xuecheng.base.utils.StringUtil;
import com.wyd.xuecheng.ucenter.model.po.XcCompany;
import com.wyd.xuecheng.ucenter.service.CompanyService;
import com.wyd.xuecheng.ucenter.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: xuecheng-plus
 * @author: Stone
 * @create: 2024-11-08 14:17
 */
@Slf4j
@RestController
@RequestMapping("/teaching")
public class UserCompanyController {

    @Resource
    private CompanyService companyService;

    @GetMapping("/my-company")
    public XcCompany myCompany() {
        //取出用户身份
        SecurityUtil.XcUser user = SecurityUtil.getUser();
        if (user != null && StringUtil.isNotEmpty(user.getCompanyId())) {
            String companyId = user.getCompanyId();
            return companyService.getById(companyId);
        } else return null;
    }


}