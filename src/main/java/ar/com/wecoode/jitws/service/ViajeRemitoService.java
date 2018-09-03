package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.ISucursalDAO;
import ar.com.wecoode.jitws.dao.IViajePropioTramoDAO;
import ar.com.wecoode.jitws.dao.IViajeRemitoDAO;
import ar.com.wecoode.jitws.model.Sucursal;
import ar.com.wecoode.jitws.model.ViajePropioTramo;
import ar.com.wecoode.jitws.model.ViajeRemito;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene el listado completo
    public List<ViajeRemito> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene un listado por numero de comprobante
    public List<ViajeRemito> listarPorNumeroComprobante(int numeroComprobante) {
        return elementoDAO.findByNumeroComprobanteContaining(numeroComprobante);
    }
    
    //Obtiene un listado de pendientes por sucursal
    public List<ViajeRemito> listarPendientesPorSucursal(int idSucursal) {
        //Obtiene la sucursal por id
        Optional<Sucursal> sucursal = sucursalDAO.findById(idSucursal);
        return elementoDAO.findBySucursalAndEstaPendienteTrue(sucursal);
    }
    
    //Obtiene un listado de pendientes por filtro
    public List<ViajeRemito> listarPendientesPorFiltro(int idSucursal, int idSucursalDestino, short numeroCamion) {
        //Obtiene la sucursal por id
        Optional<Sucursal> sucursal = sucursalDAO.findById(idSucursal);
        //Obtiene la sucursal destino por id
        Optional<Sucursal> sucursalDestino = sucursalDAO.findById(idSucursalDestino);
        //Retorna los datos
        return elementoDAO.findBySucursalAndSucursalDestinoAndNumeroCamionAndEstaPendienteTrue(sucursal, sucursalDestino, numeroCamion);
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
        return elementoDAO.findBySucursalAndSucursalDestinoAndNumeroCamionAndViajePropioTramoAndEstaPendienteFalse(
                sucursal, sucursalDestino, numeroCamion, viajePropioTramo);
    }
    
    //Asigna los remitos
    public void asignar(ViajePropioTramo elemento) {
        //Recorre la lista de remitos
        for(ViajeRemito viajeRemito : elemento.getViajeRemitos()) {
            viajeRemito.setEstaPendiente(false);
            viajeRemito.setViajePropioTramo(elemento);
            elementoDAO.save(viajeRemito);
        }
    }
    
    //Quita los remitos
    public void quitar(ViajePropioTramo elemento) {
        //Recorre la lista de remitos
        for(ViajeRemito viajeRemito : elemento.getViajeRemitos()) {
            viajeRemito.setEstaPendiente(true);
            viajeRemito.setViajePropioTramo(null);
            elementoDAO.save(viajeRemito);
        }
    }
    
    //Agrega un registro
    public void agregar(ViajeRemito elemento) {
        elementoDAO.saveAndFlush(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(ViajeRemito elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(ViajeRemito elemento) {
        elementoDAO.delete(elemento);
    }
    
}
