DROP SCHEMA IF EXISTS `jd2_project_week3` ;
CREATE SCHEMA IF NOT EXISTS `jd2_project_week3` DEFAULT CHARACTER SET utf8 ;
USE `jd2_project_week3` ;

-- -----------------------------------------------------
-- Table `jd2_project_week3`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jd2_project_week3`.`item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(40) NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jd2_project_week3`.`auditItem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jd2_project_week3`.`auditItem` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `action` VARCHAR(45) NULL,
  `auditItemcol` VARCHAR(45) NULL,
  `item_id` INT NOT NULL,
  `date` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_auditItem_item_idx` (`item_id` ASC),
  CONSTRAINT `fk_auditItem_item`
    FOREIGN KEY (`item_id`)
    REFERENCES `jd2_project_week3`.`item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
