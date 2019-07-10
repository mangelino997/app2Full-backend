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
        //Agrega el viaje propio
        Viaje viajePropio = elementoDAO.saveAndFlush(elemento);
        //Actualiza el viaje propio
        elementoDAO.save(viajePropio);
        //Verifica que la lista de tramos tenga elementos
        if (elemento.getViajeTramos() != null) {
            //Agrega los tramos del viaje
            elemento.getViajeTramos().forEach((item) -> {
                item.setViaje(viajePropio);
                ViajeTramo viajePropioTramo = viajeTramoDAO.saveAndFlush(item);
                //Agrega los dadores-destinatarios
                item.getViajeTramoClientes().forEach((elem) -> {
                    elem.setViajeTramo(viajePropioTramo);
                    viajeTramoClienteDAO.saveAndFlush(elem);
                });
            });
        }
        //Verifica que la lista de combustibles tenga elementos
        if (elemento.getViajeCombustibles() != null) {
            //Agrega las ordenes de combustible del viaje
            elemento.getViajeCombustibles().forEach((item) -> {
                item.setViaje(viajePropio);
                viajeCombustibleDAO.saveAndFlush(item);
            });
        }
        //Verifica que la lista de efectivos tenga elementos
        if (elemento.getViajeEfectivos() != null) {
            //Agrega los adelantos de efectivo del viaje
            elemento.getViajeEfectivos().forEach((item) -> {
                item.setViaje(viajePropio);
                viajeEfectivoDAO.saveAndFlush(item);
            });
        }
        //Verifica que la lista de insumos tenga elementos
        if (elemento.getViajeInsumos() != null) {
            //Agrega las ordenes de insumo del viaje
            elemento.getViajeInsumos().forEach((item) -> {
                item.setViaje(viajePropio);
                viajeInsumoDAO.saveAndFlush(item);
            });
        }
        //Verifica que la lista de gatos tenga elementos
        if (elemento.getViajeGastos() != null) {
            //Agrega los gastos del viaje
            elemento.getViajeGastos().forEach((item) -> {
                item.setViaje(viajePropio);
                viajeGastoDAO.saveAndFlush(item);
            });
        }
        //Verifica que la lista de peajes tenga elementos
        if (elemento.getViajePeajes() != null) {
            //Agrega los peajes del viaje
            elemento.getViajePeajes().forEach((item) -> {
                item.setViaje(viajePropio);
                viajePeajeDAO.saveAndFlush(item);
            });
        }
        return viajePropio;
    }

    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public void establecerAlias(Viaje viaje) {
        viaje.setAlias(viaje.getId() + " - " + viaje.getFecha()
                + " - " + viaje.getEmpresaEmision().getRazonSocial()
                + " - " + viaje.getPersonal().getNombreCompleto());
        elementoDAO.save(viaje);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Viaje viajePropio) {
        //Formatea los strings
        viajePropio = formatearStrings(viajePropio);
        //Verifica que la lista de tramos tenga elementos
        if (viajePropio.getViajeTramos() != null) {
            //Agrega los tramos del viaje
            for (ViajeTramo item : viajePropio.getViajeTramos()) {
                item.setViaje(viajePropio);
                ViajeTramo viajePropioTramo = viajeTramoDAO.save(item);
                //Agrega los dadores-destinatarios
                for (ViajeTramoCliente elem : item.getViajeTramoClientes()) {
                    elem.setViajeTramo(viajePropioTramo);
                    viajeTramoClienteDAO.saveAndFlush(elem);
                }
            }
        }
        //Verifica que la lista de combustibles tenga elementos
        if (viajePropio.getViajeCombustibles() != null) {
            //Agrega los combustibles del viaje
            for (ViajeCombustible item : viajePropio.getViajeCombustibles()) {
                if (item.getId() >= 0) {
                    item.setViaje(viajePropio);
                    viajeCombustibleDAO.save(item);
                } else {
                    item.setId(item.getId() * (-1));
                    viajeCombustibleDAO.delete(item);
                }
            }
        }
        //Verifica que la lista de efectivos tenga elementos
        if (viajePropio.getViajeEfectivos() != null) {
            //Agrega los efectivos del viaje
            for (ViajeEfectivo item : viajePropio.getViajeEfectivos()) {
                if (item.getId() >= 0) {
                    item.setViaje(viajePropio);
                    viajeEfectivoDAO.save(item);
                } else {
                    item.setId(item.getId() * (-1));
                    viajeEfectivoDAO.delete(item);
                }
            }
        }
        //Verifica que la lista de insumos tenga elementos
        if (viajePropio.getViajeInsumos() != null) {
            //Agrega los insumos del viaje
            for (ViajeInsumo item : viajePropio.getViajeInsumos()) {
                if (item.getId() >= 0) {
                    item.setViaje(viajePropio);
                    viajeInsumoDAO.save(item);
                } else {
                    item.setId(item.getId() * (-1));
                    viajeInsumoDAO.delete(item);
                }
            }
        }
        //Verifica que la lista de gastos tenga elementos
        if (viajePropio.getViajeGastos() != null) {
            //Agrega los gastos del viaje
            for (ViajeGasto item : viajePropio.getViajeGastos()) {
                if (item.getId() >= 0) {
                    item.setViaje(viajePropio);
                    viajeGastoDAO.save(item);
                } else {
                    item.setId(item.getId() * (-1));
                    viajeGastoDAO.delete(item);
                }
            }
        }
        //Verifica que la lista de peajes tenga elementos
        if (viajePropio.getViajePeajes() != null) {
            //Agrega los tramos del viaje
            for (ViajePeaje item : viajePropio.getViajePeajes()) {
                if (item.getId() >= 0) {
                    item.setViaje(viajePropio);
                    viajePeajeDAO.save(item);
                } else {
                    item.setId(item.getId() * (-1));
                    viajePeajeDAO.delete(item);
                }
            }
        }
        viajePropio.setAlias(viajePropio.getId() + " - " + viajePropio.getFecha()
                + " - " + viajePropio.getEmpresaEmision().getRazonSocial()
                + " - " + viajePropio.getPersonal().getNombreCompleto());
        //Actualiza el viaje propio
        elementoDAO.save(viajePropio);
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