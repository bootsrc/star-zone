package org.flylib.mall.common.dto;

import org.flylib.mall.common.constant.ResponseCode;
import org.flylib.mall.common.constant.ResponseMsg;

import java.io.Serializable;

public class ResponseData implements Serializable {
    private static final long serialVersionUID = 1447339403407144621L;

    private int code;
    private String msg;
    private Object data;

    public static ResponseData newOK() {
        ResponseData responseData=new ResponseData();
        responseData.setCode(ResponseCode.OK);
        responseData.setMsg(ResponseMsg.OK);
        return responseData;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
}
