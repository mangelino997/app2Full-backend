package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajePropioCombustibleDAO;
import ar.com.draimo.jitws.dao.IViajePropioDAO;
import ar.com.draimo.jitws.dao.IViajePropioEfectivoDAO;
import ar.com.draimo.jitws.dao.IViajePropioGastoDAO;
import ar.com.draimo.jitws.dao.IViajePropioInsumoDAO;
import ar.com.draimo.jitws.dao.IViajePropioPeajeDAO;
import ar.com.draimo.jitws.dao.IViajePropioTramoDAO;
import ar.com.draimo.jitws.model.ViajePropio;
import ar.com.draimo.jitws.model.ViajePropioCombustible;
import ar.com.draimo.jitws.model.ViajePropioEfectivo;
import ar.com.draimo.jitws.model.ViajePropioGasto;
import ar.com.draimo.jitws.model.ViajePropioInsumo;
import ar.com.draimo.jitws.model.ViajePropioPeaje;
import ar.com.draimo.jitws.model.ViajePropioTramo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ViajePropio
 * @author blas
 */

@Service
public class ViajePropioService {

    //Define la referencia al dao
    @Autowired
    IViajePropioDAO elementoDAO;
    
    //Define la referencia al dao viaje propio tramo
    @Autowired
    IViajePropioTramoDAO viajePropioTramoDAO;
    
    //Define la referencia al dao viaje propio combustible
    @Autowired
    IViajePropioCombustibleDAO viajePropioCombustibleDAO;
    
    //Define la referencia al dao viaje propio efectivo
    @Autowired
    IViajePropioEfectivoDAO viajePropioEfectivoDAO;
    
    //Define la referencia al dao viaje propio insumo
    @Autowired
    IViajePropioInsumoDAO viajePropioInsumoDAO;
    
    //Define la referencia al dao viaje propio gasto
    @Autowired
    IViajePropioGastoDAO viajePropioGastoDAO;
    
    //Define la referencia al dao viaje propio peaje
    @Autowired
    IViajePropioPeajeDAO viajePropioPeajeDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajePropio elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ViajePropio> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene por id
    public ViajePropio obtener(int id) {
        //Obtiene un viaje propio por id
        Optional<ViajePropio> viajePropioOptional = elementoDAO.findById(id);
        ViajePropio viajePropio = viajePropioOptional.get();
        //Obtiene la lista de tramos del viaje
        List<ViajePropioTramo> viajePropioTramos = viajePropioTramoDAO.findByViajePropio(viajePropioOptional);
        viajePropio.setViajePropioTramos(viajePropioTramos);
        //Obtiene la lista de ordenes de combustible del viaje
        List<ViajePropioCombustible> viajePropioCombustibles = viajePropioCombustibleDAO.findByViajePropio(viajePropioOptional);
        viajePropio.setViajePropioCombustibles(viajePropioCombustibles);
        //Obtiene la lista de adelantos de efectivo del viaje
        List<ViajePropioEfectivo> viajePropioEfectivos = viajePropioEfectivoDAO.findByViajePropio(viajePropioOptional);
        viajePropio.setViajePropioEfectivos(viajePropioEfectivos);
        //Obtiene la lista de ordenes de insumo del viaje
        List<ViajePropioInsumo> viajePropioInsumos = viajePropioInsumoDAO.findByViajePropio(viajePropioOptional);
        viajePropio.setViajePropioInsumos(viajePropioInsumos);
        //Obtiene la lista de gastos del viaje
        List<ViajePropioGasto> viajePropioGasto = viajePropioGastoDAO.findByViajePropio(viajePropioOptional);
        viajePropio.setViajePropioGastos(viajePropioGasto);
        //Obtiene la lista de peajes del viaje
        List<ViajePropioPeaje> viajePropioPeaje = viajePropioPeajeDAO.findByViajePropio(viajePropioOptional);
        viajePropio.setViajePropioPeajes(viajePropioPeaje);
        //Retorna los datos
        return viajePropio;
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajePropio agregar(ViajePropio elemento) {
        elemento = formatearStrings(elemento);
        //Agrega el viaje propio
        ViajePropio viajePropio = elementoDAO.saveAndFlush(elemento);
        //Verifica que la lista de tramos tenga elementos
        if (elemento.getViajePropioTramos() != null) {
            //Agrega los tramos del viaje
            elemento.getViajePropioTramos().forEach((item) -> {
                item.setViajePropio(viajePropio);
                viajePropioTramoDAO.saveAndFlush(item);
            });
        }
        //Verifica que la lista de combustibles tenga elementos
        if (elemento.getViajePropioCombustibles() != null) {
            //Agrega las ordenes de combustible del viaje
            elemento.getViajePropioCombustibles().forEach((item) -> {
                viajePropioCombustibleDAO.saveAndFlush(item);
            });
        }
        //Verifica que la lista de efectivos tenga elementos
        if (elemento.getViajePropioEfectivos() != null) {
            //Agrega los adelantos de efectivo del viaje
            elemento.getViajePropioEfectivos().forEach((item) -> {
                viajePropioEfectivoDAO.saveAndFlush(item);
            });
        }
        //Verifica que la lista de insumos tenga elementos
        if (elemento.getViajePropioInsumos() != null) {
            //Agrega las ordenes de insumo del viaje
            elemento.getViajePropioInsumos().forEach((item) -> {
                viajePropioInsumoDAO.saveAndFlush(item);
            });
        }
        //Verifica que la lista de gatos tenga elementos
        if (elemento.getViajePropioGastos() != null) {
            //Agrega los gastos del viaje
            elemento.getViajePropioGastos().forEach((item) -> {
                viajePropioGastoDAO.saveAndFlush(item);
            });
        }
        //Verifica que la lista de peajes tenga elementos
        if (elemento.getViajePropioPeajes() != null) {
            //Agrega los peajes del viaje
            elemento.getViajePropioPeajes().forEach((item) -> {
                viajePropioPeajeDAO.saveAndFlush(item);
            });
        }
        return viajePropio;
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajePropio elemento) {
        elemento = formatearStrings(elemento);
        //Actualiza el viaje propio
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ViajePropio elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private ViajePropio formatearStrings(ViajePropio elemento) {
        if(elemento.getObservacionVehiculo() != null) {
            elemento.setObservacionVehiculo(elemento.getObservacionVehiculo().trim());
        }
        if(elemento.getObservacionVehiculoRemolque() != null) {
            elemento.setObservacionVehiculoRemolque(elemento.getObservacionVehiculoRemolque().trim());
        }
        if(elemento.getObservacionChofer() != null) {
            elemento.setObservacionChofer(elemento.getObservacionChofer().trim());
        }
        if(elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        return elemento;
    }
    
}
