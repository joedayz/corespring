/*
SQLyog Community v8.61 
MySQL - 5.5.10 : Database - online
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`online` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `online`;

/*Table structure for table `authorities` */

DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `authorities` */

insert  into `authorities`(`username`,`authority`) values ('emunoz','ROLE_ADMIN'),('user','ROLE_MEMBER');

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id_category` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `code` int(11) NOT NULL,
  PRIMARY KEY (`id_category`),
  KEY `id_category` (`id_category`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `category` */

insert  into `category`(`id_category`,`name`,`code`) values (1,'REPRODUCTOR MULTIMEDIA',1),(2,'TELEFONO CELULAR',2),(3,'TABLET',3),(4,'NOTEBOOK',4);

/*Table structure for table `perfil` */

DROP TABLE IF EXISTS `perfil`;

CREATE TABLE `perfil` (
  `cod_perfil` varchar(40) NOT NULL,
  `des_perfil` varchar(40) NOT NULL,
  PRIMARY KEY (`cod_perfil`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `perfil` */

insert  into `perfil`(`cod_perfil`,`des_perfil`) values ('ROLE_USER','ROLE_USER');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id_product` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `code` varchar(50) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `id_category` int(11) NOT NULL,
  `description` varchar(250) NOT NULL,
  PRIMARY KEY (`id_product`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

/*Data for the table `product` */

insert  into `product`(`id_product`,`name`,`code`,`price`,`id_category`,`description`) values (4,'BLACKBERRY TORCH 9800','BB9800','1567.90',2,'BLACKBERRY TORCH 9800'),(6,'IPAD 2 WIFI 3G 16GB','IPAD2-W3G-16G','1799.00',1,'TABLET DE APPLE CON CAPACIDAD DE CONEXION WIFI Y 3G. CAPACIDAD DE 16GB'),(7,'IPAD WIFI 3G 16GB','FF','999.00',2,'FF'),(12,'BLACKBERRY CURVE 8520','BB8520','99999999.99',2,'BLACKBERRY CURVE 8520'),(13,'LENOVO LENOVO K1 16GB WIFI','LENOVOK126GB','1599.00',3,'TABLET LENOVO K1 16GB WIFI'),(14,'TOSHIBA THRIVE 16GB WIFI','TOTH16GBWI','1499.00',2,'TABLET DE TOSHIBA DE 16GB CON WIFI'),(15,'TOSHIBA SATELLITE','TOSHSAT 2','4444.00',4,'NOTEBOOK TOSHIBA SATELLITE  2'),(16,'PLAYSTATION 3 160GB','PLAY3160GB','1299.00',1,'PLAY STATION 3 160GB AZUL METALICO'),(17,'LENOVO K1 32GB WIFI','LENOVOK1-32GB','1599.11',3,'TABLET LENOVO IDEAPD K1 32 GB'),(18,'BLACKBERRY 98601','BB9860','1200.13',2,'BB9860'),(19,'BLACKBERRY TORCH 9801','11','11.00',1,'11'),(20,'BLACKBERRY TORCH 9880','BB9800','1567.90',2,'BLACKBERRY TORCH 9800 - de oferta!!!');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`username`,`password`,`enabled`) values ('emunoz','emunoz',1),('user','user',1);

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `cod_usuario` varchar(40) NOT NULL,
  `des_password` varchar(40) NOT NULL,
  `in_cuenta_expirada` char(1) NOT NULL DEFAULT 'N',
  `in_cuenta_bloqueada` char(1) NOT NULL DEFAULT 'N',
  `in_credencial_expirada` char(1) NOT NULL DEFAULT 'N',
  `in_habilitado` char(1) NOT NULL DEFAULT 'S',
  PRIMARY KEY (`cod_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `usuario` */

insert  into `usuario`(`cod_usuario`,`des_password`,`in_cuenta_expirada`,`in_cuenta_bloqueada`,`in_credencial_expirada`,`in_habilitado`) values ('ingas','123456','N','N','N','S');

/*Table structure for table `usuario_perfil` */

DROP TABLE IF EXISTS `usuario_perfil`;

CREATE TABLE `usuario_perfil` (
  `cod_usuario` varchar(40) NOT NULL DEFAULT '',
  `cod_perfil` varchar(40) NOT NULL DEFAULT '',
  PRIMARY KEY (`cod_usuario`,`cod_perfil`),
  KEY `cod_perfil` (`cod_perfil`),
  CONSTRAINT `usuario_perfil_ibfk_1` FOREIGN KEY (`cod_usuario`) REFERENCES `usuario` (`cod_usuario`),
  CONSTRAINT `usuario_perfil_ibfk_2` FOREIGN KEY (`cod_perfil`) REFERENCES `perfil` (`cod_perfil`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `usuario_perfil` */

insert  into `usuario_perfil`(`cod_usuario`,`cod_perfil`) values ('ingas','ROLE_USER');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
