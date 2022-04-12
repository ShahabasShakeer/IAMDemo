USE `iam_demo`;

DROP TABLE IF EXISTS `quote`;
DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL UNIQUE,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: http://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--

/*
INSERT INTO `users` 
VALUES 
(1, 'john','{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1),
(2, 'mary','{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1),
(3, 'susan','{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1),
(4, 'jacob','{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1),
(5, 'damon','{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1);
*/

INSERT INTO `users` 
VALUES 
(1, 'jacob','{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1);


--
-- Table structure for table `authorities`
--


CREATE TABLE `authorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authorities`
--



CREATE TABLE `quote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(256) NOT NULL,
  `approved` tinyint(1) DEFAULT 0,
  `user_id` int(11) DEFAULT NULL,

  PRIMARY KEY (`id`),

  KEY `FK_USER_ID_idx` (`user_id`),

  CONSTRAINT `FK_USER` 
  FOREIGN KEY (`user_id`) 
  REFERENCES `users` (`id`) 

  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*
INSERT INTO `authorities` 
VALUES 
(1, 'john','ROLE_WRITER'),
(2, 'mary','ROLE_WRITER'),
(3, 'susan','ROLE_APPROVER'),
(4, 'jacob','ROLE_ADMIN'),
(5, 'damon','ROLE_WRITER');
*/

INSERT INTO `authorities` 
VALUES 
(1, 'jacob','ROLE_ADMIN');

/*
CREATE TABLE `users_authorities` (
  `user_id` int(11) NOT NULL,
  `authority_id` int(11) NOT NULL,
  
  PRIMARY KEY (`authority_id`,`user_id`),
  
  KEY `FK_USER_idx` (`user_id`),
  
  CONSTRAINT `FK_AUTHORITY_05` FOREIGN KEY (`authority_id`) 
  REFERENCES `authorities` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_JOINTABLE_USER` FOREIGN KEY (`user_id`) 
  REFERENCES `users` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
*/