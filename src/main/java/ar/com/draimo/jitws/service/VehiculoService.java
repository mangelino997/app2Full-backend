//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICompaniaSeguroDAO;
import ar.com.draimo.jitws.dao.IConfiguracionVehiculoDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IMarcaVehiculoDAO;
import ar.com.draimo.jitws.dao.IPdfDAO;
import ar.com.draimo.jitws.dao.ITipoVehiculoDAO;
import ar.com.draimo.jitws.dao.IVehiculoDAO;
import ar.com.draimo.jitws.dto.InitVehiculoDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.ConfiguracionVehiculo;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.Pdf;
import ar.com.draimo.jitws.model.Vehiculo;
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
import org.springframework.web.multipart.MultipartFile;

/**
 * Servicio Vehiculo
 *
 * @author blas
 */
@Service
public class VehiculoService {

    //Define la referencia al dao
    @Autowired
    IVehiculoDAO elementoDAO;

    //Define la referencia al dao empresa
    @Autowired
    IEmpresaDAO empresaDAO;

    //Define la referencia al dao configuracion vehiculo
    @Autowired
    IConfiguracionVehiculoDAO configuracionVehiculoDAO;

    //Define DAO de pdf
    @Autowired
    IPdfDAO pdfDAO;

    //Define service de pdf
    @Autowired
    PdfService pdfService;

    //Define la referencia al dao tipo vehiculo
    @Autowired
    ITipoVehiculoDAO tipoVehiculoDAO;

    //Define la referencia al dao marca vehiculo
    @Autowired
    IMarcaVehiculoDAO marcaVehiculoDAO;

    //Define la subopcion pestania service
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Define la companiaSeguroDAO
    @Autowired
    ICompaniaSeguroDAO companiaSeguroDAO;

    //Obtiene la lista completa
    public InitVehiculoDTO inicializar(int rol, int subopcion, int idEmpresa) {
        InitVehiculoDTO p = new InitVehiculoDTO();
        p.setMarcaVehiculos(marcaVehiculoDAO.findAll());
        p.setTipoVehiculos(tipoVehiculoDAO.findAll());
        p.setUltimoId(obtenerSiguienteId());
        p.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(rol, subopcion));
        p.setEmpresas(empresaDAO.findAll());
        p.setCompaniasSeguros(companiaSeguroDAO.listarPorEmpresa(idEmpresa));
        return p;
    }

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Vehiculo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<Vehiculo> elementos = elementoDAO.findAll();
        return retornarObjeto(elementos, null);
    }

    //Obtiene un registro por id
    public Object obtenerPorId(int id) throws IOException {
        Vehiculo elemento = elementoDAO.findById(id).get();
        //Establece un pdf vacio a aquellos atributos que no contengan pdfs
        Pdf pdf = new Pdf();
        if (elemento.getPdfTitulo() == null) {
            elemento.setPdfTitulo(pdf);
        }
        if (elemento.getPdfCedulaIdent() == null) {
            elemento.setPdfCedulaIdent(pdf);
        }
        if (elemento.getPdfHabBromat() == null) {
            elemento.setPdfHabBromat(pdf);
        }
        if (elemento.getPdfVtoInspTecnica() == null) {
            elemento.setPdfVtoInspTecnica(pdf);
        }
        if (elemento.getPdfVtoRuta() == null) {
            elemento.setPdfVtoRuta(pdf);
        }
        if (elemento.getPdfVtoSenasa() == null) {
            elemento.setPdfVtoSenasa(pdf);
        }
        return retornarObjeto(null, elemento);
    }

    //Obtiene una lista por alias
    public Object listarPorAlias(String alias) throws IOException {
        List<Vehiculo> elementos = alias.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByAliasContainingOrderByAlias(alias);
        return retornarObjeto(elementos, null);
    }

    //Obtiene una lista por alias y empresa
    public Object listarPorAliasYEmpresa(String alias, int idEmpresa) throws IOException {
        Empresa empresa = empresaDAO.findById(idEmpresa).get();
        List<Vehiculo> elementos = alias.equals("*") ? elementoDAO.findByEmpresaOrderByAlias(empresa)
                : elementoDAO.findByAliasContainingAndEmpresaOrderByAlias(alias, empresa);
        return retornarObjeto(elementos, null);
    }

    //Obtiene una lista por alias y empresa
    public Object listarPorAliasFiltroEmpresaYFiltroRemolque(String alias,
            boolean esRemolque, int idEmpresa) throws IOException {
        alias = alias.equals("*") ? "" : alias;
        List<Vehiculo> elementos = elementoDAO.listarPorAliasFiltroEmpresaYFiltroRemolque(
                alias, esRemolque, idEmpresa);
        return retornarObjeto(elementos, null);
    }

    //Obtiene una lista por empresa, tipo de vehiculo y marca de vehiculo
    public Object listarPorConfiguarionVehiculo(int idEmpresa, int idTipoVehiculo,
            int idMarcaVehiculo) throws IOException {
        List<Vehiculo> elementos = elementoDAO.listarPorConfig(idEmpresa, idTipoVehiculo, idMarcaVehiculo);
        return retornarObjeto(elementos, null);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Vehiculo agregar(String elementoString, MultipartFile titulo, MultipartFile cedulaIdent,
            MultipartFile vtoRuta, MultipartFile vtoInspTecnica, MultipartFile vtoSenasa,
            MultipartFile habBromat) throws IOException, Exception {
        Vehiculo elemento = new ObjectMapper().readValue(elementoString, Vehiculo.class);
        controlarLongitud(elemento);
        elemento = formatearStrings(elemento);
        elemento.setPdfTitulo(establecerPdf(titulo, elemento.getDominio() + "-TITULO", null, null));
        elemento.setPdfCedulaIdent(establecerPdf(cedulaIdent, elemento.getDominio() + "-CEDULA", null, null));
        elemento.setPdfVtoRuta(establecerPdf(vtoRuta, elemento.getDominio() + "-VTORUTA", null, null));
        elemento.setPdfVtoInspTecnica(establecerPdf(vtoInspTecnica, elemento.getDominio() + "-VTOTECNICA", null, null));
        elemento.setPdfVtoSenasa(establecerPdf(vtoSenasa, elemento.getDominio() + "-VTOSENASA", null, null));
        elemento.setPdfHabBromat(establecerPdf(habBromat, elemento.getDominio() + "-VTOBROMATOLOGICA", null, null));
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public Vehiculo actualizar(String elementoString, MultipartFile titulo, MultipartFile cedulaIdent,
            MultipartFile vtoRuta, MultipartFile vtoInspTecnica, MultipartFile vtoSenasa,
            MultipartFile habBromat) throws IOException, Exception {
        Vehiculo elemento = new ObjectMapper().readValue(elementoString, Vehiculo.class);
        Vehiculo vehiculo = elementoDAO.findById(elemento.getId()).get();
        controlarLongitud(elemento);
        elemento.setFechaUltimaMod(new Date(new java.util.Date().getTime()));
        elemento = formatearStrings(elemento);
        List<Integer> lista = new ArrayList<>();
        elemento.setPdfTitulo(establecerPdf(titulo, elemento.getDominio() + "-TITULO", vehiculo, vehiculo.getPdfTitulo()));
        if(elemento.getPdfTitulo() != null && elemento.getPdfTitulo().getId() < 0) {
            lista.add(elemento.getPdfTitulo().getId()*-1);
            elemento.setPdfTitulo(null);
        }
        elemento.setPdfCedulaIdent(establecerPdf(cedulaIdent, elemento.getDominio() + "-CEDULA", vehiculo, vehiculo.getPdfCedulaIdent()));
        if(elemento.getPdfCedulaIdent() != null && elemento.getPdfCedulaIdent().getId() < 0) {
            lista.add(elemento.getPdfCedulaIdent().getId()*-1);
            elemento.setPdfCedulaIdent(null);
        }
        elemento.setPdfVtoRuta(establecerPdf(vtoRuta, elemento.getDominio() + "-VTORUTA", vehiculo, vehiculo.getPdfVtoRuta()));
        if(elemento.getPdfVtoRuta() != null && elemento.getPdfVtoRuta().getId() < 0) {
            lista.add(elemento.getPdfVtoRuta().getId()*-1);
            elemento.setPdfVtoRuta(null);
        }
        elemento.setPdfVtoInspTecnica(establecerPdf(vtoInspTecnica, elemento.getDominio() + "-VTOTECNICA", vehiculo, vehiculo.getPdfVtoInspTecnica()));
        if(elemento.getPdfVtoInspTecnica() != null && elemento.getPdfVtoInspTecnica().getId() < 0) {
            lista.add(elemento.getPdfVtoInspTecnica().getId()*-1);
            elemento.setPdfVtoInspTecnica(null);
        }
        elemento.setPdfVtoSenasa(establecerPdf(vtoSenasa, elemento.getDominio() + "-VTOSENASA", vehiculo, vehiculo.getPdfVtoSenasa()));
        if(elemento.getPdfVtoSenasa() != null && elemento.getPdfVtoSenasa().getId() < 0) {
            lista.add(elemento.getPdfVtoSenasa().getId()*-1);
            elemento.setPdfVtoSenasa(null);
        }
        elemento.setPdfHabBromat(establecerPdf(habBromat, elemento.getDominio() + "-VTOBROMATOLOGICA", vehiculo, vehiculo.getPdfHabBromat()));
        if(elemento.getPdfHabBromat() != null && elemento.getPdfHabBromat().getId() < 0) {
            lista.add(elemento.getPdfHabBromat().getId()*-1);
            elemento.setPdfHabBromat(null);
        }
        elemento = establecerAlias(elemento);
        for(int i = 0 ; i < lista.size() ; i++) {
            pdfDAO.deleteById(lista.get(i));
        }
        return elemento;
    }
    
    //Establece el valor a cada pdf dependiendo su condicion
    private Pdf establecerPdf(MultipartFile elemento, String nombre, Vehiculo vehiculo, Pdf pdfVehiculo) throws IOException {
        Pdf pdf = null;
        if(vehiculo == null) {
            if (!"null".equals(elemento.getOriginalFilename())) {
                pdf = pdfService.agregar(elemento, nombre, false);
                pdf.setTabla("vehiculo");
                pdf = pdfDAO.saveAndFlush(pdf);
            } else {
                pdf = null;
            }
        } else {
            if ("".equals(elemento.getOriginalFilename()) || "null".equals(elemento.getOriginalFilename())) {
                if(pdfVehiculo != null) {
                    pdf = new Pdf();
                    pdf.setId(pdfVehiculo.getId()*-1);
                }
            } else {
                pdf = pdfVehiculo != null ? 
                        pdfService.actualizar(pdfVehiculo.getId(), elemento, nombre, false) 
                        : pdfService.agregar(elemento, nombre, false);
                pdf.setTabla("vehiculo");
                pdf = pdfVehiculo != null ? 
                        pdfDAO.save(pdf) : pdfDAO.saveAndFlush(pdf);
            }
        }
        return pdf;
    }

    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public Vehiculo establecerAlias(Vehiculo elemento) {
        ConfiguracionVehiculo cv = configuracionVehiculoDAO.findById(
                elemento.getConfiguracionVehiculo().getId()).get();
        String nInterno = elemento.getNumeroInterno() != null ? elemento.getNumeroInterno() : "";
        elemento.setAlias(elemento.getDominio() + " - " + nInterno + " - "
                + " - " + cv.getTipoVehiculo().getNombre() + " - "
                + cv.getMarcaVehiculo().getNombre());
        return elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        Vehiculo vehiculo = elementoDAO.findById(id).get();
        elementoDAO.deleteById(id);
        if(vehiculo.getPdfTitulo() != null) {
            pdfDAO.deleteById(vehiculo.getPdfTitulo().getId());
        }
        if(vehiculo.getPdfCedulaIdent() != null) {
            pdfDAO.deleteById(vehiculo.getPdfCedulaIdent().getId());
        }
        if(vehiculo.getPdfVtoRuta() != null) {
            pdfDAO.deleteById(vehiculo.getPdfVtoRuta().getId());
        }
        if(vehiculo.getPdfVtoInspTecnica() != null) {
            pdfDAO.deleteById(vehiculo.getPdfVtoInspTecnica().getId());
        }
        if(vehiculo.getPdfVtoSenasa() != null) {
            pdfDAO.deleteById(vehiculo.getPdfVtoSenasa().getId());
        }
        if(vehiculo.getPdfHabBromat() != null) {
            pdfDAO.deleteById(vehiculo.getPdfHabBromat().getId());
        } 
    }

    //Formatea los strings
    private Vehiculo formatearStrings(Vehiculo elemento) {
        elemento.setDominio(elemento.getDominio().trim().toUpperCase());
        if (elemento.getNumeroInterno() != null) {
            elemento.setNumeroInterno(elemento.getNumeroInterno().trim().toUpperCase());
        }
        if (elemento.getNumeroMotor() != null) {
            elemento.setNumeroMotor(elemento.getNumeroMotor().trim().toUpperCase());
        }
        if (elemento.getNumeroChasis() != null) {
            elemento.setNumeroChasis(elemento.getNumeroChasis().trim().toUpperCase());
        }
        elemento.setNumeroRuta(elemento.getNumeroRuta().trim());
        return elemento;
    }

    //Controla la longitud de los atributos de tipo short
    private void controlarLongitud(Vehiculo elemento) {
        //Obtiene longitud de anio, si es mayor a 4 retorna error
        String anio = String.valueOf(elemento.getAnioFabricacion());
        if (anio.length() > 4 || anio.length() < 4) {
            throw new DataIntegrityViolationException(MensajeRespuesta.SHORT_INCORRECTO + " AÑO FABRICACIÓN");
        }
    }

    //Convierte una lista o un elemento a object para retornar con filtros aplicados
    private Object retornarObjeto(List<Vehiculo> elementos, Vehiculo elemento) throws IOException {
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
