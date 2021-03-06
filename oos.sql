/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost
 Source Database       : oos

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : utf-8

 Date: 03/18/2018 13:48:43 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sq_diningTable`
-- ----------------------------
DROP TABLE IF EXISTS `sq_diningTable`;
CREATE TABLE `sq_diningTable` (
  `id` varchar(32) NOT NULL COMMENT '桌号',
  `shopCarId` varchar(32) DEFAULT NULL COMMENT '购物车id',
  `renshu` varchar(255) DEFAULT NULL COMMENT '最大人数',
  `status` varchar(255) DEFAULT '0' COMMENT '状态，1 使用中  0 空闲',
  PRIMARY KEY (`id`),
  UNIQUE KEY `weiyi` (`shopCarId`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `sq_diningTable`
-- ----------------------------
BEGIN;
INSERT INTO `sq_diningTable` VALUES ('1', null, '8', '1'), ('11', null, '4', '1'), ('12', '152034173668664', '4', '1'), ('13', '152101583034624', '4', '1'), ('14', null, '4', '0'), ('16', null, '2', '0'), ('17', null, '4', '0'), ('18', null, '4', '0'), ('19', null, '4', '0'), ('2', null, '4', '0'), ('20', null, '4', '0'), ('28', null, '4', '0'), ('29', null, '4', '0'), ('3', null, '2', '0'), ('30', null, '4', '0'), ('4', null, '2', '0'), ('5', null, '2', '0'), ('6', null, '2', '0'), ('7', null, '2', '0'), ('8', null, '2', '0'), ('9', null, '4', '1');
COMMIT;

-- ----------------------------
--  Table structure for `sq_dishes`
-- ----------------------------
DROP TABLE IF EXISTS `sq_dishes`;
CREATE TABLE `sq_dishes` (
  `id` varchar(32) NOT NULL,
  `typeId` varchar(32) DEFAULT NULL COMMENT '菜品类型id',
  `originalPrice` varchar(10) DEFAULT NULL COMMENT '原价',
  `price` varchar(10) DEFAULT NULL COMMENT '现价',
  `isVIP` varchar(10) DEFAULT NULL COMMENT '是否会员价 1是 0否',
  `name` varchar(255) DEFAULT NULL COMMENT '菜品名称',
  `pic` varchar(255) DEFAULT NULL COMMENT '图片url',
  PRIMARY KEY (`id`),
  KEY `fk_sq_dishes` (`typeId`),
  CONSTRAINT `fk_sq_dishes` FOREIGN KEY (`typeId`) REFERENCES `sq_dishesType` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `sq_dishes`
-- ----------------------------
BEGIN;
INSERT INTO `sq_dishes` VALUES ('1', '1', '10', '8', '1', '凉菜1', '/2018-03-16/80e6618c-943a-40c1-830d-9cb0257d186a.png'), ('100', '3', '10', '8', '0', '这是测试菜品', '/2018-03-12/2cf5a4d8-c90d-4c0a-b70a-494f3617ea21.jpg'), ('2', '1', '10', '8', '1', '凉菜2', '/2018-03-12/2cf5a4d8-c90d-4c0a-b70a-494f3617ea21.jpg'), ('3', '1', '10', '8', '1', '凉菜3', '/2018-03-12/2cf5a4d8-c90d-4c0a-b70a-494f3617ea21.jpg'), ('4', '1', '10', '8', '1', '凉菜4', '/2018-03-12/2cf5a4d8-c90d-4c0a-b70a-494f3617ea21.jpg'), ('6', '1', '10', '8', '1', '凉菜6', '/2018-03-12/2cf5a4d8-c90d-4c0a-b70a-494f3617ea21.jpg'), ('7', '1', '20', '18', '1', '凉皮', '/2018-03-12/2cf5a4d8-c90d-4c0a-b70a-494f3617ea21.jpg'), ('8', '1', '12', '8', '1', '荠菜粉', '/2018-03-12/2cf5a4d8-c90d-4c0a-b70a-494f3617ea21.jpg');
COMMIT;

-- ----------------------------
--  Table structure for `sq_dishesType`
-- ----------------------------
DROP TABLE IF EXISTS `sq_dishesType`;
CREATE TABLE `sq_dishesType` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '类型名',
  `isShowOnHome` varchar(255) DEFAULT NULL COMMENT '是否在首页展示 1展示 0不展示',
  `zIndex` int(255) NOT NULL DEFAULT '999' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `sq_dishesType`
-- ----------------------------
BEGIN;
INSERT INTO `sq_dishesType` VALUES ('1', '凉菜', '1', '1'), ('2', '酒水', '1', '2'), ('3', '川菜', '1', '3'), ('4', '徽菜', '1', '4');
COMMIT;

-- ----------------------------
--  Table structure for `sq_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sq_menu`;
CREATE TABLE `sq_menu` (
  `id` varchar(32) NOT NULL,
  `menuName` varchar(255) DEFAULT NULL COMMENT '菜单名',
  `parentId` varchar(255) DEFAULT NULL COMMENT '父菜单id',
  `url` varchar(255) DEFAULT NULL COMMENT 'url',
  `zIndex` int(255) NOT NULL DEFAULT '999' COMMENT '排序',
  PRIMARY KEY (`id`),
  KEY `fk_sq_menu` (`parentId`),
  CONSTRAINT `fk_sq_menu` FOREIGN KEY (`parentId`) REFERENCES `sq_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `sq_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sq_menu` VALUES ('1', '基础功能', null, null, '0'), ('13', '财务管理', '2', '/test/xxxx', '5'), ('14', '收入报表', '13', '/page/admin/IncomeStatement', '0'), ('15', '订单查询', '13', '/page/admin/orderDetail', '0'), ('16', '购物车', '1', '/shoppingCar/getUI', '1'), ('17', '用户管理', '8', '/page/admin/userAdmin', '0'), ('18', '角色管理', '8', '/page/admin/roleAdmin', '1'), ('19', '模块管理', '8', '/page/admin/menuAdmin', '2'), ('2', '系统管理', null, null, '0'), ('20', '菜品管理', '7', '/dishes/getUI', '0'), ('21', '菜品类型管理', '7', '/page/admin/dishesTypeAdmin', '1'), ('3', '点餐', '1', '/page/baseFunction/Order', '0'), ('4', '上菜', '1', '/page/baseFunction/Serving', '4'), ('5', '配餐', '1', '/page/baseFunction/Food', '3'), ('6', '收银', '1', '/page/baseFunction/Cashier', '5'), ('7', '菜单管理', '2', null, '0'), ('8', '权限管理', '2', null, '3'), ('9', '桌位管理', '2', '/page/admin/diningTableAdmin', '1');
COMMIT;

-- ----------------------------
--  Table structure for `sq_order`
-- ----------------------------
DROP TABLE IF EXISTS `sq_order`;
CREATE TABLE `sq_order` (
  `id` varchar(32) NOT NULL,
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `userId` varchar(32) DEFAULT NULL COMMENT '用户id',
  `userName` varchar(32) DEFAULT NULL COMMENT '用户名',
  `totalprice` varchar(32) DEFAULT NULL COMMENT '无折扣商品总价',
  `shopnum` int(11) DEFAULT NULL COMMENT '商品数量',
  `isdiscount` varchar(255) DEFAULT NULL COMMENT '是否有折扣',
  `discount` varchar(255) DEFAULT NULL COMMENT '折扣钱数',
  `receivable` varchar(255) DEFAULT NULL COMMENT '应收钱数',
  `realPrice` varchar(32) DEFAULT NULL COMMENT '实收钱数',
  `changeMoney` varchar(255) DEFAULT NULL COMMENT '找零',
  `operatorId` varchar(255) DEFAULT NULL COMMENT '操作人(收银员)id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `sq_order`
-- ----------------------------
BEGIN;
INSERT INTO `sq_order` VALUES ('152117902305827', '2018-03-16 13:43:43', '2018-03-16 13:43:43', '152032032124707', '用户', '60', '6', '0', null, '60', '100', '40', '0'), ('152117908007234', '2018-03-16 13:44:40', '2018-03-16 13:44:40', '0', 'admin', '70', '7', '1', '4', '66', '70', '4', '0'), ('152118046084087', '2018-03-16 14:07:41', '2018-03-16 14:07:41', '0', 'admin', '10', '1', '1', '2', '8', '10', '2', '0');
COMMIT;

-- ----------------------------
--  Table structure for `sq_orderDetail`
-- ----------------------------
DROP TABLE IF EXISTS `sq_orderDetail`;
CREATE TABLE `sq_orderDetail` (
  `id` varchar(32) NOT NULL COMMENT '订单详情id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '创建时间',
  `orderId` varchar(32) DEFAULT NULL COMMENT '订单id',
  `shopId` varchar(32) DEFAULT NULL COMMENT '商品id',
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `originalprice` varchar(32) DEFAULT NULL COMMENT '商品原价',
  `price` varchar(10) DEFAULT NULL COMMENT '商品优惠后价格',
  `isvip` varchar(255) DEFAULT NULL COMMENT '是否打折商品',
  `shopnum` int(11) DEFAULT NULL COMMENT '商品数量',
  `xiaoji` varchar(255) DEFAULT NULL COMMENT '小计',
  PRIMARY KEY (`id`),
  KEY `fk_sq_orderDetail` (`orderId`),
  CONSTRAINT `fk_sq_orderDetail` FOREIGN KEY (`orderId`) REFERENCES `sq_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `sq_orderDetail`
-- ----------------------------
BEGIN;
INSERT INTO `sq_orderDetail` VALUES ('152117902307957', '2018-03-16 13:43:43', '2018-03-16 13:43:43', '152117902305827', '1', '凉菜1', '10', '8', '1', '1', '8'), ('152117902315752', '2018-03-16 13:43:43', '2018-03-16 13:43:43', '152117902305827', '2', '凉菜2', '10', '8', '1', '1', '8'), ('152117902316354', '2018-03-16 13:43:43', '2018-03-16 13:43:43', '152117902305827', '4', '凉菜4', '10', '8', '1', '1', '8'), ('152117902318149', '2018-03-16 13:43:43', '2018-03-16 13:43:43', '152117902305827', '3', '凉菜3', '10', '8', '1', '1', '8'), ('152117902318710', '2018-03-16 13:43:43', '2018-03-16 13:43:43', '152117902305827', '100', '这是测试菜品', '10', '8', '0', '2', '20'), ('152117908009236', '2018-03-16 13:44:40', '2018-03-16 13:44:40', '152117908007234', '3', '凉菜3', '10', '8', '1', '2', '16'), ('152117908010171', '2018-03-16 13:44:40', '2018-03-16 13:44:40', '152117908007234', '100', '这是测试菜品', '10', '8', '0', '5', '50'), ('152118046085580', '2018-03-16 14:07:41', '2018-03-16 14:07:41', '152118046084087', '3', '凉菜3', '10', '8', '1', '1', '8');
COMMIT;

-- ----------------------------
--  Table structure for `sq_role`
-- ----------------------------
DROP TABLE IF EXISTS `sq_role`;
CREATE TABLE `sq_role` (
  `id` varchar(32) NOT NULL COMMENT '角色编号',
  `roleName` varchar(255) DEFAULT NULL COMMENT '角色名',
  `zIndex` int(255) DEFAULT '0' COMMENT '同级排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `sq_role`
-- ----------------------------
BEGIN;
INSERT INTO `sq_role` VALUES ('00', '超级管理员', '2'), ('01', '用户', '1'), ('02', '服务员', '1'), ('03', '厨师', '1'), ('04', '收银员', '3'), ('05', '管理员', '4');
COMMIT;

-- ----------------------------
--  Table structure for `sq_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sq_role_menu`;
CREATE TABLE `sq_role_menu` (
  `roleId` varchar(32) NOT NULL,
  `menuId` varchar(32) NOT NULL,
  PRIMARY KEY (`roleId`,`menuId`),
  KEY `fk_sq_role_menu_1` (`menuId`),
  CONSTRAINT `fk_sq_role_menu` FOREIGN KEY (`roleId`) REFERENCES `sq_role` (`id`),
  CONSTRAINT `fk_sq_role_menu_1` FOREIGN KEY (`menuId`) REFERENCES `sq_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `sq_role_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sq_role_menu` VALUES ('00', '1'), ('00', '13'), ('05', '13'), ('00', '14'), ('05', '14'), ('00', '15'), ('05', '15'), ('00', '16'), ('01', '16'), ('00', '17'), ('05', '17'), ('00', '18'), ('05', '18'), ('00', '19'), ('05', '19'), ('00', '2'), ('05', '2'), ('00', '3'), ('01', '3'), ('00', '4'), ('02', '4'), ('00', '5'), ('03', '5'), ('00', '6'), ('04', '6'), ('00', '7'), ('05', '7'), ('00', '8'), ('05', '8'), ('00', '9'), ('05', '9');
COMMIT;

-- ----------------------------
--  Table structure for `sq_shoppingCar`
-- ----------------------------
DROP TABLE IF EXISTS `sq_shoppingCar`;
CREATE TABLE `sq_shoppingCar` (
  `id` varchar(32) NOT NULL COMMENT '购物车id',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '修改时间',
  `userid` varchar(32) DEFAULT NULL COMMENT '用户id',
  `shopNum` int(11) DEFAULT NULL COMMENT '商品数量',
  `totalPrice` int(255) DEFAULT NULL COMMENT '总价',
  `diningTableId` varchar(32) DEFAULT NULL COMMENT '餐桌 id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `weiyi` (`diningTableId`) USING HASH,
  KEY `fk_sq_shoppingCar` (`userid`),
  CONSTRAINT `fk_sq_shoppingCar` FOREIGN KEY (`userid`) REFERENCES `sq_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `sq_shoppingCar`
-- ----------------------------
BEGIN;
INSERT INTO `sq_shoppingCar` VALUES ('152118047168635', '2018-03-16 14:07:52', '2018-03-16 14:07:52', '0', '1', '8', null);
COMMIT;

-- ----------------------------
--  Table structure for `sq_shoppingCarDetail`
-- ----------------------------
DROP TABLE IF EXISTS `sq_shoppingCarDetail`;
CREATE TABLE `sq_shoppingCarDetail` (
  `id` varchar(32) NOT NULL COMMENT '购物车详情id',
  `shoppingCarId` varchar(32) DEFAULT NULL COMMENT '购物车id',
  `shopId` varchar(32) DEFAULT NULL COMMENT '商品id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `shopNum` int(11) DEFAULT NULL COMMENT '商品数量',
  `xiaoji` int(255) DEFAULT NULL COMMENT '小计',
  `status` varchar(255) DEFAULT '0' COMMENT '0未准备 1配菜中 2待上菜 3已上菜',
  PRIMARY KEY (`id`),
  KEY `fk_sq_shoppingCarDetail` (`shoppingCarId`),
  KEY `fk_sq_shoppingCarDetail_1` (`shopId`),
  CONSTRAINT `fk_sq_shoppingCarDetail` FOREIGN KEY (`shoppingCarId`) REFERENCES `sq_shoppingCar` (`id`),
  CONSTRAINT `fk_sq_shoppingCarDetail_1` FOREIGN KEY (`shopId`) REFERENCES `sq_dishes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `sq_shoppingCarDetail`
-- ----------------------------
BEGIN;
INSERT INTO `sq_shoppingCarDetail` VALUES ('152118047168617', '152118047168635', '1', '2018-03-16 14:07:52', '2018-03-16 14:07:52', '1', '8', '0');
COMMIT;

-- ----------------------------
--  Table structure for `sq_user`
-- ----------------------------
DROP TABLE IF EXISTS `sq_user`;
CREATE TABLE `sq_user` (
  `id` varchar(32) NOT NULL COMMENT '用户id',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `isVIP` varchar(255) DEFAULT NULL COMMENT '是否VIP',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_unique` (`username`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `sq_user`
-- ----------------------------
BEGIN;
INSERT INTO `sq_user` VALUES ('0', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1'), ('152032032124707', '用户', 'e10adc3949ba59abbe56e057f20f883e', '0'), ('152032035802241', '服务员', 'e10adc3949ba59abbe56e057f20f883e', '0'), ('152032037461347', '厨师', 'e10adc3949ba59abbe56e057f20f883e', '0'), ('152032038491448', '收银员', 'e10adc3949ba59abbe56e057f20f883e', '0'), ('152032039532188', '管理员', 'e10adc3949ba59abbe56e057f20f883e', '0');
COMMIT;

-- ----------------------------
--  Table structure for `sq_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sq_user_role`;
CREATE TABLE `sq_user_role` (
  `userId` varchar(32) NOT NULL,
  `roleId` varchar(32) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `fk_sq_user_role_1` (`roleId`),
  CONSTRAINT `fk_sq_user_role` FOREIGN KEY (`userId`) REFERENCES `sq_user` (`id`),
  CONSTRAINT `fk_sq_user_role_1` FOREIGN KEY (`roleId`) REFERENCES `sq_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `sq_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `sq_user_role` VALUES ('0', '00'), ('152032032124707', '01'), ('152032035802241', '02'), ('152032037461347', '03'), ('152032038491448', '04'), ('152032039532188', '05');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
