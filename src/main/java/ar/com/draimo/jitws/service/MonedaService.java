//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IMonedaDAO;
import ar.com.draimo.jitws.dto.GenericoDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Moneda;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Moneda
 *
 * @author blas
 */
@Service
public class MonedaService {

    //Define la referencia al dao
    @Autowired
    IMonedaDAO elementoDAO;
    
    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Obtiene listas necesarias para inicializar el componente (front)
    public GenericoDTO inicializar(int idRol, int idSubopcion) {
        GenericoDTO elemento = new GenericoDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idSubopcion));
        elemento.setUltimoId(obtenerSiguienteId());
        return elemento;
    }

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Moneda elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<Moneda> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<Moneda> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Obtiene la moneda principal por defecto
    public Moneda obtenerPorDefecto() {
        return elementoDAO.findByPorDefectoTrue();
    }

    //Establece la moneda como principal
    @Transactional(rollbackFor = Exception.class)
    public void establecerMonedaPrincipal(int idMoneda) {
        Moneda moneda = elementoDAO.findById(idMoneda).get();
        moneda.setPorDefecto(false);
        elementoDAO.save(moneda);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Moneda agregar(Moneda elemento) {
        elemento = formatearStrings(elemento);
        Moneda moneda = elementoDAO.findByPorDefectoTrue();
        if (moneda == null || moneda.getId() == elemento.getId()) {
            elemento.setPorDefecto(true);
        } else {
            if (elemento.getPorDefecto()) {
                moneda.setPorDefecto(false);
                elementoDAO.save(moneda);
            }
        }
        return elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Moneda elemento) {
        elemento = formatearStrings(elemento);
        Moneda moneda = elementoDAO.findByPorDefectoTrue();
        if (moneda == null || moneda.getId() == elemento.getId()) {
            elemento.setPorDefecto(true);
        } else {
            if (elemento.getPorDefecto()) {
                moneda.setPorDefecto(false);
                elementoDAO.save(moneda);
            }
        }
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        Moneda moneda = elementoDAO.findByPorDefectoTrue();
        if (moneda.getId() == elemento) {
            throw new DataIntegrityViolationException(MensajeRespuesta.ERROR_MONEDA_POR_DEFECTO);
        } else {
            elementoDAO.deleteById(elemento);
        }
    }

    //Formatea los strings
    private Moneda formatearStrings(Moneda elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}
