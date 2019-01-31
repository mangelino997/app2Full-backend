package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajeTerceroCombustibleDAO;
import ar.com.draimo.jitws.dao.IViajeTerceroDAO;
import ar.com.draimo.jitws.dao.IViajeTerceroEfectivoDAO;
import ar.com.draimo.jitws.dao.IViajeTerceroInsumoDAO;
import ar.com.draimo.jitws.dao.IViajeTerceroTramoDAO;
import ar.com.draimo.jitws.model.ViajeTercero;
import ar.com.draimo.jitws.model.ViajeTerceroCombustible;
import ar.com.draimo.jitws.model.ViajeTerceroEfectivo;
import ar.com.draimo.jitws.model.ViajeTerceroInsumo;
import ar.com.draimo.jitws.model.ViajeTerceroTramo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ViajeTercero
 * @author blas
 */

@Service
public class ViajeTerceroService {

    //Define la referencia al dao
    @Autowired
    IViajeTerceroDAO elementoDAO;
    
    //Define la referencia al dao viaje tercero tramo
    @Autowired
    IViajeTerceroTramoDAO viajeTerceroTramoDAO;
    
    //Define la referencia al dao viaje tercero combustible
    @Autowired
    IViajeTerceroCombustibleDAO viajeTerceroCombustibleDAO;
    
    //Define la referencia al dao viaje tercero efectivo
    @Autowired
    IViajeTerceroEfectivoDAO viajeTerceroEfectivoDAO;
    
    //Define la referencia al dao viaje tercero insumo
    @Autowired
    IViajeTerceroInsumoDAO viajeTerceroInsumoDAO;
    
    //Obtiene por id
    public ViajeTercero obtenerPorId(int id) {
        //Obtiene un viaje propio por id
        ViajeTercero viajeTercero = elementoDAO.obtenerPorId(id);
        //Obtiene la lista de tramos del viaje
        List<ViajeTerceroTramo> viajeTerceroTramos = viajeTerceroTramoDAO.findByViajeTercero(viajeTercero);
        viajeTercero.setViajeTerceroTramos(viajeTerceroTramos);
        //Obtiene la lista de ordenes de combustible del viaje
        List<ViajeTerceroCombustible> viajeTerceroCombustibles = viajeTerceroCombustibleDAO.findByViajeTercero(viajeTercero);
        viajeTercero.setViajeTerceroCombustibles(viajeTerceroCombustibles);
        //Obtiene la lista de adelantos de efectivo del viaje
        List<ViajeTerceroEfectivo> viajeTerceroEfectivos = viajeTerceroEfectivoDAO.findByViajeTercero(viajeTercero);
        viajeTercero.setViajeTerceroEfectivos(viajeTerceroEfectivos);
        //Obtiene la lista de ordenes de insumo del viaje
        List<ViajeTerceroInsumo> viajeTerceroInsumos = viajeTerceroInsumoDAO.findByViajeTercero(viajeTercero);
        viajeTercero.setViajeTerceroInsumos(viajeTerceroInsumos);
        //Retorna los datos
        return viajeTercero;
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeTercero agregar(ViajeTercero elemento) {
        elemento = formatearStrings(elemento);
        //Agrega el viaje tercero
        ViajeTercero viajeTercero =  elementoDAO.saveAndFlush(elemento);
        //Agrega los tramos del viaje
        elemento.getViajeTerceroTramos().forEach((item) -> {
            viajeTerceroTramoDAO.saveAndFlush(item);
        });
        //Agrega las ordenes de combustible del viaje
        elemento.getViajeTerceroCombustibles().forEach((item) -> {
            viajeTerceroCombustibleDAO.saveAndFlush(item);
        });
        //Agrega los adelantos de efectivo del viaje
        elemento.getViajeTerceroEfectivos().forEach((item) -> {
            viajeTerceroEfectivoDAO.saveAndFlush(item);
        });
        //Agrega las ordenes de insumo del viaje
        elemento.getViajeTerceroInsumos().forEach((item) -> {
            viajeTerceroInsumoDAO.saveAndFlush(item);
        });
        return viajeTercero;
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeTercero elemento) {
        elemento = formatearStrings(elemento);
        //Actualiza el viaje propio
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ViajeTercero elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private ViajeTercero formatearStrings(ViajeTercero elemento) {
        if(elemento.getNumeroLiquidacion() != null) {
            elemento.setNumeroLiquidacion(elemento.getNumeroLiquidacion().trim());
        }
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