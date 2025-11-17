/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package service;

import dao.FichaBibliograficaDAO;
import entities.FichaBibliografica;

import java.util.List;

public class FichaService {

    private FichaBibliograficaDAO fichaDAO = new FichaBibliograficaDAO();

    public void crearFicha(FichaBibliografica ficha) throws Exception {
        if (ficha.getIsbn() == null || ficha.getIsbn().trim().isEmpty()) {
            throw new Exception("El ISBN no puede estar vacío");
        }

        fichaDAO.crear(ficha);
    }

    public FichaBibliografica buscarFicha(int id) throws Exception {
        return fichaDAO.buscarPorId(id);
    }

    public List<FichaBibliografica> listarFichas() throws Exception {
        return fichaDAO.listarTodos();
    }

    public void actualizarFicha(FichaBibliografica ficha) throws Exception {
        if (ficha.getId() <= 0) throw new Exception("El ID es inválido");
        fichaDAO.actualizar(ficha);
    }

    public void eliminarFicha(int id) throws Exception {
        fichaDAO.eliminar(id);
    }
}

