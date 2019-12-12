//Paquete al que pertenece el servixio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.dto.GenericoDTO;
import ar.com.draimo.jitws.model.TipoComprobante;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio TipoComprobante
 *
 * @author blas
 */
@Service
public class TipoComprobanteService {

    //Define la referencia al dao
    @Autowired
    ITipoComprobanteDAO elementoDAO;
    
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
        TipoComprobante elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene un registro por id
    public TipoComprobante obtenerPorId(int id) {
        return elementoDAO.findById(id).get();
    }

    //Obtiene la lista completa
    public List<TipoComprobante> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene un listado por nombre
    public List<TipoComprobante> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Obtiene una lista por esta activo compra carga igual true
    public List<TipoComprobante> listarEstaActivoCompraCarga() {
        return elementoDAO.findByEstaActivoCompraCargaTrue();
    }
    
    //Obtiene una lista por esta activo ingreso carga igual true
    public List<TipoComprobante> listarEstaActivoIngresoCarga() {
        return elementoDAO.findByEstaActivoIngresoCargaTrue();
    }

    //Obtiene una lista por esta activo Reparto igual true
    public List<TipoComprobante> listarEstaActivoReparto() {
        return elementoDAO.findByEstaActivoRepartoTrue();
    }

    //Obtiene una lista por numeracion punto venta igual true
    public List<TipoComprobante> listarNumeracionPuntoVenta() {
        return elementoDAO.findByNumeracionPuntoVentaTrue();
    }

    //Obtiene una lista para emision de factura
    public List<TipoComprobante> listarParaEmisionFactura() {
        return elementoDAO.listarParaFactura();
    }

    //Obtiene una lista para emision de nota de credito
    public List<TipoComprobante> listarParaEmisionNotaCredito() {
        return elementoDAO.listarParaNotaCredito();
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public TipoComprobante agregar(TipoComprobante elemento) {
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(TipoComprobante elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private TipoComprobante formatearStrings(TipoComprobante elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        elemento.setAbreviatura(elemento.getAbreviatura().trim());
        return elemento;
    }

}