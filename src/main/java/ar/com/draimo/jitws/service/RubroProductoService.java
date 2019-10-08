//Paquete  al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IRubroProductoCuentaContableDAO;
import ar.com.draimo.jitws.dao.IRubroProductoDAO;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.RubroProducto;
import ar.com.draimo.jitws.model.RubroProductoCuentaContable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio RubroProducto
 *
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

    //Define la referencia a usuarioEmpresaService
    @Autowired
    UsuarioEmpresaService usuarioEmpresaService;

    //Define la referencia al dao empresa
    @Autowired
    IEmpresaDAO empresaDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        RubroProducto elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<RubroProducto> rubrosProductos = elementoDAO.findAllByOrderByNombreAsc();
        //Construye la lista de rubros productos cuentas contables para cada empresa
        for (RubroProducto rubroProducto : rubrosProductos) {
            rubroProducto.setRubrosProductosCuentasContables(construirCuentasContablesParaEmpresas(rubroProducto));
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string = mapper.writer(filters).writeValueAsString(rubrosProductos);
        return new ObjectMapper().readValue(string, Object.class);
    }

    //Obtiene una lista por nombre
    public Object listarPorNombre(String nombre) throws IOException {
        List<RubroProducto> rubrosProductos = nombre.equals("***")?elementoDAO.findAll():
             elementoDAO.findByNombreContaining(nombre);
        //Construye la lista de rubros productos cuentas contables para cada empresa
        for (RubroProducto rubroProducto : rubrosProductos) {
            rubroProducto.setRubrosProductosCuentasContables(construirCuentasContablesParaEmpresas(rubroProducto));
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string = mapper.writer(filters).writeValueAsString(rubrosProductos);
        return new ObjectMapper().readValue(string, Object.class);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public RubroProducto agregar(RubroProducto elemento) {
        elemento = formatearStrings(elemento);
        RubroProducto rubroProducto = elementoDAO.saveAndFlush(elemento);
        for (RubroProductoCuentaContable rpcc : elemento.getRubrosProductosCuentasContables()) {
            if (rpcc.getPlanCuentaCompra() != null) {
                rpcc.setRubroProducto(rubroProducto);
                rubroProductoCuentaContableDAO.saveAndFlush(rpcc);
            }
        }
        return rubroProducto;
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(RubroProducto elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        rubroProductoCuentaContableDAO.deleteByRubroProducto(elementoDAO.findById(elemento).get());
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private RubroProducto formatearStrings(RubroProducto elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

    //Arma la lista de rubros productos cuentas contables para todas las empresas
    private List<RubroProductoCuentaContable> construirCuentasContablesParaEmpresas(RubroProducto rubroProducto) {
        List<Empresa> empresas = empresaDAO.findAll();
        List<RubroProductoCuentaContable> rpccLista = new ArrayList<>();
        RubroProductoCuentaContable rpcc;
        for (Empresa empresa : empresas) {
            rpcc = new RubroProductoCuentaContable();
            rpcc.setEmpresa(empresa);
            rpcc.setRubroProducto(null);
            rpcc.setPlanCuentaCompra(null);
            rpccLista.add(rpcc);
        }
        int indice;
        for(RubroProductoCuentaContable r : rubroProducto.getRubrosProductosCuentasContables()) {
            indice = r.getEmpresa().getId()-1;
            rpccLista.get(indice).setId(r.getId());
            rpccLista.get(indice).setVersion(r.getVersion());
            rpccLista.get(indice).setRubroProducto(r.getRubroProducto());
            rpccLista.get(indice).setPlanCuentaCompra(r.getPlanCuentaCompra());
        }
        return rpccLista;
    }

}