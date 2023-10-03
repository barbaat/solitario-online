-- One admin user, named admin1 with password 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
INSERT INTO jugador (id,created_date,creator,last_modified_date,modifier,first_name,last_name,image,win,lost,time,mov,points,max_movs,min_movs,max_time,min_time,username)VALUES(1,'2023-1-7 00:00:00','admin1','2023-1-7 00:00:00','admin1','admin','admin','',0,0,'00:00:00',0,0,0,0,'','','admin1');

-- One jugador user, named mario with passwor mario
INSERT INTO users(username,password,enabled) VALUES ('marsannar2','mario',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'marsannar2','jugador');
INSERT INTO jugador (id,created_date,creator,last_modified_date,modifier,first_name,last_name,image,win,lost,time,mov,points,max_movs,min_movs,max_time,min_time,username)VALUES(2,'2023-1-7 00:00:00','admin1','2023-1-7 00:00:00','admin1','mario','sanchez','',0,0,'00:00:00',0,0,0,0,'','','marsannar2');

-- -- One jugador jorsilman, named jorge with password jorge
INSERT INTO users(username,password,enabled) VALUES ('jorge','jorge',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'jorge','jugador');
INSERT INTO jugador (id,created_date,creator,last_modified_date,modifier,first_name,last_name,image,win,lost,time,mov,points,max_movs,min_movs,max_time,min_time,username)VALUES(3,'2023-1-7 00:00:00','admin1','2023-1-7 00:00:00','admin1','jorge','sillero','',0,0,'00:00:00',0,0,0,0,'','','jorge');

-- -- One jugador barbaat, named barba with password barba
INSERT INTO users(username,password,enabled) VALUES ('barba','barba',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'barba','jugador');
INSERT INTO jugador (id,created_date,creator,last_modified_date,modifier,first_name,last_name,image,win,lost,time,mov,points,max_movs,min_movs,max_time,min_time,username)VALUES(4,'2023-1-7 00:00:00','admin1','2023-1-7 00:00:00','admin1','barba','barba','',0,0,'00:00:00',0,0,0,0,'','','barba');

-- -- One jugador fracaralb, named fran with passwor fran
INSERT INTO users(username,password,enabled) VALUES ('fran','fran',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'fran','jugador');
INSERT INTO jugador (id,created_date,creator,last_modified_date,modifier,first_name,last_name,image,win,lost,time,mov,points,max_movs,min_movs,max_time,min_time,username)VALUES(5,'2023-1-7 00:00:00','admin1','2023-1-7 00:00:00','admin1','fran','fran','',0,0,'00:00:00',0,0,0,0,'','','fran');

-- -- One jugador albgalhue, named gallego with password gallego
INSERT INTO users(username,password,enabled) VALUES ('gallego','gallego',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'gallego','jugador');
INSERT INTO jugador (id,created_date,creator,last_modified_date,modifier,first_name,last_name,image,win,lost,time,mov,points,max_movs,min_movs,max_time,min_time,username)VALUES(6,'2023-1-7 00:00:00','admin1','2023-1-7 00:00:00','admin1','gallego','gallego','',0,0,'00:00:00',0,0,0,0,'','','gallego');

-- -- One jugador alvnavriv, named alvaro with password alvaro
INSERT INTO users(username,password,enabled) VALUES ('alvaro','alvaro',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (11,'alvaro','jugador');
INSERT INTO jugador (id,created_date,creator,last_modified_date,modifier,first_name,last_name,image,win,lost,time,mov,points,max_movs,min_movs,max_time,min_time,username)VALUES(7,'2023-1-7 00:00:00','admin1','2023-1-7 00:00:00','admin1','alvaro','alvaro','',0,0,'00:00:00',0,0,0,0,'','','alvaro');

INSERT INTO logros(id, name, description, is_unlocked, image, num_condicion, jugador_id) VALUES (1, 'Máquina de jugar','Has ganado 5 partidas', false, '',5,1);
INSERT INTO logros(id, name, description, is_unlocked, image, num_condicion, jugador_id) VALUES (2, 'No se te da nada mal','Has alcanzado los 100 puntos', false, '',100, 1);
INSERT INTO logros(id, name, description, is_unlocked, image, num_condicion, jugador_id) VALUES (3, '¡Estás on fire!','Has alcanzado los 200 movimientos', false, '',200, 1);

INSERT INTO logros(id, name, description, is_unlocked, image, num_condicion, jugador_id) VALUES (4, 'Máquina de jugar','Has ganado 5 partidas', false, '',5,2);
INSERT INTO logros(id, name, description, is_unlocked, image, num_condicion, jugador_id) VALUES (5, 'No se te da nada mal','Has alcanzado los 100 puntos', false, '',100,2);
INSERT INTO logros(id, name, description, is_unlocked, image, num_condicion, jugador_id) VALUES (6, '¡Estás on fire!','Has alcanzado los 200 movimientos', false, '',200, 2);

INSERT INTO logros(id, name, description, is_unlocked, image, num_condicion, jugador_id) VALUES (7, 'Máquina de jugar','Has ganado 5 partidas', false, '',5,3);
INSERT INTO logros(id, name, description, is_unlocked, image, num_condicion, jugador_id) VALUES (8, 'No se te da nada mal','Has alcanzado los 100 puntos', false, '',100, 3);
INSERT INTO logros(id, name, description, is_unlocked, image, num_condicion, jugador_id) VALUES (9, '¡Estás on fire!','Has alcanzado los 200 movimientos', false, '',200, 3);

INSERT INTO logros(id, name, description, is_unlocked, image, num_condicion, jugador_id) VALUES (10, 'Máquina de jugar','Has ganado 5 partidas', false, '',5,4);
INSERT INTO logros(id, name, description, is_unlocked, image, num_condicion, jugador_id) VALUES (11, 'No se te da nada mal','Has alcanzado los 100 puntos', false, '',100, 4);
INSERT INTO logros(id, name, description, is_unlocked, image, num_condicion, jugador_id) VALUES (12, '¡Estás on fire!','Has alcanzado los 200 movimientos', false, '',200,4);

INSERT INTO logros(id, name, description, is_unlocked, image, num_condicion, jugador_id) VALUES (13, 'Máquina de jugar','Has ganado 5 partidas', false, '',5,5);
INSERT INTO logros(id, name, description, is_unlocked, image, num_condicion, jugador_id) VALUES (14, 'No se te da nada mal','Has alcanzado los 100 puntos', false, '',100, 5);
INSERT INTO logros(id, name, description, is_unlocked, image, num_condicion, jugador_id) VALUES (15, '¡Estás on fire!','Has alcanzado los 200 movimientos', false, '',200,5);

INSERT INTO logros(id, name, description, is_unlocked, image, num_condicion, jugador_id) VALUES (16, 'Máquina de jugar','Has ganado 5 partidas', false, '',5,6);
INSERT INTO logros(id, name, description, is_unlocked, image, num_condicion, jugador_id) VALUES (17, 'No se te da nada mal','Has alcanzado los 100 puntos', false, '',100, 6);
INSERT INTO logros(id, name, description, is_unlocked, image, num_condicion, jugador_id) VALUES (18, '¡Estás on fire!','Has alcanzado los 200 movimientos', false, '',200,6);

INSERT INTO logros(id, name, description, is_unlocked, image, num_condicion, jugador_id) VALUES (19, 'Máquina de jugar','Has jugado 5 partidas', false, '',5,7);
INSERT INTO logros(id, name, description, is_unlocked, image, num_condicion, jugador_id) VALUES (20, 'No se te da nada mal','Has alcanzado los 100 puntos', false, '',100, 7);
INSERT INTO logros(id, name, description, is_unlocked, image, num_condicion, jugador_id) VALUES (21, '¡Estás on fire!','Has alcanzado los 200 movimientos', false, '',200, 7);
