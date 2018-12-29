package org.flylib.mall.common.dto;

import java.io.Serializable;
import java.util.List;

public class TableData<T> implements Serializable {
    private static final long serialVersionUID = 6844750051465188580L;

    private int code;
    private int count;
    private List<T> data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
