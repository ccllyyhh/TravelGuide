/*
 Navicat Premium Data Transfer

 Source Server         : 1
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : travel

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 29/05/2019 01:35:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(7) NOT NULL AUTO_INCREMENT,
  `note_id` int(7) NOT NULL,
  `user_id` int(7) NOT NULL,
  `content` varchar(1000) COLLATE utf8_bin NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
INSERT INTO `comment` VALUES (11, 1, 32, '哈哈哈哈哈', '2019-05-22 18:26:49');
INSERT INTO `comment` VALUES (12, 1, 32, '哈哈哈哈哈哈哈', '2019-05-22 18:28:42');
COMMIT;

-- ----------------------------
-- Table structure for identify
-- ----------------------------
DROP TABLE IF EXISTS `identify`;
CREATE TABLE `identify` (
  `PHONE_NUM` varchar(32) NOT NULL COMMENT '接受验证码的电话号码',
  `IDENTIFY_CODE` varchar(255) NOT NULL COMMENT '验证码',
  `INT_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据插入的时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for note
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `NOTE_ID` bigint(40) NOT NULL AUTO_INCREMENT COMMENT '日记id',
  `USER_ID` int(11) NOT NULL COMMENT '作者id',
  `TITLE` varchar(32) NOT NULL COMMENT '日记标题',
  `COVER_URL` varchar(512) NOT NULL COMMENT '封面图片',
  `VIEW_NUM` int(11) DEFAULT '0' COMMENT '浏览数',
  `LIKE_NUM` int(11) DEFAULT '0' COMMENT '点赞数',
  `START_DAY` date DEFAULT NULL COMMENT '开始日期',
  `LOCATION` varchar(32) NOT NULL COMMENT '地点',
  `CREATE_DAY` date DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`NOTE_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=522074900046472 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='日记 日记信息表';

-- ----------------------------
-- Records of note
-- ----------------------------
BEGIN;
INSERT INTO `note` VALUES (522063506287206, 32, '【沙巴】阳光热辣辣', '/20190522/e440adbce1fc46969f71e1ccd7a1af88.jpeg', 1, 0, '2019-05-01', '马来西亚,哥打基纳巴鲁', '2019-05-22');
INSERT INTO `note` VALUES (522074900046471, 32, 'test', '/20190522/92ae25df68f64a0c80f1e857daa48169.jpeg', 2, 0, '2019-05-01', 'test', '2019-05-22');
COMMIT;

-- ----------------------------
-- Table structure for noteitem
-- ----------------------------
DROP TABLE IF EXISTS `noteitem`;
CREATE TABLE `noteitem` (
  `NOTE_ITEM_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '日记项id',
  `NOTE_ID` bigint(40) NOT NULL COMMENT '日记id',
  `DAY_NUM` int(11) NOT NULL COMMENT 'index',
  `IMG_URL` varchar(512) DEFAULT NULL COMMENT '图片url',
  `CONTENT` varchar(1024) DEFAULT NULL COMMENT '内容',
  `TIME` varchar(32) NOT NULL COMMENT '时间',
  `LOCATION` varchar(32) NOT NULL COMMENT '地点',
  PRIMARY KEY (`NOTE_ITEM_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='日记项 ';

-- ----------------------------
-- Records of noteitem
-- ----------------------------
BEGIN;
INSERT INTO `noteitem` VALUES (6, 522063506287206, 1, '/20190522/72fcd162b1d246609b6a4f5301f67bcf.jpeg', '凌晨抵达亚庇后，在机场住了一晚，坐了一早的航班飞往斗湖（仙本那） \n每天亚庇——斗湖间有很多亚航的往返航班，单程50分钟', '2019-05-22T10:35:19.000Z', '机场');
INSERT INTO `noteitem` VALUES (7, 522074900046471, 1, '/20190522/170e952c587c4a1080d1b4bad8fce9c9.jpeg', 'test', '2019-05-22T11:49:20.000Z', 'test');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `NAME` varchar(32) NOT NULL COMMENT '用户名',
  `PHONE` varchar(32) NOT NULL COMMENT '手机号',
  `PASSWORD` varchar(32) NOT NULL COMMENT '密码',
  `ROLE` int(1) NOT NULL DEFAULT '0' COMMENT '角色',
  `PHOTO` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '/image/defaultUser.jpeg',
  PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (10, 'admin', '18618393828', '4297f44b13955235245b2497399d7a93', 1, '/image/defaultUser.jpeg');
INSERT INTO `user` VALUES (32, 'pupu', '12312312312', '327bc4e22b649d47c4546a3ec93f376b', 0, '/image/defaultUser.jpeg');
COMMIT;

-- ----------------------------
-- Event structure for myevent
-- ----------------------------
DROP EVENT IF EXISTS `myevent`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` EVENT `myevent` ON SCHEDULE EVERY 1 MINUTE STARTS '2018-11-29 16:01:51' ON COMPLETION PRESERVE ENABLE DO DELETE FROM identify;
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
