/*
Navicat MySQL Data Transfer

Source Server         : 239
Source Server Version : 50720
Source Host           : 192.168.2.239:10030
Source Database       : bike_athletics

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-02-25 16:39:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for farest_mileage_board
-- ----------------------------
DROP TABLE IF EXISTS `farest_mileage_board`;
CREATE TABLE `farest_mileage_board` (
  `username` varchar(32) NOT NULL,
  `value` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for player
-- ----------------------------
DROP TABLE IF EXISTS `player`;
CREATE TABLE `player` (
  `username` varchar(32) NOT NULL,
  `data` text,
  `social_data` text,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for score_board
-- ----------------------------
DROP TABLE IF EXISTS `score_board`;
CREATE TABLE `score_board` (
  `username` varchar(32) NOT NULL,
  `value` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `username` varchar(32) NOT NULL,
  `password` varchar(60) NOT NULL,
  `role` varchar(32) DEFAULT 'USER' COMMENT 'ROLE_',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for total_mileage_board
-- ----------------------------
DROP TABLE IF EXISTS `total_mileage_board`;
CREATE TABLE `total_mileage_board` (
  `username` varchar(32) NOT NULL,
  `value` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
