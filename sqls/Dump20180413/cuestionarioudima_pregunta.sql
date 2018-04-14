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
-- Table structure for table `pregunta`
--

DROP TABLE IF EXISTS `pregunta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pregunta` (
  `idPregunta` int(11) NOT NULL,
  `textoPregunta` varchar(255) NOT NULL,
  `tipoPregunta` varchar(10) NOT NULL,
  `numRespuestas` int(11) DEFAULT NULL,
  `idCuestionario` int(11) NOT NULL,
  PRIMARY KEY (`idPregunta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='tabla de preguntas';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pregunta`
--

LOCK TABLES `pregunta` WRITE;
/*!40000 ALTER TABLE `pregunta` DISABLE KEYS */;
INSERT INTO `pregunta` VALUES (7,'pregunta para contorl grafica 3','Incluyente',NULL,6),(8,'otra pregunta para contorl 3','Excluyente',NULL,6),(10,'de que color era el caballo blanco de santiago.','Excluyente',NULL,7),(11,'si tienes habmre que peusdiojaklsdfkl asfjañlks jflkasjfñlqasñlkjfasdkljfkalsjfkslajf aioslfjasopru ','Incluyente',NULL,7),(12,'preugna otro grafica industrialesw preugna que si cuela flip','Incluyente',NULL,8),(13,'uo','Incluyente',NULL,9),(14,'pregunta 2 de graf4','Excluyente',NULL,9),(15,'vamos alla. que funcioan','Incluyente',NULL,9),(16,'y otroa mas con do cojo','Excluyente',NULL,9),(17,'asdfasdfqsfsda','Incluyente',NULL,10),(19,'la ULTIMAA','Excluyente',NULL,6),(21,'','Incluyente',NULL,12),(22,'ASDFASDF','Incluyente',NULL,13),(23,'YYYYYYYYYYYYYYYYYYYYY','Incluyente',NULL,14),(24,'RTYURTY','Incluyente',NULL,14),(25,'DDDDDDDDDDDDDDDDD','Incluyente',NULL,15),(26,'asdfasdfasfd','Incluyente',NULL,16),(30,'texto_pregunta_defecto','Incluyente',NULL,17),(31,'p2','Incluyente',NULL,17),(32,'texto_pregunta_defecto','Incluyente',NULL,17),(33,'texto_pregunta_defecto','Incluyente',NULL,17),(34,'texto_pregunta_defecto','Incluyente',NULL,18),(39,'texto_pregunta_defecto','Incluyente',NULL,11),(41,'tttttttttttttttttt','Incluyente',NULL,7),(47,'texto_pregunta_defecto','Incluyente',NULL,19),(48,'texto_pregunta_defecto','Excluyente',NULL,19),(49,'sdgsdfg','Incluyente',NULL,19),(52,'texto_pregunta_defecto','Incluyente',NULL,1),(55,'texto_pregunta_defecto','Incluyente',NULL,1),(58,'texto_pregunta_defecto','Excluyente',NULL,20),(59,'yyyyyyyyyyyyyyyyyy','Incluyente',NULL,20),(60,'hdfhhh','Incluyente',NULL,21),(61,'texto_pregunta_defecto','Incluyente',NULL,21),(62,'texto_pregunta_defecto','Incluyente',NULL,7),(63,'texto_pregunta_defecto','Incluyente',NULL,22),(64,'texto_pregunta_defecto','Incluyente',NULL,22),(65,'texto_pregunta_defecto','Incluyente',NULL,23),(66,'texto_pregunta_defecto','Incluyente',NULL,24),(67,'texto_pregunta_defecto','Incluyente',NULL,24),(68,'texto_pregunta_defecto','Incluyente',NULL,24),(69,'texto_pregunta_defecto','Incluyente',NULL,24),(70,'texto_pregunta_defecto','Incluyente',NULL,24),(82,'texto_pregunta_defecto','Incluyente',1,16),(86,'texto_pregunta_defecto','Incluyente',1,16),(87,'texto_pregunta_defecto','Incluyente',1,16),(88,'11','Incluyente',1,16),(90,'texto_pregunta_defecto','Incluyente',1,27),(91,'texto_pregunta_defecto','Incluyente',1,27),(92,'texto_pregunta_defecto','Incluyente',1,27),(93,'texto_pregunta_defecto','Incluyente',1,27),(94,'texto_pregunta_defecto','Incluyente',1,27),(95,'texto_pregunta_defefgjfgj','Incluyente',1,6),(96,'texto_pregunta_defecto','Incluyente',1,20),(97,'texto_pregunta_defecto','Incluyente',1,20),(98,'texto_pregunta_defecto','Incluyente',1,28),(99,'texto_pregunta_defecto','Incluyente',1,28),(100,'texto_pregunta_defecto','Incluyente',1,28),(101,'texto_pregunta_defecto','Incluyente',1,27),(102,'texto_pregunta_defecto','Incluyente',1,27),(103,'texto_pregunta_defecto','Incluyente',1,27),(104,'texto_pregunta_defecto','Incluyente',1,27),(105,'texto_pregdfffffdddd','Incluyente',1,27);
/*!40000 ALTER TABLE `pregunta` ENABLE KEYS */;
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
