package org.flylib.mall.shop.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomConfig {
    @Value("${custom.dbproxy.tableNameByKeyUrl}")
    private String tableNameByKeyUrl;
    @Value("${custom.workerId}")
    private long workerId;
    @Value("${custom.datacenterId}")
    private long datacenterId;

    public String getTableNameByKeyUrl() {
        return tableNameByKeyUrl;
    }

    public long getWorkerId() {
        return workerId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }
}
