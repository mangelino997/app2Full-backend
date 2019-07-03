package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICompaniaSeguroDAO;
import ar.com.draimo.jitws.dao.ICompaniaSeguroPolizaDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IPdfDAO;
import ar.com.draimo.jitws.model.CompaniaSeguroPoliza;
import ar.com.draimo.jitws.model.Pdf;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
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

    //Obtiene una lista por empresa
    public Object listarPorEmpresa(int id) throws IOException {
        List<CompaniaSeguroPoliza> elementos=  elementoDAO.findByEmpresa(empresaDAO.findById(id));
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
        if (!archivo.getOriginalFilename().equals("")) {
            Pdf u = pdfService.agregar(archivo, false);
            u.setTabla("companiaseguropoliza");
            Pdf pdf = pdfDAO.saveAndFlush(u);
            elemento.setPdf(pdf);
        } else {
            elemento.setPdf(null);
        }
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(String elementoString, MultipartFile archivo) throws IOException {
        CompaniaSeguroPoliza elemento = new ObjectMapper().readValue(elementoString, CompaniaSeguroPoliza.class);
        elemento = formatearStrings(elemento);
        if (archivo.getOriginalFilename().equals("")) {
            if(elemento.getPdf().getId() != 0) {
                pdfDAO.deleteById(elemento.getPdf().getId());
                elemento.setPdf(null);
            } else {
                elemento.setPdf(null);
            }
        } else {
            if(elemento.getPdf().getId() != 0) {
                Pdf f = pdfService.actualizar(elemento.getPdf().getId(), archivo, false);
                f.setTabla("companiaseguropoliza");
                Pdf bug = pdfDAO.save(f);
                elemento.setPdf(bug);
            } else {
                Pdf u = pdfService.agregar(archivo, false);
                u.setTabla("companiaseguropoliza");
                Pdf pdf = pdfDAO.saveAndFlush(u);
                elemento.setPdf(pdf);
            }
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