//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ITipoTarifaDAO;
import ar.com.draimo.jitws.dto.GenericoDTO;
import ar.com.draimo.jitws.dto.TipoTarifaDTO;
import ar.com.draimo.jitws.model.TipoTarifa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio TipoTarifa
 *
 * @author blas
 */
@Service
public class TipoTarifaService {

    //Define la referencia al dao
    @Autowired
    ITipoTarifaDAO elementoDAO;

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
        TipoTarifa elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<TipoTarifa> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<TipoTarifa> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Obtiene una lista por escala
    public List<TipoTarifa> listarPorEscala() {
        return elementoDAO.findByPorEscalaTrue();
    }

    //Obtiene una lista por tramo
    public List<TipoTarifa> listarPorTramo() {
        return elementoDAO.findByPorEscalaFalse();
    }

    //Obtiene una lista por ordenVenta
    public List<TipoTarifa> listarPorOrdenVenta(int idOrdenVenta) {
        return elementoDAO.listarPorOrdenVenta(idOrdenVenta);
    }

    //Obtiene una lista de tarifas por ordenVenta con el idOrdenVentaTarifa
    public List<TipoTarifaDTO> listarTarifasPorOrdenVenta(int idOrdenVenta) {
        return elementoDAO.listarTarifasPorOrdenVenta(idOrdenVenta);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public TipoTarifa agregar(TipoTarifa elemento) {
        return elementoDAO.save(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(TipoTarifa elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private TipoTarifa formatearStrings(TipoTarifa elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}
