/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : vmeet

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 05/04/2024 13:17:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '联系人ID',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID(外键关联用户表的id字段)',
  `contact_user_id` int NULL DEFAULT NULL COMMENT '联系人用户ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `contact_ibfk_1`(`user_id` ASC) USING BTREE,
  INDEX `contact_user_id`(`contact_user_id` ASC) USING BTREE,
  CONSTRAINT `contact_ibfk_1` FOREIGN KEY (`contact_user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '联系人表\r\n' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '聊天消息id',
  `sender_id` int NULL DEFAULT NULL COMMENT '发送者id',
  `receiver_id` int NULL DEFAULT NULL COMMENT '接收者id',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '消息内容',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息类型',
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间',
  `original_filename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原始文件名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 205 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '消息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for moment_comments
-- ----------------------------
DROP TABLE IF EXISTS `moment_comments`;
CREATE TABLE `moment_comments`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `moment_id` int NULL DEFAULT NULL COMMENT '动态id',
  `user_id` int NULL DEFAULT NULL COMMENT '评论用户id',
  `comment` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论内容',
  `com_timestamp` timestamp NULL DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '动态评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for moments
-- ----------------------------
DROP TABLE IF EXISTS `moments`;
CREATE TABLE `moments`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '动态id',
  `user_id` int NULL DEFAULT NULL COMMENT '发布者用户id',
  `moments_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '动态内容',
  `moments_images` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '动态图片url',
  `moments_video` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '动态视频url',
  `moments_timestamp` timestamp NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '动态表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for moments_likes
-- ----------------------------
DROP TABLE IF EXISTS `moments_likes`;
CREATE TABLE `moments_likes`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '点赞id',
  `moment_id` int NULL DEFAULT NULL COMMENT '动态id',
  `user_id` int NULL DEFAULT NULL COMMENT '点赞用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '动态点赞表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '在线状态',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for video_call_record
-- ----------------------------
DROP TABLE IF EXISTS `video_call_record`;
CREATE TABLE `video_call_record`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '视频通话记录id',
  `video_caller_id` int NULL DEFAULT NULL COMMENT '呼叫者id',
  `video_receiver_id` int NULL DEFAULT NULL COMMENT '接收者id',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '通话开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '通话结束时间',
  `duration` int NULL DEFAULT NULL COMMENT '通话时长（单位：秒）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '视频通话记录表' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
