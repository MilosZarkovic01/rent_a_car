/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 10.4.27-MariaDB : Database - rent_a_car
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rent_a_car` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `rent_a_car`;

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `email` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `administrator` */

insert  into `administrator`(`id`,`username`,`password`,`email`) values 
(1,'admin','admin','admin@gmai');

/*Table structure for table `client` */

DROP TABLE IF EXISTS `client`;

CREATE TABLE `client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(15) NOT NULL,
  `lastname` varchar(15) NOT NULL,
  `telNumber` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `client` */

insert  into `client`(`id`,`firstname`,`lastname`,`telNumber`) values 
(1,'Milos','Zarkovic','0638195893'),
(2,'Marko','Ilic','0657855361'),
(3,'Ana','Markovic','0669149860');

/*Table structure for table `pdv` */

DROP TABLE IF EXISTS `pdv`;

CREATE TABLE `pdv` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `percent` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `pdv` */

insert  into `pdv`(`id`,`percent`) values 
(1,0.18),
(2,0.2),
(3,0.32);

/*Table structure for table `pricelist` */

DROP TABLE IF EXISTS `pricelist`;

CREATE TABLE `pricelist` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dateFrom` date NOT NULL,
  `dateTo` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `pricelist` */

insert  into `pricelist`(`id`,`dateFrom`,`dateTo`) values 
(1,'2022-12-01','2022-12-15'),
(2,'2022-12-16','2022-12-19');

/*Table structure for table `pricelistitem` */

DROP TABLE IF EXISTS `pricelistitem`;

CREATE TABLE `pricelistitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `typeOfPriceListItem` varchar(10) NOT NULL,
  `currency` varchar(3) NOT NULL,
  `pdv_fk` bigint(20) NOT NULL,
  `typeOfVehicle_fk` bigint(20) NOT NULL,
  `priceList_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`priceList_id`),
  KEY `pdv_fk` (`pdv_fk`),
  KEY `typeOfVehicle_fk` (`typeOfVehicle_fk`),
  CONSTRAINT `pricelistitem_ibfk_1` FOREIGN KEY (`pdv_fk`) REFERENCES `pdv` (`id`),
  CONSTRAINT `pricelistitem_ibfk_2` FOREIGN KEY (`typeOfVehicle_fk`) REFERENCES `typeofvehicle` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `pricelistitem` */

insert  into `pricelistitem`(`id`,`price`,`typeOfPriceListItem`,`currency`,`pdv_fk`,`typeOfVehicle_fk`,`priceList_id`) values 
(1,1200,'PERHOUR','RSD',2,2,1),
(2,25,'PERHOUR','EUR',2,2,2),
(3,89,'PERDAY','EUR',1,1,2),
(4,30,'PERHOUR','EUR',3,3,2);

/*Table structure for table `renting` */

DROP TABLE IF EXISTS `renting`;

CREATE TABLE `renting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dateFrom` date NOT NULL,
  `dateTo` date NOT NULL,
  `totalAmount` double NOT NULL,
  `currency` varchar(3) NOT NULL,
  `vehicle_fk` bigint(20) NOT NULL,
  `client_fk` bigint(20) NOT NULL,
  `priceListItem_fk` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `vehicle_fk` (`vehicle_fk`),
  KEY `client_fk` (`client_fk`),
  KEY `priceListItem_fk` (`priceListItem_fk`),
  CONSTRAINT `renting_ibfk_1` FOREIGN KEY (`vehicle_fk`) REFERENCES `vehicle` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `renting_ibfk_2` FOREIGN KEY (`client_fk`) REFERENCES `client` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `renting_ibfk_3` FOREIGN KEY (`priceListItem_fk`) REFERENCES `pricelistitem` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `renting` */

insert  into `renting`(`id`,`dateFrom`,`dateTo`,`totalAmount`,`currency`,`vehicle_fk`,`client_fk`,`priceListItem_fk`) values 
(1,'2022-12-13','2022-12-14',180,'EUR',2,1,3);

/*Table structure for table `typeofvehicle` */

DROP TABLE IF EXISTS `typeofvehicle`;

CREATE TABLE `typeofvehicle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `typeofvehicle` */

insert  into `typeofvehicle`(`id`,`name`) values 
(1,'car'),
(2,'motorcycle'),
(3,'truck');

/*Table structure for table `vehicle` */

DROP TABLE IF EXISTS `vehicle`;

CREATE TABLE `vehicle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand` varchar(15) NOT NULL,
  `model` varchar(15) NOT NULL,
  `mileage` int(11) NOT NULL,
  `availability` tinyint(1) NOT NULL,
  `typeOfVehicle_fk` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `typeOfVehicle_fk` (`typeOfVehicle_fk`),
  CONSTRAINT `vehicle_ibfk_1` FOREIGN KEY (`typeOfVehicle_fk`) REFERENCES `typeofvehicle` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `vehicle` */

insert  into `vehicle`(`id`,`brand`,`model`,`mileage`,`availability`,`typeOfVehicle_fk`) values 
(1,'Mercedes-Benz','Maybach',88000,1,1),
(2,'BMW','X6',42000,0,1),
(3,'Suzuki','Hayabusa',17000,1,2),
(4,'Honda','Ridgeline',111000,1,3),
(5,'Toyota','Tundra',9000,0,3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
