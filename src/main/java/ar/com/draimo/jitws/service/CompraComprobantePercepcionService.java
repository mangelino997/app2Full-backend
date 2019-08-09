package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICompraComprobantePercepcionDAO;
import ar.com.draimo.jitws.model.CompraComprobantePercepcion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author blas
 */

@Service
public class CompraComprobantePercepcionService {

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
        //Obtiene longitud de anio, si supera 4 retorna error
        String anio = String.valueOf(elemento.getAnio());
        if (anio.length()>4) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en AÑO");
        }
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(CompraComprobantePercepcion elemento) throws Exception {
        //Obtiene longitud de anio, si supera 4 retorna error
        String anio = String.valueOf(elemento.getAnio());
        if (anio.length()>4) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en AÑO");
        }
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
