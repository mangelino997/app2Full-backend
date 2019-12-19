//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.model.TipoConceptoVenta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dto.InitVentaConceptoDTO;
import ar.com.draimo.jitws.dao.ITipoConceptoVentaDAO;

/**
 * Servicio Tipo Concepto Venta
 *
 * @author blas
 */
@Service
public class TipoConceptoVentaService {

    //Define la referencia al dao
    @Autowired
    ITipoConceptoVentaDAO elementoDAO;

    //Define la referencia al dao TipoComprobante
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;
    
    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Obtiene listas necesarias para inicializar el componente (front)
    public InitVentaConceptoDTO inicializar(int idRol, int idSubopcion) {
        InitVentaConceptoDTO elemento = new InitVentaConceptoDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idSubopcion));
        elemento.setUltimoId(obtenerSiguienteId());
        elemento.setTipoComprobantes(tipoComprobanteDAO.findAll());
        return elemento;
    }

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        TipoConceptoVenta elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<TipoConceptoVenta> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<TipoConceptoVenta> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Obtiene la lista por tipo de comprobante
    public List<TipoConceptoVenta> listarPorTipoComprobante(int idTipoComprobante) {
        return elementoDAO.findByTipoComprobanteAndEstaHabilitadoTrue(
                tipoComprobanteDAO.findById(idTipoComprobante).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public TipoConceptoVenta agregar(TipoConceptoVenta elemento) {
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(TipoConceptoVenta elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private TipoConceptoVenta formatearStrings(TipoConceptoVenta elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}