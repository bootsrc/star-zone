package org.flylib.mall.shop.service;

import org.flylib.mall.shop.constant.ConfigKey;
import org.flylib.mall.shop.repository.ConfigEntryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EcofarmService {
    @Resource
    private ConfigEntryRepository configEntryRepository;

    public String getWebViewUrl() {
        return configEntryRepository.get(ConfigKey.WEBVIEW_URL);
    }
}
