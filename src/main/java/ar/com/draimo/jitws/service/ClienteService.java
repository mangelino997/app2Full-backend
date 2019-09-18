package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IClienteCuentaBancariaDAO;
import ar.com.draimo.jitws.dao.IClienteDAO;
import ar.com.draimo.jitws.dao.IClienteOrdenVentaDAO;
import ar.com.draimo.jitws.dao.ICondicionVentaDAO;
import ar.com.draimo.jitws.dao.ICuentaBancariaDAO;
import ar.com.draimo.jitws.dao.ITipoDocumentoDAO;
import ar.com.draimo.jitws.dao.ITipoTarifaDAO;
import ar.com.draimo.jitws.model.Cliente;
import ar.com.draimo.jitws.model.ClienteCuentaBancaria;
import ar.com.draimo.jitws.model.ClienteOrdenVenta;
import ar.com.draimo.jitws.model.CuentaBancaria;
import ar.com.draimo.jitws.model.OrdenVenta;
import ar.com.draimo.jitws.model.TipoDocumento;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Cliente
 *
 * @author blas
 */
@Service
public class ClienteService {

    //Define la referencia al dao
    @Autowired
    IClienteDAO elementoDAO;

    //Define la referencia al dao CondicionVenta
    @Autowired
    ICondicionVentaDAO condicionVentaDAO;

    //Define la referencia al dao ClienteOrdenVentaDAO
    @Autowired
    IClienteOrdenVentaDAO clienteOrdenVentaDAO;

    //Define la referencia al dao TipoTarifaDAO
    @Autowired
    ITipoTarifaDAO tipoTarifaDAO;

    //Define la referencia al dao CuentaBancariaDAO
    @Autowired
    ICuentaBancariaDAO cuentaBancariaDAO;

    //Define la referencia al dao ClienteCuentaBancariaDAO
    @Autowired
    IClienteCuentaBancariaDAO clienteCuentaBancariaDAO;
    
    //Define la referencia al dao tipoDocumento
    @Autowired
    ITipoDocumentoDAO tipoDocumentoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Cliente elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene una lista completa
    public Object listar() throws IOException {
        List<Cliente> clientes = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(clientes);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene por id
    public Object obtenerPorId(int id) throws IOException {
        Cliente clientes = elementoDAO.findById(id).get();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(clientes);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por alias
    public Object listarPorAliasListaPrecio(String alias, int idCliente) throws IOException {
        List<Cliente> clientes;
        if (alias.equals("***")) {
            clientes = elementoDAO.findByIdNot(idCliente);
        } else {
            clientes = elementoDAO.findByAliasContainingAndIdNot(alias, idCliente);
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(clientes);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por alias
    public List<Cliente> listarPorAlias(String alias) throws IOException {
        List<Cliente> clientes;
        if (alias.equals("***")) {
            clientes = elementoDAO.findAll();
        } else {
            clientes = elementoDAO.findByAliasContaining(alias);
        }
        return clientes;
//        ObjectMapper mapper = new ObjectMapper();
//        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
//                .serializeAllExcept("cliente");
//        FilterProvider filters = new SimpleFilterProvider()
//                .addFilter("clienteordenventafiltro", theFilter);
//        String string = mapper.writer(filters).writeValueAsString(clientes);
//        return mapper.readValue(string, Object.class);
    }

    //Agrega un cliente eventual
    @Transactional(rollbackFor = Exception.class)
    public Cliente agregarClienteEventual(Cliente elemento) {
        elemento = formatearString(elemento);
        elemento.setEsCuentaCorriente(false);
        elemento.setCondicionVenta(condicionVentaDAO.findById(1).get());
        elemento.setEsSeguroPropio(false);
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        elemento.setImprimirControlDeuda(false);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Cliente agregar(Cliente elemento) {
        elemento = formatearString(elemento);
        Date fechaAlta = new Date(new java.util.Date().getTime());
        elemento.setFechaAlta(fechaAlta);
        Cliente c;
        if (elemento.getCuentaGrupo() != null) {
            c = elementoDAO.findById(elemento.getCuentaGrupo().getId()).get();
            if (c != null) {
                elemento.setCuentaGrupo(c);
            } else {
                throw new DataIntegrityViolationException("Registro no existente: CUENTA GRUPO");
            }
        }
        Cliente cliente = elementoDAO.saveAndFlush(elemento);
        //Agrega la lista de ordenes de venta del cliente
        if(elemento.getClienteOrdenesVentas() != null) {
            for(ClienteOrdenVenta cov : elemento.getClienteOrdenesVentas()) {
                cov.setCliente(cliente);
                cov.setFechaAlta(fechaAlta);
                clienteOrdenVentaDAO.saveAndFlush(cov);
            }
        }
        //Recorre la lista de cliente cuenta bancaria y agrega registros
        if(elemento.getClienteCuentasBancarias() != null) {
            for(ClienteCuentaBancaria ccb : elemento.getClienteCuentasBancarias()) {
                ccb.setCliente(cliente);
                clienteCuentaBancariaDAO.saveAndFlush(ccb);
            }
        }
        return cliente;
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Cliente elemento) {
        elemento = formatearString(elemento);
        elemento.setFechaUltimaMod(new Date(new java.util.Date().getTime()));
        elemento.setAlias(elemento.getId() + " - " + elemento.getRazonSocial()
                + " - " + elemento.getNombreFantasia() + " - " + elemento.getNumeroDocumento());
        Cliente c;
        if (elemento.getCuentaGrupo() != null) {
            c = elementoDAO.findById(elemento.getCuentaGrupo().getId()).get();
            if (c != null) {
                elemento.setCuentaGrupo(c);
            } else {
                throw new DataIntegrityViolationException("Registro no existente: CUENTA GRUPO");
            }
        }
        Cliente cliente = elementoDAO.save(elemento);
        //Recorre la lista de cliente cuenta bancaria y agrega registros
        for(ClienteCuentaBancaria ccb : elemento.getClienteCuentasBancarias()) {
            ccb.setCliente(cliente);
            clienteCuentaBancariaDAO.saveAndFlush(ccb);
        }
    }

    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public void establecerAlias(Cliente elemento) {
        TipoDocumento t = tipoDocumentoDAO.findById(elemento.getTipoDocumento().getId()).get();
        elemento.setAlias(elemento.getId() + " - " + elemento.getRazonSocial()
                + " - " + t.getAbreviatura() + " " + elemento.getNumeroDocumento());
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los string
    private Cliente formatearString(Cliente elemento) {
        elemento.setRazonSocial(elemento.getRazonSocial().trim().toUpperCase());
        if (elemento.getNombreFantasia() != null) {
            elemento.setNombreFantasia(elemento.getNombreFantasia().trim());
        }
        elemento.setDomicilio(elemento.getDomicilio().trim());
        if (elemento.getTelefono() != null) {
            elemento.setTelefono(elemento.getTelefono().trim());
        }
        if (elemento.getSitioWeb() != null) {
            elemento.setSitioWeb(elemento.getSitioWeb().toLowerCase().trim());
        }
        elemento.setNumeroDocumento(elemento.getNumeroDocumento().trim());
        if (elemento.getNumeroIIBB() != null) {
            elemento.setNumeroIIBB(elemento.getNumeroIIBB().trim());
        }
        if (elemento.getNumeroPolizaSeguro() != null) {
            elemento.setNumeroPolizaSeguro(elemento.getNumeroPolizaSeguro().trim());
        }
        if (elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        if (elemento.getNotaEmisionComprobante() != null) {
            elemento.setNotaEmisionComprobante(elemento.getNotaEmisionComprobante().trim());
        }
        if (elemento.getNotaImpresionComprobante() != null) {
            elemento.setNotaImpresionComprobante(elemento.getNotaImpresionComprobante().trim());
        }
        if (elemento.getNotaImpresionRemito() != null) {
            elemento.setNotaImpresionRemito(elemento.getNotaImpresionRemito().trim());
        }
        return elemento;
    }

}