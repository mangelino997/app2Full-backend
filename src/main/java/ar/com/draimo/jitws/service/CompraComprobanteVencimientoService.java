package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICompraComprobanteVencimientoDAO;
import ar.com.draimo.jitws.model.CompraComprobanteVencimiento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author blas
 */

@Service
public class CompraComprobanteVencimientoService {

    @Autowired
    ICompraComprobanteVencimientoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        CompraComprobanteVencimiento elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<CompraComprobanteVencimiento> listar() {
        return elementoDAO.findAll();
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public CompraComprobanteVencimiento agregar(CompraComprobanteVencimiento elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(CompraComprobanteVencimiento elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(CompraComprobanteVencimiento elemento) {
        elementoDAO.delete(elemento);
    }

}
