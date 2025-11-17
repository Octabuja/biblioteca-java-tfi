/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import service.FichaService;
import service.LibroService;
import entities.FichaBibliografica;
import entities.Libro;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    private static Scanner sc = new Scanner(System.in);

    private static LibroService libroService = new LibroService();
    private static FichaService fichaService = new FichaService();

   public static void main(String[] args) {

    int opcion;

    do {
        System.out.println("\n===== SISTEMA DE BIBLIOTECA =====");
        System.out.println("1. CRUD Ficha Bibliográfica");
        System.out.println("2. CRUD Libro");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
        opcion = Integer.parseInt(sc.nextLine());

        switch (opcion) {
            case 1: menuFicha(); break;
            case 2: menuLibro(); break;
            case 7: System.out.println("Saliendo..."); break;
            default: System.out.println("Opción inválida.");
        }

    } while (opcion != 7);
}
private static void menuFicha() {
    int op;
    do {
        System.out.println("\n--- CRUD FICHA BIBLIOGRÁFICA ---");
        System.out.println("1. Crear ficha");
        System.out.println("2. Buscar ficha");
        System.out.println("3. Listar fichas");
        System.out.println("4. Actualizar ficha");
        System.out.println("5. Eliminar ficha");
        System.out.println("0. Volver");
        System.out.print("Seleccione una opción: ");
        op = Integer.parseInt(sc.nextLine());

        try {
            switch (op) {
                case 1: crearFicha(); break;
                case 2: buscarFicha(); break;
                case 3: listarFichas(); break;
                case 4: actualizarFicha(); break;
                case 5: eliminarFicha(); break;
                case 0: break;
                default: System.out.println("Opción inválida.");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }

    } while (op != 0);
}

private static void menuLibro() {
    int op;
    do {
        System.out.println("\n--- CRUD LIBRO ---");
        System.out.println("1. Crear libro");
        System.out.println("2. Buscar libro");
        System.out.println("3. Listar libros");
        System.out.println("4. Actualizar libro");
        System.out.println("5. Eliminar libro");
        System.out.println("0. Volver");
        System.out.print("Seleccione una opción: ");
        op = Integer.parseInt(sc.nextLine());

        try {
            switch (op) {
                case 1: crearLibro(); break;
                case 2: buscarLibro(); break;
                case 3: listarLibros(); break;
                case 4: actualizarLibro(); break;
                case 5: eliminarLibro(); break;
                case 0: break;
                default: System.out.println("Opción inválida.");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }

    } while (op != 0);
}


    // ---- MÉTODOS DEL MENÚ ----

    private static void crearFicha() throws Exception {
        FichaBibliografica ficha = new FichaBibliografica();

        System.out.print("ISBN: ");
        ficha.setIsbn(sc.nextLine());

        System.out.print("Clasificación Dewey: ");
        ficha.setClasificacionDewey(sc.nextLine());

        System.out.print("Estantería: ");
        ficha.setEstanteria(sc.nextLine());

        System.out.print("Idioma: ");
        ficha.setIdioma(sc.nextLine());

        fichaService.crearFicha(ficha);
        System.out.println("Ficha creada con éxito (ID = " + ficha.getId() + ")");
    }
    
    private static void buscarFicha() throws Exception {
    System.out.print("Ingrese el ID de la ficha: ");
    int id = Integer.parseInt(sc.nextLine());

    var ficha = fichaService.buscarFicha(id);

    if (ficha == null || ficha.getEliminado()) {
        System.out.println("❌ Ficha no encontrada.");
    } else {
        System.out.println("📋 FICHA ENCONTRADA:");
        System.out.println(ficha);
    }
}
private static void listarFichas() throws Exception {
    var lista = fichaService.listarFichas();

    if (lista.isEmpty()) {
        System.out.println("📭 No hay fichas registradas.");
        return;
    }

    System.out.println("\n📚 LISTADO DE FICHAS:");
    for (var ficha : lista) {
        if (!ficha.getEliminado()) {
            System.out.println(ficha);
        }
    }
}
private static void actualizarFicha() throws Exception {
    System.out.print("Ingrese el ID de la ficha a actualizar: ");
    int id = Integer.parseInt(sc.nextLine());

    var ficha = fichaService.buscarFicha(id);
    if (ficha == null || ficha.getEliminado()) {
        System.out.println("❌ Ficha no encontrada.");
        return;
    }

    System.out.print("Nuevo ISBN (" + ficha.getIsbn() + "): ");
    ficha.setIsbn(sc.nextLine());

    System.out.print("Nueva Clasificación Dewey (" + ficha.getClasificacionDewey() + "): ");
    ficha.setClasificacionDewey(sc.nextLine());

    System.out.print("Nueva estantería (" + ficha.getEstanteria() + "): ");
    ficha.setEstanteria(sc.nextLine());

    System.out.print("Nuevo idioma (" + ficha.getIdioma() + "): ");
    ficha.setIdioma(sc.nextLine());

    fichaService.actualizarFicha(ficha);
    System.out.println("✔ Ficha actualizada con éxito.");
}
private static void eliminarFicha() throws Exception {
    System.out.print("Ingrese el ID de la ficha a eliminar: ");
    int id = Integer.parseInt(sc.nextLine());

    fichaService.eliminarFicha(id);
    System.out.println("✔ Ficha eliminada (baja lógica aplicada).");
}


    private static void crearLibro() throws Exception {
        Libro libro = new Libro();

        System.out.print("Título: ");
        libro.setTitulo(sc.nextLine());

        System.out.print("Autor: ");
        libro.setAutor(sc.nextLine());

        System.out.print("Editorial: ");
        libro.setEditorial(sc.nextLine());

        System.out.print("Año de edición: ");
        libro.setAnioEdicion(Integer.parseInt(sc.nextLine()));

        System.out.print("ID de ficha bibliográfica existente: ");
        int idFicha = Integer.parseInt(sc.nextLine());

        FichaBibliografica ficha = fichaService.buscarFicha(idFicha);
        if (ficha == null) throw new Exception("La ficha con ese ID no existe");

        libro.setFichaBibliografica(ficha);

        libroService.crearLibro(libro);
        System.out.println("Libro creado con éxito (ID = " + libro.getId() + ")");
    }

    private static void buscarLibro() throws Exception {
        System.out.print("Ingrese el ID del libro: ");
        int id = Integer.parseInt(sc.nextLine());

        Libro libro = libroService.buscarLibro(id);

        if (libro == null) {
            System.out.println("No se encontró un libro con ese ID.");
        } else {
            System.out.println(libro);
        }
    }

    private static void listarLibros() throws Exception {
        List<Libro> lista = libroService.listarLibros();

        if (lista.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            for (Libro libro : lista) {
                System.out.println(libro);
            }
        }
    }

    private static void actualizarLibro() throws Exception {
        System.out.print("Ingrese el ID del libro a actualizar: ");
        int id = Integer.parseInt(sc.nextLine());

        Libro libro = libroService.buscarLibro(id);
        if (libro == null) throw new Exception("No se encontró el libro.");

        System.out.print("Nuevo título: ");
        libro.setTitulo(sc.nextLine());

        System.out.print("Nuevo autor: ");
        libro.setAutor(sc.nextLine());

        System.out.print("Nueva editorial: ");
        libro.setEditorial(sc.nextLine());

        System.out.print("Nuevo año de edición: ");
        libro.setAnioEdicion(Integer.parseInt(sc.nextLine()));

        libroService.actualizarLibro(libro);
        System.out.println("Libro actualizado con éxito.");
    }

    private static void eliminarLibro() throws Exception {
        System.out.print("Ingrese ID del libro a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());

        libroService.eliminarLibro(id);
        System.out.println("Libro eliminado (baja lógica) con éxito.");
    }
}

