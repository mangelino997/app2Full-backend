package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ISucursalDAO;
import ar.com.draimo.jitws.dao.IViajePropioTramoDAO;
import ar.com.draimo.jitws.dao.IViajeRemitoDAO;
import ar.com.draimo.jitws.dao.IViajeTerceroTramoDAO;
import ar.com.draimo.jitws.model.Sucursal;
import ar.com.draimo.jitws.model.ViajePropioTramo;
import ar.com.draimo.jitws.model.ViajeRemito;
import java.util.ArrayList;
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
public class ViajeRemitoService {

    //Define la referencia al dao
    @Autowired
    IViajeRemitoDAO elementoDAO;
    
    //Define la referencia al dao sucursal
    @Autowired
    ISucursalDAO sucursalDAO;
    
    //Define la referencia al dao viaje propio tramo
    @Autowired
    IViajePropioTramoDAO viajePropioTramoDAO;
    
    //Define la referencia al dao viaje tercero tramo
    @Autowired
    IViajeTerceroTramoDAO viajeTerceroTramoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeRemito elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene el listado completo
    public List<ViajeRemito> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por alias
    public List<ViajeRemito> listarPorAlias(String alias) {
        return elementoDAO.findByAliasContaining(alias);
    }
    
    //Obtiene un listado por numero de comprobante
    public List<ViajeRemito> listarPorNumero(int numero) {
        return elementoDAO.findByNumero(numero);
    }
    
    //Obtiene un listado de pendientes por sucursal
    public List<ViajeRemito> listarPendientesPorSucursal(int idSucursal) {
        //Obtiene la sucursal por id
        Optional<Sucursal> sucursal = sucursalDAO.findById(idSucursal);
        return elementoDAO.findBySucursalEmisionAndEstaPendienteFalse(sucursal);
    }
    
    //Obtiene un listado de pendientes por filtro
    public List<ViajeRemito> listarPendientesPorFiltro(int idSucursal, int idSucursalDestino, short numeroCamion) {
        //Obtiene la sucursal por id
        Optional<Sucursal> sucursal = sucursalDAO.findById(idSucursal);
        //Obtiene la sucursal destino por id
        Optional<Sucursal> sucursalDestino = sucursalDAO.findById(idSucursalDestino);
        //Retorna los datos
        return elementoDAO.findBySucursalEmisionAndSucursalDestinoAndNumeroCamionAndEstaPendienteTrue(sucursal, sucursalDestino, numeroCamion);
    }
    
    //Obtiene un listado de asignados por filtro
    public List<ViajeRemito> listarAsignadosPorFiltro(int idSucursal, int idSucursalDestino, short numeroCamion, int idViajePropioTramo) {
        //Obtiene la sucursal por id
        Optional<Sucursal> sucursal = sucursalDAO.findById(idSucursal);
        //Obtiene la sucursal destino por id
        Optional<Sucursal> sucursalDestino = sucursalDAO.findById(idSucursalDestino);
        //Obtiene el viaje propio tramo por id
        Optional<ViajePropioTramo> viajePropioTramo = viajePropioTramoDAO.findById(idViajePropioTramo);
        //Retorna los datos
        return elementoDAO.findBySucursalEmisionAndSucursalDestinoAndNumeroCamionAndViajePropioTramoAndEstaPendienteFalse(
                sucursal, sucursalDestino, numeroCamion, viajePropioTramo);
    }
    
    //Obtiene un listado de remitos por viajePropio
    public List<ViajeRemito> listarRemitos(int idViajeTramo, int item) {
        List<ViajeRemito> viajeRemitos = new ArrayList<>();
        if (item==1) { //ViajePropioTramo
            viajeRemitos = elementoDAO.findByViajePropioTramoAndEstaFacturadoFalse
        (viajePropioTramoDAO.findById(idViajeTramo));
        } else if (item ==2){ //ViajeTerceroTramo
            viajeRemitos = elementoDAO.findByViajeTerceroTramoAndEstaFacturadoFalse
        (viajeTerceroTramoDAO.findById(idViajeTramo));
        }
        return viajeRemitos;
    }
    
    //Asigna los remitos
    @Transactional(rollbackFor = Exception.class)
    public void asignar(ViajePropioTramo elemento) {
        //Recorre la lista de remitos
        for(ViajeRemito viajeRemito : elemento.getViajeRemitos()) {
            viajeRemito.setEstaPendiente(false);
            viajeRemito.setViajePropioTramo(elemento);
            elementoDAO.save(viajeRemito);
        }
    }
    
    //Quita los remitos
    @Transactional(rollbackFor = Exception.class)
    public void quitar(ViajePropioTramo elemento) {
        //Recorre la lista de remitos
        for(ViajeRemito viajeRemito : elemento.getViajeRemitos()) {
            viajeRemito.setEstaPendiente(true);
            viajeRemito.setViajePropioTramo(null);
            elementoDAO.save(viajeRemito);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeRemito agregar(ViajeRemito elemento) {
        //Establece valores por defecto
        elemento.setEstaPendiente(true);
        elemento.setEstaFacturado(false);
        elemento.setEstaEnReparto(false);
        //Formatea los strings
        elemento = formatearStrings(elemento);
        elemento.setAlias(elemento.getNumero() + " - (R: " + elemento.getClienteRemitente().getAlias() + ") - " 
                + "(D: " + elemento.getClienteDestinatario().getAlias() + ")");
        return elementoDAO.saveAndFlush(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeRemito elemento) {
        elemento = formatearStrings(elemento);
        elemento.setAlias(elemento.getNumero() + " - (R: " + elemento.getClienteRemitente().getAlias() + ") - " 
                + "(D: " + elemento.getClienteDestinatario().getAlias() + ")");
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ViajeRemito elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private ViajeRemito formatearStrings(ViajeRemito elemento) {
        elemento.setLetra(elemento.getLetra().trim());
        if(elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        return elemento;
    }
    
}
