//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICobradorDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.ITalonarioReciboDAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.TalonarioRecibo;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de TalonarioRecibo
 *
 * @author blas
 */
@Service
public class TalonarioReciboService {

    //Define la referencia al DAO
    @Autowired
    ITalonarioReciboDAO elementoDAO;

    //Define la referencia al DAO de Cobrador 
    @Autowired
    ICobradorDAO cobradorDAO;

    //Define la referencia al DAO de Empresa
    @Autowired
    IEmpresaDAO empresaDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        TalonarioRecibo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<TalonarioRecibo> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene la lista completa
    public List<TalonarioRecibo> listarPorCobradorYEmpresa(int idCobrador, int idEmpresa) {
        return elementoDAO.findByCobradorAndTalonarioReciboLote_empresa(cobradorDAO.findById(idCobrador).get(), empresaDAO.findById(idEmpresa).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public TalonarioRecibo agregar(TalonarioRecibo elemento) throws Exception {
        if (elemento.getDesde() >= elemento.getHasta()) {
            throw new Exception("HASTA " + MensajeRespuesta.ELEMENTO_MENOR + " DESDE");
        }
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        List<TalonarioRecibo> desdeLista = elementoDAO.listarPorDesdeHasta(elemento.getDesde());
        List<TalonarioRecibo> hastaLista = elementoDAO.listarPorDesdeHasta(elemento.getHasta());
        if (!desdeLista.isEmpty()) {
            throw new Exception(MensajeRespuesta.DESDE_YA_ASIGNADO);
        }
        if (!hastaLista.isEmpty()) {
            throw new Exception(MensajeRespuesta.HASTA_YA_ASIGNADO);
        }
        if (elemento.getHasta() > elemento.getTalonarioReciboLote().getHasta()) {
            throw new Exception(MensajeRespuesta.DESDE_HASTA_INVALIDOS);
        }
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(TalonarioRecibo elemento) throws Exception {
        if (elemento.getDesde() >= elemento.getHasta()) {
            throw new Exception("HASTA " + MensajeRespuesta.ELEMENTO_MENOR + " DESDE");
        }
        TalonarioRecibo talonarioRecibo = elementoDAO.findById(elemento.getId()).get();
        if(elemento.getDesde() < talonarioRecibo.getDesde()) {
            List<TalonarioRecibo> desdeList = elementoDAO.listarPorDesdeHasta(elemento.getDesde());
            if (!desdeList.isEmpty()) {
                throw new Exception(MensajeRespuesta.DESDE_YA_ASIGNADO);
            }
        }
        if(elemento.getHasta() > talonarioRecibo.getHasta()) {
            List<TalonarioRecibo> hastaList = elementoDAO.listarPorDesdeHasta(elemento.getHasta());
            if (!hastaList.isEmpty()) {
                throw new Exception(MensajeRespuesta.HASTA_YA_ASIGNADO);
            }
        }
        if(elemento.getDesde() < talonarioRecibo.getTalonarioReciboLote().getDesde()) {
            throw new Exception(MensajeRespuesta.DESDE_EXCEDIDO);
        }
        if(elemento.getHasta() > talonarioRecibo.getTalonarioReciboLote().getHasta()) {
            throw new Exception(MensajeRespuesta.HASTA_EXCEDIDO);
        }
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
