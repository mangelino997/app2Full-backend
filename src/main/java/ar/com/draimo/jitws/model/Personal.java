//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Personal
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "personal")
public class Personal extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre",length = 45 , nullable = false)
    private String nombre;
    
    //Define el apellido
    @Column(name = "apellido",length = 45 , nullable = false)
    private String apellido;
    
    //Define el nombre completo
    @Column(name = "nombreCompleto",length = 45 , nullable = false)
    private String nombreCompleto;
    
    //Referencia a la clase Tipo de documento
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoDocumento", nullable = false)
    private TipoDocumento tipoDocumento;
    
    //Define el numero de documento
    @Column(name = "numeroDocumento",length = 11 , nullable = false)
    private String numeroDocumento;
    
    //Define el cuil
    @Column(name = "cuil",length = 11 , nullable = false, unique = true)
    private String cuil;
    
    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
    
    //Define el domicilio
    @Column(name = "domicilio",length = 60 , nullable = false)
    private String domicilio;
    
    //Referencia a la clase Barrio
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idBarrio", nullable = true)
    private Barrio barrio;
    
    //Referencia a la clase Localidad
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idLocalidad", nullable = false)
    private Localidad localidad;
    
    //Referencia a la clase Localidad (Nacimiento)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idLocalidadNacimiento", nullable = false)
    private Localidad localidadNacimiento;
    
    //Define la fecha de nacimiento
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaNacimiento", nullable = false)
    private Date fechaNacimiento;

    //Define el telefono fijo
    @Column(name = "telefonoFijo",length = 45 , nullable = true)
    private String telefonoFijo;
    
    //Define el telefono movil
    @Column(name = "telefonoMovil",length = 45 , nullable = true)
    private String telefonoMovil;
    
    //Referencia a la clese Estado civil
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEstadoCivil", nullable = false)
    private EstadoCivil estadoCivil;
    
    //Define el correo electronico
    @Column(name = "correoelectronico",length = 30 , nullable = true)
    private String correoelectronico;
    
    //Referencia a la clese Sexo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSexo", nullable = false)
    private Sexo sexo;
    
    //Referencia a la clese Sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;
    
    //Referencia a la clese Area
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idArea", nullable = false)
    private Area area;
    
    //Define la fecha de inicio
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaInicio", nullable = false)
    private Date fechaInicio;

    //Define la fecha de fin
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaFin", nullable = true)
    private Date fechaFin;
    
    //Define la antiguedad anterior anios
    @Column(name = "antiguedadAntAnio", nullable = true)
    private short antiguedadAntAnio;
    
    //Define la antiguedad anterior mes
    @Column(name = "antiguedadAntMes", nullable = true)
    private short antiguedadAntMes;
    
    //Define si es jubilado
    @Column(name = "esJubilado", nullable = false)
    private boolean esJubilado;
    
    //Define si es mensualizado
    @Column(name = "esMensualizado", nullable = false)
    private boolean esMensualizado;
    
    //Referencia a la clase Categoria
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCategoria", nullable = false)
    private Categoria categoria;
    
    //Referencia a la clase Obra social
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idObraSocial", nullable = false)
    private ObraSocial obraSocial;
    
    //Referencia a la clase Sindicato
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSindicato", nullable = false)
    private Sindicato sindicato;
    
    //Referencia a la clase Seguridad social
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSeguridadSocial", nullable = false)
    private SeguridadSocial seguridadSocial;
    
    //Referencia a la clase Afip Situacion
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipSituacion", nullable = false)
    private AfipSituacion afipSituacion;
    
    //Referencia a la clase Afip Condicion
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipCondicion", nullable = false)
    private AfipCondicion afipCondicion;
    
    //Referencia a la clase Afip Actividad
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipActividad", nullable = false)
    private AfipActividad afipActividad;
    
    //Referencia a la clase Afip Modalidad Contratacion
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipModContratacion", nullable = false)
    private AfipModContratacion afipModContratacion;
    
    //Referencia a la clase Afip Localidad
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipLocalidad", nullable = false)
    private AfipLocalidad afipLocalidad;
    
    //Referencia a la clase Afip Siniestrado
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipSiniestrado", nullable = false)
    private AfipSiniestrado afipSiniestrado;
    
    //Define si adherente obra social
    @Column(name = "adherenteObraSocial", nullable = true)
    private short adherenteObraSocial;
    
    //Define el aporte adicional obra social
    @Column(name = "aporteAdicObraSocial", nullable = true)
    private BigDecimal aporteAdicObraSocial;
    
    //Define la contribucion adicional obra social
    @Column(name = "contribAdicObraSocial", nullable = true)
    private BigDecimal contribAdicObraSocial;
    
    //Define el aporte adicional seguridad social
    @Column(name = "aporteAdicSegSoc", nullable = true)
    private BigDecimal aporteAdicSegSoc;
    
    //Define el aporte dif seguridad social
    @Column(name = "aporteDifSegSoc", nullable = true)
    private BigDecimal aporteDifSegSoc;
    
    //Define la contribucion tarea dif seguridad social
    @Column(name = "contribTareaDifSegSoc", nullable = true)
    private BigDecimal contribTareaDifSegSoc;
    
    //Define si esta en convenio colectivo
    @Column(name = "enConvenioColectivo", nullable = false)
    private boolean enConvenioColectivo;
    
    //Define convertura SCVO
    @Column(name = "conCoberturaSCVO", nullable = false)
    private boolean conCoberturaSCVO;
    
    //Define si recibe adelantos
    @Column(name = "recibeAdelanto", nullable = false)
    private boolean recibeAdelanto;
    
    //Define si recibe prestamos
    @Column(name = "recibePrestamo", nullable = false)
    private boolean recibePrestamo;
    
    //Define las cuotas del prestamo
    @Column(name = "cuotasPrestamo", nullable = false)
    private short cuotasPrestamo;
    
    //Referencia a la clase Usuario (Alta)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;
    
    //Referencia a la clase Usuario (Baja)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioBaja", nullable = true)
    private Usuario usuarioBaja;
    
    //Referencia a la clase Usuario (Modificacion)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioMod", nullable = true)
    private Usuario usuarioMod;
    
    //Define el vencimiento de la licencia de conducirde un chofer
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "vtoLicenciaConducir", nullable = true)
    private Date vtoLicenciaConducir;

    //Define el vencimiento del curso CNRT de un chofer
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "vtoCurso", nullable = true)
    private Date vtoCurso;

    //Define el vencimiento del curso De carga peligrosa
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "vtoCursoCargaPeligrosa", nullable = true)
    private Date vtoCursoCargaPeligrosa;
    
    //Define el vencimiento de LNH
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "vtoLINTI", nullable = true)
    private Date vtoLINTI;

    //Define el vencimiento de la libreta de sanidad
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "vtoLibretaSanidad", nullable = true)
    private Date vtoLibretaSanidad;

    //Referencia a la clase Usuario (Modificacion LC)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioModLC", nullable = true)
    private Usuario usuarioModLC;
    
    //Referencia a la clase Usuario (Modificacion Curso)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioModCurso", nullable = true)
    private Usuario usuarioModCurso;
    
    //Referencia a la clase Usuario (Modificacion CursoCP)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioModCursoCP", nullable = true)
    private Usuario usuarioModCursoCP;
    
    //Referencia a la clase Usuario (Modificacion LINTI)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioModLINTI", nullable = true)
    private Usuario usuarioModLINTI;
    
    //Referencia a la clase Usuario (Modificacion LS)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioModLS", nullable = true)
    private Usuario usuarioModLS;
    
    //Define la fecha de modificacion de LC
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaModLC", nullable = true)
    private Date fechaModLC;

    //Define la fecha de modificacion de Curso
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaModCurso", nullable = true)
    private Date fechaModCurso;
    
    //Define la fecha de modificacion de CursoCP
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaModCursoCP", nullable = true)
    private Date fechaModCursoCP;

    //Define la fecha de modificacion de LINTI
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaModLINTI", nullable = true)
    private Date fechaModLINTI;
    
    //Define la fecha de modificacion de LS
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaModLS", nullable = true)
    private Date fechaModLS;

    //Define el talle de camisa
    @Column(name = "talleCamisa",length = 20 , nullable = true)
    private String talleCamisa;
    
    //Define el talle de pantalon
    @Column(name = "tallePantalon",length = 20, nullable = true)
    private String tallePantalon;
    
    //Define el talle de calzado
    @Column(name = "talleCalzado",length = 20 , nullable = true)
    private String talleCalzado;
    
    //Define el turno entrada mañana
    //Define fechaRegistracion
    @JsonFormat(pattern = "HH:mm:ss", timezone = "UTC-3")
    @Column(name = "turnoMEntrada", nullable = true)
    private Time turnoMEntrada;
    
    //Define el turno salida mañana
    @JsonFormat(pattern = "HH:mm:ss", timezone = "UTC-3")
    @Column(name = "turnoMSalida", nullable = true)
    private Time turnoMSalida;
    
    //Define el turno entrada tarde
    @JsonFormat(pattern = "HH:mm:ss", timezone = "UTC-3")
    @Column(name = "turnoTEntrada", nullable = true)
    private Time turnoTEntrada;
    
    //Define el turno salida tarde
    @JsonFormat(pattern = "HH:mm:ss", timezone = "UTC-3")
    @Column(name = "turnoTSalida", nullable = true)
    private Time turnoTSalida;
    
    //Define el turno entrada noche
    @JsonFormat(pattern = "HH:mm:ss", timezone = "UTC-3")
    @Column(name = "turnoNEntrada", nullable = true)
    private Time turnoNEntrada;
    
    //Define el turno salida noche
    @JsonFormat(pattern = "HH:mm:ss", timezone = "UTC-3")
    @Column(name = "turnoNSalida", nullable = true)
    private Time turnoNSalida;
    
    //Define el turno entrada sabado
    @JsonFormat(pattern = "HH:mm:ss", timezone = "UTC-3")
    @Column(name = "turnoSEntrada", nullable = true)
    private Time turnoSEntrada;
    
    //Define el turno salida sabado
    @JsonFormat(pattern = "HH:mm:ss", timezone = "UTC-3")
    @Column(name = "turnoSSalida", nullable = true)
    private Time turnoSSalida;
    
    //Define el turno entrada domingo
    @JsonFormat(pattern = "HH:mm:ss", timezone = "UTC-3")
    @Column(name = "turnoDEntrada", nullable = true)
    private Time turnoDEntrada;
    
    //Define el turno salida domingo
    @JsonFormat(pattern = "HH:mm:ss", timezone = "UTC-3")
    @Column(name = "turnoDSalida", nullable = true)
    private Time turnoDSalida;
    
    //Define el turno rotativo
    @Column(name = "turnoRotativo", nullable = true)
    private boolean turnoRotativo;
    
    //Define el turno fuera convenio
    @Column(name = "turnoFueraConvenio", nullable = true)
    private boolean turnoFueraConvenio;
    
    //Define el telefono movil de la empresa
    @Column(name = "telefonoMovilEmpresa",length = 45 , nullable = true)
    private String telefonoMovilEmpresa;
    
    //Define el telefono movil fecha de entrega
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "telefonoMovilFechaEntrega", nullable = true)
    private Date telefonoMovilFechaEntrega;

    //Define el telefono movil fecha de devolucion
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "telefonoMovilFechaDevolucion", nullable = true)
    private Date telefonoMovilFechaDevolucion;
    
    //Define el telefono movil observacion
    @Column(name = "telefonoMovilObservacion",length = 100 , nullable = true)
    private String telefonoMovilObservacion;
    
    //Define si es chofer
    @Column(name = "esChofer", nullable = false)
    private boolean esChofer;
    
    //Define si es chofer de larga distancia
    @Column(name = "esChoferLargaDistancia", nullable = false)
    private boolean esChoferLargaDistancia;
    
    //Define si es acompañante de reparto
    @Column(name = "esAcompReparto", nullable = false)
    private boolean esAcompReparto;
    
    //Define las observaciones
    @Column(name = "observaciones",length = 200 , nullable = true)
    private String observaciones;
    
    //Define el alias para las busquedas
    @Column(name = "alias",length = 100 , nullable = true)
    private String alias;
    
    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public Localidad getLocalidadNacimiento() {
        return localidadNacimiento;
    }

    public void setLocalidadNacimiento(Localidad localidadNacimiento) {
        this.localidadNacimiento = localidadNacimiento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public short getAntiguedadAntAnio() {
        return antiguedadAntAnio;
    }

    public void setAntiguedadAntAnio(short antiguedadAntAnio) {
        this.antiguedadAntAnio = antiguedadAntAnio;
    }

    public short getAntiguedadAntMes() {
        return antiguedadAntMes;
    }

    public void setAntiguedadAntMes(short antiguedadAntMes) {
        this.antiguedadAntMes = antiguedadAntMes;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public boolean getEsJubilado() {
        return esJubilado;
    }

    public void setEsJubilado(boolean esJubilado) {
        this.esJubilado = esJubilado;
    }

    public boolean getEsMensualizado() {
        return esMensualizado;
    }

    public void setEsMensualizado(boolean esMensualizado) {
        this.esMensualizado = esMensualizado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ObraSocial getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(ObraSocial obraSocial) {
        this.obraSocial = obraSocial;
    }

    public Sindicato getSindicato() {
        return sindicato;
    }

    public void setSindicato(Sindicato sindicato) {
        this.sindicato = sindicato;
    }

    public SeguridadSocial getSeguridadSocial() {
        return seguridadSocial;
    }

    public void setSeguridadSocial(SeguridadSocial seguridadSocial) {
        this.seguridadSocial = seguridadSocial;
    }

    public AfipSituacion getAfipSituacion() {
        return afipSituacion;
    }

    public void setAfipSituacion(AfipSituacion afipSituacion) {
        this.afipSituacion = afipSituacion;
    }

    public AfipCondicion getAfipCondicion() {
        return afipCondicion;
    }

    public void setAfipCondicion(AfipCondicion afipCondicion) {
        this.afipCondicion = afipCondicion;
    }

    public AfipActividad getAfipActividad() {
        return afipActividad;
    }

    public void setAfipActividad(AfipActividad afipActividad) {
        this.afipActividad = afipActividad;
    }

    public AfipModContratacion getAfipModContratacion() {
        return afipModContratacion;
    }

    public void setAfipModContratacion(AfipModContratacion afipModContratacion) {
        this.afipModContratacion = afipModContratacion;
    }

    public AfipLocalidad getAfipLocalidad() {
        return afipLocalidad;
    }

    public void setAfipLocalidad(AfipLocalidad afipLocalidad) {
        this.afipLocalidad = afipLocalidad;
    }

    public AfipSiniestrado getAfipSiniestrado() {
        return afipSiniestrado;
    }

    public void setAfipSiniestrado(AfipSiniestrado afipSiniestrado) {
        this.afipSiniestrado = afipSiniestrado;
    }

    public short getAdherenteObraSocial() {
        return adherenteObraSocial;
    }

    public void setAdherenteObraSocial(short adherenteObraSocial) {
        this.adherenteObraSocial = adherenteObraSocial;
    }

    public BigDecimal getAporteAdicObraSocial() {
        return aporteAdicObraSocial;
    }

    public void setAporteAdicObraSocial(BigDecimal aporteAdicObraSocial) {
        this.aporteAdicObraSocial = aporteAdicObraSocial;
    }

    public BigDecimal getContribAdicObraSocial() {
        return contribAdicObraSocial;
    }

    public void setContribAdicObraSocial(BigDecimal contribAdicObraSocial) {
        this.contribAdicObraSocial = contribAdicObraSocial;
    }

    public BigDecimal getAporteAdicSegSoc() {
        return aporteAdicSegSoc;
    }

    public void setAporteAdicSegSoc(BigDecimal aporteAdicSegSoc) {
        this.aporteAdicSegSoc = aporteAdicSegSoc;
    }

    public BigDecimal getAporteDifSegSoc() {
        return aporteDifSegSoc;
    }

    public void setAporteDifSegSoc(BigDecimal aporteDifSegSoc) {
        this.aporteDifSegSoc = aporteDifSegSoc;
    }

    public BigDecimal getContribTareaDifSegSoc() {
        return contribTareaDifSegSoc;
    }

    public void setContribTareaDifSegSoc(BigDecimal contribTareaDifSegSoc) {
        this.contribTareaDifSegSoc = contribTareaDifSegSoc;
    }

    public boolean getEnConvenioColectivo() {
        return enConvenioColectivo;
    }

    public void setEnConvenioColectivo(boolean enConvenioColectivo) {
        this.enConvenioColectivo = enConvenioColectivo;
    }

    public boolean getConCoberturaSCVO() {
        return conCoberturaSCVO;
    }

    public void setConCoberturaSCVO(boolean conCoberturaSCVO) {
        this.conCoberturaSCVO = conCoberturaSCVO;
    }

    public boolean getRecibeAdelanto() {
        return recibeAdelanto;
    }

    public void setRecibeAdelanto(boolean recibeAdelanto) {
        this.recibeAdelanto = recibeAdelanto;
    }

    public boolean getRecibePrestamo() {
        return recibePrestamo;
    }

    public void setRecibePrestamo(boolean recibePrestamo) {
        this.recibePrestamo = recibePrestamo;
    }

    public short getCuotasPrestamo() {
        return cuotasPrestamo;
    }

    public void setCuotasPrestamo(short cuotasPrestamo) {
        this.cuotasPrestamo = cuotasPrestamo;
    }

    public Usuario getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(Usuario usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public Usuario getUsuarioBaja() {
        return usuarioBaja;
    }

    public void setUsuarioBaja(Usuario usuarioBaja) {
        this.usuarioBaja = usuarioBaja;
    }

    public Usuario getUsuarioMod() {
        return usuarioMod;
    }

    public void setUsuarioMod(Usuario usuarioMod) {
        this.usuarioMod = usuarioMod;
    }

    public Date getVtoLicenciaConducir() {
        return vtoLicenciaConducir;
    }

    public void setVtoLicenciaConducir(Date vtoLicenciaConducir) {
        this.vtoLicenciaConducir = vtoLicenciaConducir;
    }

    public Date getVtoCurso() {
        return vtoCurso;
    }

    public void setVtoCurso(Date vtoCurso) {
        this.vtoCurso = vtoCurso;
    }

    public Date getVtoCursoCargaPeligrosa() {
        return vtoCursoCargaPeligrosa;
    }

    public void setVtoCursoCargaPeligrosa(Date vtoCursoCargaPeligrosa) {
        this.vtoCursoCargaPeligrosa = vtoCursoCargaPeligrosa;
    }

    public Date getVtoLINTI() {
        return vtoLINTI;
    }

    public void setVtoLINTI(Date vtoLINTI) {
        this.vtoLINTI = vtoLINTI;
    }
    
    public Date getVtoLibretaSanidad() {
        return vtoLibretaSanidad;
    }

    public void setVtoLibretaSanidad(Date vtoLibretaSanidad) {
        this.vtoLibretaSanidad = vtoLibretaSanidad;
    }
    
    public Usuario getUsuarioModLC() {
        return usuarioModLC;
    }

    public void setUsuarioModLC(Usuario usuarioModLC) {
        this.usuarioModLC = usuarioModLC;
    }

    public Usuario getUsuarioModCurso() {
        return usuarioModCurso;
    }

    public void setUsuarioModCurso(Usuario usuarioModCurso) {
        this.usuarioModCurso = usuarioModCurso;
    }

    public Usuario getUsuarioModCursoCP() {
        return usuarioModCursoCP;
    }

    public void setUsuarioModCursoCP(Usuario usuarioModCursoCP) {
        this.usuarioModCursoCP = usuarioModCursoCP;
    }

    public Usuario getUsuarioModLINTI() {
        return usuarioModLINTI;
    }

    public void setUsuarioModLINTI(Usuario usuarioModLINTI) {
        this.usuarioModLINTI = usuarioModLINTI;
    }

    public Usuario getUsuarioModLS() {
        return usuarioModLS;
    }

    public void setUsuarioModLS(Usuario usuarioModLS) {
        this.usuarioModLS = usuarioModLS;
    }

    public Date getFechaModLC() {
        return fechaModLC;
    }

    public void setFechaModLC(Date fechaModLC) {
        this.fechaModLC = fechaModLC;
    }

    public Date getFechaModCurso() {
        return fechaModCurso;
    }

    public void setFechaModCurso(Date fechaModCurso) {
        this.fechaModCurso = fechaModCurso;
    }

    public Date getFechaModCursoCP() {
        return fechaModCursoCP;
    }

    public void setFechaModCursoCP(Date fechaModCursoCP) {
        this.fechaModCursoCP = fechaModCursoCP;
    }

    public Date getFechaModLINTI() {
        return fechaModLINTI;
    }

    public void setFechaModLINTI(Date fechaModLINTI) {
        this.fechaModLINTI = fechaModLINTI;
    }
    
    public Date getFechaModLS() {
        return fechaModLS;
    }

    public void setFechaModLS(Date fechaModLS) {
        this.fechaModLS = fechaModLS;
    }
    
    public String getTalleCamisa() {
        return talleCamisa;
    }

    public void setTalleCamisa(String talleCamisa) {
        this.talleCamisa = talleCamisa;
    }

    public String getTallePantalon() {
        return tallePantalon;
    }

    public void setTallePantalon(String tallePantalon) {
        this.tallePantalon = tallePantalon;
    }

    public String getTalleCalzado() {
        return talleCalzado;
    }

    public void setTalleCalzado(String talleCalzado) {
        this.talleCalzado = talleCalzado;
    }

    public Time getTurnoMEntrada() {
        return turnoMEntrada;
    }

    public void setTurnoMEntrada(Time turnoMEntrada) {
        this.turnoMEntrada = turnoMEntrada;
    }

    public Time getTurnoMSalida() {
        return turnoMSalida;
    }

    public void setTurnoMSalida(Time turnoMSalida) {
        this.turnoMSalida = turnoMSalida;
    }

    public Time getTurnoTEntrada() {
        return turnoTEntrada;
    }

    public void setTurnoTEntrada(Time turnoTEntrada) {
        this.turnoTEntrada = turnoTEntrada;
    }

    public Time getTurnoTSalida() {
        return turnoTSalida;
    }

    public void setTurnoTSalida(Time turnoTSalida) {
        this.turnoTSalida = turnoTSalida;
    }

    public Time getTurnoNEntrada() {
        return turnoNEntrada;
    }

    public void setTurnoNEntrada(Time turnoNEntrada) {
        this.turnoNEntrada = turnoNEntrada;
    }

    public Time getTurnoNSalida() {
        return turnoNSalida;
    }

    public void setTurnoNSalida(Time turnoNSalida) {
        this.turnoNSalida = turnoNSalida;
    }

    public Time getTurnoSEntrada() {
        return turnoSEntrada;
    }

    public void setTurnoSEntrada(Time turnoSEntrada) {
        this.turnoSEntrada = turnoSEntrada;
    }

    public Time getTurnoSSalida() {
        return turnoSSalida;
    }

    public void setTurnoSSalida(Time turnoSSalida) {
        this.turnoSSalida = turnoSSalida;
    }

    public Time getTurnoDEntrada() {
        return turnoDEntrada;
    }

    public void setTurnoDEntrada(Time turnoDEntrada) {
        this.turnoDEntrada = turnoDEntrada;
    }

    public Time getTurnoDSalida() {
        return turnoDSalida;
    }

    public void setTurnoDSalida(Time turnoDSalida) {
        this.turnoDSalida = turnoDSalida;
    }

    public boolean getTurnoRotativo() {
        return turnoRotativo;
    }

    public void setTurnoRotativo(boolean turnoRotativo) {
        this.turnoRotativo = turnoRotativo;
    }

    public boolean getTurnoFueraConvenio() {
        return turnoFueraConvenio;
    }

    public void setTurnoFueraConvenio(boolean turnoFueraConvenio) {
        this.turnoFueraConvenio = turnoFueraConvenio;
    }

    public String getTelefonoMovilEmpresa() {
        return telefonoMovilEmpresa;
    }

    public void setTelefonoMovilEmpresa(String telefonoMovilEmpresa) {
        this.telefonoMovilEmpresa = telefonoMovilEmpresa;
    }

    public Date getTelefonoMovilFechaEntrega() {
        return telefonoMovilFechaEntrega;
    }

    public void setTelefonoMovilFechaEntrega(Date telefonoMovilFechaEntrega) {
        this.telefonoMovilFechaEntrega = telefonoMovilFechaEntrega;
    }

    public Date getTelefonoMovilFechaDevolucion() {
        return telefonoMovilFechaDevolucion;
    }

    public void setTelefonoMovilFechaDevolucion(Date telefonoMovilFechaDevolucion) {
        this.telefonoMovilFechaDevolucion = telefonoMovilFechaDevolucion;
    }
    
    public String getTelefonoMovilObservacion() {
        return telefonoMovilObservacion;
    }

    public void setTelefonoMovilObservacion(String telefonoMovilObservacion) {
        this.telefonoMovilObservacion = telefonoMovilObservacion;
    }

    public boolean getEsChofer() {
        return esChofer;
    }

    public void setEsChofer(boolean esChofer) {
        this.esChofer = esChofer;
    }

    public boolean getEsChoferLargaDistancia() {
        return esChoferLargaDistancia;
    }

    public void setEsChoferLargaDistancia(boolean esChoferLargaDistancia) {
        this.esChoferLargaDistancia = esChoferLargaDistancia;
    }

    public boolean getEsAcompReparto() {
        return esAcompReparto;
    }

    public void setEsAcompReparto(boolean esAcompReparto) {
        this.esAcompReparto = esAcompReparto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
}