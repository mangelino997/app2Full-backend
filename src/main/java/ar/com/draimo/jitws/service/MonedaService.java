package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IMonedaDAO;
import ar.com.draimo.jitws.model.Moneda;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Moneda
 * @author blas
 */

@Service
public class MonedaService {
    
    //Define la referencia al dao
    @Autowired
    IMonedaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Moneda elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<Moneda> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Moneda> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Obtiene la moneda principal por defecto
    public Moneda obtenerPorDefecto() {
        return elementoDAO.findByPorDefectoTrue();
    }
    
    //Establece la moneda como principal
    @Transactional(rollbackFor = Exception.class)
    public void establecerMonedaPrincipal(int idMoneda) {
        Moneda moneda = elementoDAO.findById(idMoneda).get();
        moneda.setPorDefecto(false);
        elementoDAO.save(moneda);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Moneda agregar(Moneda elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Moneda elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Moneda elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Moneda formatearStrings(Moneda elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }
    
}
