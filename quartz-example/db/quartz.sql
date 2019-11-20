/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : quartz-demo

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2019-11-19 23:03:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for task_model
-- ----------------------------
DROP TABLE IF EXISTS `task_model`;
CREATE TABLE `task_model` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `corn` varchar(255) DEFAULT NULL,
  `job_class_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
