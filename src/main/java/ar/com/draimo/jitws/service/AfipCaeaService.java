package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.model.AfipCaea;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IAfipCaeaDAO;
import java.util.ArrayList;

/**
 * Servicio AfipCaea
 * @author blas
 */

@Service
public class AfipCaeaService {
    
    //Define la referencia al dao
    @Autowired
    IAfipCaeaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipCaea elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<AfipCaea> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene un listado de anios
    public List<Short> listarAnios() {
        List<Short> anios = new ArrayList<>();
        short anio = Funcion.anioInicio;
        for (short i = anio; i < anio+15; i++) {
            anios.add((short)i);
        }
        return anios;
    }
    
    //Obtiene un registro por empresa, anio, mes y quincena
    public AfipCaea obtenerPorEmpresaYPeriodoOrden(int idEmpresa, short anio, short mes, short quincena) {
        return elementoDAO.obtenerPorEmpresaYPeriodoOrden(idEmpresa, anio, mes, quincena);
    }
    
    //Obtiene un listado de registros por empresa y anio
    public List<AfipCaea> listarPorEmpresaYAnio(int idEmpresa, short anio) {
        return elementoDAO.listarPorEmpresaYAnio(idEmpresa, anio);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipCaea agregar(AfipCaea elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipCaea elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private AfipCaea formatearStrings(AfipCaea elemento) {
        elemento.setNumeroCAEA(elemento.getNumeroCAEA().trim());
        return elemento;
    }
    
}
