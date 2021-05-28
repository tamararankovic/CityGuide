insert into users(id, name, surname, email, password, role) values (1, 'Tamara', 'Rankovic', 'email1', '81dc9bdb52d04dc2036dbd8313ed055', 0) on conflict do nothing;
insert into users(id, name, surname, email, password, role) values (2, 'Tamara', 'Rankovic', 'email2', '81dc9bdb52d04dc2036dbd8313ed055', 1) on conflict do nothing;
insert into users(id, name, surname, email, password, role) values (3, 'Tamara', 'Turkovic', 'email3', '81dc9bdb52d04dc2036dbd8313ed055', 1) on conflict do nothing;
alter sequence users_id_seq restart with 4;

insert into location_type(id, name) values (1, 'park') on conflict do nothing;
alter sequence location_type_id_seq restart with 2;

insert into location_type_features(location_type_id, features) values (1, 4) on conflict do nothing;
insert into location_type_features(location_type_id, features) values (1, 6) on conflict do nothing;

insert into location(id, name, address, description, type_id) values (1, 'dunavski park', '', '', 1) on conflict do nothing;
alter sequence location_id_seq restart with 2;

insert into location_created(id, location_id, created) values (1, 1, '2021-02-25 08:00:00.000000') on conflict do nothing;
alter sequence location_created_id_seq restart with 2;