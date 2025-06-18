/*
SQLyog Ultimate v11.3 (64 bit)
MySQL - 5.7.32-log : Database - tupiansucai
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tupiansucai` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `tupiansucai`;

/*Table structure for table `beijing` */

DROP TABLE IF EXISTS `beijing`;

CREATE TABLE `beijing` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `yonghu_id` int(11) DEFAULT NULL COMMENT '用户',
  `beijing_name` varchar(200) DEFAULT NULL COMMENT '高清背景名称 Search111 ',
  `beijing_photo` varchar(200) DEFAULT NULL COMMENT '高清背景照片',
  `beijing_types` int(11) DEFAULT NULL COMMENT '高清背景分类 Search111 ',
  `beijing_geshi_types` int(11) DEFAULT NULL COMMENT '高清背景格式 Search111 ',
  `beijing_banshi_types` int(11) DEFAULT NULL COMMENT '板式 Search111 ',
  `beijing_file` varchar(200) DEFAULT NULL COMMENT '压缩包',
  `shangxia_types` int(11) DEFAULT NULL COMMENT '是否上架 ',
  `beijing_delete` int(11) DEFAULT NULL COMMENT '逻辑删除',
  `beijing_content` text COMMENT '高清背景介绍',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间  show1 show2 photoShow',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='高清背景';

/*Data for the table `beijing` */

insert  into `beijing`(`id`,`yonghu_id`,`beijing_name`,`beijing_photo`,`beijing_types`,`beijing_geshi_types`,`beijing_banshi_types`,`beijing_file`,`shangxia_types`,`beijing_delete`,`beijing_content`,`create_time`) values (4,3,'背景图片_油','http://localhost:8080/tupiansucai/upload/1638155702691.jpg',1,2,1,'http://localhost:8080/tupiansucai/upload/1638155773707.rar',1,1,'<p>油的详情</p>','2021-11-29 11:16:23'),(5,3,'高清背景_鱼','http://localhost:8080/tupiansucai/upload/1638156326903.jpg',4,3,1,'http://localhost:8080/tupiansucai/upload/1638156346518.rar',1,1,'<p>鱼的介绍</p>','2021-11-29 11:25:55'),(6,3,'高清背景图片_五花肉','http://localhost:8080/tupiansucai/upload/1638156383308.jpg',9,2,2,'http://localhost:8080/tupiansucai/upload/1638156410923.rar',1,1,'<p>五花肉的详细介绍</p>','2021-11-29 10:40:31'),(7,3,'高清背景_米','http://localhost:8080/tupiansucai/upload/1638169694604.jpg',6,2,3,'http://localhost:8080/tupiansucai/upload/1638169723154.rar',1,1,'<p>高清背景_米的详细介绍</p>','2021-11-29 15:08:59');

/*Table structure for table `beijing_collection` */

DROP TABLE IF EXISTS `beijing_collection`;

CREATE TABLE `beijing_collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `beijing_id` int(11) DEFAULT NULL COMMENT '高清背景',
  `yonghu_id` int(11) DEFAULT NULL COMMENT '用户',
  `insert_time` timestamp NULL DEFAULT NULL COMMENT '收藏时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间 show3 photoShow',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='高清背景收藏';

/*Data for the table `beijing_collection` */

insert  into `beijing_collection`(`id`,`beijing_id`,`yonghu_id`,`insert_time`,`create_time`) values (2,5,3,'2021-11-29 15:07:29','2021-11-29 15:07:29'),(3,7,3,'2021-11-29 15:12:06','2021-11-29 15:12:06');

/*Table structure for table `beijing_xiazaijilu` */

DROP TABLE IF EXISTS `beijing_xiazaijilu`;

CREATE TABLE `beijing_xiazaijilu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `beijing_id` int(11) DEFAULT NULL COMMENT '高清背景',
  `yonghu_id` int(11) DEFAULT NULL COMMENT '用户',
  `insert_time` date DEFAULT NULL COMMENT '下载时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='高清背景下载记录';

/*Data for the table `beijing_xiazaijilu` */

insert  into `beijing_xiazaijilu`(`id`,`beijing_id`,`yonghu_id`,`insert_time`,`create_time`) values (2,5,3,'2021-11-29','2021-11-29 15:03:56'),(3,6,3,'2021-11-29','2021-11-29 15:04:34');

/*Table structure for table `config` */

DROP TABLE IF EXISTS `config`;

CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '配置参数名称',
  `value` varchar(100) DEFAULT NULL COMMENT '配置参数值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='配置文件';

/*Data for the table `config` */

insert  into `config`(`id`,`name`,`value`) values (1,'轮播图1','http://localhost:8080/tupiansucai/upload/1638152641745.jpg'),(2,'轮播图2','http://localhost:8080/tupiansucai/upload/1638152656514.png'),(3,'会员购买','http://localhost:8080/tupiansucai/upload/1638152667710.png');

/*Table structure for table `dictionary` */

DROP TABLE IF EXISTS `dictionary`;

CREATE TABLE `dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dic_code` varchar(200) DEFAULT NULL COMMENT '字段',
  `dic_name` varchar(200) DEFAULT NULL COMMENT '字段名',
  `code_index` int(11) DEFAULT NULL COMMENT '编码',
  `index_name` varchar(200) DEFAULT NULL COMMENT '编码名字  Search111 ',
  `super_id` int(11) DEFAULT NULL COMMENT '父字段id',
  `beizhu` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='字典';

/*Data for the table `dictionary` */

insert  into `dictionary`(`id`,`dic_code`,`dic_name`,`code_index`,`index_name`,`super_id`,`beizhu`,`create_time`) values (1,'single_seach_types','单页数据类型',1,'网站介绍',NULL,NULL,'2021-11-29 10:11:11'),(2,'single_seach_types','单页数据类型',2,'联系我们',NULL,NULL,'2021-11-29 10:11:11'),(3,'sex_types','性别类型名称',1,'男',NULL,NULL,'2021-11-29 10:11:11'),(4,'sex_types','性别类型名称',2,'女',NULL,NULL,'2021-11-29 10:11:11'),(5,'shangxia_types','上下架名称',1,'上架',NULL,NULL,'2021-11-29 10:11:12'),(6,'shangxia_types','上下架名称',2,'下架',NULL,NULL,'2021-11-29 10:11:12'),(7,'yuansu_types','元素分类名称',1,'漂浮元素',NULL,NULL,'2021-11-29 10:11:12'),(8,'yuansu_types','元素分类名称',2,'装饰元素',NULL,NULL,'2021-11-29 10:11:12'),(9,'yuansu_types','元素分类名称',3,'节日元素',NULL,NULL,'2021-11-29 10:11:12'),(10,'yuansu_types','元素分类名称',4,'手绘/卡通',NULL,NULL,'2021-11-29 10:11:12'),(11,'yuansu_types','元素分类名称',5,'字体元素',NULL,NULL,'2021-11-29 10:11:12'),(12,'yuansu_types','元素分类名称',6,'标签元素',NULL,NULL,'2021-11-29 10:11:12'),(13,'yuansu_types','元素分类名称',7,'背景元素',NULL,NULL,'2021-11-29 10:11:12'),(14,'yuansu_types','元素分类名称',8,'图标元素',NULL,NULL,'2021-11-29 10:11:12'),(15,'yuansu_types','元素分类名称',9,'动植物元素',NULL,NULL,'2021-11-29 10:11:12'),(16,'yuansu_types','元素分类名称',10,'其他元素',NULL,NULL,'2021-11-29 10:11:12'),(17,'yuansu_geshi_types','元素格式名称',1,'PSD',NULL,NULL,'2021-11-29 10:11:12'),(18,'yuansu_geshi_types','元素格式名称',2,'AI',NULL,NULL,'2021-11-29 10:11:12'),(19,'yuansu_geshi_types','元素格式名称',3,'CDR',NULL,NULL,'2021-11-29 10:11:12'),(20,'yuansu_geshi_types','元素格式名称',4,'EPS',NULL,NULL,'2021-11-29 10:11:12'),(23,'beijing_types','高清背景分类名称',1,'扁平/简约',NULL,NULL,'2021-11-29 10:11:13'),(24,'beijing_types','高清背景分类名称',2,'商务/科技',NULL,NULL,'2021-11-29 10:11:13'),(25,'beijing_types','高清背景分类名称',3,'文艺/清新',NULL,NULL,'2021-11-29 10:11:13'),(26,'beijing_types','高清背景分类名称',4,'卡通/手绘',NULL,NULL,'2021-11-29 10:11:13'),(27,'beijing_types','高清背景分类名称',5,'质感/纹理',NULL,NULL,'2021-11-29 10:11:13'),(28,'beijing_types','高清背景分类名称',6,'摄影/风景',NULL,NULL,'2021-11-29 10:11:13'),(29,'beijing_types','高清背景分类名称',7,'电商/狂欢',NULL,NULL,'2021-11-29 10:11:13'),(30,'beijing_types','高清背景分类名称',8,'复古/中国风',NULL,NULL,'2021-11-29 10:11:13'),(31,'beijing_types','高清背景分类名称',9,'另类/其他',NULL,NULL,'2021-11-29 10:11:13'),(32,'beijing_geshi_types','高清背景格式名称',1,'PSD',NULL,NULL,'2021-11-29 10:11:13'),(33,'beijing_geshi_types','高清背景格式名称',2,'AI',NULL,NULL,'2021-11-29 10:11:13'),(34,'beijing_geshi_types','高清背景格式名称',3,'CDR',NULL,NULL,'2021-11-29 10:11:13'),(35,'beijing_banshi_types','板式名称',1,'竖图',NULL,NULL,'2021-11-29 10:11:13'),(36,'beijing_banshi_types','板式名称',2,'横图',NULL,NULL,'2021-11-29 10:11:13'),(37,'beijing_banshi_types','板式名称',3,'方图',NULL,NULL,'2021-11-29 10:11:13'),(38,'gonggao_types','公告类型名称',1,'公告类型1',NULL,NULL,'2021-11-29 10:11:13'),(39,'gonggao_types','公告类型名称',2,'公告类型2',NULL,NULL,'2021-11-29 10:11:13'),(40,'huiyuan_types','是否是会员',1,'普通用户',NULL,NULL,'2021-11-29 10:36:02'),(41,'huiyuan_types','是否是会员',2,'会员',NULL,NULL,'2021-11-29 10:36:02');

/*Table structure for table `gonggao` */

DROP TABLE IF EXISTS `gonggao`;

CREATE TABLE `gonggao` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `gonggao_name` varchar(200) DEFAULT NULL COMMENT '公告名称 Search111  ',
  `gonggao_photo` varchar(200) DEFAULT NULL COMMENT '公告图片 ',
  `gonggao_types` int(11) NOT NULL COMMENT '公告类型 ',
  `insert_time` timestamp NULL DEFAULT NULL COMMENT '公告发布时间 ',
  `gonggao_content` text COMMENT '公告详情 ',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间 show1 show2 nameShow',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='公告信息';

/*Data for the table `gonggao` */

insert  into `gonggao`(`id`,`gonggao_name`,`gonggao_photo`,`gonggao_types`,`insert_time`,`gonggao_content`,`create_time`) values (1,'公告名称1','http://localhost:8080/tupiansucai/upload/1638152355517.jpg',1,'2021-11-29 10:11:13','<p>公告详情1111</p>','2021-11-29 10:11:13');

/*Table structure for table `single_seach` */

DROP TABLE IF EXISTS `single_seach`;

CREATE TABLE `single_seach` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `single_seach_name` varchar(200) DEFAULT NULL COMMENT '名字  Search111 ',
  `single_seach_types` int(11) DEFAULT NULL COMMENT '数据类型',
  `single_seach_content` text COMMENT '内容',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间 show2 nameShow',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='单页数据';

/*Data for the table `single_seach` */

insert  into `single_seach`(`id`,`single_seach_name`,`single_seach_types`,`single_seach_content`,`create_time`) values (1,'网站介绍',1,'<h3>由来：</h3><p>记得14-15年的时候，有很多做设计的小伙伴跟我说，做设计抠图真的是耗费时间，浪费精力，做一张图需要抠几张图甚至十几张的图，大大的提升了设计师的设计时间和降低了工作效率，在设计行业摸爬滚打了几年的我和我的小伙伴就萌生了一个想法--做一个已经抠好图的设计元素素材网站，提供给广大设计师来用，然后16年就有了觅元素的诞生，觅，本义：寻找，到处寻找之意，元素意指设计作品所需要的设计元素，两者合二为一即为觅元素，这也就是觅元素名称及网站的由来。</p><h3>简介：</h3><p>觅元素是一家专注做高清png免抠设计元素以及背景免费下载的设计素材网站,提供位图、透明背景素材、高清png、图片素材、漂浮元素、装饰元素、标签元素、字体元素、图标元素等免抠设计元素的免费下载.</p><p>网站链接：http://www.51yuansu.com/</p><p>网站成立时间：2016年3月4日</p><p>网站定位：免扣设计元素免费下载网站</p><p>网站归属：杭州启觅网络科技有限公司</p><p>ICP备案号：浙ICP备19017167号-1</p><p>觅元素经过两年多的良好发展，现在已经是一个拥有500万设计元素和百万以上设计背景的素材网站，本着满足用户需求提供高质量的PNG设计元素之外，还致力于降低广大设计师获取设计素材成本，让国内2000万设计师在做设计的时候可以省时、省力的同时还可以降低素材获取成本，“一种会员、全站特权”是觅元素网站的特色，只需要一种VIP就可以下载网站任意素材。</p><h3>目标：</h3><p>致力于提供高质量免抠png设计元素，成为设计行业里一家“真正满足用户需求、满足用户真正需求”的设计网站</p>','2021-11-29 10:31:34'),(3,'联系我们',2,'<h3>联系我们：</h3><p>如果你有更好的建议和意见，友情链接，合作，请通过以下方式联系我们</p><p>QQ&nbsp;:&nbsp;1563712686 (周一至周五 9:30—18:00)</p><p>邮箱&nbsp;：&nbsp;1563712686@qq.com</p>','2021-11-29 15:42:48');

/*Table structure for table `token` */

DROP TABLE IF EXISTS `token`;

CREATE TABLE `token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `tablename` varchar(100) DEFAULT NULL COMMENT '表名',
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `token` varchar(200) NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='token表';

/*Data for the table `token` */

insert  into `token`(`id`,`userid`,`username`,`tablename`,`role`,`token`,`addtime`,`expiratedtime`) values (1,6,'admin','users','管理员','v4td2sbi2cg8330rna4xm53wucpxje47','2021-11-29 10:08:16','2021-11-29 16:42:12'),(2,3,'a1','yonghu','用户','05vfk7s8p7q4rqj3q3eklwbptg4zf17a','2021-11-29 10:40:49','2021-11-29 17:03:17');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `role` varchar(100) DEFAULT '管理员' COMMENT '角色',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='管理员表';

/*Data for the table `users` */

insert  into `users`(`id`,`username`,`password`,`role`,`addtime`) values (6,'admin','123456','管理员','2021-04-27 14:51:13');

/*Table structure for table `yonghu` */

DROP TABLE IF EXISTS `yonghu`;

CREATE TABLE `yonghu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(200) DEFAULT NULL COMMENT '账户',
  `password` varchar(200) DEFAULT NULL COMMENT '密码',
  `yonghu_name` varchar(200) DEFAULT NULL COMMENT '用户姓名 Search111 ',
  `yonghu_phone` varchar(200) DEFAULT NULL COMMENT '用户手机号 Search111 ',
  `yonghu_id_number` varchar(200) DEFAULT NULL COMMENT '用户身份证号 Search111 ',
  `yonghu_photo` varchar(200) DEFAULT NULL COMMENT '用户照片',
  `sex_types` int(11) DEFAULT NULL COMMENT '性别',
  `huiyuan_types` int(11) DEFAULT NULL COMMENT '是否是会员',
  `yonghu_email` varchar(200) DEFAULT NULL COMMENT '电子邮箱',
  `new_money` decimal(10,2) DEFAULT NULL COMMENT '余额 ',
  `yonghu_delete` int(11) DEFAULT NULL COMMENT '逻辑删除',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户';

/*Data for the table `yonghu` */

insert  into `yonghu`(`id`,`username`,`password`,`yonghu_name`,`yonghu_phone`,`yonghu_id_number`,`yonghu_photo`,`sex_types`,`huiyuan_types`,`yonghu_email`,`new_money`,`yonghu_delete`,`create_time`) values (2,'管理员专属上传账户','123456','管理员上传','17703780000','410224199610230000','http://localhost:8080/tupiansucai/upload/1638153619602.jpg',1,1,'88@qq.com','0.00',1,'2021-11-29 10:38:07'),(3,'a1','123456','张1','17703786901','410224199610232001','http://localhost:8080/tupiansucai/upload/1638153619602.jpg',2,1,'0@qq.com','0.00',1,'2021-11-29 10:40:31');

/*Table structure for table `yuansu` */

DROP TABLE IF EXISTS `yuansu`;

CREATE TABLE `yuansu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `yonghu_id` int(11) DEFAULT NULL COMMENT '用户',
  `yuansu_name` varchar(200) DEFAULT NULL COMMENT '免扣元素名称 Search111 ',
  `yuansu_photo` varchar(200) DEFAULT NULL COMMENT '免扣元素照片',
  `yuansu_types` int(11) DEFAULT NULL COMMENT '元素分类 Search111 ',
  `yuansu_geshi_types` int(11) DEFAULT NULL COMMENT '元素格式 Search111 ',
  `yuansu_file` varchar(200) DEFAULT NULL COMMENT '压缩包',
  `shangxia_types` int(11) DEFAULT NULL COMMENT '是否上架 ',
  `yuansu_delete` int(11) DEFAULT NULL COMMENT '逻辑删除',
  `yuansu_content` text COMMENT '免扣元素介绍',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间  show1 show2 photoShow',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='免扣元素';

/*Data for the table `yuansu` */

insert  into `yuansu`(`id`,`yonghu_id`,`yuansu_name`,`yuansu_photo`,`yuansu_types`,`yuansu_geshi_types`,`yuansu_file`,`shangxia_types`,`yuansu_delete`,`yuansu_content`,`create_time`) values (4,3,'免扣元素_橙子','http://localhost:8080/tupiansucai/upload/1638156519426.jpg',9,1,'http://localhost:8080/tupiansucai/upload/1638156546803.rar',1,1,'<p>橙子的详情</p>','2021-11-29 11:29:16'),(5,3,'免扣元素_苹果','http://localhost:8080/tupiansucai/upload/1638163965425.jpg',9,3,'http://localhost:8080/tupiansucai/upload/1638163991821.rar',1,1,'<p>免扣元素_苹果的介绍详情</p>','2021-11-29 13:33:25');

/*Table structure for table `yuansu_collection` */

DROP TABLE IF EXISTS `yuansu_collection`;

CREATE TABLE `yuansu_collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `yuansu_id` int(11) DEFAULT NULL COMMENT '免扣元素',
  `yonghu_id` int(11) DEFAULT NULL COMMENT '用户',
  `insert_time` timestamp NULL DEFAULT NULL COMMENT '收藏时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间 show3 photoShow',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='免扣元素收藏';

/*Data for the table `yuansu_collection` */

insert  into `yuansu_collection`(`id`,`yuansu_id`,`yonghu_id`,`insert_time`,`create_time`) values (2,5,3,'2021-11-29 14:44:27','2021-11-29 14:44:27'),(4,4,3,'2021-11-29 14:58:12','2021-11-29 14:58:12');

/*Table structure for table `yuansu_xiazaijilu` */

DROP TABLE IF EXISTS `yuansu_xiazaijilu`;

CREATE TABLE `yuansu_xiazaijilu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `yuansu_id` int(11) DEFAULT NULL COMMENT '免扣元素',
  `yonghu_id` int(11) DEFAULT NULL COMMENT '用户',
  `insert_time` date DEFAULT NULL COMMENT '下载日期',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='免扣元素下载记录';

/*Data for the table `yuansu_xiazaijilu` */

insert  into `yuansu_xiazaijilu`(`id`,`yuansu_id`,`yonghu_id`,`insert_time`,`create_time`) values (2,4,3,'2021-11-29','2021-11-29 14:46:15'),(3,5,3,'2021-11-29','2021-11-29 15:12:24');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
