/*
SQLyog Community v8.61 
MySQL - 5.5.10 : Database - blogs
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blogs` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `blogs`;

/*Table structure for table `comentario` */

DROP TABLE IF EXISTS `comentario`;

CREATE TABLE `comentario` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(50) NOT NULL,
  `CORREO_ELECTRONICO` varchar(50) NOT NULL,
  `COMENTARIO` text NOT NULL,
  `ID_POST` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_POST` (`ID_POST`),
  CONSTRAINT `FK_COMENTARIO_POST` FOREIGN KEY (`ID_POST`) REFERENCES `post` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `comentario` */

insert  into `comentario`(`ID`,`NOMBRE`,`CORREO_ELECTRONICO`,`COMENTARIO`,`ID_POST`) values (1,'rishishehrawat','rishishehrawat@mail.com','From what you have described it seems like the insertion using JPA & select in SP are being done in different transactions. This is the reason data inserted by JPA is not visible in SP. ',2),(2,'Susan Inga','ic.susan@gmail.com','Not use JPA.',2),(3,'rishishehrawat','rishishehrawat@mail.com','The only way to make this work is\r\n\r\n1.) Either both operations are done in same transaction (uncommitted data is visible only in same transaction, unless you are running the transaction with READ_UNCOMMITTED isolation level)\r\n2.) The JPA transaction that has inserted should have already committed its transaction before the SP goes & reterieves on ID\r\n\r\nIf both JPA & SP are running in 2 seperate transactions then the JPA insert will not be visible to SP select. ',2),(4,'Susan Inga','ic.susan@gmail.com','Not JPAAAAAAA',2),(5,'Susan Inga','ic.susan@gmail.com','JPAAAAAAAAAAAA',2),(6,'Susan Inga','ic.susan@gmail.com','Nooooooooooooooooooooo',2),(7,'Susan Inga','ic.susan@gmail.com','wiiiiiiiiiiiii',2),(8,'Susan Inga','ic.susan@gmail.com','weeeeeeeeeeeeeeeeee',2),(9,'Susan Inga','ic.susan@gmail.com','sssssssssssssssssss',2),(10,'Susan Inga','ic.susan@gmail.com','lalallala',1),(11,'Susan Inga','ic.susan@gmail.com','gfhfgfhg',2),(12,'rtyrty','rtyrt','yrtytryry',1),(13,'rtyrty','rtyrt','yrtyrtyrty',1),(14,'wrwer','erwerwr','werwerwer',1),(15,'werwer','dfgdfg','wwewe',1);

/*Table structure for table `post` */

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TITULO` varchar(50) NOT NULL,
  `DESCRIPCION` text NOT NULL,
  `ID_TEMA` bigint(20) NOT NULL,
  `FECHA_CREACION` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_TEMA` (`ID_TEMA`),
  CONSTRAINT `FK_POST_TEMA` FOREIGN KEY (`ID_TEMA`) REFERENCES `tema` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `post` */

insert  into `post`(`ID`,`TITULO`,`DESCRIPCION`,`ID_TEMA`,`FECHA_CREACION`) values (1,'Issue with basic dependency injection','Hi Guys,\r\n\r\nI have a scenario where I need to inject values to the Arraylist in a class that does not have the setter I believe in this scenario I need to do a get on the list in the POC class and then do a add:\r\n\r\npublic class POC {\r\n\r\nprivate ArrayList<String> beheaviour=new ArrayList<String>();\r\n\r\npublic ArrayList<String> getBeheaviour() {\r\nreturn beheaviour;\r\n}\r\n\r\n\r\n}\r\nHere is the xml mapping code :\r\n\r\n<bean id=\"poc\" class=\"outBoundocument.factory.POC\">\r\n<property name=\"beheaviour\">\r\n<list>\r\n<value>temp1</value>\r\n<value>temp2</value>\r\n<value>temp3</value>\r\n<value> temp4</value>\r\n<value>temp5</value>\r\n</list>\r\n</property>\r\n</bean>\r\n\r\nthe following code returns :\r\n\r\nError setting property values; nested exception is org.springframework.beans.NotWritablePropertyExcep tion: Invalid property beheaviour of bean class [outBoundocument.factory.POC]: Bean property beheaviour is not writable or has an invalid setter method. Does the parameter type of the setter match the return type of the getter?\r\n\r\n\r\nMoreover there is no way for me to modify the POC class so I will have to perform a property Injection some how. The Java translation of the above is as simple as\r\n\r\nPOC poc =new POC ();\r\npoc.getBeheaviour.add(\"crap\") ',1,'2011-05-23 19:50:49'),(2,'Spring transaction with JPA and stored procedure','HI,\r\n\r\nWe are using jpa with spring transaction. (Oracle database)\r\n\r\nWithin a transaction i am inserting record in one table(using jpa) then i am passing the id to stored procedure (spring storedprocedure handler). But inside the stored procedure, the data cant retrieved. because, no record exists with this id on that table.\r\n\r\nNote: Insertion is happening with jpa and stored procedure is being called using spring storedprocedure handler.\r\n\r\nPlease guide me to go ahead. Anything i need to do with transactions and its configurations.\r\n\r\nThanks in advance ',3,'2011-05-23 19:51:30');

/*Table structure for table `tema` */

DROP TABLE IF EXISTS `tema`;

CREATE TABLE `tema` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DESCRIPCION` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `tema` */

insert  into `tema`(`ID`,`DESCRIPCION`) values (1,'Container'),(2,'AOP'),(3,'Data'),(4,'Security'),(5,'Web'),(6,'Web Services'),(7,'Integration'),(8,'Batch'),(9,'SpringSource Tool Suite'),(10,'ROO');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
