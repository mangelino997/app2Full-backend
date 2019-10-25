//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICompraComprobantePercepcionDAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.CompraComprobantePercepcion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de compraComprobantePercepcion
 * @author blas
 */

@Service
public class CompraComprobantePercepcionService {

    //Refecencia al DAO
    @Autowired
    ICompraComprobantePercepcionDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        CompraComprobantePercepcion elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<CompraComprobantePercepcion> listar() {
        return elementoDAO.findAll();
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public CompraComprobantePercepcion agregar(CompraComprobantePercepcion elemento) throws Exception {
        controlarLongitud(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(CompraComprobantePercepcion elemento) throws Exception {
        controlarLongitud(elemento);
        elementoDAO.save(elemento);
    }
    
    //Controla longitudes de atributos short
    private void controlarLongitud(CompraComprobantePercepcion elemento) {
        //Obtiene longitud de anio, si supera 4 retorna error
        String anio = String.valueOf(elemento.getAnio());
        if (anio.length()>4 || anio.length()<4) {
            throw new DataIntegrityViolationException(MensajeRespuesta.SHORT_INCORRECTO + " AÑO");
        }
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}