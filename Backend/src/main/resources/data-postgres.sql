insert into login_info(username, email, password) values ('Admin', 'admin@gmail.com','admin12345');
insert into login_info(username, email, password) values ('Jovan', 'jovan@gmail.com','jovan123');

insert into login_info(username, email, password) values ('Kovac', 'Kovac@gmail.com','kovan123');
insert into login_info(username, email, password) values ('Marko', 'Marko@gmail.com','marko123');

insert into user_entity(name, surname, login_info, jmbg, phone_number, user_type) values ('Ivan', 'Ivanović', 1, '0111997710262', '0691408034', 0);
insert into user_entity(name, surname, login_info, jmbg, phone_number, user_type) values ('Jovan', 'Jovanovic', 2, '0111997710210', '0691408000', 1);

insert into user_entity(name, surname, login_info, jmbg, phone_number, user_type) values ('Kovac', 'Milacic', 3, '0111997710211', '0691408034', 2);
insert into user_entity(name, surname, login_info, jmbg, phone_number, user_type) values ('Marko', 'Jovanovic', 4, '0111997710216', '0691408000', 2);


insert into agent(first_login, number_ads, id) values (false, 0, 2);

insert into advertisement(id, brand, car_class, car_seats, fuel_type, model, name, price, trans_type, travelled, posted_by_id) values (1, 'BMW', 0, 5, 0, '2', 'Kola', 200, 0, 0, 2);

insert into endentity(activated, admin_approved, blocked, number_of_requests_canceled, id) values (true, true, false, 0, 3);
insert into endentity(activated, admin_approved, blocked, number_of_requests_canceled, id) values (true, true, false, 0, 4);
