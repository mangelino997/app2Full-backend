//Paquete al que pertenece la clase
package ar.com.wecoode.jitws.model;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
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
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    //Define el apellido
    @Column(name = "apellido", nullable = false)
    private String apellido;
    
    //Define el nombre completo
    @Column(name = "nombreCompleto", nullable = false)
    private String nombreCompleto;
    
    //Referencia a la clase Tipo de documento
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoDocumento", nullable = false)
    private TipoDocumento tipoDocumento;
    
    //Define el numero de documento
    @Column(name = "numeroDocumento", nullable = false)
    private String numeroDocumento;
    
    //Define el cuil
    @Column(name = "cuil", nullable = false)
    private String cuil;
    
    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
    
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
    @Column(name = "fechaNacimiento", nullable = false)
    private LocalDate fechaNacimiento; 
    
    //Define el telefono fijo
    @Column(name = "telefonoFijo", nullable = true)
    private String telefonoFijo;
    
    //Define el telefono movil
    @Column(name = "telefonoMovil", nullable = true)
    private String telefonoMovil;
    
    //Referencia a la clese Estado civil
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEstadoCivil", nullable = false)
    private EstadoCivil estadoCivil;
    
    //Define el correo electronico
    @Column(name = "correoelectronico", nullable = true)
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
    @Column(name = "fechaInicio", nullable = false)
    private LocalDate fechaInicio;
    
    //Define la fecha de fin
    @Column(name = "fechaFin", nullable = true)
    private LocalDate fechaFin;
    
    //Define la antiguedad anterior anios
    @Column(name = "antiguedadAntAnio", nullable = true)
    private short antiguedadAntAnio;
    
    //Define la antiguedad anterior mes
    @Column(name = "antiguedadAntMes", nullable = true)
    private short antiguedadAntMes;
    
    //Define el domicilio
    @Column(name = "domicilio", nullable = false)
    private String domicilio;
    
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
    @Column(name = "vtoLicenciaConducir", nullable = true)
    private LocalDate vtoLicenciaConducir;
    
    //Define el vencimiento del curso CNRT de un chofer
    @Column(name = "vtoCursoCNRT", nullable = true)
    private LocalDate vtoCursoCNRT;
    
    //Define el vencimiento de LNH
    @Column(name = "vtoLNH", nullable = true)
    private LocalDate vtoLNH;
    
    //Define el vencimiento de la libreta de sanidad
    @Column(name = "vtoLibretaSanidad", nullable = true)
    private LocalDate vtoLibretaSanidad;
    
    //Referencia a la clase Usuario (Modificacion LC)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioModLC", nullable = true)
    private Usuario usuarioModLC;
    
    //Referencia a la clase Usuario (Modificacion CNRT)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioModCNRT", nullable = true)
    private Usuario usuarioModCNRT;
    
    //Referencia a la clase Usuario (Modificacion LNH)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioModLNH", nullable = true)
    private Usuario usuarioModLNH;
    
    //Referencia a la clase Usuario (Modificacion LS)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioModLS", nullable = true)
    private Usuario usuarioModLS;
    
    //Define la fecha de modificacion de LC
    @Column(name = "fechaModLC", nullable = true)
    private LocalDate fechaModLC;
    
    //Define la fecha de modificacion de CNRT
    @Column(name = "fechaModCNRT", nullable = true)
    private LocalDate fechaModCNRT;
    
    //Define la fecha de modificacion de LNH
    @Column(name = "fechaModLNH", nullable = true)
    private LocalDate fechaModLNH;
    
    //Define la fecha de modificacion de LS
    @Column(name = "fechaModLS", nullable = true)
    private LocalDate fechaModLS;
    
    //Define el talle de camisa
    @Column(name = "talleCamisa", nullable = true)
    private String talleCamisa;
    
    //Define el talle de pantalon
    @Column(name = "tallePantalon", nullable = true)
    private String tallePantalon;
    
    //Define el talle de calzado
    @Column(name = "talleCalzado", nullable = true)
    private String talleCalzado;
    
    //Define el turno entrada mañana
    @Column(name = "turnoMEntrada", nullable = true)
    private Time turnoMEntrada;
    
    //Define el turno salida mañana
    @Column(name = "turnoMSalida", nullable = true)
    private Time turnoMSalida;
    
    //Define el turno entrada tarde
    @Column(name = "turnoTEntrada", nullable = true)
    private Time turnoTEntrada;
    
    //Define el turno salida tarde
    @Column(name = "turnoTSalida", nullable = true)
    private Time turnoTSalida;
    
    //Define el turno entrada noche
    @Column(name = "turnoNEntrada", nullable = true)
    private Time turnoNEntrada;
    
    //Define el turno salida noche
    @Column(name = "turnoNSalida", nullable = true)
    private Time turnoNSalida;
    
    //Define el turno entrada sabado
    @Column(name = "turnoSEntrada", nullable = true)
    private Time turnoSEntrada;
    
    //Define el turno salida sabado
    @Column(name = "turnoSSalida", nullable = true)
    private Time turnoSSalida;
    
    //Define el turno entrada domingo
    @Column(name = "turnoDEntrada", nullable = true)
    private Time turnoDEntrada;
    
    //Define el turno salida domingo
    @Column(name = "turnoDSalida", nullable = true)
    private Time turnoDSalida;
    
    //Define el turno rotativo
    @Column(name = "turnoRotativo", nullable = true)
    private boolean turnoRotativo;
    
    //Define el turno fuera convenio
    @Column(name = "turnoFueraConvenio", nullable = true)
    private boolean turnoFueraConvenio;
    
    //Define el telefono movil de la empresa
    @Column(name = "telefonoMovilEmpresa", nullable = true)
    private String telefonoMovilEmpresa;
    
    //Define el telefono movil fecha de entrega
    @Column(name = "telefonoMovilFechaEntrega", nullable = true)
    private LocalDate telefonoMovilFechaEntrega;
    
    //Define el telefono movil fecha de devolucion
    @Column(name = "telefonoMovilFechaDevolucion", nullable = true)
    private LocalDate telefonoMovilFechaDevolucion;
    
    //Define el telefono movil observacion
    @Column(name = "telefonoMovilObservacion", nullable = true)
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
    @Column(name = "observaciones", nullable = true)
    private String observaciones;
    
    //Define el alias para las busquedas
    @Column(name = "alias", nullable = true)
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
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

    public LocalDate getVtoLicenciaConducir() {
        return vtoLicenciaConducir;
    }

    public void setVtoLicenciaConducir(LocalDate vtoLicenciaConducir) {
        this.vtoLicenciaConducir = vtoLicenciaConducir;
    }

    public LocalDate getVtoCursoCNRT() {
        return vtoCursoCNRT;
    }

    public void setVtoCursoCNRT(LocalDate vtoCursoCNRT) {
        this.vtoCursoCNRT = vtoCursoCNRT;
    }

    public LocalDate getVtoLNH() {
        return vtoLNH;
    }

    public void setVtoLNH(LocalDate vtoLNH) {
        this.vtoLNH = vtoLNH;
    }

    public LocalDate getVtoLibretaSanidad() {
        return vtoLibretaSanidad;
    }

    public void setVtoLibretaSanidad(LocalDate vtoLibretaSanidad) {
        this.vtoLibretaSanidad = vtoLibretaSanidad;
    }

    public Usuario getUsuarioModLC() {
        return usuarioModLC;
    }

    public void setUsuarioModLC(Usuario usuarioModLC) {
        this.usuarioModLC = usuarioModLC;
    }

    public Usuario getUsuarioModCNRT() {
        return usuarioModCNRT;
    }

    public void setUsuarioModCNRT(Usuario usuarioModCNRT) {
        this.usuarioModCNRT = usuarioModCNRT;
    }

    public Usuario getUsuarioModLNH() {
        return usuarioModLNH;
    }

    public void setUsuarioModLNH(Usuario usuarioModLNH) {
        this.usuarioModLNH = usuarioModLNH;
    }

    public Usuario getUsuarioModLS() {
        return usuarioModLS;
    }

    public void setUsuarioModLS(Usuario usuarioModLS) {
        this.usuarioModLS = usuarioModLS;
    }

    public LocalDate getFechaModLC() {
        return fechaModLC;
    }

    public void setFechaModLC(LocalDate fechaModLC) {
        this.fechaModLC = fechaModLC;
    }

    public LocalDate getFechaModCNRT() {
        return fechaModCNRT;
    }

    public void setFechaModCNRT(LocalDate fechaModCNRT) {
        this.fechaModCNRT = fechaModCNRT;
    }

    public LocalDate getFechaModLNH() {
        return fechaModLNH;
    }

    public void setFechaModLNH(LocalDate fechaModLNH) {
        this.fechaModLNH = fechaModLNH;
    }

    public LocalDate getFechaModLS() {
        return fechaModLS;
    }

    public void setFechaModLS(LocalDate fechaModLS) {
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

    public LocalDate getTelefonoMovilFechaEntrega() {
        return telefonoMovilFechaEntrega;
    }

    public void setTelefonoMovilFechaEntrega(LocalDate telefonoMovilFechaEntrega) {
        this.telefonoMovilFechaEntrega = telefonoMovilFechaEntrega;
    }

    public LocalDate getTelefonoMovilFechaDevolucion() {
        return telefonoMovilFechaDevolucion;
    }

    public void setTelefonoMovilFechaDevolucion(LocalDate telefonoMovilFechaDevolucion) {
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