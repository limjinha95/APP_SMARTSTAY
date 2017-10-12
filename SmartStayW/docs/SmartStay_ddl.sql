

DROP DATABASE `smartstay`;
CREATE DATABASE  `smartstay` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `smartstay`;
	
DROP TABLE `user_tb`;
DROP TABLE `office_tb`;
DROP TABLE `room_tb`;
DROP TABLE `reservation_tb`;
DROP TABLE `doorlock_tb`;
DROP TABLE `coupon_tb`;
DROP TABLE `user_coupon_tb`;
DROP TABLE `user_key_tb`;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
	

	/*!40101 SET @saved_cs_client     = @@character_set_client */;
	/*!40101 SET character_set_client = utf8 */;
	CREATE TABLE `user_tb` (
	`user_no` int(11) NOT NULL,
	  `user_id` varchar(45) NOT NULL,
	  `user_name` varchar(10) NOT NULL,
	  `user_pw` varchar(255) NOT NULL,
	  `user_mobile` varchar(11) NOT NULL,
	  `user_token` tinytext NOT NULL,
	  `user_corporate_number` int(100),
	  `user_address` varchar(255),
	  PRIMARY KEY (`user_no`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	/*!40101 SET character_set_client = @saved_cs_client */;
	
	
	/*!40101 SET @saved_cs_client     = @@character_set_client */;
	/*!40101 SET character_set_client = utf8 */;
	CREATE TABLE `office_tb` (
	  `office_no` int(11) NOT NULL,
	  `office_name` varchar(45) NOT NULL,
	  `office_address` varchar(255) DEFAULT NULL,
	  `office_call` varchar(45) DEFAULT NULL,
	  `office_inform` varchar(255) DEFAULT NULL,
	  PRIMARY KEY (`office_no`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	/*!40101 SET character_set_client = @saved_cs_client */;
	
	
	/*!40101 SET @saved_cs_client     = @@character_set_client */;
	/*!40101 SET character_set_client = utf8 */;
	CREATE TABLE `room_tb` (
	  `room_no` int(11) NOT NULL,
	  `room_name` varchar(45) NOT NULL,
	  `room_type` varchar(45) NOT NULL,
	  `standard_num` int(11) NOT NULL,
	  `maximum_num` int(11) NOT NULL,
	  `cost` int(11) NOT NULL,
	  `office_no` int(11) NOT NULL,
	  PRIMARY KEY (`room_no`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	/*!40101 SET character_set_client = @saved_cs_client */;

	
	/*!40101 SET @saved_cs_client     = @@character_set_client */;
	/*!40101 SET character_set_client = utf8 */;
	CREATE TABLE `reservation_tb` (
	  `reservation_no` int(11) NOT NULL AUTO_INCREMENT,
	  `user_id` varchar(45) NOT NULL,
	  `start_date` date NOT NULL,
	  `end_date` date NOT NULL,
	  `office_no` int(11) NOT NULL,
	  `room_no` int(11) NOT NULL,
	  PRIMARY KEY (`reservation_no`)
	) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
	/*!40101 SET character_set_client = @saved_cs_client */;
	
	
	/*!40101 SET @saved_cs_client     = @@character_set_client */;
	/*!40101 SET character_set_client = utf8 */;
	CREATE TABLE `doorlock_tb` (
	  `office_no` int(11) NOT NULL,
	  `room_no` int(11) NOT NULL,
	  `ip` varchar(15) NOT NULL,
	  PRIMARY KEY (`office_code`,`room_no`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	/*!40101 SET character_set_client = @saved_cs_client */;
	
	
	/*!40101 SET @saved_cs_client     = @@character_set_client */;
	/*!40101 SET character_set_client = utf8 */;
	CREATE TABLE `coupon_tb` (
	  `coupon_no` int(11) NOT NULL AUTO_INCREMENT,
	  `coupon_name` varchar(45) NOT NULL,
	  `start_date` date NOT NULL,
	  `end_date` date NOT NULL,
	  `coupon_info` longtext NOT NULL,
	  `cost` int(11) NOT NULL,
	  PRIMARY KEY (`coupon_no`)
	) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
	/*!40101 SET character_set_client = @saved_cs_client */;
	
	
	/*!40101 SET @saved_cs_client     = @@character_set_client */;
	/*!40101 SET character_set_client = utf8 */;
	CREATE TABLE `user_coupon_tb` (
	  `user_id` varchar(45) NOT NULL,
	  `coupon_no` int(11) NOT NULL,
	  PRIMARY KEY (`user_id`,`coupon_no`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	/*!40101 SET character_set_client = @saved_cs_client */;
	

	/*!40101 SET @saved_cs_client     = @@character_set_client */;
	/*!40101 SET character_set_client = utf8 */;
	CREATE TABLE `user_key_tb` (
	  `reservation_no` int(11) NOT NULL,
	  `user_id` varchar(45) NOT NULL,
	  PRIMARY KEY (`reservation_no`,`user_id`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	/*!40101 SET character_set_client = @saved_cs_client */;
	


	


	/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
	/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
	/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
	/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
	/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
	/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
	/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
	/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
	