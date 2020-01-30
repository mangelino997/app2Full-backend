//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.dao.IAfipActividadDAO;
import ar.com.draimo.jitws.dao.IAfipCondicionDAO;
import ar.com.draimo.jitws.dao.IAfipLocalidadDAO;
import ar.com.draimo.jitws.dao.IAfipModContratacionDAO;
import ar.com.draimo.jitws.dao.IAfipSiniestradoDAO;
import ar.com.draimo.jitws.dao.IAfipSituacionDAO;
import ar.com.draimo.jitws.dao.IAreaDAO;
import ar.com.draimo.jitws.dao.ICategoriaDAO;
import ar.com.draimo.jitws.dao.IEstadoCivilDAO;
import ar.com.draimo.jitws.dao.IFotoDAO;
import ar.com.draimo.jitws.dao.IMonedaDAO;
import ar.com.draimo.jitws.dao.IObraSocialDAO;
import ar.com.draimo.jitws.dao.IPdfDAO;
import ar.com.draimo.jitws.dao.IPersonalCuentaBancariaDAO;
import ar.com.draimo.jitws.dao.IPersonalDAO;
import ar.com.draimo.jitws.dao.ISeguridadSocialDAO;
import ar.com.draimo.jitws.dao.ISexoDAO;
import ar.com.draimo.jitws.dao.ISindicatoDAO;
import ar.com.draimo.jitws.dao.ISucursalDAO;
import ar.com.draimo.jitws.dao.ITipoCuentaBancariaDAO;
import ar.com.draimo.jitws.dao.ITipoDocumentoDAO;
import ar.com.draimo.jitws.dto.ChoferDTO;
import ar.com.draimo.jitws.dto.InitPersonalDTO;
import ar.com.draimo.jitws.dto.PersonalDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Foto;
import ar.com.draimo.jitws.model.Pdf;
import ar.com.draimo.jitws.model.Personal;
import ar.com.draimo.jitws.model.PersonalCuentaBancaria;
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
 * @author blas
 */
@Service
public class PersonalService {

    //Define la referencia al dao
    @Autowired
    IPersonalDAO elementoDAO;

    //Define la referencia al dao
    @Autowired
    IPersonalCuentaBancariaDAO personalCuentaBancariaDAO;

    //Define la referencia al dao de foto
    @Autowired
    IFotoDAO fotoDAO;

    //Define la referencia al service de foto
    @Autowired
    FotoService fotoService;

    //Define la referencia al dao de pdf
    @Autowired
    IPdfDAO pdfDAO;

    //Define la referencia afipActividadDAO
    @Autowired
    IAfipActividadDAO afipActividadDAO;

    //Define la referencia afipCondicionDAO
    @Autowired
    IAfipCondicionDAO afipCondicionDAO;

    //Define la referencia afipLocalidadDAO
    @Autowired
    IAfipLocalidadDAO afipLocalidadDAO;

    //Define la referencia afipModContratacionDAO
    @Autowired
    IAfipModContratacionDAO afipModContratacionDAO;

    //Define la referencia al dao de AfipSiniestrado
    @Autowired
    IAfipSiniestradoDAO afipSiniestradoDAO;

    //Define la referencia al dao de AfipSituacion
    @Autowired
    IAfipSituacionDAO afipSituacionDAO;

    //Define la referencia al dao de Area
    @Autowired
    IAreaDAO areaDAO;

    //Define la referencia al dao de Categoria
    @Autowired
    ICategoriaDAO categoriaDAO;

    //Define la referencia al dao de EstadoCivil
    @Autowired
    IEstadoCivilDAO estadoCivilDAO;

    //Define la referencia obraSocialDAO
    @Autowired
    IObraSocialDAO obraSocialDAO;

    //Define la referencia sexoDAO
    @Autowired
    ISexoDAO sexoDAO;

    //Define la referencia seguridadDAO
    @Autowired
    ISeguridadSocialDAO seguridadSocialDAO;

    //Define la referencia sindicatoDAO
    @Autowired
    ISindicatoDAO sindicatoDAO;

    //Define la referencia al dao de sucursal
    @Autowired
    ISucursalDAO sucursalDAO;

    //Define la referencia tipoDocumentoDAO
    @Autowired
    ITipoDocumentoDAO tipoDocumentoDAO;
    
    //Define la referencia al dao tipoCuentaBancariaDAO
    @Autowired
    ITipoCuentaBancariaDAO tipoCuentaBancariaDAO;

    //Define la referencia al service de pdf
    @Autowired
    PdfService pdfService;
    
    //Define la subopcion pestania service
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Define el rol opcion service
    @Autowired
    RolOpcionService rolOpcionService;
    
    //Referencia al DAO de moneda
    @Autowired
    IMonedaDAO monedaDAO;
    
    //Inicializa los datos
    public InitPersonalDTO inicializar(int idUsuario, int idRol, int idSubopcion) {
        InitPersonalDTO p = new InitPersonalDTO();
        p.setUltimoId(obtenerSiguienteId());
        p.setAfipActividades(afipActividadDAO.findAll());
        p.setAfipCondiciones(afipCondicionDAO.findAll());
        p.setAfipLocalidades(afipLocalidadDAO.findAll());
        p.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idSubopcion));
        p.setOpciones(rolOpcionService.listarPorRolYSubopcion(idRol, idSubopcion));
        p.setAfipModContrataciones(afipModContratacionDAO.findAll());
        p.setTipoCuentaBancarias(tipoCuentaBancariaDAO.findAll());
        p.setAfipSiniestrados(afipSiniestradoDAO.findAll());
        p.setAfipSituacion(afipSituacionDAO.findAll());
        p.setAreas(areaDAO.findAll());
        p.setCategorias(categoriaDAO.findAll());
        p.setEstadoCiviles(estadoCivilDAO.findAll());
        p.setFecha(new Date(new java.util.Date().getTime()));
        p.setObraSociales(obraSocialDAO.findAll());
        p.setSeguridadSociales(seguridadSocialDAO.findAll());
        p.setSexos(sexoDAO.findAll());
        p.setSindicatos(sindicatoDAO.findAll());
        p.setSucursales(sucursalDAO.findAll());
        p.setTipoDocumentos(tipoDocumentoDAO.findAll());
        p.setMonedas(monedaDAO.findAll());
        return p;
    }

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Personal elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException, Exception {
        List<Personal> elementos = elementoDAO.findAll();
        return retornarObjeto(elementos, null);
    }

    //Obtiene una lista por alias y/o empresa y/o sucursal y/o activos
    public Object listarPorAlias(String alias, boolean activos, int idEmpresa,
            int idSucursal) throws IOException, Exception {
        Date fecha = new Date(new java.util.Date().getTime());
        //Establece el string vacio a alias en caso de que el usuario quiera listar todo
        alias = (alias.equals("*") ? "" : alias);
        /* Obtiene un listado por alias, activos o todos.
        Si recibe '***' en el alias no filtra por el mismo.
        idEmpresa y idSucursal pueden ser 0. en este caso no filtra por los mismos
        La fecha de hoy es para ver que el personal no este dado de baja*/
        List<Personal> elementos = elementoDAO.listarPorAlias(alias, activos,
                idEmpresa, idSucursal, fecha);
        return retornarObjeto(elementos, null);
    }

    //Obtiene un registro por id
    public Object obtenerPorId(int id) throws IOException, Exception {
        Personal elemento = elementoDAO.obtenerPorId(id);
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
            elemento.setFoto(new Foto());
        }
        if (elemento == null) {
            throw new Exception(MensajeRespuesta.NO_EXISTENTE);
        }

        return retornarObjeto(null, elemento);
    }

    //Obtiene un listado de choferes por alias, distancia y empresa ordenados por nombre
    public Object listarChoferesPorDistanciaPorAliasOrdenadoPorNombre(String alias,
            int largaDistancia, int idEmpresa) throws IOException {
        Date fecha = new Date(new java.util.Date().getTime());
        //Establece el string vacio a alias en caso de que el usuario quiera listar todo
        alias = (alias.equals("*") ? "" : alias);
        List<Personal> elementos
                = elementoDAO.listarChoferesPorDistanciaPorAliasOrdenadoPorNombre(
                        alias, largaDistancia, idEmpresa, fecha);
        return retornarObjeto(elementos, null);
    }

    //Obtiene un listado de choferes por alias, distancia y empresa ordenados por nombre
    public Object listarChoferesPorAliasOrdenadoPorNombre(String alias, int idEmpresa) throws IOException {
        Date fecha = new Date(new java.util.Date().getTime());
        //Establece el string vacio a alias en caso de que el usuario quiera listar todo
        alias = (alias.equals("*") ? "" : alias);
        List<Personal> elementos
                = elementoDAO.listarChoferesPorDistanciaPorAliasOrdenadoPorNombre(
                        alias, 2, idEmpresa, fecha);
        return retornarObjeto(elementos, null);
    }

    //Obtiene un listado de acompañantes ordenados por nombre
    public Object listarAcompaniantesPorAliasOrdenadosPorNombre(String alias) throws IOException {
        Date fecha = new Date(new java.util.Date().getTime());
        //Establece el string vacio a alias en caso de que el usuario quiera listar todo
        alias = (alias.equals("*") ? "" : alias);
        List<Personal> elementos = elementoDAO.listarAcompaniantesPorAliasOrdenadoPorNombre(
                alias, fecha);
        return retornarObjeto(elementos, null);
    }

    //Obtiene un listado por filtros
    public Object listarPorFiltros(PersonalDTO elemento) throws IOException {
        List<Personal> elementos = elementoDAO.listarPorFiltros(elemento.getIdSucursal(),
                elemento.getIdArea(),elemento.getIdModContratacion(), elemento.getIdCategoria(),
                elemento.getTipoEmpleado());
        return retornarObjeto(elementos, null);
    }

    //Obtiene un listado de choferes por filtros
    public Object listarChoferesPorFiltros(ChoferDTO elemento) throws IOException {
        List<Personal> elementos = elementoDAO.listarChoferesPorFiltros(elemento.getChofer(),
                elemento.getVtoCurso(), elemento.getVtoCargaPeligrosa(),
                elemento.getVtoLicConducir(), elemento.getVtoLinti(),
                elemento.getVtoLibSanidad(), elemento.getVtoFisico());
        return retornarObjeto(elementos, null);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Personal agregar(String elementoString, MultipartFile foto, MultipartFile licConducir,
            MultipartFile linti, MultipartFile libSanidad, MultipartFile dni,
            MultipartFile altaTemprana) throws IOException, Exception {
        Personal personal = new ObjectMapper().readValue(elementoString, Personal.class);
        personal = formatearStrings(personal);
        controlarLongitud(personal);
        //Establece la foto
        personal.setFoto(establecerFoto(foto, personal));
        //Establece el nombre
        String nombre = personal.getApellido().toUpperCase() + "-" + personal.getNombre().toUpperCase();
        //Establece los pdfs
        personal.setPdfLicConducir(establecerPdf(licConducir, nombre + "-LICENCIA"));
        personal.setPdfLinti(establecerPdf(linti, nombre + "-LINTI"));
        personal.setPdfLibSanidad(establecerPdf(libSanidad, nombre + "-LIBSANIDAD"));
        personal.setPdfDni(establecerPdf(dni, nombre + "-DNI"));
        personal.setPdfAltaTemprana(establecerPdf(altaTemprana, nombre + "-ALTATEMPRANA"));
        //Agrega el personal
        Personal p = elementoDAO.saveAndFlush(personal);
        //Agrega la cuentas bancarias, si hubiera
        if (personal.getPersonalCuentaBancarias() != null) {
            for (PersonalCuentaBancaria pcb : personal.getPersonalCuentaBancarias()) {
                    pcb.setPersonal(p);
                    personalCuentaBancariaDAO.saveAndFlush(pcb);
            }
        }
        return p;
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public Personal actualizar(Personal elemento) {
        Personal personal = elementoDAO.findById(elemento.getId()).get();
        controlarLongitud(elemento);
        //Establece los pdfs
        elemento.setFoto(personal.getFoto());
        elemento.setPdfLicConducir(personal.getPdfLicConducir());
        elemento.setPdfLinti(personal.getPdfLinti());
        elemento.setPdfLibSanidad(personal.getPdfLibSanidad());
        elemento.setPdfDni(personal.getPdfDni());
        elemento.setPdfAltaTemprana(personal.getPdfAltaTemprana());
        //Añade las nuevas cuentas bancarias
        if (elemento.getPersonalCuentaBancarias()!= null) {
            for (PersonalCuentaBancaria pcb : elemento.getPersonalCuentaBancarias()) {
                if(pcb.getId() == 0 ){
                    pcb.setPersonal(personal);
                    personalCuentaBancariaDAO.saveAndFlush(pcb);
                }
            }
        }
        return formatearStrings(elemento);
    }
    
    //Actualiza un pdf
    @Transactional(rollbackFor = Exception.class)
    public Object actualizarPDF(int idPersonal, int idPdf, String tipoPdf, MultipartFile archivo) throws IOException {
        Personal personal = elementoDAO.findById(idPersonal).get();
        Personal p;
        Foto foto = null;
        Pdf pdf = null;
        //Establece el nombre
        String nombre = personal.getApellido().toUpperCase() + "-" + personal.getNombre().toUpperCase();
        //Si es una foto
        if(tipoPdf.equals("foto")) {
            if(idPdf == 1) {
                foto = fotoService.agregar(archivo, nombre + "-FOTO", false);
                foto.setTabla("personal");
                foto = fotoDAO.saveAndFlush(foto);
            } else {
                foto = fotoService.actualizar(idPdf, "personal", archivo, nombre + "-FOTO", false);
                foto = fotoDAO.save(foto);
            }
            personal.setFoto(foto);
            p = elementoDAO.saveAndFlush(personal);
        } else {
            //Si es un pdf
            if(idPdf == 0) {
                pdf = pdfService.agregar(archivo, nombre + establecerNombrePdf(tipoPdf), false);
                pdf.setTabla("personal");
                pdf = pdfDAO.saveAndFlush(pdf);
            } else {
                pdf = pdfService.actualizar(idPdf, "personal", archivo, nombre + establecerNombrePdf(tipoPdf), false);
                pdf = pdfDAO.save(pdf);
            }
            p = elementoDAO.saveAndFlush(verificarTipoPdf(personal, tipoPdf, pdf));
        }
        Personal elemento = new Personal();
        elemento.setVersion(p.getVersion());
        elemento.setFoto(foto);
        elemento.setPdfDni(pdf);
        return retornarObjeto(null, elemento);
    }
    
    //Elimina un pdf
    @Transactional(rollbackFor = Exception.class)
    public Personal eliminarPDF(int idPersonal, int idPdf, String tipoPdf) {
        Personal personal = elementoDAO.findById(idPersonal).get();
        Personal p;
        //Si es una foto
        if(tipoPdf.equals("foto")) {
            personal.setFoto(null);
            p = elementoDAO.save(personal);
            fotoDAO.deleteById(idPdf);
        } else {
            p = elementoDAO.save(verificarTipoPdf(personal, tipoPdf, null));
            pdfDAO.deleteById(idPdf);
        }
        return p;
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Verifica el tipo de pdf
    private Personal verificarTipoPdf(Personal elemento, String tipoPdf, Pdf pdf) {
        switch(tipoPdf) {
            case "pdfDni":
                elemento.setPdfDni(pdf);
                break;
            case "pdfAltaTemprana":
                elemento.setPdfAltaTemprana(pdf);
                break;
            case "pdfLicConducir":
                elemento.setPdfLicConducir(pdf);
                break;
            case "pdfLinti":
                elemento.setPdfLinti(pdf);
                break;
            case "pdfLibSanidad":
                elemento.setPdfLibSanidad(pdf);
                break;
        }
        return elemento;
    }
    
    private String establecerNombrePdf(String tipo) {
        String nombre = null;
        switch(tipo) {
            case "pdfDni":
                nombre = "-DNI";
                break;
            case "pdfAltaTemprana":
                nombre = "-ALTATEMPRANA";
                break;
            case "pdfLicConducir":
                nombre = "-LICENCIA";
                break;
            case "pdfLinti":
                nombre = "-LINTI";
                break;
            case "pdfLibSanidad":
                nombre = "-LIBSANIDAD";
                break;
        }
        return nombre;
    }
    
    //Establece la foto
    private Foto establecerFoto(MultipartFile archivo, Personal elemento) throws IOException {
        Foto foto;
        String nombre = elemento.getApellido().toUpperCase() + "-" + elemento.getNombre().toUpperCase();
        if (!archivo.getOriginalFilename().equals("")) {
            Foto p = fotoService.agregar(archivo, nombre + "-FOTO", false);
            p.setTabla("personal");
            foto = fotoDAO.saveAndFlush(p);
            elemento.setFoto(foto);
        } else {
            foto = null;
        }
        return foto;
    }
    
    //Establece los pdfs
    private Pdf establecerPdf(MultipartFile elemento, String nombre) throws IOException {
        Pdf pdf;
        if (!"".equals(elemento.getOriginalFilename())) {
            pdf = pdfService.agregar(elemento, nombre, false);
            pdf.setTabla("personal");
            pdf = pdfDAO.saveAndFlush(pdf);
        } else {
            pdf = null;
        }
        return pdf;
    }

    //Controla la longitud de los atributos de tipo short
    private boolean controlarLongitud(Personal elemento) {
        if (elemento.getAntiguedadAntAnio() > 60) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD
                    + " ANTIGUEDAD ANT. AÑO");
        }
        //Obtiene antiguedadAntMes, si es mayor a 11 retorna error
        if (elemento.getAntiguedadAntMes() > 11) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD
                    + " ANTIGUEDAD ANT. MES");
        }
        //Obtiene adherenteOb.Soc., si es mayor a 12 retorna error
        if (elemento.getAdherenteObraSocial() > 12) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD
                    + " ADHERENTE OBRA SOCIAL");
        }
        //Obtiene longitud de anio, si es mayor a 2 retorna error
        String cuotasPr = String.valueOf(elemento.getCuotasPrestamo());
        if (cuotasPr.length() > 2) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD
                    + " CUOTAS PRESTAMO");
        }
        return true;
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

    //Retorna un object aplicando los filtros
    private Object retornarObjeto(List<Personal> elementos, Personal elemento) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = elemento != null ? SimpleBeanPropertyFilter
                .serializeAllExcept() : SimpleBeanPropertyFilter
                        .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter)
                .addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos != null ? elementos : elemento);
        return mapper.readValue(string, Object.class);
    }

}
