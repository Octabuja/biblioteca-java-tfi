📄 README – Sistema de Biblioteca (Java + MySQL)

Título del proyecto: Sistema de Gestión de Biblioteca
Tecnologías: Java + JDBC + MySQL
Arquitectura: En capas (Entities, DAO, Service, Main)
Relación: 1 a 1 unidireccional (Libro → FichaBibliografica)

## Descripción general

Este proyecto es una aplicación de consola desarrollada en Java que permite gestionar el catálogo de una biblioteca mediante operaciones CRUD (Crear, Leer, Actualizar y Eliminar) aplicadas sobre:

Libros

Fichas Bibliográficas

Cada libro posee una única ficha asociada, implementando una relación 1:1 unidireccional, donde el libro conoce a la ficha, pero la ficha no conoce al libro.

Además, se utiliza el mecanismo de baja lógica, lo que significa que los registros no son eliminados físicamente de la base, sino que se marcan mediante el atributo eliminado = TRUE.

## Funcionalidades

### Gestión de Fichas Bibliográficas

Crear nueva ficha

Buscar ficha por su ID

Listar todas las fichas activas

Editar información

Eliminar (baja lógica)

### Gestión de Libros

Crear libro (requiere ficha existente)

Buscar libro por ID

Listar libros activos

Editar información

Eliminar (baja lógica)

## Estructura del proyecto
src/

 ├─ config/                → Conexión JDBC (DatabaseConnection)

 ├─ dao/                   → Interfaces y clases DAO (acceso a BD)
 
 ├─ entities/              → Libro, FichaBibliografica, Base
 
 ├─ service/               → Reglas de negocio y validaciones

 └─ main/                  → AppMenu (interfaz de consola)

database/
 
 └─ database.sql           → Script único con creación de tablas + inserts

### Base de datos

Se incluye un archivo llamado database.sql, el cual contiene:

Creación de la base de datos

Creación de tablas

Relaciones y claves foráneas

Datos iniciales (inserts de prueba)

## Para ejecutarlo:

Abrir MySQL Workbench, DBeaver, phpMyAdmin o consola MySQL

Ejecutar el archivo completo

Verificar que exista la base biblioteca_db

## Configuración JDBC

En el archivo DatabaseConnection.java deben configurarse los datos de la conexión:

private static final String URL = "jdbc:mysql://localhost:3306/biblioteca_db";
private static final String USER = "root";
private static final String PASSWORD = "";

### Cómo ejecutar el proyecto

## Requisitos previos:

Java JDK instalado (versión 17 o superior recomendada)

MySQL instalado y corriendo (XAMPP, Workbench, WAMP, etc.)

Archivo mysql-connector-j.jar agregado al proyecto en las librerías

Haber ejecutado database.sql antes de iniciar la aplicación

## Pasos para ejecutar:

Iniciar el servidor MySQL

Ejecutar database.sql para crear estructura y registros base

Abrir el proyecto en un IDE (NetBeans, Eclipse, IntelliJ, VSCode)

Confirmar que el archivo mysql-connector-j.jar esté cargado en Libraries

Revisar datos de conexión en DatabaseConnection.java

Ejecutar la clase principal:
main/AppMenu.java


👤 Autores

Nombre: [Bujaldon Octavio , Briñoccoli Adrian
Carrera / Curso: Tecnicatura en Programación - Progamacion 2
Institución: [UTN]
Año: 2025
