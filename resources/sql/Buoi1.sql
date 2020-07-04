create database if not exists student_cms_plusplus default character set=utf8mb4;
use student_cms_plusplus;
-- id (PK, AI), name, mssv, password, phone, address, age, email, created_timstamp, last_updated_timestamp
CREATE TABLE student(
	`id` INT AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(45) NOT NULL,
	`mssv` VARCHAR(15) NOT NULL,
	`password` VARCHAR(45) NOT NULL,
	`phone` VARCHAR(20) NOT NULL,
	`address` VARCHAR(100) NOT NULL,
	`age` INT NOT NULL,
	`email` VARCHAR(50) NOT NULL,
	`created_timestamp` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`last_updated_timestamp`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
-- id (PK, AI), name, major, total_student, teacher_name, teacher_phone, created_timestamp, last_updated_timestamp
create table class(
	`id` int auto_increment primary key,
    `name` varchar(45),
    `major` varchar(55),
    `total_student` int,
	`teacher_name` varchar(45),
    `created_timestamp` timestamp default current_timestamp on update current_timestamp,
    `last_updated_timestamp` timestamp default current_timestamp on update current_timestamp
);
-- student_id, class_id
create table student_class(
	`student_id` int,
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES student(id),
    `class_id`	int,
    CONSTRAINT fk_class FOREIGN KEY (class_id) REFERENCES class(id)
);
INSERT INTO `student_cms_plusplus`.`student` (`id`, `name`, `mssv`, `password`, `phone`, `address`, `age`, `email`, `created_timestamp`, `last_updated_timestamp`)
	VALUES ('1', 'Nguyễn Ngọc Nhiên', '1620', 'nnhien', '0394023098', 'Hồ Chí Minh', '22', 'nguyenngocnhien98@gmail.com', '2016-3-1', '2019-2-1');
INSERT INTO `student_cms_plusplus`.`student` (`id`, `name`, `mssv`, `password`, `phone`, `address`, `age`, `email`, `created_timestamp`, `last_updated_timestamp`)
	VALUES ('2', 'Phan Huy Phụng', '2731', 'pphung', '0382318381', 'Đà Nẵng', '21', 'phanhuyphung@gmail.com', '2018-6-2', '2020-5-4');
INSERT INTO `student_cms_plusplus`.`class` (`id`, `name`, `major`, `total_student`, `teacher_name`, `created_timestamp`, `last_updated_timestamp`)
	VALUES ('3', 'Quản Trị Kinh Doanh', 'Quản Trị Kinh Doanh', '28', 'Nguyễn Văn Huy', '2019-3-3', '2019-5-2');
INSERT INTO `student_cms_plusplus`.`class` (`id`, `name`, `major`, `total_student`, `teacher_name`, `created_timestamp`, `last_updated_timestamp`)
	VALUES ('4', 'Ứng dụng phần mềm', 'Công Nghệ Thông Tin', '27', 'Nguyễn Xuân Kiểm', '2020-6-10', '2020-10-30');
INSERT INTO `student_cms_plusplus`.`student_class` values(1,3);
INSERT INTO `student_cms_plusplus`.`student_class` values(2,4);