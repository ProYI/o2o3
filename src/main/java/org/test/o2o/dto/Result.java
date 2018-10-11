/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Result
 * Author: Administrator
 * Date: 2018-09-16 12:26
 * Description: 封装json对象，所有返回结果都使用它
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dto;


/**

 * 〈功能简述〉<br>

 * 〈泛型类
 * 封装json对象，所有返回结果都使用它〉

 *

 * @author Administrator

 * @create 2018-09-16

 * @since 1.0.0

 */

public class Result<T> {
    private boolean success; //是否成功标志
    private T data; //成功时返回的数据
    private String errorMsg;
    private int errorCode;
    public Result() {}

    //成功时的构造器
    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    //错误时的构造器
    public Result(boolean success, int errorCode, String errorMsg) {
        this.success = success;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}