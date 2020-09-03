package com.gzmpc.support.sso.core.dto;

import java.util.List;

/**
 * ResponseBean

 */
public class ResponseResult<T> {

    public ResponseResult(Integer code, String msg, T data, boolean status) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.status = status;
    }
    public ResponseResult() {
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * HTTP状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private T data;


    /**
     * status true
     */
    private  boolean status;


    public void setResponsePage(ResponseResult result, List<T> list, Integer current, Integer pageSize){
        int length = list.size();
        current = length/pageSize - 1 >= current ? current : length/pageSize - 1;
        if(current < 1 || pageSize < 1){
            current = 1;
            pageSize = 10;
        }
        int startIndex = Math.max((current - 1) * pageSize, 0);
        int endIndex = Math.min(current * pageSize, length);
        Pager pager = new Pager();
        ResponsePage responsePage = new ResponsePage();
        pager.setCurrent(current);
        pager.setPageSize(pageSize);
        pager.setTotal(list.size());
        responsePage.setPager(pager);
        responsePage.setData(list.subList(startIndex, endIndex));
        result.setData(responsePage);
    }




}
