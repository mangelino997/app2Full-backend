//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICompaniaSeguroDAO;
import ar.com.draimo.jitws.dao.ICompaniaSeguroPolizaDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IPdfDAO;
import ar.com.draimo.jitws.dto.InitCompaniaSeguroPolizaDTO;
import ar.com.draimo.jitws.model.CompaniaSeguroPoliza;
import ar.com.draimo.jitws.model.Pdf;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Servicio Compania Seguro Poliza
 *
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
    
    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Obtiene el siguiente id
    public InitCompaniaSeguroPolizaDTO inicializar(int idRol, int idSubpcion) {
        InitCompaniaSeguroPolizaDTO elemento = new InitCompaniaSeguroPolizaDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idSubpcion));
        elemento.setEmpresas(empresaDAO.findAll());
        elemento.setFecha(new Date(new java.util.Date().getTime()));
        elemento.setUltimoId(obtenerSiguienteId());
        return elemento;
    }

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        CompaniaSeguroPoliza elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<CompaniaSeguroPoliza> elementos= elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene la lista completa
    public Object obtenerPorId(int id) throws IOException {
        CompaniaSeguroPoliza elemento= elementoDAO.findById(id).get();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept();
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elemento);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por empresa
    public Object listarPorEmpresa(int idEmpresa) throws IOException {
        List<CompaniaSeguroPoliza> elementos=  elementoDAO.findByEmpresa(empresaDAO.findById(idEmpresa));
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroImagen", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por compania de seguro
    public Object listarPorCompaniaSeguro(int idCompaniaSeguro) throws IOException {
        List<CompaniaSeguroPoliza> elementos = elementoDAO.findByCompaniaSeguro(
                companiaSeguroDAO.findById(idCompaniaSeguro));
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene por compania de seguro y empresa
    public Object listarPorCompaniaSeguroYEmpresa(int idCompaniaSeguro, int idEmpresa) throws IOException {
        List<CompaniaSeguroPoliza> elementos =  elementoDAO.findByCompaniaSeguroAndEmpresa(
                companiaSeguroDAO.findById(idCompaniaSeguro), empresaDAO.findById(idEmpresa));
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por nombre de compania de seguro
    public Object listarPorCompaniaSeguroNombre(String nombre) throws IOException {
        List<CompaniaSeguroPoliza> elementos=  elementoDAO.findByCompaniaSeguro_NombreContaining(nombre);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public CompaniaSeguroPoliza agregar(String elementoString, MultipartFile archivo) throws IOException {
        CompaniaSeguroPoliza elemento = new ObjectMapper().readValue(elementoString, CompaniaSeguroPoliza.class);
        Pdf pdf = null;
        if(!archivo.getOriginalFilename().equals("")) {
            String nombre = elemento.getEmpresa().getAbreviatura() + "-" + elemento.getNumeroPoliza();
            pdf = pdfService.agregar(archivo, nombre, false);
            pdf.setTabla("companiaseguropoliza");
            pdf = pdfDAO.saveAndFlush(pdf);
            elemento.setPdf(pdf);
        } else {
            elemento.setPdf(null);
        }
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(CompaniaSeguroPoliza elemento) {
        CompaniaSeguroPoliza companiaSeguroPoliza = elementoDAO.findById(elemento.getId()).get();
        elemento = formatearStrings(elemento);
        elemento.setPdf(companiaSeguroPoliza.getPdf());
        elementoDAO.save(elemento);
    }
    
    //Actualiza un pdf
    @Transactional(rollbackFor = Exception.class)
    public Object actualizarPDF(int idCompaniaSeguroPoliza, int idPdf, MultipartFile archivo) throws IOException {
        CompaniaSeguroPoliza companiaSeguroPoliza = elementoDAO.findById(idCompaniaSeguroPoliza).get();
        String nombre = companiaSeguroPoliza.getEmpresa().getAbreviatura() + "-" + companiaSeguroPoliza.getNumeroPoliza();
        Pdf pdf;
        if(idPdf == 0) {
            pdf = pdfService.agregar(archivo, nombre, false);
            pdf.setTabla("companiaseguropoliza");
            pdf = pdfDAO.saveAndFlush(pdf);
        } else {
            pdf = pdfService.actualizar(idPdf, "companiaseguropoliza", archivo, nombre, false);
            pdf = pdfDAO.saveAndFlush(pdf);
        }
        companiaSeguroPoliza.setPdf(pdf);
        CompaniaSeguroPoliza csp = elementoDAO.saveAndFlush(companiaSeguroPoliza);
        CompaniaSeguroPoliza elemento = new CompaniaSeguroPoliza();
        elemento.setVersion(csp.getVersion());
        elemento.setPdf(pdf);
        return retornarObjeto(null, elemento);
    }
    
    //Elimina un pdf
    @Transactional(rollbackFor = Exception.class)
    public CompaniaSeguroPoliza eliminarPDF(int idCompaniaSeguroPoliza, int idPdf) {
        CompaniaSeguroPoliza companiaSeguroPoliza = elementoDAO.findById(idCompaniaSeguroPoliza).get();
        companiaSeguroPoliza.setPdf(null);
        CompaniaSeguroPoliza csp = elementoDAO.save(companiaSeguroPoliza);
        pdfDAO.deleteById(idPdf);
        return csp;
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private CompaniaSeguroPoliza formatearStrings(CompaniaSeguroPoliza elemento) {
        elemento.setNumeroPoliza(elemento.getNumeroPoliza().trim());
        return elemento;
    }
    
    //Convierte una lista o un elemento a object para retornar con filtros aplicados
    private Object retornarObjeto(List<CompaniaSeguroPoliza> elementos, CompaniaSeguroPoliza elemento) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = elementos != null ? SimpleBeanPropertyFilter
                .serializeAllExcept("datos") : SimpleBeanPropertyFilter
                .serializeAllExcept();
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos != null ? elementos : elemento);
        return mapper.readValue(string, Object.class);
    }

}