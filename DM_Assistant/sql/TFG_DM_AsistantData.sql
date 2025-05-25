 /*Rasgos de razas*/
INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES ('Ojos de medianoche', 'No sufrirï¿½s ceguera por oscuridad o brumas', 0);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES ((SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Ojos de medianoche'), 1);

INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES ('Cultura medicinal', 'Todas los desafï¿½os de Medicina que realices tendrï¿½n un bonificador de +1d6 adicional. Ademï¿½s siempre que cures a un objetivo o a ti mismo curarï¿½s +1d8 extra.', 0);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES ((SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Cultura medicinal'), 1);

INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES ('Acero y roca', 'Obtienes 1d4 de impacto y combate con cualquier martillo que empuï¿½es. Ademï¿½s, todos los hechizos de elemento montaï¿½oso que conjures aplicarï¿½n 1d4 de daï¿½o adicional por cada dado de impacto que emplee el hechizo.', 0);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES ((SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Acero y roca'), 1);

INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES ('Fortaleza mental', 'Obtienes +1d4 a todos tus desafï¿½os de Inteligencia. Siempre que vayas a recibir un penalizador a la Inteligencia podrï¿½s realizar un desafï¿½o de Intuiciï¿½n (15) para evitar recibirlo.', 0);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES ((SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Fortaleza mental'), 1);

INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES ('Camuflaje natural', 'Una vez por descanso largo podrï¿½s volverte invisible durante 3 minutos', 1);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES ((SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Camuflaje natural'), 1);

INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES ('Afortunado', 'Una vez por descanso largo podrï¿½s erepetir cualquier tirada de dado que realices.', 1);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES ((SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Afortunado'), 1);

INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES ('Vigor del norte', 'Podrï¿½s realizar un desafï¿½o de Resistencia (15) para ignorar cualquier estado fï¿½sico que te provoquen. Si falla no podrï¿½s volver a intentar retirarlo con este mï¿½todo hasta tu siguiente descanso largo.', 0);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES ((SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Vigor del norte'), 1);

INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES ('Brutalidad', 'Podrï¿½s aplicar hasta 3 bonificador de 1d6 a tus desafï¿½os de Fuerza o Constituciï¿½n por cada descanso largo.', 1);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES ((SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Brutalidad'), 1);

/*Rasgo de bï¿½rbaro*/
INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES ('Furia de batalla', 'Cada vez que golpees a un objetivo obtendrï¿½s 2 puntos de furia hasta un mï¿½ximo de 10. Podrï¿½s entrar en estado de furia para poder consumir tus puntos de furia y lanzar tus talentos de bï¿½rbaro. Una vez termine el estado de furia se perderï¿½n todos los puntos de furia. El estado de furia terminarï¿½ automï¿½ticamente si no intentas golpear a un objetivo en 3 turnos (1:30 min).', 0);
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
/*Arquetipo Bï¿½rbaro*/
INSERT INTO ARQUETIPOS (idArquetipo, fuerza, constitucion, destreza, inteligencia, sabiduria, carisma, iniciativa, aura, movimiento)
VALUES (101, 4, 4, 0, 0, 0, 0, 0, 10, 0);
/*Arquetipo Bardo*/
INSERT INTO ARQUETIPOS (idArquetipo, fuerza, constitucion, destreza, inteligencia, sabiduria, carisma, iniciativa, aura, movimiento)
VALUES (102, 0, 0, 0, 4, 0, 4, 0, 12, 0);
/*Arquetipo Clï¿½rigo*/
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
/*Arquetipo Paladï¿½n*/
INSERT INTO ARQUETIPOS (idArquetipo, fuerza, constitucion, destreza, inteligencia, sabiduria, carisma, iniciativa, aura, movimiento)
VALUES (107, 0, 4, 0, 0, 0, 4, 0, 10, 0);
/*Arquetipo Pï¿½caro*/
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
VALUES (01, 'Bï¿½rbaro', '1d8', 101, HEXTORAW('70'), HEXTORAW('4'));
INSERT INTO CLASES (idClase, nombre, dado, idArquetipo, armas, armaduras) 
VALUES (02, 'Bardo', '1d4', 102, HEXTORAW('40'), HEXTORAW('0'));
INSERT INTO CLASES (idClase, nombre, dado, idArquetipo, armas, armaduras) 
VALUES (03, 'Clï¿½rigo', '1d6', 103, HEXTORAW('64'), HEXTORAW('6'));
INSERT INTO CLASES (idClase, nombre, dado, idArquetipo, armas, armaduras) 
VALUES (04, 'Explorador', '1d6', 104, HEXTORAW('88'), HEXTORAW('4'));
INSERT INTO CLASES (idClase, nombre, dado, idArquetipo, armas, armaduras) 
VALUES (05, 'Guerrero', '1d8', 105, HEXTORAW('FC'), HEXTORAW('7'));
INSERT INTO CLASES (idClase, nombre, dado, idArquetipo, armas, armaduras) 
VALUES (06, 'Mago', '1d4', 106, HEXTORAW('0'), HEXTORAW('4'));
INSERT INTO CLASES (idClase, nombre, dado, idArquetipo, armas, armaduras) 
VALUES (07, 'Paladï¿½n', '1d8', 107, HEXTORAW('74'), HEXTORAW('7'));
INSERT INTO CLASES (idClase, nombre, dado, idArquetipo, armas, armaduras) 
VALUES (08, 'Pï¿½caro', '1d6', 108, HEXTORAW('C8'), HEXTORAW('4'));

/*Subclases*/
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (001, 01, 'Berseker');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (002, 01, 'Tribal');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (003, 01, 'Hermitaï¿½o');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (004, 02, 'Artista acadï¿½mico');
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
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (021, 07, 'Jurado de inspiraciï¿½n');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (022, 07, 'Jurado de triunfo');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (023, 08, 'Asesino');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (024, 08, 'Ladrï¿½n');
INSERT INTO SUBCLASES (idSubclase, idClase, nombre) VALUES (025, 08, 'Bribï¿½n');

INSERT INTO RELIGIONES (nombre) VALUES ('Lapidario');
INSERT INTO RELIGIONES (nombre) VALUES ('Husave');
INSERT INTO RELIGIONES (nombre) VALUES ('Seguidor de los Tres Grandes');
INSERT INTO RELIGIONES (nombre) VALUES ('Triunvitario');
INSERT INTO RELIGIONES (nombre) VALUES ('Hereje Antiguo');
INSERT INTO RELIGIONES (nombre) VALUES ('Orador de Borus');
INSERT INTO RELIGIONES (nombre) VALUES ('Herï¿½tico Insentiente');
INSERT INTO RELIGIONES (nombre) VALUES ('Adorador del Retorno');
INSERT INTO RELIGIONES (nombre) VALUES ('Testï¿½gos del ï¿½ltimo Heroe');
INSERT INTO RELIGIONES (nombre) VALUES ('Jaoblita');
INSERT INTO RELIGIONES (nombre) VALUES ('Sendas Domerin');
INSERT INTO RELIGIONES (nombre) VALUES ('Orador de Ascuas');
INSERT INTO RELIGIONES (nombre) VALUES ('Auscultor de Susurros'); 

INSERT INTO O_NACIONES (nombre) VALUES ('Nordast');
INSERT INTO O_NACIONES (nombre) VALUES ('Imperio Sirivi');
INSERT INTO O_NACIONES (nombre) VALUES ('Oncacia');
INSERT INTO O_NACIONES (nombre) VALUES ('Dornan');
INSERT INTO O_NACIONES (nombre) VALUES ('Thurimgard');
INSERT INTO O_NACIONES (nombre) VALUES ('Innon');
INSERT INTO O_NACIONES (nombre) VALUES ('Patria Nagi');
INSERT INTO O_NACIONES (nombre) VALUES ('Santuario');
INSERT INTO O_NACIONES (nombre) VALUES ('Cuna de Karkos');
INSERT INTO O_NACIONES (nombre) VALUES ('Islas de Mend Hevak');
INSERT INTO O_NACIONES (nombre) VALUES ('Archipiï¿½lago Coralino');
INSERT INTO O_NACIONES (nombre) VALUES ('Shival Rag');

INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '1'), 'Illsur');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '1'), 'Arforth');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '1'), 'Seregoth');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '1'), 'Tyrisan');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '1'), 'Merysan');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '1'), 'Varmosa');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '1'), 'Myrhai');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '2'), 'Mullsari');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '2'), 'Tottem');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '2'), 'Abulyar');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '2'), 'Tahale');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '2'), 'Raftal');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '3'), 'Oncacia');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '3'), 'Shencacia');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '3'), 'Hashen');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '3'), 'Nu-Hu-Dome');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '4'), 'Dornan');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '4'), 'Morrag');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '4'), 'Dendur');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '4'), 'Adarken');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '5'), 'Arandur');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '5'), 'Dardros');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '5'), 'Rukmol');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '5'), 'Ringen');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '6'), 'Innonkai');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '6'), 'Innonen');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '7'), 'Nagihara');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '7'), 'Zezerin');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '8'), 'Santuario');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '9'), 'Tï¿½merax');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '9'), 'Orgona');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '9'), 'Cï¿½netra');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '9'), 'Gargatra');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '9'), 'Apï¿½trax');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '9'), 'Krotï¿½rax');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '9'), 'Krin');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '9'), 'Omn');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '9'), 'Umbramar');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '9'), 'Sufrï¿½xis');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '10'), 'Tierras de la Peste');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '11'), 'Archipiï¿½lago Coralino');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '12'), 'Shival Rag');

INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Nordast'), 'Unionista');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Nordast'), 'Independentista de Voran');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Nordast'), 'Fiel a la nueva monarquï¿½a');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Nordast'), 'Varmosano');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Nordast'), 'Tyrisano');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Nordast'), 'Merysano');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Nordast'), 'Arfortino');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Nordast'), 'Seregodiense');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Nordast'), 'La mueca');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Nordast'), 'Aulladores');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Nordast'), 'Guardia dorada');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Nordast'), 'Seras Sanctum');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Oncacia'), 'Oncai-lis');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Oncacia'), 'Shen-lis');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Oncacia'), 'Hashen-lis');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Oncacia'), 'Nuhu-lis');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Oncacia'), 'Defensor de las Provincias');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Oncacia'), 'Oncai-lis');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Oncacia'), 'Dominio soberano');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Dornan'), 'Orok''Vaar');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Dornan'), 'Er''Meduk');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Dornan'), 'Orok''Abu');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Dornan'), 'Kalag''Ar');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Dornan'), 'Gar''Ken');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Dornan'), 'Godo-Go''Ke');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Dornan'), 'Orok''Ogeraru');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Dornan'), 'Tkiarug');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Dornan'), 'Dkirg-Re''Ko');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Dornan'), 'Skulkar Moiso');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Dornan'), 'Warg-Soaro');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Dornan'), 'Oo-Dotergo');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Dornan'), 'Ole-Mokare');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Dornan'), 'Kaaru''Vaar');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Dornan'), 'T''Grull-Soru');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Dornan'), 'Duse-Derargo');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Dornan'), 'Ketorog');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Dornan'), 'Okug-Odeku');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Dornan'), 'Moï¿½ug');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Dornan'), 'Diekrug-Ook''Ug');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Imperio Sirivi'), 'Ha-mullsar');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Imperio Sirivi'), 'Ha-tahal');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Imperio Sirivi'), 'Ha-raftal');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Imperio Sirivi'), 'Ha-abulyar');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Imperio Sirivi'), 'Ha-tottem');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Imperio Sirivi'), 'Surcador de Dunas');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Imperio Sirivi'), 'Adorador de la Arena');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Imperio Sirivi'), 'Carcelero de Nahasiv');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Imperio Sirivi'), 'Orden de la Cadena');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Imperio Sirivi'), 'Velazul');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Imperio Sirivi'), 'Pacto de Piedra');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Imperio Sirivi'), 'Imperial');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Imperio Sirivi'), 'El Dictamen');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Imperio Sirivi'), 'Camara Husavek');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Imperio Sirivi'), 'Mano de Mun-Yair');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Imperio Sirivi'), 'Hijo de la Repï¿½blica');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Santuario'), 'Explorador de Fronteras');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Santuario'), 'Preservador de Vidas');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Santuario'), 'Buscador de Verdad');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Santuario'), 'Reclmador de Justicia');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Santuario'), 'Cultivador de Diplomacia');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Santuario'), 'Guardian del Hogar');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Thurimgard'), 'Arandur Breno');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Thurimgard'), 'Dardros Breno');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Thurimgard'), 'Rukmol Breno');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Thurimgard'), 'Ringen Breno');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Thurimgard'), 'Grumble');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Thurimgard'), 'Pacto de la Forja');

INSERT INTO OBJETOS (rareza, nombre, valor, peso, token) VALUES ('Común', 'Gorro', 25, 1, '/icons/gorro.png');
INSERT INTO ARMADURAS (idObjeto, tipo, resistencia) VALUES ((SELECT idObjeto FROM OBJETOS WHERE nombre = 'Gorro'), 'Casco', hextoraw('0000'));

INSERT INTO OBJETOS (rareza, nombre, valor, peso, token) VALUES ('Raro', 'Casco de Hierro', 25, 1, '/icons/casco_hierro.png');
INSERT INTO ARMADURAS (idObjeto, tipo, resistencia) VALUES ((SELECT idObjeto FROM OBJETOS WHERE nombre = 'Casco de Hierro'), 'Casco', hextoraw('0000'));

INSERT INTO OBJETOS (rareza, nombre, valor, peso, token) VALUES ('Común', 'Casco de Mithril', 25, 1, '/icons/casco_mithril.png');
INSERT INTO ARMADURAS (idObjeto, tipo, resistencia) VALUES ((SELECT idObjeto FROM OBJETOS WHERE nombre = 'Casco de Mithril'), 'Casco', hextoraw('0000'));

INSERT INTO OBJETOS (rareza, nombre, valor, peso, token) VALUES ('Común', 'Camisa', 25, 1, '/icons/camisa.png');
INSERT INTO ARMADURAS (idObjeto, tipo, resistencia) VALUES ((SELECT idObjeto FROM OBJETOS WHERE nombre = 'Camisa'), 'Coraza', hextoraw('0000'));

INSERT INTO OBJETOS (rareza, nombre, valor, peso, token) VALUES ('Común', 'Coraza de Cuero', 25, 1, '/icons/coraza_cuero.png');
INSERT INTO ARMADURAS (idObjeto, tipo, resistencia) VALUES ((SELECT idObjeto FROM OBJETOS WHERE nombre = 'Coraza de Cuero'), 'Coraza', hextoraw('0000'));

INSERT INTO OBJETOS (rareza, nombre, valor, peso, token) VALUES ('Raro', 'Coraza de Hierro', 25, 1, '/icons/coraza_hierro.png');
INSERT INTO ARMADURAS (idObjeto, tipo, resistencia) VALUES ((SELECT idObjeto FROM OBJETOS WHERE nombre = 'Coraza de Hierro'), 'Coraza', hextoraw('0000'));

INSERT INTO OBJETOS (rareza, nombre, valor, peso, token) VALUES ('Común', 'Botas de Cuero', 25, 1, '/icons/botas_cuero.png');
INSERT INTO ARMADURAS (idObjeto, tipo, resistencia) VALUES ((SELECT idObjeto FROM OBJETOS WHERE nombre = 'Botas de Cuero'), 'Botas', hextoraw('0000'));

INSERT INTO OBJETOS (rareza, nombre, valor, peso, token) VALUES ('Raro', 'Botas de Hierro', 25, 1, '/icons/botas_hierro.png');
INSERT INTO ARMADURAS (idObjeto, tipo, resistencia) VALUES ((SELECT idObjeto FROM OBJETOS WHERE nombre = 'Botas de Hierro'), 'Botas', hextoraw('0000'));

INSERT INTO OBJETOS (rareza, nombre, valor, peso, token) VALUES ('Épico', 'Botas de Mithril', 25, 1, '/icons/botas_mithril.png');
INSERT INTO ARMADURAS (idObjeto, tipo, resistencia) VALUES ((SELECT idObjeto FROM OBJETOS WHERE nombre = 'Botas de Mithril'), 'Botas', hextoraw('0000'));

INSERT INTO OBJETOS (rareza, nombre, valor, peso, token) VALUES ('Raro', 'Espada Larga', 150, 3, '/icons/espada_larga.png');
INSERT INTO ARMAS (idObjeto, dado, tipoDanio) VALUES ((SELECT idObjeto FROM OBJETOS WHERE nombre = 'Espada Larga'), '1d6', 'Lacerante');

INSERT INTO OBJETOS (rareza, nombre, valor, peso, token) VALUES ('Común', 'Daga', 50, 2, '/icons/daga.png');
INSERT INTO ARMAS (idObjeto, dado, tipoDanio) VALUES ((SELECT idObjeto FROM OBJETOS WHERE nombre = 'Daga'), '1d4', 'Lacerante');

INSERT INTO OBJETOS (rareza, nombre, valor, peso, token) VALUES ('Raro', 'Hacha de Guerra', 200, 5, '/icons/hacha_guerra.png');
INSERT INTO ARMAS (idObjeto, dado, tipoDanio) VALUES ((SELECT idObjeto FROM OBJETOS WHERE nombre = 'Hacha de Guerra'), '1d8', 'Lacerante');


COMMIT;

