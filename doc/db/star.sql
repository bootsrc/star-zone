/*
Navicat MariaDB Data Transfer

Source Server         : huabei3-1-mariadb-star
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : star

Target Server Type    : MariaDB
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-05-19 12:31:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account_data
-- ----------------------------
DROP TABLE IF EXISTS `account_data`;
CREATE TABLE `account_data` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `mi_regid` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for captcha_record
-- ----------------------------
DROP TABLE IF EXISTS `captcha_record`;
CREATE TABLE `captcha_record` (
  `id` bigint(20) DEFAULT NULL,
  `mobile` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `captcha` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for chat_msg
-- ----------------------------
DROP TABLE IF EXISTS `chat_msg`;
CREATE TABLE `chat_msg` (
  `id` bigint(20) DEFAULT NULL,
  `sender_id` bigint(20) DEFAULT NULL,
  `receiver_id` bigint(20) DEFAULT NULL,
  `msg_body` text COLLATE utf8mb4_unicode_ci,
  `msg_type` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for daily_check_in
-- ----------------------------
DROP TABLE IF EXISTS `daily_check_in`;
CREATE TABLE `daily_check_in` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for file_resource
-- ----------------------------
DROP TABLE IF EXISTS `file_resource`;
CREATE TABLE `file_resource` (
  `file_id` bigint(20) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `creater` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `file_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `file_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `states` int(11) NOT NULL,
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for fly_shard
-- ----------------------------
DROP TABLE IF EXISTS `fly_shard`;
CREATE TABLE `fly_shard` (
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `count` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for fly_token
-- ----------------------------
DROP TABLE IF EXISTS `fly_token`;
CREATE TABLE `fly_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for fly_user
-- ----------------------------
DROP TABLE IF EXISTS `fly_user`;
CREATE TABLE `fly_user` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int(3) DEFAULT '0',
  `password` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mobile` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(20) NOT NULL,
  `version` int(11) DEFAULT '1' COMMENT '乐观锁的版本号',
  `stock` int(11) DEFAULT '1' COMMENT '库存量',
  `big_img` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `catalog_id` int(20) DEFAULT NULL,
  `hot` bit(1) NOT NULL,
  `img` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `img_list` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `introduce` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `label` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `origin_price` int(11) DEFAULT NULL,
  `price` int(11) NOT NULL,
  `product_html` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sort_value` int(11) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for order_0
-- ----------------------------
DROP TABLE IF EXISTS `order_0`;
CREATE TABLE `order_0` (
  `id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `address` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(3) NULL DEFAULT NULL COMMENT '更新时间',
  `consignee` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收货人',
  `consignee_moblie` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收货人电话',
  `remark` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `province` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '省份',
  `city` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '城市',
  `district` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区县',
  `express_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `deliver_time` datetime DEFAULT NULL COMMENT '发货时间',
  `confirm_time` datetime DEFAULT NULL COMMENT '确认时间',
  `delivery_way` int(11) DEFAULT NULL COMMENT '配送方式  0 快递  1自提',
  `cart_item_list` varchar(3000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '购物车里所有的商品信息(json string)',
  `discount_list` varchar(3000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '使用的所有优惠券(json string)',
  `amount` bigint(12) DEFAULT NULL COMMENT '商品总价格(不含运费)',
  `postage` bigint(12) DEFAULT NULL COMMENT '邮费',
  `should_pay` bigint(12) DEFAULT NULL COMMENT 'shouldPay = 商品总价格 + postage - discountList总金额'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for order_1
-- ----------------------------
DROP TABLE IF EXISTS `order_1`;
CREATE TABLE `order_1` (
  `id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `address` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(3) NULL DEFAULT NULL COMMENT '更新时间',
  `consignee` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '收货人',
  `consignee_moblie` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '收货人电话',
  `remark` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `province` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '省份',
  `city` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '城市',
  `district` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '区县',
  `express_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `deliver_time` datetime DEFAULT NULL COMMENT '发货时间',
  `confirm_time` datetime DEFAULT NULL COMMENT '确认时间',
  `delivery_way` int(11) DEFAULT NULL COMMENT '配送方式  0 快递  1自提',
  `cart_item_list` varchar(3000) CHARACTER SET utf8 DEFAULT NULL COMMENT '购物车里所有的商品信息(json string)',
  `discount_list` varchar(3000) CHARACTER SET utf8 DEFAULT NULL COMMENT '使用的所有优惠券(json string)',
  `amount` bigint(12) DEFAULT NULL COMMENT '商品总价格(不含运费)',
  `postage` bigint(12) DEFAULT NULL COMMENT '邮费',
  `should_pay` bigint(12) DEFAULT NULL COMMENT 'shouldPay = 商品总价格 + postage - discountList总金额'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for order_2
-- ----------------------------
DROP TABLE IF EXISTS `order_2`;
CREATE TABLE `order_2` (
  `id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `address` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(3) NULL DEFAULT NULL COMMENT '更新时间',
  `consignee` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收货人',
  `consignee_moblie` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收货人电话',
  `remark` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `province` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '省份',
  `city` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '城市',
  `district` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区县',
  `express_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `deliver_time` datetime DEFAULT NULL COMMENT '发货时间',
  `confirm_time` datetime DEFAULT NULL COMMENT '确认时间',
  `delivery_way` int(11) DEFAULT NULL COMMENT '配送方式  0 快递  1自提',
  `cart_item_list` varchar(3000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '购物车里所有的商品信息(json string)',
  `discount_list` varchar(3000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '使用的所有优惠券(json string)',
  `amount` bigint(12) DEFAULT NULL COMMENT '商品总价格(不含运费)',
  `postage` bigint(12) DEFAULT NULL COMMENT '邮费',
  `should_pay` bigint(12) DEFAULT NULL COMMENT 'shouldPay = 商品总价格 + postage - discountList总金额'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for order_3
-- ----------------------------
DROP TABLE IF EXISTS `order_3`;
CREATE TABLE `order_3` (
  `id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `address` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(3) NULL DEFAULT NULL COMMENT '更新时间',
  `consignee` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收货人',
  `consignee_moblie` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收货人电话',
  `remark` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `province` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '省份',
  `city` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '城市',
  `district` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区县',
  `express_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `deliver_time` datetime DEFAULT NULL COMMENT '发货时间',
  `confirm_time` datetime DEFAULT NULL COMMENT '确认时间',
  `delivery_way` int(11) DEFAULT NULL COMMENT '配送方式  0 快递  1自提',
  `cart_item_list` varchar(3000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '购物车里所有的商品信息(json string)',
  `discount_list` varchar(3000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '使用的所有优惠券(json string)',
  `amount` bigint(12) DEFAULT NULL COMMENT '商品总价格(不含运费)',
  `postage` bigint(12) DEFAULT NULL COMMENT '邮费',
  `should_pay` bigint(12) DEFAULT NULL COMMENT 'shouldPay = 商品总价格 + postage - discountList总金额'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for pay_msg
-- ----------------------------
DROP TABLE IF EXISTS `pay_msg`;
CREATE TABLE `pay_msg` (
  `msg_id` bigint(20) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `create_time` timestamp(3) NULL DEFAULT NULL,
  `pay_type` int(3) DEFAULT '0',
  `amount` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for star_comment
-- ----------------------------
DROP TABLE IF EXISTS `star_comment`;
CREATE TABLE `star_comment` (
  `id` bigint(20) DEFAULT NULL,
  `comment_text` text COLLATE utf8mb4_unicode_ci,
  `create_time` datetime DEFAULT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment
-- ----------------------------
DROP TABLE IF EXISTS `star_moment`;
CREATE TABLE `star_moment` (
  `id` bigint(20) DEFAULT NULL,
  `content` text COLLATE utf8mb4_unicode_ci,
  `img` text COLLATE utf8mb4_unicode_ci,
  `create_time` datetime DEFAULT NULL,
  `like_count` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_0
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_0`;
CREATE TABLE `star_moment_like_0` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_1
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_1`;
CREATE TABLE `star_moment_like_1` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_10
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_10`;
CREATE TABLE `star_moment_like_10` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_11
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_11`;
CREATE TABLE `star_moment_like_11` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_12
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_12`;
CREATE TABLE `star_moment_like_12` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_13
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_13`;
CREATE TABLE `star_moment_like_13` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_14
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_14`;
CREATE TABLE `star_moment_like_14` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_15
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_15`;
CREATE TABLE `star_moment_like_15` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_16
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_16`;
CREATE TABLE `star_moment_like_16` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_17
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_17`;
CREATE TABLE `star_moment_like_17` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_18
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_18`;
CREATE TABLE `star_moment_like_18` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_19
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_19`;
CREATE TABLE `star_moment_like_19` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_2
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_2`;
CREATE TABLE `star_moment_like_2` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_20
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_20`;
CREATE TABLE `star_moment_like_20` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_21
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_21`;
CREATE TABLE `star_moment_like_21` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_22
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_22`;
CREATE TABLE `star_moment_like_22` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_23
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_23`;
CREATE TABLE `star_moment_like_23` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_24
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_24`;
CREATE TABLE `star_moment_like_24` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_25
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_25`;
CREATE TABLE `star_moment_like_25` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_26
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_26`;
CREATE TABLE `star_moment_like_26` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_27
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_27`;
CREATE TABLE `star_moment_like_27` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_28
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_28`;
CREATE TABLE `star_moment_like_28` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_29
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_29`;
CREATE TABLE `star_moment_like_29` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_3
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_3`;
CREATE TABLE `star_moment_like_3` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_30
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_30`;
CREATE TABLE `star_moment_like_30` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_31
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_31`;
CREATE TABLE `star_moment_like_31` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_4
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_4`;
CREATE TABLE `star_moment_like_4` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_5
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_5`;
CREATE TABLE `star_moment_like_5` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_6
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_6`;
CREATE TABLE `star_moment_like_6` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_7
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_7`;
CREATE TABLE `star_moment_like_7` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_8
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_8`;
CREATE TABLE `star_moment_like_8` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_moment_like_9
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_9`;
CREATE TABLE `star_moment_like_9` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for star_topic
-- ----------------------------
DROP TABLE IF EXISTS `star_topic`;
CREATE TABLE `star_topic` (
  `topic_id` bigint(20) NOT NULL COMMENT '话题ID',
  `title` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '话题标题',
  `img` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '话题LOGO图片url',
  `introduction` longtext COLLATE utf8mb4_unicode_ci COMMENT '话题引言',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `content` longtext COLLATE utf8mb4_unicode_ci,
  `status` int(11) DEFAULT '0' COMMENT 'status=0:未上线；status=1:已上线',
  `serial` int(11) DEFAULT '100' COMMENT '排序号，数值小的排前面,最小为0',
  PRIMARY KEY (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for user_profile
-- ----------------------------
DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE `user_profile` (
  `user_id` bigint(20) NOT NULL,
  `mobile` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `star_sign` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `nickname` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '昵称',
  `head_img` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `head_version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for user_score
-- ----------------------------
DROP TABLE IF EXISTS `user_score`;
CREATE TABLE `user_score` (
  `user_id` bigint(20) NOT NULL,
  `check_in_count` int(11) NOT NULL DEFAULT '0' COMMENT '签到次数',
  `score` int(11) NOT NULL DEFAULT '0' COMMENT '积分',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
