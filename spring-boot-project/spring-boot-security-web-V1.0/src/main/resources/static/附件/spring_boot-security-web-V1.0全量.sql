/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 50533
Source Host           : 127.0.0.1:3306
Source Database       : spring_boot

Target Server Type    : MYSQL
Target Server Version : 50533
File Encoding         : 65001

Date: 2019-09-10 18:03:41
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('25', 'admin-tan', 'a123456', '谭好好', '2019-09-07', null, '1', '1', null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('29', 'tanzxuhao', 'a123456', '谭好好', null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for s_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_permission`;
CREATE TABLE `s_permission` (
  `id` mediumint(11) NOT NULL,
  `permission` varchar(32) DEFAULT NULL,
  `url` varchar(32) DEFAULT NULL,
  `describe` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_permission
-- ----------------------------
INSERT INTO `s_permission` VALUES ('1', 'P_INDEX', '/index', 'index页面资源');
INSERT INTO `s_permission` VALUES ('2', 'P_ADMIN', '/admin', 'admin页面资源');
INSERT INTO `s_permission` VALUES ('3', 'P_HELLO', '/hello', 'hello页面资源');

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
  `id` mediumint(11) NOT NULL,
  `role` varchar(32) DEFAULT NULL,
  `describe` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES ('1', 'R_ADMIN', '大总管，所有权限');
INSERT INTO `s_role` VALUES ('2', 'R_HELLO', '说hello相关的权限');

-- ----------------------------
-- Table structure for s_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_role_permission`;
CREATE TABLE `s_role_permission` (
  `fk_role_id` mediumint(11) DEFAULT NULL,
  `fk_permission_id` mediumint(11) DEFAULT NULL,
  KEY `union_key` (`fk_role_id`,`fk_permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_role_permission
-- ----------------------------
INSERT INTO `s_role_permission` VALUES ('1', '1');
INSERT INTO `s_role_permission` VALUES ('1', '2');
INSERT INTO `s_role_permission` VALUES ('1', '3');
INSERT INTO `s_role_permission` VALUES ('2', '1');
INSERT INTO `s_role_permission` VALUES ('2', '3');

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `id` mediumint(11) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('1', 'admin', 'admin');
INSERT INTO `s_user` VALUES ('2', 'veiking', 'veiking');
INSERT INTO `s_user` VALUES ('3', 'xiaoming', 'xiaoming');

-- ----------------------------
-- Table structure for s_user_role
-- ----------------------------
DROP TABLE IF EXISTS `s_user_role`;
CREATE TABLE `s_user_role` (
  `fk_user_id` int(11) DEFAULT NULL,
  `fk_role_id` int(11) DEFAULT NULL,
  KEY `union_key` (`fk_user_id`,`fk_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user_role
-- ----------------------------
INSERT INTO `s_user_role` VALUES ('1', '1');
INSERT INTO `s_user_role` VALUES ('2', '2');
