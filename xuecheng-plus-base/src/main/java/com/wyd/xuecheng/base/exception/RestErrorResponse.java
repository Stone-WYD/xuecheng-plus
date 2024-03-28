package com.wyd.xuecheng.base.exception;

import java.io.Serializable;

/**
 * @program: xuecheng-plus
 * @author: Stone
 * @create: 2024-03-28 20:58
 * 错误响应参数包装
 */
public class RestErrorResponse  implements Serializable {

    private String errMessage;

    public RestErrorResponse(String errMessage){
        this.errMessage= errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}