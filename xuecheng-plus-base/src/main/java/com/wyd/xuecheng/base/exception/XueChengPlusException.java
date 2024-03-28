package com.wyd.xuecheng.base.exception;

/**
 * @program: xuecheng-plus
 * @author: Stone
 * @create: 2024-03-28 20:55
 */
public class XueChengPlusException extends RuntimeException{


    private String errMessage;

    public XueChengPlusException() {
        super();
    }

    public XueChengPlusException(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public static void cast(CommonError commonError){
        throw new XueChengPlusException(commonError.getErrMessage());
    }
    public static void cast(String errMessage){
        throw new XueChengPlusException(errMessage);
    }
}