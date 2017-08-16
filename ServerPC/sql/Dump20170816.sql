CREATE DATABASE  IF NOT EXISTS `hack` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hack`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: hack
-- ------------------------------------------------------
-- Server version	5.1.73-community

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

--
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coupon` (
  `IDX` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  `STARTDATE` date NOT NULL,
  `ENDDATE` date NOT NULL,
  `INFO` longtext NOT NULL,
  `COST` int(11) NOT NULL,
  PRIMARY KEY (`IDX`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;
INSERT INTO `coupon` VALUES (1,'5000won','2017-08-01','2017-08-31','5000won discount',5000),(2,'10000won','2017-08-01','2017-08-15','10000won discount',10000);
/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doorlock`
--

DROP TABLE IF EXISTS `doorlock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doorlock` (
  `OFFICECODE` varchar(10) NOT NULL,
  `RNUM` varchar(4) NOT NULL,
  `IP` varchar(15) NOT NULL,
  PRIMARY KEY (`OFFICECODE`,`RNUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doorlock`
--

LOCK TABLES `doorlock` WRITE;
/*!40000 ALTER TABLE `doorlock` DISABLE KEYS */;
INSERT INTO `doorlock` VALUES ('certis','001','172.169.1.1'),('certis','002','172.169.1.2'),('wap','001','172.168.0.1'),('wap','002','172.168.0.2'),('wap','003','172.168.0.3');
/*!40000 ALTER TABLE `doorlock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `office`
--

DROP TABLE IF EXISTS `office`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `office` (
  `OFFICECODE` varchar(10) NOT NULL,
  `NAME` varchar(45) NOT NULL,
  `inform` varchar(45) DEFAULT NULL,
  `OfficePnum` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`OFFICECODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `office`
--

LOCK TABLES `office` WRITE;
/*!40000 ALTER TABLE `office` DISABLE KEYS */;
INSERT INTO `office` VALUES ('certis','certis','security circle of PKNU','051-987-6543'),('wap','wap','Programming circle of PKNU','051-123-4567');
/*!40000 ALTER TABLE `office` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `IDX` int(11) NOT NULL AUTO_INCREMENT,
  `USERID` varchar(45) NOT NULL,
  `STARTDATE` date NOT NULL,
  `ENDDATE` date NOT NULL,
  `OFFICECODE` varchar(10) NOT NULL,
  `RNUM` varchar(4) NOT NULL,
  PRIMARY KEY (`IDX`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (7,'wlsgk0323','2017-08-19','2017-08-21','wap','001'),(8,'wlsgk0323','2017-08-25','2017-08-28','certis','002');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `OFFICECODE` varchar(10) NOT NULL,
  `ROOMNUMBER` varchar(45) NOT NULL,
  `MINNUM` int(11) NOT NULL,
  `MAXNUM` int(11) NOT NULL,
  PRIMARY KEY (`OFFICECODE`,`ROOMNUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES ('certis','001',2,3),('certis','002',2,4),('wap','001',2,4),('wap','002',2,4),('wap','003',4,6);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usecoupon`
--

DROP TABLE IF EXISTS `usecoupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usecoupon` (
  `USERID` varchar(45) NOT NULL,
  `COUPONIDX` int(11) NOT NULL,
  PRIMARY KEY (`USERID`,`COUPONIDX`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usecoupon`
--

LOCK TABLES `usecoupon` WRITE;
/*!40000 ALTER TABLE `usecoupon` DISABLE KEYS */;
INSERT INTO `usecoupon` VALUES ('wlsgk0323',1),('wlsgk0323',2);
/*!40000 ALTER TABLE `usecoupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usekey`
--

DROP TABLE IF EXISTS `usekey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usekey` (
  `RESERVATIONIDX` int(11) NOT NULL,
  `USERID` varchar(45) NOT NULL,
  PRIMARY KEY (`RESERVATIONIDX`,`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usekey`
--

LOCK TABLES `usekey` WRITE;
/*!40000 ALTER TABLE `usekey` DISABLE KEYS */;
INSERT INTO `usekey` VALUES (7,'wlsgk0323'),(8,'wlsgk0323');
/*!40000 ALTER TABLE `usekey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `ID` varchar(45) NOT NULL,
  `NAME` varchar(10) NOT NULL,
  `PWD` varchar(255) NOT NULL,
  `Pnum` varchar(11) NOT NULL,
  `Token` tinytext NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('wlsgk0323','LimJinHa','*A4B6157319038724E3560894F7F932C8886EBFCF','01047019413','TestToken');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'hack'
--

--
-- Dumping routines for database 'hack'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-16 20:03:38
