package org.flylib.mall.shop.repository;

import org.flylib.mall.common.constant.ResponseCode;
import org.flylib.mall.common.constant.ResponseMsg;
import org.flylib.mall.common.dto.TableData;
import org.flylib.mall.shop.entity.Goods;
import org.flylib.mall.shop.mapper.BaseGoodsMapper;
import org.flylib.mall.shop.mapper.GoodsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class GoodsRepository {
    private static final Logger log = LoggerFactory.getLogger(GoodsRepository.class);
    private static final String TABLE_NAME = "goods";
    private static final String COLUMNS = "id, catalog_id, name, img, label," +
            " price, origin_price, hot, status, sort_value, stock, version, introduce," +
            " product_html, big_img, img_list, create_time, update_time ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * @param page
     * @param size
     * @param type type=0:返回<code>List<BaseGoods></code>; type=1 返回<code>List<Goods></code>
     * @return
     */
    public TableData byPage(int page, int size, int type) {
        final String sql = "SELECT * FROM " + TABLE_NAME + " "
                + " ORDER BY sort_value LIMIT ?, ?";
        log.info("---sqlStr:{}", sql);
        int start = page * size;
        PreparedStatementCreator creator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql
                        , Statement.NO_GENERATED_KEYS);
                ps.setInt(1, start);
                ps.setInt(2, size);
                return ps;
            }
        };
        List data = null;
        switch (type) {
            case 0:
                data = jdbcTemplate.query(creator, new BaseGoodsMapper());
                break;
            case 1:
                data = jdbcTemplate.query(creator, new GoodsMapper());
        }
        String countSql = "SELECT COUNT(*) FROM goods";
        int count = jdbcTemplate.queryForObject(countSql, Integer.class);

        TableData tableData = new TableData();
        tableData.setCode(ResponseCode.OK);
        tableData.setMsg(ResponseMsg.OK);
        tableData.setCount(count);
        tableData.setData(data);
        return tableData;
    }

    /**
     * 没有使用乐观锁，直接减库存的操作，很容易出错，导致库存量为负数
     *
     * @param goodsId
     * @param delta   减去多少个库存量
     * @return
     */
    @Deprecated
    public int minusNotSafe(long goodsId, int delta) {
        if (goodsId < 1) {
            log.error("goodsId<1");
            return 0;
        }
        final String queryVersionSql = "SELECT stock, version from " + TABLE_NAME + " WHERE id=?";
        Map<String, Object> map = jdbcTemplate.queryForMap(queryVersionSql, new Object[]{goodsId}
                , new int[]{Types.BIGINT});
        if (map != null && map.size() > 0) {
            int stock = (int) map.get("stock");
            int version = (int) map.get("version");
            final String minusSql = "update " + TABLE_NAME
                    + " set stock=?, version=? WHERE id=?";
            if (stock < delta) {
                log.info("---StockIsZero,goodsId={}---", goodsId);
                return 0;
            }
            int updatedCount = jdbcTemplate.update(minusSql
                    , new Object[]{stock - delta, version + 1, goodsId}
                    , new int[]{Types.INTEGER, Types.INTEGER, Types.BIGINT});
            if (updatedCount == 0) {
                log.info("---MinusOperationIsRejected,goodsId={}", goodsId);
            }
            return updatedCount;
        }
        return 0;
    }

    /**
     * 库存减一
     * 使用了乐观锁去减库存
     *
     * @param goodsId
     * @param delta   减去多少个库存量
     * @return
     */
    public int minus(long goodsId, int delta) {
        if (goodsId < 1) {
            log.error("goodsId<1");
            return 0;
        }
        final String queryVersionSql = "SELECT stock, version from " + TABLE_NAME + " WHERE id=?";
        Map<String, Object> map = jdbcTemplate.queryForMap(queryVersionSql, new Object[]{goodsId}
                , new int[]{Types.BIGINT});
        if (map != null && map.size() > 0) {
            int stock = (int) map.get("stock");
            int versionValue = (int) map.get("version");
            int newVersion = versionValue + 1;
            final String minusSql = "update " + TABLE_NAME
                    + " set stock=?, version=? WHERE id=? AND version=?";
            if (stock < 1) {
                log.info("---StockIsZero,goodsId={},version={}---", goodsId, newVersion);
                return 0;
            }
            if (versionValue < delta) {
                log.error("---versionValue<{}", delta);
            }
            int updatedCount = jdbcTemplate.update(minusSql
                    , new Object[]{stock - delta, newVersion, goodsId, versionValue}
                    , new int[]{Types.INTEGER, Types.INTEGER, Types.BIGINT, Types.INTEGER});
            if (updatedCount == 0) {
                log.info("---MinusOperationIsRejected,goodsId={},newVersion={}", goodsId, newVersion);
            }

            // 测试事务是否OK
//            if (updatedCount > 0) {
//                log.error("---tx rollback---");
//                throw new RuntimeException("ManullyThrowRuntimeException------");
//            }
            return updatedCount;
        }
        return 0;
    }

    public Optional<Goods> findById(long goodsId) {
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        Goods goods = jdbcTemplate.queryForObject(sql, new Object[]{goodsId}, new int[]{Types.BIGINT}
                , new GoodsMapper());
        Optional<Goods> optionalGoods = Optional.ofNullable(goods);
        return optionalGoods;
    }

    public int add(Goods goods) {
        String sql = "INSERT INTO goods ( " + COLUMNS + " ) "
                + "VALUES ( "
                + " :id,:catalogId,:name,:img,:label,:price,:originPrice,:hot,:status,:sortValue,:stock,:version," +
                ":introduce,:productHtml,:bigImg,:imgList,:createTime,:updateTime"
                + " )";
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("id", goods.getId());
        parameter.put("catalogId", goods.getCatalogId());
        parameter.put("name", goods.getName());
        parameter.put("img", goods.getImg());
        parameter.put("label", goods.getLabel());
        parameter.put("price", goods.getPrice());
        parameter.put("originPrice", goods.getOriginPrice());
        parameter.put("hot", goods.isHot());
        parameter.put("status", goods.getStatus());
        parameter.put("sortValue", goods.getSortValue());
        parameter.put("stock", goods.getStock());
        parameter.put("version", goods.getVersion());
        parameter.put("introduce", goods.getIntroduce());
        parameter.put("productHtml", goods.getProductHtml());
        parameter.put("bigImg", goods.getBigImg());
        parameter.put("imgList", goods.getImgList());
        parameter.put("createTime", new Timestamp(goods.getCreateTime().getTime()));
        parameter.put("updateTime", new Timestamp(goods.getUpdateTime().getTime()));
        log.info("SQLparameter={}", parameter);
        int updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }

    public int update(Goods goods) {
        int updatedCount = 0;

        String sql = "UPDATE " + TABLE_NAME + " SET "
                + "catalog_id=:catalogId, name=:name, img=:img, label=:label,"
                + " price=:price, origin_price=:originPrice, hot=:hot, status=:status, sort_value=:sortValue," +
                " stock=:stock, version=:version, introduce=:introduce,"
                + " product_html=:productHtml, big_img=:bigImg, img_list=:imgList," +
                " create_time=:createTime, update_time=:updateTime "
                + " WHERE id=:id ";

        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("id", goods.getId());
        parameter.put("catalogId", goods.getCatalogId());
        parameter.put("name", goods.getName());
        parameter.put("img", goods.getImg());
        parameter.put("label", goods.getLabel());
        parameter.put("price", goods.getPrice());
        parameter.put("originPrice", goods.getOriginPrice());
        parameter.put("hot", goods.isHot());
        parameter.put("status", goods.getStatus());
        parameter.put("sortValue", goods.getSortValue());
        parameter.put("stock", goods.getStock());
        parameter.put("version", goods.getVersion());
        parameter.put("introduce", goods.getIntroduce());
        parameter.put("productHtml", goods.getProductHtml());
        parameter.put("bigImg", goods.getBigImg());
        parameter.put("imgList", goods.getImgList());
        parameter.put("createTime", new Timestamp(goods.getCreateTime().getTime()));
        parameter.put("updateTime", new Timestamp(goods.getUpdateTime().getTime()));

        updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }

    public int delete(long id) {
        int updatedCount = 0;
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE "
                + "id=:id";
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("id", id);
        updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }
}
