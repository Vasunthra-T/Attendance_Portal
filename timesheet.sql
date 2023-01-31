create table employee 
(
id int primary key,
emp_name varchar(40),
emp_code varchar(30),
phone bigint,
email varchar (40),
manager_id varchar(30),
role varchar(30),
password varchar(30)
);

insert into  employee (id,emp_name,emp_code,phone,email,manager_id,role,password)
values (1,'Vasunthra','E101', 9833289385,'vasunthra@gmail.com','E114','Developer','vasu@123'),
  (2,'Archana Devi','E102', 9831829195,'archanadevi@gmail.com','E119','Developer','archana!'),
  (3,'Kowreesh','E103', 6331289385,'kowreesh@gmail.com','E115','Developer','kowreesh890'),
  (4,'Harish','E104', 8883881921,'harish@gmail.com','E114','Developer','harish123'),
  (5,'Ruthsan','E105', 6374991710,'ruthsan@gmail.com','E113','Developer','ruthsan@12'),
  (6,'Hariharan','E106', 7990211225,'hariharan@gmail.com','E119','Developer','hariharan456'),
  (7,'Elakiya','E107', 8887166892,'elakiya@gmail.com','E120','QA','elaki@01'),
  (8,'Jaisri','E108', 8892391829,'jaisri@gmail.com','E116','Developer','jaisri90'),
  (9,'Vishnupriya','E109', 8823790192,'vishnupriya@gmail.com','E119','Developer','priya11'),
  (10,'Srinidhi','E110', 7723419091,'srinidhi@gmail.com','E114','QA','srinidhi!'),
  (11,'Brundha','E111', 6678912918,'brundha@gmail.com','E116','Developer','brundha23'),
  (12,'Suthir','E112', 9734892210,'suthir@gmail.com','E115','Developer','suthir24'),
  (13,'Ashok','E113', 7722189712,'ashok@gmail.com','','Manager','ashok67'),
  (14,'Mani','E114', 8891087189,'mani@gmail.com','E113','Manager','mani@15'),
  (15,'Jayaram','E115', 8880001112,'jayaram@gmail.com','E113','Manager','jayaram78'),
  (16,'Saravana','E116', 8722100918,'saravan@gmail.com','E113','Manager','saravana9'),
  (17,'Abinesh','E117', 9993320091,'abinesh@gmail.com','E119','Developer','abinesh77'),
  (18,'Vikasini','E118', 7989218399,'vikasini@gmail.com','E114','Developer','vikasini09'),
  (19,'Deepak','E119', 7789163791,'deepak@gmail.com','E113','Manager','deepak88'),
  (20,'Sivaram','E120', 6678219082,'sivaram@gmail.com','E113','Manager','siva@14'),
  (21,'Neela Varshini','E121', 9903348920,'neelavarshini@gmail.com','E116','QA','neela99');

  
