/*
Navicat MySQL Data Transfer

Source Server         : flylib-hk-mysql
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : mall

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2018-07-05 14:59:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for star_moment_like_0
-- ----------------------------
DROP TABLE IF EXISTS `star_moment_like_0`;
CREATE TABLE `star_moment_like_0` (
  `id` bigint(20) NOT NULL,
  `moment_id` bigint(20) DEFAULT NULL,
  `like_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
