//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.ITalonarioReciboLoteDAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.TalonarioReciboLote;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Define TalonarioReciboLote
 *
 * @author blas
 */
@Service
public class TalonarioReciboLoteService {

    //Define la referencia al DAO
    @Autowired
    ITalonarioReciboLoteDAO elementoDAO;

    //Define la referencia al DAO de empresa
    @Autowired
    IEmpresaDAO empresaDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        TalonarioReciboLote elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<TalonarioReciboLote> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene la lista por empresa y lote entregado false
    public List<TalonarioReciboLote> listarPorEmpresaYLoteNoEntregado(int idEmpresa) {
        return elementoDAO.findByEmpresaAndLoteEntregadoFalse(empresaDAO.findById(idEmpresa).get());
    }

    //Obtiene la lista por empresa 
    public List<TalonarioReciboLote> listarPorEmpresa(int idEmpresa) {
        return elementoDAO.findByEmpresaOrderByPuntoVentaAsc(empresaDAO.findById(idEmpresa).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public TalonarioReciboLote agregar(TalonarioReciboLote elemento) throws Exception {
        controlarLongitud(elemento);
        Date fecha = new Date(new java.util.Date().getTime());
        elemento.setFechaAlta(fecha);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(TalonarioReciboLote elemento) throws Exception {
        controlarLongitud(elemento);
        elementoDAO.save(elemento);
    }
    
    //Controla la longitud de los atributos short
    private void controlarLongitud(TalonarioReciboLote elemento) throws Exception {
        if (elemento.getDesde() > elemento.getHasta()) {
            throw new Exception("HASTA " + MensajeRespuesta.ELEMENTO_MENOR + " DESDE");
        }
        if (String.valueOf(elemento.getDesde()).length() > 8) {
            throw new Exception(MensajeRespuesta.LONGITUD + " DESDE");
        }
        if (String.valueOf(elemento.getHasta()).length() > 8) {
            throw new Exception(MensajeRespuesta.LONGITUD + " HASTA");
        }
        if (String.valueOf(elemento.getPuntoVenta()).length() > 8) {
            throw new Exception(MensajeRespuesta.LONGITUD + " PUNTO DE VENTA");
        }
        List<TalonarioReciboLote> desdeLista = elementoDAO.listarPorDesdeHasta(
                elemento.getDesde(), elemento.getPuntoVenta(), elemento.getLetra());
        List<TalonarioReciboLote> hastaLista = elementoDAO.listarPorDesdeHasta(
                elemento.getHasta(), elemento.getPuntoVenta(), elemento.getLetra());
        if (!desdeLista.isEmpty() || !hastaLista.isEmpty()) {
            throw new Exception(MensajeRespuesta.DESDE_HASTA_YA_ASIGNADO);
        }
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}