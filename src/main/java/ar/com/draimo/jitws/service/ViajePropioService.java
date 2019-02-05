package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajePropioCombustibleDAO;
import ar.com.draimo.jitws.dao.IViajePropioDAO;
import ar.com.draimo.jitws.dao.IViajePropioEfectivoDAO;
import ar.com.draimo.jitws.dao.IViajePropioGastoDAO;
import ar.com.draimo.jitws.dao.IViajePropioInsumoDAO;
import ar.com.draimo.jitws.dao.IViajePropioPeajeDAO;
import ar.com.draimo.jitws.dao.IViajePropioTramoClienteDAO;
import ar.com.draimo.jitws.dao.IViajePropioTramoDAO;
import ar.com.draimo.jitws.model.ViajePropio;
import ar.com.draimo.jitws.model.ViajePropioCombustible;
import ar.com.draimo.jitws.model.ViajePropioEfectivo;
import ar.com.draimo.jitws.model.ViajePropioGasto;
import ar.com.draimo.jitws.model.ViajePropioInsumo;
import ar.com.draimo.jitws.model.ViajePropioPeaje;
import ar.com.draimo.jitws.model.ViajePropioTramo;
import ar.com.draimo.jitws.model.ViajePropioTramoCliente;
import java.util.List;
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
    
    //Define la referencia al dao viaje propio tramo cliente
    @Autowired
    IViajePropioTramoClienteDAO viajePropioTramoClienteDAO;
    
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
        return elementoDAO.obtenerTodos();
    }
    
    //Obtiene por id
    public ViajePropio obtenerPorId(int id) {
        //Obtiene un viaje propio por id
        ViajePropio viajePropio = elementoDAO.obtenerPorId(id);
        //Obtiene la lista de tramos del viaje
        List<ViajePropioTramo> viajePropioTramos = viajePropioTramoDAO.findByViajePropio(viajePropio);
        viajePropio.setViajePropioTramos(viajePropioTramos);
        //Obtiene la lista de ordenes de combustible del viaje
        List<ViajePropioCombustible> viajePropioCombustibles = viajePropioCombustibleDAO.findByViajePropio(viajePropio);
        viajePropio.setViajePropioCombustibles(viajePropioCombustibles);
        //Obtiene la lista de adelantos de efectivo del viaje
        List<ViajePropioEfectivo> viajePropioEfectivos = viajePropioEfectivoDAO.findByViajePropio(viajePropio);
        viajePropio.setViajePropioEfectivos(viajePropioEfectivos);
        //Obtiene la lista de ordenes de insumo del viaje
        List<ViajePropioInsumo> viajePropioInsumos = viajePropioInsumoDAO.findByViajePropio(viajePropio);
        viajePropio.setViajePropioInsumos(viajePropioInsumos);
        //Obtiene la lista de gastos del viaje
        List<ViajePropioGasto> viajePropioGasto = viajePropioGastoDAO.findByViajePropio(viajePropio);
        viajePropio.setViajePropioGastos(viajePropioGasto);
        //Obtiene la lista de peajes del viaje
        List<ViajePropioPeaje> viajePropioPeaje = viajePropioPeajeDAO.findByViajePropio(viajePropio);
        viajePropio.setViajePropioPeajes(viajePropioPeaje);
        //Retorna los datos
        return viajePropio;
    }
    
    //Obtiene una lista de registros por alias
    public List<ViajePropio> listarPorAlias(String alias) {
        return elementoDAO.findByAliasContaining(alias);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajePropio agregar(ViajePropio elemento) {
        elemento = formatearStrings(elemento);
        //Agrega el viaje propio
        ViajePropio viajePropio = elementoDAO.saveAndFlush(elemento);
        //Establece el alias
        viajePropio.setAlias(viajePropio.getId() + " - " + viajePropio.getFecha() 
                + " - " + viajePropio.getEmpresa().getRazonSocial() 
                + " - " + viajePropio.getPersonal().getNombreCompleto());
        //Actualiza el viaje propio
        elementoDAO.save(viajePropio);
        //Verifica que la lista de tramos tenga elementos
        if (elemento.getViajePropioTramos() != null) {
            //Agrega los tramos del viaje
            elemento.getViajePropioTramos().forEach((item) -> {
                item.setViajePropio(viajePropio);
                ViajePropioTramo viajePropioTramo = viajePropioTramoDAO.saveAndFlush(item);
                //Agrega los dadores-destinatarios
                item.getViajePropioTramoClientes().forEach((elem) -> {
                    elem.setViajePropioTramo(viajePropioTramo);
                    viajePropioTramoClienteDAO.saveAndFlush(elem);
                });
            });
        }
        //Verifica que la lista de combustibles tenga elementos
        if (elemento.getViajePropioCombustibles() != null) {
            //Agrega las ordenes de combustible del viaje
            elemento.getViajePropioCombustibles().forEach((item) -> {
                item.setViajePropio(viajePropio);
                viajePropioCombustibleDAO.saveAndFlush(item);
            });
        }
        //Verifica que la lista de efectivos tenga elementos
        if (elemento.getViajePropioEfectivos() != null) {
            //Agrega los adelantos de efectivo del viaje
            elemento.getViajePropioEfectivos().forEach((item) -> {
                item.setViajePropio(viajePropio);
                viajePropioEfectivoDAO.saveAndFlush(item);
            });
        }
        //Verifica que la lista de insumos tenga elementos
        if (elemento.getViajePropioInsumos() != null) {
            //Agrega las ordenes de insumo del viaje
            elemento.getViajePropioInsumos().forEach((item) -> {
                item.setViajePropio(viajePropio);
                viajePropioInsumoDAO.saveAndFlush(item);
            });
        }
        //Verifica que la lista de gatos tenga elementos
        if (elemento.getViajePropioGastos() != null) {
            //Agrega los gastos del viaje
            elemento.getViajePropioGastos().forEach((item) -> {
                item.setViajePropio(viajePropio);
                viajePropioGastoDAO.saveAndFlush(item);
            });
        }
        //Verifica que la lista de peajes tenga elementos
        if (elemento.getViajePropioPeajes() != null) {
            //Agrega los peajes del viaje
            elemento.getViajePropioPeajes().forEach((item) -> {
                item.setViajePropio(viajePropio);
                viajePropioPeajeDAO.saveAndFlush(item);
            });
        }
        return viajePropio;
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajePropio elemento) {
        //Formatea los strings
        elemento = formatearStrings(elemento);
        //Verifica que la lista de tramos tenga elementos
        
        /* VER COMO TRATAR EL TEMA DE MODIFICAR Y ELIMINAR ITEMS */
        
//        if (elemento.getViajePropioTramos() != null) {
//            //Agrega los tramos del viaje
//            for(ViajePropioTramo item : elemento.getViajePropioTramos()) {
//                item.setViajePropio(elemento);
//                ViajePropioTramo viajePropioTramo = viajePropioTramoDAO.save(item);
//                //Agrega los dadores-destinatarios
//                for(ViajePropioTramoCliente elem : item.getViajePropioTramoClientes()) {
//                    elem.setViajePropioTramo(viajePropioTramo);
//                    viajePropioTramoClienteDAO.saveAndFlush(elem);
//                }
//            }
//        }
//        //Verifica que la lista de combustibles tenga elementos
//        if (elemento.getViajePropioCombustibles() != null) {
//            //Agrega los combustibles del viaje
//            for(ViajePropioCombustible item : elemento.getViajePropioCombustibles()) {
//                if(item.getId() != 0) {
//                    item.setViajePropio(elemento);
//                    viajePropioCombustibleDAO.save(item);
//                } else {
//                    viajePropioCombustibleDAO.delete(item);
//                }
//            }
//        }
//        //Verifica que la lista de efectivos tenga elementos
//        if (elemento.getViajePropioEfectivos() != null) {
//            //Agrega los efectivos del viaje
//            for(ViajePropioEfectivo item : elemento.getViajePropioEfectivos()) {
//                item.setViajePropio(elemento);
//                viajePropioEfectivoDAO.save(item);
//            }
//        }
//        //Verifica que la lista de insumos tenga elementos
//        if (elemento.getViajePropioInsumos() != null) {
//            //Agrega los insumos del viaje
//            for(ViajePropioInsumo item : elemento.getViajePropioInsumos()) {
//                item.setViajePropio(elemento);
//                viajePropioInsumoDAO.save(item);
//            }
//        }
//        //Verifica que la lista de gastos tenga elementos
//        if (elemento.getViajePropioGastos() != null) {
//            //Agrega los gastos del viaje
//            for(ViajePropioGasto item : elemento.getViajePropioGastos()) {
//                item.setViajePropio(elemento);
//                viajePropioGastoDAO.save(item);
//            }
//        }
//        //Verifica que la lista de peajes tenga elementos
//        if (elemento.getViajePropioPeajes() != null) {
//            //Agrega los tramos del viaje
//            for(ViajePropioPeaje item : elemento.getViajePropioPeajes()) {
//                item.setViajePropio(elemento);
//                viajePropioPeajeDAO.save(item);
//            }
//        }
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
