-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: cuestionarioudima
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `respuesta`
--

DROP TABLE IF EXISTS `respuesta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `respuesta` (
  `idRespuesta` int(11) NOT NULL,
  `textoRespuesta` varchar(255) NOT NULL,
  `idPreguntaResp` int(11) NOT NULL,
  `validaRespuesta` int(11) DEFAULT NULL,
  PRIMARY KEY (`idRespuesta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='respuestas de las preguntas de los cuestinarios';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respuesta`
--

LOCK TABLES `respuesta` WRITE;
/*!40000 ALTER TABLE `respuesta` DISABLE KEYS */;
INSERT INTO `respuesta` VALUES (6,'uno',7,0),(7,'dos',7,1),(8,'teres',7,0),(9,'raul',8,0),(10,'dani',8,1),(11,'carlo',8,0),(12,'ala',8,0),(13,'raf',8,0),(14,'daini',8,0),(15,'paa',8,0),(18,'verde',10,0),(19,'negor',10,0),(20,'blanco',10,1),(21,'correr',11,0),(22,'comer',11,1),(23,'tragar',11,1),(24,'cagar',11,0),(25,'jarrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr',12,0),(26,'comorllllllllllllllll',12,1),(27,'sdfg',13,1),(28,'sdfg',13,1),(29,'werwer',14,0),(30,'sdfg',14,1),(31,'sdg',14,0),(32,'sdfg',14,0),(33,'sdfg',14,0),(34,'sdg',14,0),(35,'sdf',15,0),(36,'sdfgsdf',15,1),(37,'sdfgs',15,0),(38,'sdfg',15,0),(39,'sdfgsdfg',15,0),(40,'sdfg',15,0),(41,'sdfg',15,1),(42,'asfasdf',16,0),(43,'asdff',16,1),(44,'  fg',17,1),(47,'SDFGSDF',19,0),(48,'GSDFG',19,0),(49,'SDFGSDF',19,0),(50,'DFGSDFGSDFG',19,0),(51,'SDFG',19,1),(52,'SDFGSDFG',19,0),(54,'YTHGK',21,0),(55,'FG',22,1),(56,'RTYRTY',23,1),(57,'RTY',24,1),(58,'RTY',24,0),(59,'DFG',25,1),(60,'as',26,1),(61,'asdf',26,0),(62,'asdf',26,1),(70,'texto por defecto',30,0),(71,'texto por defecto',30,0),(72,'texto por defecto',30,0),(73,'texto por defecto',31,0),(74,'texto por defecto',32,0),(75,'texto por defecto',33,0),(76,'texto por defecto',33,0),(77,'texto por defecto',33,0),(78,'texto por defecto',33,0),(79,'texto por defecto',33,0),(80,'texto por defecto',33,0),(81,'jjjjjjjjjjjjjjjjj',34,0),(82,'hhhhhhhhhhhhh',34,0),(83,'texto por defecto',34,0),(84,'texto por defecto',34,0),(85,'texto por defecto',34,0),(92,'texto por defecto',39,0),(94,'texto por defecto',41,0),(95,'texto por defecto',41,0),(101,'texto por defecto',47,0),(102,'texto por defecto',48,1),(103,'dfgsdg',48,0),(104,'texto por defecto',48,0),(105,'texto por defecto',48,0),(106,'texto por defecto',49,1),(109,'texto por defecto',52,0),(112,'texto por defecto',55,0),(117,'texto por defecto',58,0),(118,'texto por defecto',58,1),(119,'texto por defecto',59,1),(120,'texto por defecto',59,1),(121,'ghjfg',59,0),(122,'texto por defecto',59,1),(123,'texto por defecto',59,0),(124,'fgjffffffffffffffffffff',60,1),(125,'fghjffffffffffffffffffffffff',60,1),(126,'texto por defecto',61,0),(127,'texto por defecto',62,0),(128,'texto por defecto',63,0),(129,'texto por defecto',64,0),(130,'texto por defecto',65,0),(131,'texto por defecto',66,0),(132,'texto por defecto',67,0),(133,'texto por defecto',68,0),(134,'texto por defecto',69,0),(135,'texto por defecto',70,0),(166,'texto por defecto',82,0),(170,'texto por defecto',86,0),(171,'texto por defecto',87,0),(172,'22fghjfgj',88,1),(174,'texto por defecto',90,0),(175,'texto por defecto',91,0),(176,'texto por defecto',92,0),(177,'texto por defecto',93,0),(178,'texto por defecto',94,0),(179,'texto pykghjk',95,1),(180,'texto por defecto',96,0),(181,'texto por defecto',97,0),(182,'texto por defecto',98,0),(183,'texto por defecto',99,0),(184,'texto por defecto',100,0),(185,'texto por defecto',101,0),(186,'texto por defecto',102,0),(187,'texto por defecto',103,0),(188,'texto por defecto',104,0),(189,'tffffffffffffff',105,1);
/*!40000 ALTER TABLE `respuesta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-13 19:26:12
