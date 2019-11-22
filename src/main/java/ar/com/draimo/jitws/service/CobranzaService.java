//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICobranzaDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.model.Cobranza;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de Cobranza
 *
 * @author blas
 */
@Service
public class CobranzaService {

    //Define el dao
    @Autowired
    ICobranzaDAO elementoDAO;

    //Define el dao de empresa
    @Autowired
    IEmpresaDAO empresaDAO;

    //Define el dao de tipoComprobante
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Cobranza elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<Cobranza> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por empresa
    public List<Cobranza> listarPorEmpresa(int idEmpresa) {
        return elementoDAO.findByEmpresa(empresaDAO.findById(idEmpresa).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Cobranza agregar(Cobranza elemento) {
        elemento.setFechaRegistracion(new Timestamp(new java.util.Date().getTime()));
        elemento.setTipoComprobante(tipoComprobanteDAO.findById(4).get());
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Cobranza elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
