//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IAfipDeduccionPersonalDAO;
import ar.com.draimo.jitws.dao.IAfipTipoBeneficioDAO;
import ar.com.draimo.jitws.dao.IAfipTipoBeneficioDeduccionDAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.AfipTipoBeneficioDeduccion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio AfipTipoBeneficioDeduccion
 *
 * @author blas
 */
@Service
public class AfipTipoBeneficioDeduccionService {

    //Define la referencia al dao
    @Autowired
    IAfipTipoBeneficioDeduccionDAO elementoDAO;

    //Define la referencia al dao de afipTipoBeneficio
    @Autowired
    IAfipTipoBeneficioDAO beneficioDAO;

    //Define la referencia al dao de afipDeduccionPersonal
    @Autowired
    IAfipDeduccionPersonalDAO deduccionDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipTipoBeneficioDeduccion elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<AfipTipoBeneficioDeduccion> listar() {
        return elementoDAO.findAllByOrderByAfipDeduccionPersonal_Id();
    }

    //Obtiene una lista por anio y idTipoBeneficio
    public List<AfipTipoBeneficioDeduccion> listarPorAnioYBeneficio(short anio, int idTipoBeneficio) {
        return elementoDAO.findByAnioAndAfipTipoBeneficioOrderByAfipDeduccionPersonal_Id(
                anio, beneficioDAO.findById(idTipoBeneficio).get());
    }

    //Obtiene una lista por anio y beneficio. Si recibe un mes devuelve el 
    //monto acumado que corresponderia a ese mes
    public List<AfipTipoBeneficioDeduccion> listarPorFiltros(short anio,
            int idBeneficio, int idMes) throws Exception {
        if (idMes > 12) {
            throw new Exception(MensajeRespuesta.NO_EXISTENTE + " MES");
        } else {
            return elementoDAO.listarPorFiltros(anio, idBeneficio, idMes);
        }
    }

    //Obtiene una lista por idTipoBeneficio
    public List<AfipTipoBeneficioDeduccion> listarPorBeneficio(int idTipoBeneficio) {
        return elementoDAO.findByAfipTipoBeneficioOrderByAfipDeduccionPersonal_Id(
                beneficioDAO.findById(idTipoBeneficio).get());
    }

    //Obtiene una lista por idDedudccionPersonal
    public List<AfipTipoBeneficioDeduccion> listarPorDeduccionPersonal(int idDedudccion) {
        return elementoDAO.findByAfipDeduccionPersonalOrderByAfipDeduccionPersonal_Id(
                deduccionDAO.findById(idDedudccion).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipTipoBeneficioDeduccion agregar(AfipTipoBeneficioDeduccion elemento) throws Exception {
        elemento = controlarLongitud(elemento,1);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipTipoBeneficioDeduccion elemento) throws Exception {
        elemento = controlarLongitud(elemento, 2);
        elementoDAO.save(elemento);
    }

    //Controla la longitud de los shorts
    private AfipTipoBeneficioDeduccion controlarLongitud(AfipTipoBeneficioDeduccion elemento, int opcion) {
        String anio = String.valueOf(elemento.getAnio());
        //Obtiene longitud de anio, si supera 4 retorna error
        if (anio.length() > 4 || anio.length() < 4) {
            throw new DataIntegrityViolationException(MensajeRespuesta.SHORT_INCORRECTO + " AÑO");
        }
        if (elemento.isImporteAnualMensual()) {
            if (elemento.getMes() == null) {
                throw new DataIntegrityViolationException(MensajeRespuesta.ELEMENTO_NO_NULL + " MES");
            }
        } else {
            if (opcion == 1) {
                if (elementoDAO.findByAnioAndAfipTipoBeneficioAndAfipDeduccionPersonalOrderByAfipDeduccionPersonal_Id(
                        elemento.getAnio(), elemento.getAfipTipoBeneficio(),
                        elemento.getAfipDeduccionPersonal()).isEmpty()) {
                    elemento.setMes(null);
                } else {
                    throw new DataIntegrityViolationException(MensajeRespuesta.EXISTENTE_PARA_ANIO_FISCAL
                            + "DEDUCCIÓN PERSONAL Y TIPO BENEFICIO");
                }
            }
            elemento.setMes(null);
        }
        return elemento;
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
