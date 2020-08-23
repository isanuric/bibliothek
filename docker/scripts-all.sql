-- MySQL dump 10.13  Distrib 8.0.18, for macos10.14 (x86_64)
--
-- Host: 0.0.0.0    Database: bibdb
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=314 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (202,'Author-KSRXB','KSRXB'),(203,'Author-OGFYS','OGFYS'),(204,'Author-AZVWH','AZVWH'),(205,'Author-YHCKO','YHCKO'),(206,'Author-ZOBFB','ZOBFB'),(207,'Author-NXJDR','NXJDR'),(208,'Author-AJZUB','AJZUB'),(209,'Author-MRGGW','MRGGW'),(210,'Author-DPAGZ','DPAGZ'),(211,'Author-ZVXAB','ZVXAB'),(212,'Author-SERQS','SERQS'),(213,'Author-IMMOY','IMMOY'),(214,'Author-WHBSX','WHBSX'),(215,'Author-PQONP','PQONP'),(216,'Author-JGJSS','JGJSS'),(217,'Author-LNTLO','LNTLO'),(218,'Author-GGRXV','GGRXV'),(219,'Author-DKUYR','DKUYR'),(220,'Author-EXLWA','EXLWA'),(221,'Author-THHCM','THHCM'),(222,'Author-MMNCR','MMNCR'),(223,'Author-WJORE','WJORE'),(224,'Author-RJBQF','RJBQF'),(225,'Author-CFWSA','CFWSA'),(226,'Author-PRLIY','PRLIY'),(227,'Author-BXRDK','BXRDK'),(228,'Author-PKZWT','PKZWT'),(229,'Author-BOJKQ','BOJKQ'),(230,'Author-IGSYU','IGSYU'),(231,'Author-JXSHD','JXSHD'),(232,'Author-DXSPJ','DXSPJ'),(233,'Author-QOKWE','QOKWE'),(234,'Author-LYHHN','LYHHN'),(235,'Author-ZIKHY','ZIKHY'),(236,'Author-ZQIKO','ZQIKO'),(237,'Author-FPZWN','FPZWN'),(238,'Author-LTFCR','LTFCR'),(239,'Author-KDQWT','KDQWT'),(240,'Author-SHBBX','SHBBX'),(241,'Author-KKOYU','KKOYU'),(242,'Author-GKKWU','GKKWU'),(243,'Author-ZKBYU','ZKBYU'),(244,'Author-JJQMK','JJQMK'),(245,'Author-YMZLK','YMZLK'),(246,'Author-RXPUG','RXPUG'),(247,'Author-AMKUW','AMKUW'),(248,'Author-JDMRD','JDMRD'),(249,'Author-MKPDH','MKPDH'),(250,'Author-IOOPV','IOOPV'),(251,'Author-ILMLB','ILMLB'),(252,'Author-MVPHH','MVPHH'),(253,'Author-WCSKT','WCSKT'),(254,'Author-KTAPZ','KTAPZ'),(255,'Author-IBDWI','IBDWI'),(256,'Author-SWQSA','SWQSA'),(257,'Author-TQBSA','TQBSA'),(258,'Author-LICEB','LICEB'),(259,'Author-LMUDW','LMUDW'),(260,'Author-NREHV','NREHV'),(261,'Author-NSITZ','NSITZ'),(262,'Author-OTSBW','OTSBW'),(263,'Author-OVAYM','OVAYM'),(264,'Author-QTTAG','QTTAG'),(265,'Author-LWYGI','LWYGI'),(266,'Author-JAKSP','JAKSP'),(267,'Author-FVERR','FVERR'),(268,'Author-OJYCK','OJYCK'),(269,'Author-ZYKOX','ZYKOX'),(270,'Author-PUDRG','PUDRG'),(271,'Author-FVWVO','FVWVO'),(272,'Author-FERNI','FERNI'),(273,'Author-YTCAL','YTCAL'),(274,'Author-LATBR','LATBR'),(275,'Author-FMWJN','FMWJN'),(276,'Author-WFEMB','WFEMB'),(277,'Author-QUHHW','QUHHW'),(278,'Author-FXCFO','FXCFO'),(279,'Author-NLUPL','NLUPL'),(280,'Author-XPMFU','XPMFU'),(281,'Author-UGLOS','UGLOS'),(282,'Author-GFBPE','GFBPE'),(283,'Author-FGUKW','FGUKW'),(284,'Author-WWQYY','WWQYY'),(285,'Author-LEKJW','LEKJW'),(286,'Author-NATHE','NATHE'),(287,'Author-HBHBT','HBHBT'),(288,'Author-NSPYH','NSPYH'),(289,'Author-YKLDV','YKLDV'),(290,'Author-RKENY','RKENY'),(291,'Author-IOYZN','IOYZN'),(292,'Author-FLZSN','FLZSN'),(293,'Author-FETSF','FETSF'),(294,'Author-ZBHXT','ZBHXT'),(295,'Author-SUQQM','SUQQM'),(296,'Author-FJFEM','FJFEM'),(297,'Author-QOXKB','QOXKB'),(298,'Author-CKADD','CKADD'),(299,'Author-TFXOK','TFXOK'),(300,'Author-GJWDN','GJWDN');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorities` (
  `username_ref` varchar(45) NOT NULL,
  `role` varchar(45) DEFAULT NULL,
  `members_username` varchar(45) NOT NULL,
  PRIMARY KEY (`username_ref`,`members_username`),
  KEY `fk_authorities_members_idx` (`members_username`),
  CONSTRAINT `fk_authorities_members` FOREIGN KEY (`members_username`) REFERENCES `members` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `author_id` int DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_book_autor1_idx` (`author_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,212,'Sein und Zeit'),(2,212,'Was heisst Denken?'),(3,212,'Also sprach Zarathustra'),(4,213,'Menschliches Allzumenschliches'),(5,212,'Jenseits von Gut und Bí_se'),(6,213,'Die Chemie des Todes'),(7,213,'Verwesung'),(8,213,'Kalte Asche'),(9,212,'The Antichrist'),(10,212,'Beyond Good and Evil'),(11,212,'Acts of Religion, ed., Gil Anidjar, London: Routledge, 2002.'),(12,212,'Adieu to Emmanuel Levinas, trans., Michael Naas and Pascalle-Anne Brault, Stanford: Stanford University Press, 1999.'),(13,212,'The Animal that Therefore I am, ed., Marie-Loiuse Mallet, trans., David Wills, New York: Fordham University Press, 2008.'),(14,212,'Aporias, trans., Thomas Dutoit, Stanford: Stanford University Press, 1993.'),(15,212,'The Beast and the Sovereignć(Volume 1), trans. Geoffrey Bennigton, Chicago: University of Chicago Press, 2009.'),(16,212,'The Beast and the Sovereignć(Volume 2), trans. Geoffrey Bennington, Chicago: University of Chicago, 2011.'),(17,212,'The Death Penaltyć(Volume 1), trans. Peggy Kamuf, Chicago: University of Chicago Press, 2014.'),(18,212,'The Death Penaltyć(Volume 2), trans. Elizabeth Rottenberg, Chicago: University of Chicago Press, 2017.'),(19,212,'Dissemination, trans., Barbara Johnson, Chicago: University of Chicago Press, 1981.'),(20,212,'The Ear of the Other: Otobiography, Transference, Translation, trans., Peggy Kamuf, New York: Schocken, 1985.'),(21,212,'Eyes of the University: Right to Philosophy 2, Stanford: Stanford University Press, 2004.'),(22,212,'For What Tomorrow  A Dialogue, trans., Jeff Fort Stanford: Stanford University Press, 2004.'),(23,212,'The Gift of Death and Literature in Secret. Second Edition, trans., David Wills, Chicago: University of Chicago Press, 2008.'),(24,212,'Given Time: 1. Counterfeit Money, trans., Peggy Kamuf, Chicago: University of Chicago, 1992.'),(25,212,'Glas, trans., John P. Leavey, Jr. and Richard Rand, Lincoln: University of Nebraska Press, 1986.'),(26,212,'Heidegger: The Question of Being and History, trans., Geoffrey Bennington, Chicago: University of Chicago Press, 2013.'),(27,212,'Learning to Live Finally: The Last Interview, trans., Pacalle-Ann Brault and Michael Naas, Hoboken, NJ: Meilville House Publishing, 2007.'),(28,212,'Limited Inc, trans., Samuel Weber, Evanston: Northwestern University Press, 1988 [1977].'),(29,212,'ńLimited Inc,î inćGlyph 2: Johns Hopkins Textual Studies, 1977, 162_254.'),(30,212,'Margins of Philosophy, trans., Alan Bass, Chicago: University of Chicago Press, 1982.'),(31,212,'Memoirs for Paul de Man, trans., Cecile Lindsay, Jonathan Culler, and Eduardo Cadava, New York: Columbia University Press, 1986.'),(32,212,'Monolinguism of the Other, trans., Patrick Mensah, Stanford: Stanford University Press, 1998.'),(33,212,'Of Grammatology, trans., Gayatri Spivak, Baltimore: The Johns Hopkins University Press, 1974.'),(34,212,'Of Hospitality: Anne Dufourmantelle Invites Jacques Derrida to Respond, trans., Rachel Bowlby, Stanford: Stanford University Press, 2000.'),(35,212,'On the Name, ed., Thomas Dutoit, Stanford: Stanford University Press, 1995.'),(36,212,'Of Spirit, trans., Rachel Bowlby, Chicago: University of Chicago, 1989.'),(37,212,'On Touching _ Jean-Luc Nancy, trans., Christine Irizarry, Stanford: Stanford University Press, 2005.'),(38,212,'Paper Machine, trans., Rachel Bowlby, Stanford: Stanford University Press, 2005.'),(39,212,'Points  Interviews, 1974_1994, trans., Peggy Kamuf and others, Stanford: Stanford University Press, 1995.'),(40,212,'Politics of Friendship, trans., George Collins, London: Verso, 1997.'),(41,212,'Positions, trans., Alan Bass, Chicago: University of Chicago Press, 1981.'),(42,212,'The Postcard from Socrates to Freud and Beyond, trans., Alan Bass, Chicago: University of Chicago Press, 1987.'),(43,212,'The Problem of Genesis in HusserlÍs Philosophy, trans., Marion Hobson, Chicago: University of Chicago Press, 2003.'),(44,212,'Religion, trans., Samuel Weber,. Stanford: Stanford University Press, 1998.'),(45,212,'Rogues, trans., Pascale-Anne Brault and Michael Naas, Stanford: Stanford University Press, 2005.'),(46,212,'ńSignature Event Context,î inćGlyph: Johns Hopkins Textual Studies, 1977, 172_197.'),(47,212,'Sovereignties in Question: The Poetics of Paul Celan, eds., Thomas Dutoit and Outi Pasanen, New York: Fordham University Press, 2005.'),(48,212,'Specters of Marx, trans., Peggy Kamuf, New York: Routledge, 1994.'),(49,212,'Theory and Practice, trans. David Wills, Chicago: University of Chicago Press, 2019.'),(50,212,'The Truth in Painting, trans., Geoff Bennington and Ian McLeod, Chicago: University of Chicago Press, 1987.'),(51,212,'Speech and Phenomena, trans., David B. Allison, Evanston, IL: Northwestern University Press, 1973.'),(52,212,'Voice and Phenomenon, trans., Leonard Lawlor, Evanston, IL: Northwestern University Press, 2011.'),(53,212,'The Work of Mourning, eds., Pascale-Anne Brault and Michael Naas, Chicago: University of Chicago Press, 2001.'),(54,212,'Writing andćDifference, trans., Alan Bass, Chicago: University of Chicago, 1978.'),(55,212,'Test Book I'),(57,212,'Test Book I'),(59,212,'Test Book I'),(61,202,'Test Book I'),(63,202,'Test Book I'),(65,202,'Test Book I'),(67,202,'Test Book I'),(69,202,'Test Book I');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `username` varchar(45) NOT NULL,
  `password` varchar(250) DEFAULT NULL,
  `name` varchar(150) DEFAULT NULL,
  `surname` varchar(150) DEFAULT NULL,
  `email` varchar(250) DEFAULT NULL,
  `enabled` int DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES ('admin-DFSQC','pass-DFSQC','name-DFSQC','DFSQC','admin-DFSQC@gmail.com',1),('admin-HIBCB','pass-HIBCB','name-HIBCB','HIBCB','admin-HIBCB@gmail.com',1),('admin-KJCJT','pass-KJCJT','name-KJCJT','KJCJT','admin-KJCJT@gmail.com',1),('admin-KTEQH','pass-KTEQH','name-KTEQH','KTEQH','admin-KTEQH@gmail.com',1),('admin-LLSKX','pass-LLSKX','name-LLSKX','LLSKX','admin-LLSKX@gmail.com',1),('admin-LYYJV','pass-LYYJV','name-LYYJV','LYYJV','admin-LYYJV@gmail.com',1),('admin-NCQMH','pass-NCQMH','name-NCQMH','NCQMH','admin-NCQMH@gmail.com',1),('admin-NKAWT','pass-NKAWT','name-NKAWT','NKAWT','admin-NKAWT@gmail.com',1),('admin-NPRLH','pass-NPRLH','name-NPRLH','NPRLH','admin-NPRLH@gmail.com',1),('admin-OCEJD','pass-OCEJD','name-OCEJD','OCEJD','admin-OCEJD@gmail.com',1),('admin-PAMDS','pass-PAMDS','name-PAMDS','PAMDS','admin-PAMDS@gmail.com',1),('admin-QOFLD','pass-QOFLD','name-QOFLD','QOFLD','admin-QOFLD@gmail.com',1),('admin-RCXKH','pass-RCXKH','name-RCXKH','RCXKH','admin-RCXKH@gmail.com',1),('admin-UCBZJ','pass-UCBZJ','name-UCBZJ','UCBZJ','admin-UCBZJ@gmail.com',1),('admin-VKQNI','pass-VKQNI','name-VKQNI','VKQNI','admin-VKQNI@gmail.com',1),('admin-XUKGP','pass-XUKGP','name-XUKGP','XUKGP','admin-XUKGP@gmail.com',1),('admin-YTUXI','pass-YTUXI','name-YTUXI','YTUXI','admin-YTUXI@gmail.com',1),('admin-YWRHC','pass-YWRHC','name-YWRHC','YWRHC','admin-YWRHC@gmail.com',1),('admin-ZROCB','pass-ZROCB','name-ZROCB','ZROCB','admin-ZROCB@gmail.com',1),('adminOne','pass','admin','one','admim@gmx.com',1),('userthree','pass',NULL,NULL,'userthree@gmail.com',1);
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-23 17:09:20
