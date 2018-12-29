package org.flylib.mall.shop.vo.mobile;

import java.io.Serializable;
import java.util.List;

public class MomentParam implements Serializable {
    private static final long serialVersionUID = 3437784549256436639L;
    private List<String> img;
    private String content;

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
