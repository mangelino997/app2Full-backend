package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IGrupoCuentaContableDAO;
import ar.com.draimo.jitws.model.PlanCuenta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IPlanCuentaDAO;
import ar.com.draimo.jitws.model.Empresa;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Servicio PlandeCuenta
 * @author blas
 */

@Service
public class PlanCuentaService {
    
    //Define la referencia al dao
    @Autowired
    IPlanCuentaDAO elementoDAO;
    
    //Define la referencia al dao Empresa
    @Autowired
    IEmpresaDAO empresaDAO;
    
    //Define la referencia al dao Empresa
    @Autowired
    IGrupoCuentaContableDAO grupoCuentaContableDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        PlanCuenta elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public Object listar() throws JsonProcessingException, IOException {
        List<PlanCuenta> planesCuenta = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(planesCuenta);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Obtiene una lista por nombre
    public Object listarPorNombre(String nombre) throws IOException {
        List<PlanCuenta> planesCuenta = elementoDAO.findAll();
        if(nombre.equals("***")) {
            planesCuenta= elementoDAO.findAll();
        } else {
            planesCuenta= elementoDAO.findByNombreContaining(nombre);
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(planesCuenta);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Obtiene un listado por grupocuentacontable y estaactivo
    public Object listarGrupoActivo(int idEmpresa) throws IOException {
        List<PlanCuenta> planesCuenta =  elementoDAO.listarGrupoActivo(idEmpresa);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(planesCuenta);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Obtiene el plan de cuenta por grupo Estado de Resultados por empresa
    public Object listarGrupoEstadoResultados(int idEmpresa) throws IOException {
        List<PlanCuenta> planesCuenta =  elementoDAO.listarGrupoEstadoResultados(idEmpresa);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(planesCuenta);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Obtiene el plan de cuenta
    public Object obtenerPlanCuenta(int idEmpresa) 
            throws JsonProcessingException, IOException {
        Empresa empresa = empresaDAO.findById(idEmpresa).get();
        PlanCuenta planCuenta = elementoDAO.findByEmpresaAndNivel(empresa, (short)1);
        PlanCuenta pc = crearPlanCuenta(planCuenta);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(pc);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Crea el plan de cuenta
    public PlanCuenta crearPlanCuenta(PlanCuenta planCuenta) throws IOException {
        List<PlanCuenta> hijos = elementoDAO.findByPadre(planCuenta);
        if(!hijos.isEmpty()) {
            planCuenta.setHijos(hijos);
            for(PlanCuenta pc : planCuenta.getHijos()) {
                pc = (PlanCuenta) crearPlanCuenta(pc);
            }
        }
        return planCuenta;
    }
    
    //Lista por empresa y grupo cuenta contable
    public Object listarPorEmpresaYGrupoCuentaContable(int idEmpresa, int idGrupoCuentaContable) throws IOException {
        List<PlanCuenta> planesCuenta = elementoDAO.findByEmpresaAndGrupoCuentaContable(empresaDAO.findById(idEmpresa).get(), 
                grupoCuentaContableDAO.findById(idGrupoCuentaContable).get());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(planesCuenta);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Obtiene por empresa y grupo cuenta contable
    public Object obtenerPorEmpresaYGrupoCuentaContable(int idEmpresa, int idGrupoCuentaContable) throws IOException {
        PlanCuenta planCuenta = elementoDAO.findByEmpresaAndGrupoCuentaContableAndNivel(empresaDAO.findById(idEmpresa).get(), 
                grupoCuentaContableDAO.findById(idGrupoCuentaContable).get(), (short)2);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(planCuenta);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public PlanCuenta agregar(PlanCuenta elemento) throws Exception {
        elemento = formatearStrings(elemento);
        //Obtiene longitud de nivel, si es mayor a 1 retorna error
        String nivel = String.valueOf(elemento.getNivel());
        if (nivel.length()>1) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en NIVEL");
        }
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public Object actualizar(PlanCuenta elemento) throws IOException, Exception {
        elemento = formatearStrings(elemento);
        //Obtiene longitud de nivel, si es mayor a 1 retorna error
        String nivel = String.valueOf(elemento.getNivel());
        if (nivel.length()>1) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en NIVEL");
        }
        PlanCuenta planCuenta = elementoDAO.saveAndFlush(elemento);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(planCuenta);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        PlanCuenta elemento = elementoDAO.findById(id).get();
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private PlanCuenta formatearStrings(PlanCuenta elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }
    
}