INSERT INTO BRAND(ID, NAME, CODE) VALUES (1, 'BMW', 'safku2');
INSERT INTO BRAND(ID, NAME, CODE) VALUES (2, 'Volkswagen', 'safku3');
INSERT INTO BRAND(ID, NAME, CODE) VALUES (3, 'Audi', 'safku4');
INSERT INTO BRAND(ID, NAME, CODE) VALUES (4, 'Skoda', 'safku5');
INSERT INTO BRAND(ID, NAME, CODE) VALUES (5, 'Fiat', 'safku6');
INSERT INTO BRAND(ID, NAME, CODE) VALUES (6, 'Ford', 'safku7');
INSERT INTO BRAND(ID, NAME, CODE) VALUES (7, 'Peugeot', 'safku8');

INSERT INTO CAR_CLASS(ID, NAME, CODE) VALUES (1, 'Karavan', 'sa12ku2');
INSERT INTO CAR_CLASS(ID, NAME, CODE) VALUES (2, 'Hatchback', 'sa12ku3');
INSERT INTO CAR_CLASS(ID, NAME, CODE) VALUES (3, 'Limousine', 'sa12ku4');
INSERT INTO CAR_CLASS(ID, NAME, CODE) VALUES (4, 'SUV', 'sa12ku5');
INSERT INTO CAR_CLASS(ID, NAME, CODE) VALUES (5, 'Minivan', 'sa12ku6');
INSERT INTO CAR_CLASS(ID, NAME, CODE) VALUES (6, 'Pickup truck', 'sa12ku7');
INSERT INTO CAR_CLASS(ID, NAME, CODE) VALUES (7, 'Van', 'sa12ku8');
INSERT INTO CAR_CLASS(ID, NAME, CODE) VALUES (8, 'Truck', 'sa12ku9');


INSERT INTO FUEL_TYPE(ID, NAME, CODE) VALUES (1, 'Dizel', 'gj71');
INSERT INTO FUEL_TYPE(ID, NAME, CODE) VALUES (2, 'Petrol', 'gj72');
INSERT INTO FUEL_TYPE(ID, NAME, CODE) VALUES (3, 'Electric', 'gj73');
INSERT INTO FUEL_TYPE(ID, NAME, CODE) VALUES (4, 'Hybrid', 'gj74');
INSERT INTO FUEL_TYPE(ID, NAME, CODE) VALUES (5, 'Gas', 'gj75');

INSERT INTO MODEL(ID, NAME, CODE) VALUES (1, 'Polo', 'gsa2');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (2, 'Golf', 'gsa3');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (3, 'Jetta', 'gsa4');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (4, 'Passat', 'gsa5');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (5, 'Tiguan', 'gsa6');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (6, 'A3', 'gsa7');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (7, 'A4', 'gsa8');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (8, 'A6', 'gsa9');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (9, 'A7', 'gsa10');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (10, 'A8', 'gsa11');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (11, 'Punto', 'gsa12');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (12, 'Panda', 'gsa13');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (13, 'Q7', 'gsa14');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (14, 'F150', 'gsa15');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (15, 'Fabia', 'gsa16');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (16, 'M3', 'gsa25');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (17, 'X5', 'gsa26');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (18, 'M5', 'gsa27');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (19, 'Superb', 'gsa17');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (20, 'Octavia', 'gsa18');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (21, 'Golf 6', 'gsa19');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (22, 'Golf 7', 'gsa20');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (23, 'Focus', 'gsa21');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (24, 'Fiesta', 'gsa22');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (25, '206', 'gsa23');
INSERT INTO MODEL(ID, NAME, CODE) VALUES (26, '307', 'gsa24');

INSERT INTO TRANSMISSION_TYPE(ID, NAME, CODE) VALUES (1, 'Automatic', 'asd1');
INSERT INTO TRANSMISSION_TYPE(ID, NAME, CODE) VALUES (2, 'Manual', 'asd2');
INSERT INTO TRANSMISSION_TYPE(ID, NAME, CODE) VALUES (3, 'Sequential', 'asd3');
INSERT INTO TRANSMISSION_TYPE(ID, NAME, CODE) VALUES (4, 'Semi-automatic', 'asd4');

INSERT INTO LOGIN_INFO (EMAIL, IS_ACCOUNT_NON_EXPIRED, IS_ACCOUNT_NON_LOCKED, IS_CREDENTIALS_NON_EXPIRED, IS_ENABLED,  PASSWORD, USERNAME)
VALUES ('admin@gmail.com', true, true, true, true, 'Susa$k0njina', 'admin');

INSERT INTO LOGIN_INFO (EMAIL, IS_ACCOUNT_NON_EXPIRED, IS_ACCOUNT_NON_LOCKED, IS_CREDENTIALS_NON_EXPIRED, IS_ENABLED,  PASSWORD, USERNAME)
VALUES ('user@gmail.com', true, true, true, true, 'Susa$k0njina', 'user');

INSERT INTO LOGIN_INFO (EMAIL, IS_ACCOUNT_NON_EXPIRED, IS_ACCOUNT_NON_LOCKED, IS_CREDENTIALS_NON_EXPIRED, IS_ENABLED,  PASSWORD, USERNAME)
VALUES ('kajza@gmail.com', true, true, true, true, 'Susa$k0njina', 'kajza');

INSERT INTO LOGIN_INFO (EMAIL, IS_ACCOUNT_NON_EXPIRED, IS_ACCOUNT_NON_LOCKED, IS_CREDENTIALS_NON_EXPIRED, IS_ENABLED,  PASSWORD, USERNAME)
VALUES ('agent@gmail.com', true, true, true, true, 'Susa$k0njina', 'agent');

INSERT INTO LOGIN_INFO (EMAIL, IS_ACCOUNT_NON_EXPIRED, IS_ACCOUNT_NON_LOCKED, IS_CREDENTIALS_NON_EXPIRED, IS_ENABLED,  PASSWORD, USERNAME)
VALUES ('sss', true, true, true, true, 'sss', 'sss');

INSERT INTO LOGIN_INFO (EMAIL, IS_ACCOUNT_NON_EXPIRED, IS_ACCOUNT_NON_LOCKED, IS_CREDENTIALS_NON_EXPIRED, IS_ENABLED,  PASSWORD, USERNAME)
VALUES ('ddd', true, true, true, true, 'ddd', 'ddd');


--USER
INSERT INTO USER_ENTITY (JMBG, NAME, PHONE_NUMBER, SURNAME, USER_TYPE, LOGIN_INFO) VALUES
(0111997710268, 'Administrator', 0691408080, 'Administrator', 0, 1);

INSERT INTO USER_ENTITY (JMBG, NAME, PHONE_NUMBER, SURNAME, USER_TYPE, LOGIN_INFO) VALUES
(0111997710260, 'Korisnik', 0691408082, 'Korisnik', 2, 2);

INSERT INTO USER_ENTITY (JMBG, NAME, PHONE_NUMBER, SURNAME, USER_TYPE, LOGIN_INFO) VALUES
(0111997710263, 'Korisnik', 0691408082, 'Korisnik', 2, 3);

INSERT INTO USER_ENTITY (JMBG, NAME, PHONE_NUMBER, SURNAME, USER_TYPE, LOGIN_INFO) VALUES
(0111997710265, 'Agent', 0691408082, 'Agent', 1, 4);

INSERT INTO USER_ENTITY (JMBG, NAME, PHONE_NUMBER, SURNAME, USER_TYPE, LOGIN_INFO) VALUES
(0111997710273, 'Korisnik', 0691408082, 'Korisnik', 2, 5);

INSERT INTO USER_ENTITY (JMBG, NAME, PHONE_NUMBER, SURNAME, USER_TYPE, LOGIN_INFO) VALUES
(0111997710265, 'Agent', 0691408082, 'Agent', 1, 6);

--ENDENTITY
INSERT INTO ENDENTITY (ACTIVATED, ADMIN_APPROVED, BLOCKED, NUMBER_OF_ADS, NUMBER_OF_REQUESTS_CANCELED, ENTITY_USER_ID) VALUES
(TRUE, TRUE, FALSE, 0, 0, 3);

INSERT INTO ENDENTITY (ACTIVATED, ADMIN_APPROVED, BLOCKED, NUMBER_OF_ADS, NUMBER_OF_REQUESTS_CANCELED, ENTITY_USER_ID) VALUES
 (FALSE, FALSE, FALSE, 0, 0, 2);

INSERT INTO ENDENTITY (ACTIVATED, ADMIN_APPROVED, BLOCKED, NUMBER_OF_ADS, NUMBER_OF_REQUESTS_CANCELED, ENTITY_USER_ID) VALUES
(TRUE, TRUE, FALSE, 0, 0, 5);


--AGENT
INSERT INTO AGENT (ADRESS, BUSINESS_REGISTRATION_NUMBER, FIRST_LOGIN, NUMBER_ADS, USER_ID) VALUES
('ADRESA', 54343, FALSE, 0, 2);

INSERT INTO AGENT (ADRESS, BUSINESS_REGISTRATION_NUMBER, FIRST_LOGIN, NUMBER_ADS, USER_ID) VALUES
('ADRESA', 54345, FALSE, 0, 6);


INSERT INTO ADVERTISEMENT(NAME, MODEL_ID, BRAND_ID, FUEL_TYPE_ID, TRANSMISSION_TYPE_ID, CAR_CLASS_ID, TRAVELLED, CAR_SEATS, PRICE, POSTED_BY_ID, DISCOUNT, PRICE_WITH_DISCOUNT, PICTURES, GRADE) VALUES
('Nesto', 18, 1, 1, 1, 1, 20000, 5, 120, 6, 10, 108, null, 0.0);

INSERT INTO ENDUSER_RENTED(endentity_id, ad_id) VALUES (1, 1);
