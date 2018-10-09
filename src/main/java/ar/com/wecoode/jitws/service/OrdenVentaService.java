package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.constant.Funcion;
import ar.com.wecoode.jitws.dao.IOrdenVentaDAO;
import ar.com.wecoode.jitws.dao.IOrdenVentaEscalaDAO;
import ar.com.wecoode.jitws.dao.IOrdenVentaTramoDAO;
import ar.com.wecoode.jitws.model.OrdenVenta;
import ar.com.wecoode.jitws.model.OrdenVentaEscala;
import ar.com.wecoode.jitws.model.OrdenVentaTramo;
import java.time.LocalDate;
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
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        OrdenVenta elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
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
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public OrdenVenta agregar(OrdenVenta elemento) {
        /*
        * Establece PreciosDesde de ActivaDesde
        * Por cuestiones de ahorro de codigo, se pasa el valor de PreciosDesde
        * de OrdenVentaEscala mediante ActivaDesde de OrdenVenta
        */
        LocalDate preciosDesde = elemento.getActivaDesde();
        //Formatea los string de OrdenVenta
        elemento = formatearStrings(elemento);
        //Establece la fecha actual
        elemento.setFechaAlta(LocalDate.now());
        //Establece activa en true
        elemento.setEstaActiva(true);
        //Establece ActivaDesde con fecha actual
        elemento.setActivaDesde(LocalDate.now());
        //Agrega la orden de venta
        OrdenVenta ordenVenta =  elementoDAO.save(elemento);
        //Verifica si la lista es de OrdenVentaEscala o OrdenVentaTramo
        if(!elemento.getOrdenesVentasEscalas().isEmpty()) {
            //Recorre la lista de OrdenVentaEscala
            for (OrdenVentaEscala ove : elemento.getOrdenesVentasEscalas()) {
                //Establece el id de orden venta
                ove.setOrdenVenta(ordenVenta);
                //Establece la fecha de precios desde
                ove.setPreciosDesde(preciosDesde);
                //Verifica que exista algun precio asignado para guardar
                if (!(ove.getImporteFijo() == null && ove.getPrecioUnitario() == null
                        && ove.getMinimo() == null)) {
                    //Agrega la orden venta escala
                    ordenVentaEscalaDAO.save(ove);
                }
            }
        } else {
            //Recorre la lista de OrdenVentaTramo
            for(OrdenVentaTramo ovt : elemento.getOrdenesVentasTramos()) {
                //Establece el id de orden venta
                ovt.setOrdenVenta(ordenVenta);
                //Establece la fecha de precios desde
                ovt.setPreciosDesde(preciosDesde);
                //Verifica que exista algun precio o importe asignado para guardar
                if (!(ovt.getImporteFijoSeco() == null && ovt.getImporteFijoRef() == null
                        && ovt.getPrecioUnitarioSeco() == null && ovt.getPrecioUnitarioRef() == null)) {
                    //Agrega la orden venta tramo
                    ordenVentaTramoDAO.save(ovt);
                }
            }
        }
        //Retorna la orden de venta almacenada
        return ordenVenta;
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
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        if(elemento.getObservaciones() != null) {
            elemento.setObservaciones(Funcion.primerLetraAMayuscula(elemento.getObservaciones().trim()));
        }
        return elemento;
    }
    
}
