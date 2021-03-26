-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 27, 2021 at 12:54 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `supermarket`
--

-- --------------------------------------------------------

--
-- Table structure for table `electrical`
--

CREATE TABLE `electrical` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `cost` varchar(255) NOT NULL,
  `make` varchar(255) NOT NULL,
  `warrenty` varchar(255) NOT NULL,
  `managerId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `electrical`
--

INSERT INTO `electrical` (`id`, `name`, `cost`, `make`, `warrenty`, `managerId`) VALUES
(1, 'Drill', '7.99', 'Nortans', '4 Years', 1),
(2, 'Chain Saw', '159.99', 'Harvey Norman', '3 Years', 3),
(3, 'Power Hose', '7.2', 'Jeremys Jangles', 'No Warrenty', 2),
(4, 'Washing Machine', '79.99', 'Harvey Norman', '3 Years', 2);

-- --------------------------------------------------------

--
-- Table structure for table `manager`
--

CREATE TABLE `manager` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `office` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `manager`
--

INSERT INTO `manager` (`id`, `name`, `office`) VALUES
(1, 'John Smith', '260843'),
(2, 'Conor Weldon', '492942'),
(3, 'Sarah Corntan', '586921'),
(4, 'tewt', '34243');

-- --------------------------------------------------------

--
-- Table structure for table `supermarket`
--

CREATE TABLE `supermarket` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `season` text NOT NULL,
  `rating` varchar(255) NOT NULL,
  `warrenty` varchar(255) NOT NULL,
  `cost` double NOT NULL,
  `managerId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `supermarket`
--

INSERT INTO `supermarket` (`id`, `name`, `season`, `rating`, `warrenty`, `cost`, `managerId`) VALUES
(1, 'Easter Eggs', 'Easter', '4 Stars', 'No Warranty', 1.99, 1),
(2, 'Christmas Cookies', 'Christmas', '5 Star', 'No Warranty', 4.99, 2),
(3, 'Cool Pops', 'Summer', '5 Star', '1 Year', 3.99, 2),
(4, 'Pumpkin', 'Halloween', '4 Star', '3 Months', 7.99, 1),
(5, 'Easter Egg Basket', 'Easter', '3 Star', '2 Years', 9.99, 3),
(6, 'Christmas Tree', 'Christmas', '5 Star', '4 Years', 79.99, 1),
(7, 'Beach Ball', 'Summer', '4 Star', '1 Year', 14.99, 2),
(8, 'Dracula Costume', 'Halloween', '3 Star', '3 Year', 19.99, 3),
(9, 'Bunny Ears', 'Easter', '2 Star', 'No Warrenty', 2.99, 2),
(10, 'test', 'test', 'test', 'test', 4, 1);

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE `test` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `season` varchar(255) NOT NULL,
  `rating` varchar(255) NOT NULL,
  `warrenty` varchar(255) NOT NULL,
  `cost` int(11) NOT NULL,
  `managerid` int(10) UNSIGNED NOT NULL,
  `make` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `electrical`
--
ALTER TABLE `electrical`
  ADD PRIMARY KEY (`id`),
  ADD KEY `manager_fk` (`managerId`);

--
-- Indexes for table `manager`
--
ALTER TABLE `manager`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `supermarket`
--
ALTER TABLE `supermarket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `supermarket_manager_fk` (`managerId`);

--
-- Indexes for table `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `electrical`
--
ALTER TABLE `electrical`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `manager`
--
ALTER TABLE `manager`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `supermarket`
--
ALTER TABLE `supermarket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `test`
--
ALTER TABLE `test`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `electrical`
--
ALTER TABLE `electrical`
  ADD CONSTRAINT `manager_fk` FOREIGN KEY (`managerId`) REFERENCES `manager` (`id`);

--
-- Constraints for table `supermarket`
--
ALTER TABLE `supermarket`
  ADD CONSTRAINT `supermarket_manager_fk` FOREIGN KEY (`managerId`) REFERENCES `manager` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
