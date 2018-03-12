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

 Date: 03/06/2018 15:32:40 PM
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
INSERT INTO `sq_diningTable` VALUES ('1', '151831372111583', '8', '1'), ('11', null, '4', '0'), ('12', null, '4', '0'), ('13', null, '4', '0'), ('14', null, '4', '0'), ('16', null, '2', '0'), ('17', null, '4', '0'), ('18', null, '4', '0'), ('19', null, '4', '0'), ('2', null, '4', '0'), ('20', null, '4', '0'), ('28', null, '4', '0'), ('29', null, '4', '0'), ('3', null, '2', '0'), ('30', null, '4', '0'), ('4', null, '2', '0'), ('5', null, '2', '0'), ('6', null, '2', '0'), ('7', null, '2', '0'), ('8', null, '2', '0'), ('9', '151832192254900', '4', '1');
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
INSERT INTO `sq_dishes` VALUES ('1', '1', '10', '8', '1', '凉菜1', '/images/test1.jpg'), ('2', '1', '10', '10', '0', '凉菜2', '/images/test1.jpg'), ('3', '1', '10', '8', '1', '凉菜3', '/images/test1.jpg'), ('4', '1', '10', '8', '1', '凉菜4', '/images/test1.jpg'), ('5', '1', '10', '8', '1', '凉菜5', '/images/test1.jpg'), ('6', '1', '10', '8', '1', '凉菜6', '/images/test1.jpg');
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
INSERT INTO `sq_dishesType` VALUES ('1', '凉菜', '1', '1'), ('2', '酒水', '1', '4'), ('3', '川菜', '1', '2'), ('4', '徽菜', '1', '3');
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
INSERT INTO `sq_menu` VALUES ('1', '基础功能', null, null, '0'), ('10', '充值管理', '2', null, '4'), ('11', '充值记录查询', '10', null, '0'), ('12', '用户充值', '10', null, '0'), ('13', '财务管理', '2', '/test/xxxx', '5'), ('14', '查看总收入', '13', null, '0'), ('15', '收入明细查询', '13', null, '0'), ('16', '购物车', '1', '/shoppingCar/getUI', '1'), ('17', '用户管理', '8', '/page/admin/userAdmin', '0'), ('18', '角色管理', '8', '/page/admin/roleAdmin', '1'), ('19', '模块管理', '8', '/page/admin/menuAdmin', '2'), ('2', '系统管理', null, null, '0'), ('3', '点餐', '1', '/page/baseFunction/Order', '0'), ('4', '上菜', '1', null, '3'), ('5', '配餐', '1', null, '4'), ('6', '收银', '1', null, '5'), ('7', '菜单管理', '2', '/page/admin/dishesAdmin', '0'), ('8', '权限管理', '2', null, '3'), ('9', '桌位管理', '2', '/page/admin/diningTableAdmin', '1');
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
INSERT INTO `sq_role` VALUES ('00', '超级管理员', '0'), ('01', '用户', '0'), ('02', '服务员', '1'), ('03', '厨师', '2'), ('04', '收银员', '3'), ('05', '管理员', '4');
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
INSERT INTO `sq_role_menu` VALUES ('00', '1'), ('00', '10'), ('05', '10'), ('00', '11'), ('05', '11'), ('00', '12'), ('05', '12'), ('00', '13'), ('05', '13'), ('00', '14'), ('05', '14'), ('00', '15'), ('05', '15'), ('00', '16'), ('01', '16'), ('00', '17'), ('05', '17'), ('00', '18'), ('05', '18'), ('00', '19'), ('05', '19'), ('00', '2'), ('05', '2'), ('00', '3'), ('01', '3'), ('00', '4'), ('02', '4'), ('00', '5'), ('03', '5'), ('00', '6'), ('04', '6'), ('00', '7'), ('05', '7'), ('00', '8'), ('05', '8'), ('00', '9'), ('05', '9');
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
  `totalPirce` int(255) DEFAULT NULL COMMENT '总价',
  `diningTableId` varchar(32) DEFAULT NULL COMMENT '餐桌 id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `weiyi` (`diningTableId`) USING HASH,
  KEY `fk_sq_shoppingCar` (`userid`),
  CONSTRAINT `fk_sq_shoppingCar` FOREIGN KEY (`userid`) REFERENCES `sq_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `status` varchar(255) DEFAULT '0' COMMENT '0未准备 1准备中 2已上菜',
  PRIMARY KEY (`id`),
  KEY `fk_sq_shoppingCarDetail` (`shoppingCarId`),
  KEY `fk_sq_shoppingCarDetail_1` (`shopId`),
  CONSTRAINT `fk_sq_shoppingCarDetail` FOREIGN KEY (`shoppingCarId`) REFERENCES `sq_shoppingCar` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sq_shoppingCarDetail_1` FOREIGN KEY (`shopId`) REFERENCES `sq_dishes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
