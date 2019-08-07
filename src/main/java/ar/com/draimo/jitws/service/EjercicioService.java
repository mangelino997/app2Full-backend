package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.dao.IEjercicioDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.model.Ejercicio;
import ar.com.draimo.jitws.model.Empresa;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Ejercicio
 * @author blas
 */

@Service
public class EjercicioService {

    //Define la referencia al dao
    @Autowired
    IEjercicioDAO elementoDAO;

    //Define la referencia al dao de empresa
    @Autowired
    IEmpresaDAO empresaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Ejercicio elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<Ejercicio> listar(int idEmpresa) {
        return elementoDAO.findByEmpresa(empresaDAO.findById(idEmpresa).get());
    }
    
    //Obtiene una lista por nombre
    public List<Ejercicio> listarPorNombre(String nombre, int idEmpresa) {
        Empresa empresa = empresaDAO.findById(idEmpresa).get();
        if(nombre.equals("***")) {
            return elementoDAO.findByEmpresa(empresa);
        } else {
            return elementoDAO.findByEmpresaAndNombreContaining(empresa, nombre);
        }
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

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Ejercicio agregar(Ejercicio elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Ejercicio elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private Ejercicio formatearStrings(Ejercicio elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}
