create database if not exists BDDContacts3;

use BDDContacts3;

drop table if exists utilisateurs;

CREATE TABLE `utilisateurs` (
    `id_utilisateurs` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(64) UNIQUE,
    PRIMARY KEY (`id_utilisateurs`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `utilisateurs` (`id_utilisateurs`,`name`) VALUES (1, 'Samsung');
INSERT INTO `utilisateurs` (`id_utilisateurs`,`name`) VALUES (2, 'Ipad');
INSERT INTO `utilisateurs` (`id_utilisateurs`,`name`) VALUES (3, 'Montre_connecte');

drop table if exists Contacts;

CREATE TABLE `Contacts` (
	`id_contacts` int(11) NOT NULL AUTO_INCREMENT,
	`last_name` varchar(64) DEFAULT NULL,
	`first_name` varchar(64) DEFAULT NULL,
	`number` varchar(64) DEFAULT NULL,
	`id_utilisateurs` int(11) DEFAULT NULL,
  FOREIGN KEY (`id_utilisateurs`) REFERENCES utilisateurs(`id_utilisateurs`),
  PRIMARY KEY (`id_contacts`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `Contacts` (`last_name`,`first_name`,`number`,`id_utilisateurs`) VALUES ('CHAUVIN','Louis','0781506059',1);
INSERT INTO `Contacts` (`last_name`,`first_name`,`number`,`id_utilisateurs`) VALUES ('DESAINT','Erwan','0675757575',2);
INSERT INTO `Contacts` (`last_name`,`first_name`,`number`,`id_utilisateurs`) VALUES ('GRONDIN','Olivia','0685858585',3);
INSERT INTO `Contacts` (`last_name`,`first_name`,`number`,`id_utilisateurs`) VALUES ('RHOUDRY','Hamza','0657575757',1);