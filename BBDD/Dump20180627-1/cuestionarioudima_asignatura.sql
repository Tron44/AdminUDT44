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
-- Table structure for table `asignatura`
--

DROP TABLE IF EXISTS `asignatura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asignatura` (
  `idAsignatura` int(11) NOT NULL,
  `nombreAsignatura` varchar(65) NOT NULL,
  `codigoAsignatura` varchar(65) DEFAULT NULL,
  `idGrado` int(11) DEFAULT NULL COMMENT 'grado de asignatura',
  `idProfesor` int(2) DEFAULT NULL,
  PRIMARY KEY (`idAsignatura`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='tabla con las asignaturas pertenecientes a los distintos gra';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignatura`
--

LOCK TABLES `asignatura` WRITE;
/*!40000 ALTER TABLE `asignatura` DISABLE KEYS */;
INSERT INTO `asignatura` VALUES (1,'programacion avanzada','e101',107,NULL),(2,'estadistica','e102',107,NULL),(3,'gestion de proyectos','e103',107,NULL),(4,'programacion avanzada108','e201',108,NULL),(5,'estadistica108','e202',108,NULL),(6,'gestion de proyectos108','e203',108,NULL),(7,'programacion avanzada110','e301',110,NULL),(8,'estadistica110','e302',110,NULL),(9,'gestion de proyectos110','e303',110,NULL),(20,'Fundamentos de Programación','1375',111,1),(21,'Álgebra','1755',111,1),(22,'Tecnología y Estructura de Computadores','1380',111,1),(23,'Análisis Matemático','1756',111,1),(24,'Tecnologías de la Información y de la Comunicación','1541',111,1),(25,'Electromagnetismo, Semiconductores y Ondas','1757',111,1),(26,'Matemática Discreta','1514',111,1),(27,'Estructuras de datos','1388',111,1),(28,'Inglés','1198',111,1),(29,'Metodología de la Programación','1382',111,1),(30,'Estadística y Probabilidad','1758',111,1),(31,'Bases de Datos','1392',111,1),(32,'Derecho Informático y Deontología Profesional','1759',111,1),(33,'Redes de Computadores','1537',111,1),(34,'Lógica','1760',111,1),(35,'Sistemas Operativos','1384',111,1),(36,'Interacción Persona-Computador','1761',111,1),(37,'Paradigmas de la Programación','1762',111,1),(38,'Organización de Empresas','1386',111,1),(39,'Análisis y Diseño de Algoritmos','1391',111,1),(40,'Ingeniería de Sistemas y de la Información','1763',111,1),(41,'Inteligencia Artificial','1395',111,1),(42,'Ingeniería del Software','1764',111,1),(43,'Sistemas Distribuidos','1398',111,1),(44,'Autómatas y Procesadores de lenguajes','1765',111,1),(45,'Arquitectura de Computadores y Sistemas','1401',111,1),(46,'Ingeniería del Conocimiento','1417',111,1),(47,'Seguridad en Redes y Criptografía','1766',111,1),(48,'Principios de la Economía de la Empresa','1378',111,1),(49,'Habilidades Directivas','1768',111,1),(50,'Auditoría de Sistemas Informáticos','1769',111,1),(51,'Responsabilidad Social Corporativa','1770',111,1),(52,'Programación Avanzada','1772',111,1),(53,'Ingeniería del Software II','1773',111,1),(54,'Ingeniería del Software III','1774',111,1),(55,'Patrones de Diseño y Frameworks','1775',111,1),(56,'Programación Web','1776',111,1),(57,'Programación en entornos empresariales','1777',111,1),(58,'Gestión de Proyectos','1405',111,1),(59,'Ingeniería de Requisitos','1778',111,1),(60,'Minería de Datos','1779',111,1),(61,'Sistemas de Apoyo a la Decisión','1420',111,1),(62,'Business Intelligence','1780',111,1),(63,'Aplicaciones Empresariales','1781',111,1),(64,'Modelos y Tecnologías de los Sistemas de Información','1396',111,1),(65,'Bases de Datos II','1782',111,1),(66,'Investigación Operativa','1523',111,1),(67,'Planificación Estratégica de las TIC','1783',111,1),(70,'Expresión Gráfica','1505',115,1),(71,'Fundamentos Matemáticos','1506',115,1),(72,'Fundamentos Físicos','1507',115,1),(73,'Fundamentos de Economía de la Empresa','1508',115,1),(74,'Tecnologías de la Información y de la Comunicación','1541',115,1),(75,'Química','1510',115,1),(76,'Ampliación de Fundamentos Matemáticos','1511',115,1),(77,'Mecánica','1512',115,1),(78,'Microeconomía','1074',115,1),(79,'Gestión de la Información y del Conocimiento','1329',115,1),(80,'Fundamentos de Estadística','1513',115,1),(81,'Matemática Discreta','1514',115,1),(82,'Bases de Datos','1392',115,1),(83,'Fundamentos de Electricidad y Electrónica','1515',115,1),(84,'Prevención de Riesgos Laborales','1516',115,1),(85,'Ingeniería de Materiales y Fabricación','1517',115,1),(86,'Sistemas de Gestión de Calidad','1518',115,1),(87,'Organización de la Producción','1519',115,1),(88,'Tecnología Mecánica','1520',115,1),(89,'Fundamentos de Termodinámica y Mecánica de Fluidos','1521',115,1),(90,'Automatización Industrial','1522',115,1),(91,'Investigación Operativa','1523',115,1),(92,'Oficina Técnica. Proyectos','1524',115,1),(93,'Procesos e Ingeniería de Fabricación','1525',115,1),(94,'Tecnología Eléctrica','1526',115,1),(95,'Tecnología Energética, Medio Ambiente y Energías Renovables','1527',115,1),(96,'Técnicas de Optimización de Sistemas Industriales','1528',115,1),(97,'Filosofías y Metodologías Industriales','1529',115,1),(98,'Logística','1530',115,1),(99,'Sistemas Integrados de Información Industrial','1531',115,1),(100,'Gestión de Proyectos en Ingeniería','1532',115,1),(101,'Organización de Empresas','1386',115,1),(102,'Inglés','1198',115,1),(103,'Análisis de Estados Financieros','1084',115,1),(104,'Dirección Comercial','1534',115,1),(105,'Dirección Estratégica de la Empresa Internacional','1493',115,1),(106,'Sociología General','1107',115,1),(107,'Introducción a la Macroeconomía','1535',115,1),(108,'Ética y Deontología Profesional','1536',115,1),(109,'Habilidades Profesionales','1383',115,1),(110,'Fundamentos de Programación','1375',115,1),(111,'Redes de Computadores','1537',115,1),(112,'Fundamentos de Sistemas de Información','1389',115,1),(113,'Prácticas en Empresas','1500',115,1);
/*!40000 ALTER TABLE `asignatura` ENABLE KEYS */;
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
