//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICobradorDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.ITalonarioReciboDAO;
import ar.com.draimo.jitws.dao.ITalonarioReciboLoteDAO;
import ar.com.draimo.jitws.dto.InitTalonarioReciboDTO;
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

    //Define la referencia al talonarioReciboLoteDAO 
    @Autowired
    ITalonarioReciboLoteDAO talonarioReciboLoteDAO;

    //Define la referencia al DAO de Cobrador 
    @Autowired
    ICobradorDAO cobradorDAO;

    //Define la referencia al DAO de Empresa
    @Autowired
    IEmpresaDAO empresaDAO;
    
    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Obtiene listas necesarias para inicializar el componente (front)
    public InitTalonarioReciboDTO inicializar(int idEmpresa, int idRol, int idSubopcion) {
        InitTalonarioReciboDTO elemento = new InitTalonarioReciboDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idSubopcion));
        elemento.setTalonarioReciboLotes(talonarioReciboLoteDAO.findByEmpresaAndLoteEntregadoFalse(
                empresaDAO.findById(idEmpresa).get()));
        elemento.setCobradores(cobradorDAO.findByEstaActivoTrueOrderByNombreAsc());
        elemento.setUltimoId(obtenerSiguienteId());
        return elemento;
    }

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
        return elementoDAO.findByCobradorAndTalonarioReciboLote_empresa(
                cobradorDAO.findById(idCobrador).get(), empresaDAO.findById(idEmpresa).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public TalonarioRecibo agregar(TalonarioRecibo elemento) throws Exception {
        controlarLongitud(elemento);
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(TalonarioRecibo elemento) throws Exception {
        controlarLongitud(elemento);
        TalonarioRecibo tr = elementoDAO.verificarDesdeHasta(elemento.getTalonarioReciboLote().getId(),
                elemento.getDesde(), elemento.getHasta());
            if(tr.getDesde() != elemento.getDesde() && tr.getHasta() != elemento.getHasta()) {
                throw new Exception(MensajeRespuesta.DESDE_HASTA_YA_ASIGNADO);
        }
        elementoDAO.save(elemento);
    }
    
    //Controla la longitud de los atributos short
    private void controlarLongitud(TalonarioRecibo elemento) throws Exception {
        if (elemento.getDesde() >= elemento.getHasta()) {
            throw new Exception("HASTA " + MensajeRespuesta.ELEMENTO_MENOR + " DESDE");
        }
        List<TalonarioRecibo> desdeLista = elementoDAO.listarPorDesdeHasta(
                elemento.getTalonarioReciboLote().getId(), elemento.getDesde());
        List<TalonarioRecibo> hastaLista = elementoDAO.listarPorDesdeHasta(
                elemento.getTalonarioReciboLote().getId(), elemento.getHasta());
        if (!desdeLista.isEmpty()) {
            throw new Exception(MensajeRespuesta.DESDE_YA_ASIGNADO);
        }
        if (!hastaLista.isEmpty()) {
            throw new Exception(MensajeRespuesta.HASTA_YA_ASIGNADO);
        }
        if (elemento.getHasta() > elemento.getTalonarioReciboLote().getHasta()) {
            throw new Exception(MensajeRespuesta.DESDE_HASTA_INVALIDOS);
        }
        if(elemento.getDesde() < elemento.getTalonarioReciboLote().getDesde()) {
            throw new Exception(MensajeRespuesta.DESDE_EXCEDIDO);
        }
        if(elemento.getHasta() > elemento.getTalonarioReciboLote().getHasta()) {
            throw new Exception(MensajeRespuesta.HASTA_EXCEDIDO);
        }
        TalonarioRecibo tr = elementoDAO.verificarDesdeHasta(
                elemento.getTalonarioReciboLote().getId(), elemento.getDesde(), elemento.getHasta());
        if (tr != null) {
            throw new Exception(MensajeRespuesta.DESDE_HASTA_YA_ASIGNADO);
        }
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
