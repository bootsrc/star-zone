package org.flylib.mall.shop.repository;

import org.flylib.mall.shop.model.ConfigEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ConfigEntryRepository {
    private static final Logger log = LoggerFactory.getLogger(ConfigEntryRepository.class);

    private static final String COLUMNS = " config_key, config_value ";
    private static final String TABLE_NAME = "config_entry";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String get(String key) {
        String sql = "SELECT " + COLUMNS + " FROM " + TABLE_NAME
                + " WHERE config_key=:configKey LIMIT 1 ";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("configKey", key);
        List<ConfigEntry> configEntryList = namedParameterJdbcTemplate.query(sql, param,
                new BeanPropertyRowMapper<>(ConfigEntry.class));
        if (configEntryList == null || configEntryList.size() == 0) {
            return null;
        } else {
            return configEntryList.get(0).getConfigValue();
        }
    }
}
