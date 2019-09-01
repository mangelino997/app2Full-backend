package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IRubroProductoCuentaContableDAO;
import ar.com.draimo.jitws.dao.IRubroProductoDAO;
import ar.com.draimo.jitws.dao.IUsuarioEmpresaDAO;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.RubroProducto;
import ar.com.draimo.jitws.model.RubroProductoCuentaContable;
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
 * Servicio RubroProducto
 * @author blas
 */

@Service
public class RubroProductoService {

    //Define la referencia al dao
    @Autowired
    IRubroProductoDAO elementoDAO;
    
    //Define la referencia al RubroProductoCuentaContableDAO
    @Autowired
    IRubroProductoCuentaContableDAO rubroProductoCuentaContableDAO;
    
    //Define la referencia al usuarioEmpresaService
    @Autowired
    UsuarioEmpresaService usuarioEmpresaService;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        RubroProducto elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<RubroProducto> rubrosProductos = elementoDAO.findAllByOrderByNombreAsc();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(rubrosProductos);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Obtiene una lista por nombre
    public Object listarPorNombre(String nombre) throws IOException {
        List<RubroProducto> rubrosProductos = null;
        if(nombre.equals("***")) {
            rubrosProductos = elementoDAO.findAll();
        } else {
            rubrosProductos = elementoDAO.findByNombreContaining(nombre);
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(rubrosProductos);
        return new ObjectMapper().readValue(string, Object.class);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public RubroProducto agregar(RubroProducto elemento) {
        elemento = formatearStrings(elemento);
        RubroProducto rubroProducto = elementoDAO.saveAndFlush(elemento);
        for(RubroProductoCuentaContable rpcc : elemento.getRubrosProductosCuentasContables()) {
            rpcc.setRubroProducto(rubroProducto);
            rubroProductoCuentaContableDAO.saveAndFlush(rpcc);
        }
        return rubroProducto;
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(RubroProducto elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
        RubroProducto rubroProducto = elementoDAO.save(elemento);
        for(RubroProductoCuentaContable rpcc : elemento.getRubrosProductosCuentasContables()) {
            rpcc.setRubroProducto(rubroProducto);
            rubroProductoCuentaContableDAO.save(rpcc);
        }
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private RubroProducto formatearStrings(RubroProducto elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}
