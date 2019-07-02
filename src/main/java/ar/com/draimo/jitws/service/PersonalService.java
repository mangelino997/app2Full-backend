package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IFotoDAO;
import ar.com.draimo.jitws.dao.IPdfDAO;
import ar.com.draimo.jitws.dao.IPersonalDAO;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.Foto;
import ar.com.draimo.jitws.model.Pdf;
import ar.com.draimo.jitws.model.Personal;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Servicio Personal
 *
 * @author blas
 */
@Service
public class PersonalService {

    //Define la referencia al dao
    @Autowired
    IPersonalDAO elementoDAO;

    //Define la referencia al dao de empresa
    @Autowired
    IEmpresaDAO empresaDAO;

    //Define la referencia al dao de foto
    @Autowired
    IFotoDAO fotoDAO;

    //Define la referencia al service de foto
    @Autowired
    FotoService fotoService;

    //Define la referencia al dao de pdf
    @Autowired
    IPdfDAO pdfDAO;

    //Define la referencia al service de pdf
    @Autowired
    PdfService pdfService;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Personal elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<Personal> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por alias
    public List<Personal> listarPorAlias(String alias) {
        if (alias.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByAliasContaining(alias);
        }
    }

    //Obtiene una lista por alias y empresa
    public List<Personal> listarPorAliasYEmpresa(String alias, int idEmpresa) {
        Empresa empresa = empresaDAO.findById(idEmpresa).get();
        if (alias.equals("***")) {
            return elementoDAO.findByEmpresa(empresa);
        } else {
            return elementoDAO.findByEmpresaAndAliasContaining(empresa, alias);
        }
    }

    //Obtiene un chofer por alias
    public List<Personal> listarChoferPorAlias(String alias) {
        return elementoDAO.findByAliasContainingAndEsChoferTrue(alias);
    }

    //Obtiene un listado de choferes ordenados por nombre de corta distancia
    public List<Personal> listarChoferesCortaDistanciaOrdenadoPorNombre(String alias) {
        return elementoDAO.listarChoferesCortaDistanciaPorAliasOrdenadoPorNombre(alias);
    }

    //Obtiene un listado de choferes ordenados por nombre de larga distancia
    public List<Personal> listarChoferesLargaDistanciaOrdenadoPorNombre(String alias) {
        return elementoDAO.listarChoferesLargaDistanciaPorAliasOrdenadoPorNombre(alias);
    }

    //Obtiene un listado de acompañantes ordenados por nombre
    public List<Personal> listarAcompaniantesOrdenadoPorNombre(String alias) {
        return elementoDAO.listarAcompaniantesPorAliasOrdenadoPorNombre(alias);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Personal agregar(String elementoString, MultipartFile foto, MultipartFile licConducir,
            MultipartFile linti, MultipartFile libSanidad) throws IOException {
        Personal elemento = new ObjectMapper().readValue(elementoString, Personal.class);
        elemento = formatearStrings(elemento);
        if (!foto.getOriginalFilename().equals("")) {
            Foto p = fotoService.agregar(foto, false);
            p.setTabla("personal");
            Foto f = fotoDAO.saveAndFlush(p);
            elemento.setFoto(f);
        } else {
            elemento.setFoto(null);
        }
        if (!licConducir.getOriginalFilename().equals("")) {
            Pdf p1 = pdfService.agregar(licConducir, false);
            p1.setTabla("personal");
            Pdf pdf1 = pdfDAO.saveAndFlush(p1);
            elemento.setPdfLicConducir(pdf1);
        } else {
            elemento.setPdfLicConducir(null);
        }
        if (!linti.getOriginalFilename().equals("")) {
            Pdf p2 = pdfService.agregar(linti, false);
            p2.setTabla("personal");
            Pdf pdf2 = pdfDAO.saveAndFlush(p2);
            elemento.setPdfLinti(pdf2);
        } else {
            elemento.setPdfLinti(null);
        }
        if (!libSanidad.getOriginalFilename().equals("")) {
            Pdf p3 = pdfService.agregar(libSanidad, false);
            p3.setTabla("personal");
            Pdf pdf3 = pdfDAO.saveAndFlush(p3);
            elemento.setPdfLibSanidad(pdf3);
        } else {
            elemento.setPdfLibSanidad(null);
        }
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(String elementoString, MultipartFile foto, MultipartFile licConducir,
            MultipartFile linti, MultipartFile libSanidad) throws IOException {
        Personal elemento = new ObjectMapper().readValue(elementoString, Personal.class);
        if (foto.getOriginalFilename().equals("")) {
            if (elemento.getFoto().getId() != 0) {
                pdfDAO.deleteById(elemento.getFoto().getId());
                elemento.setFoto(null);
            } else {
                elemento.setFoto(null);
            }
        } else {
            Foto f = fotoService.actualizar(elemento.getFoto().getId(), foto, false);
            f.setTabla("personal");
            Foto f1 = fotoDAO.save(f);
            elemento.setFoto(f1);
        }
        if (licConducir.getOriginalFilename().equals("")) {
            if (elemento.getPdfLicConducir().getId() != 0) {
                pdfDAO.deleteById(elemento.getPdfLicConducir().getId());
                elemento.setPdfLicConducir(null);
            } else {
                elemento.setPdfLicConducir(null);
            }
        } else {
            Pdf p1 = pdfService.actualizar(elemento.getPdfLicConducir().getId(), licConducir, false);
            p1.setTabla("personal");
            Pdf pdf1 = pdfDAO.save(p1);
            elemento.setPdfLicConducir(pdf1);
        }
        if (linti.getOriginalFilename().equals("")) {
            if (elemento.getPdfLinti().getId() != 0) {
                pdfDAO.deleteById(elemento.getPdfLinti().getId());
                elemento.setPdfLinti(null);
            } else {
                elemento.setPdfLinti(null);
            }
        } else {
            Pdf p2 = pdfService.actualizar(elemento.getPdfLinti().getId(), linti, false);
            p2.setTabla("personal");
            Pdf pdf2 = pdfDAO.save(p2);
            elemento.setPdfLinti(pdf2);
        }
        if (libSanidad.getOriginalFilename().equals("")) {
            if (elemento.getPdfLibSanidad().getId() != 0) {
                pdfDAO.deleteById(elemento.getPdfLibSanidad().getId());
                elemento.setPdfLibSanidad(null);
            } else {
                elemento.setPdfLibSanidad(null);
            }
        } else {
            Pdf p3 = pdfService.actualizar(elemento.getPdfLibSanidad().getId(), libSanidad, false);
            p3.setTabla("personal");
            Pdf pdf3 = pdfDAO.save(p3);
            elemento.setPdfLibSanidad(pdf3);
        }
        establecerAlias(elemento);
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Personal elemento) {
        elementoDAO.delete(elemento);
    }

    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public void establecerAlias(Personal elemento) {
        elemento.setAlias(elemento.getId() + " - " + elemento.getNombreCompleto()
                + " - " + elemento.getNumeroDocumento());
        elementoDAO.save(elemento);
    }

    //Formatea los strings
    private Personal formatearStrings(Personal elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        elemento.setApellido(Funcion.convertirATitulo(elemento.getApellido().trim()));
        elemento.setNombreCompleto(elemento.getApellido() + " " + elemento.getNombre());
        elemento.setNumeroDocumento(elemento.getNumeroDocumento().trim());
        elemento.setCuil(elemento.getCuil().trim());
        if (elemento.getTelefonoFijo() != null) {
            elemento.setTelefonoFijo(elemento.getTelefonoFijo().trim());
        }
        if (elemento.getTelefonoMovil() != null) {
            elemento.setTelefonoMovil(elemento.getTelefonoMovil().trim());
        }
        if (elemento.getCorreoelectronico() != null) {
            elemento.setCorreoelectronico(elemento.getCorreoelectronico().trim().toLowerCase());
        }
        elemento.setDomicilio(elemento.getDomicilio().trim());
        if (elemento.getTalleCamisa() != null) {
            elemento.setTalleCamisa(elemento.getTalleCamisa().trim());
        }
        if (elemento.getTallePantalon() != null) {
            elemento.setTallePantalon(elemento.getTallePantalon().trim());
        }
        if (elemento.getTalleCalzado() != null) {
            elemento.setTalleCalzado(elemento.getTalleCalzado().trim());
        }
        if (elemento.getTelefonoMovilEmpresa() != null) {
            elemento.setTelefonoMovilEmpresa(elemento.getTelefonoMovilEmpresa().trim());
        }
        if (elemento.getTelefonoMovilObservacion() != null) {
            elemento.setTelefonoMovilObservacion(elemento.getTelefonoMovilObservacion().trim());
        }
        if (elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        return elemento;
    }

}
