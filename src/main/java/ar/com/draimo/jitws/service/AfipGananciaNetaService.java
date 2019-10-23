//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IAfipAlicuotaGananciaDAO;
import ar.com.draimo.jitws.dao.IAfipGananciaNetaDAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.AfipGananciaNeta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio AfipGananciaNeta
 *
 * @author blas
 */
@Service
public class AfipGananciaNetaService {

    //Define la referencia al dao
    @Autowired
    IAfipGananciaNetaDAO elementoDAO;

    //Define la referencia al dao de afipAlicuotaGanancia
    @Autowired
    IAfipAlicuotaGananciaDAO gananciaDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipGananciaNeta elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<AfipGananciaNeta> listar() {
        List<AfipGananciaNeta> ganancias = elementoDAO.findAllByOrderByImporte();
        return ganancias;
    }

    //Obtiene una lista por filtros
    public List<AfipGananciaNeta> listarPorFiltros(short anio, int idMes) throws Exception {
        if (idMes > 12) {
            throw new Exception(MensajeRespuesta.NO_EXISTENTE + "MES");
        } else {
            List<AfipGananciaNeta> ganancias = elementoDAO.listarPorFiltros(anio, idMes);
            return ganancias;
        }
    }

    //Obtiene una lista por idAlicuotaGanancia
    public List<AfipGananciaNeta> listarPorAfipAlicuotaGanancia(int idAlicuotaGanancia) {

        List<AfipGananciaNeta> ganancias = elementoDAO.findByAfipAlicuotaGananciaOrderByImporte(
                gananciaDAO.findById(idAlicuotaGanancia).get());
        return ganancias;
    }

    //Obtiene una lista por AnioFiscal
    public List<AfipGananciaNeta> listarPorAnioFiscal(short anioFiscal) {
        controlarLongitud(anioFiscal);
        List<AfipGananciaNeta> ganancias = elementoDAO.findByAnioOrderByImporte(anioFiscal);
        return ganancias;
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipGananciaNeta agregar(AfipGananciaNeta elemento) throws Exception {
        controlarLongitud(elemento.getAnio());
        if (elementoDAO.findByAnioAndImporte(elemento.getAnio(), elemento.getImporte()).isEmpty()) {
            return elementoDAO.saveAndFlush(elemento);
        } else {
            throw new DataIntegrityViolationException(MensajeRespuesta.EXISTENTE_PARA_ANIO_FISCAL 
                    + " GANANCIA NETA ACUMULADA");
        }
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipGananciaNeta elemento) throws Exception {
        controlarLongitud(elemento.getAnio());
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Controla la longitud incorrecta en atributos de tipo short
    private void controlarLongitud(short elemento) {
        String anio = String.valueOf(elemento);
        //Obtiene longitud de anio, si supera 4 retorna error
        if (anio.length() > 4 || anio.length() < 4) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " AÑO");
        }
    }

}
