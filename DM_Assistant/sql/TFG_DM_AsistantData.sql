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
VALUES ('Vigor del norte', 'Podrás realizar un desafï¿½o de Resistencia (15) para ignorar cualquier estado físico que te provoquen. Si falla no podrás volver a intentar retirarlo con este método hasta tu siguiente descanso largo.', 0);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES ((SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Vigor del norte'), 1);

INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES ('Brutalidad', 'Podrás aplicar hasta 3 bonificador de 1d6 a tus desafíos de Fuerza o Constitución por cada descanso largo.', 1);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES ((SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Brutalidad'), 1);

/* Rasgo de bárbaro */
INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES (
  'Furia de batalla',
  'Cada vez que golpees a un objetivo, obtendrás 2 puntos de furia hasta un máximo de 10. Podrás entrar en estado de furia para consumir tus puntos de furia y lanzar tus talentos de bárbaro. Una vez que termine el estado de furia, se perderán todos los puntos acumulados. El estado de furia terminará automáticamente si no intentas golpear a un objetivo en 3 turnos (1:30 min).',
  0
);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES (
  (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Furia de batalla'),
  1
);
INSERT INTO RASGOS_CLASES (idClase, idRasgo)
VALUES (
  (SELECT idClase FROM CLASES WHERE nombre = 'Bárbaro'),
  (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Furia de batalla')
);

/* Rasgo de bardo */
INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES (
  'Conjuración musical',
  'Descubres el poder que radica en la recitación y la musicalidad, y hallas la manera de manifestarlo a través de tus poemas, cantos y canciones. Cuando lances un hechizo que aplique Comunicación Arcana, en su lugar aplicará tu bonificador de Jovialidad.',
  0
);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES (
  (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Conjuración musical'),
  1
);
INSERT INTO RASGOS_CLASES (idClase, idRasgo)
VALUES (
  (SELECT idClase FROM CLASES WHERE nombre = 'Bardo'),
  (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Conjuración musical')
);

/* Rasgo de clérigo (y paladín) */
INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES (
  'Fidelidad al dogma',
  'De entre todos los caminos que puede tomar una persona, tú escogiste el de la fe. Has dedicado tu vida al estudio de una religión, comulgas con sus enseñanzas y las promueves por el mundo. Ahora, esta no solo ocupa un lugar importante en tu vida, sino también en tu corazón. Declara tu fe por un dogma: obtendrás acceso tanto a los hechizos de fe como a los de tu dogma, de acuerdo con tu progreso. Obtienes el don: Símbolo del dogma.',
  0
);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES (
  (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Fidelidad al dogma'),
  1
);
INSERT INTO RASGOS_CLASES (idClase, idRasgo)
VALUES (
  (SELECT idClase FROM CLASES WHERE nombre = 'Clérigo'),
  (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Fidelidad al dogma')
);
INSERT INTO RASGOS_CLASES (idClase, idRasgo)
VALUES (
  (SELECT idClase FROM CLASES WHERE nombre = 'Paladín'),
  (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Fidelidad al dogma')
);

/* Rasgo de explorador */
INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES (
  'Dominio de exploración',
  'Obtienes acceso a los hechizos de explorador. Obtienes el don: Dominio de exploración.',
  0
);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES (
  (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Dominio de exploración'),
  1
);
INSERT INTO RASGOS_CLASES (idClase, idRasgo)
VALUES (
  (SELECT idClase FROM CLASES WHERE nombre = 'Explorador'),
  (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Dominio de exploración')
);

/* Rasgo de guerrero */
INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES (
  'Estilo de combate',
  'Obtienes un estilo de combate y acceso a los talentos de guerrero. Obtendrás tu foco de batalla, que acumulará un punto cada vez que realices una acción de combate exitosa, y perderás un punto cada vez que fracases. Si recibes daño psíquico o te afecta alguna habilidad de control mental o ilusión, perderás todo tu foco de batalla. Obtienes el don: Estilo de combate.',
  0
);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES (
  (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Estilo de combate'),
  1
);
INSERT INTO RASGOS_CLASES (idClase, idRasgo)
VALUES (
  (SELECT idClase FROM CLASES WHERE nombre = 'Guerrero'),
  (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Estilo de combate')
);

/* Rasgo de mago */
INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES (
  'Arcanum de hechicería',
  'El Arcanum guarda tus hechizos. Puedes copiar nuevos durante descansos largos superando un desafío (nivel del hechizo + 2d6). Al lograrlo, se revela el coste: (10 + nivel) × 4 + 4d4 - nivel de mago. Si no tienes fondos, deberás repetir todo el proceso en otro descanso.',
  0
);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES (
  (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Arcanum de hechicería'),
  1
);
INSERT INTO RASGOS_CLASES (idClase, idRasgo)
VALUES (
  (SELECT idClase FROM CLASES WHERE nombre = 'Mago'),
  (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Arcanum de hechicería')
);

/* Rasgo de pícaro */
INSERT INTO HABILIDADES (nombre, descripcion, activa) 
VALUES (
  'Actos de travesura',
  'Obtienes acceso a los talentos de pícaro y el don: Herramientas de pillastre. Durante tu turno, tendrás una acción adicional llamada Travesura, que podrás usar para correr, saltar, ocultarte, recomponerte o realizar piruetas.',
  0
);
INSERT INTO RASGOS (idHabilidad, unlockLvl)
VALUES (
  (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Actos de travesura'),
  1
);
INSERT INTO RASGOS_CLASES (idClase, idRasgo)
VALUES (
  (SELECT idClase FROM CLASES WHERE nombre = 'Pícaro'),
  (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Actos de travesura')
);

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

/*Hechizos*/
INSERT INTO HABILIDADES (nombre, descripcion, activa) VALUES ('Bola de Fuego', 'Conjura una llama colérica entre tus manos para lanzarla contra un objeto a no más de 15m de tu posición', 1);
INSERT INTO HECHIZOS (idHabilidad, costeAura, tipoLanzamiento, preparacion, recuperacion, usos, nivel, dado, rango)
VALUES ((SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Bola de Fuego'), 10, 2, 1, 2, 4, 1, '2d10', 20);
INSERT INTO HECHIZOS_CLASES (idClase, idHechizo) VALUES ((SELECT idClase FROM CLASES WHERE nombre = 'Mago'), (SELECT idHabilidad FROM HABILIDADES WHERE nombre = 'Bola de Fuego'));

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
INSERT INTO O_NACIONES (nombre) VALUES ('Archipiélago Coralino');
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
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '9'), 'Témerax');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '9'), 'Orgona');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '9'), 'Cénetra');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '9'), 'Gargatra');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '9'), 'Apátrax');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '9'), 'Krotórax');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '9'), 'Krin');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '9'), 'Omn');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '9'), 'Umbramar');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '9'), 'Sufráxis');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '10'), 'Tierras de la Peste');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '11'), 'Archipiélago Coralino');
INSERT INTO O_REINOS (nombreNacion, nombre) VALUES ((SELECT nombre FROM O_NACIONES WHERE idNacion = '12'), 'Shival Rag');

INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Nordast'), 'Unionista');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Nordast'), 'Independentista de Voran');
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Nordast'), 'Fiel a la nueva monarquía');
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
INSERT INTO IDEOLOGIAS (idNacion, nombre) VALUES ((SELECT idNacion FROM O_NACIONES WHERE nombre = 'Imperio Sirivi'), 'Hijo de la República');
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

INSERT INTO OBJETOS (rareza, nombre, valor, peso, token) VALUES ('Común', 'Poción', 10, 1, '/icons/pocion.png');
INSERT INTO CONSUMIBLES (idObjeto, dado, tipo) VALUES ((SELECT idObjeto FROM OBJETOS WHERE nombre = 'Poción'), '1d8', 'Cura');

INSERT INTO OBJETOS (rareza, nombre, valor, peso, token) VALUES ('Raro', 'Gran Poción', 22, 1, '/icons/gran_pocion.png');
INSERT INTO CONSUMIBLES (idObjeto, dado, tipo) VALUES ((SELECT idObjeto FROM OBJETOS WHERE nombre = 'Gran Poción'), '2d8', 'Cura');

INSERT INTO OBJETOS (rareza, nombre, valor, peso, token) VALUES ('Común', 'Elixir', 10, 1, '/icons/elixir.png');
INSERT INTO CONSUMIBLES (idObjeto, dado, tipo) VALUES ((SELECT idObjeto FROM OBJETOS WHERE nombre = 'Elixir'), '1d8', 'Elixir');

INSERT INTO OBJETOS (rareza, nombre, valor, peso, token) VALUES ('Común', 'Gran Elixir', 10, 1, '/icons/gran_elixir.png');
INSERT INTO CONSUMIBLES (idObjeto, dado, tipo) VALUES ((SELECT idObjeto FROM OBJETOS WHERE nombre = 'Gran Elixir'), '2d8', 'Elixir');

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

