package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IPuntoVentaDAO;
import ar.com.draimo.jitws.dao.ISucursalDAO;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.PuntoVenta;
import ar.com.draimo.jitws.model.Sucursal;
import java.util.ArrayList;
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
    
    //Define la referencia al dao empresa
    @Autowired
    IEmpresaDAO empresaDAO;
    
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
    
    //Obtiene una lista por sucursal y empresa
    public List<PuntoVenta> listarPorEmpresaYSucursal(int idEmpresa, int idSucursal) {
        //Obtiene la sucursal por id
        Sucursal sucursal = sucursalDAO.findById(idSucursal).get();
        //Obtiene la empresa por id
        Empresa empresa = empresaDAO.findById(idEmpresa).get();
        //Obtiene la lista de puntos de venta
        List<Object> elementos = elementoDAO.listarPorEmpresaYSucursal(sucursal, empresa);
        //Arma la lista de puntos de venta
        List<PuntoVenta> puntosVentas = new ArrayList<>();
        PuntoVenta puntoVenta;
        for(Object elemento : elementos) {
            puntoVenta = new PuntoVenta();
            puntoVenta.setPuntoVenta((int) elemento);
            puntosVentas.add(puntoVenta);
        }
        //Retorna los datos
        return puntosVentas;
    }
    
    //Obtiene el numero 
    public int obtenerNumero(int puntoVenta, String codigoAfip, int idSucursal, int idEmrpesa) {
        //Obtiene la sucursal por id
        Sucursal sucursal = sucursalDAO.findById(idSucursal).get();
        //Obtiene la empresa por id
        Empresa empresa = empresaDAO.findById(idEmrpesa).get();
        PuntoVenta pVenta = elementoDAO.findByPuntoVentaAndCodigoAfipAndSucursalAndEmpresa(puntoVenta, codigoAfip, sucursal, empresa);
        int numero = pVenta.getUltimoNumero() + 1;
        return numero;
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public PuntoVenta agregar(PuntoVenta elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(PuntoVenta elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(PuntoVenta elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private PuntoVenta formatearStrings(PuntoVenta elemento) {
        elemento.setCodigoAfip(elemento.getCodigoAfip().trim());
        return elemento;
    }

}