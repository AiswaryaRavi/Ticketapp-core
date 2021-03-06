
CREATE TABLE `USERS` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(40) NOT NULL,
  `EMAIL_ID` VARCHAR(50) NOT NULL,
  `PASSWORD` VARCHAR(30) NOT NULL,
  `ACTIVE` TINYINT(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL_ID` (`EMAIL_ID`)
) ENGINE=INNODB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1




CREATE TABLE `ROLES` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(10) NOT NULL,
  `ACTIVE` TINYINT(1) DEFAULT '1',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `name` (`name`)
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1



CREATE TABLE `PRIORITY` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(10) NOT NULL,
  `ACTIVE` TINYINT(1) DEFAULT '1',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1




CREATE TABLE `TICKET_TRANSACTIONS` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` INT(11) NOT NULL,
  `SUBJECT` VARCHAR(30) NOT NULL,
  `DESCRIPTION` TEXT NOT NULL,
  `PRIORITY_ID` INT(11) NOT NULL,
  `CREATED_DATE` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DEPARTMENT_ID` INT(11) DEFAULT NULL,
  `ASSIGNED_EMPLOYEE_ID` INT(11) DEFAULT NULL,
  `RESOLVED_DATE` DATETIME DEFAULT NULL,
  `STATUS` VARCHAR(30) DEFAULT 'OPEN',
  PRIMARY KEY (`ID`),
  KEY `FK_USER_ID` (`USER_ID`),
  KEY `FK_EMPLOYEE_ID` (`ASSIGNED_EMPLOYEE_ID`),
  KEY `FK1_DEPARTMENT_ID` (`DEPARTMENT_ID`),
  KEY `FK1_PRIORITY_ID` (`PRIORITY_ID`),
  CONSTRAINT `FK1_DEPARTMENT_ID` FOREIGN KEY (`DEPARTMENT_ID`) REFERENCES `DEPARTMENTS` (`ID`),
  CONSTRAINT `FK1_PRIORITY_ID` FOREIGN KEY (`PRIORITY_ID`) REFERENCES `PRIORITY` (`ID`),
  CONSTRAINT `FK_EMPLOYEE_ID` FOREIGN KEY (`ASSIGNED_EMPLOYEE_ID`) REFERENCES `EMPLOYEES` (`ID`),
  CONSTRAINT `FK_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `USERS` (`ID`)
) ENGINE=INNODB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1





CREATE TABLE `EMPLOYEES` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `DEPARTMENT_ID` INT(11) NOT NULL,
  `ROLE_ID` INT(11) NOT NULL,
  `NAME` VARCHAR(30) NOT NULL,
  `EMAIL_ID` VARCHAR(50) NOT NULL,
  `PASSWORD` VARCHAR(30) NOT NULL,
  `STATUS` VARCHAR(30) NOT NULL DEFAULT 'NOT ASSIGNED',
  `ACTIVE` TINYINT(1) DEFAULT '1',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL_ID` (`EMAIL_ID`),
  KEY `FK_DEPARTMENT_ID` (`DEPARTMENT_ID`),
  KEY `FK_ROLE_ID` (`ROLE_ID`),
  CONSTRAINT `FK_DEPARTMENT_ID` FOREIGN KEY (`DEPARTMENT_ID`) REFERENCES `DEPARTMENTS` (`ID`),
  CONSTRAINT `FK_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `ROLES` (`ID`)
) ENGINE=INNODB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1





CREATE TABLE `ISSUE_SOLUTIONS` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `TICKET_ID` INT(11) NOT NULL,
  `SOLUTION` TEXT NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_TICKET_ID` (`TICKET_ID`),
  CONSTRAINT `FK_TICKET_ID` FOREIGN KEY (`TICKET_ID`) REFERENCES `TICKET_TRANSACTIONS` (`ID`)
) ENGINE=INNODB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1




CREATE TABLE `DEPARTMENTS` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(30) NOT NULL,
  `ACTIVE` TINYINT(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1



