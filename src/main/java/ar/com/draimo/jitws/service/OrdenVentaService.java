package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IClienteDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaEscalaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaTramoDAO;
import ar.com.draimo.jitws.model.OrdenVenta;
import ar.com.draimo.jitws.model.OrdenVentaEscala;
import ar.com.draimo.jitws.model.OrdenVentaTramo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio OrdenVenta
 * @author blas
 */

@Service
public class OrdenVentaService {
    
    //Define la referencia al dao
    @Autowired
    IOrdenVentaDAO elementoDAO;
    
    //Define la referencia al dao orden venta escala
    @Autowired
    IOrdenVentaEscalaDAO ordenVentaEscalaDAO;
    
    //Define la referencia al dao orden venta tramo
    @Autowired
    IOrdenVentaTramoDAO ordenVentaTramoDAO;
    
    //Define la referencia al dao cliente
    @Autowired
    IClienteDAO clienteDAO;
    
    //Define la referencia al dao empresaa
    @Autowired
    IEmpresaDAO empresaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        OrdenVenta elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<OrdenVenta> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<OrdenVenta> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
   
    //Obtiene una lista por cliente
    public List<OrdenVenta> listarPorCliente(int idCliente) {
        return elementoDAO.findByCliente(clienteDAO.findById(idCliente));
    }
   
    //Obtiene una lista por empresa
    public Object listarPorEmpresa(int idEmpresa) throws JsonProcessingException, IOException {
        List<OrdenVenta> ordenesVenta = elementoDAO.findByEmpresa(empresaDAO.findById(idEmpresa));
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ordenVenta");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroOrdenVentaEscala", theFilter).addFilter("filtroOrdenVentaTramo", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(ordenesVenta);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public OrdenVenta agregar(OrdenVenta elemento) {
        //Formatea los string de OrdenVenta
        elemento = formatearStrings(elemento);
        //Establece la fecha actual
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        //Establece activa en true
        elemento.setEstaActiva(true);
        //Establece ActivaDesde con fecha actual
        elemento.setActivaDesde(new Date(new java.util.Date().getTime()));
        //Agrega la orden de venta
        OrdenVenta ordenVenta =  elementoDAO.save(elemento);
        //Verifica si la lista es de OrdenVentaEscala o OrdenVentaTramo
        if(!elemento.getOrdenesVentasEscalas().isEmpty()) {
            //Recorre la lista de OrdenVentaEscala
            for (OrdenVentaEscala ove : elemento.getOrdenesVentasEscalas()) {
                //Establece el id de orden venta
                ove.setOrdenVenta(ordenVenta);
                //Verifica que exista algun precio asignado para guardar
                if (!(ove.getImporteFijo() == null && ove.getPrecioUnitario() == null
                        && ove.getMinimo() == null)) {
                    //Agrega la orden venta escala
                    ordenVentaEscalaDAO.saveAndFlush(ove);
                }
            }
        } else {
            //Recorre la lista de OrdenVentaTramo
            for(OrdenVentaTramo ovt : elemento.getOrdenesVentasTramos()) {
                //Establece el id de orden venta
                ovt.setOrdenVenta(ordenVenta);
                //Verifica que exista algun precio o importe asignado para guardar
                if (!(ovt.getImporteFijoSeco() == null && ovt.getImporteFijoRef() == null
                        && ovt.getPrecioUnitarioSeco() == null && ovt.getPrecioUnitarioRef() == null)) {
                    //Agrega la orden venta tramo
                    ordenVentaTramoDAO.saveAndFlush(ovt);
                }
            }
        }
        //Retorna la orden de venta almacenada
        return elementoDAO.saveAndFlush(ordenVenta);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(OrdenVenta elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(OrdenVenta elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private OrdenVenta formatearStrings(OrdenVenta elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        if(elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        return elemento;
    }
    
}
