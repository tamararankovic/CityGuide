insert into users(id, name, surname, email, password, role) values (1, 'Tamara', 'Rankovic', 'isa.mejl.za.usere@gmail.com', '1234', 0);
insert into users(id, name, surname, email, password, role) values (2, 'Tamara', 'Rankovic', 'isa.mejl.za.usere+1@gmail.com', '1234', 1);
insert into users(id, name, surname, email, password, role) values (3, 'Tamara', 'Turkovic', 'isa.mejl.za.usere+2@gmail.com', '1234', 1);
alter sequence users_id_seq restart with 4;

insert into location(id, name, address, description) values (1, 'dunavski park', '', '');
alter sequence location_id_seq restart with 2;

insert into location_created(id, location_id, created) values (1, 1, '2021-02-25 08:00:00.000000');
alter sequence location_created_id_seq restart with 2;