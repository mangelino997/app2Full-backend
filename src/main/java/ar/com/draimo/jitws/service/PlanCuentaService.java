package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEmpresaDAO;
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
    public List<PlanCuenta> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Obtiene un listado por grupocuentacontable y estaactivo
    public List<PlanCuenta> listarGrupoActivo(int idEmpresa) {
        return elementoDAO.listarGrupoActivo(idEmpresa);
    }
    
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
    
    public PlanCuenta crearPlanCuenta(PlanCuenta planCuenta) {
        List<PlanCuenta> hijos = elementoDAO.findByPadre(planCuenta);
        if(!hijos.isEmpty()) {
            planCuenta.setHijos(hijos);
            for(PlanCuenta pc : planCuenta.getHijos()) {
                pc = crearPlanCuenta(pc);
            }
        }
        return planCuenta;
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public PlanCuenta agregar(PlanCuenta elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public Object actualizar(PlanCuenta elemento) throws IOException {
        elemento = formatearStrings(elemento);
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