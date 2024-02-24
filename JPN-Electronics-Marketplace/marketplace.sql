/*
 Navicat MySQL Data Transfer

 Source Server         : 582
 Source Server Type    : MySQL
 Source Server Version : 80300 (8.3.0)
 Source Host           : localhost:3306
 Source Schema         : marketplace

 Target Server Type    : MySQL
 Target Server Version : 80300 (8.3.0)
 File Encoding         : 65001

 Date: 23/02/2024 07:50:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS marketplace;
USE marketplace;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `product_id` bigint NOT NULL AUTO_INCREMENT,
  `product_seller_id` bigint DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_description` varchar(255) DEFAULT NULL,
  `product_price` int DEFAULT NULL,
  `product_img_url` varchar(255) DEFAULT NULL,
  `product_upload_time` datetime DEFAULT NULL,
  `product_is_sold` int DEFAULT '0',
  `product_is_deleted` int DEFAULT '0',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of product
-- ----------------------------
BEGIN;
INSERT INTO `product` (`product_id`, `product_seller_id`, `product_name`, `product_description`, `product_price`, `product_img_url`, `product_upload_time`, `product_is_sold`, `product_is_deleted`) VALUES (1, 1, 'iPhone 15', 'Brand new iPhone, bought on official website', 1000, 'https://www.att.com/scmsassets/global/devices/phones/apple/apple-iphone-15-plus/carousel/pink-1.png', '2024-02-23 01:07:46', 0, 0);
INSERT INTO `product` (`product_id`, `product_seller_id`, `product_name`, `product_description`, `product_price`, `product_img_url`, `product_upload_time`, `product_is_sold`, `product_is_deleted`) VALUES (2, 1, 'banana', 'Fresh banana', 2, 'https://domf5oio6qrcr.cloudfront.net/medialibrary/6372/202ebeef-6657-44ec-8fff-28352e1f5999.jpg', '2024-02-23 01:10:40', 0, 0);
INSERT INTO `product` (`product_id`, `product_seller_id`, `product_name`, `product_description`, `product_price`, `product_img_url`, `product_upload_time`, `product_is_sold`, `product_is_deleted`) VALUES (3, 2, 'couch', 'Moving sale, good as new', 50, 'https://assets.wfcdn.com/im/91378491/resize-h600-w600%5Ecompr-r85/8140/81409053/Sofas.jpg', '2024-02-23 01:16:11', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `user_email` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_contact_info` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_is_deleted` int DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`user_id`, `user_email`, `user_name`, `user_contact_info`, `password`, `user_is_deleted`) VALUES (1, '1@gmail.com', 'Bob', '1@gmail.com', '123456', 0);
INSERT INTO `user` (`user_id`, `user_email`, `user_name`, `user_contact_info`, `password`, `user_is_deleted`) VALUES (2, '2@gmail.com', 'Alice', 'ins: alice2', '123456', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
