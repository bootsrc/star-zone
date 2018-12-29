package org.flylib.mall.common.shardtable.entity;

import org.flylib.mall.common.shardtable.hash.ConsistentHash;

import java.io.Serializable;
import java.util.Date;
import java.util.TreeMap;

public class Shard implements Serializable {
    private static final long serialVersionUID = 96490624614045810L;
    private String name;
    private Integer count;
    private TreeMap<Long, String> map;
    private Date createTime;
    private Date updateTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public TreeMap<Long, String> getMap() {
        return map;
    }

    public void setMap(TreeMap<Long, String> map) {
        this.map = map;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Shard{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", map=" + map +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public static class Builder {
        private static final String DEFAULT_NAME = "";
        private static final int DEFAULT_COUNT = 4;

        private String name;
        private int count;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder count(int count){
            this.count = count;
            return this;
        }

        public Shard build(){
            Shard shard = new Shard();
            shard.setName(name);
            shard.setCount(count);
            return newShard();
        }

        private Shard newShard() {
            ConsistentHash hashObj = new ConsistentHash(name, count);
            hashObj.buildHashRing();
            TreeMap<Long, String> map = hashObj.getMap();

            Date date = new Date();
            Shard shard = new Shard();
            shard.setName(name);
            shard.setCount(count);
            shard.setCreateTime(date);
            shard.setUpdateTime(date);
            shard.setMap(map);
            return shard;
        }
    }
}
