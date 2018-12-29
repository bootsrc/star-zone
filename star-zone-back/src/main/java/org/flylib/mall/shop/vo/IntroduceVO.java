package org.flylib.mall.shop.vo;

import java.io.Serializable;

public class IntroduceVO implements Serializable {
    private static final long serialVersionUID = 2872747216768226L;
    private String textContent;
    private String appDownloadWebsite;

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getAppDownloadWebsite() {
        return appDownloadWebsite;
    }

    public void setAppDownloadWebsite(String appDownloadWebsite) {
        this.appDownloadWebsite = appDownloadWebsite;
    }
}
