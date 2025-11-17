/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;
import java.util.List;

public interface GenericDAO<T> {
    

void crear(T entidad) throws Exception;

T buscarPorId(int id) throws Exception;

List<T> listarTodos() throws Exception;

void actualizar(T entidad) throws Exception;

void eliminar(int id) throws Exception; // baja lógica


}
