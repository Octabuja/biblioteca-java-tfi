/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

public class Libro extends Base {

    private String titulo;
    private String autor;
    private String editorial;
    private int anioEdicion;
    private FichaBibliografica fichaBibliografica;

    public Libro() {
        super(); // eliminado = false por defecto
    }

    public Libro(int id, Boolean eliminado, String titulo, String autor, String editorial,
                 int anioEdicion, FichaBibliografica fichaBibliografica) {
        super(id, eliminado);
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anioEdicion = anioEdicion;
        this.fichaBibliografica = fichaBibliografica;
    }

    // getters y setters...

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnioEdicion() {
        return anioEdicion;
    }

    public void setAnioEdicion(Integer anioEdicion) {
        this.anioEdicion = anioEdicion;
    }

    public FichaBibliografica getFichaBibliografica() {
        return fichaBibliografica;
    }

    public void setFichaBibliografica(FichaBibliografica fichaBibliografica) {
        this.fichaBibliografica = fichaBibliografica;
    }
    
    @Override
    public String toString() {
        return "Libro{" +
                "id=" + getId() +
                ", eliminado=" + getEliminado() +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", editorial='" + editorial + '\'' +
                ", anioEdicion=" + anioEdicion +
                ", fichaBibliografica=" + (fichaBibliografica != null ? fichaBibliografica.getIsbn() : "sin ficha") +
                '}';
    }
    
}

