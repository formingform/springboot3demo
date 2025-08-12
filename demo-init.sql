CREATE DATABASE IF NOT EXISTS springboot3demo DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
SET FOREIGN_KEY_CHECKS = 0;
use springboot3demo;

use springboot3demo;
DROP TABLE  IF EXISTS `users`;
CREATE TABLE `users` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name` varchar(42) NOT NULL COMMENT '用户名',
    `email` varchar(128) NOT NULL COMMENT 'email地址',
    `create_time`            timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`            timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `name` (`name`)
);

insert into `users` (`name`, `email`) values('Tom', 'tom@email.com');
insert into `users` (`name`, `email`) values('Jerry', 'jerry@email.com');