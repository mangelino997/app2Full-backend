//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEjercicioDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Ejercicio;
import ar.com.draimo.jitws.model.Empresa;
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
        return nombre.equals("***") ? elementoDAO.findByEmpresa(empresa) :
            elementoDAO.findByEmpresaAndNombreContaining(empresa, nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Ejercicio agregar(Ejercicio elemento) throws Exception {
        elemento = formatearStrings(elemento);
        controlarLongitud(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Ejercicio elemento) throws Exception {
        elemento = formatearStrings(elemento);
        controlarLongitud(elemento);
        elementoDAO.save(elemento);
    }
    
    //Controla error de longitud
    private void controlarLongitud(Ejercicio elemento) throws Exception {
        String anio = String.valueOf(elemento.getAnioInicio());
        if (anio.length()>4) {
            throw new Exception(MensajeRespuesta.SHORT_INCORRECTO + " AÑO INICIO");
        }
        //Obtiene longitud de meses, si supera 12 retorna error
        if (elemento.getCantidadMeses()>12) {
            throw new Exception(MensajeRespuesta.SHORT_INCORRECTO + " CANTIDAD MESES");
        }
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