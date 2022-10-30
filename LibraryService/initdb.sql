CREATE SCHEMA `library` ;

CREATE TABLE `library`.`Studyprogram` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `studyname` VARCHAR(100) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `library`.`Books` (
  `idBooks` INT NOT NULL AUTO_INCREMENT,
  `stock` INT NULL,
  `studyprogramid` INT NULL,
  `price` FLOAT NOT NULL,
  PRIMARY KEY (`idBooks`),
  INDEX `studyprogramid_idx` (`studyprogramid` ASC) VISIBLE,
  CONSTRAINT `studyprogramid`
    FOREIGN KEY (`studyprogramid`)
    REFERENCES `library`.`Studyprogram` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `library`.`Students` (
  `studentid` INT NOT NULL AUTO_INCREMENT,
  `wallet` FLOAT NULL,
  `studentname` VARCHAR(100) NULL,
  PRIMARY KEY (`studentid`));


CREATE TABLE `library`.`StudentBookRel` (
  `relid` INT NOT NULL AUTO_INCREMENT,
  `studentid` INT NULL,
  `bookid` INT NULL,
  PRIMARY KEY (`relid`),
  INDEX `studentid_idx` (`studentid` ASC) VISIBLE,
  INDEX `bookid_idx` (`bookid` ASC) VISIBLE,
  CONSTRAINT `studentid`
    FOREIGN KEY (`studentid`)
    REFERENCES `library`.`Students` (`studentid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `bookid`
    FOREIGN KEY (`bookid`)
    REFERENCES `library`.`Books` (`idBooks`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `library`.`Students` 
ADD COLUMN `programid` INT NULL AFTER `studentname`,
ADD INDEX `programid_idx` (`programid` ASC) VISIBLE;
;
ALTER TABLE `library`.`Students` 
ADD CONSTRAINT `programid`
  FOREIGN KEY (`programid`)
  REFERENCES `library`.`Studyprogram` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


INSERT INTO library.Studyprogram (studyname) VALUES ("banking");
INSERT INTO library.Studyprogram (studyname) VALUES ("software development");

INSERT INTO library.Students (wallet, studentname, programid) VALUES (100.5, "Jens Larsen", 1);
INSERT INTO library.Students (wallet, studentname, programid) VALUES (10.95, "Hans Hansen", 2);

INSERT INTO library.Books (stock, studyprogramid, price) VALUES (45, 1, 55.95);
INSERT INTO library.Books (stock, studyprogramid, price) VALUES (2, 2, 31.95);
INSERT INTO library.Books (stock, studyprogramid, price) VALUES (99, 2, 391.95);

INSERT INTO library.StudentBookRel (studentid, bookid) VALUES (1, 1);






