package com.wyd.xuecheng.ucenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wyd.xuecheng.ucenter.mapper.XcUserMapper;
import com.wyd.xuecheng.ucenter.mapper.XcUserRoleMapper;
import com.wyd.xuecheng.ucenter.model.dto.AuthParamsDto;
import com.wyd.xuecheng.ucenter.model.dto.XcUserExt;
import com.wyd.xuecheng.ucenter.model.po.XcUser;
import com.wyd.xuecheng.ucenter.model.po.XcUserRole;
import com.wyd.xuecheng.ucenter.service.AuthService;
import com.wyd.xuecheng.ucenter.service.WxAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * @author xh
 * @date 2024-10-29
 * @Description:
 */
@Slf4j
@Service("wx_authservice")
public class WxAuthServiceImpl implements AuthService, WxAuthService{

    @Autowired
    private XcUserMapper xcUserMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${weixin.appid:111}")
    private String appid;

    @Value("${weixin.secret:111}")
    private String secret;

    @Autowired
    private XcUserRoleMapper xcUserRoleMapper;

    @Autowired
    private WxAuthServiceImpl currentProxy;

    /**
     * 前端嵌入微信二维码页面被扫码后携带参数跳转到这里，具体逻辑是使用 code 去微信服务获取用户信息并保存到数据库
     * */
    @Override
    public XcUser wxAuth(String code) {
        // 扫描二维码后收到微信返回的code，通过这个code调用微信接口获取用户信息
        Map<String, String> access_token_map = getAccess_token(code);
        if (access_token_map == null) {
            return null;
        }
        // 从返回结果中获取用户信息
        Map<String, String> userInfo =
                getUserInfo(access_token_map.get("access_token"), access_token_map.get("openid"));
        if (userInfo == null) {
            return null;
        }
        // 添加用户信息到数据库
        XcUser xcUser = currentProxy.addWxUser(userInfo);
        return xcUser;
    }

    /**
     * 微信返回示例：
     * {
     *  "access_token":"ACCESS_TOKEN",
     *  "expires_in":7200,
     *  "refresh_token":"REFRESH_TOKEN",
     *  "openid":"OPENID",
     *  "scope":"SCOPE",
     *  "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
     *  }
     * */
    private Map<String, String> getAccess_token(String code) {
        String wxUrlTemplate = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
        // 请求微信地址
        String wxUrl = String.format(wxUrlTemplate, appid, secret, code);
        log.info("调用微信接口申请access_token,url:{}", wxUrl);

        ResponseEntity<String> exchange = restTemplate.exchange(wxUrl, HttpMethod.POST, null, String.class);
        String result = exchange.getBody();
        log.info("调用微信接口申请access_token返回值为:{}", result);
        // 将结果转换为 Map
        Map<String, String> resultMap = JSON.parseObject(result, Map.class);
        return resultMap;
    }

    /**获取用户信息，示例如下：
     {
     "openid":"OPENID",
     "nickname":"NICKNAME",
     "sex":1,
     "province":"PROVINCE",
     "city":"CITY",
     "country":"COUNTRY",
     "headimgurl": "https://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
     "privilege":[
     "PRIVILEGE1",
     "PRIVILEGE2"
     ],
     "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
     }
     */
    private Map<String, String> getUserInfo(String access_token, String openid) {
        String wxUrl_template = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
        // 请求微信地址
        String wxUrl = String.format(wxUrl_template, access_token, openid);
        log.info("调用微信接口申请用户信息，url:{}", wxUrl);

        ResponseEntity<String> exchange = restTemplate.exchange(wxUrl, HttpMethod.POST, null, String.class);

        // 防止乱码进行转码
        String result = new String(exchange.getBody()
                .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        Map<String, String> resultMap = JSON.parseObject(result, Map.class);
        return resultMap;
    }

    @Transactional
    public XcUser addWxUser(Map<String, String> userInfo_map) {
        String unionid = userInfo_map.get("unionid");
        // 根据 unionid 去数据库获取用户信息
        XcUser xcUser = xcUserMapper.selectOne(new LambdaQueryWrapper<XcUser>()
                .eq(XcUser::getWxUnionid, unionid));
        if (xcUser != null) {
            // 曾经登录过，直接返回数据库用户信息
            return xcUser;
        }
        // 将用户信息保存到数据库
        String userId = UUID.randomUUID().toString();
        xcUser = new XcUser();
        xcUser.setId(userId);
        xcUser.setWxUnionid(unionid);
        //记录从微信得到的昵称
        xcUser.setNickname(userInfo_map.get("nickname"));
        xcUser.setUserpic(userInfo_map.get("headimgurl"));
        xcUser.setName(userInfo_map.get("nickname"));
        xcUser.setUsername(unionid);
        xcUser.setPassword(unionid);
        xcUser.setUtype("101001");//学生类型
        xcUser.setStatus("1");//用户状态
        xcUser.setCreateTime(LocalDateTime.now());
        xcUserMapper.insert(xcUser);
        XcUserRole xcUserRole = new XcUserRole();
        xcUserRole.setId(UUID.randomUUID().toString());
        xcUserRole.setUserId(userId);
        xcUserRole.setRoleId("17");//学生角色
        xcUserRoleMapper.insert(xcUserRole);
        return xcUser;
    }

    /**
     * 执行到这一步，就从微信获取到用户信息了，这里需要读取用户信息，不存在用户信息则新增用户信息。
     * */
    @Override
    public XcUserExt execute(AuthParamsDto authParamsDto) {
        //账号
        String username = authParamsDto.getUsername();
        XcUser user = xcUserMapper.selectOne(new LambdaQueryWrapper<XcUser>().eq(XcUser::getUsername, username));
        if(user==null){
            //返回空表示用户不存在
            throw new RuntimeException("账号不存在");
        }
        XcUserExt xcUserExt = new XcUserExt();
        BeanUtils.copyProperties(user,xcUserExt);
        return xcUserExt;
    }
}
