package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICompaniaSeguroDAO;
import ar.com.draimo.jitws.dao.ICompaniaSeguroPolizaDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IPdfDAO;
import ar.com.draimo.jitws.model.CompaniaSeguro;
import ar.com.draimo.jitws.model.CompaniaSeguroPoliza;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.Pdf;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Servicio Compania Seguro Poliza
 * @author blas
 */

@Service
public class CompaniaSeguroPolizaService {
    
    //Define la referencia al dao
    @Autowired
    ICompaniaSeguroPolizaDAO elementoDAO;
    
    //Define la referencia al dao empresa
    @Autowired
    IEmpresaDAO empresaDAO;
    
    //Define la referencia al dao compania seguro
    @Autowired
    ICompaniaSeguroDAO companiaSeguroDAO;
    
    //Define la referencia al dao pdf
    @Autowired
    IPdfDAO pdfDAO;
    
    //Define la referencia al service pdf
    @Autowired
    PdfService pdfService;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        CompaniaSeguroPoliza elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<CompaniaSeguroPoliza> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por empresa
    public List<CompaniaSeguroPoliza> listarPorEmpresa(int id) {
        Optional<Empresa> empresa = empresaDAO.findById(id);
        return elementoDAO.findByEmpresa(empresa);
    }
    
    //Obtiene una lista por compania de seguro
    public List<CompaniaSeguroPoliza> listarPorCompaniaSeguro(int idCompaniaSeguro) {
        Optional<CompaniaSeguro> companiaSeguro = companiaSeguroDAO.findById(idCompaniaSeguro);
        return elementoDAO.findByCompaniaSeguro(companiaSeguro);
    }
    
    //Obtiene por compania de seguro y empresa
    public List<CompaniaSeguroPoliza> listarPorCompaniaSeguroYEmpresa(int idCompaniaSeguro, int idEmpresa) {
        Optional<CompaniaSeguro> companiaSeguro = companiaSeguroDAO.findById(idCompaniaSeguro);
        Optional<Empresa> empresa = empresaDAO.findById(idEmpresa);
        return elementoDAO.findByCompaniaSeguroAndEmpresa(companiaSeguro, empresa);
    }
    
    //Obtiene una lista por nombre de compania de seguro
    public List<CompaniaSeguroPoliza> listarPorCompaniaSeguroNombre(String nombre) {
        return elementoDAO.findByCompaniaSeguro_NombreContaining(nombre);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public CompaniaSeguroPoliza agregar(String elementoString, MultipartFile archivo) throws IOException {
        CompaniaSeguroPoliza elemento = new ObjectMapper().readValue(elementoString, CompaniaSeguroPoliza.class);
        if (!archivo.getName().equals("")) {
            Pdf u = pdfService.agregar(archivo, false);
            u.setTabla("companiaseguropoliza");
            Pdf pdf = pdfDAO.saveAndFlush(u);
            elemento.setPdf(pdf);
        }
        return elementoDAO.saveAndFlush(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(String elementoString,MultipartFile archivo) throws IOException {
        CompaniaSeguroPoliza elemento = new ObjectMapper().readValue(elementoString, CompaniaSeguroPoliza.class);
        elemento = formatearStrings(elemento);
        if(!archivo.getName().equals("")){
            Pdf f = pdfService.actualizar(elemento.getPdf().getId(), archivo, false);
            f.setTabla("companiaseguropoliza");
            Pdf bug = pdfDAO.save(f);
            elemento.setPdf(bug);
        }
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(CompaniaSeguroPoliza elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private CompaniaSeguroPoliza formatearStrings(CompaniaSeguroPoliza elemento) {
        elemento.setNumeroPoliza(elemento.getNumeroPoliza().trim());
        return elemento;
    }
    
}
