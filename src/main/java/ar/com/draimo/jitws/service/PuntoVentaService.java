package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPuntoVentaDAO;
import ar.com.draimo.jitws.dao.ISucursalDAO;
import ar.com.draimo.jitws.model.PuntoVenta;
import ar.com.draimo.jitws.model.Sucursal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Punto de Venta
 * @author blas
 */

@Service
public class PuntoVentaService {

    //Define la referencia al dao
    @Autowired
    IPuntoVentaDAO elementoDAO;
    
    //Define la referencia al dao sucursal
    @Autowired
    ISucursalDAO sucursalDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        PuntoVenta elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<PuntoVenta> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por sucursal
    public List<PuntoVenta> listarPorSucursal(int idSucursal) {
        //Obtiene la sucursal por id
        Optional<Sucursal> sucursal = sucursalDAO.findById(idSucursal);
        //Retorna los datos
        return elementoDAO.findBySucursal(sucursal);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public PuntoVenta agregar(PuntoVenta elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(PuntoVenta elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(PuntoVenta elemento) {
        elementoDAO.delete(elemento);
    }

}
