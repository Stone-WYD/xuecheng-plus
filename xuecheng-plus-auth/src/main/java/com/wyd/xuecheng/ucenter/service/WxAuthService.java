package com.wyd.xuecheng.ucenter.service;

import com.wyd.xuecheng.ucenter.model.po.XcUser;

/**
 * @author xh
 * @date 2024-10-29
 * @Description:
 */
public interface WxAuthService {

    XcUser wxAuth(String code);

}
