//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IVentaConfigDAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.VentaConfig;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio VentaConfig
 *
 * @author blas
 */
@Service
public class VentaConfigService {

    //Define la referencia al dao
    @Autowired
    IVentaConfigDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        VentaConfig elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene un registro por id
    public VentaConfig obtenerPorId(int id) {
        return elementoDAO.findById(id).get();
    }

    //Obtiene la lista completa
    public List<VentaConfig> listar() {
        return elementoDAO.findAll();
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public VentaConfig agregar(VentaConfig elemento) throws Exception {
        controlarLongitud(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(VentaConfig elemento) throws Exception {
        controlarLongitud(elemento);
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Control de longitud de atributos short
    private void controlarLongitud(VentaConfig elemento) {
        //Obtiene longitud de aforo, si supera 3 retorna error
        String aforo = String.valueOf(elemento.getAforo());
        if (aforo.length() > 3) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " AFORO");
        }
    }
    
}