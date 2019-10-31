//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IOrigenDestinoDAO;
import ar.com.draimo.jitws.dao.ITramoDAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Tramo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Tramo
 * @author blas
 */

@Service
public class TramoService {

    //Define la referencia al dao
    @Autowired
    ITramoDAO elementoDAO;
    
    //Define la referencia al dao origenDestino
    @Autowired
    IOrigenDestinoDAO origenDestinoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Tramo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<Tramo> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por origen
    public List<Tramo> listarPorOrigen(String nombre) {
        return elementoDAO.findByOrigen_NombreContaining(nombre);
    }
    
    //Obtiene una lista por destino
    public List<Tramo> listarPorDestino(String nombre) {
        return elementoDAO.findByDestino_NombreContaining(nombre);
    }
    
    //Obtiene una lista por origen y/o destino
    public List<Tramo> listarPorFiltro(int idOrigen, int idDestino) {
        return (idOrigen != 0 && idDestino != 0?elementoDAO.findByOrigenAndDestino(
            origenDestinoDAO.findById(idOrigen).get(),origenDestinoDAO.findById(idDestino).get()):
                idOrigen!=0?elementoDAO.findByOrigen( origenDestinoDAO.findById(idOrigen).get()):
                idDestino!=0?elementoDAO.findByDestino(origenDestinoDAO.findById(idDestino).get()):
                elementoDAO.findAll()); 
    }
    

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Tramo agregar(Tramo elemento) throws Exception {
        controlarLongitud(elemento);
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Tramo elemento) throws Exception {
        controlarLongitud(elemento);
        elementoDAO.save(formatearStrings(elemento));
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Controla la longitud de atributos short
    private void controlarLongitud( Tramo elemento) {
        //Obtiene longitud de anio, si es mayor a 4 retorna error
        String km = String.valueOf(elemento.getKm());
        if (km.length()>4) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " KM");
        }
    }
    
    //Formatea los strings
    private Tramo formatearStrings(Tramo elemento) {
        elemento.setRutaAlternativa(elemento.getRutaAlternativa() == null?"-":
                elemento.getRutaAlternativa().trim());
        return elemento;
    }

}