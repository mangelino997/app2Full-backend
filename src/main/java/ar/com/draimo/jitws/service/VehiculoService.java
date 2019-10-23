//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IConfiguracionVehiculoDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IMarcaVehiculoDAO;
import ar.com.draimo.jitws.dao.IPdfDAO;
import ar.com.draimo.jitws.dao.ITipoVehiculoDAO;
import ar.com.draimo.jitws.dao.IVehiculoDAO;
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

    //Define la referencia al dao tipo vehiculo
    @Autowired
    ITipoVehiculoDAO tipoVehiculoDAO;

    //Define la referencia al dao marca vehiculo
    @Autowired
    IMarcaVehiculoDAO marcaVehiculoDAO;

    //Define la referencia al dao configuracion vehiculo
    @Autowired
    IConfiguracionVehiculoDAO configuracionVehiculoDAO;

    //Define DAO de pdf
    @Autowired
    IPdfDAO pdfDAO;

    //Define service de pdf
    @Autowired
    PdfService pdfService;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Vehiculo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<Vehiculo> elementos = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene un registro por id
    public Object obtenerPorId(int id) throws IOException {
        Vehiculo elemento = elementoDAO.findById(id).get();
        //Establece un pdf vacio a aquellos atributos que no contengan pdfs
        if (elemento.getPdfTitulo() == null) {
            elemento.setPdfTitulo(new Pdf());
        }
        if (elemento.getPdfCedulaIdent() == null) {
            elemento.setPdfCedulaIdent(new Pdf());
        }
        if (elemento.getPdfHabBromat() == null) {
            elemento.setPdfHabBromat(new Pdf());
        }
        if (elemento.getPdfVtoInspTecnica() == null) {
            elemento.setPdfVtoInspTecnica(new Pdf());
        }
        if (elemento.getPdfVtoRuta() == null) {
            elemento.setPdfVtoRuta(new Pdf());
        }
        if (elemento.getPdfVtoSenasa() == null) {
            elemento.setPdfVtoSenasa(new Pdf());
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept();
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elemento);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por alias
    public Object listarPorAlias(String alias) throws IOException {
        List<Vehiculo> elementos = alias.equals("***") ? elementoDAO.findAll()
                : elementoDAO.findByAliasContainingOrderByAlias(alias);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por alias y empresa
    public Object listarPorAliasYEmpresa(String alias, int idEmpresa) throws IOException {
        Empresa empresa = empresaDAO.findById(idEmpresa).get();
        List<Vehiculo> elementos = alias.equals("***") ? elementoDAO.findByEmpresaOrderByAlias(empresa)
                : elementoDAO.findByAliasContainingAndEmpresaOrderByAlias(alias, empresa);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por alias y empresa
    public Object listarPorAliasFiltroEmpresaYFiltroRemolque(String alias, 
            boolean esRemolque, int idEmpresa) throws IOException {
        alias = alias.equals("***") ? "" : alias;
        List<Vehiculo> elementos = elementoDAO.listarPorAliasFiltroEmpresaYFiltroRemolque(
                alias, esRemolque, idEmpresa);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por empresa, tipo de vehiculo y marca de vehiculo
    public Object listarPorConfiguarionVehiculo(int idEmpresa, int idTipoVehiculo, 
            int idMarcaVehiculo) throws IOException {
        List<Vehiculo> elementos = elementoDAO.listarPorConfig(idEmpresa, idTipoVehiculo, idMarcaVehiculo);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Vehiculo agregar(String elementoString, MultipartFile titulo, MultipartFile cedulaIdent,
            MultipartFile vtoRuta, MultipartFile vtoInspTecnica, MultipartFile vtoSenasa,
            MultipartFile habBromat) throws IOException, Exception {
        Vehiculo elemento = new ObjectMapper().readValue(elementoString, Vehiculo.class);
        //Obtiene longitud de anio, si es mayor a 4 retorna error
        String anio = String.valueOf(elemento.getAnioFabricacion());
        if (anio.length() > 4 || anio.length() < 4) {
            throw new DataIntegrityViolationException(MensajeRespuesta.SHORT_INCORRECTO + " AÑO FABRICACIÓN");
        }
        Pdf pdfTitulo = null, pdfCI = null, pdfVtoRuta = null, pdfIT = null, pdfVtoSenasa = null, pdfHB = null;
        if (!"null".equals(titulo.getOriginalFilename())) {
            Pdf pTitulo = pdfService.agregar(titulo, false);
            pTitulo.setTabla("vehiculo");
            pdfTitulo = pdfDAO.saveAndFlush(pTitulo);
        }
        elemento.setPdfTitulo(pdfTitulo);
        if (!"null".equals(cedulaIdent.getOriginalFilename())) {
            Pdf pCedulaIden = pdfService.agregar(cedulaIdent, false);
            pCedulaIden.setTabla("vehiculo");
            pdfCI = pdfDAO.saveAndFlush(pCedulaIden);
        }
        elemento.setPdfCedulaIdent(pdfCI);
        if (!"null".equals(vtoRuta.getOriginalFilename())) {
            Pdf pVtoRuta = pdfService.agregar(vtoRuta, false);
            pVtoRuta.setTabla("vehiculo");
            pdfVtoRuta = pdfDAO.saveAndFlush(pVtoRuta);
        }
        elemento.setPdfVtoRuta(pdfVtoRuta);
        if (!"null".equals(vtoInspTecnica.getOriginalFilename())) {
            Pdf pVtoInspTecnica = pdfService.agregar(vtoInspTecnica, false);
            pVtoInspTecnica.setTabla("vehiculo");
            pdfIT = pdfDAO.saveAndFlush(pVtoInspTecnica);
        }
        elemento.setPdfVtoInspTecnica(pdfIT);
        if (!"null".equals(vtoSenasa.getOriginalFilename())) {
            Pdf pVtoSenasa = pdfService.agregar(vtoSenasa, false);
            pVtoSenasa.setTabla("vehiculo");
            pdfVtoSenasa = pdfDAO.saveAndFlush(pVtoSenasa);
        }
        elemento.setPdfVtoSenasa(pdfVtoSenasa);
        if (!"null".equals(habBromat.getOriginalFilename())) {
            Pdf pHabBromat = pdfService.agregar(habBromat, false);
            pHabBromat.setTabla("vehiculo");
            pdfHB = pdfDAO.saveAndFlush(pHabBromat);
        }
        elemento.setPdfHabBromat(pdfHB);
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public Vehiculo actualizar(String elementoString, MultipartFile titulo, MultipartFile cedulaIdent,
            MultipartFile vtoRuta, MultipartFile vtoInspTecnica, MultipartFile vtoSenasa,
            MultipartFile habBromat) throws IOException, Exception {
        Vehiculo elemento = new ObjectMapper().readValue(elementoString, Vehiculo.class);
        Vehiculo vehiculo = elementoDAO.findById(elemento.getId()).get();
        elemento.setFechaUltimaMod(new Date(new java.util.Date().getTime()));
        elemento = formatearStrings(elemento);
        //Obtiene longitud de anio, si es mayor a 4 retorna error
        String anio = String.valueOf(elemento.getAnioFabricacion());
        if (anio.length() > 4 || anio.length() < 4) {
            throw new DataIntegrityViolationException(MensajeRespuesta.SHORT_INCORRECTO + " AÑO FABRICACIÓN");
        }
        if ("null".equals(titulo.getOriginalFilename())) {
            if (vehiculo.getPdfTitulo() != null) {
                pdfDAO.deleteById(vehiculo.getPdfTitulo().getId());
            }
            elemento.setPdfTitulo(null);
        } else {
            Pdf pTitulo = vehiculo.getPdfTitulo() != null ? pdfService.actualizar(
                    vehiculo.getPdfTitulo().getId(), titulo, false)
                    : pdfService.agregar(titulo, false);
            pTitulo.setTabla("vehiculo");
            Pdf pdfTitulo = vehiculo.getPdfTitulo() != null ? pdfDAO.save(pTitulo)
                    : pdfDAO.saveAndFlush(pTitulo);
            elemento.setPdfTitulo(pdfTitulo);
        }
        if ("null".equals(cedulaIdent.getOriginalFilename())) {
            if (vehiculo.getPdfCedulaIdent() != null) {
                pdfDAO.deleteById(vehiculo.getPdfCedulaIdent().getId());
            }
            elemento.setPdfCedulaIdent(null);
        } else {
            Pdf pCedulaIden = vehiculo.getPdfCedulaIdent() != null ? pdfService.actualizar(
                    vehiculo.getPdfCedulaIdent().getId(), cedulaIdent, false)
                    : pdfService.agregar(cedulaIdent, false);
            pCedulaIden.setTabla("vehiculo");
            Pdf pdfCedulaIden = vehiculo.getPdfCedulaIdent() != null ? pdfDAO.save(pCedulaIden)
                    : pdfDAO.saveAndFlush(pCedulaIden);
            elemento.setPdfCedulaIdent(pdfCedulaIden);
        }
        if ("null".equals(vtoRuta.getOriginalFilename())) {
            if (vehiculo.getPdfVtoRuta() != null) {
                pdfDAO.deleteById(vehiculo.getPdfVtoRuta().getId());
            }
            elemento.setPdfVtoRuta(null);
        } else {
            Pdf pVtoRuta = vehiculo.getPdfVtoRuta() != null ? pdfService.actualizar(
                    vehiculo.getPdfVtoRuta().getId(), vtoRuta, false)
                    : pdfService.agregar(vtoRuta, false);
            pVtoRuta.setTabla("vehiculo");
            Pdf pdfVtoRuta = vehiculo.getPdfVtoRuta() != null ? pdfDAO.save(pVtoRuta)
                    : pdfDAO.saveAndFlush(pVtoRuta);
            elemento.setPdfVtoRuta(pdfVtoRuta);
        }
        if ("null".equals(vtoInspTecnica.getOriginalFilename())) {
            if (vehiculo.getPdfVtoInspTecnica() != null) {
                pdfDAO.deleteById(vehiculo.getPdfVtoInspTecnica().getId());
            }
            elemento.setPdfVtoInspTecnica(null);
        } else {
            Pdf pVtoInspTecnica = vehiculo.getPdfVtoInspTecnica() != null
                    ? pdfService.actualizar(vehiculo.getPdfVtoInspTecnica().getId(), vtoInspTecnica, false)
                    : pdfService.agregar(vtoInspTecnica, false);
            pVtoInspTecnica.setTabla("vehiculo");
            Pdf pdfVtoInspTecnica = vehiculo.getPdfVtoInspTecnica() != null
                    ? pdfDAO.save(pVtoInspTecnica) : pdfDAO.saveAndFlush(pVtoInspTecnica);
            elemento.setPdfVtoInspTecnica(pdfVtoInspTecnica);
        }
        if ("null".equals(vtoSenasa.getOriginalFilename())) {
            if (vehiculo.getPdfVtoSenasa() != null) {
                pdfDAO.deleteById(vehiculo.getPdfVtoSenasa().getId());
            }
            elemento.setPdfVtoSenasa(null);
        } else {
            Pdf pVtoSenasa = vehiculo.getPdfVtoSenasa() != null ? pdfService.actualizar(
                    vehiculo.getPdfVtoSenasa().getId(), vtoSenasa, false)
                    : pdfService.agregar(vtoSenasa, false);
            pVtoSenasa.setTabla("vehiculo");
            Pdf pdfVtoSenasa = vehiculo.getPdfVtoSenasa() != null
                    ? pdfDAO.save(pVtoSenasa) : pdfDAO.saveAndFlush(pVtoSenasa);
            elemento.setPdfVtoSenasa(pdfVtoSenasa);

        }
        if ("null".equals(habBromat.getOriginalFilename())) {
            if (vehiculo.getPdfHabBromat() != null) {
                pdfDAO.deleteById(vehiculo.getPdfHabBromat().getId());
            }
            elemento.setPdfHabBromat(null);
        } else {
            Pdf pHabBromat = vehiculo.getPdfHabBromat() != null ? pdfService.actualizar(
                    vehiculo.getPdfHabBromat().getId(), habBromat, false)
                    : pdfService.agregar(habBromat, false);
            pHabBromat.setTabla("vehiculo");
            Pdf pdfHabBromat = vehiculo.getPdfHabBromat() != null
                    ? pdfDAO.save(pHabBromat) : pdfDAO.saveAndFlush(pHabBromat);
            elemento.setPdfHabBromat(pdfHabBromat);
        }
        return establecerAlias(elemento);
    }

    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public Vehiculo establecerAlias(Vehiculo elemento) throws IOException {
        Empresa e = empresaDAO.findById(elemento.getEmpresa().getId()).get();
        ConfiguracionVehiculo cv = configuracionVehiculoDAO.findById(
                elemento.getConfiguracionVehiculo().getId()).get();
        String nInterno=elemento.getNumeroInterno() != null?elemento.getNumeroInterno():"";
        elemento.setAlias(elemento.getDominio() + " - " + nInterno + " - "
                + e.getRazonSocial() + " - " + cv.getTipoVehiculo().getNombre() + " - "
                + cv.getMarcaVehiculo().getNombre());
        return elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private Vehiculo formatearStrings(Vehiculo elemento) {
        elemento.setDominio(elemento.getDominio().trim().toUpperCase());
        if (elemento.getNumeroInterno() != null) {
            elemento.setNumeroInterno(elemento.getNumeroInterno().trim());
        }
        if (elemento.getNumeroMotor() != null) {
            elemento.setNumeroMotor(elemento.getNumeroMotor().trim());
        }
        if (elemento.getNumeroChasis() != null) {
            elemento.setNumeroChasis(elemento.getNumeroChasis().trim());
        }
        elemento.setNumeroRuta(elemento.getNumeroRuta().trim());
        return elemento;
    }

}