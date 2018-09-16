package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.constant.Funcion;
import ar.com.wecoode.jitws.dao.IClienteDAO;
import ar.com.wecoode.jitws.model.Cliente;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Cliente
 * @author blas
 */

@Service
public class ClienteService {
    
    //Define la referencia al dao
    @Autowired
    IClienteDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Cliente elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene una lista completa
    public List<Cliente> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene por id
    public Optional<Cliente> obtenerPorId(int id) {
        return elementoDAO.findById(id);
    }
    
    //Obtiene una lista por alias
    public List<Cliente> listarPorAlias(String alias) {
        if(alias.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByAliasContaining(alias);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Cliente agregar(Cliente elemento) {
        elemento = formatearString(elemento);
        elementoDAO.saveAndFlush(elemento);
        elemento.setAlias(elemento.getId() + " - " + elemento.getRazonSocial() 
                + " - " + elemento.getNombreFantasia() + " - " + elemento.getNumeroDocumento());
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Cliente elemento) {
        elemento = formatearString(elemento);
        elemento.setAlias(elemento.getId() + " - " + elemento.getRazonSocial() 
                + " - " + elemento.getNombreFantasia() + " - " + elemento.getNumeroDocumento());
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Cliente elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los string
    private Cliente formatearString(Cliente elemento) {
        elemento.setRazonSocial(Funcion.convertirATitulo(elemento.getRazonSocial().trim()));
        elemento.setNombreFantasia(Funcion.convertirATitulo(elemento.getNombreFantasia().trim()));
        elemento.setDomicilio(Funcion.primerLetraAMayuscula(elemento.getDomicilio().trim()));
        elemento.setSitioWeb(elemento.getSitioWeb().toLowerCase().trim());
        elemento.setObservaciones(Funcion.primerLetraAMayuscula(elemento.getObservaciones().trim()));
        elemento.setNotaEmisionComprobante(Funcion.primerLetraAMayuscula(elemento.getNotaEmisionComprobante().trim()));
        elemento.setNotaImpresionComprobante(Funcion.primerLetraAMayuscula(elemento.getNotaImpresionComprobante().trim()));
        elemento.setNotaImpresionRemito(Funcion.primerLetraAMayuscula(elemento.getNotaImpresionRemito().trim()));
        return elemento;
    }
    
}
