package com.wyd.xuecheng.ucenter.service;

import com.wyd.xuecheng.ucenter.model.dto.AuthParamsDto;
import com.wyd.xuecheng.ucenter.model.dto.XcUserExt;

/**
 * @author xh
 * @date 2024-10-17
 * @Description:
 */

public interface AuthService {

    /**
    * 认证方法
    * */
    XcUserExt execute(AuthParamsDto authParamsDto);
}
