CREATE DATABASE IF NOT EXISTS biblioteca_db;
USE biblioteca_db;

-- ==========================
-- 1) Tabla FICHA BIBLIOGRÁFICA
-- ==========================

CREATE TABLE ficha_bibliografica (
    id INT AUTO_INCREMENT PRIMARY KEY,
    isbn VARCHAR(17) UNIQUE,
    clasificacion_dewey VARCHAR(20),
    estanteria VARCHAR(20),
    idioma VARCHAR(30),
    eliminado BOOLEAN NOT NULL DEFAULT FALSE
);

-- ==========================
-- 2) Tabla LIBRO 
-- ==========================
CREATE TABLE libro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(120) NOT NULL,
    editorial VARCHAR(100),
    anio_edicion INT,
    eliminado BOOLEAN NOT NULL DEFAULT FALSE,

    -- Relación 1 a 1
    ficha_id INT UNIQUE,
    CONSTRAINT fk_libro_ficha FOREIGN KEY (ficha_id)
        REFERENCES ficha_bibliografica(id)
        ON DELETE CASCADE
);

USE biblioteca_db;

-- ==========================
-- Insertar FICHAS
-- ==========================
INSERT INTO ficha_bibliografica (isbn, clasificacion_dewey, estanteria, idioma, eliminado)
VALUES
('978-84-376-0494-7', '863.44', 'A1', 'Español', FALSE),
('978-0-15-601219-5', '843.912', 'B2', 'Francés', FALSE),
('978-0-452-28423-4', '823.912', 'C3', 'Inglés', FALSE);

-- ==========================
-- Insertar LIBROS
-- Cada libro referencia la ficha creada arriba
-- ==========================
INSERT INTO libro (titulo, autor, editorial, anio_edicion, eliminado, ficha_id)
VALUES
('Cien años de soledad', 'Gabriel García Márquez', 'Sudamericana', 1967, FALSE, 1),
('El Principito', 'Antoine de Saint-Exupéry', 'Reynal & Hitchcock', 1943, FALSE, 2),
('1984', 'George Orwell', 'Secker & Warburg', 1949, FALSE, 3);

