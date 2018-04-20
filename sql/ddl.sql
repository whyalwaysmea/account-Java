/*
SQLyog 企业版 - MySQL GUI v8.14
MySQL - 5.7.17-log : Database - account
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

/*Data for the table `account_book_parters` */

/*Table structure for table `account_book` */

DROP TABLE IF EXISTS `account_book`;

CREATE TABLE `account_book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账本id',
  `name` varchar(36) NOT NULL COMMENT '账本名称',
  `cover_img` varchar(255) DEFAULT NULL COMMENT '账本封面url',
  `owner_id` varchar(36) NOT NULL COMMENT '账本拥有者',
  `creator_id` varchar(36) NOT NULL COMMENT '账本创建者',
  `default_book` tinyint(4) DEFAULT 0 COMMENT '是否是默认账本, 1是',
  `budgetary_amount` int(11) DEFAULT 0 COMMENT '预算金额（分）',
  `surplus_budgetary_amount` int(11) DEFAULT '0' COMMENT '剩余预算金额（分）',
  `multiple_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '账本类型  0：个人账本 1：多人账本',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_account_time` datetime DEFAULT NULL COMMENT '最后记账时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否被删除， 1被删除',
  `faker_user` tinyint(1) DEFAULT '0' COMMENT '是否是虚假用户，0：否 1是',
  PRIMARY KEY (`id`),
  KEY `idx_delete` (`is_delete`),
  KEY `idx_faker_user` (`faker_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账本';

/*Data for the table `account_book` */

/*Table structure for table `account_record` */

DROP TABLE IF EXISTS `account_record`;

CREATE TABLE `account_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账本记录id',
  `book_id` bigint(20) NOT NULL COMMENT '账本id',
  `amount` int(11) NOT NULL COMMENT '金额',
  `record_type` tinyint(4) NOT NULL COMMENT '收入(1)or支出(2)',
  `main_type` varchar(255) NOT NULL COMMENT '主要分类',
  `secondary_type` varchar(255) DEFAULT NULL COMMENT '次要分类',
  `pay_income_way` varchar(255) DEFAULT NULL COMMENT '收支途径',
  `creator_id` varchar(36) NOT NULL COMMENT '创建者',
  `remark` text COMMENT '备注',
  `record_time` datetime DEFAULT NULL COMMENT '记录关联日期',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_creator_id` (`creator_id`),
  KEY `idx_record_type` (`record_type`),
  KEY `idx_book_id` (`book_id`),
  KEY `idx_record_time` (`record_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账本记录了';

/*Data for the table `account_record` */

/*Table structure for table `account_record_parters` */

DROP TABLE IF EXISTS `account_record_parters`;

CREATE TABLE `account_record_parters` (
  `book_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账本记录id',
  `wechat_openid` varchar(36) NOT NULL COMMENT '微信用户openid',
  PRIMARY KEY (`book_id`,`wechat_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账本参与者';

/*Data for the table `account_record_parters` */

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

/*Data for the table `budgetary` */

/*Table structure for table `expenditure_type` */

DROP TABLE IF EXISTS `expenditure_type`;

CREATE TABLE `expenditure_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `pid` bigint(20) DEFAULT NULL COMMENT '分类父id',
  `name` varchar(36) NOT NULL COMMENT '分类名称',
  `creator_id` varchar(36) NOT NULL COMMENT '分类创建者',
  `icon_url` varchar(255) DEFAULT NULL COMMENT '分类icon url',
  `order_id` bigint(20) DEFAULT NULL COMMENT '排序id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_creator_id` (`creator_id`),
  KEY `idx_pid` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8 COMMENT='支出分类';

/*Data for the table `expenditure_type` */

insert  into `expenditure_type`(`id`,`pid`,`name`,`creator_id`,`icon_url`,`order_id`,`create_time`) values (1,NULL,'普通','-1',NULL,90,'2018-04-10 16:16:10'),(2,NULL,'餐饮','-1',NULL,80,'2018-04-10 16:16:10'),(3,NULL,'服饰','-1',NULL,70,'2018-04-10 16:16:10'),(4,NULL,'购物','-1',NULL,60,'2018-04-10 16:16:10'),(5,NULL,'交通','-1',NULL,50,'2018-04-10 16:16:10'),(6,NULL,'娱乐','-1',NULL,40,'2018-04-10 16:16:10'),(7,NULL,'居家','-1',NULL,30,'2018-04-10 16:16:10'),(8,NULL,'其他','-1',NULL,20,'2018-04-10 16:16:10'),(9,1,'早餐','-1',NULL,110,'2018-04-10 16:18:05'),(10,1,'午餐','-1',NULL,100,'2018-04-10 16:18:05'),(11,1,'晚餐','-1',NULL,90,'2018-04-10 16:18:05'),(12,1,'夜宵','-1',NULL,80,'2018-04-10 16:18:05'),(13,1,'油盐酱醋','-1',NULL,70,'2018-04-10 16:18:05'),(14,1,'买菜','-1',NULL,60,'2018-04-10 16:18:05'),(15,1,'烟酒茶','-1',NULL,50,'2018-04-10 16:18:05'),(16,1,'水果','-1',NULL,40,'2018-04-10 16:18:05'),(17,1,'零食','-1',NULL,30,'2018-04-10 16:18:05'),(18,1,'饭卡','-1',NULL,20,'2018-04-10 16:18:05'),(19,1,'聚会','-1',NULL,10,'2018-04-10 16:18:05'),(20,2,'衣服','-1',NULL,100,'2018-04-10 16:22:33'),(21,2,'裤子','-1',NULL,90,'2018-04-10 16:22:33'),(22,2,'鞋子','-1',NULL,80,'2018-04-10 16:22:33'),(23,2,'包包','-1',NULL,70,'2018-04-10 16:22:33'),(24,2,'网购','-1',NULL,60,'2018-04-10 16:22:33'),(25,2,'其他','-1',NULL,50,'2018-04-10 16:22:33'),(26,3,'网购剁手','-1',NULL,40,'2018-04-10 16:24:44'),(27,3,'洗护用品','-1',NULL,30,'2018-04-10 16:24:44'),(28,3,'厨房用品','-1',NULL,20,'2018-04-10 16:24:44'),(29,3,'家居日用','-1',NULL,10,'2018-04-10 16:24:44'),(30,3,'家具家电','-1',NULL,100,'2018-04-10 16:24:44'),(31,3,'汽车用品','-1',NULL,90,'2018-04-10 16:24:44'),(32,3,'数码产品','-1',NULL,80,'2018-04-10 16:24:44'),(33,3,'办公用品','-1',NULL,70,'2018-04-10 16:24:44'),(34,3,'运动用品','-1',NULL,100,'2018-04-10 16:24:44'),(35,4,'公交卡','-1',NULL,90,'2018-04-10 16:26:20'),(36,4,'打车','-1',NULL,80,'2018-04-10 16:26:20'),(37,4,'加油','-1',NULL,70,'2018-04-10 16:26:20'),(38,4,'停车','-1',NULL,60,'2018-04-10 16:26:20'),(39,4,'保养','-1',NULL,50,'2018-04-10 16:26:20'),(40,4,'维修','-1',NULL,40,'2018-04-10 16:26:20'),(41,4,'违章罚款','-1',NULL,30,'2018-04-10 16:26:20'),(42,4,'外出','-1',NULL,20,'2018-04-10 16:26:20'),(43,5,'电影','-1',NULL,70,'2018-04-10 16:27:11'),(44,5,'K歌','-1',NULL,60,'2018-04-10 16:27:11'),(45,5,'麻将','-1',NULL,50,'2018-04-10 16:27:11'),(46,5,'聚会','-1',NULL,40,'2018-04-10 16:27:11'),(47,5,'游戏','-1',NULL,30,'2018-04-10 16:27:11'),(48,5,'旅游','-1',NULL,20,'2018-04-10 16:27:11'),(49,6,'房租','-1',NULL,110,'2018-04-10 16:27:53'),(50,6,'话费宽带','-1',NULL,100,'2018-04-10 16:27:53'),(51,6,'水电燃气','-1',NULL,90,'2018-04-10 16:27:53'),(52,6,'物业','-1',NULL,80,'2018-04-10 16:27:53'),(53,6,'家政服务','-1',NULL,70,'2018-04-10 16:27:53');

/*Table structure for table `income_type` */

DROP TABLE IF EXISTS `income_type`;

CREATE TABLE `income_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `pid` bigint(20) DEFAULT NULL COMMENT '分类父id',
  `name` varchar(36) NOT NULL COMMENT '分类名称',
  `creator_id` varchar(36) DEFAULT '-1' COMMENT '分类创建者',
  `icon_url` varchar(255) DEFAULT NULL COMMENT '分类icon url',
  `order_id` bigint(20) DEFAULT NULL COMMENT '序号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_creator_id` (`creator_id`),
  KEY `idx_pid` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='收入分类';

/*Data for the table `income_type` */

insert  into `income_type`(`id`,`pid`,`name`,`creator_id`,`icon_url`,`order_id`,`create_time`) values (1,NULL,'工资','-1',NULL,1,'2018-04-10 16:31:48'),(2,NULL,'生活费','-1',NULL,2,'2018-04-10 16:31:53'),(3,NULL,'兼职','-1',NULL,3,'2018-04-10 16:32:02'),(4,NULL,'其他','-1',NULL,4,'2018-04-10 16:32:12');

/*Table structure for table `pay_income_ways` */

DROP TABLE IF EXISTS `pay_income_ways`;

CREATE TABLE `pay_income_ways` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '方式id',
  `name` varchar(36) NOT NULL COMMENT '收支方式名称',
  `creator_id` varchar(36) DEFAULT '-1' COMMENT '收支方式创建者',
  `icon_url` varchar(255) DEFAULT NULL COMMENT 'icon的url',
  `order_id` int(11) DEFAULT NULL COMMENT '排序id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_creator_id` (`creator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='收支方式';

/*Data for the table `pay_income_ways` */

insert  into `pay_income_ways`(`id`,`name`,`creator_id`,`icon_url`,`order_id`,`create_time`) values (1,'支付宝','-1',NULL,1,'2018-04-10 16:32:30'),(2,'微信','-1',NULL,2,'2018-04-10 16:32:37'),(3,'现金','-1',NULL,3,'2018-04-10 16:32:42'),(4,'信用卡','-1',NULL,4,'2018-04-10 16:32:48');

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

/*Data for the table `periodic_account` */

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

/*Data for the table `periodic_account_parter` */

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

/*Data for the table `wechat_user` */

insert  into `wechat_user`(`wechat_openid`,`nick_name`,`avatar_url`,`gender`,`city`,`province`,`country`,`continuity_account_days`,`create_time`,`last_login_time`,`last_account_time`) values ('hahaha','哈哈哈',NULL,NULL,NULL,NULL,NULL,NULL,'2018-04-10 17:37:16','2018-04-10 17:37:16',NULL),('string','string','string',NULL,'string','string','string',NULL,'2018-04-11 17:48:50','2018-04-12 16:19:53',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
