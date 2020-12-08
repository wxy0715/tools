/*
Navicat MySQL Data Transfer

Source Server         : hotel
Source Server Version : 50173
Source Host           : 47.101.130.87:3306
Source Database       : wxy0715

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2020-03-29 12:44:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for in_room_info
-- ----------------------------
DROP TABLE IF EXISTS `in_room_info`;
CREATE TABLE `in_room_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer_name` varchar(40) DEFAULT NULL COMMENT '客人姓名',
  `gender` varchar(2) DEFAULT '1' COMMENT '性别(1男 0女)',
  `is_vip` varchar(2) DEFAULT '0' COMMENT '0普通，1vip',
  `idcard` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `money` varchar(40) DEFAULT NULL COMMENT '押金',
  `create_date` varchar(20) DEFAULT NULL COMMENT '入住时间',
  `room_id` bigint(20) DEFAULT NULL COMMENT '房间表主键',
  `status` varchar(2) DEFAULT '2' COMMENT '显示状态：0退房,1续住,2住房中',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of in_room_info
-- ----------------------------
INSERT INTO `in_room_info` VALUES ('43', '王星宇', '1', '1', '411311199001015599', '13012345678', '100', '2018-09-06 20:19:03', '7', '0');
INSERT INTO `in_room_info` VALUES ('44', '刘备', '0', '1', '411311199001015599', '13012345678', '100', '2018-09-06 20:19:03', '2', '2');
INSERT INTO `in_room_info` VALUES ('45', '王星宇', '1', '1', '411311199001015599', '13012345678', '10000w', '2018-09-06 20:19:03', '3', '2');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_num` varchar(20) DEFAULT NULL COMMENT '订单编号',
  `order_money` bigint(20) DEFAULT NULL COMMENT '订单总价',
  `order_status` varchar(2) DEFAULT '0' COMMENT '0未结算，1已结算',
  `room_id` bigint(20) DEFAULT NULL COMMENT '房间主键',
  `create_date` datetime DEFAULT NULL COMMENT '下单时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for room_type
-- ----------------------------
DROP TABLE IF EXISTS `room_type`;
CREATE TABLE `room_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `room_type_name` varchar(20) DEFAULT NULL COMMENT '房间类型名',
  `room_price` bigint(100) DEFAULT NULL COMMENT '房间的单价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room_type
-- ----------------------------
INSERT INTO `room_type` VALUES ('1', '单人间', '140');
INSERT INTO `room_type` VALUES ('2', '双人间', '180');
INSERT INTO `room_type` VALUES ('3', '豪华间', '1000');
INSERT INTO `room_type` VALUES ('4', '别墅', '15000');
INSERT INTO `room_type` VALUES ('5', '情趣床', '20000');
INSERT INTO `room_type` VALUES ('6', '大床房', '5000');
INSERT INTO `room_type` VALUES ('7', '电脑房', '13000');

-- ----------------------------
-- Table structure for rooms
-- ----------------------------
DROP TABLE IF EXISTS `rooms`;
CREATE TABLE `rooms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `room_num` varchar(10) DEFAULT NULL COMMENT '房间编号',
  `room_status` varchar(2) DEFAULT '0' COMMENT '房间的状态(0空房，1入住)',
  `room_type_id` bigint(20) DEFAULT '1' COMMENT '房间类型主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rooms
-- ----------------------------
INSERT INTO `rooms` VALUES ('1', '8201', '0', '1');
INSERT INTO `rooms` VALUES ('2', '8202', '1', '1');
INSERT INTO `rooms` VALUES ('3', '8203', '1', '3');
INSERT INTO `rooms` VALUES ('4', '8204', '0', '2');
INSERT INTO `rooms` VALUES ('5', '8205', '0', '2');
INSERT INTO `rooms` VALUES ('6', '8206', '0', '1');
INSERT INTO `rooms` VALUES ('7', '8207', '0', '1');
INSERT INTO `rooms` VALUES ('8', '8208', '0', '2');
INSERT INTO `rooms` VALUES ('9', '8209', '0', '2');
INSERT INTO `rooms` VALUES ('10', '8210', '0', '2');
INSERT INTO `rooms` VALUES ('11', '8211', '0', '2');
INSERT INTO `rooms` VALUES ('12', '8212', '0', '1');
INSERT INTO `rooms` VALUES ('13', '8213', '0', '1');
INSERT INTO `rooms` VALUES ('14', '8214', '0', '1');
INSERT INTO `rooms` VALUES ('15', '8215', '0', '1');

-- ----------------------------
-- Table structure for system_authority
-- ----------------------------
DROP TABLE IF EXISTS `system_authority`;
CREATE TABLE `system_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `authority_name` varchar(20) NOT NULL COMMENT '权限名',
  `authority_url` varchar(200) NOT NULL COMMENT '权限地址',
  `parent` bigint(20) NOT NULL DEFAULT '0' COMMENT '记住上级的主键:0一级节点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_authority
-- ----------------------------
INSERT INTO `system_authority` VALUES ('1', '入住管理', '#', '0');
INSERT INTO `system_authority` VALUES ('2', '订单管理', '#', '0');
INSERT INTO `system_authority` VALUES ('3', '会员管理', '#', '0');
INSERT INTO `system_authority` VALUES ('4', '用户管理', '#', '0');
INSERT INTO `system_authority` VALUES ('5', '客房管理', '#', '0');
INSERT INTO `system_authority` VALUES ('6', '客人意见', '#', '0');
INSERT INTO `system_authority` VALUES ('7', '入住信息管理', 'bill/inroominfo.jsp', '1');
INSERT INTO `system_authority` VALUES ('11', '订单信息', 'order/orderInfo.jsp', '2');
INSERT INTO `system_authority` VALUES ('12', '订单添加', 'order/addOrder.jsp', '2');
INSERT INTO `system_authority` VALUES ('13', '会员信息查询', 'vip/vip.jsp', '3');
INSERT INTO `system_authority` VALUES ('14', '会员信息管理', 'vip/updateVipInfo.jsp', '3');
INSERT INTO `system_authority` VALUES ('15', '添加会员', 'vip/addvip.jsp', '3');
INSERT INTO `system_authority` VALUES ('16', '客房信息查询', 'room/houseInfo.jsp', '4');
INSERT INTO `system_authority` VALUES ('17', '客房信息管理', 'room/houseManage.jsp', '4');
INSERT INTO `system_authority` VALUES ('18', '添加客房', 'room/addRoom.jsp', '4');
INSERT INTO `system_authority` VALUES ('19', '用户信息查询', 'user/userInfo.jsp', '5');
INSERT INTO `system_authority` VALUES ('20', '用户信息管理', 'ser/userInfo.jsp', '5');
INSERT INTO `system_authority` VALUES ('21', '添加用户', 'user/addUser.jsp', '0');

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `username` varchar(40) NOT NULL COMMENT '账号',
  `password` blob NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('1', 'admin', 0x5D2875B20D67AC36EEEB36F636AB6284);
INSERT INTO `system_user` VALUES ('2', 'admin1', 0xB2A884BD9489F36DDB991D8F665CF3F7);

-- ----------------------------
-- Table structure for user_authority
-- ----------------------------
DROP TABLE IF EXISTS `user_authority`;
CREATE TABLE `user_authority` (
  `user_id` bigint(20) NOT NULL COMMENT '系统用户主键',
  `authority_id` bigint(20) NOT NULL COMMENT '权限主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_authority
-- ----------------------------
INSERT INTO `user_authority` VALUES ('1', '1');
INSERT INTO `user_authority` VALUES ('1', '2');
INSERT INTO `user_authority` VALUES ('1', '3');
INSERT INTO `user_authority` VALUES ('1', '4');
INSERT INTO `user_authority` VALUES ('1', '5');
INSERT INTO `user_authority` VALUES ('1', '6');
INSERT INTO `user_authority` VALUES ('1', '7');
INSERT INTO `user_authority` VALUES ('1', '8');
INSERT INTO `user_authority` VALUES ('1', '9');
INSERT INTO `user_authority` VALUES ('1', '10');
INSERT INTO `user_authority` VALUES ('1', '11');
INSERT INTO `user_authority` VALUES ('1', '12');
INSERT INTO `user_authority` VALUES ('1', '13');
INSERT INTO `user_authority` VALUES ('1', '14');
INSERT INTO `user_authority` VALUES ('1', '15');
INSERT INTO `user_authority` VALUES ('1', '16');
INSERT INTO `user_authority` VALUES ('1', '17');
INSERT INTO `user_authority` VALUES ('1', '18');
INSERT INTO `user_authority` VALUES ('1', '19');
INSERT INTO `user_authority` VALUES ('1', '20');
INSERT INTO `user_authority` VALUES ('1', '21');
INSERT INTO `user_authority` VALUES ('1', '22');
INSERT INTO `user_authority` VALUES ('2', '11');
INSERT INTO `user_authority` VALUES ('2', '4');
INSERT INTO `user_authority` VALUES ('2', '10');
INSERT INTO `user_authority` VALUES ('2', '15');
INSERT INTO `user_authority` VALUES ('2', '20');
INSERT INTO `user_authority` VALUES ('2', '2');
INSERT INTO `user_authority` VALUES ('2', '3');
INSERT INTO `user_authority` VALUES ('2', '12');

-- ----------------------------
-- Table structure for vip
-- ----------------------------
DROP TABLE IF EXISTS `vip`;
CREATE TABLE `vip` (
  `vip` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `vip_num` varchar(20) NOT NULL COMMENT '会员卡编号',
  `vip_rate` float NOT NULL COMMENT '1-9折',
  `idcard` varchar(20) NOT NULL COMMENT '会员身份证',
  `create_date` datetime NOT NULL COMMENT '会员办理日期',
  PRIMARY KEY (`vip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vip
-- ----------------------------

-- ----------------------------
-- Table structure for wxykey
-- ----------------------------
DROP TABLE IF EXISTS `wxykey`;
CREATE TABLE `wxykey` (
  `encryptkey` varchar(10) NOT NULL DEFAULT '',
  `id` tinyint(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wxykey
-- ----------------------------
INSERT INTO `wxykey` VALUES ('wxy0715', '1');
