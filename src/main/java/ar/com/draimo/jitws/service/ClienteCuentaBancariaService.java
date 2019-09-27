//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IClienteDAO;
import ar.com.draimo.jitws.dao.IClienteCuentaBancariaDAO;
import ar.com.draimo.jitws.dao.ICuentaBancariaDAO;
import ar.com.draimo.jitws.model.ClienteCuentaBancaria;
import ar.com.draimo.jitws.model.CuentaBancaria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ClienteCuentaBancaria
 *
 * @author blas
 */
@Service
public class ClienteCuentaBancariaService {

    //Define la referencia al dao 
    @Autowired
    IClienteCuentaBancariaDAO elementoDAO;

    //Define la referencia al dao CuentaBancaria
    @Autowired
    ICuentaBancariaDAO cuentaBancariaDAO;

    //Define la referencia a clienteDAO
    @Autowired
    IClienteDAO clienteDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ClienteCuentaBancaria elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<ClienteCuentaBancaria> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por Cliente
    public List<ClienteCuentaBancaria> listarPorCliente(int idCliente) {
        return elementoDAO.findByCliente(clienteDAO.findById(idCliente).get());
    }

    //Obtiene una lista por cuentaBancaria
    public List<ClienteCuentaBancaria> listarPorCuentaBancaria(int idCuentaBancaria){
        CuentaBancaria cuentaBancaria = cuentaBancariaDAO.findById(idCuentaBancaria).get();
        return elementoDAO.findByCuentaBancaria(cuentaBancaria);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ClienteCuentaBancaria agregar(ClienteCuentaBancaria elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public ClienteCuentaBancaria actualizar(ClienteCuentaBancaria elemento) {
        return elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }

}