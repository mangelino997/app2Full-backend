package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IProveedorCuentaContableDAO;
import ar.com.draimo.jitws.dao.IProveedorDAO;
import ar.com.draimo.jitws.dao.ITipoDocumentoDAO;
import ar.com.draimo.jitws.model.Proveedor;
import ar.com.draimo.jitws.model.ProveedorCuentaContable;
import ar.com.draimo.jitws.model.TipoDocumento;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Proveedor
 *
 * @author blas
 */
@Service
public class ProveedorService {

    //Define la referencia al dao
    @Autowired
    IProveedorDAO elementoDAO;

    //Define la referencia al dao ProveedorCuentaContableDAO
    @Autowired
    IProveedorCuentaContableDAO proveedorCuentaContableDAO;

    //Define la referencia al dao tipoDocumento
    @Autowired
    ITipoDocumentoDAO tipoDocumentoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Proveedor elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<Proveedor> proveedores = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string = mapper.writer(filters).writeValueAsString(proveedores);
        return new ObjectMapper().readValue(string, Object.class);
    }

    //Obtiene una lista por nombre
    public Object listarPorAlias(String alias) throws IOException {
        List<Proveedor> proveedores = null;
        if (alias.equals("***")) {
            proveedores = elementoDAO.findAll();
        } else {
            proveedores = elementoDAO.findByAliasContaining(alias);
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string = mapper.writer(filters).writeValueAsString(proveedores);
        return new ObjectMapper().readValue(string, Object.class);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Proveedor agregar(Proveedor elemento) {
        elemento = formatearStrings(elemento);
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        Proveedor proveedor = elementoDAO.saveAndFlush(elemento);
        if (elemento.getProveedorCuentasContables() != null) {
            for (ProveedorCuentaContable pcc : elemento.getProveedorCuentasContables()) {
                pcc.setProveedor(proveedor);
                proveedorCuentaContableDAO.saveAndFlush(pcc);
            }
        }
        return proveedor;
    }

    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public void establecerAlias(Proveedor elemento) {
        TipoDocumento t = tipoDocumentoDAO.findById(elemento.getTipoDocumento().getId()).get();
        elemento.setAlias(elemento.getId() + " - " + elemento.getRazonSocial()
                + " - " + t.getAbreviatura() + " " + elemento.getNumeroDocumento());
        elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Proveedor elemento) {
        elemento = formatearStrings(elemento);
        establecerAlias(elemento);
        Proveedor proveedor = elementoDAO.save(elemento);
        if (elemento.getProveedorCuentasContables() != null) {
            for (ProveedorCuentaContable pcc : elemento.getProveedorCuentasContables()) {
                pcc.setProveedor(proveedor);
                proveedorCuentaContableDAO.save(pcc);
            }
        }
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private Proveedor formatearStrings(Proveedor elemento) {
        elemento.setRazonSocial(elemento.getRazonSocial().trim().toUpperCase());
        elemento.setDomicilio(elemento.getDomicilio().trim());
        elemento.setNumeroDocumento(elemento.getNumeroDocumento().trim());
        if (elemento.getNombreFantasia() != null) {
            elemento.setNombreFantasia(elemento.getNombreFantasia().trim());
        }
        if (elemento.getNumeroIIBB() != null) {
            elemento.setNumeroIIBB(elemento.getNumeroIIBB().trim());
        }
        if (elemento.getSitioWeb() != null) {
            elemento.setSitioWeb(elemento.getSitioWeb().trim().toLowerCase());
        }
        if (elemento.getTelefono() != null) {
            elemento.setTelefono(elemento.getTelefono().trim());
        }
        if (elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        if (elemento.getNotaIngresarComprobante() != null) {
            elemento.setNotaIngresarComprobante(elemento.getNotaIngresarComprobante().trim());
        }
        if (elemento.getNotaImpresionOrdenPago() != null) {
            elemento.setNotaImpresionOrdenPago(elemento.getNotaImpresionOrdenPago().trim());
        }
        if (elemento.getNumeroCuenta() != null) {
            elemento.setNumeroCuenta(elemento.getNumeroCuenta().trim());
        }
        if (elemento.getTitular() != null) {
            elemento.setTitular(elemento.getTitular().trim());
        }
        if (elemento.getNumeroCBU() != null) {
            elemento.setNumeroCBU(elemento.getNumeroCBU().trim());
        }
        if (elemento.getAliasCBU() != null) {
            elemento.setAliasCBU(elemento.getAliasCBU().trim());
        }
        return elemento;
    }

}
