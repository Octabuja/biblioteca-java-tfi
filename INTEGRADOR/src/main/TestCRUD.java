/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;


import dao.FichaBibliograficaDAO;
import dao.LibroDAO;
import entities.FichaBibliografica;
import entities.Libro;

import java.util.List;

public class TestCRUD {

    public static void main(String[] args) {

        FichaBibliograficaDAO fichaDAO = new FichaBibliograficaDAO();
        LibroDAO libroDAO = new LibroDAO();

        try {
            System.out.println("===== TEST CREAR FICHA =====");
            FichaBibliografica ficha1 = new FichaBibliografica();
            ficha1.setIsbn("978-1111111111");
            ficha1.setClasificacionDewey("123");
            ficha1.setEstanteria("A10");
            ficha1.setIdioma("Español");
            fichaDAO.crear(ficha1);
            System.out.println("Ficha creada con ID: " + ficha1.getId());

            System.out.println("\n===== TEST CREAR LIBRO =====");
            Libro libro1 = new Libro();
            libro1.setTitulo("Nuevo Libro");
            libro1.setAutor("Autor Desconocido");
            libro1.setEditorial("Editorial X");
            libro1.setAnioEdicion(2024);
            libro1.setFichaBibliografica(ficha1);  // Relación 1→1

            libroDAO.crear(libro1);
            System.out.println("Libro creado con ID: " + libro1.getId());

            System.out.println("\n===== TEST BUSCAR LIBRO POR ID =====");
            Libro buscado = libroDAO.buscarPorId(libro1.getId());
            System.out.println(buscado);

            System.out.println("\n===== TEST LISTAR LIBROS =====");
            List<Libro> libros = libroDAO.listarTodos();
            libros.forEach(System.out::println);

            System.out.println("\n===== TEST ACTUALIZAR LIBRO =====");
            buscado.setTitulo("Título actualizado");
            libroDAO.actualizar(buscado);
            System.out.println("Libro actualizado: " + libroDAO.buscarPorId(buscado.getId()));

            System.out.println("\n===== TEST ELIMINAR (BAJA LÓGICA) =====");
            libroDAO.eliminar(buscado.getId());
            System.out.println("Libro eliminado, comprobando...");
            System.out.println("Resultado al buscar: " + libroDAO.buscarPorId(buscado.getId()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
