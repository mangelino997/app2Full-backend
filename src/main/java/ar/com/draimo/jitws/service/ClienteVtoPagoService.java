//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IClienteDAO;
import ar.com.draimo.jitws.dao.IClienteVtoPagoDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.model.Cliente;
import ar.com.draimo.jitws.model.ClienteVtoPago;
import ar.com.draimo.jitws.model.Empresa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ClienteVtoPago
 * @author blas
 */

@Service
public class ClienteVtoPagoService {
    
    //Define el dao
    @Autowired
    IClienteVtoPagoDAO elementoDAO;
    
    //Define el dao de cliente
    @Autowired
    IClienteDAO clienteDAO;
    
    //Define el dao de empresa
    @Autowired
    IEmpresaDAO empresaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ClienteVtoPago elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento!=null?elemento.getId()+1:1);
    }
    
    //Obtiene la lista completa
    public List<ClienteVtoPago> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por cliente y empresa
    public List<ClienteVtoPago> listarPorClienteYEmpresa(int idCliente, int idEmpresa) {
        Empresa e = empresaDAO.findById(idEmpresa).get();
        Cliente c = clienteDAO.findById(idCliente).get();
        return elementoDAO.findByClienteAndEmpresa(c, e);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ClienteVtoPago agregar(ClienteVtoPago elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ClienteVtoPago elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
}