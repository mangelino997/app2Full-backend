package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.PlanCuenta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IPlanCuentaDAO;
import ar.com.draimo.jitws.dto.PlanCuentaDTO;
import java.util.ArrayList;

/**
 * Servicio PlandeCuenta
 * @author blas
 */

@Service
public class PlanCuentaService {
    
    //Define la referencia al dao
    @Autowired
    IPlanCuentaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        PlanCuenta elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<PlanCuenta> listar() {
        return elementoDAO.findAll();
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
    
    //Crear el arbol de plan de cuenta
    public PlanCuentaDTO crearArbol() {
        //Define un plan de cuenta dto
        PlanCuentaDTO planCuentaDTO = new PlanCuentaDTO();
        //Obtiene la lista de plan de cuenta
        List<PlanCuenta> planCuenta = elementoDAO.findAll();
        //Define la lista de hijos
        List<PlanCuenta> hijos;
        //Define una lista de planes de cuenta dto
        List<PlanCuentaDTO> planCuentaDTOs;
        //Define una plan cuenta dto
        PlanCuentaDTO plDTO;
        //Recorre la lista para crear el arbol
        for(PlanCuenta pl: planCuenta) {
            if(pl.getPadre() == null) {
                hijos = elementoDAO.findByPadre(pl);
                planCuentaDTO.setNombre(pl.getNombre());
                planCuentaDTOs = new ArrayList<>();
                for(PlanCuenta hijo : hijos) {
                    plDTO = new PlanCuentaDTO();
                    plDTO.setNombre(hijo.getNombre());
                    plDTO.setHijos(new ArrayList<>());
                    planCuentaDTOs.add(plDTO);
                }
                planCuentaDTO.setHijos(planCuentaDTOs);
            }
        }
        return planCuentaDTO;
    }
    
    public PlanCuenta crearPlanCuenta(PlanCuenta planCuenta) {
        List<PlanCuenta> hijos = elementoDAO.findByPadre(planCuenta);
        if(!hijos.isEmpty()) {
            planCuenta.setHijos(hijos);
            for(PlanCuenta pc : planCuenta.getHijos()) {
                crearPlanCuenta(pc);
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
    public void actualizar(PlanCuenta elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(PlanCuenta elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private PlanCuenta formatearStrings(PlanCuenta elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }
    
}
