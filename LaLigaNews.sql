-- Borrar la base de datos LaLigaNews si ya existe
DROP DATABASE IF EXISTS LaLigaNews;

-- Crear la base de datos LaLigaNews
CREATE DATABASE LaLigaNews;

-- Usar la base de datos LaLigaNews
USE LaLigaNews;

-- Crear la tabla Partido
CREATE TABLE Partido (
    id INT AUTO_INCREMENT,
    jornada INT NOT NULL,
    equipo_local TEXT NOT NULL,
    equipo_visitante TEXT NOT NULL,
    resultado TEXT NOT NULL,
    PRIMARY KEY (id)
);

-- Crear la tabla Noticia
CREATE TABLE Noticia (
    id INT AUTO_INCREMENT,
    titulo TEXT NOT NULL,
    texto TEXT NOT NULL,
    imagen TEXT NOT NULL,
    PRIMARY KEY (id)
);