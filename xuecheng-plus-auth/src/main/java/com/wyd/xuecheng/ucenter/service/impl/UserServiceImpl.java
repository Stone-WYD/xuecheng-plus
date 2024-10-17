package com.wyd.xuecheng.ucenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wyd.xuecheng.ucenter.mapper.XcUserMapper;
import com.wyd.xuecheng.ucenter.model.dto.AuthParamsDto;
import com.wyd.xuecheng.ucenter.model.dto.XcUserExt;
import com.wyd.xuecheng.ucenter.model.po.XcUser;
import com.wyd.xuecheng.ucenter.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: xuecheng-plus
 * @author: Stone
 * @create: 2024-10-16 10:17
 */
@Service
@Slf4j
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private XcUserMapper xcUserMapper;

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * @description 根据账号查询用户信息
     * @param s  账号
     * @return org.springframework.security.core.userdetails.UserDetails
     * @author Mr.M
     * @date 2022/9/28 18:30
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        AuthParamsDto authParams;
        try {
            authParams = JSON.parseObject(s, AuthParamsDto.class);
        } catch (Exception e) {
            log.error("认证请求不符合项目要求：{}", e.getMessage());
            throw new RuntimeException("认证请求数据格式不对！");
        }
        // 开始认证
        AuthService authService = applicationContext.getBean(authParams.getAuthType() + "_authservice", AuthService.class);
        XcUserExt xcUserExt = authService.execute(authParams);
        return getUserPrincipal(xcUserExt);
        /* 为了统一认证，要把 Spring Security 原本的密码校验功能去除掉，然后走上面的新的校验逻辑
        // 用户姓名
        String username = authParams.getUsername();
        XcUser user = xcUserMapper.selectOne(new LambdaQueryWrapper<XcUser>().eq(XcUser::getUsername, username));
        if(user==null){
            //返回空表示用户不存在
            return null;
        }
        //取出数据库存储的正确密码
        String password  =user.getPassword();
        //用户权限,如果不加报Cannot pass a null GrantedAuthority collection
        // 正常用户权限也应该存放在数据库中，这里暂时不讨论
        String[] authorities= {"p1"};

        // 安全考虑不放入密码
        user.setPassword(null);
        String userStr = JSON.toJSONString(user);
        //创建UserDetails对象,权限信息待实现授权功能时再向UserDetail中加入
        UserDetails userDetails = User.withUsername(userStr).password(password).authorities(authorities).build();

        return userDetails;*/
    }

    private UserDetails getUserPrincipal(XcUserExt user) {
        //用户权限,如果不加报Cannot pass a null GrantedAuthority collection
        String[] authorities = {"p1"};
        String password = user.getPassword();
        //为了安全在令牌中不放密码
        user.setPassword(null);
        //将user对象转json
        String userString = JSON.toJSONString(user);
        //创建UserDetails对象
        UserDetails userDetails = User.withUsername(userString).password(password ).authorities(authorities).build();
        return userDetails;
    }

}