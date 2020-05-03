insert into login_info(username, email, password) values ('Admin', 'admin@gmail.com','admin12345');
insert into login_info(username, email, password) values ('Jovan', 'jovan@gmail.com','jovan123');

insert into login_info(username, email, password) values ('Kovac', 'Kovac@gmail.com','kovan123');
insert into login_info(username, email, password) values ('Marko', 'Marko@gmail.com','marko123');

insert into user_entity(name, surname, login_info, jmbg, phone_number, user_type) values ('Ivan', 'Ivanović', 1, '0111997710262', '0691408034', 0);
insert into user_entity(name, surname, login_info, jmbg, phone_number, user_type) values ('Jovan', 'Jovanovic', 2, '0111997710210', '0691408000', 1);

insert into user_entity(name, surname, login_info, jmbg, phone_number, user_type) values ('Kovac', 'Milacic', 3, '0111997710211', '0691408034', 2);
insert into user_entity(name, surname, login_info, jmbg, phone_number, user_type) values ('Marko', 'Jovanovic', 4, '0111997710216', '0691408000', 2);


insert into agent(first_login, number_ads, id) values (false, 0, 2);

insert into endentity(activated, admin_approved, blocked, number_of_requests_canceled, id) values (true, true, false, 0, 3);
insert into endentity(activated, admin_approved, blocked, number_of_requests_canceled, id) values (true, true, false, 0, 4);

insert into cardetails(id, code, name, type) values (1, '123', 'BMW', 3);
insert into cardetails(id, code, name, type) values (2, '543', 'Passat', 1);
insert into cardetails(id, code, name, type) values (3, '312', '2', 0);
insert into cardetails(id, code, name, type) values (4, '132', 'Dizel', 2);
insert into cardetails(id, code, name, type) values (5, '321', 'Automatic', 4);

