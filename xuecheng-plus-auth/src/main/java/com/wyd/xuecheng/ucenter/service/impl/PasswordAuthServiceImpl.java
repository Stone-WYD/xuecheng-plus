package com.wyd.xuecheng.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wyd.xuecheng.base.utils.StringUtil;
import com.wyd.xuecheng.ucenter.feignclient.CheckCodeServiceClient;
import com.wyd.xuecheng.ucenter.mapper.XcUserMapper;
import com.wyd.xuecheng.ucenter.model.dto.AuthParamsDto;
import com.wyd.xuecheng.ucenter.model.dto.XcUserExt;
import com.wyd.xuecheng.ucenter.model.po.XcUser;
import com.wyd.xuecheng.ucenter.service.AuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author xh
 * @date 2024-10-17
 * @Description:
 */
@Service("password_authservice")
public class PasswordAuthServiceImpl implements AuthService {

    @Autowired
    XcUserMapper xcUserMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CheckCodeServiceClient checkCodeServiceClient;

    @Override
    public XcUserExt execute(AuthParamsDto authParamsDto) {

        // 验证码校验
        String checkcodekey = authParamsDto.getCheckcodekey();
        String checkcode = authParamsDto.getCheckcode();
        if(StringUtil.isBlank(checkcodekey) || StringUtil.isBlank(checkcode)){
            throw new RuntimeException("验证码为空");
        }
        Boolean verify = checkCodeServiceClient.verify(checkcodekey, checkcode);
        if (!verify) {
            throw new RuntimeException("验证码有误！");
        }
        // 账号
        String username = authParamsDto.getUsername();
        XcUser xcUser = xcUserMapper.selectOne(new LambdaQueryWrapper<XcUser>().eq(XcUser::getUsername, username));
        if (xcUser == null) {
            throw new RuntimeException("账号不存在！");
        }
        XcUserExt xcUserExt = new XcUserExt();
        BeanUtils.copyProperties(xcUser, xcUserExt);
        // 校验密码
        String passwordDb = xcUserExt.getPassword();
        String passwordForm = authParamsDto.getPassword();
        if (!passwordEncoder.matches(passwordDb, passwordForm)) {
            throw new RuntimeException("账号或密码错误");
        }
        return xcUserExt;
    }
}
