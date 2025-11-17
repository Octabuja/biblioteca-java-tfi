/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DatabaseConnection;
import entities.FichaBibliografica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FichaBibliograficaDAO implements GenericDAO<FichaBibliografica> {

    @Override
    public void crear(FichaBibliografica ficha) throws Exception {
        String sql = "INSERT INTO ficha_bibliografica (isbn, clasificacion_dewey, estanteria, idioma, eliminado) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, ficha.getIsbn());
            stmt.setString(2, ficha.getClasificacionDewey());
            stmt.setString(3, ficha.getEstanteria());
            stmt.setString(4, ficha.getIdioma());
            stmt.setBoolean(5, ficha.getEliminado());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                ficha.setId(rs.getInt(1));
            }
        }
    }

    @Override
    public FichaBibliografica buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM ficha_bibliografica WHERE id = ? AND eliminado = FALSE";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) return null;

            return new FichaBibliografica(
                    rs.getInt("id"),
                    rs.getBoolean("eliminado"),
                    rs.getString("isbn"),
                    rs.getString("clasificacion_dewey"),
                    rs.getString("estanteria"),
                    rs.getString("idioma")
            );
        }
    }

    @Override
    public List<FichaBibliografica> listarTodos() throws Exception {
        String sql = "SELECT * FROM ficha_bibliografica WHERE eliminado = FALSE";
        List<FichaBibliografica> lista = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FichaBibliografica ficha = new FichaBibliografica(
                        rs.getInt("id"),
                        rs.getBoolean("eliminado"),
                        rs.getString("isbn"),
                        rs.getString("clasificacion_dewey"),
                        rs.getString("estanteria"),
                        rs.getString("idioma")
                );
                lista.add(ficha);
            }
        }

        return lista;
    }

    @Override
    public void actualizar(FichaBibliografica ficha) throws Exception {
        String sql = "UPDATE ficha_bibliografica SET isbn=?, clasificacion_dewey=?, estanteria=?, idioma=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ficha.getIsbn());
            stmt.setString(2, ficha.getClasificacionDewey());
            stmt.setString(3, ficha.getEstanteria());
            stmt.setString(4, ficha.getIdioma());
            stmt.setInt(5, ficha.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        String sql = "UPDATE ficha_bibliografica SET eliminado = TRUE WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    // Método especial para transacciones:
public void crearConConexion(FichaBibliografica ficha, Connection conn) throws Exception {
    String sql = "INSERT INTO ficha_bibliografica (isbn, clasificacion_dewey, estanteria, idioma, eliminado) VALUES (?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, ficha.getIsbn());
        stmt.setString(2, ficha.getClasificacionDewey());
        stmt.setString(3, ficha.getEstanteria());
        stmt.setString(4, ficha.getIdioma());
        stmt.setBoolean(5, ficha.getEliminado());

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            ficha.setId(rs.getInt(1)); // asigna ID generado
        }
    }
}

}

