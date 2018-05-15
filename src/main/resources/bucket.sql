/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : bucket

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2018-05-15 14:44:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `pid` int(10) DEFAULT '0' COMMENT '父id',
  `name` varchar(64) DEFAULT NULL COMMENT '权限描述',
  `level` int(10) DEFAULT '0' COMMENT '级别',
  `sort` int(10) DEFAULT '0' COMMENT '排序字段',
  `code` varchar(64) DEFAULT NULL COMMENT '权限编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '0', '权限设置', '0', '0', 'permisson:list');
INSERT INTO `permission` VALUES ('2', '1', '权限添加', '1', '1', 'permission:add');
INSERT INTO `permission` VALUES ('3', '1', '权限更新', '1', '2', 'permission:update');
INSERT INTO `permission` VALUES ('4', '1', '权限删除', '1', '3', 'permission:delete');
INSERT INTO `permission` VALUES ('5', '0', '角色管理', '0', '4', 'role:list');
INSERT INTO `permission` VALUES ('6', '5', '角色添加', '1', '5', 'role:add');
INSERT INTO `permission` VALUES ('7', '5', '角色更新', '1', '6', 'role:update');
INSERT INTO `permission` VALUES ('8', '5', '角色删除', '1', '7', 'role:delete');
INSERT INTO `permission` VALUES ('9', '0', '人员管理', '0', '8', 'user:list');
INSERT INTO `permission` VALUES ('10', '9', '人员添加', '1', '9', 'user:add');
INSERT INTO `permission` VALUES ('11', '9', '人员更新', '1', '10', 'user:update');
INSERT INTO `permission` VALUES ('12', '9', '人员删除', '1', '11', 'user:delete');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '角色名称',
  `code` varchar(32) DEFAULT NULL COMMENT '角色编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', 'admin');
INSERT INTO `role` VALUES ('2', '代理', 'agency');
INSERT INTO `role` VALUES ('3', '客户', 'customer');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` int(20) DEFAULT NULL COMMENT '角色ID',
  `permission_id` int(20) DEFAULT NULL COMMENT '权限ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关系表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1');
INSERT INTO `role_permission` VALUES ('1', '2');
INSERT INTO `role_permission` VALUES ('1', '3');
INSERT INTO `role_permission` VALUES ('1', '4');
INSERT INTO `role_permission` VALUES ('1', '5');
INSERT INTO `role_permission` VALUES ('1', '6');
INSERT INTO `role_permission` VALUES ('1', '7');
INSERT INTO `role_permission` VALUES ('1', '8');
INSERT INTO `role_permission` VALUES ('1', '9');
INSERT INTO `role_permission` VALUES ('1', '10');
INSERT INTO `role_permission` VALUES ('1', '11');
INSERT INTO `role_permission` VALUES ('1', '12');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(255) NOT NULL COMMENT '登录名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `nick_name` varchar(20) DEFAULT '' COMMENT '用户昵称',
  `telephone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `last_login_time` varchar(64) DEFAULT NULL COMMENT '最后登录时间',
  `status` int(1) DEFAULT '1' COMMENT '1:有效，0:禁止登录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '杰克', '15888888888', 'jack@admin.com', '2016-06-16 11:15:33', '2016-06-16 11:15:33', '1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` int(20) DEFAULT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
