/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.wecode.jitws.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Interfaz generica.
 * Define los metodos genericos contra la base de datos.
 * @author blas
 */

public interface IGenericoDAO<E, ID extends Serializable> {
    
    //Obtiene la lista de columnas
    public List<String> listarColumnas(String tabla);
    
    //Obtiene una lista de objetos
    public List<E> listar();
    
    //Obtiene un objeto mediante un id 
    public E obtener(ID id);
    
    //Agrega un objeto
    public E agregar(E entidad);
    
    //Actualiza un objeto
    public void actualizar(E entidad);
    
    //Elimina un objeto
    public void eliminar(E entidad);
    
}
