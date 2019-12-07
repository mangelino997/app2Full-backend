//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IAfipCondicionIvaDAO;
import ar.com.draimo.jitws.dao.IClienteCuentaBancariaDAO;
import ar.com.draimo.jitws.dao.IClienteDAO;
import ar.com.draimo.jitws.dao.IClienteOrdenVentaDAO;
import ar.com.draimo.jitws.dao.IClienteVtoPagoDAO;
import ar.com.draimo.jitws.dao.ICobradorDAO;
import ar.com.draimo.jitws.dao.ICondicionVentaDAO;
import ar.com.draimo.jitws.dao.IContactoClienteDAO;
import ar.com.draimo.jitws.dao.ICuentaBancariaDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IResumenClienteDAO;
import ar.com.draimo.jitws.dao.IRolDAO;
import ar.com.draimo.jitws.dao.IRolOpcionDAO;
import ar.com.draimo.jitws.dao.IRubroDAO;
import ar.com.draimo.jitws.dao.ISituacionClienteDAO;
import ar.com.draimo.jitws.dao.ISubopcionDAO;
import ar.com.draimo.jitws.dao.ISubopcionPestaniaDAO;
import ar.com.draimo.jitws.dao.ISucursalDAO;
import ar.com.draimo.jitws.dao.ITipoDocumentoDAO;
import ar.com.draimo.jitws.dao.ITipoTarifaDAO;
import ar.com.draimo.jitws.dao.IVendedorDAO;
import ar.com.draimo.jitws.dao.IZonaDAO;
import ar.com.draimo.jitws.dto.ClienteDTO;
import ar.com.draimo.jitws.dto.PruebaDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Cliente;
import ar.com.draimo.jitws.model.ClienteCuentaBancaria;
import ar.com.draimo.jitws.model.ClienteOrdenVenta;
import ar.com.draimo.jitws.model.ClienteVtoPago;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.Rol;
import ar.com.draimo.jitws.model.Subopcion;
import ar.com.draimo.jitws.model.TipoDocumento;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    //Define la referencia al dao ClienteVtoPagoDAO
    @Autowired
    IClienteVtoPagoDAO clienteVtoPagoDAO;

    //Define la referencia al dao tipoDocumento
    @Autowired
    ITipoDocumentoDAO tipoDocumentoDAO;

    //Define la referencia al dao empresa
    @Autowired
    IEmpresaDAO empresaDAO;
    
    //Define la referencia al dao contactoCliente
    @Autowired
    IContactoClienteDAO contactoClienteDAO;
    
    //Define la referencia al dao condicionIva
    @Autowired
    IAfipCondicionIvaDAO afipCondicionIvaDAO;
    
    //Define la referencia al dao resumenCliente
    @Autowired
    IResumenClienteDAO resumenClienteDAO;
    
    //Define la referencia al dao situacionCliente
    @Autowired
    ISituacionClienteDAO situacionClienteDAO;
    
    //Define la referencia al dao sucursal
    @Autowired
    ISucursalDAO sucursalDAO;
    
    //Define la referencia al dao cobrador
    @Autowired
    IVendedorDAO vendedorDAO;
    
    //Define la referencia al dao vendedor
    @Autowired
    ICobradorDAO cobradorDAO;
    
    //Define la referencia al dao zona
    @Autowired
    IZonaDAO zonaDAO;
    
    //Define la referencia al dao rubro
    @Autowired
    IRubroDAO rubroDAO;
    
    //Define la referencia al dao subopcion
    @Autowired
    ISubopcionPestaniaDAO subopcionPestaniaDAO;
    
    //Define la referencia al dao rolOpcion
    @Autowired
    IRolOpcionDAO rolOpcionDAO;
    
    //Define la referencia al dao rol
    @Autowired
    IRolDAO rolDAO;
    
    //Define la referencia al dao rolOpcion
    @Autowired
    ISubopcionDAO subopcionDAO;
    
    //Define la referencia al service de empresa
    @Autowired
    UsuarioEmpresaService empresaService;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Cliente elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene una lista completa
    public Object listar() throws IOException {
        List<Cliente> clientes = elementoDAO.findAll();
        //Construye la lista de rubros productos cuentas contables para cada empresa
        for (Cliente cliente : clientes) {
            cliente.setClienteCuentasBancarias(construirCuentasBancariasParaEmpresas(cliente));
            cliente.setClienteVtosPagos(construirVtoPagoParaEmpresas(cliente));
        }
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
        Cliente cliente = elementoDAO.findById(id).get();
        //Construye la lista de rubros productos cuentas contables para cada empresa
        cliente.setClienteCuentasBancarias(construirCuentasBancariasParaEmpresas(cliente));
        cliente.setClienteVtosPagos(construirVtoPagoParaEmpresas(cliente));
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(cliente);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por alias
    public Object listarPorAliasListaPrecio(String alias, int idCliente) throws IOException {
        List<Cliente> clientes = alias.equals("*") ? elementoDAO.findByIdNot(idCliente):
            elementoDAO.findByAliasContainingAndIdNot(alias, idCliente);
        //Construye la lista de rubros productos cuentas contables para cada empresa
        for (Cliente cliente : clientes) {
            cliente.setClienteCuentasBancarias(construirCuentasBancariasParaEmpresas(cliente));
            cliente.setClienteVtosPagos(construirVtoPagoParaEmpresas(cliente));
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
    public List<Cliente> listarPorAlias(String alias, boolean activos) throws IOException {
        List<Cliente> clientes=  alias.equals("*") ? elementoDAO.findAll() : activos==true ? 
                elementoDAO. findByAliasContainingAndFechaBajaIsNull(alias):
                elementoDAO.findByAliasContaining(alias);
        //Construye la lista de rubros productos cuentas contables para cada empresa
        for (Cliente cliente : clientes) {
            cliente.setClienteCuentasBancarias(construirCuentasBancariasParaEmpresas(cliente));
            cliente.setClienteVtosPagos(construirVtoPagoParaEmpresas(cliente));
        }
        return clientes;
    }
    
    //Obtiene un listado por filtro
    public Object listarPorFiltros(ClienteDTO clienteDTO) throws IOException {
        List<Cliente> elementos = elementoDAO.listarPorFiltros(clienteDTO.getIdLocalidad(), clienteDTO.getIdCobrador(),
                clienteDTO.getIdCondicionVenta(), clienteDTO.getEsSeguroPropio());
        return retornarObjeto(elementos, null);
    }
    
    //Agrega un cliente eventual
    public PruebaDTO listarParaInicializar(int idUsuario, int idRol, int idSubopcion) {
        PruebaDTO p = new PruebaDTO();
        Optional<Rol> rol = rolDAO.findById(idRol);
        Optional<Subopcion> subopcion = subopcionDAO.findById(idSubopcion);
        p.setEmpresas(empresaService.listarEmpresasActivasDeUsuario(idUsuario));
        p.setRolOpciones(rolOpcionDAO.findByRolAndOpcion_Subopcion(rol, subopcion));
        p.setSubopcionPestanias(subopcionPestaniaDAO.findByRolAndSubopcion(rol, subopcion));
        p.setAfipCondicionesIvas(afipCondicionIvaDAO.findAll());
        p.setCobradores(cobradorDAO.findAll());
        p.setCondicionVentas(condicionVentaDAO.findAll());
        p.setResumenClientes(resumenClienteDAO.findAll());
        p.setRubros(rubroDAO.findAll());
        p.setSituacionClientes(situacionClienteDAO.findAll());
        p.setSucursales(sucursalDAO.findAll());
        p.setTipoDocumentos(tipoDocumentoDAO.findAll());
        p.setVendedores(vendedorDAO.findAll());
        p.setZonas(zonaDAO.findAll());
        return p;
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
                throw new DataIntegrityViolationException(MensajeRespuesta.NO_EXISTENTE + " CUENTA GRUPO");
            }
        }
        Cliente cliente = elementoDAO.saveAndFlush(elemento);
        //Agrega la lista de ordenes de venta del cliente
        if (elemento.getClienteOrdenesVentas() != null) {
            for (ClienteOrdenVenta cov : elemento.getClienteOrdenesVentas()) {
                cov.setCliente(cliente);
                cov.setFechaAlta(fechaAlta);
                clienteOrdenVentaDAO.saveAndFlush(cov);
            }
        }
        //Recorre la lista de cliente cuenta bancaria y agrega registros
        if (elemento.getClienteCuentasBancarias() != null) {
            for (ClienteCuentaBancaria ccb : elemento.getClienteCuentasBancarias()) {
                if (ccb.getCuentaBancaria() != null) {
                    ccb.setCliente(cliente);
                    clienteCuentaBancariaDAO.saveAndFlush(ccb);
                }
            }
        }
        //Registra los vencimientos de pagos
        if (elemento.getClienteVtosPagos() != null) {
            for (ClienteVtoPago cvp : elemento.getClienteVtosPagos()) {
                if(cvp != null) {
                    cvp.setCliente(cliente);
                    clienteVtoPagoDAO.saveAndFlush(cvp);
                }
            }
        }
        return cliente;
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Cliente elemento) {
        elemento = formatearString(elemento);
        Date fechaAlta = new Date(new java.util.Date().getTime());
        elemento.setFechaUltimaMod(fechaAlta);
        Cliente c;
        if (elemento.getCuentaGrupo() != null) {
            c = elementoDAO.findById(elemento.getCuentaGrupo().getId()).get();
            if (c != null) {
                elemento.setCuentaGrupo(c);
            } else {
                throw new DataIntegrityViolationException(MensajeRespuesta.NO_EXISTENTE + " CUENTA GRUPO");
            }
        }
        establecerAlias(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        //Elimina los contactos del cliente
        contactoClienteDAO.deleteByCliente(elementoDAO.findById(id).get());
        //Elimina el cliente
        elementoDAO.deleteById(id);
    }

    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public Cliente establecerAlias(Cliente elemento) {
        TipoDocumento t = tipoDocumentoDAO.findById(elemento.getTipoDocumento().getId()).get();
        elemento.setAlias(elemento.getId() + " - " + elemento.getRazonSocial() + " - " + elemento.getNombreFantasia() 
                + " - " + t.getAbreviatura() + " " + elemento.getNumeroDocumento());
        return elementoDAO.save(elemento);
    }

    //Convierte una lista o un elemento a object para retornar con filtros aplicados
    private Object retornarObjeto(List<Cliente> elementos, Cliente elemento) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos!=null ? elementos : elemento);
        return mapper.readValue(string, Object.class);
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

    //Arma la lista de cliente cuentas contables para todas las empresas
    private List<ClienteCuentaBancaria> construirCuentasBancariasParaEmpresas(Cliente cliente) {
        List<Empresa> empresas = empresaDAO.findAll();
        List<ClienteCuentaBancaria> ccbLista = new ArrayList<>();
        ClienteCuentaBancaria ccb;
        for (Empresa empresa : empresas) {
            ccb = new ClienteCuentaBancaria();
            ccb.setEmpresa(empresa);
            ccb.setCliente(null);
            ccb.setCuentaBancaria(null);
            ccbLista.add(ccb);
        }
        int indice;
        for (ClienteCuentaBancaria r : cliente.getClienteCuentasBancarias()) {
            indice = r.getEmpresa().getId() - 1;
            ccbLista.get(indice).setId(r.getId());
            ccbLista.get(indice).setVersion(r.getVersion());
            ccbLista.get(indice).setCliente(r.getCliente());
            ccbLista.get(indice).setCuentaBancaria(r.getCuentaBancaria());
        }
        return ccbLista;
    }
    
    //Arma la lista de cliente cuentas contables para todas las empresas
    private List<ClienteVtoPago> construirVtoPagoParaEmpresas(Cliente cliente) {
        List<Empresa> empresas = empresaDAO.findAll();
        List<ClienteVtoPago> cvpLista = new ArrayList<>();
        ClienteVtoPago cvp;
        for (Empresa empresa : empresas) {
            cvp = new ClienteVtoPago();
            cvp.setEmpresa(empresa);
            cvp.setCliente(null);
            cvp.setDiasFechaFactura((short)0);
            cvp.setEnero(null);
            cvp.setFebrero(null);
            cvp.setMarzo(null);
            cvp.setAbril(null);
            cvp.setMayo(null);
            cvp.setJunio(null);
            cvp.setJulio(null);
            cvp.setAgosto(null);
            cvp.setSeptiembre(null);
            cvp.setOctubre(null);
            cvp.setNoviembre(null);
            cvp.setDiciembre(null);
            cvpLista.add(cvp);
        }
        int indice;
        for (ClienteVtoPago r : cliente.getClienteVtosPagos()) {
            indice = r.getEmpresa().getId() - 1;
            cvpLista.get(indice).setId(r.getId());
            cvpLista.get(indice).setVersion(r.getVersion());
            cvpLista.get(indice).setCliente(r.getCliente());
            cvpLista.get(indice).setDiasFechaFactura(r.getDiasFechaFactura());
            cvpLista.get(indice).setEnero(r.getEnero());
            cvpLista.get(indice).setFebrero(r.getFebrero());
            cvpLista.get(indice).setMarzo(r.getMarzo());
            cvpLista.get(indice).setAbril(r.getAbril());
            cvpLista.get(indice).setMayo(r.getMayo());
            cvpLista.get(indice).setJunio(r.getJunio());
            cvpLista.get(indice).setJulio(r.getJulio());
            cvpLista.get(indice).setAgosto(r.getAgosto());
            cvpLista.get(indice).setSeptiembre(r.getSeptiembre());
            cvpLista.get(indice).setOctubre(r.getOctubre());
            cvpLista.get(indice).setNoviembre(r.getNoviembre());
            cvpLista.get(indice).setDiciembre(r.getDiciembre());
        }
        return cvpLista;
    }
    
    
}