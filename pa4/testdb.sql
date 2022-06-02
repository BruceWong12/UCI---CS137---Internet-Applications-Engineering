-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- 主机： 172.19.0.1:3306
-- 生成日期： 2022-06-01 08:27:31
-- 服务器版本： 5.7.22
-- PHP 版本： 7.2.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `testdb`
--

-- --------------------------------------------------------

--
-- 表的结构 `categories`
--

CREATE TABLE `categories` (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '分类名称',
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `categories`
--

INSERT INTO `categories` (`id`, `name`, `createdAt`, `updatedAt`) VALUES
('3088c810-800c-48cf-ab7e-4a718f9c324a', '男装女装', '2022-05-31 07:06:42', '2022-05-31 07:06:42'),
('49cfecb7-76c0-443a-86d7-2d8129aa1a8b', '电子数码', '2022-05-31 07:06:22', '2022-05-31 07:06:22'),
('5905b292-4313-40c8-bab1-66590197a5be', '办公家具', '2022-05-31 07:07:05', '2022-05-31 07:07:05'),
('f3d12aa6-2668-4824-9e6e-0f3057a0f4e4', '母婴玩具', '2022-05-31 07:07:26', '2022-05-31 07:07:26');

-- --------------------------------------------------------

--
-- 表的结构 `goods`
--

CREATE TABLE `goods` (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'id',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `imgs` varchar(255) DEFAULT NULL COMMENT '图片',
  `category` varchar(128) DEFAULT NULL COMMENT '分类',
  `categoryId` varchar(128) DEFAULT NULL COMMENT '分类ID',
  `user` varchar(255) DEFAULT NULL COMMENT '作者',
  `userId` varchar(255) DEFAULT NULL COMMENT '作者ID',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `price` double DEFAULT NULL COMMENT '价格',
  `number` bigint(20) DEFAULT NULL COMMENT '数量',
  `published` tinyint(1) DEFAULT '0' COMMENT '是否发布',
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `goods`
--

INSERT INTO `goods` (`id`, `title`, `imgs`, `category`, `categoryId`, `user`, `userId`, `description`, `price`, `number`, `published`, `createdAt`, `updatedAt`) VALUES
('1026e7e9-939a-4a10-b497-a296b2e7b183', '0.1201201', 'http://localhost:8080/upload/1654064367783.jpg', '男装女装', '3088c810-800c-48cf-ab7e-4a718f9c324a', 'admin', '00ebd457-3604-49e8-8e77-82e892f94669', '120210', 10, 10, 1, '2022-06-01 14:33:10', '2022-06-01 14:33:10'),
('13f6d8ea-1334-4260-a67e-606cb3cc312e', '12021', 'http://localhost:8080/upload/1654065839796.jpg', '电子数码', '49cfecb7-76c0-443a-86d7-2d8129aa1a8b', 'admin', '00ebd457-3604-49e8-8e77-82e892f94669', '12012012', 10, 10, 1, '2022-06-01 14:44:02', '2022-06-01 14:44:02'),
('554063ed-5b18-4bb3-ae01-10e1482c66ea', '1202101', 'http://localhost:8080/upload/1654064367783.jpg', '电子数码', '49cfecb7-76c0-443a-86d7-2d8129aa1a8b', 'admin', '00ebd457-3604-49e8-8e77-82e892f94669', '1201012', 120, 1202101, 1, '2022-06-01 14:43:03', '2022-06-01 14:43:03'),
('8348a8ef-617c-4028-96b4-f6404808bc4d', '23.1', 'http://localhost:8080/upload/1654064367783.jpg', '电子数码', '49cfecb7-76c0-443a-86d7-2d8129aa1a8b', 'admin', '00ebd457-3604-49e8-8e77-82e892f94669', '1202101', 10, 120, 1, '2022-06-01 14:40:50', '2022-06-01 14:40:50');

-- --------------------------------------------------------

--
-- 表的结构 `users`
--

CREATE TABLE `users` (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'id',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`, `createdAt`, `updatedAt`) VALUES
('00ebd457-3604-49e8-8e77-82e892f94669', 'admin', '123456', '1512450111@qq.com', '2022-05-31 07:03:19', '2022-05-31 07:03:19'),
('2d8c6e8a-a215-488e-b0cc-e435262ba061', '李四', '123456', '1512450000@qq.com', '2022-05-31 07:03:58', '2022-05-31 07:03:58'),
('8b20419a-0284-486a-9420-1968957b8230', 'root', '123456', '1512450000@qq.com', '2022-05-31 07:03:37', '2022-05-31 07:03:37'),
('c0c3bcdd-b130-4e2e-9ae9-7dcb5332145e', '张三', '123456', '1512450000@qq.com', '2022-05-31 07:03:52', '2022-05-31 07:03:52');

--
-- 转储表的索引
--

--
-- 表的索引 `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `goods`
--
ALTER TABLE `goods`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
