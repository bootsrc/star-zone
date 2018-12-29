package org.flylib.mall.shop.mapper;

import org.flylib.mall.shop.entity.BaseGoods;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseGoodsMapper implements RowMapper<BaseGoods> {
    @Override
    public BaseGoods mapRow(ResultSet rs, int rowNum) throws SQLException {
        BaseGoods baseGoods = new BaseGoods();
        baseGoods.setId(rs.getLong("id"));
        baseGoods.setCatalogId(rs.getInt("catalog_id"));
        baseGoods.setName(rs.getString("name"));
        baseGoods.setImg(rs.getString("img"));
        baseGoods.setLabel(rs.getString("label"));
        baseGoods.setPrice(rs.getInt("price"));
        baseGoods.setOriginPrice(rs.getInt("origin_price"));
        baseGoods.setHot(rs.getBoolean("hot"));
        baseGoods.setStatus(rs.getInt("status"));
        baseGoods.setSortValue(rs.getInt("sort_value"));
        baseGoods.setStock(rs.getInt("stock"));
        return baseGoods;
    }
}
