insert into USUARIO (id, email, password, nombre, apellidos, admin, gestionado) values (NEXTVAL('hibernate_sequence'), 'usuario@usuario.com', '1234', 'Miguel', 'Campos Rivera', false, true);
insert into USUARIO (id, email, password, nombre, apellidos, admin, gestionado) values (NEXTVAL('hibernate_sequence'), 'admin@admin.com', 'admin', 'Luis Miguel', 'López Magaña', true, true);

insert into AULA (id, nombre) values (1, '1º DAM');
insert into AULA (id, nombre) values (2, '2º DAM');
insert into AULA (id, nombre) values (3, 'Biblioteca');
insert into AULA (id, nombre) values (4, 'Laboratorio');
insert into AULA (id, nombre) values (5, '1º AyF');
insert into AULA (id, nombre) values (6, '1º Comercio');