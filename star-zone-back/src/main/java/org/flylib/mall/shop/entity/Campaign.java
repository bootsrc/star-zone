package org.flylib.mall.shop.entity;

import java.io.Serializable;

public class Campaign implements Serializable {
    private static final long serialVersionUID = -4046949183638958201L;
    private String text;
    private int readCount;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }
}
