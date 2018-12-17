-- database initialize script

-- create database
CREATE DATABASE seckill;

-- use database
use seckill;

-- create seckill table
CREATE TABLE seckill(
`seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品id',
`name` varchar(120) NOT NULL COMMENT '商品名称',
`number` int NOT NULL COMMENT '库存数量',
`start_time` timestamp NOT NULL COMMENT '秒杀开始时间',
`end_time` timestamp NOT NULL COMMENT '秒杀结束时间',
`create_time` timestamp NOT NULL DEFAULT  CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';


-- initialize the dataset
insert into
  seckill(name, number, start_time, end_time)
values
  ('1000元秒杀iPhone', 100, '2018-12-16 00:00:00', '2018-12-17 00:00:00'),
  ('500元秒杀iPad', 100, '2018-12-16 00:00:00', '2018-12-17 00:00:00'),
  ('300元秒杀iWatch', 100, '2018-12-16 00:00:00', '2018-12-17 00:00:00'),
  ('200元秒杀iPod', 100, '2018-12-16 00:00:00', '2018-12-17 00:00:00');


-- seckill success table
CREATE TABLE success_killed(
`seckill_id` bigint NOT NULL COMMENT '秒杀商品id',
`user_phone` bigint NOT NULL COMMENT '用户手机号',
`state` tinyint NOT NULL DEFAULT -1 COMMENT '状态表示： -1：无效 0：成功 1：已付款',
`create_time` timestamp NOT NULL COMMENT '创建时间',
PRIMARY KEY(seckill_id, user_phone),
key idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';


-- connect database console
mysql -uroot -p