/**
 * Copyright (C), 2018-2019, 兔讯科技有限公司
 * FileName: ResponseModel
 * Author: 彭陈
 * Date: 2019/1/16 15:53
 */


package vip.proyi.config;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**

 * 〈通用返回类型〉
 *
 * @author 彭陈
 * @create 2019/1/16
 * @since 1.0.0
 */

public class ResponseModel<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 每一次接口返回类型的json格式统一为
     * staus:XXX
     * message:XXX
     * data:XXX
     */
    private int status;
    private String message;
    private T data;
    private String code;

    /**
     * 将构造器私有，即外部不能new出该对象
     */
    private ResponseModel(int status) {
        this.status = status;
    }

    private ResponseModel(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public ResponseModel(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private ResponseModel(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * status是0 返回true
     * status不是0 返回false
     *
     * JsonIgnore不让其值显示在返回的json数据中
     */
    @JsonIgnore
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static <T> ResponseModel<T> createBySuccess() {
        return new ResponseModel<T>(ResponseCode.SUCCESS.getCode());
    }

    /**
     * 因为String也是 T类型的一种数据类型，如果data的数据也是String类型，岂不是和ResponseModel(int status, T data)构造函数冲突
     *  createBySuccessMessage和createBySuccess(T data)即完成这两种情况的区分
     */
    public static <T> ResponseModel<T> createBySuccessMessage(String message) {
        return new ResponseModel<T>(ResponseCode.SUCCESS.getCode(), message);
    }
    public static <T> ResponseModel<T> createBySuccess(T data) {
        return new ResponseModel<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> ResponseModel<T> createBySuccess(String message, T data) {
        return new ResponseModel<T>(ResponseCode.SUCCESS.getCode(), message, data);
    }

    public static <T> ResponseModel<T> createByError() {
        return new ResponseModel<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }
    public static <T> ResponseModel<T> createByErrorMessage(String errorMessage) {
        return new ResponseModel<T>(ResponseCode.ERROR.getCode(), errorMessage);
    }
    public static <T> ResponseModel<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
        return new ResponseModel<T>(errorCode, errorMessage);
    }
}