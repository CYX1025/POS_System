CREATE DATABASE  IF NOT EXISTS `portfolio` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `portfolio`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: portfolio
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `porder_detail`
--

DROP TABLE IF EXISTS `porder_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `porder_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_no` varchar(45) NOT NULL,
  `product_id` varchar(45) NOT NULL,
  `product_name` varchar(45) NOT NULL,
  `price` int NOT NULL,
  `quantity` int NOT NULL,
  `member` varchar(45) DEFAULT NULL,
  `employee` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `porder_detail`
--

LOCK TABLES `porder_detail` WRITE;
/*!40000 ALTER TABLE `porder_detail` DISABLE KEYS */;
INSERT INTO `porder_detail` VALUES (32,'202508080001','fp1','松露義大利麵',600,2,'Dennis Chang',NULL),(33,'202508080001','fp2','奶油義大利麵',550,4,'Dennis Chang',NULL),(34,'202508080001','av3','莫斯科騾子',380,4,'Dennis Chang',NULL),(35,'202508080001','av1','柯夢波丹',400,4,'Dennis Chang',NULL),(36,'202508080002','fp1','松露義大利麵',600,4,'Dennis Chang',NULL),(38,'202508080002','av3','莫斯科騾子',380,4,'Dennis Chang',NULL),(39,'202508080002','av1','柯夢波丹',400,4,'Dennis Chang',NULL),(40,'202508080002','av2','血腥瑪麗',400,3,'Dennis Chang',NULL),(42,'202508080003','fp1','松露義大利麵',600,5,'Dennis Chang',NULL),(43,'202508080003','df2','炸雞',250,6,'Dennis Chang',NULL),(44,'202508080003','df1','松露薯條',250,4,'Dennis Chang',NULL),(64,'202508190001','fp2','奶油義大利麵',550,5,'Dennis Chang',NULL),(65,'202508190001','fp1','松露義大利麵',600,5,'Dennis Chang',NULL),(66,'202508190001','aw2','OldFashioned',380,6,'Dennis Chang',NULL),(67,'202508190001','at3','帕洛瑪',360,3,'Dennis Chang',NULL),(68,'202508190001','at2','龍舌蘭日出',360,3,'Dennis Chang',NULL),(77,'202508220001','fp1','松露義大利麵',600,7,'Quee Wang',NULL),(78,'202508220001','av3','莫斯科騾子',380,12,'Quee Wang',NULL),(79,'202508220001','df2','炸雞',250,13,'Quee Wang',NULL),(80,'202508220002','fp2','奶油義大利麵',550,1,'Quee Wang',NULL),(81,'202508230001','fp1','松露義大利麵',600,6,NULL,'E0002'),(82,'202508230001','df2','炸雞',250,6,NULL,'E0002'),(83,'202508230001','av1','柯夢波丹',400,4,NULL,'E0002'),(84,'202508230002','fp1','松露義大利麵',600,4,'M0006','E0002'),(85,'202508230002','ds1','可樂',180,3,'M0006','E0002'),(86,'202508230002','av2','血腥瑪麗',400,1,'M0006','E0002'),(87,'202508230002','at3','帕洛瑪',360,5,'M0006','E0002'),(91,'202508230005','fp1','松露義大利麵',600,2,'M0002','E0001'),(92,'202508230005','ds1','可樂',180,2,'M0002','E0001'),(93,'202508230005','av3','莫斯科騾子',380,1,'M0002','E0001'),(94,'202508230005','at1','瑪格麗特',350,1,'M0002','E0001');
/*!40000 ALTER TABLE `porder_detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-25 10:07:55
