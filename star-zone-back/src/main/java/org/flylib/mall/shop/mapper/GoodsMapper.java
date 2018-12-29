package org.flylib.mall.shop.mapper;

import org.flylib.mall.shop.entity.Goods;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GoodsMapper implements RowMapper<Goods> {
    @Override
    public Goods mapRow(ResultSet rs, int rowNum) throws SQLException {
        Goods goods = new Goods();
        // 设置BaseGoods字段
        goods.setId(rs.getLong("id"));
        goods.setCatalogId(rs.getInt("catalog_id"));
        goods.setName(rs.getString("name"));
        goods.setImg(rs.getString("img"));
        goods.setLabel(rs.getString("label"));
        goods.setPrice(rs.getInt("price"));
        goods.setOriginPrice(rs.getInt("origin_price"));
        goods.setHot(rs.getBoolean("hot"));
        goods.setStatus(rs.getInt("status"));
        goods.setSortValue(rs.getInt("sort_value"));
        goods.setStock(rs.getInt("stock"));
        // 设置Goods剩余字段
        goods.setVersion(rs.getInt("version"));
        goods.setIntroduce(rs.getString("introduce"));
        goods.setProductHtml(rs.getString("product_html"));
        goods.setBigImg(rs.getString("big_img"));
        goods.setImgList(rs.getString("img_list"));
        goods.setCreateTime(rs.getTimestamp("create_time"));
        goods.setUpdateTime(rs.getTimestamp("update_time"));
        return goods;
    }
}
