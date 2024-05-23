# Host: localhost  (Version 5.7.19-log)
# Date: 2023-03-30 23:09:14
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(12) NOT NULL DEFAULT '' COMMENT '密码',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `favorites` varchar(20) DEFAULT NULL COMMENT '爱好',
  `phone` varchar(15) DEFAULT NULL COMMENT '电话',
  `sex` varchar(1) DEFAULT NULL COMMENT '性别',
  `role` varchar(1) DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'lily','9',17,'篮球','13883099870','1','0'),(2,'smith','1',23,'足球,篮球','15783099871','1','1'),(10,'jack','123',19,'足球,篮球','13998099322','1','1');
