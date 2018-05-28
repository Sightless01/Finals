CREATE DATABASE  IF NOT EXISTS `database` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `database`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: database
-- ------------------------------------------------------
-- Server version	5.7.14

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(555) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'bruce','$2b$10$lnUKHpZ.p6/qN9s3YCjkD.K7UTvucExzYlnpvuFUdoq/6aq8eSWce'),(2,'fayps','$2b$10$Vn.vwV3I9.Jq7.C/F.gWoOHV4yDRRxgvW8KexYZDcurVmjDhLvxmC'),(3,'jascha','$2b$10$1vXhbfYAu4JNxjmZvHIzEucX6CQ9qAD90.enNt2Iw763d7/JIvIhG'),(4,'noelle','$2b$10$9Eq9kjSzla3FJwvfFTppWeWYSz71o1prJKM6BJ86zxWjGsVS00yGq');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `client_id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `contact` bigint(20) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '0',
  `block` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'noelle','bakakeng, baguio city',9126583123,'oppa@gmail.com','oppanoelle','$2a$10$WXctez4iSoG2Nm0V8OlRLOaBN7MMAZ/oHM21uDR16VJ9BgL/R3RZm',0,0),(2,'jascha','makati, manila',2546424236,'jasha@yahoo.com','jascha','$2a$10$WXctez4iSoG2Nm0V8OlRLOcgh/BEr0cLFaJfMNut/Y5TQ2fVwbFaG',0,0),(3,'fayps','camp 7, baguio city',9465283554,'twistafries@yahoo.co','twistafries','$2a$10$WXctez4iSoG2Nm0V8OlRLO4PfYqfqs4lOpnfJzsJ.KUkODYbeMWQG',0,0),(4,'bruce','sison, pangasinan',9653872382,'brucee@gmail.com','brucelee','$2a$10$WXctez4iSoG2Nm0V8OlRLOickce/hNQ7ub5k6AHd.Z5Dpr5.IMjyK',1,0);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `comp_id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `contact` bigint(20) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '0',
  `block` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`comp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'champion','champion','champ@email.com',9123446,'st. noelle, baguio city','$2b$10$ecxz8AZxREj2lY4dBwYun.sOIdFri296Wa4/thjxmP6TAajIEsqg2',1,0),(2,'adidas','adiiii','adi@yahoo.com',93247823,'st. ares, baguio city','$2b$10$ecxz8AZxREj2lY4dBwYun.qlb4XiZvtcAV2BCxJgTl1d2AMx5liTi',1,0),(3,'nautica','nautica','nautica@gmail.com',965578732,'st. fayps, baguio city','$2b$10$ecxz8AZxREj2lY4dBwYun.4Vg06a8R3Wlo1goKHF2YWxIG39hsXeK',1,0),(4,'hiit','hiithiit','hiit@gmail.com',95346823,'st. makati, manila','$2b$10$ecxz8AZxREj2lY4dBwYun.F0.HpheeYgZMIwiPCqnA16pjImXzU0W',1,0),(5,'montague burton','mon','monBur@email.com',9235378,'quezon city','$2b$10$ecxz8AZxREj2lY4dBwYun.VgSc7D8aIIBh1VQRWaz4dLaXfICLhhO',1,0),(6,'ikaw','lang','sapat@na.com',123,'sapusomo','$2b$10$jgx9eSICycHRrmTqO1VTJOW3LmHClW2hN016R40Y5G5f/Kx9rWOJu',0,0);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `prod_id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `categories` varchar(45) DEFAULT NULL,
  `frontview` varchar(200) DEFAULT NULL,
  `sideview` varchar(200) DEFAULT NULL,
  `backview` varchar(200) DEFAULT NULL,
  `availability` tinyint(4) DEFAULT NULL,
  `comp_id` tinyint(4) NOT NULL,
  PRIMARY KEY (`prod_id`),
  KEY `comp_id_idx` (`comp_id`),
  CONSTRAINT `comp_id` FOREIGN KEY (`comp_id`) REFERENCES `company` (`comp_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'SHIRT TEE','Cotton champion logo t-shirt',250,'tops','http://database:2018/image/champion/shirtF1.jpg','http://database:2018/image/champion/shirtS1.jpg','http://database:2018/image/champion/shirtB1.jpg',1,1),(2,'SHIRT PRINTED TEE','Step out in signature Champion style Heritage Tee',250,'tops','http://database:2018/image/champion/shirtF2.jpg','http://database:2018/image/champion/shirtS2.jpg','http://database:2018/image/champion/shirtB2.jpg',2,1),(3,'CHAMPION CROPPED COACH JACKET','100% polyester with cotton lining',699,'jacket','http://database:2018/image/champion/jacketF1.jpg','http://database:2018/image/champion/jacketS1.jpg','http://database:2018/image/champion/jacketB1.jpg',2,1),(4,'REVERSE WEAVE JOGGER','one pouch pocket at the back and a standout Champion logo patch at the left thigh.',400,'jumpsuits','http://database:2018/image/champion/joggerF1.jpg','http://database:2018/image/champion/joggerS1.jpg','http://database:2018/image/champion/joggerB1.jpg',1,1),(5,'SLEEVE SCRIPT TEE','made of lightweight cotton with a crew neck, long cuffed sleeves, the script logo across the chest',499,'knits','http://database:2018/image/champion/longsleeveF1.jpg','http://database:2018/image/champion/longsleeveS1.jpg','http://database:2018/image/champion/longsleeveB1.jpg',3,1),(6,'CHAMPION LOGO POOL SLIDE','made from durable material with a wide strap across the toes, lined in soft material ',299,'slipper','http://database:2018/image/champion/slipperF1.jpg','http://database:2018/image/champion/slipperS1.jpg','http://database:2018/image/champion/slipperB1.jpg',1,1),(7,'ADIDAS WOMENS CRAZY 8 ADV W','a knit textile/leather upper with a metallic effect, EVA midsole for lightweight cushioning',3200,'shoes','http://database:2018/image/adidas/shoesF1.jpg','http://database:2018/image/adidas/shoesS1.jpg','http://database:2018/image/adidas/shoesB1.jpg',1,2),(8,'CROP TANK TOP','sleeveless cropped cut, 3 stripes down each side, and a trefoil logo stitched on the chest.',499,'cropped','http://database:2018/image/adidas/sandoF1.jpg','http://database:2018/image/adidas/sandoS1.jpg','http://database:2018/image/adidas/sandoB1.jpg',2,2),(9,'3 STRIPES DRESS',' short sleeves with three stripes each and ribbed cuffs, a trefoil logo stitched on the chest,',899,'dress','http://database:2018/image/adidas/dressF1.jpg','http://database:2018/image/adidas/dressS1.jpg','http://database:2018/image/adidas/dressB1.jpg',2,2),(10,'WASH LONG SLEEVE BUTTON DOWN','stretch cotton shirt, Sleek and simple ',350,'longsleeve','http://database/image/nautica/poloF1.jpg','http://database:2018/image/nautica/poloS1.jpg','http://database:2018/image/nautica/poloB1.jpg',1,3),(11,'5-POCKET STRETCH JEANS','super-soft stretch denim feature the Gulf Stream medium wash',2000,'pants','http://database:2018/image/nautica/pantsF1.jpg','http://database:2018/image/nautica/pantsS1.jpg','http://database:2018/image/nautica/pantsB1.jpg',1,3),(12,'ACTIVE FIT SIGNATURE SWEATPANT','crafted in a comfortable sueded fleece and have a stretch waistband for added flexibility',699,'activewear','http://database:2018/image/nautica/joggerF1.jpg','http://database:2018/image/nautica/joggerS1.jpg','http://database:2018/image/nautica/joggerB1.jpg',1,3),(13,'COLORBLOCK LOGO JACKET','lightweight, water resistant jacket.',1000,'jacket','http://database:2018/image/nautica/jacketF1.jpg','http://database:2018/image/nautica/jacketS1.jpg','http://database:2018/image/nautica/jacketB1.jpg',1,3),(14,'HIIT Grey Muscle Fit Stretch T-Shirt','Muscle fit crew neck t-shirt with side seam tape detail. ',499,'activewear','http://database:2018/image/hiit/shirtF1.jpg','http://database:2018/image/hiit/shirtS1.jpg','http://database:2018/image/hiit/shirtB1.jpg',1,4),(15,'HIIT Grey Muscle Fit Stretch T-Shirt','Lightweight stretch training shorts with mesh panelled side detail and secure zip pocket. ',499,'acrivewear','http://database:2018/image/hiit/shortF1.jpg','http://database:2018/image/hiit/shortS1.jpg','http://database:2018/image/hiit/shortB1.jpg',2,4),(16,'HIIT Black Contour Running Tights','Cropped running tights with added stretch for greater movement. Contour print detail and a zipped key pocket with florescent tape. ',350,'activewear','http://database:2018/image/hiit/thightF1.jpg','http://database:2018/image/hiit/thightS1.jpg','http://database:2018/image/hiit/thightB1.jpg',1,4),(17,'3 Piece Montague Burton Grey Cotton Slim Fit Suit',' suit jacket comes with a textured wide lapel, single breasted with one button fastening and double back vent.',5500,'tuxedo','http://database:2018/image/montagueBurton/tuxF1.jpg','http://database:2018/image/montagueBurton/tuxS1.jpg','http://database:2018/image/montagueBurton/tuxB1.jpg',2,5),(18,'2 Piece Montague Burton Bright Blue Semi Plain Slim Fit Suit','This suit jacket comes with a wide lapel, single breasted with one button fastening and double back vent. ',6500,'tuxedo','http://database:2018/image/montagueBurton/tuxeF1.jpg','http://database:2018/image/montagueBurton/tuxeS1.jpg','http://database:2018/image/montagueBurton/tuxeB1.jpg',3,5);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `client_id` tinyint(4) NOT NULL,
  `prod_id` tinyint(4) NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `request_date` date DEFAULT NULL,
  KEY `client_id_idx` (`client_id`),
  KEY `prod_id_idx` (`prod_id`),
  CONSTRAINT `client_id` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1,2,'2018-05-25','2018-05-28',1,'2018-05-20'),(1,5,'2018-05-25','2018-05-28',NULL,'2018-05-20'),(1,4,'2018-05-25','2018-05-28',NULL,'2018-05-20'),(3,2,'2018-05-18','2018-05-20',0,'2018-05-12'),(3,4,'2018-05-18','2018-05-20',1,'2018-05-12'),(3,6,'2018-04-02','2018-04-07',1,'2018-04-01'),(2,8,'2018-03-15','2018-03-18',NULL,'2018-03-05'),(2,4,'2018-04-17','2018-04-20',NULL,'2018-04-05'),(2,3,'2018-04-10','2018-04-12',0,'2018-04-05');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `date_paid` date DEFAULT NULL,
  `date_returned` date DEFAULT NULL,
  `client_id` tinyint(4) NOT NULL,
  `prod_id` tinyint(4) NOT NULL,
  KEY `cilent_id_idx` (`client_id`),
  KEY `prod_id_idx` (`prod_id`),
  CONSTRAINT `cilent_id` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `prod_id` FOREIGN KEY (`prod_id`) REFERENCES `products` (`prod_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES ('2017-10-21','2017-10-25',2,2),('2016-01-01',NULL,3,10),('2018-04-13',NULL,4,15),('2018-05-18','2018-05-20',3,4),(NULL,NULL,3,6);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-29  6:04:30
