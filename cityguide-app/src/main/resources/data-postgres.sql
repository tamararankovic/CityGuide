insert into users(id, name, surname, email, password, role) values (1, 'Tamara', 'Rankovic', 'isa.mejl.za.usere@gmail.com', '1234', 0);
insert into users(id, name, surname, email, password, role) values (2, 'Tamara', 'Rankovic', 'isa.mejl.za.usere+1@gmail.com', '1234', 1);
insert into users(id, name, surname, email, password, role) values (3, 'Tamara', 'Turkovic', 'isa.mejl.za.usere+2@gmail.com', '1234', 1);
alter sequence users_id_seq restart with 4;