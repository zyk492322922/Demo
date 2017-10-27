package com.zyk.demo.common;

/**
 * 返回结果统一形式工具类
 */
public class ResponseBuilder {

    private String status ;     //  1: 成功 ; 0: 失败

    private String msg ;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private Object data;


    ResponseBuilder(String status,String msg,Object data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    ResponseBuilder(Object data){
        this.status = "1";
        this.msg = "";
        this.data = data;
    }

    ResponseBuilder(){
        this.status = "1";
        this.msg = "";
    }

    ResponseBuilder(String msg){
        this.status = "0";
        this.msg = msg;
    }

    /**
     * 返回没有参数的调用成功结果，一般用于新增， 更新等接口
     * @return
     */
    public static ResponseBuilder buildNormalResponse(){
        return new ResponseBuilder();
    }

    /**
     * 返回带有结果的调用成功结果, 一般用于查询
     * @param object
     * @return
     */
    public static ResponseBuilder buildNormalResponse(Object object){
        return new ResponseBuilder(object);
    }

    /**
     * 返回带有错误信息的调用失败结果
     * @param msg
     * @return
     */
    public static  ResponseBuilder buildErrorResponse(String msg){
        return new ResponseBuilder(msg);
    }




}
