-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: sims_dbm
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.1

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
-- Table structure for table `Assignment`
--

DROP TABLE IF EXISTS `Assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Assignment` (
  `DocumentNo` int(11) NOT NULL,
  `StudentID` int(11) NOT NULL,
  `DueDate` date DEFAULT NULL,
  `Grade` int(11) DEFAULT NULL,
  PRIMARY KEY (`DocumentNo`,`StudentID`),
  KEY `fk_StudentID` (`StudentID`),
  CONSTRAINT `Assignment_ibfk_1` FOREIGN KEY (`DocumentNo`) REFERENCES `ClassMaterial` (`DocumentNo`),
  CONSTRAINT `Assignment_ibfk_2` FOREIGN KEY (`StudentID`) REFERENCES `Student` (`StudentID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Assignment`
--

LOCK TABLES `Assignment` WRITE;
/*!40000 ALTER TABLE `Assignment` DISABLE KEYS */;
/*!40000 ALTER TABLE `Assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Class`
--

DROP TABLE IF EXISTS `Class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Class` (
  `SectionNo` int(11) NOT NULL AUTO_INCREMENT,
  `CourseID` int(11) DEFAULT NULL,
  `RoomNo` varchar(10) DEFAULT NULL,
  `ClassTime` date DEFAULT NULL,
  `ClassDays` varchar(2) DEFAULT NULL,
  `StaffID` int(11) NOT NULL,
  PRIMARY KEY (`SectionNo`),
  KEY `fk_StaffID` (`StaffID`),
  CONSTRAINT `Class_ibfk_1` FOREIGN KEY (`StaffID`) REFERENCES `SchoolStaff` (`StaffID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Class`
--

LOCK TABLES `Class` WRITE;
/*!40000 ALTER TABLE `Class` DISABLE KEYS */;
/*!40000 ALTER TABLE `Class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ClassMaterial`
--

DROP TABLE IF EXISTS `ClassMaterial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ClassMaterial` (
  `DocumentNo` int(11) NOT NULL,
  `MaterialName` varchar(50) DEFAULT NULL,
  `DocumentType` varchar(40) DEFAULT NULL,
  `SectionNo` int(11) NOT NULL,
  PRIMARY KEY (`DocumentNo`),
  KEY `fk_SectionNo` (`SectionNo`),
  CONSTRAINT `ClassMaterial_ibfk_1` FOREIGN KEY (`SectionNo`) REFERENCES `Class` (`SectionNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ClassMaterial`
--

LOCK TABLES `ClassMaterial` WRITE;
/*!40000 ALTER TABLE `ClassMaterial` DISABLE KEYS */;
/*!40000 ALTER TABLE `ClassMaterial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Login`
--

DROP TABLE IF EXISTS `Login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Login` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(40) DEFAULT NULL,
  `Password` char(40) DEFAULT NULL,
  `pwdSalt` binary(16) DEFAULT NULL,
  `Role` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Username` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=1000001 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Login`
--

LOCK TABLES `Login` WRITE;
/*!40000 ALTER TABLE `Login` DISABLE KEYS */;
INSERT INTO `Login` VALUES (1000000,'Admin','5a2a1325e21f466f1737f0cbc6306482b06cd4dd','_6ñX”\Ùòˆšw5’tq','Administrator');
/*!40000 ALTER TABLE `Login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SchoolStaff`
--

DROP TABLE IF EXISTS `SchoolStaff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SchoolStaff` (
  `StaffID` int(11) NOT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `Position` varchar(30) DEFAULT NULL,
  `Department` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`StaffID`),
  CONSTRAINT `SchoolStaff_ibfk_1` FOREIGN KEY (`StaffID`) REFERENCES `Login` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SchoolStaff`
--

LOCK TABLES `SchoolStaff` WRITE;
/*!40000 ALTER TABLE `SchoolStaff` DISABLE KEYS */;
/*!40000 ALTER TABLE `SchoolStaff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student`
--

DROP TABLE IF EXISTS `Student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student` (
  `StudentID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(20) DEFAULT NULL,
  `LastName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`StudentID`),
  CONSTRAINT `Student_ibfk_1` FOREIGN KEY (`StudentID`) REFERENCES `Login` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student`
--

LOCK TABLES `Student` WRITE;
/*!40000 ALTER TABLE `Student` DISABLE KEYS */;
/*!40000 ALTER TABLE `Student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student_Class`
--

DROP TABLE IF EXISTS `Student_Class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student_Class` (
  `StudentID` int(11) NOT NULL,
  `SectionNo` int(11) NOT NULL,
  PRIMARY KEY (`StudentID`,`SectionNo`),
  KEY `fk_SectionNo` (`SectionNo`),
  CONSTRAINT `Student_Class_ibfk_1` FOREIGN KEY (`StudentID`) REFERENCES `Student` (`StudentID`),
  CONSTRAINT `Student_Class_ibfk_2` FOREIGN KEY (`SectionNo`) REFERENCES `Class` (`SectionNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student_Class`
--

LOCK TABLES `Student_Class` WRITE;
/*!40000 ALTER TABLE `Student_Class` DISABLE KEYS */;
/*!40000 ALTER TABLE `Student_Class` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-03 17:27:45
