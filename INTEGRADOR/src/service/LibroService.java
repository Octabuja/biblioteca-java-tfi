/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.LibroDAO;
import dao.FichaBibliograficaDAO;
import entities.FichaBibliografica;
import entities.Libro;
import config.DatabaseConnection;
import java.sql.Connection;

import java.util.List;

public class LibroService {

    private LibroDAO libroDAO = new LibroDAO();
    private FichaBibliograficaDAO fichaDAO = new FichaBibliograficaDAO();

    public void crearLibro(Libro libro) throws Exception {

        if (libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()) {
            throw new Exception("El título no puede estar vacío");
        }

        if (libro.getAutor() == null || libro.getAutor().trim().isEmpty()) {
            throw new Exception("El autor no puede estar vacío");
        }

        if (libro.getFichaBibliografica() == null) {
            throw new Exception("El libro debe tener una ficha bibliográfica asociada");
        }

        FichaBibliografica fichaBD = fichaDAO.buscarPorId(libro.getFichaBibliografica().getId());
        if (fichaBD == null) {
            throw new Exception("Ficha bibliográfica inexistente");
        }

        libroDAO.crear(libro);
    }

    public Libro buscarLibro(int id) throws Exception {
        return libroDAO.buscarPorId(id);
    }

    public List<Libro> listarLibros() throws Exception {
        return libroDAO.listarTodos();
    }

    public void actualizarLibro(Libro libro) throws Exception {
        if (libro.getId() <= 0) throw new Exception("ID de libro inválido");

        libroDAO.actualizar(libro);
    }

    public void eliminarLibro(int id) throws Exception {
        libroDAO.eliminar(id);
    }
    
    public void crearLibroConFicha(Libro libro, FichaBibliografica ficha, boolean simularError) throws Exception {
    Connection conn = null;
    try {
        conn = DatabaseConnection.getConnection();
        conn.setAutoCommit(false); // inicio de transacción

        // Crear ficha primero
        fichaDAO.crearConConexion(ficha, conn);

        // Asociar la ficha recién creada al libro
        libro.setFichaBibliografica(ficha);

        // Simulación de fallo para demostrar rollback
        if (simularError) {
            throw new Exception("💥 Error simulado en la creación del libro");
        }

        // Crear libro
        libroDAO.crearConConexion(libro, conn);

        conn.commit();
        System.out.println("✔ Transacción completada: Libro + Ficha guardados");

    } catch (Exception e) {
        if (conn != null) {
            conn.rollback();
            System.out.println("⛔ Rollback realizado: No se guardaron cambios");
        }
        throw e;
    } finally {
        if (conn != null) {
            conn.setAutoCommit(true);
            conn.close();
        }
    }
}
}

