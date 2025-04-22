/*Rasgos de razas*/
INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES ('Ojos de medianoche', 'No sufrirás ceguera por oscuridad o brumas', 0);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES ((SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Ojos de medianoche'), 1);

INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES ('Cultura medicinal', 'Todas los desafíos de Medicina que realices tendrán un bonificador de +1d6 adicional. Además siempre que cures a un objetivo o a ti mismo curarás +1d8 extra.', 0);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES ((SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Cultura medicinal'), 1);

INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES ('Acero y roca', 'Obtienes 1d4 de impacto y combate con cualquier martillo que empuñes. Además, todos los hechizos de elemento montañoso que conjures aplicarán 1d4 de daño adicional por cada dado de impacto que emplee el hechizo.', 0);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES ((SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Acero y roca'), 1);

INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES ('Fortaleza mental', 'Obtienes +1d4 a todos tus desafíos de Inteligencia. Siempre que vayas a recibir un penalizador a la Inteligencia podrás realizar un desafío de Intuición (15) para evitar recibirlo.', 0);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES ((SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Fortaleza mental'), 1);

INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES ('Camuflaje natural', 'Una vez por descanso largo podrás volverte invisible durante 3 minutos', 1);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES ((SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Camuflaje natural'), 1);

INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES ('Afortunado', 'Una vez por descanso largo podrás erepetir cualquier tirada de dado que realices.', 1);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES ((SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Afortunado'), 1);

INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES ('Vigor del norte', 'Podrás realizar un desafío de Resistencia (15) para ignorar cualquier estado físico que te provoquen. Si falla no podrás volver a intentar retirarlo con este método hasta tu siguiente descanso largo.', 0);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES ((SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Vigor del norte'), 1);

INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES ('Brutalidad', 'Podrás aplicar hasta 3 bonificador de 1d6 a tus desafíos de Fuerza o Constitución por cada descanso largo.', 1);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES ((SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Brutalidad'), 1);

/*Rasgo de bárbaro*/
INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES ('Furia de batalla', 'Cada vez que golpees a un objetivo obtendrás 2 puntos de furia hasta un máximo de 10. Podrás entrar en estado de furia para poder consumir tus puntos de furia y lanzar tus talentos de bárbaro. Una vez termine el estado de furia se perderán todos los puntos de furia. El estado de furia terminará automáticamente si no intentas golpear a un objetivo en 3 turnos (1:30 min).', 0);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES ((SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Furia de batalla'), 1);

/*Arquetipos*/
/*Arquetipo Innosita*/
INSERT INTO ARQUETIPOS (idArquetipo, fuerza, constitucion, destreza, inteligencia, sabiduria, carisma, iniciativa, aura, movimiento)
VALUES (001, 4, 4, 7, 3, 7, 5, 0, 5, 10);
/*Arquetipo Oncaciano*/
INSERT INTO ARQUETIPOS (idArquetipo, fuerza, constitucion, destreza, inteligencia, sabiduria, carisma, iniciativa, aura, movimiento)
VALUES (002, 5, 1, 5, 5, 7, 5, 0, 10, 10);
/*Arquetipo Graug*/
INSERT INTO ARQUETIPOS (idArquetipo, fuerza, constitucion, destreza, inteligencia, sabiduria, carisma, iniciativa, aura, movimiento)
VALUES (003, 6, 8, 6, 2, 5, 5, 0, 5, 8);
/*Arquetipo Sirivi y Nordastri*/
INSERT INTO ARQUETIPOS (idArquetipo, fuerza, constitucion, destreza, inteligencia, sabiduria, carisma, iniciativa, aura, movimiento)
VALUES (004, 5, 5, 5, 5, 5, 5, 0, 5, 10);
/*Arquetipo Nagi*/
INSERT INTO ARQUETIPOS (idArquetipo, fuerza, constitucion, destreza, inteligencia, sabiduria, carisma, iniciativa, aura, movimiento)
VALUES (005, 5, 5, 7, 1, 5, 7, 0, 5, 12);
/*Arquetipo Uglat*/
INSERT INTO ARQUETIPOS (idArquetipo, fuerza, constitucion, destreza, inteligencia, sabiduria, carisma, iniciativa, aura, movimiento)
VALUES (006, 5, 5, 1, 7, 5, 7, 0, 5, 8);
/*Arquetipo Druk*/
INSERT INTO ARQUETIPOS (idArquetipo, fuerza, constitucion, destreza, inteligencia, sabiduria, carisma, iniciativa, aura, movimiento)
VALUES (007, 8, 8, 5, 3, 3, 3, 0, 5, 10);
/*Arquetipo Bárbaro*/
INSERT INTO ARQUETIPOS (idArquetipo, fuerza, constitucion, destreza, inteligencia, sabiduria, carisma, iniciativa, aura, movimiento)
VALUES (101, 4, 4, 0, 0, 0, 0, 0, 10, 0);
/*Arquetipo Bardo*/
INSERT INTO ARQUETIPOS (idArquetipo, fuerza, constitucion, destreza, inteligencia, sabiduria, carisma, iniciativa, aura, movimiento)
VALUES (102, 0, 0, 0, 4, 0, 4, 0, 12, 0);
/*Arquetipo Clérigo*/
INSERT INTO ARQUETIPOS (idArquetipo, fuerza, constitucion, destreza, inteligencia, sabiduria, carisma, iniciativa, aura, movimiento)
VALUES (103, 0, 0, 0, 4, 0, 4, 0, 15, 0);
/*Arquetipo Explorador*/
INSERT INTO ARQUETIPOS (idArquetipo, fuerza, constitucion, destreza, inteligencia, sabiduria, carisma, iniciativa, aura, movimiento)
VALUES (104, 0, 0, 4, 0, 0, 4, 2, 10, 2);
/*Arquetipo Guerrero*/
INSERT INTO ARQUETIPOS (idArquetipo, fuerza, constitucion, destreza, inteligencia, sabiduria, carisma, iniciativa, aura, movimiento)
VALUES (105, 4, 0, 4, 0, 0, 0, 0, 10, 0);   
/*Arquetipo Mago*/
INSERT INTO ARQUETIPOS (idArquetipo, fuerza, constitucion, destreza, inteligencia, sabiduria, carisma, iniciativa, aura, movimiento)
VALUES (106, 0, 0, 0, 0, 4, 4, 0, 15, 0);
/*Arquetipo Paladín*/
INSERT INTO ARQUETIPOS (idArquetipo, fuerza, constitucion, destreza, inteligencia, sabiduria, carisma, iniciativa, aura, movimiento)
VALUES (107, 0, 4, 0, 0, 0, 4, 0, 10, 0);
/*Arquetipo Pícaro*/
INSERT INTO ARQUETIPOS (idArquetipo, fuerza, constitucion, destreza, inteligencia, sabiduria, carisma, iniciativa, aura, movimiento)
VALUES (108, 0, 0, 4, 4, 0, 0, 4, 10, 2);

/*Razas*/
INSERT INTO RAZAS (nombre, idArquetipo, idRasgo) 
VALUES ('Innosita', 001, (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Ojos de medianoche')); 
INSERT INTO RAZAS (nombre, idArquetipo, idRasgo) 
VALUES ('Oncaciano', 002, (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Cultura medicinal'));
INSERT INTO RAZAS (nombre, idArquetipo, idRasgo) 
VALUES ('Graug', 003, (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Acero y roca'));
INSERT INTO RAZAS (nombre, idArquetipo, idRasgo) 
VALUES ('Sirivi', 004, (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Fortaleza mental'));
INSERT INTO RAZAS (nombre, idArquetipo, idRasgo) 
VALUES ('Nagi', 005, (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Camuflaje natural'));
INSERT INTO RAZAS (nombre, idArquetipo, idRasgo) 
VALUES ('Uglat', 006, (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Afortunado'));
INSERT INTO RAZAS (nombre, idArquetipo, idRasgo) 
VALUES ('Nordastri', 004, (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Vigor del norte'));
INSERT INTO RAZAS (nombre, idArquetipo, idRasgo) 
VALUES ('Druk', 007, (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Brutalidad'));

/*Clases*/
INSERT INTO CLASES (idClase, nombre, dado, idArquetipo, armas, armaduras) 
VALUES (01, 'Bárbaro', '1d8', 101, HEXTORAW('70'), HEXTORAW('4'));
INSERT INTO CLASES (idClase, nombre, dado, idArquetipo, armas, armaduras) 
VALUES (02, 'Bardo', '1d4', 102, HEXTORAW('40'), HEXTORAW('0'));
INSERT INTO CLASES (idClase, nombre, dado, idArquetipo, armas, armaduras) 
VALUES (03, 'Clérigo', '1d6', 103, HEXTORAW('64'), HEXTORAW('6'));
INSERT INTO CLASES (idClase, nombre, dado, idArquetipo, armas, armaduras) 
VALUES (04, 'Explorador', '1d6', 104, HEXTORAW('88'), HEXTORAW('4'));
INSERT INTO CLASES (idClase, nombre, dado, idArquetipo, armas, armaduras) 
VALUES (05, 'Guerrero', '1d8', 105, HEXTORAW('FC'), HEXTORAW('7'));
INSERT INTO CLASES (idClase, nombre, dado, idArquetipo, armas, armaduras) 
VALUES (06, 'Mago', '1d4', 106, HEXTORAW('0'), HEXTORAW('4'));
INSERT INTO CLASES (idClase, nombre, dado, idArquetipo, armas, armaduras) 
VALUES (07, 'Paladín', '1d8', 107, HEXTORAW('74'), HEXTORAW('7'));
INSERT INTO CLASES (idClase, nombre, dado, idArquetipo, armas, armaduras) 
VALUES (08, 'Pícaro', '1d6', 108, HEXTORAW('C8'), HEXTORAW('4'));

/*Subclases*/
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (001, 01, 'Berseker');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (002, 01, 'Tribal');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (003, 01, 'Hermitaño');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (004, 02, 'Artista académico');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (005, 02, 'Artista extravagante');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (006, 02, 'Artista libre');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (007, 03, 'Devoto');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (008, 03, 'Profeta');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (009, 03, 'Inquisidor');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (010, 04, 'Trampero');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (011, 04, 'Domador');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (012, 04, 'Tirador');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (013, 05, 'Caballero');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (014, 05, 'Mercenario');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (015, 05, 'Centinela');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (016, 06, 'Elementalista');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (017, 06, 'Artifice arcano');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (018, 06, 'Trascendido');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (019, 06, 'Adivino');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (020, 07, 'Jurado de lealtad');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (021, 07, 'Jurado de inspiración');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (022, 07, 'Jurado de triunfo');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (023, 08, 'Asesino');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (024, 08, 'Ladrón');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (025, 08, 'Bribón');

INSERT INTO RELIGIONES (nombre) VALUES ('Lapidario');
INSERT INTO RELIGIONES (nombre) VALUES ('Husave');
INSERT INTO RELIGIONES (nombre) VALUES ('Seguidor de los Tres Grandes');
INSERT INTO RELIGIONES (nombre) VALUES ('Triunvitario');
INSERT INTO RELIGIONES (nombre) VALUES ('Hereje Antiguo');
INSERT INTO RELIGIONES (nombre) VALUES ('Orador de Borus');
INSERT INTO RELIGIONES (nombre) VALUES ('Herético Insentiente');
INSERT INTO RELIGIONES (nombre) VALUES ('Adorador del Retorno');
INSERT INTO RELIGIONES (nombre) VALUES ('Testígos del Último Heroe');
INSERT INTO RELIGIONES (nombre) VALUES ('Jaoblita');
INSERT INTO RELIGIONES (nombre) VALUES ('Sendas Domerin');
INSERT INTO RELIGIONES (nombre) VALUES ('Orador de Ascuas');
INSERT INTO RELIGIONES (nombre) VALUES ('Auscultor de Susurros'); 

INSERT INTO REINOS (nombre) VALUES ('Nordast');
INSERT INTO REINOS (nombre) VALUES ('Imperio Sirivi');
INSERT INTO REINOS (nombre) VALUES ('Oncacia');
INSERT INTO REINOS (nombre) VALUES ('Dornan');
INSERT INTO REINOS (nombre) VALUES ('Thurimgard');
INSERT INTO REINOS (nombre) VALUES ('Innon');
INSERT INTO REINOS (nombre) VALUES ('Patria Nagi');
INSERT INTO REINOS (nombre) VALUES ('Santuario');
INSERT INTO REINOS (nombre) VALUES ('Cuna de Karkos');
INSERT INTO REINOS (nombre) VALUES ('Islas de Mend Hevak');
INSERT INTO REINOS (nombre) VALUES ('Archipiélago Coralino');
INSERT INTO REINOS (nombre) VALUES ('Shival Rag');

INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Nordast'), 'Unionista');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Nordast'), 'Independentista de Voran');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Nordast'), 'Fiel a la nueva monarquía');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Nordast'), 'Varmosano');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Nordast'), 'Tyrisano');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Nordast'), 'Merysano');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Nordast'), 'Arfortino');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Nordast'), 'Seregodiense');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Nordast'), 'Illsureño');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Nordast'), 'Hijo de Mirhai');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Nordast'), 'La mueca');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Nordast'), 'Aulladores');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Nordast'), 'Guardia dorada');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Nordast'), 'Seras Sanctum');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Oncacia'), 'Oncai-lis');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Oncacia'), 'Shen-lis');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Oncacia'), 'Hashen-lis');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Oncacia'), 'Nuhu-lis');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Oncacia'), 'Defensor de las Provincias');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Oncacia'), 'Oncai-lis');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Oncacia'), 'Dominio soberano');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Dornan'), 'Orok''Vaar');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Dornan'), 'Er''Meduk');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Dornan'), 'Orok''Abu');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Dornan'), 'Kalag''Ar');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Dornan'), 'Gar''Ken');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Dornan'), 'Godo-Go''Ke');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Dornan'), 'Orok''Ogeraru');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Dornan'), 'Tkiarug');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Dornan'), 'Dkirg-Re''Ko');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Dornan'), 'Skulkar Moiso');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Dornan'), 'Warg-Soaro');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Dornan'), 'Oo-Dotergo');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Dornan'), 'Ole-Mokare');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Dornan'), 'Kaaru''Vaar');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Dornan'), 'T''Grull-Soru');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Dornan'), 'Duse-Derargo');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Dornan'), 'Ketorog');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Dornan'), 'Okug-Odeku');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Dornan'), 'Moöug');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Dornan'), 'Diekrug-Ook''Ug');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Imperio Sirivi'), 'Ha-mullsar');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Imperio Sirivi'), 'Ha-tahal');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Imperio Sirivi'), 'Ha-raftal');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Imperio Sirivi'), 'Ha-abulyar');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Imperio Sirivi'), 'Ha-tottem');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Imperio Sirivi'), 'Surcador de Dunas');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Imperio Sirivi'), 'Adorador de la Arena');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Imperio Sirivi'), 'Carcelero de Nahasiv');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Imperio Sirivi'), 'Orden de la Cadena');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Imperio Sirivi'), 'Velazul');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Imperio Sirivi'), 'Pacto de Piedra');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Imperio Sirivi'), 'Imperial');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Imperio Sirivi'), 'El Dictamen');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Imperio Sirivi'), 'Camara Husavek');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Imperio Sirivi'), 'Mano de Mun-Yair');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Imperio Sirivi'), 'Hijo de la República');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Santuario'), 'Explorador de Fronteras');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Santuario'), 'Preservador de Vidas');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Santuario'), 'Buscador de Verdad');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Santuario'), 'Reclmador de Justicia');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Santuario'), 'Cultivador de Diplomacia');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Santuario'), 'Guardian del Hogar');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Thurimgard'), 'Arandur Breno');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Thurimgard'), 'Dardros Breno');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Thurimgard'), 'Rukmol Breno');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Thurimgard'), 'Ringen Breno');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Thurimgard'), 'Grumble');
INSERT INTO IDEOLOGIAS (idReino, nombre) VALUES ((SELECT idReino FROM REINOS WHERE nombre = 'Thurimgard'), 'Pacto de la Forja');

COMMIT;

