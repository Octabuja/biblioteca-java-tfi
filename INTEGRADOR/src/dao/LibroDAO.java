/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DatabaseConnection;
import entities.Libro;
import entities.FichaBibliografica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO implements GenericDAO<Libro> {

    @Override
    public void crear(Libro libro) throws Exception {
        String sql = "INSERT INTO libro (titulo, autor, editorial, anio_edicion, eliminado, ficha_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getEditorial());
            stmt.setObject(4, libro.getAnioEdicion(), Types.INTEGER);
            stmt.setBoolean(5, libro.getEliminado());
            stmt.setObject(6, libro.getFichaBibliografica() != null ? libro.getFichaBibliografica().getId() : null, Types.INTEGER);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                libro.setId(rs.getInt(1));
            }
        }
    }

    @Override
    public Libro buscarPorId(int id) throws Exception {
        String sql = """
                SELECT l.*, f.id AS f_id, f.isbn, f.clasificacion_dewey, f.estanteria, f.idioma, f.eliminado AS f_eliminado
                FROM libro l
                LEFT JOIN ficha_bibliografica f ON l.ficha_id = f.id
                WHERE l.id = ? AND l.eliminado = FALSE
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) return null;

            // Crear objeto Ficha
            FichaBibliografica ficha = null;
            if (rs.getInt("f_id") != 0) {
                ficha = new FichaBibliografica(
                        rs.getInt("f_id"),
                        rs.getBoolean("f_eliminado"),
                        rs.getString("isbn"),
                        rs.getString("clasificacion_dewey"),
                        rs.getString("estanteria"),
                        rs.getString("idioma")
                );
            }

            // Crear objeto Libro
            return new Libro(
                    rs.getInt("id"),
                    rs.getBoolean("eliminado"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("editorial"),
                    rs.getInt("anio_edicion"),
                    ficha
            );
        }
    }

    @Override
    public List<Libro> listarTodos() throws Exception {
        String sql = """
                SELECT l.*, f.id AS f_id, f.isbn, f.clasificacion_dewey, f.estanteria, f.idioma, f.eliminado AS f_eliminado
                FROM libro l
                LEFT JOIN ficha_bibliografica f ON l.ficha_id = f.id
                WHERE l.eliminado = FALSE
                """;

        List<Libro> lista = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                FichaBibliografica ficha = null;
                if (rs.getInt("f_id") != 0) {
                    ficha = new FichaBibliografica(
                            rs.getInt("f_id"),
                            rs.getBoolean("f_eliminado"),
                            rs.getString("isbn"),
                            rs.getString("clasificacion_dewey"),
                            rs.getString("estanteria"),
                            rs.getString("idioma")
                    );
                }

                Libro libro = new Libro(
                        rs.getInt("id"),
                        rs.getBoolean("eliminado"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("editorial"),
                        rs.getInt("anio_edicion"),
                        ficha
                );

                lista.add(libro);
            }
        }

        return lista;
    }

    @Override
    public void actualizar(Libro libro) throws Exception {
        String sql = "UPDATE libro SET titulo=?, autor=?, editorial=?, anio_edicion=?, ficha_id=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getEditorial());
            stmt.setObject(4, libro.getAnioEdicion(), Types.INTEGER);
            stmt.setObject(5, libro.getFichaBibliografica() != null ? libro.getFichaBibliografica().getId() : null, Types.INTEGER);
            stmt.setInt(6, libro.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        String sql = "UPDATE libro SET eliminado = TRUE WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    // Método especial para transacciones: recibe conexión externa
public void crearConConexion(Libro libro, Connection conn) throws Exception {
    String sql = "INSERT INTO libro (titulo, autor, editorial, anio_edicion, eliminado, ficha_id) VALUES (?, ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, libro.getTitulo());
        stmt.setString(2, libro.getAutor());
        stmt.setString(3, libro.getEditorial());
        stmt.setObject(4, libro.getAnioEdicion()); 
        stmt.setBoolean(5, libro.getEliminado());
        stmt.setInt(6, libro.getFichaBibliografica().getId());

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            libro.setId(rs.getInt(1)); // asigna ID generado
        }
    }
}

}


