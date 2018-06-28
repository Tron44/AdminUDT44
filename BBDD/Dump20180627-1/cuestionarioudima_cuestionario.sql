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
-- Table structure for table `cuestionario`
--

DROP TABLE IF EXISTS `cuestionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuestionario` (
  `idCuestionario` int(11) NOT NULL,
  `nombreCuestionario` varchar(255) DEFAULT NULL,
  `publicacion` int(11) DEFAULT NULL,
  `idAsignatura` int(11) DEFAULT NULL,
  `descCuestionario` varchar(100) DEFAULT NULL,
  `idProfesor` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCuestionario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='tabla de cuestionarios de asignaturas';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuestionario`
--

LOCK TABLES `cuestionario` WRITE;
/*!40000 ALTER TABLE `cuestionario` DISABLE KEYS */;
INSERT INTO `cuestionario` VALUES (40,'p1',0,70,'descripcion defecto',NULL),(41,'Introduce el nombre del csd',0,22,'descripcion defecto',NULL),(42,'g1',0,70,'descripcion defecto',NULL),(43,'g2',0,70,'descripcion defecto',NULL),(45,'fdgfdg',1,20,'descripcion defecto',NULL),(48,'',0,75,'descripcion defecto',NULL),(49,'Control 1',0,75,'descripcion defecto',NULL),(51,'rr',1,85,'r111111',NULL),(52,'gg',1,20,'jemplo 2',NULL),(53,'primer control de mecánica',1,77,'de las unidades primera a cinco.',NULL),(54,'grafica con ,coma',1,70,'desc grafica con ,coma',NULL),(55,'Introduce ef',0,71,'dddddddddddddddddddddd',NULL),(56,'ff',0,72,'descffddddd',NULL),(57,'ff',1,72,'descffddddd',NULL),(58,'fffffffffffffff',0,72,'ffff11111',11),(59,'fffffffffffffff',0,72,'ffff11111',11),(60,'fraul',0,72,'frauldesc',19),(61,'mecanica 2 tratando comillas y 14 preguntas',1,77,'descripcion asociada a mecanica 2',11),(62,'Control unidades 1 a 5',0,21,'contempla la unidad 0',11),(63,'11111',0,23,'1111111',11),(64,'11111',0,23,'1111111',11),(65,'11111',0,23,'1111111',11),(66,'11111',0,23,'1111111',11),(67,'11111',0,23,'1111111',11),(68,'2222',0,23,'Introduce la descripción del control (máx 100)',11),(69,'333333333333333',0,23,'Introduce la descripción del control (máx 100)',11),(70,'777',0,23,'Introduce la descripción de777l control (máx 100)',11),(71,'888',0,23,'Introduce 888la descripción del control (máx 100)',11),(72,'10000ol',0,23,'Introduce la descripción del control (máx 100)',11),(73,'1212',0,23,'Introduce la descripció12n del control (máx 100)',11),(74,'14',0,23,'Introduce la descripción del control (máx 100)14',11),(75,'17',0,23,'Introduce la descripción del control (máx 100)',11);
/*!40000 ALTER TABLE `cuestionario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-27 20:45:25
