CREATE DATABASE  IF NOT EXISTS `database` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `database`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: database
-- ------------------------------------------------------
-- Server version	5.7.19

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'bruce','$2y$10$qcFOgO4rc1DKhOscC6JaA.cCymAthyawbzdCDabx3YXMQUA0/3pfm'),(2,'fayps','$2y$10$jjdJ4DoJBZftwrUDe5ErNe8QQGXClMFZth4dZDZY/aaq3pYEzLhvG'),(3,'jascha','$2y$10$S3eQ9.jg0iqj.6yaiRc90uJuck7cautx4m4BN9ySp7lDFmqcki3L6'),(4,'noelle','$2y$10$7SxRpaf6yPN3WmS.TuyZFeJomjDv22jFn0MZlqYWcStFH5noeTFDe');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `client_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `contact` bigint(20) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `block` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'noelle','bakakeng, baguio city',9126583123,'oppa@gmail.com','oppanoelle','$10$7SxRpaf6yPN3WmS.TuyZFeJomjDv22jFn0MZlqYWcStFH5noeTFDe',0,0),(2,'jascha','makati, manila',2546424236,'jasha@yahoo.com','jascha','$10$S3eQ9.jg0iqj.6yaiRc90uJuck7cautx4m4BN9ySp7lDFmqcki3L6',0,0),(3,'fayps','camp 7, baguio city',9465283554,'twistafries@yahoo.co','twistafries','$10$jjdJ4DoJBZftwrUDe5ErNe8QQGXClMFZth4dZDZY/aaq3pYEzLhvG',0,0),(4,'bruce','sison, pangasinan',9653872382,'brucee@gmail.com','brucelee','$10$qcFOgO4rc1DKhOscC6JaA.cCymAthyawbzdCDabx3YXMQUA0/3pfm',1,0);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `comp_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `contact` bigint(20) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `block` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`comp_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'champion','champion','champ@email.com',9123446,'st. noelle, baguio city','$2y$10$mlKJDhVql9TAVSHziq6nH.8tR2DE4YAUJuNJah9.al4EPehTQIpVO',0,0),(2,'adidas','adiiii','adi@yahoo.com',93247823,'st. ares, baguio city','$2y$10$n2VcTR0JU7GOu/N4B2atjuBEombYCth./ipQBB.gen3WQ2wMDKgJG',0,0),(3,'nautica','nautica','nautica@gmail.com',965578732,'st. fayps, baguio city','$2y$10$jKL07LQz1r0KLJY6w.aEm.qz.BZzgnnY3hMDuEeCyTgDoSh5HJHk6',1,0),(4,'hiit','hiithiit','hiit@gmail.com',95346823,'st. makati, manila','$2y$10$7B/cs8KMv7xzCoROcs9gfOO8DQQGzKIDbY4on5Cuf5cv8SmEBSPtC',0,0),(5,'montague burton','mon','monBur@email.com',9235378,'quezon city','$2y$10$HyQhFgzsc68AbfibI5AmO.I.kJAVThFGTQ/rvDIvkC905Q9CE9MP6',0,0);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `prod_id` tinyint(4) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `desctription` varchar(200) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `categories` varchar(45) DEFAULT NULL,
  `event` varchar(45) DEFAULT NULL,
  `frontview` varchar(200) DEFAULT NULL,
  `sideview` varchar(200) DEFAULT NULL,
  `backview` varchar(200) DEFAULT NULL,
  `availability` tinyint(4) DEFAULT NULL,
  `comp_id` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`prod_id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'SHIRT TEE','Cotton champion logo t-shirt by the iconic sportswear label',250,'tops','daytime','/image/champion/shirtF1.jpg','/image/champion/shirtS1.jpg','/image/champion/shirtB1.jpg',0,'1'),(2,'SHIRT PRINTED TEE','Step out in signature Champion style Heritage Tee',250,'tops','daytime','/image/champion/shirtF2.jpg','/image/champion/shirtS2.jpg','/image/champion/shirtB2.jpg',0,'1'),(3,'CHAMPION CROPPED COACH JACKET','100% polyester with cotton lining',699,'jacket','vacation','/image/champion/jacketF1.jpg','/image/champion/jacketS1.jpg','/image/champion/jacketB1.jpg',0,'1'),(4,'REVERSE WEAVE JOGGER','one pouch pocket at the back and a standout Champion logo patch at the left thigh.',400,'jumpsuits','weekend','/image/champion/joggerF1.jpg','/image/champion/joggerS1.jpg','/image/champion/joggerB1.jpg',0,'1'),(5,'SLEEVE SCRIPT TEE','made of lightweight cotton with a crew neck, long cuffed sleeves, the script logo across the chest',499,'knits','weekend','/image/champion/longsleeveF1.jpg','/image/champion/longsleeveS1.jpg','/image/champion/longsleeveB1.jpg',0,'1'),(6,'CHAMPION LOGO POOL SLIDE','made from durable material with a wide strap across the toes, lined in soft material ',299,'slipper','vacation','/image/champion/slipperF1.jpg','/image/champion/slipperS1.jpg','/image/champion/slipperB1.jpg',0,'1'),(7,'ADIDAS WOMENS CRAZY 8 ADV W','a knit textile/leather upper with a metallic effect, EVA midsole for lightweight cushioning',3200,'shoes','daytime','/image/adidas/shoesF1.jpg','/image/adidas/shoesS1.jpg','/image/adidas/shoesB1.jpg',0,'2'),(8,'CROP TANK TOP','sleeveless cropped cut, 3 stripes down each side, and a trefoil logo stitched on the chest.',499,'cropped','party','/image/adidas/sandoF1.jpg','/image/adidas/sandoS1.jpg','/image/adidas/sandoB1.jpg',0,'2'),(9,'3 STRIPES DRESS',' short sleeves with three stripes each and ribbed cuffs, a trefoil logo stitched on the chest,',899,'dress','night out','/image/adidas/dressF1.jpg','/image/adidas/dressS1.jpg','/image/adidas/dressB1.jpg',0,'2'),(10,'WASH LONG SLEEVE BUTTON DOWN','stretch cotton shirt, Sleek and simple ',350,'longsleeve','work','/image/nautica/poloF1.jpg','/image/nautica/poloS1.jpg','/image/nautica/poloB1.jpg',0,'3'),(11,'5-POCKET STRETCH JEANS','super-soft stretch denim feature the Gulf Stream medium wash',2000,'pants','daytime','/image/nautica/pantsF1.jpg','/image/nautica/pantsS1.jpg','/image/nautica/pantsB1.jpg',0,'3'),(12,'ACTIVE FIT SIGNATURE SWEATPANT','crafted in a comfortable sueded fleece and have a stretch waistband for added flexibility',699,'activewear','sports','/image/nautica/joggerF1.jpg','/image/nautica/joggerS1.jpg','/image/nautica/joggerB1.jpg',1,'3'),(13,'COLORBLOCK LOGO JACKET','lightweight, water resistant jacket.',1000,'jacket','winter','/image/nautica/jacketF1.jpg','/image/nautica/jacketS1.jpg','/image/nautica/jacketB1.jpg',0,'3'),(14,'HIIT Grey Muscle Fit Stretch T-Shirt','Muscle fit crew neck t-shirt with side seam tape detail. ',499,'activewear','sports','/image/hiit/shirtF1.jpg','/image/hiit/shirtS1.jpg','/image/hiit/shirtB1.jpg',0,'4'),(15,'HIIT Grey Muscle Fit Stretch T-Shirt','Lightweight stretch training shorts with mesh panelled side detail and secure zip pocket. ',499,'acrivewear','sports','/image/hiit/shortF1.jpg','/image/hiit/shortS1.jpg','/image/hiit/shortB1.jpg',1,'4'),(16,'HIIT Black Contour Running Tights','Cropped running tights with added stretch for greater movement. Contour print detail and a zipped key pocket with florescent tape. ',350,'activewear','sports','/image/hiit/thightF1.jpg','/image/hiit/thightS1.jpg','/image/hiit/thightB1.jpg',0,'4'),(17,'3 Piece Montague Burton Grey Cotton Slim Fit Suit',' suit jacket comes with a textured wide lapel, single breasted with one button fastening and double back vent.',5500,'tuxedo','prom','/image/montagueBurton/tuxF1.jpg','/image/montagueBurton/tuxS1.jpg','/image/montagueBurton/tuxB1.jpg',0,'5'),(18,'2 Piece Montague Burton Bright Blue Semi Plain Slim Fit Suit','This suit jacket comes with a wide lapel, single breasted with one button fastening and double back vent. ',6500,'tuxedo','wedding','/image/montagueBurton/tuxeF1.jpg','/image/montagueBurton/tuxeS1.jpg','/image/montagueBurton/tuxeB1.jpg',0,'5');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `trans_id` int(11) NOT NULL,
  `date_booked` varchar(45) DEFAULT NULL,
  `date_paid` varchar(45) DEFAULT NULL,
  `date_returned` varchar(45) DEFAULT NULL,
  `comp_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `prod_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`trans_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,'10-18-17','10-18-17','10-23-17',1,2,2),(2,'12-01-17','12-03-17','12-10-17',3,3,10),(3,'03-30-17','03-31-18','04-05-18',5,1,17),(4,'05-03-18','05-03-18','05-08-18',4,4,15);
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

-- Dump completed on 2018-05-22 22:45:17
