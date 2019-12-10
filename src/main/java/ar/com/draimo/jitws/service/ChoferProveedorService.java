//Paquete al que pertence el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IChoferProveedorDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IProveedorDAO;
import ar.com.draimo.jitws.dao.ITipoDocumentoDAO;
import ar.com.draimo.jitws.dto.GenericoDTO;
import ar.com.draimo.jitws.dto.InitChoferProveedorDTO;
import ar.com.draimo.jitws.model.ChoferProveedor;
import ar.com.draimo.jitws.model.Proveedor;
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
 * Servicio Chofer Proveedor
 * @author blas
 */

@Service
public class ChoferProveedorService {
    
    //Define la referencia al dao
    @Autowired
    IChoferProveedorDAO elementoDAO;
    
    //Define la referencia al dao proveedor
    @Autowired
    IProveedorDAO proveedorDAO;
    
    //Define la referencia al dao empresa
    @Autowired
    IEmpresaDAO empresaDAO;
    
    //Define la referencia al dao tipodoc
    @Autowired
    ITipoDocumentoDAO tipoDocumentoDAO;
    
    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Obtiene el siguiente id
    public InitChoferProveedorDTO inicializar(int idRol, int idSubopcion) {
        InitChoferProveedorDTO elemento = new InitChoferProveedorDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idSubopcion));
        elemento.setUltimoId(obtenerSiguienteId());
        elemento.setTipoDocumentos(tipoDocumentoDAO.findAll());
        return elemento;
    }
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ChoferProveedor elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ChoferProveedor> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<ChoferProveedor> listarPorAlias(String alias) {
        return alias.equals("*") ?
            elementoDAO.findAll() : elementoDAO.findByAliasContaining(alias);
    }
    
    //Obtiene una lista por alias activos
    public List<ChoferProveedor> listarActivosPorAlias(String alias) {
        return alias.equals("*")?elementoDAO.findByUsuarioBajaIsNull():
                elementoDAO.findByAliasContainingAndUsuarioBajaIsNull(alias);
    }
    
    //Obtiene una lista por alias y proveedor
    public Object listarPorAliasYProveedor(String alias, int idProveedor) throws IOException {
        Proveedor proveedor = proveedorDAO.findById(idProveedor).get();
        List<ChoferProveedor> elementos =  alias.equals("*") ?elementoDAO.findByProveedor(proveedor):
                elementoDAO.findByAliasContainingAndProveedor(alias,proveedor);
        return aplicarFiltros(elementos);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ChoferProveedor agregar(ChoferProveedor elemento) {
        elemento = formatearStrings(elemento);
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        return elementoDAO.saveAndFlush(elemento);
    }
    
    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public void establecerAlias(ChoferProveedor elemento) {
        elemento.setAlias(elemento.getId() + " - " + elemento.getNombre() 
                + " - " + elemento.getNumeroDocumento());
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ChoferProveedor elemento) {
        elemento = formatearStrings(elemento);
        elemento.setFechaUltimaMod(new Date(new java.util.Date().getTime()));
        establecerAlias(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private ChoferProveedor formatearStrings(ChoferProveedor elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        elemento.setDomicilio(elemento.getDomicilio().trim());
        elemento.setNumeroDocumento(elemento.getNumeroDocumento().trim());
        if(elemento.getTelefonoFijo() != null) {
            elemento.setTelefonoFijo(elemento.getTelefonoFijo().trim());
        }
        if(elemento.getTelefonoMovil() != null) {
            elemento.setTelefonoMovil(elemento.getTelefonoMovil().trim());
        }
        return elemento;
    }
    
    
    //Retorna un object aplicando los filtros
    private Object aplicarFiltros(List<ChoferProveedor> elementos) throws IOException {
       ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return new ObjectMapper().readValue(string, Object.class);
    }
}