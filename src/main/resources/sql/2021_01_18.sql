CREATE TABLE `lz_test_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `type` int(11) DEFAULT '0' COMMENT '0',
  `branch_id` int(11) DEFAULT NULL COMMENT '版本号',
  `real_name` varchar(256) DEFAULT NULL COMMENT '真实名称',
  `mobile` varchar(256) DEFAULT NULL COMMENT '手机号码',
  `username` varchar(256) DEFAULT NULL COMMENT '用户名',
  `task_id` int(11) DEFAULT NULL COMMENT '任务 id',
  `staff_id` int(11) DEFAULT '0' COMMENT '员工 id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COMMENT='项目用户';



INSERT INTO `lz_test_user` (`id`, `is_delete`, `gmt_create`, `gmt_modified`, `type`, `branch_id`, `real_name`, `mobile`, `username`, `task_id`, `staff_id`)
VALUES
	(13, 0, '2021-01-19 11:39:49', '2021-01-19 11:39:49', 0, 1, '金x', '1575xxx', '1xxxx', 1, 311),
	(14, 0, '2021-01-19 11:39:49', '2021-01-19 11:39:49', 0, 1, '李x', '1826xxx', '1xxxxxx', 1, 323),
	(15, 0, '2021-01-19 11:39:49', '2021-01-19 11:39:49', 0, 1, '傅x', '15858xx', '1xxx06579', 1, 368),
	(16, 0, '2021-01-19 11:42:14', '2021-01-19 11:42:14', 0, 1, 'xxx', '1564xx8', 'xxx952078', 1, 296),
	(17, 0, '2021-01-19 11:42:14', '2021-01-19 11:42:14', 0, 1, 'x虹', '13530xx', '1xxx46053', 1, 322),
	(18, 0, '2021-01-19 11:42:14', '2021-01-19 11:42:14', 0, 1, 'x杰', '13325xx', '1xxx12551', 1, 395),
	(19, 0, '2021-01-19 11:55:04', '2021-01-19 11:55:04', 1, 1, 'x婉', '18217xx', '1xxx26772', 1, 350),
	(20, 0, '2021-01-19 11:55:04', '2021-01-19 11:55:04', 1, 1, 'x晶', '18157xx', '1xxx27792', 1, 360),
	(21, 0, '2021-01-19 11:55:04', '2021-01-19 11:55:04', 2, 1, 'x晶', '18157xx', '1xxx27792', 1, 360),
	(22, 0, '2021-01-19 11:55:04', '2021-01-19 11:55:04', 2, 1, 'x一', '15958xx', '1xxx10291', 1, 371),
	(23, 0, '2021-01-19 11:58:12', '2021-01-19 11:58:12', 3, 1, 'x森', '1575xxx', '1xxxx', 1, 311),
	(24, 0, '2021-01-19 11:58:12', '2021-01-19 11:58:12', 3, 1, 'x城', '1585xxx', '1xxx06579', 1, 368),
	(25, 0, '2021-01-19 12:07:16', '2021-01-19 12:07:16', 0, 1, 'x波', '1807xxx', '1xxx90005', 3, 291),
	(26, 0, '2021-01-19 12:07:16', '2021-01-19 12:07:16', 0, 1, 'x晶', '1875xxx', '1xxx55558', 3, 299),
	(27, 0, '2021-01-19 12:07:16', '2021-01-19 12:07:16', 1, 1, 'x云', '1826xxx', '1xxxxxx', 3, 323),
	(28, 0, '2021-01-19 12:07:16', '2021-01-19 12:07:16', 1, 1, 'x城', '1585xxx', '1xxx06579', 3, 368),
	(29, 0, '2021-01-19 12:07:17', '2021-01-19 12:07:17', 4, 1, 'x云', '1826xxx', '1xxxxxx', 3, 323),
	(30, 0, '2021-01-19 12:07:17', '2021-01-19 12:07:17', 4, 1, 'x城', '15858xx', '1xxx06579', 3, 368),
	(31, 0, '2021-01-19 12:13:41', '2021-01-19 12:13:41', 2, 1, 'x杰', '13325xx', '1xxx12551', 6, 395),
	(32, 0, '2021-01-19 12:14:20', '2021-01-19 12:14:20', 0, 1, 'x波', '18072xx', '1xxx90005', 5, 291),
	(33, 0, '2021-01-19 12:14:21', '2021-01-19 12:14:21', 3, 1, 'x森', '1575xxx', '1xxxx', 5, 311),
	(34, 0, '2021-01-19 12:14:21', '2021-01-19 12:14:21', 3, 1, 'x云', '1828xxx', '1xxxxxx', 5, 323),
	(41, 0, '2021-01-19 14:24:34', '2021-01-19 14:24:34', 0, 1, 'x虹', '1353xxx', '1xxx46053', 8, 322),
	(42, 0, '2021-01-19 15:41:10', '2021-01-19 15:41:10', 3, 1, 'x晶', '1815xxx', '1xxx27792', 1, 360),
	(43, 0, '2021-01-19 15:41:10', '2021-01-19 15:41:10', 3, 1, 'x一', '1595xxx', '1xxx10291', 1, 371),
	(44, 0, '2021-01-19 15:41:10', '2021-01-19 15:41:10', 4, 1, 'x晗', '1864xxx', '1xxx11896', 1, 301),
	(45, 0, '2021-01-19 15:41:11', '2021-01-19 15:41:11', 4, 1, 'x甜', '1575xxx', '1xxx76952', 1, 310),
	(46, 0, '2021-01-19 15:41:11', '2021-01-19 15:41:11', 4, 1, 'x海', '1526xxx', '1xxx89338', 1, 325),
	(47, 0, '2021-01-19 15:41:11', '2021-01-19 15:41:11', 5, 1, 'x金', '1760xxx', '1xxx50797', 1, 317);
