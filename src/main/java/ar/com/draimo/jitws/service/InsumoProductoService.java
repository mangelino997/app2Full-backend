//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.InsumoProducto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IInsumoProductoDAO;
import ar.com.draimo.jitws.dao.IMarcaProductoDAO;
import ar.com.draimo.jitws.dao.IRubroProductoDAO;
import ar.com.draimo.jitws.model.RubroProducto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.math.BigDecimal;

/*
*
 * Servicio InsumoProducto
 * @author blas
 */

@Service
public class InsumoProductoService {
    
    //Define la referencia al dao
    @Autowired
    IInsumoProductoDAO elementoDAO;
    
    //Define la referencia al dao de rubro
    @Autowired
    IRubroProductoDAO rubroProductoDAO;
    
    //Define la referencia al dao de marca
    @Autowired
    IMarcaProductoDAO marcaProductoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        InsumoProducto elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<InsumoProducto> elementos = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(elementos);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Obtiene una lista por rubro y marca
    public Object listarPorRubroYMarca(int idRubroProducto, int idMarcaProducto) throws IOException {
        RubroProducto rubro = rubroProductoDAO.findById(idRubroProducto).get();
        List<InsumoProducto> elementos =  idMarcaProducto != 0 ? elementoDAO.findByRubroProductoAndMarcaProducto(
                rubro, marcaProductoDAO.findById(idMarcaProducto).get()): elementoDAO.findByRubroProducto(rubro);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(elementos);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Obtiene una lista por nombre
    public Object listarPorAlias(String alias) throws IOException {
        List<InsumoProducto> elementos= alias.equals("***")? elementoDAO.findAll():
            elementoDAO.findByAliasContaining(alias);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(elementos);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Obtiene una lista de combustibles
    public Object listarCombustibles() throws IOException {
        List<InsumoProducto> elementos = elementoDAO.findByRubroProducto_EsCombustibleTrue();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(elementos);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Obtiene una lista de insumos
    public Object listarInsumos() throws IOException {
        List<InsumoProducto> elementos = elementoDAO.findByRubroProducto_EsInsumoTrue();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(elementos);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Obtiene precio unitario por insumo
    public BigDecimal obtenerPrecioUnitario(int idInsumoProducto) {
        InsumoProducto insumoProducto = elementoDAO.findById(idInsumoProducto).get();
        return insumoProducto.getPrecioUnitarioViaje();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public int agregar(InsumoProducto elemento) throws IOException {
        elemento = formatearStrings(elemento);
        elemento.setPrecioUnitarioVenta(elemento.getPrecioUnitarioVenta()== null? 
                new BigDecimal(0): elemento.getPrecioUnitarioVenta());
        elemento.setPrecioUnitarioViaje(elemento.getPrecioUnitarioViaje()== null? 
                new BigDecimal(0):elemento.getPrecioUnitarioViaje());
        elemento.setItcNeto(elemento.getItcNeto()== null? new BigDecimal(0): 
                elemento.getItcNeto());
        elemento.setItcPorLitro(elemento.getItcPorLitro()== null? new BigDecimal(0):
                elemento.getItcPorLitro());
        elementoDAO.saveAndFlush(elemento);
        return elemento.getId();
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(InsumoProducto elemento) {
        elemento = formatearStrings(elemento);
        elemento.setPrecioUnitarioVenta(elemento.getPrecioUnitarioVenta()== null? 
                new BigDecimal(0): elemento.getPrecioUnitarioVenta());
        elemento.setPrecioUnitarioViaje(elemento.getPrecioUnitarioViaje()== null? 
                new BigDecimal(0):elemento.getPrecioUnitarioViaje());
        elemento.setItcNeto(elemento.getItcNeto()== null? new BigDecimal(0): 
                elemento.getItcNeto());
        elemento.setItcPorLitro(elemento.getItcPorLitro()== null? new BigDecimal(0):
                elemento.getItcPorLitro());
        establecerAlias(elemento.getId(), elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Establece el alias
    public void establecerAlias(int id, InsumoProducto elemento) {
        elemento.setAlias(id +" - "+ elemento.getNombre() + " - "+
                elemento.getRubroProducto().getNombre() + " - " + 
                elemento.getMarcaProducto().getNombre());
        elementoDAO.save(elemento);
    }
    
    //Formatea los strings
    private InsumoProducto formatearStrings(InsumoProducto elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }
    
}
