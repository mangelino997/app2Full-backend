package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IMonedaCuentaContableDAO;
import ar.com.draimo.jitws.dao.IMonedaDAO;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.Moneda;
import ar.com.draimo.jitws.model.MonedaCuentaContable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio MonedaCuentaContable
 * @author blas
 */

@Service
public class MonedaCuentaContableService {

    //Define la referencia al dao
    @Autowired
    IMonedaCuentaContableDAO elementoDAO;
    
    //Define la referencia al dao moneda
    @Autowired
    IMonedaDAO monedaDAO;
    
    //Define la referencia al dao empresa
    @Autowired
    IEmpresaDAO empresaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        MonedaCuentaContable elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<MonedaCuentaContable> monedasCuentasContables = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(monedasCuentasContables);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Obtiene una lista por moneda
    public List<MonedaCuentaContable> listarPorMoneda(int id) {
        Optional<Moneda> elemento = monedaDAO.findById(id);
        return elementoDAO.findByMoneda(elemento.get());
    }
    
    //Obtiene una lista por moneda
    public List<MonedaCuentaContable> listarPorEmpresa(int id) {
        Optional<Empresa> elemento = empresaDAO.findById(id);
        return elementoDAO.findByEmpresa(elemento.get());
    }
    
    //Obtiene un registro por moneda y empresa
    public MonedaCuentaContable obtenerPorMonedaYEmpresa(int idMoneda, int idEmpresa) {
        Moneda moneda = monedaDAO.findById(idMoneda).get();
        Empresa empresa = empresaDAO.findById(idEmpresa).get();
        return elementoDAO.findByMonedaAndEmpresa(moneda, empresa);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public MonedaCuentaContable agregar(MonedaCuentaContable elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(MonedaCuentaContable elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(MonedaCuentaContable elemento) {
        elementoDAO.delete(elemento);
    }

}
