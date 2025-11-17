/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entities.FichaBibliografica;
import entities.Libro;
import service.LibroService;

public class testTransaccion {

    public static void main(String[] args) {

        LibroService service = new LibroService();

        try {
            System.out.println("===== TEST TRANSACCIÓN =====");

            // 1️⃣ Creamos la ficha nueva
            FichaBibliografica ficha = new FichaBibliografica();
            ficha.setIsbn("ROLL-TEST-001");
            ficha.setClasificacionDewey("999");
            ficha.setEstanteria("Z9");
            ficha.setIdioma("Test");

            // 2️⃣ Creamos el libro nuevo
            Libro libro = new Libro();
            libro.setTitulo("Libro de Prueba con Fallo");
            libro.setAutor("Autor Prueba");
            libro.setEditorial("Editorial Prueba");
            libro.setAnioEdicion(2025);

            // 3️⃣ Llamamos a método transaccional con error simulado activado
            boolean simularError = true; // Cambiar a false para ver éxito

            service.crearLibroConFicha(libro, ficha, simularError);

        } catch (Exception e) {
            System.out.println("Resultado final del test: " + e.getMessage());
        }
    }
}
