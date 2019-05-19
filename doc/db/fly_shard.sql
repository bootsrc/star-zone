/*
Navicat MariaDB Data Transfer

Source Server         : huabei3-1-mariadb-star
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : star

Target Server Type    : MariaDB
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-05-19 12:35:53
*/

SET FOREIGN_KEY_CHECKS=0;

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
-- Records of fly_shard
-- ----------------------------
INSERT INTO `fly_shard` VALUES ('order', '4', '2018-07-05 11:33:22', '2018-07-05 11:33:22');
INSERT INTO `fly_shard` VALUES ('star_moment_like', '32', '2018-07-05 15:11:37', '2018-07-05 15:11:37');
