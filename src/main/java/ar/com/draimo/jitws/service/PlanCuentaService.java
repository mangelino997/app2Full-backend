//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IGrupoCuentaContableDAO;
import ar.com.draimo.jitws.model.PlanCuenta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IPlanCuentaDAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.ProveedorCuentaContable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Servicio PlandeCuenta
 *
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

    //Define la referencia al dao grupocuentacontable
    @Autowired
    IGrupoCuentaContableDAO grupoCuentaContableDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        PlanCuenta elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public Object listar() throws JsonProcessingException, IOException {
        List<PlanCuenta> planesCuenta = elementoDAO.findAll();
        return aplicarFiltros(planesCuenta, null);
    }

    //Obtiene una lista por nombre
    public Object listarPorNombre(String nombre) throws IOException {
        List<PlanCuenta> planesCuenta = nombre.equals("*")
                ? elementoDAO.findAll() : elementoDAO.findByNombreContaining(nombre);
        return aplicarFiltros(planesCuenta, null);
    }

    //Obtiene un listado por grupocuentacontable y estaactivo
    public Object listarGrupoActivo(int idEmpresa) throws IOException {
        List<PlanCuenta> planesCuenta = elementoDAO.listarGrupoActivo(idEmpresa);
        return aplicarFiltros(planesCuenta, null);
    }

    //Obtiene el plan de cuenta por grupo Estado de Resultados por empresa
    public Object listarGrupoEstadoResultados(int idEmpresa) throws IOException {
        List<PlanCuenta> planesCuenta = elementoDAO.listarGrupoEstadoResultados(idEmpresa);
        return aplicarFiltros(planesCuenta, null);
    }

    //Obtiene el plan de cuenta
    public Object obtenerPlanCuenta(int idEmpresa)
            throws JsonProcessingException, IOException {
        Empresa empresa = empresaDAO.findById(idEmpresa).get();
        PlanCuenta planCuenta = elementoDAO.findByEmpresaAndNivel(empresa, (short) 1);
        PlanCuenta pc = crearPlanCuenta(planCuenta);
        return aplicarFiltros(null, pc);
    }

    //Crea el plan de cuenta
    public PlanCuenta crearPlanCuenta(PlanCuenta planCuenta) {
        List<PlanCuenta> hijos = elementoDAO.findByPadre(planCuenta);
        if (!hijos.isEmpty()) {
            planCuenta.setHijos(hijos);
            for (PlanCuenta pc : planCuenta.getHijos()) {
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
        String string = mapper.writer(filters).writeValueAsString(planesCuenta);
        return new ObjectMapper().readValue(string, Object.class);
    }

    //Obtiene por empresa y grupo cuenta contable
    public Object obtenerPorEmpresaYGrupoCuentaContable(int idEmpresa, int idGrupoCuentaContable) throws IOException {
        PlanCuenta planCuenta = elementoDAO.findByEmpresaAndGrupoCuentaContableAndNivel(empresaDAO.findById(idEmpresa).get(),
                grupoCuentaContableDAO.findById(idGrupoCuentaContable).get(), (short) 2);
        return aplicarFiltros(null, planCuenta);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public PlanCuenta agregar(PlanCuenta elemento) throws Exception {
        elemento = formatearStrings(elemento);
        controlarLongitud(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public Object actualizar(PlanCuenta elemento) throws IOException, Exception {
        elemento = formatearStrings(elemento);
        controlarLongitud(elemento);
        PlanCuenta planCuenta = elementoDAO.save(elemento);
        return aplicarFiltros(null, planCuenta);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        PlanCuenta elemento = elementoDAO.findById(id).get();
        elementoDAO.delete(elemento);
    }
    
    //Controla la longitud de los atributos de tipo short
    private void controlarLongitud(PlanCuenta elemento) {
        //Obtiene longitud de nivel, si es mayor a 1 retorna error
        String nivel = String.valueOf(elemento.getNivel());
        if (nivel.length() > 1) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " NIVEL");
        }
    }

    //Formatea los strings
    private PlanCuenta formatearStrings(PlanCuenta elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

    //Retorna un object aplicando los filtros
    private Object aplicarFiltros(List<PlanCuenta> elementos, PlanCuenta elemento) throws IOException {
       ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("padre");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPlanCuenta", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos!= null ? elementos : elemento);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
}
