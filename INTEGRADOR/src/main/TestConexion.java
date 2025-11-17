/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import config.DatabaseConnection;
import java.sql.Connection;

public class TestConexion {

    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("✔ Conexión exitosa a la base de datos!");
        } catch (Exception e) {
            System.out.println("❌ Error al conectar con la base:");
            e.printStackTrace();
        }
    }
}


