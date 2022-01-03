-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 26, 2019 at 04:16 AM
-- Server version: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tailornew`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_login`
--

CREATE TABLE IF NOT EXISTS `admin_login` (
  `email` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin_login`
--

INSERT INTO `admin_login` (`email`, `password`) VALUES
('admin@gmail.com', 'admin'),
('jay@gmail.com', 'jay');

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE IF NOT EXISTS `city` (
  `cityid` int(4) NOT NULL AUTO_INCREMENT,
  `stateid` int(4) NOT NULL,
  `cityname` varchar(30) NOT NULL,
  PRIMARY KEY (`cityid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `city`
--

INSERT INTO `city` (`cityid`, `stateid`, `cityname`) VALUES
(1, 1, 'Vadodara'),
(2, 1, 'Surat'),
(3, 1, 'ahamdabad'),
(4, 1, 'Anand');

-- --------------------------------------------------------

--
-- Table structure for table `itemname`
--

CREATE TABLE IF NOT EXISTS `itemname` (
  `itemid` int(4) NOT NULL AUTO_INCREMENT,
  `itemname` varchar(20) NOT NULL,
  `titemid` int(4) NOT NULL,
  `basicrate` int(5) NOT NULL,
  `extradesignrate` int(5) NOT NULL,
  `image` varchar(250) NOT NULL,
  PRIMARY KEY (`itemid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- Dumping data for table `itemname`
--

INSERT INTO `itemname` (`itemid`, `itemname`, `titemid`, `basicrate`, `extradesignrate`, `image`) VALUES
(22, 'Shirt', 4, 350, 50, 'http://192.168.43.194/Tailornew/uploads/22.jpg'),
(23, 'pant', 1, 250, 0, 'http://192.168.43.194/Tailornew/uploads/23.jpg'),
(24, 'modi koti', 3, 500, 0, 'http://192.168.43.194/Tailornew/uploads/24.jpg'),
(25, 'navy koti', 4, 1500, 0, 'http://192.168.43.194/Tailornew/uploads/25.jpg'),
(26, 'jeans', 5, 350, 0, 'http://192.168.43.194/Tailornew/uploads/26.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `measure1`
--

CREATE TABLE IF NOT EXISTS `measure1` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `length` varchar(30) NOT NULL,
  `shoulder` varchar(30) NOT NULL,
  `baay` varchar(30) NOT NULL,
  `chest` varchar(30) NOT NULL,
  `arm` varchar(30) NOT NULL,
  `front` varchar(30) NOT NULL,
  `collor` varchar(30) NOT NULL,
  `tid` varchar(30) NOT NULL,
  `uid` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `measure1`
--

INSERT INTO `measure1` (`id`, `length`, `shoulder`, `baay`, `chest`, `arm`, `front`, `collor`, `tid`, `uid`) VALUES
(1, '1', '1', '2', '2', '3', '3', '3', '2', '8'),
(2, '28', '10', '15', '36', '10', '8', '15', '3', '15');

-- --------------------------------------------------------

--
-- Table structure for table `measure2`
--

CREATE TABLE IF NOT EXISTS `measure2` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `length` varchar(30) NOT NULL,
  `waist` varchar(30) NOT NULL,
  `seat` varchar(30) NOT NULL,
  `bottom` varchar(30) NOT NULL,
  `knee` varchar(30) NOT NULL,
  `thigh` varchar(30) NOT NULL,
  `level` varchar(30) NOT NULL,
  `tid` varchar(30) NOT NULL,
  `uid` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `measure2`
--

INSERT INTO `measure2` (`id`, `length`, `waist`, `seat`, `bottom`, `knee`, `thigh`, `level`, `tid`, `uid`) VALUES
(1, '5', '5', '5', '5', '5', '8', '4', '2', '8'),
(2, '35', '36', '39', '20', '14', '', '29', '2', '15');

-- --------------------------------------------------------

--
-- Table structure for table `registration`
--

CREATE TABLE IF NOT EXISTS `registration` (
  `userid` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `gender` varchar(20) NOT NULL,
  `address` varchar(100) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `stateid` int(4) NOT NULL,
  `cityid` int(4) NOT NULL,
  `contactno` bigint(10) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `registration`
--

INSERT INTO `registration` (`userid`, `name`, `gender`, `address`, `email`, `password`, `stateid`, `cityid`, `contactno`) VALUES
(2, 'a', 'Male', 'ccd', 'a@b.com', 'dsddd', 0, 0, 2587456325),
(3, 'jay', 'Male', 'assd fhdd hddgbx', 'j@g.com', 'j', 0, 0, 9874563210),
(4, 'qwewq', 'Male', 'xnxjxj', 'q@w.com', 'q', 0, 0, 9630852580),
(5, 'dk', 'Male', 'hdjsh', 'd@g.com', 'd', 0, 0, 9632580741),
(6, 'rohit', 'Male', 'kb', 'rohit@gmail.com', '1234', 0, 0, 9872121212),
(7, 'raj', 'Male', 'karelibaug', 'raj@gmail.com', '1234', 0, 0, 9831231231),
(8, 'vidhi', 'Male', 'Vyas falia', 'vidhi@gmail.com', '1234', 0, 0, 9825252525),
(10, 'dk', 'Male', 'dg', 'dk@gmail.com', '1234', 0, 0, 9844772145),
(11, 'pk', 'Male', 'vh', 'pk@gmail.com', '1234', 0, 0, 5454542142),
(12, 'jk', 'Male', 'vv', 'jk@gmail.com', '1234', 0, 0, 4545452147),
(13, 'jat', 'Male', 'thasra', 'jay@gmail.com', 'jay', 0, 0, 9988552210),
(15, 'pragnes', 'Male', 'gsgshx', 'p@gmail.com', 'a', 0, 0, 6467689565),
(16, 'jay', 'Male', 'satak pol', 'jay@g.com', '1', 0, 0, 9724458305);

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE IF NOT EXISTS `staff` (
  `staffid` int(4) NOT NULL AUTO_INCREMENT,
  `designation` varchar(20) NOT NULL,
  `staffname` varchar(30) NOT NULL,
  `address` varchar(100) NOT NULL,
  `email` varchar(30) NOT NULL,
  `contactno` varchar(15) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`staffid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`staffid`, `designation`, `staffname`, `address`, `email`, `contactno`, `password`) VALUES
(1, 'tailor', 'ramu', 'fdsfdf', 't@g.com', '9856321470', 't'),
(2, 'tailor', 'shamu', 'zxcvb', 't1@g.com', '9865821470', 't1'),
(3, 'Designer', 'Jay', 'Umreth', 'jay@gmail.com', '9724458305', 'd');

-- --------------------------------------------------------

--
-- Table structure for table `state`
--

CREATE TABLE IF NOT EXISTS `state` (
  `stateid` int(4) NOT NULL AUTO_INCREMENT,
  `statename` varchar(30) NOT NULL,
  PRIMARY KEY (`stateid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `state`
--

INSERT INTO `state` (`stateid`, `statename`) VALUES
(1, 'Gujarat');

-- --------------------------------------------------------

--
-- Table structure for table `taskassign`
--

CREATE TABLE IF NOT EXISTS `taskassign` (
  `taskid` int(4) NOT NULL AUTO_INCREMENT,
  `staffid` varchar(10) NOT NULL,
  `userid` varchar(10) NOT NULL,
  `description` varchar(30) NOT NULL,
  `taskstatus` varchar(20) NOT NULL,
  `itemid` varchar(20) NOT NULL,
  `deliverydate` varchar(20) NOT NULL,
  PRIMARY KEY (`taskid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `taskassign`
--

INSERT INTO `taskassign` (`taskid`, `staffid`, `userid`, `description`, `taskstatus`, `itemid`, `deliverydate`) VALUES
(1, '2', '1', 'pant', 'progress', '3', '2019-03-22'),
(2, '03', '1', 'asap', 'pending', '2', '2019-03-27'),
(3, '0', '1', 'pant', 'In progress', '20', '2019-04-02'),
(4, '0', '1', 'gxjxj', 'In progress', '17', '20/01/2019'),
(5, '1', '5', '56', 'In progress', '19', '2019-03-22'),
(6, '2', '6', 'embrace dark', 'In progress', '19', '22/02/2019'),
(7, '2', '1', 'zxc', 'pending', '21', ''),
(8, '2', '6', 'blue color', 'In progress', '20', '17/07/2019'),
(9, '1', '7', 'no note', 'In progress', '19', '21/02/2019'),
(10, '2', '8', 'open open', 'In progress', '20', '27/02/2019'),
(11, '1', '9', 'no bordrr', 'In progress', '20', '21/02/2019'),
(12, '2', '10', 'vb', 'pending', '20', ''),
(13, '2', '12', 'gh', 'pending', '20', ''),
(14, '1', '14', 'no,', 'pending', '20', ''),
(15, '2', '15', 'border', 'In progress', '19', '27/02/2019'),
(16, '2', '16', 'no', 'pending', '19', ''),
(17, '3', '15', 'border', 'In progress', '22', '02/04/2019'),
(18, '1', '15', 'no notes', 'pending', '23', '2019-03-31'),
(20, '2', '16', 'done', 'pending', '23', '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
