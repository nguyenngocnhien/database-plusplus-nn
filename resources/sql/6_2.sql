-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `QL_DE_TAI` DEFAULT CHARACTER SET utf8 ;
USE `QL_DE_TAI`;

-- -----------------------------------------------------
-- Table `mydb`.`GIAO_VIEN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GIAO_VIEN` (
  `ma_gv` VARCHAR(45) NOT NULL,
  `ho_ten` VARCHAR(45) NULL,
  `hoc_ham` VARCHAR(45) NULL,
  `hoc_vi` VARCHAR(45) NULL,
  PRIMARY KEY (`ma_gv`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`DE_TAI`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DE_TAI` (
  `ma_de_tai` VARCHAR(45) NOT NULL,
  `ten_de_tai` VARCHAR(45) NULL,
  `linh_vuc` VARCHAR(45) NULL,
  `GIAO_VIEN_ma_gv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ma_de_tai`),
  INDEX `fk_DE_TAI_GIAO_VIEN1_idx` (`GIAO_VIEN_ma_gv` ASC),
  CONSTRAINT `fk_DE_TAI_GIAO_VIEN1`
    FOREIGN KEY (`GIAO_VIEN_ma_gv`)
    REFERENCES `mydb`.`GIAO_VIEN` (`ma_gv`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`SINH_VIEN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SINH_VIEN` (
  `ma_sv` VARCHAR(45) NOT NULL,
  `ho_ten` VARCHAR(45) NULL,
  `ten_lop` VARCHAR(45) NULL,
  PRIMARY KEY (`ma_sv`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`DE_TAI_SINH_VIEN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DE_TAI_SINH_VIEN` (
  `DE_TAI_ma_de_tai` VARCHAR(45) NOT NULL,
  `SINH_VIEN_ma_sv` VARCHAR(45) NOT NULL,
  `truong_nhom` TINYINT NULL,
  PRIMARY KEY (`DE_TAI_ma_de_tai`, `SINH_VIEN_ma_sv`),
  INDEX `fk_DE_TAI_has_SINH_VIEN_SINH_VIEN1_idx` (`SINH_VIEN_ma_sv` ASC),
  INDEX `fk_DE_TAI_has_SINH_VIEN_DE_TAI_idx` (`DE_TAI_ma_de_tai` ASC),
  CONSTRAINT `fk_DE_TAI_has_SINH_VIEN_DE_TAI`
    FOREIGN KEY (`DE_TAI_ma_de_tai`)
    REFERENCES `mydb`.`DE_TAI` (`ma_de_tai`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DE_TAI_has_SINH_VIEN_SINH_VIEN1`
    FOREIGN KEY (`SINH_VIEN_ma_sv`)
    REFERENCES `mydb`.`SINH_VIEN` (`ma_sv`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
