CREATE SCHEMA `users_servlet` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `users_servlet`.`users` (
  `id` VARCHAR(36) NOT NULL,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `salary` DECIMAL(20,2) NULL,
  `dateOfBirth` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
