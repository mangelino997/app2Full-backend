package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IMarcaVehiculoDAO;
import ar.com.draimo.jitws.dao.IPdfDAO;
import ar.com.draimo.jitws.dao.ITipoVehiculoDAO;
import ar.com.draimo.jitws.dao.IVehiculoDAO;
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
        if (elemento.getPdfTitulo()==null) {
            elemento.setPdfTitulo(new Pdf());
        }
        if (elemento.getPdfCedulaIdent()==null) {
            elemento.setPdfCedulaIdent(new Pdf());
        }
        if (elemento.getPdfHabBromat()==null) {
            elemento.setPdfHabBromat(new Pdf());
        }
        if (elemento.getPdfVtoInspTecnica()==null) {
            elemento.setPdfVtoInspTecnica(new Pdf());
        }
        if (elemento.getPdfVtoRuta()==null) {
            elemento.setPdfVtoRuta(new Pdf());
        }
        if (elemento.getPdfVtoSenasa()==null) {
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
        List<Vehiculo> elementos;
        if (alias.equals("***")) {
            elementos = elementoDAO.findAll();
        } else {
            elementos = elementoDAO.findByAliasContaining(alias);
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por alias filtrado por no remolque
    public Object listarPorAliasYRemolqueFalse(String alias) throws IOException {
        List<Vehiculo> elementos = elementoDAO.findByAliasContainingAndConfiguracionVehiculo_TipoVehiculo_EsRemolqueFalse(alias);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por alias filtrado por remolque
    public Object listarPorAliasYRemolqueTrue(String alias) throws IOException {
        List<Vehiculo> elementos = elementoDAO.findByAliasContainingAndConfiguracionVehiculo_TipoVehiculo_EsRemolqueTrue(alias);
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
    public Object listarFiltro(int idEmpresa, int idTipoVehiculo, int idMarcaVehiculo) throws IOException {
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
        elemento = formatearStrings(elemento);
        //Obtiene longitud de anio, si es mayor a 4 retorna error
        String anio = String.valueOf(elemento.getAnioFabricacion());
        if (anio.length()>4) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en AÑO FABRICACIÓN");
        }
        if (!titulo.getOriginalFilename().equals("")) {
            Pdf pTitulo = pdfService.agregar(titulo, false);
            pTitulo.setTabla("vehiculo");
            Pdf pdfTitulo = pdfDAO.saveAndFlush(pTitulo);
            elemento.setPdfTitulo(pdfTitulo);
        } else {
            elemento.setPdfTitulo(null);
        }
        if (!cedulaIdent.getOriginalFilename().equals("")) {
            Pdf pCedulaIden = pdfService.agregar(cedulaIdent, false);
            pCedulaIden.setTabla("vehiculo");
            Pdf pdfCedulaIden = pdfDAO.saveAndFlush(pCedulaIden);
            elemento.setPdfCedulaIdent(pdfCedulaIden);
        } else {
            elemento.setPdfCedulaIdent(null);
        }
        if (!vtoRuta.getOriginalFilename().equals("")) {
            Pdf pVtoRuta = pdfService.agregar(vtoRuta, false);
            pVtoRuta.setTabla("vehiculo");
            Pdf pdfVtoRuta = pdfDAO.saveAndFlush(pVtoRuta);
            elemento.setPdfVtoRuta(pdfVtoRuta);
        } else {
            elemento.setPdfVtoRuta(null);
        }
        if (!vtoInspTecnica.getOriginalFilename().equals("")) {
            Pdf pVtoInspTecnica = pdfService.agregar(vtoInspTecnica, false);
            pVtoInspTecnica.setTabla("vehiculo");
            Pdf pdfVtoInspTecnica = pdfDAO.saveAndFlush(pVtoInspTecnica);
            elemento.setPdfVtoInspTecnica(pdfVtoInspTecnica);
        } else {
            elemento.setPdfVtoInspTecnica(null);
        }
        if (!vtoSenasa.getOriginalFilename().equals("")) {
            Pdf pVtoSenasa = pdfService.agregar(vtoSenasa, false);
            pVtoSenasa.setTabla("vehiculo");
            Pdf pdfVtoSenasa = pdfDAO.saveAndFlush(pVtoSenasa);
            elemento.setPdfVtoSenasa(pdfVtoSenasa);
        } else {
            elemento.setPdfVtoSenasa(null);
        }
        if (!habBromat.getOriginalFilename().equals("")) {
            Pdf pHabBromat = pdfService.agregar(habBromat, false);
            pHabBromat.setTabla("vehiculo");
            Pdf pdfHabBromat = pdfDAO.saveAndFlush(pHabBromat);
            elemento.setPdfHabBromat(pdfHabBromat);
        } else {
            elemento.setPdfHabBromat(null);
        }
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        return elementoDAO.saveAndFlush(elemento);
    }

    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public void establecerAlias(Vehiculo elemento) throws IOException {
        elemento.setAlias(elemento.getDominio() + " - "
                + elemento.getConfiguracionVehiculo().getTipoVehiculo().getNombre() + " - "
                + elemento.getConfiguracionVehiculo().getMarcaVehiculo().getNombre());
        elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(String elementoString, MultipartFile titulo, MultipartFile cedulaIdent,
            MultipartFile vtoRuta, MultipartFile vtoInspTecnica, MultipartFile vtoSenasa,
            MultipartFile habBromat) throws IOException, Exception {
        Vehiculo elemento = new ObjectMapper().readValue(elementoString, Vehiculo.class);
        Vehiculo vehiculo = elementoDAO.findById(elemento.getId()).get();
        elemento.setFechaUltimaMod(new Date(new java.util.Date().getTime()));
        elemento = formatearStrings(elemento);
        //Obtiene longitud de anio, si es mayor a 4 retorna error
        String anio = String.valueOf(elemento.getAnioFabricacion());
        if (anio.length()>4) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en AÑO FABRICACIÓN");
        }
        if (titulo.getOriginalFilename().equals("")) {
            if (vehiculo.getPdfTitulo() != null) {
                pdfDAO.deleteById(vehiculo.getPdfTitulo().getId());
                elemento.setPdfTitulo(null);
            } else {
                elemento.setPdfTitulo(null);
            }
        } else {
            if (vehiculo.getPdfTitulo() != null) {
                Pdf pTitulo = pdfService.actualizar(vehiculo.getPdfTitulo().getId(), titulo, false);
                pTitulo.setTabla("vehiculo");
                Pdf pdfTitulo = pdfDAO.save(pTitulo);
                elemento.setPdfTitulo(pdfTitulo);
            } else {
                Pdf u = pdfService.agregar(titulo, false);
                u.setTabla("vehiculo");
                Pdf pdf1 = pdfDAO.saveAndFlush(u);
                elemento.setPdfTitulo(pdf1);
            }
        }
        if (cedulaIdent.getOriginalFilename().equals("")) {
            if (vehiculo.getPdfCedulaIdent() != null) {
                pdfDAO.deleteById(vehiculo.getPdfCedulaIdent().getId());
                elemento.setPdfCedulaIdent(null);
            } else {
                elemento.setPdfCedulaIdent(null);
            }
        } else {
            if (vehiculo.getPdfTitulo() != null) {
                Pdf pCedulaIden = pdfService.actualizar(vehiculo.getPdfCedulaIdent().getId(), cedulaIdent, false);
                pCedulaIden.setTabla("vehiculo");
                Pdf pdfCedulaIden = pdfDAO.save(pCedulaIden);
                elemento.setPdfCedulaIdent(pdfCedulaIden);
            } else {
                Pdf u = pdfService.agregar(cedulaIdent, false);
                u.setTabla("vehiculo");
                Pdf pdf2 = pdfDAO.saveAndFlush(u);
                elemento.setPdfCedulaIdent(pdf2);
            }
        }
        if (vtoRuta.getOriginalFilename().equals("")) {
            if (vehiculo.getPdfVtoRuta() != null) {
                pdfDAO.deleteById(vehiculo.getPdfVtoRuta().getId());
                elemento.setPdfVtoRuta(null);
            } else {
                elemento.setPdfVtoRuta(null);
            }
        } else {
            if (vehiculo.getPdfTitulo() != null) {
                Pdf pVtoRuta = pdfService.actualizar(vehiculo.getPdfVtoRuta().getId(), vtoRuta, false);
                pVtoRuta.setTabla("vehiculo");
                Pdf pdfVtoRuta = pdfDAO.save(pVtoRuta);
                elemento.setPdfVtoRuta(pdfVtoRuta);
            } else {
                Pdf u = pdfService.agregar(vtoRuta, false);
                u.setTabla("vehiculo");
                Pdf pdf3 = pdfDAO.saveAndFlush(u);
                elemento.setPdfVtoRuta(pdf3);
            }
        }
        if (vtoInspTecnica.getOriginalFilename().equals("")) {
            if (vehiculo.getPdfVtoInspTecnica() != null) {
                pdfDAO.deleteById(vehiculo.getPdfVtoInspTecnica().getId());
                elemento.setPdfVtoInspTecnica(null);
            } else {
                elemento.setPdfVtoInspTecnica(null);
            }
        } else {
            if (vehiculo.getPdfTitulo() != null) {
                Pdf pVtoInspTecnica = pdfService.actualizar(vehiculo.getPdfVtoInspTecnica().getId(), vtoInspTecnica, false);
                pVtoInspTecnica.setTabla("vehiculo");
                Pdf pdfVtoInspTecnica = pdfDAO.save(pVtoInspTecnica);
                elemento.setPdfVtoInspTecnica(pdfVtoInspTecnica);
            } else {
                Pdf u = pdfService.agregar(vtoInspTecnica, false);
                u.setTabla("vehiculo");
                Pdf pdf4 = pdfDAO.saveAndFlush(u);
                elemento.setPdfVtoInspTecnica(pdf4);
            }
        }
        if (vtoSenasa.getOriginalFilename().equals("")) {
            if (vehiculo.getPdfVtoSenasa() != null) {
                pdfDAO.deleteById(vehiculo.getPdfVtoSenasa().getId());
                elemento.setPdfVtoSenasa(null);
            } else {
                elemento.setPdfVtoSenasa(null);
            }
        } else {
            if (vehiculo.getPdfTitulo() != null) {
                Pdf pVtoSenasa = pdfService.actualizar(vehiculo.getPdfVtoSenasa().getId(), vtoSenasa, false);
                pVtoSenasa.setTabla("vehiculo");
                Pdf pdfVtoSenasa = pdfDAO.save(pVtoSenasa);
                elemento.setPdfVtoSenasa(pdfVtoSenasa);
            } else {
                Pdf u = pdfService.agregar(vtoSenasa, false);
                u.setTabla("vehiculo");
                Pdf pdf5 = pdfDAO.saveAndFlush(u);
                elemento.setPdfVtoSenasa(pdf5);
            }
        }
        if (habBromat.getOriginalFilename().equals("")) {
            if (vehiculo.getPdfHabBromat() != null) {
                pdfDAO.deleteById(vehiculo.getPdfHabBromat().getId());
                elemento.setPdfHabBromat(null);
            } else {
                elemento.setPdfHabBromat(null);
            }
        } else {
            if (vehiculo.getPdfTitulo() != null) {
                Pdf pHabBromat = pdfService.actualizar(vehiculo.getPdfHabBromat().getId(), habBromat, false);
                pHabBromat.setTabla("vehiculo");
                Pdf pdfHabBromat = pdfDAO.save(pHabBromat);
                elemento.setPdfHabBromat(pdfHabBromat);
            } else {
                Pdf u = pdfService.agregar(habBromat, false);
                u.setTabla("vehiculo");
                Pdf pdf6 = pdfDAO.saveAndFlush(u);
                elemento.setPdfHabBromat(pdf6);
            }
        }
        elementoDAO.save(elemento);
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