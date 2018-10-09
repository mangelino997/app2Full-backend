package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IEscalaTarifaDAO;
import ar.com.wecoode.jitws.dao.IOrdenVentaEscalaDAO;
import ar.com.wecoode.jitws.model.EscalaTarifa;
import ar.com.wecoode.jitws.model.OrdenVentaEscala;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio OrdenVentaEscala
 * @author blas
 */

@Service
public class OrdenVentaEscalaService {
    
    //Define la referencia al dao
    @Autowired
    IOrdenVentaEscalaDAO elementoDAO;
    
    //Define la referencia al dao escala tarifa
    @Autowired
    IEscalaTarifaDAO escalaTarifaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        OrdenVentaEscala elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<OrdenVentaEscala> listar() {
        return elementoDAO.findAll();
    }
    
    /*
    * Obtiene una lista con todas las escalas tarifas asignadas
    * para la tabla de orden-venta.html
    */
    public List<OrdenVentaEscala> listarConEscalaTarifa() {
        //Obtiene la lista completa de escalas tarifas
        List<EscalaTarifa> escalasTarifas = escalaTarifaDAO.findAll();
        //Ordena la lista de escalas tarifas
        escalasTarifas.sort(Comparator.comparing(EscalaTarifa::getValor));
        //Crea una lista vacia de ordenes de ventas escalas
        List<OrdenVentaEscala> ordenesVentasEscalas = new ArrayList<>();
        //Define una orden venta escala
        OrdenVentaEscala ordenVentaEscala;
        //Recorre la lista de escalas tarifas
        for(EscalaTarifa escalaTarifa : escalasTarifas) {
            ordenVentaEscala = new OrdenVentaEscala();
            ordenVentaEscala.setEscalaTarifa(escalaTarifa);
            ordenesVentasEscalas.add(ordenVentaEscala);
        }
        //Retorna los datos
        return ordenesVentasEscalas;
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public OrdenVentaEscala agregar(OrdenVentaEscala elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(OrdenVentaEscala elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(OrdenVentaEscala elemento) {
        elementoDAO.delete(elemento);
    }
    
}
