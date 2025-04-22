/*DROPS DEL SCRIPT*/
DROP TRIGGER ideologias_trigger;
DROP SEQUENCE ideologias_seq;
DROP TRIGGER reinos_trigger;
DROP SEQUENCE reinos_seq;
DROP TRIGGER religiones_trigger;
DROP SEQUENCE religiones_seq;
DROP TRIGGER razas_trigger;
DROP SEQUENCE razas_seq;
DROP TRIGGER objetos_trigger;
DROP SEQUENCE objetos_seq;
DROP TRIGGER habilidades_trigger;
DROP SEQUENCE habilidades_seq;
DROP TRIGGER personajes_trigger;
DROP SEQUENCE personajes_seq;

DROP TABLE INVENTARIO_PERSONAJE;
DROP TABLE EQUIPO_PERSONAJE;
DROP TABLE RASGOS_PERSONAJES;
DROP TABLE VENTAJAS_PERSONAJES;
DROP TABLE HECHIZOS_PERSONAJES;
DROP TABLE DONES_PERSONAJES;
DROP TABLE TALENTOS_PERSONAJES;
DROP TABLE HECHIZOS_CLASES;
DROP TABLE RASGOS_CLASES;
DROP TABLE RASGOS_SUBCLASES;
DROP TABLE PERSONAJE_IDEOLOGIA;

DROP TABLE VENTAJAS;
DROP TABLE DONES;
DROP TABLE TALENTOS;
DROP TABLE PERSONAJES;
DROP TABLE HECHIZOS;
DROP TABLE ARMADURAS;
DROP TABLE ARMAS;
DROP TABLE OBJETOS;
DROP TABLE SUBCLASES;
DROP TABLE CLASES;
DROP TABLE IDEOLOGIAS;
DROP TABLE REINOS;
DROP TABLE RELIGIONES;
DROP TABLE RAZAS;
DROP TABLE ARQUETIPOS;
DROP TABLE RASGOS;
DROP TABLE HABILIDADES;

/*CREACIÓN DE ENTIDADES*/

CREATE TABLE HABILIDADES (
    idHabilidad NUMBER CONSTRAINT pk_habilidades PRIMARY KEY,
    nombre VARCHAR(20) CONSTRAINT nn_nombre_hab NOT NULL,
    descripcion VARCHAR(500) CONSTRAINT nn_desc_hab NOT NULL,
    activa NUMBER(1) CONSTRAINT nn_activa_hab NOT NULL
);

CREATE TABLE RASGOS (
    idHabilidad NUMBER CONSTRAINT pk_rasgos PRIMARY KEY,
    unlockLvl NUMBER CONSTRAINT nn_unlock_ras NOT NULL,
    CONSTRAINT habilidad_rasgo_fk FOREIGN KEY (idHabilidad) REFERENCES HABILIDADES(idHabilidad)
);

CREATE TABLE ARQUETIPOS (
    idArquetipo NUMBER(3) CONSTRAINT pk_arquetipo PRIMARY KEY,
    fuerza NUMBER(2) CONSTRAINT nn_fuer_arq NOT NULL,
    constitucion NUMBER(2) CONSTRAINT nn_cons_arq NOT NULL,
    destreza NUMBER(2) CONSTRAINT nn_dest_arq NOT NULL,
    inteligencia NUMBER(2) CONSTRAINT nn_inte_arq NOT NULL,
    sabiduria NUMBER(2) CONSTRAINT nn_sabi_arq NOT NULL,
    carisma NUMBER(2) CONSTRAINT nn_cari_arq NOT NULL,
    iniciativa NUMBER(2) CONSTRAINT nn_inic_arq NOT NULL,
    aura NUMBER(2) CONSTRAINT nn_aura_arq NOT NULL,
    movimiento NUMBER(2) CONSTRAINT nn_movi_arq NOT NULL
);

CREATE TABLE RAZAS (
    idRaza NUMBER CONSTRAINT pk_razas PRIMARY KEY,
    nombre VARCHAR2(20) CONSTRAINT nn_nombre_raz NOT NULL,
    idArquetipo NUMBER,
    idRasgo NUMBER,
    CONSTRAINT rasgo_raza_kf FOREIGN KEY (idRasgo) REFERENCES RASGOS(idHabilidad),
    CONSTRAINT arquetipo_raza_fk FOREIGN KEY (idArquetipo) REFERENCES ARQUETIPOS(idArquetipo)
);

CREATE TABLE RELIGIONES (
    idReligion NUMBER CONSTRAINT pk_religiones PRIMARY KEY,
    nombre VARCHAR2(30) CONSTRAINT nn_nombre_rel NOT NULL
);

CREATE TABLE REINOS (
    idReino NUMBER CONSTRAINT pk_reinos PRIMARY KEY,
    nombre VARCHAR2(30) CONSTRAINT nn_nombre_rei NOT NULL
);

CREATE TABLE IDEOLOGIAS (
    idIdeologia NUMBER CONSTRAINT pk_ideologia PRIMARY KEY,
    idReino NUMBER CONSTRAINT nn_reino_ide NOT NULL,
    nombre VARCHAR2(30) CONSTRAINT nn_nombre_ide NOT NULL,
    CONSTRAINT reino_ideologia FOREIGN KEY (idReino) REFERENCES REINOS(idReino)
);

CREATE TABLE CLASES (
    idClase NUMBER CONSTRAINT pk_clase PRIMARY KEY,
    nombre VARCHAR2 (255) CONSTRAINT nn_nombre_cl NOT NULL,
    dado VARCHAR2 (5) CONSTRAINT nn_dado_cl NOT NULL,
    idArquetipo NUMBER CONSTRAINT nn_idarq_cla NOT NULL,
    armas RAW(1),
    armaduras RAW(1),
    
    CONSTRAINT arquetipo_clase_fk FOREIGN KEY (idArquetipo) REFERENCES ARQUETIPOS(idArquetipo)
    /*RELACIÓN CON HECHIZOS_CLASES*/
    /*RELACIÓN CON RASGOS_CLASES*/
    /*RELACIÓN CON SUBCLASES*/
);

CREATE TABLE SUBCLASES (
    idSubclase NUMBER CONSTRAINT pk_subclases PRIMARY KEY,
    idClase NUMBER CONSTRAINT nn_idclase_sub NOT NULL,
    nombre VARCHAR2 (255) CONSTRAINT nn_nombre_sc NOT NULL,
    CONSTRAINT clase_subclase_fk FOREIGN KEY(idClase) REFERENCES CLASES(idClase)
    /*RELACIÓN CON TABLA RASGOS_SUBCLASE*/
);

CREATE TABLE OBJETOS (
    idObjeto NUMBER CONSTRAINT pk_objetos PRIMARY KEY,
    rareza VARCHAR2(20) CONSTRAINT nn_rareza_obj NOT NULL,
    nombre VARCHAR2(20) CONSTRAINT nn_nombre_obj NOT NULL,
    valor NUMBER CONSTRAINT nn_valor_obj NOT NULL,
    peso NUMBER CONSTRAINT nn_peso_obj NOT NULL
);

CREATE TABLE ARMAS (
    idObjeto NUMBER CONSTRAINT un_idobjeto_arm UNIQUE,
    dado VARCHAR2(5) CONSTRAINT nn_dado_arma NOT NULL,
    tipoDanio VARCHAR(20) CONSTRAINT nn_tipod_arma NOT NULL,
    CONSTRAINT objetos_armas_fk FOREIGN KEY (idObjeto) REFERENCES OBJETOS(idObjeto)
);

CREATE TABLE ARMADURAS (
    idObjeto NUMBER CONSTRAINT un_idobjeto_armd UNIQUE,
    tipo VARCHAR2(20) CONSTRAINT nn_tipo_armd NOT NULL,
    resistencia RAW(2),
    CONSTRAINT objetos_armaduras_fk FOREIGN KEY (idObjeto) REFERENCES OBJETOS(idObjeto)
);

CREATE TABLE HECHIZOS (
    idHabilidad NUMBER CONSTRAINT pk_hechizos PRIMARY KEY,
    costeAura NUMBER CONSTRAINT nn_coste_hec NOT NULL,
    tipoLanzamiento NUMBER CONSTRAINT nn_tipolance_hec NOT NULL,
    preparacion NUMBER CONSTRAINT nn_preparacion_hec NOT NULL,
    recuperacion NUMBER CONSTRAINT nn_recuperacion_hec NOT NULL,
    usos NUMBER CONSTRAINT nn_usos_hec NOT NULL,
    nivel NUMBER CONSTRAINT nn_nivel_hec NOT NULL,
    dado VARCHAR2(5) CONSTRAINT nn_dado_hec NOT NULL,
    rango NUMBER CONSTRAINT nn_rango_hec NOT NULL,
    CONSTRAINT habilidades_hechizos FOREIGN KEY (idHabilidad) REFERENCES HABILIDADES (idHabilidad)
);

CREATE TABLE PERSONAJES (
    /*Identificador*/
    idPersonaje NUMBER CONSTRAINT pk_personaje PRIMARY KEY,

    /*Datos temáticos*/
    nombre VARCHAR2(255) CONSTRAINT nn_nombre_pj NOT NULL,
    idRaza NUMBER CONSTRAINT nn_raza_pj NOT NULL,
    sexo VARCHAR2(255) CONSTRAINT nn_sexo_pj NOT NULL,
    idReligion NUMBER CONSTRAINT nn_religion_pj NOT NULL,
    idReino NUMBER CONSTRAINT nn_reino_pj NOT NULL,
    edad NUMBER CONSTRAINT nn_edad_pj NOT NULL,

    /*Datos de clase*/
    idClase NUMBER CONSTRAINT nn_idclase_pj NOT NULL, /*FOREIGN KEY*/
    idSubclase NUMBER, /*FOREIGN KEY*/

    /*Datos estadisticos referenciados desde tabla ESTADISTICAS*/
    estadoJson CLOB, /*Json con todos los datos de estadísticas, estados y buffos*/

    /*Datos de progreso y habilidades*/
    oro NUMBER DEFAULT 30,
    experiencia NUMBER DEFAULT 0,
    nivel NUMBER DEFAULT 1,
    armas RAW(1),
    armaduras RAW(1), 
    /*RELACIÓN TALENTOS_PERSONAJES*/
    /*RELACIÓN DONES_PERSONAJES*/
    /*RELACIÓN HECHIZOS_PERSONAJES*/
    /*RELACIÓN VENTAJAS_PERSONAJES*/
    /*RELACIÓN RASGOS_PERSONAJES*/
    /*RELACIÓN EQUIPO_PERSONAJES*/
    /*RELACIÓN INVENTARIO_PERSONAJES*/
    
    /*Claves foraneas*/
    CONSTRAINT reino_personaje_fk FOREIGN KEY (idReino) REFERENCES REINOS(idReino),
    CONSTRAINT religion_personaje_fk FOREIGN KEY (idReligion) REFERENCES RELIGIONES(idReligion),
    CONSTRAINT raza_personaje_fk FOREIGN KEY (idRaza) REFERENCES RAZAS(idRaza),
    CONSTRAINT clase_personaje_fk FOREIGN KEY (idClase) REFERENCES CLASES(idClase),
    CONSTRAINT subclase_personaje_fk FOREIGN KEY (idSubclase) REFERENCES SUBCLASES(idSubclase)
);

CREATE TABLE TALENTOS (
    idHabilidad NUMBER CONSTRAINT pk_talentos PRIMARY KEY,
    idClase NUMBER CONSTRAINT nn_idclase_tal NOT NULL,
    tipoLanzamiento NUMBER CONSTRAINT nn_tipolance_tal NOT NULL,
    preparacion NUMBER CONSTRAINT nn_preparacion_tal NOT NULL,
    recuperacion NUMBER CONSTRAINT nn_recuperacion_tal NOT NULL,
    usos NUMBER CONSTRAINT nn_usos_tal NOT NULL,
    CONSTRAINT habilidad_talento FOREIGN KEY (idHabilidad) REFERENCES HABILIDADES(idHabilidad),
    CONSTRAINT clase_talento FOREIGN KEY (idClase) REFERENCES CLASES(idClase)
    /*RELACIÓN TALENTOS_PERSONAJES*/
);

CREATE TABLE DONES (
    idHabilidad NUMBER CONSTRAINT pk_dones PRIMARY KEY,
    preparacion NUMBER CONSTRAINT nn_preparacion_don NOT NULL,
    usos NUMBER CONSTRAINT nn_usos_don NOT NULL,
    CONSTRAINT habilidad_don_fk FOREIGN KEY (idHabilidad) REFERENCES HABILIDADES(idHabilidad)
);

CREATE TABLE VENTAJAS (
    idHabilidad NUMBER CONSTRAINT pk_ventajas PRIMARY KEY,
    requisito NUMBER CONSTRAINT nn_requisito_ven NOT NULL,
    raiz VARCHAR(20) CONSTRAINT nn_raiz_ven NOT NULL,
    CONSTRAINT habilidad_ventaja_fk FOREIGN KEY (idHabilidad) REFERENCES HABILIDADES(idHabilidad)
);

/*CREACIÓN DE TABLAS RELACIONALES*/

CREATE TABLE PERSONAJE_IDEOLOGIA (
    idPersonaje NUMBER,
    idIdeologia NUMBER,
    CONSTRAINT personaje_ideologia_fk FOREIGN KEY (idPersonaje) REFERENCES PERSONAJES(idPersonaje) ON DELETE CASCADE,
    CONSTRAINT ideologia_personaje_fk FOREIGN KEY (idIdeologia) REFERENCES IDEOLOGIAS(idIdeologia) ON DELETE CASCADE
);

CREATE TABLE RASGOS_SUBCLASES (
    idSubclase NUMBER,
    idRasgo NUMBER,
    CONSTRAINT rasgos_subclases_fk FOREIGN KEY (idRasgo) REFERENCES RASGOS (idHabilidad),
    CONSTRAINT subclase_rasgo_fk FOREIGN KEY (idSubclase) REFERENCES SUBCLASES (idSubclase)
);

CREATE TABLE RASGOS_CLASES (
    idClase NUMBER,
    idRasgo NUMBER,
    CONSTRAINT rasgos_clases_fk FOREIGN KEY (idRasgo) REFERENCES RASGOS (idHabilidad),
    CONSTRAINT clases_rasgos_fk FOREIGN KEY (idClase) REFERENCES CLASES (idClase)
);

CREATE TABLE HECHIZOS_CLASES (
    idClase NUMBER,
    idHechizo NUMBER,
    CONSTRAINT hechizos_clases_fk FOREIGN KEY (idHechizo) REFERENCES HECHIZOS (idHabilidad),
    CONSTRAINT clases_hechizos_fk FOREIGN KEY (idClase) REFERENCES CLASES (idClase)
);

CREATE TABLE TALENTOS_PERSONAJES (
    idTalento NUMBER,
    idPersonaje NUMBER,
    CONSTRAINT talento_personaje_fk FOREIGN KEY (idTalento) REFERENCES TALENTOS(idHabilidad),
    CONSTRAINT personaje_talento_fk FOREIGN KEY (idPersonaje) REFERENCES PERSONAJES(idPersonaje)
);

CREATE TABLE DONES_PERSONAJES (
    idDon NUMBER,
    idPersonaje NUMBER,
    CONSTRAINT don_personaje_fk FOREIGN KEY (idDon) REFERENCES DONES(idHabilidad),
    CONSTRAINT personaje_don_fk FOREIGN KEY (idPersonaje) REFERENCES PERSONAJES(idPersonaje)
);

CREATE TABLE HECHIZOS_PERSONAJES (
    idHechizo NUMBER,
    idPersonaje NUMBER,
    CONSTRAINT hechizo_personaje_fk FOREIGN KEY (idHechizo) REFERENCES HECHIZOS(idHabilidad),
    CONSTRAINT personaje_hechizo_fk FOREIGN KEY (idPersonaje) REFERENCES PERSONAJES(idPersonaje)
);

CREATE TABLE VENTAJAS_PERSONAJES (
    idVentaja NUMBER,
    idPersonaje NUMBER,
    CONSTRAINT ventajas_personaje_fk FOREIGN KEY (idVentaja) REFERENCES VENTAJAS(idHabilidad),
    CONSTRAINT personaje_ventajas_fk FOREIGN KEY (idPersonaje) REFERENCES PERSONAJES(idPersonaje)
);

CREATE TABLE RASGOS_PERSONAJES (
    idRasgo NUMBER,
    idPersonaje NUMBER,
    CONSTRAINT rasgo_personaje_fk FOREIGN KEY (idRasgo) REFERENCES RASGOS(idHabilidad),
    CONSTRAINT personaje_rasgo_fk FOREIGN KEY (idPersonaje) REFERENCES PERSONAJES(idPersonaje)
);

CREATE TABLE EQUIPO_PERSONAJE (
    idObjeto NUMBER,
    idPersonaje NUMBER,
    CONSTRAINT equipo_personaje_fk FOREIGN KEY (idObjeto) REFERENCES OBJETOS(idObjeto),
    CONSTRAINT personaje_equipo_fk FOREIGN KEY (idPersonaje) REFERENCES PERSONAJES(idPersonaje)
);

CREATE TABLE INVENTARIO_PERSONAJE (
    idObjeto NUMBER,
    idPersonaje NUMBER,
    CONSTRAINT invent_personaje_fk FOREIGN KEY (idObjeto) REFERENCES OBJETOS(idObjeto),
    CONSTRAINT personaje_invent_fk FOREIGN KEY (idPersonaje) REFERENCES PERSONAJES(idPersonaje)
);

/*CREACIÓN DE TRIGGERS Y SECUENCIAS*/
CREATE SEQUENCE personajes_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER personajes_trigger
BEFORE INSERT ON PERSONAJES
FOR EACH ROW
BEGIN
    IF :NEW.idPersonaje IS NULL THEN
        SELECT personajes_seq.NEXTVAL INTO :NEW.idPersonaje FROM DUAL;
    END IF;
END;
/

CREATE SEQUENCE habilidades_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER habilidades_trigger
BEFORE INSERT ON HABILIDADES
FOR EACH ROW
BEGIN
    IF :NEW.idHabilidad IS NULL THEN
        SELECT habilidades_seq.NEXTVAL INTO :NEW.idHabilidad FROM DUAL;
    END IF;
END;
/

CREATE SEQUENCE objetos_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER objetos_trigger
BEFORE INSERT ON OBJETOS
FOR EACH ROW
BEGIN
    IF :NEW.idObjeto IS NULL THEN
        SELECT objetos_seq.NEXTVAL INTO :NEW.idObjeto FROM DUAL;
    END IF;
END;
/

CREATE SEQUENCE razas_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER razas_trigger
BEFORE INSERT ON RAZAS
FOR EACH ROW
BEGIN
    IF :NEW.idRaza IS NULL THEN
        SELECT razas_seq.NEXTVAL INTO :NEW.idRaza FROM DUAL;
    END IF;
END;
/

CREATE SEQUENCE religiones_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER religiones_trigger
BEFORE INSERT ON RELIGIONES
FOR EACH ROW
BEGIN
    IF :NEW.idReligion IS NULL THEN
        SELECT religiones_seq.NEXTVAL INTO :NEW.idReligion FROM DUAL;
    END IF;
END;
/

CREATE SEQUENCE reinos_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER reinos_trigger
BEFORE INSERT ON REINOS
FOR EACH ROW
BEGIN
    IF :NEW.idReino IS NULL THEN
        SELECT reinos_seq.NEXTVAL INTO :NEW.idReino FROM DUAL;
    END IF;
END;
/

CREATE SEQUENCE ideologias_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER ideologias_trigger
BEFORE INSERT ON IDEOLOGIAS
FOR EACH ROW
BEGIN
    IF :NEW.idIdeologia IS NULL THEN
        SELECT ideologias_seq.NEXTVAL INTO :NEW.idIdeologia FROM DUAL;
    END IF;
END;
/