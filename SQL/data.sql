-- MySQL dump 10.13  Distrib 5.7.18, for Linux (x86_64)
--
-- Host: localhost    Database: sims_dbm
-- ------------------------------------------------------
-- Server version	5.7.18-0ubuntu0.16.04.1

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
  `DueDate` date DEFAULT NULL,
  `GradeWeight` float DEFAULT NULL,
  PRIMARY KEY (`DocumentNo`),
  CONSTRAINT `Assignment_ibfk_1` FOREIGN KEY (`DocumentNo`) REFERENCES `ClassMaterial` (`DocumentNo`) ON DELETE CASCADE ON UPDATE CASCADE
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
  `CourseID` varchar(15) DEFAULT NULL,
  `RoomNo` varchar(10) DEFAULT NULL,
  `ClassBeginTime` time DEFAULT NULL,
  `ClassEndTime` time DEFAULT NULL,
  `ClassDays` varchar(5) DEFAULT NULL,
  `UserID` int(11) NOT NULL,
  PRIMARY KEY (`SectionNo`),
  KEY `fk_UserID` (`UserID`),
  CONSTRAINT `Class_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `SchoolStaff` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1000010 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Class`
--

LOCK TABLES `Class` WRITE;
/*!40000 ALTER TABLE `Class` DISABLE KEYS */;
INSERT INTO `Class` VALUES (1000000,'MATH2402','N234','08:00:00','09:15:00','MW',1000030),(1000001,'CS3304','S876','09:30:00','11:00:00','TTh',1000032),(1000002,'ENG3000','N1099','11:15:00','12:30:00','TTh',1000031),(1000003,'PHYS2402','A606','12:15:00','14:00:00','MW',1000033),(1000004,'MATH3306','B202','15:45:00','17:00:00','TTh',1000034),(1000005,'CS4420','C220','14:15:00','15:30:00','MW',1000036),(1000006,'ENG1403','N1060','15:45:00','17:00:00','MW',1000035),(1000007,'CHEM','N806','17:15:00','18:30:00','W',1000037),(1000008,'CS3300','S876','10:00:00','11:45:00','MW',1000032),(1000009,'CS4000','N1000','10:00:00','11:45:00','MW',1000039);
/*!40000 ALTER TABLE `Class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ClassMaterial`
--

DROP TABLE IF EXISTS `ClassMaterial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ClassMaterial` (
  `DocumentNo` int(11) NOT NULL AUTO_INCREMENT,
  `MaterialName` varchar(50) DEFAULT NULL,
  `DocumentType` varchar(40) DEFAULT NULL,
  `SectionNo` int(11) NOT NULL,
  PRIMARY KEY (`DocumentNo`),
  KEY `fk_SectionNo` (`SectionNo`),
  CONSTRAINT `ClassMaterial_ibfk_1` FOREIGN KEY (`SectionNo`) REFERENCES `Class` (`SectionNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
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
) ENGINE=InnoDB AUTO_INCREMENT=1000040 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Login`
--

LOCK TABLES `Login` WRITE;
/*!40000 ALTER TABLE `Login` DISABLE KEYS */;
INSERT INTO `Login` VALUES (1000000,'Admin','42005e57d317fbb4b34f51d1b07313099eb6c029','^Mª¡_Î–® ¼\æ°5ü','Staff'),(1000001,'FundieA1','14c8cdb7662e16e0f9f3d887fc4368e251c2a656','…›c5L\åsJf­’','Student'),(1000002,'GraceJ1','280816884164c0b2579b866dc2f6ce1e66b3781f','†™\Ôı\×=aAŸ\æş7\î	','Student'),(1000003,'SmithT1','b4638b5907e95bda1b74f438379c1bc02ceca239','ßeü)”“KxG[4·¾ƒ','Student'),(1000004,'RainvilleW1','4dcc7783bc1ffa3f032d5334990e44df3a0c8c27','“\å1Ô‹Á\İ\äø£\Ú)ı','Student'),(1000005,'CaulfieldJ1','1dc10100b01dd66d1e88a1bc515b93b45ea97f58','\ÃV‚\Ú#6\èuŠw@	\ÅF','Student'),(1000006,'RonayneO1','7cea5b5b9974fc787c69deee4695ac316c213a10','\ì$\Ä\ßú¯—\íUQb\nôU','Student'),(1000007,'PalumboM1','0b2686d52f9a426ec207bd90bbeb739b334b0a6e','\n@j‹ƒkŸô\Öº\ÃşP','Student'),(1000008,'WangP1','530882cb1a11e703cb498463b14ac506b7e2b8dc','e\à°öQAn‘\Ú\ç÷\İiÃ˜','Student'),(1000009,'WestO1','2b8845d40ead7a401b6df0ce3098fa6afe6f0690','XMQ„\É.`IŒº5e°\Îøs','Student'),(1000010,'KendallW1','1f654d8d211cb567771ef225f142395a97348c48','p8r	\Ã+N&?‘&¬\Ğ','Student'),(1000011,'BodinJ1','a607ba49acd64e1c1f27afeb76827084227b3b84','DV“\Z¿n;ƒ\åşSü\×','Student'),(1000012,'SedarisA1','e7e7aa94b17e16ba6c9880d22e37b3527915a6ef','ø\İ]°Š“½\n,’ğqC','Student'),(1000013,'JacobsonP1','11d34d354ab3dea9aba1651fb8097cac78af40f5','ıW»_Fºrˆöy]I?\İ ','Student'),(1000014,'StaplesA1','eee81d4451b22abce7bc30543839b0a61eb30719','\É>\çnlx#ş¬r÷0h*©°','Student'),(1000015,'ZapataT1','0b2fa0ff58ee047e22efd4b5aeb780ec2bea3169','xwÏ‹O@öWªUB’`b\Â','Student'),(1000016,'PaquetteN1','ba2389549b605490abeadc84f6de1cea7f7318ea','¨¶tT\Ç4\Ì\\óJ0$\Z\Ş\Ã','Student'),(1000017,'WintersJ1','2931c9a9d32855b52e8bab68c1ea8f7cb05a9f7f','VÈV#QAJy}\èú \É\È','Student'),(1000018,'PrescottV1','7a4e7ee2fb44e66f56235fb5c7432bf06b90a243','\ÎŠm\êoÿÒ•/\ì\Ğ_Îˆ','Student'),(1000019,'ArgyleJ1','2a711f2580838af0afc572dc8ad29940ce91fd0b','Y\Òm\Ö÷ˆ\Îp\Äú\Æ','Student'),(1000020,'WoodE1','2e7d4d9acf1d8caf84798ffd4bb4e8d568e035ae','\ÒÌ”eùõùu\ßA*”^d','Student'),(1000021,'GesincourtS1','745acdf77b9c419472ee67b11e5f1e80a2ccf968','¶\ë\rAzÀ_nô{ò\Í','Student'),(1000022,'BodinS1','a8823333b5ebf724bbfa1ddc02ec5bf1e10072f2','\Õ\é\ÃødNj¿ì¥•\Õs†','Student'),(1000023,'LunngrenS1','277dff7426ee74b03c54bb1c0cd567c6460a3957','+pöÉ¼\ë\à”‹Ì»^SS\êm','Student'),(1000024,'WhelanM1','fcfb56e758bc9087253fc8314ed004c38c533c37','\ëU†v¼†µ	{T\Şo\İ\âr','Student'),(1000025,'LiA1','2a2a6c68e6a7dd11c59a44c5a2b6f346e9df559a','c\Ëú»IõşEy€\åV°','Student'),(1000026,'PaikF1','eed790dc9a500469108da700e9957b0dba0d9ee2','I†Zñ\éEs)[\Z@ÿ','Student'),(1000027,'BrillJ1','e25b90aee5981279ac00a6418f22359df9a72dbb','\Z$\ïm6m\0v¥R\íX\ì€Z','Student'),(1000028,'VentiC1','81592fca1c0633d5b2f2c36180c4c81a99cdab7d','­qò#8T\Ú\ëÀúğLß±','Student'),(1000029,'MillerP1','253a57f963cc89dee771e9e1ee9a5d4d1a4eac1b','¾\Éú\ÃXúo»p|Ù•<[','Student'),(1000030,'ChanM1','58bbe52ef65bd67ecba0634c4f42aa36982d354e','Gr\àhs-<¿t¿©,','Staff'),(1000031,'WhiteA1','4375e781b7791e6f02a8f4d8fedc0f3d25196672','ş]\å\Ë:¼\Î9¢ö¤sñ*\Ê\É','Staff'),(1000032,'AndradaJ1','0205ab4b0fb14bb4bc760b28b78758974579691a','\Üd \"\Æñ\Şi’?\rHM','Staff'),(1000033,'HopeW1','0369a552ebe51b6495ed5328807e82d97b77e66c','¬\çıAh®_G\ÓÁõ]n','Staff'),(1000034,'LiuS1','ecb790bb297340f6c1a97e509c0802d2ea72e1d7','º\ZÁ6¿µ\Şm&\è¬‡ ','Staff'),(1000035,'StockJ1','a763d69c44a20f7709b779b296907cfcb0c13c28','”ü[\ZšX~¤®¤Šğ¿','Staff'),(1000036,'MartinezH1','6482da0eadfe0e23b3166b6b2f33a7b0d205a39b','uOÿıQXr–#ı\Û/','Staff'),(1000037,'SimsJ1','d8cbbe12316fa9b861bc142a5e8dde937d2609ac','ˆ;\Æ?õ2^iÀ÷3','Staff'),(1000038,'JonesD1','24290291b9575a9447a796262372f217c4cc97d2','\ì\n*(º\âA\Ökc§\æ\ßmu','Student'),(1000039,'TestT1','6bd67567a9a2966025e7ae01a4bd87f0a99ece9a','‰±¬rs’\n\×\îe«ŸH¤ò8','Staff');
/*!40000 ALTER TABLE `Login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SchoolStaff`
--

DROP TABLE IF EXISTS `SchoolStaff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SchoolStaff` (
  `UserID` int(11) NOT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `Position` varchar(30) DEFAULT NULL,
  `Department` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  CONSTRAINT `SchoolStaff_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `Login` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SchoolStaff`
--

LOCK TABLES `SchoolStaff` WRITE;
/*!40000 ALTER TABLE `SchoolStaff` DISABLE KEYS */;
INSERT INTO `SchoolStaff` VALUES (1000000,'Admin','Nimda','Database Administrator','IT'),(1000030,'Martin','Chan','Teacher','Mathematics'),(1000031,'Aaron','White','Teacher','English'),(1000032,'Joseph','Andrada','Teacher','Computer Science'),(1000033,'William','Hope','Teacher','Science'),(1000034,'Sheila','Liu','Teacher','Mathematics'),(1000035,'John','Stock','Teacher','English'),(1000036,'Hugo','Martinez','Teacher','Computer Science'),(1000037,'Julie','Sims','Teacher','Science'),(1000039,'Teacher','Test','Teacher','Computer Science');
/*!40000 ALTER TABLE `SchoolStaff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student`
--

DROP TABLE IF EXISTS `Student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(20) DEFAULT NULL,
  `LastName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  CONSTRAINT `Student_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `Login` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1000039 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student`
--

LOCK TABLES `Student` WRITE;
/*!40000 ALTER TABLE `Student` DISABLE KEYS */;
INSERT INTO `Student` VALUES (1000001,'Austin','Fundie'),(1000002,'James','Grace'),(1000003,'Tyler','Smith'),(1000004,'William','Rainville'),(1000005,'John','Caulfield'),(1000006,'Olaf','Ronayne'),(1000007,'Mark','Palumbo'),(1000008,'Pei','Wang'),(1000009,'Oliver','West'),(1000010,'William','Kendall'),(1000011,'John','Bodin'),(1000012,'Allen','Sedaris'),(1000013,'Paul','Jacobson'),(1000014,'Aaron','Staples'),(1000015,'Tom','Zapata'),(1000016,'Nicole','Paquette'),(1000017,'Jen','Winters'),(1000018,'Viella','Prescott'),(1000019,'Jessica','Argyle'),(1000020,'Emily','Wood'),(1000021,'Seb','Gesincourt'),(1000022,'Steve','Bodin'),(1000023,'Steve','Lunngren'),(1000024,'Mark','Whelan'),(1000025,'Adam','Li'),(1000026,'Florian','Paik'),(1000027,'Jen','Brill'),(1000028,'Chase','Venti'),(1000029,'Price','Miller'),(1000038,'Derek','Jones');
/*!40000 ALTER TABLE `Student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student_Assignment`
--

DROP TABLE IF EXISTS `Student_Assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student_Assignment` (
  `UserID` int(11) NOT NULL,
  `DocumentNo` int(11) NOT NULL,
  `FileName` varchar(50) DEFAULT NULL,
  `Grade` float DEFAULT NULL,
  PRIMARY KEY (`DocumentNo`,`UserID`),
  KEY `fk_UserID` (`UserID`),
  CONSTRAINT `Student_Assignment_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `Student` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Student_Assignment_ibfk_2` FOREIGN KEY (`DocumentNo`) REFERENCES `ClassMaterial` (`DocumentNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student_Assignment`
--

LOCK TABLES `Student_Assignment` WRITE;
/*!40000 ALTER TABLE `Student_Assignment` DISABLE KEYS */;
/*!40000 ALTER TABLE `Student_Assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student_Class`
--

DROP TABLE IF EXISTS `Student_Class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student_Class` (
  `UserID` int(11) NOT NULL,
  `SectionNo` int(11) NOT NULL,
  `GradeAvg` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`UserID`,`SectionNo`),
  KEY `fk_SectionNo` (`SectionNo`),
  CONSTRAINT `Student_Class_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `Student` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Student_Class_ibfk_2` FOREIGN KEY (`SectionNo`) REFERENCES `Class` (`SectionNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student_Class`
--

LOCK TABLES `Student_Class` WRITE;
/*!40000 ALTER TABLE `Student_Class` DISABLE KEYS */;
INSERT INTO `Student_Class` VALUES (1000001,1000003,NULL),(1000001,1000007,NULL),(1000002,1000003,NULL),(1000002,1000006,NULL),(1000003,1000000,NULL),(1000003,1000003,NULL),(1000003,1000006,NULL),(1000003,1000007,NULL),(1000004,1000002,NULL),(1000004,1000003,NULL),(1000004,1000005,NULL),(1000004,1000007,NULL),(1000004,1000008,NULL),(1000005,1000005,NULL),(1000006,1000002,NULL),(1000006,1000004,NULL),(1000006,1000006,NULL),(1000006,1000008,NULL),(1000007,1000002,NULL),(1000007,1000004,NULL),(1000007,1000005,NULL),(1000008,1000003,NULL),(1000008,1000004,NULL),(1000008,1000005,NULL),(1000008,1000007,NULL),(1000009,1000002,NULL),(1000009,1000003,NULL),(1000009,1000004,NULL),(1000009,1000005,NULL),(1000009,1000006,NULL),(1000009,1000008,NULL),(1000010,1000000,NULL),(1000010,1000001,NULL),(1000010,1000002,NULL),(1000010,1000003,NULL),(1000010,1000004,NULL),(1000010,1000006,NULL),(1000010,1000008,NULL),(1000011,1000000,NULL),(1000011,1000002,NULL),(1000011,1000004,NULL),(1000011,1000007,NULL),(1000011,1000008,NULL),(1000012,1000000,NULL),(1000012,1000002,NULL),(1000012,1000003,NULL),(1000012,1000005,NULL),(1000012,1000006,NULL),(1000012,1000007,NULL),(1000012,1000008,NULL),(1000013,1000000,NULL),(1000013,1000002,NULL),(1000013,1000005,NULL),(1000014,1000000,NULL),(1000014,1000002,NULL),(1000014,1000003,NULL),(1000014,1000006,NULL),(1000014,1000008,NULL),(1000015,1000000,NULL),(1000015,1000002,NULL),(1000015,1000006,NULL),(1000015,1000007,NULL),(1000016,1000000,NULL),(1000016,1000002,NULL),(1000016,1000005,NULL),(1000016,1000006,NULL),(1000016,1000007,NULL),(1000016,1000008,NULL),(1000017,1000000,NULL),(1000017,1000001,NULL),(1000017,1000002,NULL),(1000017,1000004,NULL),(1000017,1000005,NULL),(1000017,1000008,NULL),(1000018,1000002,NULL),(1000018,1000003,NULL),(1000018,1000004,NULL),(1000018,1000005,NULL),(1000018,1000007,NULL),(1000018,1000008,NULL),(1000019,1000000,NULL),(1000019,1000002,NULL),(1000019,1000007,NULL),(1000020,1000001,NULL),(1000020,1000002,NULL),(1000020,1000008,NULL),(1000021,1000000,NULL),(1000021,1000001,NULL),(1000021,1000002,NULL),(1000021,1000004,NULL),(1000021,1000005,NULL),(1000021,1000006,NULL),(1000022,1000001,NULL),(1000022,1000002,NULL),(1000022,1000004,NULL),(1000022,1000005,NULL),(1000022,1000006,NULL),(1000022,1000007,NULL),(1000022,1000008,NULL),(1000023,1000000,NULL),(1000023,1000001,NULL),(1000023,1000002,NULL),(1000023,1000003,NULL),(1000023,1000004,NULL),(1000023,1000005,NULL),(1000024,1000000,NULL),(1000024,1000001,NULL),(1000024,1000002,NULL),(1000024,1000003,NULL),(1000024,1000007,NULL),(1000025,1000001,NULL),(1000025,1000002,NULL),(1000025,1000004,NULL),(1000025,1000005,NULL),(1000025,1000006,NULL),(1000025,1000008,NULL),(1000026,1000000,NULL),(1000026,1000001,NULL),(1000026,1000002,NULL),(1000026,1000005,NULL),(1000026,1000008,NULL),(1000027,1000001,NULL),(1000027,1000002,NULL),(1000027,1000004,NULL),(1000028,1000001,NULL),(1000028,1000002,NULL),(1000028,1000004,NULL),(1000028,1000006,NULL),(1000029,1000001,NULL),(1000029,1000002,NULL),(1000029,1000005,NULL),(1000038,1000009,NULL);
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

-- Dump completed on 2017-04-28 11:35:05
