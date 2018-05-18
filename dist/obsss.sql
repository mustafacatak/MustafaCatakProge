/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 100128
 Source Host           : localhost:3306
 Source Schema         : obs

 Target Server Type    : MySQL
 Target Server Version : 100128
 File Encoding         : 65001

 Date: 17/05/2018 08:54:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ogrenci
-- ----------------------------
DROP TABLE IF EXISTS `ogrenci`;
CREATE TABLE `ogrenci`  (
  `ogrenciid` int(11) NOT NULL AUTO_INCREMENT,
  `ogrencino` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `adsoyad` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `sehir` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `telno` varchar(11) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ogrenciid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ogrenci
-- ----------------------------
INSERT INTO `ogrenci` VALUES (1, '546545646', 'mustafa', 'burdur', '546546');
INSERT INTO `ogrenci` VALUES (4, '54654646', 'Hasan', 'Ankara', '64546546');
INSERT INTO `ogrenci` VALUES (11, '456456', 'hsan', 'Ankara', '050655656');
INSERT INTO `ogrenci` VALUES (14, '65464646', 'mustafa', 'ankara', '546465');
INSERT INTO `ogrenci` VALUES (16, '545646', 'Hasan', 'Manisa', '454646');
INSERT INTO `ogrenci` VALUES (17, '456456', 'hsan', 'Ankara', '050655656');

SET FOREIGN_KEY_CHECKS = 1;
