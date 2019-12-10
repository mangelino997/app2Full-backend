package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IMonedaCuentaContableDAO;
import ar.com.draimo.jitws.dao.IMonedaDAO;
import ar.com.draimo.jitws.dto.InitMonedaCuentaContableDTO;
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
 *
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
    
    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Obtiene listas necesarias para inicializar el componente (front)
    public InitMonedaCuentaContableDTO inicializar(int idEmpresa, int idRol, int idSubopcion) {
        InitMonedaCuentaContableDTO elemento = new InitMonedaCuentaContableDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idSubopcion));
        elemento.setMonedas(monedaDAO.findAll());
        elemento.setMonedaCuentaContables(elementoDAO.findByEmpresa(empresaDAO.findById(idEmpresa).get()));
        elemento.setUltimoId(obtenerSiguienteId());
        return elemento;
    }

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        MonedaCuentaContable elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<MonedaCuentaContable> monedasCuentasContables = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string = mapper.writer(filters).writeValueAsString(monedasCuentasContables);
        return new ObjectMapper().readValue(string, Object.class);
    }

    //Obtiene una lista por moneda
    public List<MonedaCuentaContable> listarPorMoneda(int id) {
        Optional<Moneda> elemento = monedaDAO.findById(id);
        return elementoDAO.findByMoneda(elemento.get());
    }

    //Obtiene una lista por nombre de moneda
    public Object listarPorNombreMoneda(String nombre, int idEmpresa) throws IOException {
        Empresa empresa = empresaDAO.findById(idEmpresa).get();
        List<MonedaCuentaContable> monedasCuentasContables = nombre.equals("*")?
            elementoDAO.findByEmpresa(empresa):
            elementoDAO.findByMoneda_NombreContainingAndEmpresa(nombre, empresa);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string = mapper.writer(filters).writeValueAsString(monedasCuentasContables);
        return new ObjectMapper().readValue(string, Object.class);
    }

    //Obtiene una lista por moneda
    public Object listarPorEmpresa(int id) throws IOException {
        Optional<Empresa> elemento = empresaDAO.findById(id);
        List<MonedaCuentaContable> monedas= elementoDAO.findByEmpresa(elemento.get());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string = mapper.writer(filters).writeValueAsString(monedas);
        return new ObjectMapper().readValue(string, Object.class);
    }

    //Obtiene un registro por moneda y empresa
    public Object obtenerPorMonedaYEmpresa(int idMoneda, int idEmpresa) throws IOException {
        Moneda moneda = monedaDAO.findById(idMoneda).get();
        Empresa empresa = empresaDAO.findById(idEmpresa).get();
        MonedaCuentaContable monedaCuentaContable = elementoDAO.findByMonedaAndEmpresa(moneda, empresa);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string = mapper.writer(filters).writeValueAsString(monedaCuentaContable);
        return new ObjectMapper().readValue(string, Object.class);
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
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    public Object listarPorNombreMoneda(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}