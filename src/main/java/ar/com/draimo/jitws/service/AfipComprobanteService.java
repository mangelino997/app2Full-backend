//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IAfipComprobanteDAO;
import ar.com.draimo.jitws.dao.IAfipCondicionIvaDAO;
import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.model.AfipComprobante;
import ar.com.draimo.jitws.model.AfipCondicionIva;
import ar.com.draimo.jitws.model.TipoComprobante;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio AfipComprobante
 * @author blas
 */

@Service
public class AfipComprobanteService {
    
    //Define el dao
    @Autowired
    IAfipComprobanteDAO elementoDAO;
    
    //Define el dao de tipoComprobante
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;
    
    //Define el dao de afipCondicionIva
    @Autowired
    IAfipCondicionIvaDAO afipCondicionIvaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipComprobante elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento!=null?elemento.getId()+1:1;
    }
    
    //Obtiene la lista completa
    public List<AfipComprobante> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene por codigoAfip
    public AfipComprobante obtenerPorCodigoAfip(String codigoAfip) {
        return elementoDAO.findByCodigoAfip(codigoAfip);
    }
    
    //Obtiene el codigo afip
    public String obtenerCodigoAfip(int idTipoComprobante, String letra) {
        Optional<TipoComprobante> tipoComprobante = tipoComprobanteDAO.findById(idTipoComprobante);
        AfipComprobante ac = elementoDAO.findByTipoComprobanteAndLetra(tipoComprobante, letra);
        return ac.getCodigoAfip();
    }
    
    //Obtiene la letra segun condicion de iva
    public String obtenerLetra(int idCondicionIva, int idTipoComprobante) {
        AfipCondicionIva aci = afipCondicionIvaDAO.findById(idCondicionIva).get();
        return aci.getId()==1? "A":"B";
    }
  
    //Obtiene por tipo de comprobante
    public List<AfipComprobante> listarPorTipoComprobante(int idTipoComprobante) {
        return elementoDAO.findByTipoComprobante(tipoComprobanteDAO.findById(idTipoComprobante).get());
    }
    
    //Obtiene una lista de letras por Tipo de Comprobante
    public List<String> listarLetras(int idTipoComprobante) {
        return elementoDAO.listarLetras(idTipoComprobante);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipComprobante agregar(AfipComprobante elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipComprobante elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private AfipComprobante formatearStrings(AfipComprobante elemento) {
        elemento.setLetra(elemento.getLetra().trim());
        elemento.setCodigoAfip(elemento.getCodigoAfip().trim());
        return elemento;
    }
    
}