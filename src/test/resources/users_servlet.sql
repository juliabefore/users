CREATE SCHEMA `users_servlet` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `users_servlet`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `salary` DECIMAL(20,2) NULL,
  `dateOfBirth` DATE NOT NULL,
  PRIMARY KEY (`id`));

insert into users(firstName, lastName, salary, dateOfBirth)
values ('firstName1', 'lastName1', 100.20, date'2010-01-01');
insert into users(firstName, lastName, salary, dateOfBirth)
values ('firstName2', 'lastName2', 4400.50, date'2010-01-02');
insert into users(firstName, lastName, salary, dateOfBirth)
values ('firstName3', 'lastName3', null, date'2010-01-03');
insert into users(firstName, lastName, salary, dateOfBirth)
values ('firstName4', 'lastName4', 10.00, date'2010-01-04');
insert into users(firstName, lastName, salary, dateOfBirth)
values ('firstName5', 'lastName5', 47.30, date'2010-01-05');
insert into users(firstName, lastName, salary, dateOfBirth)
values ('firstName6', 'lastName6', 40027.30, date'2010-01-06');
