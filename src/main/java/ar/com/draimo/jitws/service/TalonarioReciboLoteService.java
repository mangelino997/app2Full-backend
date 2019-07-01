package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.ITalonarioReciboLoteDAO;
import ar.com.draimo.jitws.model.TalonarioReciboLote;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Define TalonarioReciboLote
 * @author blas
 */

@Service
public class TalonarioReciboLoteService {

    @Autowired
    ITalonarioReciboLoteDAO elementoDAO;

    @Autowired
    IEmpresaDAO empresaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        TalonarioReciboLote elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<TalonarioReciboLote> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene la lista por empresa y lote entregado false
    public List<TalonarioReciboLote> listarPorEmpresaYLoteEntregadoFalse(int idEmpresa) {
        return elementoDAO.findByEmpresaAndLoteEntregadoFalse(empresaDAO.findById(idEmpresa).get());
    }
    
    //Obtiene la lista por empresa 
    public List<TalonarioReciboLote> listarPorEmpresa(int idEmpresa) {
        return elementoDAO.findByEmpresaOrderByPuntoVentaAsc(empresaDAO.findById(idEmpresa).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public TalonarioReciboLote agregar(TalonarioReciboLote elemento) throws Exception {
        if(elemento.getDesde()>elemento.getHasta()) {
            throw new Exception("'Hasta' no puede ser menor a 'Desde'");
        }
        Date fecha = new Date(new java.util.Date().getTime());
        elemento.setFechaAlta(fecha);
        List<TalonarioReciboLote> desdeLista = elementoDAO.listarPorDesdeHasta(
                elemento.getDesde());
        List<TalonarioReciboLote> hastaLista = elementoDAO.listarPorDesdeHasta(
                elemento.getHasta());
        if(!desdeLista.isEmpty()) {
            throw new Exception("'Desde' ya pertenece a otro talonario");
        }
        if(!hastaLista.isEmpty()) {
            throw new Exception("'Hasta' ya pertenece a otro talonario");
        }
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(TalonarioReciboLote elemento) throws Exception {
        if(elemento.getDesde()>elemento.getHasta()) {
            throw new Exception("'Hasta' no puede ser menor a 'Desde'");
        }
        List<TalonarioReciboLote> desdeLista = elementoDAO.listarPorDesdeHasta(
                elemento.getDesde());
        List<TalonarioReciboLote> hastaLista = elementoDAO.listarPorDesdeHasta(
                elemento.getHasta());
        if(!desdeLista.isEmpty()) {
            throw new Exception("'Desde' ya pertenece a otro talonario");
        }
        if(!hastaLista.isEmpty()) {
            throw new Exception("'Hasta' ya pertenece a otro talonario");
        }
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(TalonarioReciboLote elemento) {
        elementoDAO.delete(elemento);
    }

}
