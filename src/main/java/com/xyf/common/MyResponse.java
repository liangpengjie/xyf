package com.xyf.common;

/**
 * @author 木木
 * @date 2018/8/25 18:17
 * @description
 */
public class MyResponse {

    private String message ="操作成功";
    private int code = 1;
    private Object date;

    public MyResponse(Object date) {
        this.date = date;
    }

    public MyResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public MyResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }
}
