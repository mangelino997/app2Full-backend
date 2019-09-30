package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.dao.IFotoDAO;
import ar.com.draimo.jitws.dao.IPdfDAO;
import ar.com.draimo.jitws.dao.IPersonalDAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Foto;
import ar.com.draimo.jitws.model.Pdf;
import ar.com.draimo.jitws.model.Personal;
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
 * Servicio Personal
 *
 * @author blas
 */
@Service
public class PersonalService {

    //Define la referencia al dao
    @Autowired
    IPersonalDAO elementoDAO;

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
    public Object listar() throws IOException, Exception {
        List<Personal> elementos = elementoDAO.findAll();
        if (elementos.isEmpty()) {
            throw new Exception(MensajeRespuesta.LISTA_SIN_CONTENIDO);
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

    //Obtiene una lista por alias y/o empresa y/o sucursal y/o activos
    public Object listarPorAlias(String alias, boolean activos, int idEmpresa, 
            int idSucursal) throws IOException, Exception {
        Date fecha = new Date(new java.util.Date().getTime());
        //Establece el string vacio a alias en caso de que el usuario quiera listar todo
        alias =(alias.equals("***")?"": alias);
        /* Obtiene un listado por alias, activos o todos.
        Si recibe '***' en el alias no filtra por el mismo.
        idEmpresa y idSucursal pueden ser 0. en este caso no filtra por los mismos
        La fecha de hoy es para ver que el personal no este dado de baja*/
        List<Personal> elementos = elementoDAO.listarPorAlias(alias, activos, 
                idEmpresa, idSucursal, fecha);
        if (elementos.isEmpty()) {
            throw new Exception(MensajeRespuesta.LISTA_SIN_CONTENIDO);
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter).addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene un registro por id
    public Object obtenerPorId(int id) throws IOException, Exception {
        Personal elemento = elementoDAO.findById(id).get();
        Pdf pdf = new Pdf();
        if (elemento.getPdfAltaTemprana() == null) {
            elemento.setPdfAltaTemprana(pdf);
        }
        if (elemento.getPdfDni() == null) {
            elemento.setPdfDni(pdf);
        }
        if (elemento.getPdfLibSanidad() == null) {
            elemento.setPdfLibSanidad(pdf);
        }
        if (elemento.getPdfLicConducir() == null) {
            elemento.setPdfLicConducir(pdf);
        }
        if (elemento.getPdfLinti() == null) {
            elemento.setPdfLinti(pdf);
        }
        if (elemento.getFoto() == null) {
            Foto foto = fotoDAO.findById(1).get();
            elemento.setFoto(foto);
        }
        if (elemento==null) {
            throw new Exception("No se encontró el registro.");
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept();
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter).addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elemento);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene un listado de choferes por alias, distancia y empresa ordenados por nombre
    public Object listarChoferesPorDistanciaPorAliasOrdenadoPorNombre(String alias, 
            boolean largaDistancia, int idEmpresa) throws IOException {
        Date fecha = new Date(new java.util.Date().getTime());
        //Establece el string vacio a alias en caso de que el usuario quiera listar todo
        alias =(alias.equals("***")?"": alias);
        int distancia = largaDistancia ? 1 : 0;
        List<Personal> elementos = 
                elementoDAO.listarChoferesPorDistanciaPorAliasOrdenadoPorNombre(
                        alias,distancia, idEmpresa, fecha);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter).addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene un listado de choferes por alias, distancia y empresa ordenados por nombre
    public Object listarChoferesPorAliasOrdenadoPorNombre(String alias, int idEmpresa) throws IOException {
        Date fecha = new Date(new java.util.Date().getTime());
        //Establece el string vacio a alias en caso de que el usuario quiera listar todo
        alias =(alias.equals("***")?"": alias);
        List<Personal> elementos = 
                elementoDAO.listarChoferesPorDistanciaPorAliasOrdenadoPorNombre(
                        alias,2, idEmpresa, fecha);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter).addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene un listado de acompañantes ordenados por nombre
    public Object listarAcompaniantesPorAliasOrdenadosPorNombre(String alias) throws IOException {
        Date fecha = new Date(new java.util.Date().getTime());
        //Establece el string vacio a alias en caso de que el usuario quiera listar todo
        alias =(alias.equals("***")?"": alias);
        List<Personal> elementos = elementoDAO.listarAcompaniantesPorAliasOrdenadoPorNombre(
                alias,fecha);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter).addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Personal agregar(String elementoString, MultipartFile foto, MultipartFile licConducir,
            MultipartFile linti, MultipartFile libSanidad, MultipartFile dni, MultipartFile altaTemprana) throws IOException, Exception {
        Personal elemento = new ObjectMapper().readValue(elementoString, Personal.class);
        elemento = formatearStrings(elemento);
        controlDeLongitud(elemento);
        if (!foto.getOriginalFilename().equals("")) {
            Foto p = fotoService.agregar(foto, false);
            p.setTabla("personal");
            Foto f = fotoDAO.saveAndFlush(p);
            elemento.setFoto(f);
        } else {
            elemento.setFoto(fotoDAO.findById(1).get());
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
        if (!dni.getOriginalFilename().equals("")) {
            Pdf p4 = pdfService.agregar(dni, false);
            p4.setTabla("personal");
            Pdf pdf4 = pdfDAO.saveAndFlush(p4);
            elemento.setPdfDni(pdf4);
        } else {
            elemento.setPdfDni(null);
        }
        if (!altaTemprana.getOriginalFilename().equals("")) {
            Pdf p5 = pdfService.agregar(altaTemprana, false);
            p5.setTabla("personal");
            Pdf pdf5 = pdfDAO.saveAndFlush(p5);
            elemento.setPdfAltaTemprana(pdf5);
        } else {
            elemento.setPdfAltaTemprana(null);
        }
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(String elementoString, MultipartFile foto, MultipartFile licConducir,
            MultipartFile linti, MultipartFile libSanidad, MultipartFile dni, MultipartFile altaTemprana) throws IOException {
        Personal elemento = new ObjectMapper().readValue(elementoString, Personal.class);
        Personal personal = elementoDAO.findById(elemento.getId()).get();
        controlDeLongitud(elemento);
        if (foto.getOriginalFilename().equals("")) {
            if (personal.getFoto() != null) {
                pdfDAO.deleteById(personal.getFoto().getId());
                elemento.setFoto(fotoDAO.findById(1).get());
            } else {
                elemento.setFoto(fotoDAO.findById(1).get());
            }
        } else {
            if (personal.getFoto() != null) {
                Foto f = fotoService.actualizar(personal.getFoto().getId(), foto, false);
                f.setTabla("personal");
                Foto f1 = fotoDAO.save(f);
                elemento.setFoto(f1);
            } else {
                Foto p = fotoService.agregar(foto, false);
                p.setTabla("personal");
                Foto f = fotoDAO.saveAndFlush(p);
                elemento.setFoto(f);
            }
        }
        if (licConducir.getOriginalFilename().equals("")) {
            if (personal.getPdfLicConducir() != null) {
                pdfDAO.deleteById(personal.getPdfLicConducir().getId());
                elemento.setPdfLicConducir(null);
            } else {
                elemento.setPdfLicConducir(null);
            }
        } else {
            if (personal.getPdfLicConducir() != null) {
                Pdf p1 = pdfService.actualizar(personal.getPdfLicConducir().getId(), licConducir, false);
                p1.setTabla("personal");
                Pdf pdf1 = pdfDAO.save(p1);
                elemento.setPdfLicConducir(pdf1);
            } else {
                Pdf p1 = pdfService.agregar(licConducir, false);
                p1.setTabla("personal");
                Pdf pdf1 = pdfDAO.saveAndFlush(p1);
                elemento.setPdfLicConducir(pdf1);
            }
        }
        if (linti.getOriginalFilename().equals("")) {
            if (personal.getPdfLinti() != null) {
                pdfDAO.deleteById(personal.getPdfLinti().getId());
                elemento.setPdfLinti(null);
            } else {
                elemento.setPdfLinti(null);
            }
        } else {
            if (personal.getPdfLinti() != null) {
                Pdf p2 = pdfService.actualizar(personal.getPdfLinti().getId(), linti, false);
                p2.setTabla("personal");
                Pdf pdf2 = pdfDAO.save(p2);
                elemento.setPdfLinti(pdf2);
            } else {
                Pdf p2 = pdfService.agregar(linti, false);
                p2.setTabla("personal");
                Pdf pdf2 = pdfDAO.saveAndFlush(p2);
                elemento.setPdfLinti(pdf2);
            }
        }
        if (libSanidad.getOriginalFilename().equals("")) {
            if (personal.getPdfLibSanidad() != null) {
                pdfDAO.deleteById(personal.getPdfLibSanidad().getId());
                elemento.setPdfLibSanidad(null);
            } else {
                elemento.setPdfLibSanidad(null);
            }
        } else {
            if (personal.getPdfLibSanidad() != null) {
                Pdf p3 = pdfService.actualizar(personal.getPdfLibSanidad().getId(), libSanidad, false);
                p3.setTabla("personal");
                Pdf pdf3 = pdfDAO.save(p3);
                elemento.setPdfLibSanidad(pdf3);
            } else {
                Pdf p3 = pdfService.agregar(libSanidad, false);
                p3.setTabla("personal");
                Pdf pdf3 = pdfDAO.saveAndFlush(p3);
                elemento.setPdfLibSanidad(pdf3);
            }
        }
//        if (dni.getOriginalFilename().equals("")) {
//            if (personal.getPdfDni() != null) {
//                pdfDAO.deleteById(personal.getPdfDni().getId());
//                elemento.setPdfDni(null);
//            } else {
//                elemento.setPdfDni(null);
//            }
//        } else {
//            if (personal.getPdfDni() != null) {
//                Pdf p4 = pdfService.actualizar(personal.getPdfDni().getId(), dni, false);
//                p4.setTabla("personal");
//                Pdf pdf4 = pdfDAO.save(p4);
//                elemento.setPdfDni(pdf4);
//            } else {
//                Pdf p4 = pdfService.agregar(dni, false);
//                p4.setTabla("personal");
//                Pdf pdf4 = pdfDAO.saveAndFlush(p4);
//                elemento.setPdfDni(pdf4);
//            }
//        }
        if (altaTemprana.getOriginalFilename().equals("")) {
            if (personal.getPdfAltaTemprana() != null) {
                pdfDAO.deleteById(personal.getPdfAltaTemprana().getId());
                elemento.setPdfAltaTemprana(null);
            } else {
                elemento.setPdfAltaTemprana(null);
            }
        } else {
            if (personal.getPdfAltaTemprana() != null) {
                Pdf p5 = pdfService.actualizar(personal.getPdfAltaTemprana().getId(), altaTemprana, false);
                p5.setTabla("personal");
                Pdf pdf5 = pdfDAO.save(p5);
                elemento.setPdfAltaTemprana(pdf5);
            } else {
                Pdf p5 = pdfService.agregar(altaTemprana, false);
                p5.setTabla("personal");
                Pdf pdf5 = pdfDAO.saveAndFlush(p5);
                elemento.setPdfAltaTemprana(pdf5);
            }
        }
        establecerAlias(elemento);
        elementoDAO.save(elemento);
    }
    private boolean controlDeLongitud(Personal elemento) {
        if (elemento.getAntiguedadAntAnio() > 60) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " ANTIGUEDAD ANT. AÑO");
        }
        //Obtiene antiguedadAntMes, si es mayor a 11 retorna error
        if (elemento.getAntiguedadAntMes() > 11) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " ANTIGUEDAD ANT. MES");
        }
        //Obtiene adherenteOb.Soc., si es mayor a 12 retorna error
        if (elemento.getAdherenteObraSocial() > 12) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " ADHERENTE OBRA SOCIAL");
        }
        //Obtiene longitud de anio, si es mayor a 2 retorna error
        String cuotasPr = String.valueOf(elemento.getCuotasPrestamo());
        if (cuotasPr.length() > 2) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " CUOTAS PRESTAMO");
        }
        return true;
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
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
