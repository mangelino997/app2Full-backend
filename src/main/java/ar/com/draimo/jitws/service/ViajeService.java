package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.Viaje;
import ar.com.draimo.jitws.model.ViajeCombustible;
import ar.com.draimo.jitws.model.ViajeEfectivo;
import ar.com.draimo.jitws.model.ViajeGasto;
import ar.com.draimo.jitws.model.ViajeInsumo;
import ar.com.draimo.jitws.model.ViajePeaje;
import ar.com.draimo.jitws.model.ViajeTramo;
import ar.com.draimo.jitws.model.ViajeTramoCliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IViajeCombustibleDAO;
import ar.com.draimo.jitws.dao.IViajeDAO;
import ar.com.draimo.jitws.dao.IViajeEfectivoDAO;
import ar.com.draimo.jitws.dao.IViajeGastoDAO;
import ar.com.draimo.jitws.dao.IViajeInsumoDAO;
import ar.com.draimo.jitws.dao.IViajePeajeDAO;
import ar.com.draimo.jitws.dao.IViajeTramoClienteDAO;
import ar.com.draimo.jitws.dao.IViajeTramoDAO;

/**
 * Servicio Viaje
 *
 * @author blas
 */
@Service
public class ViajeService {

    //Define la referencia al dao
    @Autowired
    IViajeDAO elementoDAO;

    //Define la referencia al dao viaje tramo
    @Autowired
    IViajeTramoDAO viajeTramoDAO;

    //Define la referencia al dao viaje tramo cliente
    @Autowired
    IViajeTramoClienteDAO viajeTramoClienteDAO;

    //Define la referencia al dao viaje combustible
    @Autowired
    IViajeCombustibleDAO viajeCombustibleDAO;

    //Define la referencia al dao viaje efectivo
    @Autowired
    IViajeEfectivoDAO viajeEfectivoDAO;

    //Define la referencia al dao viaje insumo
    @Autowired
    IViajeInsumoDAO viajeInsumoDAO;

    //Define la referencia al dao viaje gasto
    @Autowired
    IViajeGastoDAO viajeGastoDAO;

    //Define la referencia al dao viaje peaje
    @Autowired
    IViajePeajeDAO viajePeajeDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Viaje elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<Viaje> listar() {
        return elementoDAO.obtenerTodos();
    }

    //Obtiene por id
    public Viaje obtenerPorId(int id) {
        //Obtiene un viaje propio por id
        Viaje viaje = elementoDAO.obtenerPorId(id);
        //Obtiene la lista de tramos del viaje
        List<ViajeTramo> viajePropioTramos = viajeTramoDAO.findByViaje(viaje);
        viaje.setViajeTramos(viajePropioTramos);
        //Obtiene la lista de ordenes de combustible del viaje
        List<ViajeCombustible> viajePropioCombustibles = viajeCombustibleDAO.findByViaje(viaje);
        viaje.setViajeCombustibles(viajePropioCombustibles);
        //Obtiene la lista de adelantos de efectivo del viaje
        List<ViajeEfectivo> viajePropioEfectivos = viajeEfectivoDAO.findByViaje(viaje);
        viaje.setViajeEfectivos(viajePropioEfectivos);
        //Obtiene la lista de ordenes de insumo del viaje
        List<ViajeInsumo> viajePropioInsumos = viajeInsumoDAO.findByViaje(viaje);
        viaje.setViajeInsumos(viajePropioInsumos);
        //Obtiene la lista de gastos del viaje
        List<ViajeGasto> viajePropioGasto = viajeGastoDAO.findByViaje(viaje);
        viaje.setViajeGastos(viajePropioGasto);
        //Obtiene la lista de peajes del viaje
        List<ViajePeaje> viajePropioPeaje = viajePeajeDAO.findByViaje(viaje);
        viaje.setViajePeajes(viajePropioPeaje);
        //Retorna los datos
        return viaje;
    }

    //Obtiene una lista de registros por alias
    public List<Viaje> listarPorAlias(String alias) {
        if(alias.equals("***")) {
            return elementoDAO.findAll();
        }else {
            return elementoDAO.findByAliasContaining(alias);
        }
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Viaje agregar(Viaje elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public Viaje establecerAlias(Viaje viaje) {
        viaje.setAlias(viaje.getId() + " - " + viaje.getFecha()
                + " - " + viaje.getEmpresaEmision().getRazonSocial()
                + " - " + viaje.getPersonal().getNombreCompleto());
        return elementoDAO.save(viaje);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public Viaje actualizar(Viaje viaje) {
        //Formatea los strings
        viaje = formatearStrings(viaje);
        //Establece el alias
        viaje.setAlias(viaje.getId() + " - " + viaje.getFecha()
                + " - " + viaje.getEmpresaEmision().getRazonSocial()
                + " - " + viaje.getPersonal().getNombreCompleto());
        //Actualiza el viaje
        return elementoDAO.save(viaje);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Viaje elemento) {
        elementoDAO.delete(elemento);
    }

    //Formatea los strings
    private Viaje formatearStrings(Viaje elemento) {
        if (elemento.getObservacionVehiculo() != null) {
            elemento.setObservacionVehiculo(elemento.getObservacionVehiculo().trim());
        }
        if (elemento.getObservacionVehiculoRemolque() != null) {
            elemento.setObservacionVehiculoRemolque(elemento.getObservacionVehiculoRemolque().trim());
        }
        if (elemento.getObservacionChofer() != null) {
            elemento.setObservacionChofer(elemento.getObservacionChofer().trim());
        }
        if (elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        return elemento;
    }

}