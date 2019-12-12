//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.model.VentaItemConcepto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IVentaItemConceptoDAO;
import ar.com.draimo.jitws.dto.InitVentaConceptoDTO;

/**
 * Servicio Venta Item Concepto
 *
 * @author blas
 */
@Service
public class VentaItemConceptoService {

    //Define la referencia al dao
    @Autowired
    IVentaItemConceptoDAO elementoDAO;

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
        VentaItemConcepto elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<VentaItemConcepto> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<VentaItemConcepto> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Obtiene la lista por tipo de comprobante
    public List<VentaItemConcepto> listarPorTipoComprobante(int idTipoComprobante) {
        return elementoDAO.findByTipoComprobanteAndEstaHabilitadoTrue(
                tipoComprobanteDAO.findById(idTipoComprobante).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public VentaItemConcepto agregar(VentaItemConcepto elemento) {
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(VentaItemConcepto elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private VentaItemConcepto formatearStrings(VentaItemConcepto elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}