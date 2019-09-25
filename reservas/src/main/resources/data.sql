insert into USUARIO (id, email, password, nombre, apellidos, admin, gestionado) values (NEXTVAL('hibernate_sequence'), 'usuario@usuario.com', '1234', 'Miguel', 'Campos Rivera', false, true);
insert into USUARIO (id, email, password, nombre, apellidos, admin, gestionado) values (NEXTVAL('hibernate_sequence'), 'admin@admin.com', 'admin', 'Luis Miguel', 'López Magaña', true, true);
insert into USUARIO (id, email, password, nombre, apellidos, admin, gestionado) values (NEXTVAL('hibernate_sequence'), 'kbramall0@nytimes.com', '6syZHFD', 'Kaine', 'Bramall', false, false);
insert into USUARIO (id, email, password, nombre, apellidos, admin, gestionado) values (NEXTVAL('hibernate_sequence'), 'jpetrus1@discovery.com', 'o5iJqjpXy6', 'Judon', 'Petrus', false, false);
insert into USUARIO (id, email, password, nombre, apellidos, admin, gestionado) values (NEXTVAL('hibernate_sequence'), 'gstilling2@nature.com', 'kZoN4PUpSy1', 'Gwyn', 'Stilling', false, false);
insert into USUARIO (id, email, password, nombre, apellidos, admin, gestionado) values (NEXTVAL('hibernate_sequence'), 'hsagg3@wiley.com', '9DeR3uprwr', 'Herschel', 'Sagg', false, false);
insert into USUARIO (id, email, password, nombre, apellidos, admin, gestionado) values (NEXTVAL('hibernate_sequence'), 'asquibb4@nature.com', 'Nk8SFh', 'Alf', 'Squibb', false, false);
insert into USUARIO (id, email, password, nombre, apellidos, admin, gestionado) values (NEXTVAL('hibernate_sequence'), 'oszubert5@globo.com', '8JwXLJ12PDvB', 'Ody', 'Szubert', false, false);
insert into USUARIO (id, email, password, nombre, apellidos, admin, gestionado) values (NEXTVAL('hibernate_sequence'), 'tangeli6@feedburner.com', 'YusrXVH2DkHo', 'Todd', 'Angeli', false, false);
insert into USUARIO (id, email, password, nombre, apellidos, admin, gestionado) values (NEXTVAL('hibernate_sequence'), 'rpoultney7@merriam-webster.com', 'UMj3EepXm', 'Ripley', 'Poultney', false, false);
insert into USUARIO (id, email, password, nombre, apellidos, admin, gestionado) values (NEXTVAL('hibernate_sequence'), 'ctorn8@reverbnation.com', 'O3MgnZ', 'Candace', 'Torn', false, false);
insert into USUARIO (id, email, password, nombre, apellidos, admin, gestionado) values (NEXTVAL('hibernate_sequence'), 'vbunting9@mashable.com', 'URTzuoj', 'Virgie', 'Bunting', false, false);

insert into AULA (id, nombre) values (NEXTVAL('hibernate_sequence'), '1º DAM');
insert into AULA (id, nombre) values (NEXTVAL('hibernate_sequence'), '2º DAM');
insert into AULA (id, nombre) values (NEXTVAL('hibernate_sequence'), 'Biblioteca');
insert into AULA (id, nombre) values (NEXTVAL('hibernate_sequence'), 'Laboratorio');
insert into AULA (id, nombre) values (NEXTVAL('hibernate_sequence'), '1º AyF');
insert into AULA (id, nombre) values (NEXTVAL('hibernate_sequence'), '1º Comercio');


insert into RESERVA (id, hora, aula_id, usuario_id, fecha) values (NEXTVAL('hibernate_sequence'), 0, 13, 1 ,'2019-09-27');