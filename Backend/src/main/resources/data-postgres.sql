insert into login_info(username, email, password) values ('Admin', 'admin@gmail.com','admin12345');
insert into login_info(username, email, password) values ('Jovan', 'jovan@gmail.com','jovan123');

insert into login_info(username, email, password) values ('Kovac', 'kovac@gmail.com','kovan123');
insert into login_info(username, email, password) values ('Marko', 'marko@gmail.com','marko123');
insert into login_info(username, email, password) values ('Ivan', 'ivan@gmail.com','ivan123');


insert into user_entity(name, surname, login_info, jmbg, phone_number, user_type) values ('Ivan', 'Ivanović', 1, '0111997710262', '0691408034', 0);
insert into user_entity(name, surname, login_info, jmbg, phone_number, user_type) values ('Jovan', 'Jovanovic', 2, '0111997710210', '0691408000', 1);

insert into user_entity(name, surname, login_info, jmbg, phone_number, user_type) values ('Kovac', 'Milacic', 3, '0111997710211', '0691408034', 1 );
insert into user_entity(name, surname, login_info, jmbg, phone_number, user_type) values ('Marko', 'Jovanovic', 4, '0111997710216', '0691408000', 2);
insert into user_entity(name, surname, login_info, jmbg, phone_number, user_type) values ('Ivan', 'Ivanovic', 5, '0111997710219', '0691408001', 2);

insert into agent(first_login, number_ads, id) values (false, 0, 2);
insert into agent(first_login, number_ads, id) values (false, 0, 3);

insert into endentity(activated, admin_approved, blocked, number_of_requests_canceled, id) values (true, true, false, 0, 4);
insert into endentity(activated, admin_approved, blocked, number_of_requests_canceled, id) values (true, true, false, 0, 5);

insert into cardetails(code, name, type) values ('123', 'BMW', 3);
insert into cardetails(code, name, type) values ('543', 'Passat', 1);
insert into cardetails(code, name, type) values ('312', '2', 0);
insert into cardetails(code, name, type) values ('132', 'Dizel', 2);
insert into cardetails(code, name, type) values ('321', 'Automatic', 4);


insert into advertisement(brand, car_class, car_seats, fuel_type, model, name, price, trans_type, travelled, posted_by_id, grade, discount, price_with_discount)
 values ('BMW', 'Passat', 4, 'Dizel', '2', 'Nesto', 120, 'Automatic', 0, 2, 0, 2.2, 100);

insert into enduser_rented(endentity_id, ad_id) values (4, 1);

insert into grade(value, ad_id) values (2.3, 1);
