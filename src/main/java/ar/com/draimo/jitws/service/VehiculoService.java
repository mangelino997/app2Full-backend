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
        List<Vehiculo> elementos= elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por alias
    public Object listarPorAlias(String alias) throws IOException {
        List<Vehiculo> elementos;
        if (alias.equals("***")) {
            elementos=  elementoDAO.findAll();
        } else {
            elementos=  elementoDAO.findByAliasContaining(alias);
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por alias filtrado por no remolque
    public Object listarPorAliasYRemolqueFalse(String alias) throws IOException {
        List<Vehiculo> elementos= elementoDAO.findByAliasContainingAndConfiguracionVehiculo_TipoVehiculo_EsRemolqueFalse(alias);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por alias filtrado por remolque
    public Object listarPorAliasYRemolqueTrue(String alias) throws IOException {
        List<Vehiculo> elementos= elementoDAO.findByAliasContainingAndConfiguracionVehiculo_TipoVehiculo_EsRemolqueTrue(alias);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por empresa, tipo de vehiculo y marca de vehiculo
    public Object listarFiltro(int idEmpresa, int idTipoVehiculo, int idMarcaVehiculo) throws IOException {
        List<Vehiculo> elementos=  elementoDAO.listarPorConfig(idEmpresa, idTipoVehiculo, idMarcaVehiculo);
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
    public Vehiculo agregar(String elementoString, MultipartFile titulo, MultipartFile cedulaIdent,
            MultipartFile vtoRuta, MultipartFile vtoInspTecnica, MultipartFile vtoSenasa,
            MultipartFile habBromat) throws IOException {
        Vehiculo elemento = new ObjectMapper().readValue(elementoString, Vehiculo.class);
        elemento = formatearStrings(elemento);
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
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
        return elementoDAO.saveAndFlush(elemento);
    }

    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public void establecerAlias(String elementoString) throws IOException {
        Vehiculo elemento = new ObjectMapper().readValue(elementoString, Vehiculo.class);
        elemento.setAlias(elemento.getDominio() + " - "
                + elemento.getConfiguracionVehiculo().getTipoVehiculo().getNombre() + " - "
                + elemento.getConfiguracionVehiculo().getMarcaVehiculo().getNombre());
        elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(String elementoString, MultipartFile titulo, MultipartFile cedulaIdent,
            MultipartFile vtoRuta, MultipartFile vtoInspTecnica, MultipartFile vtoSenasa,
            MultipartFile habBromat) throws IOException {
        Vehiculo elemento = new ObjectMapper().readValue(elementoString, Vehiculo.class);
        elemento.setFechaUltimaMod(new Date(new java.util.Date().getTime()));
        elemento = formatearStrings(elemento);
        if (titulo.getOriginalFilename().equals("")) {
            if (elemento.getPdfTitulo().getId() != 0) {
                pdfDAO.deleteById(elemento.getPdfTitulo().getId());
                elemento.setPdfTitulo(null);
            } else {
                elemento.setPdfTitulo(null);
            }
        } else {
            Pdf pTitulo = pdfService.actualizar(elemento.getPdfTitulo().getId(), titulo, false);
            pTitulo.setTabla("vehiculo");
            Pdf pdfTitulo = pdfDAO.save(pTitulo);
            elemento.setPdfTitulo(pdfTitulo);
        }
        if (cedulaIdent.getOriginalFilename().equals("")) {
            if (elemento.getPdfCedulaIdent().getId() != 0) {
                pdfDAO.deleteById(elemento.getPdfCedulaIdent().getId());
                elemento.setPdfCedulaIdent(null);
            } else {
                elemento.setPdfCedulaIdent(null);
            }
        } else {
            Pdf pCedulaIden = pdfService.actualizar(elemento.getPdfCedulaIdent().getId(), cedulaIdent, false);
            pCedulaIden.setTabla("vehiculo");
            Pdf pdfCedulaIden = pdfDAO.save(pCedulaIden);
            elemento.setPdfCedulaIdent(pdfCedulaIden);
        }
        if (vtoRuta.getOriginalFilename().equals("")) {
            if (elemento.getPdfVtoRuta().getId() != 0) {
                pdfDAO.deleteById(elemento.getPdfVtoRuta().getId());
                elemento.setPdfVtoRuta(null);
            } else {
                elemento.setPdfVtoRuta(null);
            }
        } else {
            Pdf pVtoRuta = pdfService.actualizar(elemento.getPdfVtoRuta().getId(), vtoRuta, false);
            pVtoRuta.setTabla("vehiculo");
            Pdf pdfVtoRuta = pdfDAO.save(pVtoRuta);
            elemento.setPdfVtoRuta(pdfVtoRuta);
        }
        if (vtoInspTecnica.getOriginalFilename().equals("")) {
            if (elemento.getPdfVtoInspTecnica().getId() != 0) {
                pdfDAO.deleteById(elemento.getPdfVtoInspTecnica().getId());
                elemento.setPdfVtoInspTecnica(null);
            } else {
                elemento.setPdfVtoInspTecnica(null);
            }
        } else {
            Pdf pVtoInspTecnica = pdfService.actualizar(elemento.getPdfVtoInspTecnica().getId(), vtoInspTecnica, false);
            pVtoInspTecnica.setTabla("vehiculo");
            Pdf pdfVtoInspTecnica = pdfDAO.save(pVtoInspTecnica);
            elemento.setPdfVtoInspTecnica(pdfVtoInspTecnica);
        }
        if (vtoSenasa.getOriginalFilename().equals("")) {
            if (elemento.getPdfVtoSenasa().getId() != 0) {
                pdfDAO.deleteById(elemento.getPdfVtoSenasa().getId());
                elemento.setPdfVtoSenasa(null);
            } else {
                elemento.setPdfVtoSenasa(null);
            }
        } else {
            Pdf pVtoSenasa = pdfService.actualizar(elemento.getPdfVtoSenasa().getId(), vtoSenasa, false);
            pVtoSenasa.setTabla("vehiculo");
            Pdf pdfVtoSenasa = pdfDAO.save(pVtoSenasa);
            elemento.setPdfVtoSenasa(pdfVtoSenasa);
        }
        if (habBromat.getOriginalFilename().equals("")) {
            if (elemento.getPdfHabBromat().getId() != 0) {
                pdfDAO.deleteById(elemento.getPdfHabBromat().getId());
                elemento.setPdfHabBromat(null);
            } else {
                elemento.setPdfHabBromat(null);
            }
        } else {
            Pdf pHabBromat = pdfService.actualizar(elemento.getPdfHabBromat().getId(), habBromat, false);
            pHabBromat.setTabla("vehiculo");
            Pdf pdfHabBromat = pdfDAO.save(pHabBromat);
            elemento.setPdfHabBromat(pdfHabBromat);
        }
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Vehiculo elemento) {
        elementoDAO.delete(elemento);
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
