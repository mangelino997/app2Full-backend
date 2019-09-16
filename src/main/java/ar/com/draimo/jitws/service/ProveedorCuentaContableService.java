package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IPlanCuentaDAO;
import ar.com.draimo.jitws.dao.IProveedorCuentaContableDAO;
import ar.com.draimo.jitws.dao.IProveedorDAO;
import ar.com.draimo.jitws.model.ProveedorCuentaContable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ProveedorCuentaContable
 * @author blas
 */

@Service
public class ProveedorCuentaContableService {
    
    //Define la referencia al dao
    @Autowired
    IProveedorCuentaContableDAO elementoDAO;
    
    //Define la referencia a empresaDAO
    @Autowired
    IEmpresaDAO empresaDAO;
    
    //Define la referencia a planCuentaDAO
    @Autowired
    IPlanCuentaDAO planCuentaDAO;
    
    //Define la referencia a rubroProductoDAO
    @Autowired
    IProveedorDAO proveedorDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ProveedorCuentaContable elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento!=null?elemento.getId()+1:1);
    }
    
    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<ProveedorCuentaContable> elementos = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(elementos);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Obtiene una lista por EmpresaEmpresa
    public Object listarPorEmpresa(int idEmpresa) throws IOException {
        List<ProveedorCuentaContable> elementos = elementoDAO.findByEmpresa(empresaDAO.findById(idEmpresa).get());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(elementos);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Obtiene una lista por PlanCuenta
    public Object listarPorPlanCuenta(int idPlanCuenta) throws IOException {
        List<ProveedorCuentaContable> elementos = elementoDAO.findByPlanCuentaCompra(
                planCuentaDAO.findById(idPlanCuenta).get());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(elementos);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Obtiene una lista por Proveedor
    public Object listarPorProveedor(int idProveedor) throws IOException {
        List<ProveedorCuentaContable> elementos = elementoDAO.findByProveedor(proveedorDAO.findById(idProveedor).get());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(elementos);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Object agregar(ProveedorCuentaContable elemento) throws IOException {
        elemento = elementoDAO.save(elemento);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(elemento);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public Object actualizar(ProveedorCuentaContable elemento) throws IOException {
        elemento = elementoDAO.save(elemento);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(elemento);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
}
