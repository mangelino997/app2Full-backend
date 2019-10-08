//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IMarcaVehiculoDAO;
import ar.com.draimo.jitws.dao.IProveedorDAO;
import ar.com.draimo.jitws.dao.IVehiculoProveedorDAO;
import ar.com.draimo.jitws.exception.CodigoRespuesta;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.MarcaVehiculo;
import ar.com.draimo.jitws.model.Proveedor;
import ar.com.draimo.jitws.model.VehiculoProveedor;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio VehiculoProveedor
 *
 * @author blas
 */
@Service
public class VehiculoProveedorService {

    //Define la referencia al dao
    @Autowired
    IVehiculoProveedorDAO elementoDAO;

    //Define la referencia al dao de proveedor
    @Autowired
    IProveedorDAO proveedorDAO;

    //Define la referencia al dao de marcaVehiculo
    @Autowired
    IMarcaVehiculoDAO marcaVehiculoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        VehiculoProveedor elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<VehiculoProveedor> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por proveedor
    public List<VehiculoProveedor> listarPorProveedor(int idProveedor) {
        return elementoDAO.findByProveedor(proveedorDAO.findById(idProveedor).get());
    }

    //Obtiene una lista por nombre
    public List<VehiculoProveedor> listarPorAlias(String alias) {
        List<VehiculoProveedor> elementos= alias.equals("***") ? elementoDAO.findAll()
                : elementoDAO.findByAliasContainingOrderByAlias(alias);
        if(elementos.isEmpty()) {
            throw new DataIntegrityViolationException(String.valueOf(
                    CodigoRespuesta.LISTA_SIN_CONTENIDO));
        }
        return elementos;
    }

    //Obtiene una lista por alias filtro remolque
    public List<VehiculoProveedor> listarPorAliasFiltroRemolque(String alias) {
        List<VehiculoProveedor> elementos  = 
            elementoDAO.findByAliasContainingAndTipoVehiculo_EsRemolqueTrueOrderByAlias(alias);
        if(elementos.isEmpty()) {
            throw new DataIntegrityViolationException(String.valueOf(
                    CodigoRespuesta.LISTA_SIN_CONTENIDO));
        }
        return elementos;
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public VehiculoProveedor agregar(VehiculoProveedor elemento) throws Exception {
        elemento = formatearStrings(elemento);
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        //Obtiene longitud de anio, si es mayor a 4 retorna error
        String anio = String.valueOf(elemento.getAnioFabricacion());
        if (anio.length() > 4 || anio.length() < 4) {
            throw new DataIntegrityViolationException(MensajeRespuesta.SHORT_INCORRECTO + " AÑO FABRICACIÓN");
        }
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public VehiculoProveedor establecerAlias(VehiculoProveedor elemento) {
        Proveedor p = proveedorDAO.findById(elemento.getProveedor().getId()).get();
        MarcaVehiculo m = marcaVehiculoDAO.findById(elemento.getMarcaVehiculo().getId()).get();
        elemento.setAlias(elemento.getId() + " - " + p.getRazonSocial()
                + " - " + elemento.getDominio() + " - " + m.getNombre());
        return elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public VehiculoProveedor actualizar(VehiculoProveedor elemento) throws Exception {
        elemento.setFechaUltimaMod(new Date(new java.util.Date().getTime()));
        //Obtiene longitud de anio, si es mayor a 4 retorna error
        String anio = String.valueOf(elemento.getAnioFabricacion());
        if (anio.length() > 4 || anio.length() < 4) {
            throw new DataIntegrityViolationException(MensajeRespuesta.SHORT_INCORRECTO + " AÑO FABRICACIÓN");
        }
        return establecerAlias(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private VehiculoProveedor formatearStrings(VehiculoProveedor elemento) {
        elemento.setDominio(elemento.getDominio().trim());
        elemento.setNumeroMotor(elemento.getNumeroMotor() != null ? elemento.getNumeroMotor().trim()
                : elemento.getNumeroMotor());
        elemento.setNumeroChasis(elemento.getNumeroChasis() != null ? elemento.getNumeroChasis().trim()
                : elemento.getNumeroChasis());
        elemento.setNumeroPoliza(elemento.getNumeroPoliza().trim());
        elemento.setNumeroRuta(elemento.getNumeroRuta().trim());
        return elemento;
    }

}