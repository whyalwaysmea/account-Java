/*
SQLyog Ultimate v11.3 (32 bit)
MySQL - 5.7.20-log : Database - account
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`account` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `account`;

/*Table structure for table `account_book_parters` */

DROP TABLE IF EXISTS `account_book_parters`;

CREATE TABLE `account_book_parters` (
  `book_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账本id',
  `wechat_openid` varchar(36) NOT NULL COMMENT '微信用户openid',
  PRIMARY KEY (`book_id`,`wechat_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账本参与者';

/*Table structure for table `account_books` */

DROP TABLE IF EXISTS `account_books`;

CREATE TABLE `account_books` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账本id',
  `name` varchar(36) NOT NULL COMMENT '账本名称',
  `cover_img` varchar(255) DEFAULT NULL COMMENT '账本封面url',
  `owner_id` varchar(36) NOT NULL COMMENT '账本拥有者',
  `creator_id` varchar(36) NOT NULL COMMENT '账本创建者',
  `default_book` varchar(4) DEFAULT NULL COMMENT '是否是默认账本',
  `budgetary_amount` int(11) DEFAULT '0' COMMENT '预算金额（分）',
  `surplus_budgetary_amount` int(11) DEFAULT '0' COMMENT '剩余预算金额（分）',
  `book_type` tinyint(4) NOT NULL COMMENT '账本类型  1：个人账本 2：多人账本',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_account_time` datetime DEFAULT NULL COMMENT '最后记账时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否被删除， 1被删除',
  PRIMARY KEY (`id`),
  KEY `idx_delete` (`is_delete`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账本';

/*Table structure for table `account_record` */

DROP TABLE IF EXISTS `account_record`;

CREATE TABLE `account_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账本记录id',
  `book_id` bigint(20) NOT NULL COMMENT '账本id',
  `amount` int(11) NOT NULL COMMENT '金额',
  `record_type` tinyint(4) NOT NULL COMMENT '收入(1)or支出(2)',
  `main_type` bigint(20) NOT NULL COMMENT '主要分类',
  `secondary_type` bigint(20) DEFAULT NULL COMMENT '次要分类',
  `pay_income_way` bigint(20) DEFAULT NULL COMMENT '收支途径',
  `creator_id` varchar(36) NOT NULL COMMENT '创建者',
  `remark` text COMMENT '备注',
  `record_time` datetime DEFAULT NULL COMMENT '记录关联日期',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_account_time` datetime DEFAULT NULL COMMENT '最后记账时间',
  PRIMARY KEY (`id`),
  KEY `idx_creator_id` (`creator_id`),
  KEY `idx_record_type` (`record_type`),
  KEY `idx_book_id` (`book_id`),
  KEY `idx_record_time` (`record_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账本记录了';

/*Table structure for table `account_record_parters` */

DROP TABLE IF EXISTS `account_record_parters`;

CREATE TABLE `account_record_parters` (
  `book_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账本记录id',
  `wechat_openid` varchar(36) NOT NULL COMMENT '微信用户openid',
  PRIMARY KEY (`book_id`,`wechat_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账本参与者';

/*Table structure for table `budgetary` */

DROP TABLE IF EXISTS `budgetary`;

CREATE TABLE `budgetary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account_book_id` bigint(20) NOT NULL COMMENT '账本id',
  `expenditure_type_id` bigint(20) NOT NULL COMMENT '分类id',
  `total_amount` int(11) DEFAULT '0' COMMENT '总预算金额（分）',
  `surplus_amount` int(11) DEFAULT '0' COMMENT '剩余预算金额（分）',
  PRIMARY KEY (`id`),
  KEY `idx_account_book_id` (`account_book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预算详情';

/*Table structure for table `expenditure_type` */

DROP TABLE IF EXISTS `expenditure_type`;

CREATE TABLE `expenditure_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `pid` bigint(20) NOT NULL COMMENT '分类父id',
  `name` varchar(36) NOT NULL COMMENT '分类名称',
  `creator_id` varchar(36) DEFAULT '-1' COMMENT '分类创建者',
  `icon_url` varchar(255) DEFAULT NULL COMMENT '分类icon url',
  `order_id` int(20) DEFAULT NULL COMMENT '排序id',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_creator_id` (`creator_id`),
  KEY `idx_pid` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支出分类';

/*Table structure for table `income_type` */

DROP TABLE IF EXISTS `income_type`;

CREATE TABLE `income_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `pid` bigint(20) NOT NULL COMMENT '分类父id',
  `name` varchar(36) NOT NULL COMMENT '分类名称',
  `creator_id` varchar(36) DEFAULT '-1' COMMENT '分类创建者',
  `icon_url` varchar(255) DEFAULT NULL COMMENT '分类icon url',
  `order_id` int(20) DEFAULT NULL COMMENT '序号',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_creator_id` (`creator_id`),
  KEY `idx_pid` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收入分类';

/*Table structure for table `pay_income_ways` */

DROP TABLE IF EXISTS `pay_income_ways`;

CREATE TABLE `pay_income_ways` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '方式id',
  `name` varchar(36) NOT NULL COMMENT '收支方式名称',
  `creator_id` varchar(36) DEFAULT '-1' COMMENT '收支方式创建者',
  `icon_url` varchar(255) DEFAULT NULL COMMENT 'icon的url',
  `order_id` int(11) DEFAULT NULL COMMENT '排序id',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_creator_id` (`creator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收支方式';

/*Table structure for table `periodic_account` */

DROP TABLE IF EXISTS `periodic_account`;

CREATE TABLE `periodic_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `book_id` bigint(20) NOT NULL COMMENT '账本id',
  `amount` int(11) NOT NULL COMMENT '金额',
  `record_type` tinyint(4) NOT NULL COMMENT '收入(1)or支出(2)',
  `main_type` bigint(20) NOT NULL COMMENT '主要分类',
  `secondary_type` bigint(20) DEFAULT NULL COMMENT '次要分类',
  `pay_income_way` bigint(20) DEFAULT NULL COMMENT '收支途径',
  `peridoic_type` tinyint(4) NOT NULL COMMENT '周期时间 0：每天 1：每周 2：每月 3：每季度',
  `peridoic_date` tinyint(4) NOT NULL COMMENT '周期点，具体几号或者星期几',
  `creator_id` varchar(36) NOT NULL COMMENT '创建者',
  `remark` text COMMENT '备注',
  `record_time` datetime DEFAULT NULL COMMENT '自动记账时间',
  `next_record_time` datetime DEFAULT NULL COMMENT '下次记账时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_creator_id` (`creator_id`),
  KEY `idx_book_id` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='周期帐';

/*Table structure for table `periodic_account_parter` */

DROP TABLE IF EXISTS `periodic_account_parter`;

CREATE TABLE `periodic_account_parter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `periodic_account_id` bigint(20) NOT NULL COMMENT '周期帐id',
  `user_id` varchar(36) DEFAULT NULL COMMENT '微信openid',
  PRIMARY KEY (`id`),
  KEY `idx_periodic_account_id` (`periodic_account_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='周期帐参与者';

/*Table structure for table `wechat_user` */

DROP TABLE IF EXISTS `wechat_user`;

CREATE TABLE `wechat_user` (
  `wechat_openid` varchar(36) NOT NULL COMMENT '微信openid',
  `nick_name` varchar(36) DEFAULT NULL COMMENT '用户昵称',
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `gender` tinyint(4) DEFAULT NULL COMMENT '用户性别,值为1时是男性，值为2时是女性，值为0时是未知',
  `city` varchar(36) DEFAULT NULL COMMENT '用户所在城市',
  `province` varchar(36) DEFAULT NULL COMMENT '用户所在省份',
  `country` varchar(36) DEFAULT NULL COMMENT '用户所在国家',
  `continuity_account_days` int(11) DEFAULT NULL COMMENT '连续记账天数',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_login_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
  `last_account_time` datetime DEFAULT NULL COMMENT '最后记账时间',
  PRIMARY KEY (`wechat_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信用户信息表';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
