//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.AfipCaea;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IAfipCaeaDAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import org.springframework.dao.DataIntegrityViolationException;

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
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<AfipCaea> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene un registro por empresa, anio, mes y quincena
    public AfipCaea obtenerPorEmpresaYPeriodoOrden(int idEmpresa, short anio, int idMes, int idQuincena) {
        return elementoDAO.obtenerPorEmpresaYPeriodoOrden(idEmpresa, anio, idMes, idQuincena);
    }
    
    //Obtiene un listado de registros por empresa y anio
    public List<AfipCaea> listarPorEmpresaYAnio(int idEmpresa, short anio) {
        return elementoDAO.listarPorEmpresaYAnio(idEmpresa, anio);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipCaea agregar(AfipCaea elemento) throws Exception {
        elemento = formatearStrings(elemento);
        String anio = String.valueOf(elemento.getAnio());
        //Obtiene la longitud del anio, si supera 4 retorna mensaje de error
        if (anio.length()>4) {
            throw new DataIntegrityViolationException(MensajeRespuesta.SHORT_INCORRECTO + " AÑO");
        }
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipCaea elemento) throws Exception {
        elemento = formatearStrings(elemento);
        String anio = String.valueOf(elemento.getAnio());
        //Obtiene la longitud del anio, si supera 4 retorna mensaje de error
        if (anio.length()>4) {
            throw new DataIntegrityViolationException(MensajeRespuesta.SHORT_INCORRECTO + " AÑO");
        }
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
