//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IPdfDAO;
import ar.com.draimo.jitws.dao.IRetiroDepositoComprobanteDAO;
import ar.com.draimo.jitws.dao.IRetiroDepositoDAO;
import ar.com.draimo.jitws.dao.ISeguimientoEstadoDAO;
import ar.com.draimo.jitws.dao.ISeguimientoSituacionDAO;
import ar.com.draimo.jitws.dao.ISeguimientoVentaComprobanteDAO;
import ar.com.draimo.jitws.dao.ISeguimientoViajeRemitoDAO;
import ar.com.draimo.jitws.dao.ISucursalDAO;
import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.dto.elementoDTO;
import ar.com.draimo.jitws.model.Pdf;
import ar.com.draimo.jitws.model.RetiroDeposito;
import ar.com.draimo.jitws.model.RetiroDepositoComprobante;
import ar.com.draimo.jitws.model.SeguimientoEstado;
import ar.com.draimo.jitws.model.SeguimientoSituacion;
import ar.com.draimo.jitws.model.SeguimientoVentaComprobante;
import ar.com.draimo.jitws.model.SeguimientoViajeRemito;
import ar.com.draimo.jitws.model.Sucursal;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Servicio RetiroDeposito
 *
 * @author blas
 */
@Service
public class RetiroDepositoService {

    //Define la referencia al dao
    @Autowired
    IRetiroDepositoDAO elementoDAO;

    //Define la referencia al dao de retiro deposito comprobante
    @Autowired
    IRetiroDepositoComprobanteDAO elementoComprobanteDAO;

    //Define la referencia al dao empresa
    @Autowired
    IEmpresaDAO empresaDAO;

    //Define la referencia al dao Pdf
    @Autowired
    IPdfDAO pdfDAO;

    //Define la referencia al service pdf
    @Autowired
    PdfService pdfService;

    //Define la referencia al dao de tipoComprobante
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;

    //Define la referencia al dao de sucursal
    @Autowired
    ISucursalDAO sucursalDAO;

    //Define la referencia al dao de SeguimientoEstado
    @Autowired
    ISeguimientoEstadoDAO seguimientoEstadoDAO;

    //Define la referencia al dao de SeguimientoSituacion
    @Autowired
    ISeguimientoSituacionDAO seguimientoSituacionDAO;

    //Define la referencia al dao de SeguimientoViajeRemito
    @Autowired
    ISeguimientoViajeRemitoDAO seguimientoViajeRemitoDAO;

    //Define la referencia al dao de SeguimientoventaComprobante
    @Autowired
    ISeguimientoVentaComprobanteDAO seguimientoVentaComprobanteDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        RetiroDeposito elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<RetiroDeposito> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene la lista de planillas por esta cerrada
    public List<RetiroDeposito> listarPorEstaCerrada(boolean estaCerrada) {
        return elementoDAO.listarPorEstaCerrada(estaCerrada);
    }

    //Cierra un retiro
    public boolean cerrarRetiro(int idRetiroDeposito) {
        RetiroDeposito r = elementoDAO.findById(idRetiroDeposito).get();
        List<RetiroDepositoComprobante> rdCtes = elementoComprobanteDAO.findByRetiroDeposito(r);
        if (rdCtes.isEmpty()) {
            return false;
        } else {
            SeguimientoVentaComprobante svCte = new SeguimientoVentaComprobante();
            SeguimientoViajeRemito svRemito = new SeguimientoViajeRemito();
            SeguimientoEstado se = seguimientoEstadoDAO.findById(5).get();
            SeguimientoSituacion ss = seguimientoSituacionDAO.findById(1).get();
            Sucursal sucursal = sucursalDAO.findById(r.getSucursal().getId()).get();
            LocalDateTime fecha = LocalDateTime.now();
            for (RetiroDepositoComprobante retiroDepositoComprobante : rdCtes) {
                if (retiroDepositoComprobante.getVentaComprobante() != null) {
                    svCte.setSeguimientoEstado(se);
                    svCte.setSeguimientoSituacion(ss);
                    svCte.setFecha(fecha);
                    svCte.setSucursal(sucursal);
                    seguimientoVentaComprobanteDAO.saveAndFlush(svCte);
                } else if (retiroDepositoComprobante.getViajeRemito() != null) {
                    svRemito.setSeguimientoEstado(se);
                    svRemito.setSeguimientoSituacion(ss);
                    svRemito.setFecha(fecha);
                    svRemito.setSucursal(sucursal);
                    seguimientoViajeRemitoDAO.saveAndFlush(svRemito);
                }
            }
            r.setEstaCerrada(true);
            elementoDAO.save(r);
            return true;
        }
    }

    //Obtiene una lista por filtros(empresa y sucursal opcionales)
    public List<RetiroDeposito> listarPorFiltros(elementoDTO retiroDTO) {
        return elementoDAO.listarPorFiltros(retiroDTO.getIdEmpresa(),
                retiroDTO.getIdSucursal(), retiroDTO.getFechaDesde(), 
                retiroDTO.getFechaHasta(), retiroDTO.isEstaCerrada());
    }

    //Obtiene una lista por empresa
    public List<RetiroDeposito> listarPorEmpresa(int id) {
        return elementoDAO.findByEmpresa(empresaDAO.findById(id));
    }

    //Obtiene por numeroDocumento
    public List<RetiroDeposito> obtenerPorNumeroDocumento(String elemento) {
        return elementoDAO.findByNumeroDocumento(elemento);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public RetiroDeposito agregar(String retiroString, MultipartFile archivo) throws IOException {
        RetiroDeposito elemento = new ObjectMapper().readValue(retiroString, RetiroDeposito.class);
        elemento.setTipoComprobante(tipoComprobanteDAO.findById(25).get());
        elemento.setFechaRegistracion(LocalDateTime.now());
        elemento.setEstaCerrada(false);
        elemento = formatearStrings(elemento);
        Pdf pdf;
        if (!archivo.getOriginalFilename().equals("")) {
            Pdf u = pdfService.agregar(archivo, false);
            u.setTabla("retiroDeposito");
            pdf = pdfDAO.saveAndFlush(u);
        } else {
            pdf = null;
        }
        elemento.setPdfDni(pdf);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(String retiroString, MultipartFile archivo) throws IOException {
        RetiroDeposito elemento = new ObjectMapper().readValue(retiroString, RetiroDeposito.class);
        elemento = formatearStrings(elemento);
//        if (archivo.getOriginalFilename().equals("")) {
//            if (elemento.getPdfDni().getId() != 0) {
//                pdfDAO.deleteById(elemento.getPdfDni().getId());
//                elemento.setPdfDni(null);
//            } else {
//                elemento.setPdfDni(null);
//            }
//        } else {
//            if (elemento.getPdfDni().getId() != 0) {
//                Pdf f = pdfService.actualizar(elemento.getPdfDni().getId(), archivo, false);
//                Pdf dni = pdfDAO.save(f);
//                elemento.setPdfDni(dni);
//            } else {
//                Pdf u = pdfService.agregar(archivo, false);
//                Pdf dni = pdfDAO.saveAndFlush(u);
//                elemento.setPdfDni(dni);
//            }
//        }
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoComprobanteDAO.deleteByRetiroDeposito(elementoDAO.findById(elemento).get());
        elementoDAO.deleteById(elemento);
    }

    private RetiroDeposito formatearStrings(RetiroDeposito e) {
        e.setNumeroDocumento(e.getNumeroDocumento().trim());
        return e;
    }

}