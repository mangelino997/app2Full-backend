//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IClienteDAO;
import ar.com.draimo.jitws.dao.ISucursalClienteDAO;
import ar.com.draimo.jitws.model.SucursalCliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio SucursalCliente
 *
 * @author blas
 */
@Service
public class SucursalClienteService {

    //Define la referencia al dao
    @Autowired
    ISucursalClienteDAO elementoDAO;

    //Define la referencia al dao cliente
    @Autowired
    IClienteDAO clienteDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        SucursalCliente elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<SucursalCliente> elementos = elementoDAO.findAll();
        return retornarObjeto(elementos);
    }

    //Obtiene una lista por nombre
    public Object listarPorNombre(String nombre) throws IOException {
        List<SucursalCliente> elementos = nombre.equals("***")
                ? elementoDAO.findAll() : elementoDAO.findByNombreContaining(nombre);
        return retornarObjeto(elementos);
    }

    //Obtiene una lista por nombre de banco
    public Object listarPorCliente(int idCliente) throws IOException {
        List<SucursalCliente> elementos = elementoDAO.findByCliente(clienteDAO.findById(idCliente));
        return retornarObjeto(elementos);
    }

    //Obtiene una lista por alias del cliente
    public Object listarPorAliasCliente(String alias) throws IOException {
        List<SucursalCliente> elementos = elementoDAO.findByCliente_AliasContaining(alias);
        return retornarObjeto(elementos);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public SucursalCliente agregar(SucursalCliente elemento) {
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(SucursalCliente elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Prepara el elemento para retornarlo con los filtros aplicados(object)
    private Object retornarObjeto(List<SucursalCliente> elementos) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Formatea los strings
    private SucursalCliente formatearStrings(SucursalCliente elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        elemento.setDomicilio(elemento.getDomicilio().trim());
        elemento.setTelefonoFijo(elemento.getTelefonoFijo() != null?
                elemento.getTelefonoFijo().trim(): "");
        elemento.setTelefonoMovil(elemento.getTelefonoMovil()!= null?
                elemento.getTelefonoMovil().trim():"");
        return elemento;
    }

}