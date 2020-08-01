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
CREATE SCHEMA IF NOT EXISTS QL_HANG DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`MAT_HANG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MAT_HANG` (
  `ma_hang` VARCHAR(45) NOT NULL,
  `ten_hang` VARCHAR(45) NULL,
  `don_vi_tinh` VARCHAR(45) NULL,
  `so_luong_hien_co` INT NULL,
  `gia_ban_hien_tai` FLOAT NULL,
  PRIMARY KEY (`ma_hang`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`PHIEU_NHAP`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PHIEU_NHAP` (
  `so_phieu` INT NOT NULL,
  `ngay_nhap` DATE NOT NULL,
  `ten_nha_cung_cap` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`so_phieu`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`PHIEU_XUAT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PHIEU_XUAT` (
  `so_phieu_xuat` INT NOT NULL,
  `ngay_ban` DATE NOT NULL,
  `ten_nguoi_mua_hang` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`so_phieu_xuat`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`NHAP_HANG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `NHAP_HANG` (
  `ma_hang` VARCHAR(45) NOT NULL,
  `so_phieu` INT NOT NULL,
  `sl_nhap` INT NULL,
  `don_gia_nhap` FLOAT NULL,
  PRIMARY KEY (`ma_hang`, `so_phieu`),
  INDEX `fk_MAT_HANG_has_PHIEU_NHAP_PHIEU_NHAP1_idx` (`so_phieu` ASC),
  INDEX `fk_MAT_HANG_has_PHIEU_NHAP_MAT_HANG1_idx` (`ma_hang` ASC),
  CONSTRAINT `fk_MAT_HANG_has_PHIEU_NHAP_MAT_HANG1`
    FOREIGN KEY (`ma_hang`)
    REFERENCES `mydb`.`MAT_HANG` (`ma_hang`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MAT_HANG_has_PHIEU_NHAP_PHIEU_NHAP1`
    FOREIGN KEY (`so_phieu`)
    REFERENCES `mydb`.`PHIEU_NHAP` (`so_phieu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`XUAT_HANG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `XUAT_HANG` (
  `ma_hang` VARCHAR(45) NOT NULL,
  `so_phieu_xuat` INT NOT NULL,
  `sl_xuat` INT NULL,
  `don_gia_xuat` FLOAT NULL,
  PRIMARY KEY (`ma_hang`, `so_phieu_xuat`),
  INDEX `fk_MAT_HANG_has_HOA_DON_XUAT_HOA_DON_XUAT1_idx` (`so_phieu_xuat` ASC),
  INDEX `fk_MAT_HANG_has_HOA_DON_XUAT_MAT_HANG1_idx` (`ma_hang` ASC),
  CONSTRAINT `fk_MAT_HANG_has_HOA_DON_XUAT_MAT_HANG1`
    FOREIGN KEY (`ma_hang`)
    REFERENCES `mydb`.`MAT_HANG` (`ma_hang`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MAT_HANG_has_HOA_DON_XUAT_HOA_DON_XUAT1`
    FOREIGN KEY (`so_phieu_xuat`)
    REFERENCES `mydb`.`PHIEU_XUAT` (`so_phieu_xuat`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
