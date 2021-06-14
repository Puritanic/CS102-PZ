-- MySQL dump 10.13  Distrib 8.0.25, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: cs102pz
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `answers`
--

DROP TABLE IF EXISTS `answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `answer` varchar(450) NOT NULL,
  `question_id` int DEFAULT NULL,
  UNIQUE KEY `id` (`id`),
  KEY `answers_questions_id_fk` (`question_id`),
  CONSTRAINT `answers_questions_id_fk` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` VALUES (1,'public, default, protected, private',1),(2,'static, private, public, null',1),(3,'default, protected, main, volatile',1),(4,'Inheritance',2),(5,'Abstraction',2),(6,'Polymorphism',2),(7,'Inheritance',3),(8,'Polymorphism',3),(9,'Anon class',3),(10,'Inheritance',4),(11,'Polymorphism',4),(12,'Encapsulation',4),(13,'Private class',5),(14,'Abstract class',5),(15,'Static class',5),(16,'Local variable',6),(17,'Constant variable',6),(18,'Private variable',6),(19,'Instance variable',7),(20,'Protected variable',7),(21,'Local variable',7),(22,'No. You can only define constants and methods in an interface',8),(23,'Yes. You can define constants and methods in an interface',8),(24,'It depends.',8),(25,'Put them in the same file.',9),(26,'Create an interface.',9),(27,'Merge them into one class.',9),(28,'Complex numbers',10),(29,'Big O notation',10),(30,'Omega notation',10),(37,'Enumeration',14),(38,'Iterator',14),(39,' None of the above',14),(40,'compareWith\n',15),(41,'compareTo()',15),(42,'compare()',15);
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `players` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `total_points` int unsigned DEFAULT '0',
  `finished_games` int unsigned DEFAULT '0',
  `is_admin` tinyint(1) DEFAULT '0',
  `password` varchar(600) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players`
--

LOCK TABLES `players` WRITE;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
INSERT INTO `players` VALUES (1,'darkot','test@test.com',135,8,1,'95iWt/wcVfXyuVrFN2ktl4Z2sNGr2fHbIPUPDUjSqJeXKgBmLZfX1SCAj2AmEPsl'),(2,'TestAcc','test1@test.com',35,3,0,'6v9r4mnRYYPCtw35u08e4qNQntjVlgTKfCF2FHzBrutx7qV+TxBXZIcSNR4/y+wh'),(3,'TestAcc2','test2@test.com',40,3,0,'9SmBSKB2zoHSo/bucuH5cuAkObhy3vgI3k2phjB6mSWznzKjGUND+UTcZ08k2gLN'),(4,'TestAcc3','test3@test.com',60,4,0,'LoKcc4O805v5rK2GCe3aLjLoYY2x/m8ids/R1NdIrXpNMiQiz9G2pjYVFe60SYCP'),(5,'TestAcc4','test4@test.com',0,0,0,'Ftk25it8ct8XdH41dstxWfYUGRAoUU5DNNu9+poyN4mo1xGao9A0Sh6xQjO97sJX'),(6,'TestAcc5','test5@test.com',50,3,0,'L48ZAPX+QuZhnShWNhTH5Aeq75OdKRYMuriYjiWzwdQQ8advITOA9jAtnwJuR1Oo'),(7,'TestAcc6','test6@test.com',55,6,0,'D60cV6g6w8N0SM2ZfYCHhAB1ptMLPRp9DVe55bwCQpecYPphiPhGCryBNgZx+3ff'),(8,'TestAcc7','test7@test.com',20,3,1,'HeYHxfbgv5B8vhxTMj/CdDPYFS0/xOpHVF41aN9VScfw4u7MlAVQyK9JP0aW88lP'),(9,'TestAcc8','test8@test.com',10,2,0,'i4ASaIFg8+049HaYKSP2QXgtLxtYI92c/Tiz/vxeVj4x+mO8JoKTiQ/YKE4uaVZ3'),(10,'TestAcc9','test9@test.com',15,2,0,'nbrQEEvyhObhx3aMHswzAcHVwd6qECOrX+FLk2tfLnqUbWxwopHoGKDqWonvEX3u'),(11,'TestAcc10','test10@test.com',15,1,0,'zmZvAhvzgG2eOC9QftIYVPwHYdYtxCRXWorEfpqZkjsrFgh+Xq0z4d9JO++ldz4O');
/*!40000 ALTER TABLE `players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question` varchar(450) NOT NULL,
  `correct_answer_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,'What are the four access modifies?',1),(2,'What is it called when an object\'s type is determined at runtime?',6),(3,'What is it called when a class is created with attributes and/or methods from a more generic class, without copy-and-pasting them?',7),(4,'What is it called when we are using access modifiers to hide information about a class\'s implementation?',12),(5,'What type of class is defined to be incomplete and cannot be instanced?',14),(6,'What term is used to describe a variable that can only be used within a specific method or block?',16),(7,'What term is used for a variable that cannot be accessed from a static context (that is, without an instance of the class)?',19),(8,'Can you define variables in an interface?',22),(9,'What do you make if you expect multiple classes to interact in the same way?',26),(10,'What is used to describe the time complexity of an algorithm?',29),(14,'Which allows the removal of elements from a collection?',39),(15,'The Comparator interface contains the method?',42);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-14 18:15:58
