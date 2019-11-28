//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IProveedorCuentaContableDAO;
import ar.com.draimo.jitws.dao.IProveedorDAO;
import ar.com.draimo.jitws.dao.ITipoDocumentoDAO;
import ar.com.draimo.jitws.dto.ProveedorDTO;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.Proveedor;
import ar.com.draimo.jitws.model.ProveedorCuentaContable;
import ar.com.draimo.jitws.model.TipoDocumento;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
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

    //Define la referencia al dao ProveedorCuentaContable
    @Autowired
    IProveedorCuentaContableDAO proveedorCuentaContableDAO;

    //Define la referencia al dao tipoDocumento
    @Autowired
    ITipoDocumentoDAO tipoDocumentoDAO;
    
    //Define la referencia al dao empresa
    @Autowired
    IEmpresaDAO empresaDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Proveedor elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<Proveedor> proveedores = elementoDAO.findAll();
        return aplicarFiltros(proveedores);
    }
    
    //Obtiene un listado por filtro
    public Object listarPorFiltros(ProveedorDTO proveedorDTO) throws IOException {
        List<Proveedor> elementos = elementoDAO.listarPorFiltros(proveedorDTO.getIdTipoProveedor(), proveedorDTO.getIdLocalidad(),
                proveedorDTO.getIdCondicionCompra());
        return aplicarFiltros(elementos);
    }

    //Obtiene una lista por nombre
    public Object listarPorAlias(String alias) throws IOException {
        List<Proveedor> proveedores = alias.equals("***") ? elementoDAO.findAll()
                : elementoDAO.findByAliasContaining(alias);
        //Construye la lista de rubros productos cuentas contables para cada empresa
        for (Proveedor proveedor : proveedores) {
            proveedor.setProveedorCuentasContables(construirCuentasContablesParaEmpresas(proveedor));
        }
        return aplicarFiltros(proveedores);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Proveedor agregar(Proveedor elemento) {
        elemento = formatearStrings(elemento);
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        Proveedor proveedor = elementoDAO.saveAndFlush(elemento);
        if (elemento.getProveedorCuentasContables() != null) {
            for (ProveedorCuentaContable pcc : elemento.getProveedorCuentasContables()) {
                if(pcc.getPlanCuentaCompra() != null) {
                    pcc.setProveedor(proveedor);
                    proveedorCuentaContableDAO.saveAndFlush(pcc);
                }
            }
        }
        return proveedor;
    }

    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public Proveedor establecerAlias(Proveedor elemento) {
        TipoDocumento t = tipoDocumentoDAO.findById(elemento.getTipoDocumento().getId()).get();
        elemento.setAlias(elemento.getId() + " - " + elemento.getRazonSocial() + " - " + elemento.getNombreFantasia()
                + " - " + t.getAbreviatura() + " " + elemento.getNumeroDocumento());
        return elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Proveedor elemento) {
        elemento = formatearStrings(elemento);
        establecerAlias(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        proveedorCuentaContableDAO.deleteByProveedor(elementoDAO.findById(elemento).get());
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
    
    //Arma la lista de cliente cuentas contables para todas las empresas
    private List<ProveedorCuentaContable> construirCuentasContablesParaEmpresas(Proveedor proveedor) {
        List<Empresa> empresas = empresaDAO.findAll();
        List<ProveedorCuentaContable> pccLista = new ArrayList<>();
        ProveedorCuentaContable pcc;
        for (Empresa empresa : empresas) {
            pcc = new ProveedorCuentaContable();
            pcc.setEmpresa(empresa);
            pcc.setProveedor(null);
            pcc.setPlanCuentaCompra(null);
            pccLista.add(pcc);
        }
        int indice;
        for (ProveedorCuentaContable r : proveedor.getProveedorCuentasContables()) {
            indice = r.getEmpresa().getId() - 1;
            pccLista.get(indice).setId(r.getId());
            pccLista.get(indice).setVersion(r.getVersion());
            pccLista.get(indice).setProveedor(r.getProveedor());
            pccLista.get(indice).setPlanCuentaCompra(r.getPlanCuentaCompra());
        }
        return pccLista;
    }

    //Retorna un object aplicando los filtros
    private Object aplicarFiltros(List<Proveedor> elementos) throws IOException {
       ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
}
