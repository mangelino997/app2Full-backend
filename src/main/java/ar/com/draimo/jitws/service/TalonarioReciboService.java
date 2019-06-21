package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ITalonarioReciboDAO;
import ar.com.draimo.jitws.model.TalonarioRecibo;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author blas
 */
@Service
public class TalonarioReciboService {

    @Autowired
    ITalonarioReciboDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        TalonarioRecibo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<TalonarioRecibo> listar() {
        return elementoDAO.findAll();
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public TalonarioRecibo agregar(TalonarioRecibo elemento) throws Exception {
        if (elemento.getDesde() >= elemento.getHasta()) {
            throw new Exception("'Hasta' no puede ser mayor a 'Desde'");
        }
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        List<TalonarioRecibo> desdeLista = elementoDAO.listarPorDesdeHasta(elemento.getDesde());
        List<TalonarioRecibo> hastaLista = elementoDAO.listarPorDesdeHasta(elemento.getHasta());
        if (!desdeLista.isEmpty()) {
            throw new Exception("'Desde' ya pertenece a otro talonario");
        }
        if (!hastaLista.isEmpty()) {
            throw new Exception("'Hasta' ya pertenece a otro talonario");
        }
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(TalonarioRecibo elemento) throws Exception {
        if (elemento.getDesde() >= elemento.getHasta()) {
            throw new Exception("'Hasta' no puede ser mayor a 'Desde'");
        }
        List<TalonarioRecibo> desdeList = elementoDAO.listarPorDesdeHasta(
                elemento.getDesde());
        List<TalonarioRecibo> hastaList = elementoDAO.listarPorDesdeHasta(
                elemento.getHasta());
        if (!desdeList.isEmpty()) {
            throw new Exception("'Desde' ya pertenece a otro talonario");
        }
        if (!hastaList.isEmpty()) {
            throw new Exception("'Hasta' ya pertenece a otro talonario");
        }
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(TalonarioRecibo elemento) {
        elementoDAO.delete(elemento);
    }

}