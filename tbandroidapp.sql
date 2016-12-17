-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 08, 2016 at 07:45 AM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tbandroidapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `p_id` int(10) NOT NULL,
  `p_heading` text NOT NULL,
  `p_content` text NOT NULL,
  `p_status` int(1) NOT NULL,
  `u_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`p_id`, `p_heading`, `p_content`, `p_status`, `u_id`) VALUES
(1, 'demo', 'demo', 1, 1),
(2, 'demo', 'demo', 2, 1),
(3, 'demo1', 'demo1', 0, 1),
(4, 'tejas', 'sadddddddddddddddddsa \nsadasd\nsdad\nasd\nasd\nasd\nasd\nsad\nasd\nsad\nas\ndas\nda\nsd\nasd\nasd\nasd', 0, 1),
(5, '', '', 0, 1),
(6, '', '', 0, 1),
(7, 'job posting', 'full job posttt\nssadfsaff\nasf\nsaf\nsaff\nasf\nasf\nasf\nsaf\nsaf\nasf\nsaf\nsaf\nsaf\nasf\nasf\nsfa\nasf\nsfa', 0, 1),
(8, 'job post mnj', 'safdsafsdf\ndsfg\nsdf\ndsf\nsdf\nds\nfdsfdsf', 0, 1),
(9, 'opening pspl', 'sadfsfsdfg\nghfgjhfgjhgk\nhgjghjghj\nfgh', 0, 1),
(10, '5555', '555', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `u_id` int(10) NOT NULL,
  `u_firstname` text NOT NULL,
  `u_lastname` text NOT NULL,
  `u_email` text NOT NULL,
  `u_password` text NOT NULL,
  `u_dob` text NOT NULL,
  `u_gender` varchar(10) NOT NULL,
  `u_address` text NOT NULL,
  `u_city` text NOT NULL,
  `u_mobile` varchar(10) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`u_id`, `u_firstname`, `u_lastname`, `u_email`, `u_password`, `u_dob`, `u_gender`, `u_address`, `u_city`, `u_mobile`, `status`) VALUES
(1, 'tejas', 'gawali', '', '', '', '', 'pimpri', 'pune', '8888876264', 0),
(2, 'Tejas', 'Gawali', 'tejas.gawali4@gmail.com', 'tejas', '12/06/1992', 'Male', 'Pimpri', 'pune', '8888876265', 1),
(5, 'firstname', 'lastname', 'emailid', 'password', 'dob', 'gender', 'address', 'city', 'mobile', 0),
(7, 'demo', 'demo', 'deo', 'demo', '', '', 'deo', 'demo', '123456', 0),
(8, '', '', '', '', '', '', '', '', '', 0),
(9, 'test', 'test', 'test', 'test', '', '', 'test', 'test', '1123456789', 0),
(10, 'test1', 'test1', 'test1', 'test1', '', '', 'test1', 'test1', '1123456782', 0),
(12, 'test2', 'test2', 'test2', 'test2', '', '', 'test2', 'test2', '1123456783', 0),
(13, 'test2', 'test2', 'test2', 'test2', '', '', 'test2', 'test2', '1123456784', 0),
(14, 'test3', 'test3', 'test3', 'test3', '', '', 'test3', 'test3', '1123456785', 0),
(15, 'demo1', 'demo1', 'demo1', 'demo1', '', '', 'demo1', 'demo1', '1234567896', 0),
(16, 'demo1', 'demo1', 'demo1', 'demo1', '', '', 'demo1', 'demo1', '123456789', 0),
(17, 'demo1', 'demo1', 'demo1', 'demo1', '', '', 'demo1', 'demo1', '1234567892', 0),
(18, 'demo1', 'demo1', 'demo1', 'demo1', '', '', 'demo1', 'demo1', '1234567895', 0),
(20, 'mk', 'mk', 'mk', 'mk', '', '', 'mk', 'mk', '3216457892', 0),
(21, 'mnj', 'mnj', 'mnj', 'mnj', '', '', 'mnj', 'mnj', '1234569871', 0),
(22, 'gbdgbj', 'dbgjdbjg\nb', 'avinash@gmail.com', '123456', '', '', 'pune', 'pune', '9893652521', 0),
(23, 'mangesh', 'khude', 'mangesh', '56325956', '', '', '55', 'pune', '9764125025', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`p_id`),
  ADD KEY `u_id` (`u_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`u_id`),
  ADD UNIQUE KEY `u_mobile` (`u_mobile`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `p_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `u_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `users` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
