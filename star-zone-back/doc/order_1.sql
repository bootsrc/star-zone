/*
Navicat MySQL Data Transfer

Source Server         : localhost-mall-5.7.21
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : mall

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-04-22 18:37:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_1
-- ----------------------------
DROP TABLE IF EXISTS `order_1`;
CREATE TABLE `order_1` (
  `id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(3) NULL DEFAULT NULL COMMENT '更新时间',
  `consignee` varchar(50) DEFAULT NULL COMMENT '收货人',
  `consignee_moblie` varchar(50) DEFAULT NULL COMMENT '收货人电话',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `district` varchar(50) DEFAULT NULL COMMENT '区县',
  `express_id` varchar(255) DEFAULT NULL,
  `deliver_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '发货时间',
  `confirm_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '确认时间',
  `delivery_way` int(11) DEFAULT NULL COMMENT '配送方式  0 快递  1自提',
  `cart_item_list` varchar(3000) DEFAULT NULL COMMENT '购物车里所有的商品信息(json string)',
  `discount_list` varchar(3000) DEFAULT NULL COMMENT '使用的所有优惠券(json string)',
  `amount` bigint(12) DEFAULT NULL COMMENT '商品总价格(不含运费)',
  `postage` bigint(12) DEFAULT NULL COMMENT '邮费',
  `should_pay` bigint(12) DEFAULT NULL COMMENT 'shouldPay = 商品总价格 + postage - discountList总金额'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
