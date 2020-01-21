//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IAfipComprobanteDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IPuntoVentaDAO;
import ar.com.draimo.jitws.dao.ISucursalDAO;
import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.dto.InitPuntoVentaDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.PuntoVenta;
import ar.com.draimo.jitws.model.Sucursal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Punto de Venta
 *
 * @author blas
 */
@Service
public class PuntoVentaService {

    //Define la referencia al dao
    @Autowired
    IPuntoVentaDAO elementoDAO;

    //Define la referencia al dao sucursal
    @Autowired
    ISucursalDAO sucursalDAO;

    //Define la referencia al dao empresa
    @Autowired
    IEmpresaDAO empresaDAO;

    //Define la referencia al dao afipComprobante
    @Autowired
    IAfipComprobanteDAO afipComprobanteDAO;

    //Define la referencia al dao tipoComprobante
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;

    //Define la subopcion pestania service
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;

    //Obtiene la lista completa
    public InitPuntoVentaDTO inicializar(int rol, int subopcion) {
        InitPuntoVentaDTO p = new InitPuntoVentaDTO();
        p.setEmpresas(empresaDAO.findAll());
        p.setSucursales(sucursalDAO.findAll());
        p.setUltimoId(obtenerSiguienteId());
        p.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(rol, subopcion));
        p.setTipoComprobantes(tipoComprobanteDAO.findByNumeracionPuntoVentaTrue());
        return p;
    }

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        PuntoVenta elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<PuntoVenta> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por sucursal
    public List<PuntoVenta> listarPorSucursal(int idSucursal) {
        //Obtiene la sucursal por id
        Optional<Sucursal> sucursal = sucursalDAO.findById(idSucursal);
        //Retorna los datos
        return elementoDAO.findBySucursal(sucursal);
    }

    //Obtiene una lista por sucursal
    public List<PuntoVenta> listarPorEmpresa(int idEmpresa) {
        //Obtiene la sucursal por id
        Optional<Empresa> empresa = empresaDAO.findById(idEmpresa);
        //Retorna los datos
        return elementoDAO.findByEmpresa(empresa);
    }

    //Obtiene una lista por sucursal y empresa
    public List<PuntoVenta> listarPorSucursalYEmpresa(int idSucursal, int idEmpresa) {
        return elementoDAO.listarPorSucursalYEmpresa(idSucursal, idEmpresa);
    }

    //Obtiene una lista de hablilitados por sucursal, empresa y fe
    public List<PuntoVenta> listarHabilitadosPorSucursalEmpresaYFe(int idSucursal, int idEmpresa) {
        return elementoDAO.findByEmpresaAndSucursalAndFeTrueAndEstaHabilitadoTrue(
                empresaDAO.findById(idEmpresa).get(), sucursalDAO.findById(idSucursal).get());
    }

    //Obtiene una lista por sucursal y empresa y agrega letra a cada registro
    public List<PuntoVenta> listarPorSucursalYEmpresaLetra(int idSucursal, int idEmpresa) {
        return elementoDAO.listarPorSucursalYEmpresa(idSucursal, idEmpresa);
    }

    //Obtiene una lista por sucursal y empresa
    public List<PuntoVenta> listarPorEmpresaYSucursalYTipoComprobante(int idEmpresa, int idSucursal, int idTipoComprobante) {
        //Obtiene la lista de puntos de venta
        List<Object> elementos = elementoDAO.listarPorEmpresaSucursalYTipoComprobante(idSucursal, idEmpresa, idTipoComprobante);
        //Arma la lista de puntos de venta
        List<PuntoVenta> puntosVentas = new ArrayList<>();
        PuntoVenta puntoVenta;
        boolean aux = true;
        for (Object elemento : elementos) {
            puntoVenta = new PuntoVenta();
            puntoVenta.setPuntoVenta((int) elemento);
            if (aux) {
                aux = false;
                puntoVenta.setPorDefecto(true);
            }
            puntosVentas.add(puntoVenta);
        }
        puntosVentas.sort(Comparator.comparing(PuntoVenta::getPuntoVenta));
        //Retorna los datos
        return puntosVentas;
    }

    //Obtiene el numero 
    public int obtenerNumero(int puntoVenta, String codigoAfip, int idSucursal, int idEmrpesa) {
        //Obtiene la sucursal por id
        Sucursal sucursal = sucursalDAO.findById(idSucursal).get();
        //Obtiene la empresa por id
        Empresa empresa = empresaDAO.findById(idEmrpesa).get();
        PuntoVenta pVenta
                = elementoDAO.findByPuntoVentaAndSucursalAndEmpresaAndAfipComprobante_CodigoAfip(
                        puntoVenta, sucursal, empresa, codigoAfip);
        int numero = pVenta.getUltimoNumero() + 1;
        return numero;
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public PuntoVenta agregar(PuntoVenta elemento) throws Exception {
        controlarLongitud(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(PuntoVenta elemento) throws Exception {
        controlarLongitud(elemento);
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Controla la longitud de los atributos de tipo short
    private void controlarLongitud(PuntoVenta elemento) {
        //Obtiene longitud de anio, si es mayor a 1 retorna error
        String copias = String.valueOf(elemento.getCopias());
        if (copias.length() > 1) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " COPIAS");
        }
    }

}
