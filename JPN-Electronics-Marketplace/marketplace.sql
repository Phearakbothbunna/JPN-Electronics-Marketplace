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
  `contact_info` varchar(255) DEFAULT NULL,
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
INSERT INTO `product` (`product_id`, `product_seller_id`, `product_name`, `product_description`, `product_price`, `contact_info`, `product_img_url`, `product_upload_time`, `product_is_sold`, `product_is_deleted`) VALUES (1, 1, 'iPhone 15', 'Brand new iPhone, bought on official website', 1000, 'phone number: 2533433332','https://www.att.com/scmsassets/global/devices/phones/apple/apple-iphone-15-plus/carousel/pink-1.png', '2024-02-23 01:07:46', 0, 0);
INSERT INTO `product` (`product_id`, `product_seller_id`, `product_name`, `product_description`, `product_price`, `contact_info`, `product_img_url`, `product_upload_time`, `product_is_sold`, `product_is_deleted`) VALUES (2, 1, 'Keychron Keyboard', 'Brand new mechanical keyboard, high quality!', 99, 'Instagram: tech_geek2', 'https://ae01.alicdn.com/kf/Sf5b38be19a2f426dbdefad97e5be7728W.png_640x640.png_.webp', '2024-02-23 01:10:40', 0, 0);
INSERT INTO `product` (`product_id`, `product_seller_id`, `product_name`, `product_description`, `product_price`, `contact_info`, `product_img_url`, `product_upload_time`, `product_is_sold`, `product_is_deleted`) VALUES (3, 2, 'Logitech Mouse', 'Wireless logitech mouse. Just bought 2 months ago, still look new.', 25, 'phone number: 6099998788', 'https://resource.logitech.com/w_800,c_lpad,ar_16:9,q_auto,f_auto,dpr_1.0/d_transparent.gif/content/dam/logitech/en/products/mice/m220/gallery/m220-charcoal-gallery-1.png?v=1', '2024-02-23 01:16:11', 0, 0);
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
