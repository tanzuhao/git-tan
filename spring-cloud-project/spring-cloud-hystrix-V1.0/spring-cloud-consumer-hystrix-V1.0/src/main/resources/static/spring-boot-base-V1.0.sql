/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 50533
Source Host           : localhost:3306
Source Database       : spring_boot

Target Server Type    : MYSQL
Target Server Version : 50533
File Encoding         : 65001

Date: 2019-09-05 19:00:01
*/

create database spring_boot default character set utf8;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` mediumint(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `version_number` varchar(20) DEFAULT NULL,
  `sex` int(1) DEFAULT NULL,
  `enabled` int(1) DEFAULT NULL,
  `operate` varchar(20) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `create_date` varchar(50) DEFAULT NULL,
  `updater` varchar(30) DEFAULT NULL,
  `update_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('15', 'tanzxuhao', 'a123456', '谭好好', null, null, null, null, null, null, null, null, null);
