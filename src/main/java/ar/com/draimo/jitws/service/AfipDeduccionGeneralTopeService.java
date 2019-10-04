//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IAfipDeduccionGeneralDAO;
import ar.com.draimo.jitws.dao.IAfipDeduccionGeneralTopeDAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.AfipDeduccionGeneralTope;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio AfipDeduccionGeneralTope
 *
 * @author blas
 */
@Service
public class AfipDeduccionGeneralTopeService {

    //Define la referencia al dao
    @Autowired
    IAfipDeduccionGeneralTopeDAO elementoDAO;

    //Define la referencia al dao de afipDeduccionGeneral
    @Autowired
    IAfipDeduccionGeneralDAO afipDeduccionGeneralDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipDeduccionGeneralTope elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<AfipDeduccionGeneralTope> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene la lista por anio
    public List<AfipDeduccionGeneralTope> listarPorAnio(short anio) {
        return elementoDAO.findByAnioOrderByAfipDeduccionGeneral_Id(anio);
    }

    //Obtiene una lista por Descripcion
    public List<AfipDeduccionGeneralTope> listarPorDescripcion(String descripcion) {
        return descripcion.equals("***") ? elementoDAO.findAll()
                : elementoDAO.findByDescripcionContaining(descripcion);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipDeduccionGeneralTope agregar(AfipDeduccionGeneralTope elemento) throws Exception {
        elemento = formatearStrings(elemento);
        controlarLongitud(elemento);
        if (elementoDAO.findByAnioAndAfipDeduccionGeneralOrderByAfipDeduccionGeneral_Id(
                elemento.getAnio(), elemento.getAfipDeduccionGeneral()).isEmpty()) {
            return elementoDAO.saveAndFlush(elemento);
        } else {
            throw new DataIntegrityViolationException(MensajeRespuesta.EXISTENTE_PARA_ANIO_FISCAL + "DEDUCCIÓN GENERAL");
        }
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipDeduccionGeneralTope elemento) throws Exception {
        elemento = formatearStrings(elemento);
        controlarLongitud(elemento);
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Cotrola la longitud de los atributos short
    private void controlarLongitud(AfipDeduccionGeneralTope elemento) {
        String anio = String.valueOf(elemento.getAnio());
        //Obtiene longitud de anio, si no es igual a 4 retorna error
        if (anio.length() > 4 || anio.length() < 4) {
            throw new DataIntegrityViolationException(MensajeRespuesta.SHORT_INCORRECTO + "AÑO");
        }
    }

    //Formatea los strings
    private AfipDeduccionGeneralTope formatearStrings(AfipDeduccionGeneralTope elemento) {
        if (elemento.getDescripcion() != null) {
            elemento.setDescripcion(elemento.getDescripcion().trim());
        }
        return elemento;
    }

}
