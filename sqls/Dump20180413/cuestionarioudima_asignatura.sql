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
  `idGrado` int(11) DEFAULT NULL,
  `nombreAsignatura` varchar(65) NOT NULL,
  `codigoAsignatura` varchar(65) DEFAULT NULL,
  `idProfesor` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAsignatura`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='tabla con las asignaturas pertenecientes a los distintos gra';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignatura`
--

LOCK TABLES `asignatura` WRITE;
/*!40000 ALTER TABLE `asignatura` DISABLE KEYS */;
INSERT INTO `asignatura` VALUES (20,111,'Fundamentos de Programación','1375',1),(21,111,'Álgebra','1755',1),(22,111,'Tecnología y Estructura de Computadores','1380',1),(23,111,'Análisis Matemático','1756',1),(24,111,'Tecnologías de la Información y de la Comunicación','1541',1),(25,111,'Electromagnetismo, Semiconductores y Ondas','1757',1),(26,111,'Matemática Discreta','1514',1),(27,111,'Estructuras de datos','1388',1),(28,111,'Inglés','1198',1),(29,111,'Metodología de la Programación','1382',1),(30,111,'Estadística y Probabilidad','1758',1),(31,111,'Bases de Datos','1392',1),(32,111,'Derecho Informático y Deontología Profesional','1759',1),(33,111,'Redes de Computadores','1537',1),(34,111,'Lógica','1760',1),(35,111,'Sistemas Operativos','1384',1),(36,111,'Interacción Persona-Computador','1761',1),(37,111,'Paradigmas de la Programación','1762',1),(38,111,'Organización de Empresas','1386',1),(39,111,'Análisis y Diseño de Algoritmos','1391',1),(40,111,'Ingeniería de Sistemas y de la Información','1763',1),(41,111,'Inteligencia Artificial','1395',1),(42,111,'Ingeniería del Software','1764',1),(43,111,'Sistemas Distribuidos','1398',1),(44,111,'Autómatas y Procesadores de lenguajes','1765',1),(45,111,'Arquitectura de Computadores y Sistemas','1401',1),(46,111,'Ingeniería del Conocimiento','1417',1),(47,111,'Seguridad en Redes y Criptografía','1766',1),(48,111,'Principios de la Economía de la Empresa','1378',1),(49,111,'Habilidades Directivas','1768',1),(50,111,'Auditoría de Sistemas Informáticos','1769',1),(51,111,'Responsabilidad Social Corporativa','1770',1),(52,111,'Programación Avanzada','1772',1),(53,111,'Ingeniería del Software II','1773',1),(54,111,'Ingeniería del Software III','1774',1),(55,111,'Patrones de Diseño y Frameworks','1775',1),(56,111,'Programación Web','1776',1),(57,111,'Programación en entornos empresariales','1777',1),(58,111,'Gestión de Proyectos','1405',1),(59,111,'Ingeniería de Requisitos','1778',1),(60,111,'Minería de Datos','1779',1),(61,111,'Sistemas de Apoyo a la Decisión','1420',1),(62,111,'Business Intelligence','1780',1),(63,111,'Aplicaciones Empresariales','1781',1),(64,111,'Modelos y Tecnologías de los Sistemas de Información','1396',1),(65,111,'Bases de Datos II','1782',1),(66,111,'Investigación Operativa','1523',1),(67,111,'Planificación Estratégica de las TIC','1783',1),(70,115,'Expresión Gráfica','1505',1),(71,115,'Fundamentos Matemáticos','1506',1),(72,115,'Fundamentos Físicos','1507',1),(73,115,'Fundamentos de Economía de la Empresa','1508',1),(74,115,'Tecnologías de la Información y de la Comunicación','1541',1),(75,115,'Química','1510',1),(76,115,'Ampliación de Fundamentos Matemáticos','1511',1),(77,115,'Mecánica','1512',1),(78,115,'Microeconomía','1074',1),(79,115,'Gestión de la Información y del Conocimiento','1329',1),(80,115,'Fundamentos de Estadística','1513',1),(81,115,'Matemática Discreta','1514',1),(82,115,'Bases de Datos','1392',1),(83,115,'Fundamentos de Electricidad y Electrónica','1515',1),(84,115,'Prevención de Riesgos Laborales','1516',1),(85,115,'Ingeniería de Materiales y Fabricación','1517',1),(86,115,'Sistemas de Gestión de Calidad','1518',1),(87,115,'Organización de la Producción','1519',1),(88,115,'Tecnología Mecánica','1520',1),(89,115,'Fundamentos de Termodinámica y Mecánica de Fluidos','1521',1),(90,115,'Automatización Industrial','1522',1),(91,115,'Investigación Operativa','1523',1),(92,115,'Oficina Técnica. Proyectos','1524',1),(93,115,'Procesos e Ingeniería de Fabricación','1525',1),(94,115,'Tecnología Eléctrica','1526',1),(95,115,'Tecnología Energética, Medio Ambiente y Energías Renovables','1527',1),(96,115,'Técnicas de Optimización de Sistemas Industriales','1528',1),(97,115,'Filosofías y Metodologías Industriales','1529',1),(98,115,'Logística','1530',1),(99,115,'Sistemas Integrados de Información Industrial','1531',1),(100,115,'Gestión de Proyectos en Ingeniería','1532',1),(101,115,'Organización de Empresas','1386',1),(102,115,'Inglés','1198',1),(103,115,'Análisis de Estados Financieros','1084',1),(104,115,'Dirección Comercial','1534',1),(105,115,'Dirección Estratégica de la Empresa Internacional','1493',1),(106,115,'Sociología General','1107',1),(107,115,'Introducción a la Macroeconomía','1535',1),(108,115,'Ética y Deontología Profesional','1536',1),(109,115,'Habilidades Profesionales','1383',1),(110,115,'Fundamentos de Programación','1375',1),(111,115,'Redes de Computadores','1537',1),(112,115,'Fundamentos de Sistemas de Información','1389',1),(113,115,'Prácticas en Empresas','1500',1);
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

-- Dump completed on 2018-04-13 19:26:12
