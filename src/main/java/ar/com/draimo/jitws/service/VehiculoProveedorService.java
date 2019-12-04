//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IMarcaVehiculoDAO;
import ar.com.draimo.jitws.dao.IProveedorDAO;
import ar.com.draimo.jitws.dao.IVehiculoProveedorDAO;
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
        return alias.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByAliasContainingOrderByAlias(alias);
    }

    //Obtiene una lista por alias filtro remolque
    public List<VehiculoProveedor> listarPorAliasFiltroRemolque(String alias) {
        return elementoDAO.findByAliasContainingAndTipoVehiculo_EsRemolqueTrueOrderByAlias(alias);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public VehiculoProveedor agregar(VehiculoProveedor elemento) throws Exception {
        elemento = formatearStrings(elemento);
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        controlarLongitud(elemento);
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
        elemento = formatearStrings(elemento);
        controlarLongitud(elemento);
        return establecerAlias(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Controla longitud de atributos short
    private void controlarLongitud(VehiculoProveedor elemento) {
        //Obtiene longitud de anio, si es mayor a 4 retorna error
        String anio = String.valueOf(elemento.getAnioFabricacion());
        if (anio.length() > 4 || anio.length() < 4) {
            throw new DataIntegrityViolationException(MensajeRespuesta.SHORT_INCORRECTO
                    + " AÑO FABRICACIÓN");
        }
    }

    //Formatea los strings
    private VehiculoProveedor formatearStrings(VehiculoProveedor elemento) {
        elemento.setDominio(elemento.getDominio().trim().toUpperCase());
        elemento.setNumeroMotor(elemento.getNumeroMotor() != null ? elemento.getNumeroMotor().trim().toUpperCase()
                : elemento.getNumeroMotor());
        elemento.setNumeroChasis(elemento.getNumeroChasis() != null ? elemento.getNumeroChasis().trim().toUpperCase()
                : elemento.getNumeroChasis());
        elemento.setNumeroPoliza(elemento.getNumeroPoliza().trim());
        elemento.setNumeroRuta(elemento.getNumeroRuta().trim());
        return elemento;
    }

}
