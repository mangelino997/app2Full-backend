package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IPdfDAO;
import ar.com.draimo.jitws.dao.IRetiroDepositoComprobanteDAO;
import ar.com.draimo.jitws.dao.IRetiroDepositoDAO;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.Pdf;
import ar.com.draimo.jitws.model.RetiroDeposito;
import ar.com.draimo.jitws.model.RetiroDepositoComprobante;
import ar.com.draimo.jitws.model.Soporte;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Servicio RetiroDeposito
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
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        RetiroDeposito elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<RetiroDeposito> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene la lista de planillas abiertas
    public List<RetiroDeposito> listarPorEstaCerrada(boolean estaCerrada) {
        return elementoDAO.listarPorEstaCerrada(estaCerrada);
    }
    
    //Cierra un reparto
    public boolean cerrarReparto(int idRetiroDeposito) {
        RetiroDeposito r = elementoDAO.findById(idRetiroDeposito).get();
        List<RetiroDepositoComprobante> c = elementoComprobanteDAO.findByRetiroDeposito(r);
        if (c.isEmpty()) {
            return false;
        }else {
            r.setEstaCerrada(true);
            elementoDAO.save(r);
            return true;
        }
    }
    
    //Obtiene una lista por empresa
    public List<RetiroDeposito> listarPorEmpresa(int id) {
        Optional<Empresa> elemento = empresaDAO.findById(id);
        return elementoDAO.findByEmpresa(elemento);
    }
    
    //Obtiene por numeroDocumento
    public List<RetiroDeposito> obtenerPorNumeroDocumento(String elemento) {
        return elementoDAO.findByNumeroDocumento(elemento);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public RetiroDeposito agregar(String retiroString, MultipartFile archivo) throws IOException {
        RetiroDeposito elemento = new ObjectMapper().readValue(retiroString, RetiroDeposito.class);
//        elemento = formatearStrings(elemento);
//        if (!archivo.getOriginalFilename().equals("")) {
//            Pdf u = pdfService.agregar(archivo, false);
//            Pdf pdf = pdfDAO.saveAndFlush(u);
//            elemento.setPdfDni(pdf);
//        } else {
//            elemento.setPdfDni(null);
//        }
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
        elementoDAO.deleteById(elemento);
    }
    
    private RetiroDeposito formatearStrings(RetiroDeposito e) {
        e.setNumeroDocumento(e.getNumeroDocumento().trim());
        return e;
    }

}
