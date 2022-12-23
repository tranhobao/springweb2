-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 22, 2022 lúc 12:59 PM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `market`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `id` int(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `description` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `name`, `description`) VALUES
(1, 'Fruit', 'The kind that can be eaten without cooking'),
(2, 'Green Vegetables', 'The kind used to make salads or soups'),
(3, 'Spices', 'The kind used to enhance the taste of food');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `id` int(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  `full_name` varchar(40) NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `customer`
--

INSERT INTO `customer` (`id`, `password`, `full_name`, `address`, `city`) VALUES
(1, 'Abcd1234', 'John Smith', '30 Broadway', 'London'),
(2, 'Abcd1234', 'Jonny English', '99 River View', 'Reading'),
(3, 'Abcd1234', 'Elizabeth', '23 Buckinghamshire', 'York'),
(4, 'Abcd1234', 'Beatrix', '66 Royal Crescent', 'Bath'),
(10, 'Abcd1234', 'a', '681 Nguyễn Trãi P11 Q5, Quận 5', 'Tp.Hồ Chí Minh');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(40);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ordered`
--

CREATE TABLE `ordered` (
  `id` int(10) UNSIGNED NOT NULL,
  `customer_id` int(10) NOT NULL,
  `date_created` date NOT NULL,
  `total` float NOT NULL,
  `note` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `ordered`
--

INSERT INTO `ordered` (`id`, `customer_id`, `date_created`, `total`, `note`) VALUES
(0, 1, '2021-08-15', 150000, 'Use environmental protection bags'),
(1, 2, '2021-08-16', 235000, ''),
(2, 3, '2021-08-16', 65000, 'Need fast delivery'),
(3, 3, '2021-08-17', 80000, ''),
(4, 4, '2022-11-22', 0, ''),
(5, 3, '2022-11-22', 150000, ''),
(6, 1, '2022-11-22', 95000, ''),
(7, 4, '2022-11-23', 140000, ''),
(14, 10, '2022-12-21', 65000, ''),
(17, 10, '2022-12-21', 30000, ''),
(19, 10, '2022-12-21', 30000, ''),
(21, 10, '2022-12-21', 15000, ''),
(28, 10, '2022-12-22', 30000, ''),
(30, 10, '2022-12-22', 30000, ''),
(32, 10, '2022-12-22', 15000, ''),
(34, 10, '2022-12-22', 80000, ''),
(36, 10, '2022-12-22', 80000, ''),
(38, 10, '2022-12-22', 150000, '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ordered_detail`
--

CREATE TABLE `ordered_detail` (
  `id` int(10) UNSIGNED NOT NULL,
  `ordered_id` int(10) UNSIGNED NOT NULL,
  `vegetable_id` int(10) NOT NULL,
  `quantity` tinyint(4) NOT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `ordered_detail`
--

INSERT INTO `ordered_detail` (`id`, `ordered_id`, `vegetable_id`, `quantity`, `price`) VALUES
(0, 0, 1, 1, 30000),
(1, 1, 2, 1, 35000),
(2, 1, 3, 1, 150000),
(3, 1, 4, 1, 80000),
(4, 2, 5, 1, 35000),
(5, 2, 7, 2, 30000),
(6, 3, 6, 2, 80000),
(7, 4, 7, 1, 15000),
(8, 5, 6, 3, 120000),
(9, 5, 7, 2, 30000),
(10, 6, 4, 1, 80000),
(11, 6, 7, 1, 15000),
(12, 7, 1, 2, 60000),
(13, 7, 6, 2, 80000),
(15, 14, 2, 1, 35000),
(16, 14, 1, 1, 30000),
(18, 17, 1, 1, 30000),
(20, 19, 1, 1, 30000),
(22, 21, 7, 1, 15000),
(29, 28, 1, 1, 30000),
(31, 30, 1, 1, 30000),
(33, 32, 7, 1, 15000),
(35, 34, 4, 1, 80000),
(37, 36, 4, 1, 80000),
(39, 38, 3, 1, 150000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `vegetable`
--

CREATE TABLE `vegetable` (
  `id` int(10) NOT NULL,
  `category_id` int(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `unit` varchar(20) NOT NULL,
  `amount` int(10) NOT NULL,
  `image` varchar(50) NOT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `vegetable`
--

INSERT INTO `vegetable` (`id`, `category_id`, `name`, `unit`, `amount`, `image`, `price`) VALUES
(1, 1, 'Tomato', 'kg', 95, 'images/tomato.jpg', 30000),
(2, 1, 'potato', 'kg', 150, 'images/potato.jpg', 35000),
(3, 1, 'Apple', 'bag', 49, 'images/apple.jpg', 150000),
(4, 1, 'Water melon', 'per fruit', 17, 'images/watermelon.jpg', 80000),
(5, 2, ' broccoli', 'kg', 50, 'images/broccoli.jpg', 35000),
(6, 2, 'celery', 'kg', 78, 'images/celery.jpg', 40000),
(7, 1, ' spring onion', 'bunch', 47, 'images/springonion.jpg', 15000);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `ordered`
--
ALTER TABLE `ordered`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ordered_fk_customer` (`customer_id`);

--
-- Chỉ mục cho bảng `ordered_detail`
--
ALTER TABLE `ordered_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ordered_detail_vs_vegetable` (`vegetable_id`),
  ADD KEY `fk_ordered_detail_vs_ordered` (`ordered_id`);

--
-- Chỉ mục cho bảng `vegetable`
--
ALTER TABLE `vegetable`
  ADD PRIMARY KEY (`id`),
  ADD KEY `vegetable_fk_category` (`category_id`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `ordered`
--
ALTER TABLE `ordered`
  ADD CONSTRAINT `ordered_fk_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `ordered_detail`
--
ALTER TABLE `ordered_detail`
  ADD CONSTRAINT `fk_ordered_detail_vs_ordered` FOREIGN KEY (`ordered_id`) REFERENCES `ordered` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_ordered_detail_vs_vegetable` FOREIGN KEY (`vegetable_id`) REFERENCES `vegetable` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
