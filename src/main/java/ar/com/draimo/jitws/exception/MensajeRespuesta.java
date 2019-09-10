package ar.com.draimo.jitws.exception;

import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

/**
 *
 * @author blas
 */
public class MensajeRespuesta {

    /**
     * ** MENSAJES ***
     */
    public static final String AGREGADO = "Registro agregado con éxito";
    public static final String ACTUALIZADO = "Registro actualizado con éxito";
    public static final String ELIMINADO = "Registro eliminado con éxito";
    public static final String REASIGNADO = "Registro reasignado con éxito";
    public static final String SIN_CONTENIDO = "Sin contenido";
    public static final String LISTA_SIN_CONTENIDO = "Lista sin contenido";
    public static final String NO_AUTORIZADO = "Usuario o contraseña incorrecto";
    public static final String DATO_DUPLICADO = "Dato duplicado";
    public static final String ERROR_INTERNO_SERVIDOR = "Error interno en el servidor";
    public static final String ASIGNADOS = "Registro/s asignado/s con éxito";
    public static final String QUITADOS = "Registro/s quitado/s con éxito";
    public static final String TRANSACCION_NO_ACTUALIZADA = "Registro actualizado por otra transacción";
    public static final String ERROR_SINC_SOCKET = "Error de sincronización de datos";
    public static final String TABLA_REESTABLECIDA = "Tabla reestablecida con éxito";
    public static final String LONGITUD = "Cantidad caracteres excedida en";
    public static final String CERRADO = "Registro cerrado con exito";
    public static final String ANULADO = "Registro anulado con exito";
    public static final String NORMALIZADO = "Registro normalizado con exito";
    public static final String NO_EXISTENTE = "Registro no existente";
    public static final String ROL_ASIGNADO = "El rol esta asignado a un usuario";
    public static final String ELEMENTO_ASIGNADO = "El registro esta asignado en otro módulo";
    public static final String ELEMENTO_NO_NULL = "No puede estar vacío.";

    //Retorna mensaje con Response Entity de agrego con exito
    public static ResponseEntity<?> agregado(int id) {
        return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.CREADO, MensajeRespuesta.AGREGADO, (id + 1)), HttpStatus.CREATED);
    }

    //Retorna mensaje con Response Entity de actualizado con exito
    public static ResponseEntity<?> actualizado() {
        return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.OK,
                MensajeRespuesta.ACTUALIZADO, 0), HttpStatus.OK);
    }

    //Retorna mensaje con Response Entity de eliminado con exito
    public static ResponseEntity<?> eliminado() {
        return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.OK, MensajeRespuesta.ELIMINADO, 0), HttpStatus.OK);
    }

    //Retorna mensaje con Response Entity de anulado con exito
    public static ResponseEntity<?> anulado() {
        return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.OK, MensajeRespuesta.ANULADO, 0), HttpStatus.OK);
    }

    //Retorna mensaje con Response Entity de normalizado con exito
    public static ResponseEntity<?> normalizado() {
        return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.OK, MensajeRespuesta.NORMALIZADO, 0), HttpStatus.OK);
    }

    //Retorna mensaje con ReponseEntity de error interno en el servidor
    public static ResponseEntity<?> error() {
        return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.ERROR_INTERNO_SERVIDOR,
                MensajeRespuesta.ERROR_INTERNO_SERVIDOR, 0), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Retorna mensaje con ReponseEntity intentalo nuevamente
    public static ResponseEntity<?> seleccioneNuevosDatos() {
        List<String> mensajes = new ArrayList<>();
        mensajes.add("Para Cuenta bancaria-Tipo chequera: desde y hasta existentes");
        mensajes.add("Por favor, seleccione otros datos");
        return new ResponseEntity<>(new EstadoRespuestaChequera(CodigoRespuesta.ERROR_INTERNO_SERVIDOR,
                mensajes, 0), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Retorna mensaje con ReponseEntity de error de sincronizacion
    public static ResponseEntity<?> errorSincSocket() {
        return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.ERROR_SINC_SOCKET,
                MensajeRespuesta.ERROR_SINC_SOCKET, 0), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Retorna mensaje con ReponseEntity de error de sincronizacion
    public static ResponseEntity<?> ssssssss(JpaObjectRetrievalFailureException a) {
        String[] s = a.getLocalizedMessage().split(" ");
        String[] ss = s[3].split(".");

        return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.ERROR_SINC_SOCKET,
                MensajeRespuesta.ERROR_SINC_SOCKET, 0), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Retorna mensaje con ReponseEntity de transaccion no actualizada
    public static ResponseEntity<?> transaccionNoActualizada() {
        return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.TRANSACCION_NO_ACTUALIZADA,
                MensajeRespuesta.TRANSACCION_NO_ACTUALIZADA, 0), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Retorna mensaje con ReponseEntity de tabla reestablecida
    public static ResponseEntity<?> tablaReestablecida() {
        return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.OK,
                MensajeRespuesta.TABLA_REESTABLECIDA, 0), HttpStatus.OK);
    }

    //Retorna mensaje con ReponseEntity de asignado
    public static ResponseEntity<?> asignado() {
        return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.OK,
                MensajeRespuesta.ASIGNADOS, 0), HttpStatus.OK);
    }

    //Retorna mensaje con ReponseEntity de quitado
    public static ResponseEntity<?> quitado() {
        return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.OK,
                MensajeRespuesta.QUITADOS, 0), HttpStatus.OK);
    }

    //Retorna mensaje con ReponseEntity de cerrado
    public static ResponseEntity<?> cerrado() {
        return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.CERRADO_CON_EXITO,
                MensajeRespuesta.CERRADO, 0), HttpStatus.OK);
    }

    //Retorna mensaje con ReponseEntity de sin contenido
    public static ResponseEntity sinContenido() {
        return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.SIN_CONTENIDO,
                MensajeRespuesta.SIN_CONTENIDO, 0), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Retorna mensaje de dato duplicado 
    public static ResponseEntity<?> datoDuplicado() {
        return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO,
                MensajeRespuesta.DATO_DUPLICADO, 0), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Retorna mensaje de registro no existente
    public static ResponseEntity<?> registroNoExistente() {
        return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.NO_EXISTENTE,
                MensajeRespuesta.NO_EXISTENTE, 0), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Retorna mensaje de registro no existente
    public static ResponseEntity<?> rolAsignado() {
        return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.ROL_ASIGNADO,
                MensajeRespuesta.ROL_ASIGNADO, 0), HttpStatus.OK);
    }

    //Envia mensaje de dato repetido
    public static ResponseEntity<?> datoDuplicado(DataIntegrityViolationException dive) {
        //Obtiene el mensaje de duplicidad de datos
        String[] partes = dive.getMostSpecificCause().getMessage().split("'");
        String[] partes2 = dive.getMostSpecificCause().getMessage().split("`");
        String c = dive.getMostSpecificCause().getMessage();
        String[] parte3 = c.split(" ", 3);
        String mensajeRespuesta = MensajeRespuesta.ELEMENTO_ASIGNADO;
        int codigoRespuesta = CodigoRespuesta.ERROR_INTERNO_SERVIDOR;
        String plusMensaje = " ";
        switch (partes.length) {
            case 1:
                switch (partes2.length) {
                    case 1:
                        codigoRespuesta = CodigoRespuesta.ERROR_INTERNO_SERVIDOR;
                        mensajeRespuesta = partes[0];
                        plusMensaje = "";
                        break;
                    case 13:
                        return datoInexistente(parte3[1], dive.getMostSpecificCause().getMessage());
                }
                break;
            case 4:
                mensajeRespuesta = MensajeRespuesta.DATO_DUPLICADO;
                //Determina que columna tiene el dato duplicado
                switch (partes[3]) {
                    case DuplicidadError.ABREVIATURA_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_ABREVIATURA;
                        plusMensaje = " ABREVIATURA";
                        break;
                    case DuplicidadError.CODIGO_AFIP_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_CODIGO_AFIP;
                        plusMensaje = " CODIGO AFIP";
                        break;
                    case DuplicidadError.CORREOELECTRONICO_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_CORREOELECTRONICO;
                        plusMensaje = " CORREO ELECTRONICO";
                        break;
                    case DuplicidadError.CUIL_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_CUIL;
                        plusMensaje = " CUIL";
                        break;
                    case DuplicidadError.CUIT_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_CUIT;
                        plusMensaje = " CUIT";
                        break;
                    case DuplicidadError.DOCUMENTO_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_DOCUMENTO;
                        plusMensaje = " DNI";
                        break;
                    case DuplicidadError.DOMINIO_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_DOMINIO;
                        plusMensaje = " DOMINIO";
                        break;
                    case DuplicidadError.ID_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_ID;
                        plusMensaje = " ID";
                        break;
                    case DuplicidadError.NOMBRE_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_NOMBRE;
                        plusMensaje = " NOMBRE";
                        break;
                    case DuplicidadError.NUMERO_INTERNO_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_NUMERO_INTERNO;
                        plusMensaje = " NUMERO INTERNO";
                        break;
                    case DuplicidadError.ORDEN_VENTA_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_ORDEN_VENTA;
                        plusMensaje = " ORDEN VENTA";
                        break;
                    case DuplicidadError.TIPO_TARIFA_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_TIPO_TARIFA;
                        plusMensaje = ": TIPO TARIFA YA EXISTE EN LA TABLA";
                        break;
                    case DuplicidadError.USERNAME_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_USERNAME;
                        plusMensaje = " NOMBRE DE USUARIO";
                        break;
                    case DuplicidadError.ANIO_AFIP_DEDUCCION_GENERAL_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_ANIO_AFIP_DEDUCCION_GENERAL;
                        plusMensaje = " AÑO Y DEDUCCION GENERAL";
                        break;
                    case DuplicidadError.ANIO_BENEFICIO_DEDUCCION_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_ANIO_BENEFICIO_DEDUCCION;
                        plusMensaje = " AÑO, TIPO DE BENEFICIO, DEDUCCIÓN PERSONAL Y MES";
                        break;
                    case DuplicidadError.ANIO_IMPORTE_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_ANIO_IMPORTE;
                        plusMensaje = " AÑO E IMPORTE";
                        break;
                    case DuplicidadError.CLIENTE_ORDEN_VENTA_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_CLIENTE_ORDEN_VENTA;
                        plusMensaje = " CLIENTE Y ORDEN VENTA";
                        break;
                    case DuplicidadError.COMPRA_COMPROBANTE_PERCEPCION_PROVINCIA_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_COMPRA_COMPROBANTE;
                        plusMensaje = " COMPRA COMPROBANTE PERCEPCION Y JURISDICCIÓN";
                        break;
                    case DuplicidadError.CUENTA_DESDE_HASTA_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_CUENTA_DESDE_HASTA;
                        plusMensaje = " DESDE, HASTA Y CUENTA";
                        break;
                    case DuplicidadError.MONEDA_EMPRESA_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_MONEDA_EMPRESA;
                        plusMensaje = " MONEDA Y EMPRESA";
                        break;
                    case DuplicidadError.MONEDA_FECHA_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_MONEDA_FECHA;
                        plusMensaje = " MONEDA Y FECHA";
                        break;
                    case DuplicidadError.ORDEN_VENTA_TARIFA_ESCALA_TARIFA_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_ORDEN_VENTA_TARIFA_ESCALA;
                        plusMensaje = " ORDEN VENTA TARIFA Y ESCALA TARIFA";
                        break;
                    case DuplicidadError.ORDEN_VENTA_TARIFA_TRAMO_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_ORDEN_VENTA_TARIFA_TRAMO;
                        plusMensaje = " ORDEN VENTA TARIFA Y TRAMO";
                        break;
                    case DuplicidadError.PROVEEDOR_EMPRESA_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_PROVEEDOR_EMPRESA;
                        plusMensaje = " PROVEEDOR Y EMPRESA";
                        break;
                    case DuplicidadError.PROVEEDOR_TIPO_COMPROBANTE_NUMERO_COMPROBANTE_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_PROVEEDOR_TIPO_COMPROBANTE_NUMERO_COMPROBANTE;
                        plusMensaje = " PROVEEDOR, TIPO COMPROBANTE Y NUMERO DE COMPROBANTE";
                        break;
                    case DuplicidadError.REPARTO_ORDEN_RECOLECCION_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_REPARTO_ORECOLECCION;
                        plusMensaje = " REPARTO Y ORDEN RECOLECCION";
                        break;
                    case DuplicidadError.REPARTO_VENTA_COMPROBANTE_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_REPARTO_VCOMPROBANTE;
                        plusMensaje = " REPARTO Y VENTA COMPROBANTE";
                        break;
                    case DuplicidadError.REPARTO_VIAJE_REMITO_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_REPARTO_VREMITO;
                        plusMensaje = " REPARTO Y VIAJE REMITO";
                        break;
                    case DuplicidadError.RUBRO_PRODUCTO_CUENTA_CONTABLE_UNICO:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_RUBRO_PRODUCTO;
                        plusMensaje = ": RUBRO PRODUCTO Y EMPRESA";
                        break;
                    case DuplicidadError.UNICIDAD_TABLA:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_TRAMO;
                        plusMensaje = "";
                        break;
                    default:
                        codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO;
                        plusMensaje = " ";
                        break;
                }
                break;
            case 3:
                mensajeRespuesta = MensajeRespuesta.LONGITUD;
                if (partes[0] == "Truncation") {
                    switch (partes[1]) {
                        case LongitudError.FECHA_ALTA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_ALTA_LONGITUD;
                            plusMensaje = " FECHA ALTA";
                            break;
                        case LongitudError.FECHA_BAJA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_BAJA_LONGITUD;
                            plusMensaje = " FECHA BAJA";
                            break;
                        case LongitudError.FECHA_CAJA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_CAJA_LONGITUD;
                            plusMensaje = " FECHA CAJA";
                            break;
                        case LongitudError.FECHA_CIERRE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_CIERRE_LONGITUD;
                            plusMensaje = " FECHA CIERRE";
                            break;
                        case LongitudError.FECHA_COBRO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_COBRO_LONGITUD;
                            plusMensaje = " FECHA COBRO";
                            break;
                        case LongitudError.FECHA_CONTABLE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_CONTABLE_LONGITUD;
                            plusMensaje = " FECHA CONTABLE";
                            break;
                        case LongitudError.FECHA_DESDE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_DESDE_LONGITUD;
                            plusMensaje = " FECHA DESDE";
                            break;
                        case LongitudError.FECHA_EMISION_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_EMISION_LONGITUD;
                            plusMensaje = " FECHA EMISION";
                            break;
                        case LongitudError.FECHA_FIN_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_FIN_LONGITUD;
                            plusMensaje = " FECHA FIN";
                            break;
                        case LongitudError.FECHA_HASTA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_HASTA_LONGITUD;
                            plusMensaje = " FECHA HASTA";
                            break;
                        case LongitudError.FECHA_INICIO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_INICIO_LONGITUD;
                            plusMensaje = " FECHA INICIO";
                            break;
                        case LongitudError.FECHA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_LONGITUD;
                            plusMensaje = " FECHA";
                            break;
                        case LongitudError.FECHA_MOD_CURSO_CP_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_MOD_CURSO_CP_LONGITUD;
                            plusMensaje = " FECHA MOD CURSO CP";
                            break;
                        case LongitudError.FECHA_MOD_CURSO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_MOD_CURSO_LONGITUD;
                            plusMensaje = " FECHA MOD CURSO";
                            break;
                        case LongitudError.FECHA_MOD_LC_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_MOD_LC_LONGITUD;
                            plusMensaje = " FECHA MOD LIC. CONDUCIR";
                            break;
                        case LongitudError.FECHA_MOD_LINTI_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_MOD_LINTI_LONGITUD;
                            plusMensaje = " FECHA MOD LINTI";
                            break;
                        case LongitudError.FECHA_MOD_LS_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_MOD_LS_LONGITUD;
                            plusMensaje = " FECHA MOD LS";
                            break;
                        case LongitudError.FECHA_NACIMIENTO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_NACIMIENTO_LONGITUD;
                            plusMensaje = " FECHA NACIMIENTO";
                            break;
                        case LongitudError.FECHA_PAGO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_PAGO_LONGITUD;
                            plusMensaje = " FECHA PAGO";
                            break;
                        case LongitudError.FECHA_REGRESO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_REGRESO_LONGITUD;
                            plusMensaje = " FECHA REGRESO";
                            break;
                        case LongitudError.FECHA_SALIDA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_SALIDA_LONGITUD;
                            plusMensaje = " FECHA SALIDA";
                            break;
                        case LongitudError.FECHA_TOPE_INFORMAR_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_TOPE_INFORMAR_LONGITUD;
                            plusMensaje = " FECHA TOPE INFORMAR";
                            break;
                        case LongitudError.FECHA_TRAMO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_TRAMO_LONGITUD;
                            plusMensaje = " FECHA TRAMO";
                            break;
                        case LongitudError.FECHA_ULTIMA_MOD_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_ULTIMA_MOD_LONGITUD;
                            plusMensaje = " FECHA ULTIMA MOD.";
                            break;
                        case LongitudError.FECHA_VTO_PAGO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_VTO_PAGO_LONGITUD;
                            plusMensaje = " FECHA VTO. PAGO";
                            break;
                        case LongitudError.INICIO_ACTIVIDAD_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.INICIO_ACTIVIDAD_LONGITUD;
                            plusMensaje = " INICIO ACTIVIDAD";
                            break;
                        case LongitudError.PRECIOS_DESDE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.PRECIOS_DESDE_LONGITUD;
                            plusMensaje = " PRECIOS DESDE";
                            break;
                        case LongitudError.TELEFONO_MOVIL_FECHA_DEVOLUCION_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TELEFONO_MOVIL_FECHA_DEVOLUCION_LONGITUD;
                            plusMensaje = " TELEFONO MOVIL FECHA DEVOLUCION";
                            break;
                        case LongitudError.TELEFONO_MOVIL_FECHA_ENTREGA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TELEFONO_MOVIL_FECHA_ENTREGA_LONGITUD;
                            plusMensaje = " TELEFONO MOVIL FECHA ENTREGA";
                            break;
                        case LongitudError.VTO_CURSO_CARGA_PELIGROSA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_CURSO_CARGA_PELIGROSA_LONGITUD;
                            plusMensaje = " VTO. CURSO CARGA PELIGROSA";
                            break;
                        case LongitudError.VTO_CURSO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_CURSO_LONGITUD;
                            plusMensaje = " VTO. CURSO";
                            break;
                        case LongitudError.VTO_HAB_BROMATOLOGICA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_HAB_BROMATOLOGICA_LONGITUD;
                            plusMensaje = " VTO. HAB. BROMATOLÓGICA";
                            break;
                        case LongitudError.VTO_LIB_SANIDAD_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_LIB_SANIDAD_LONGITUD;
                            plusMensaje = " VTO. LIB. SANIDAD";
                            break;
                        case LongitudError.VTO_LIC_CONDUCIR_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_LIC_CONDUCIR_LONGITUD;
                            plusMensaje = " VTO. LIC. CONDUCIR";
                            break;
                        case LongitudError.VTO_LINTI_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_LINTI_LONGITUD;
                            plusMensaje = " VTO. LINTI";
                            break;
                        case LongitudError.VTO_POLIZA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_POLIZA_LONGITUD;
                            plusMensaje = " VTO. POLIZA";
                            break;
                        case LongitudError.VTO_POLIZA_SEGURO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_POLIZA_SEGURO_LONGITUD;
                            plusMensaje = " VTO. POLIZA SEGURO";
                            break;
                        case LongitudError.VTO_PSICOFISICO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_PSICOFISICO_LONGITUD;
                            plusMensaje = " VTO. PSICO-FÍSICO";
                            break;
                        case LongitudError.VTO_RTO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_RTO_LONGITUD;
                            plusMensaje = " VTO. RTO";
                            break;
                        case LongitudError.VTO_RUTA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_RUTA_LONGITUD;
                            plusMensaje = " VTO. RUTA";
                            break;
                        case LongitudError.VTO_SENASA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_SENASA_LONGITUD;
                            plusMensaje = " VTO. SENASA";
                            break;
                        case LongitudError.ABREVIATURA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.ABREVIATURA_LONGITUD;
                            plusMensaje = " ABREVIATURA";
                            break;
                        case LongitudError.ADICIONAL_BASICO_VACACIONES_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.ADICIONAL_BASICO_VACACIONES_LONGITUD;
                            plusMensaje = " ADICIONAL BASICO VACACIONES";
                            break;
                        case LongitudError.ANCHO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.ANCHO_LONGITUD;
                            plusMensaje = " ANCHO";
                            break;
                        case LongitudError.ALIAS_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.ALIAS_LONGITUD;
                            plusMensaje = " ALIAS";
                            break;
                        case LongitudError.ALIAS_CBU_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.ALIAS_CBU_LONGITUD;
                            plusMensaje = " ALIAS CBU";
                            break;
                        case LongitudError.ALICUOTA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.ALICUOTA_LONGITUD;
                            plusMensaje = " ALICUOTA";
                            break;
                        case LongitudError.ALICUOTA_IVA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.ALICUOTA_IVA_LONGITUD;
                            plusMensaje = " ALICUOTA IVA";
                            break;
                        case LongitudError.ALTURA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.ALTURA_LONGITUD;
                            plusMensaje = " ALTURA";
                            break;
                        case LongitudError.APELLIDO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.APELLIDO_LONGITUD;
                            plusMensaje = " APELLIDO";
                            break;
                        case LongitudError.APORTE_ADIC_OBRA_SOCIAL_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.APORTE_ADIC_OBRA_SOCIAL_LONGITUD;
                            plusMensaje = " APORTE ADIC. OBRA SOCIAL";
                            break;
                        case LongitudError.APORTE_ADIC_SEG_SOCIAL_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.APORTE_ADIC_SEG_SOCIAL_LONGITUD;
                            plusMensaje = " APORTE ADIC. SEGURIDAD SOCIAL";
                            break;
                        case LongitudError.APORTE_DIF_SEG_SOCIAL_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.APORTE_DIF_SEG_SOCIAL_LONGITUD;
                            plusMensaje = " APORTE DIF. SEGURIDAD SOCIAL";
                            break;
                        case LongitudError.BASICO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.BASICO_LONGITUD;
                            plusMensaje = " BASICO";
                            break;
                        case LongitudError.CANTIDAD_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CANTIDAD_LONGITUD;
                            plusMensaje = " CANTIDAD";
                            break;
                        case LongitudError.CAPACIDAD_CARGA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CAPACIDAD_CARGA_LONGITUD;
                            plusMensaje = " CAPACIDAD CARGA";
                            break;
                        case LongitudError.CODIGO_AFIP_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CODIGO_AFIP_LONGITUD;
                            plusMensaje = " CODIGO AFIP";
                            break;
                        case LongitudError.CODIGO_AREA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CODIGO_AREA_LONGITUD;
                            plusMensaje = " CODIGO DE AREA";
                            break;
                        case LongitudError.CODIGO_AREA_ALT_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CODIGO_AREA_ALT_LONGITUD;
                            plusMensaje = " CODIGO DE AREA ALT";
                            break;
                        case LongitudError.COMISION_CR_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.COMISION_CR_LONGITUD;
                            plusMensaje = " COMISION CR";
                            break;
                        case LongitudError.CONTRIB_ADIC_OBRA_SOCIAL_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CONTRIB_ADIC_OBRA_SOCIAL_LONGITUD;
                            plusMensaje = " CONTRIB. ADIC. OBRA SOCIAL";
                            break;
                        case LongitudError.CONTRIB_TAREA_DIF_SEG_SOCIAL_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CONTRIB_TAREA_DIF_SEG_SOCIAL_LONGITUD;
                            plusMensaje = " CONTRIB. TAREA DIF. SEGURIDAD SOCIAL";
                            break;
                        case LongitudError.CORREO_ELECTRONICO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CORREO_ELECTRONICO_LONGITUD;
                            plusMensaje = " CORREO ELECTRONICO";
                            break;
                        case LongitudError.COSTO_POR_KM_PROPIO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.COSTO_POR_KM_PROPIO_LONGITUD;
                            plusMensaje = " COSTO POR KM. PROPIO";
                            break;
                        case LongitudError.COSTO_POR_KM_TERCERO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.COSTO_POR_KM_TERCERO_LONGITUD;
                            plusMensaje = " COSTO POR KM. TERCERO";
                            break;
                        case LongitudError.COSTO_PROPIO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.COSTO_PROPIO_LONGITUD;
                            plusMensaje = " COSTO PROPIO";
                            break;
                        case LongitudError.COSTO_TERCERO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.COSTO_TERCERO_LONGITUD;
                            plusMensaje = " COSTO TERCERO";
                            break;
                        case LongitudError.CREDITO_LIMITE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CREDITO_LIMITE_LONGITUD;
                            plusMensaje = " CRÉDITO LÍMITE";
                            break;
                        case LongitudError.CUIL_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CUIL_LONGITUD;
                            plusMensaje = " CUIL";
                            break;
                        case LongitudError.CUIT_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CUIT_LONGITUD;
                            plusMensaje = " CUIT";
                            break;
                        case LongitudError.DESCRIPCION_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.DESCRIPCION_LONGITUD;
                            plusMensaje = " DESCRIPCION";
                            break;
                        case LongitudError.DESCUENTO_FLETE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.DESCUENTO_FLETE_LONGITUD;
                            plusMensaje = " DESCUENTO FLETE";
                            break;
                        case LongitudError.DESCUENTO_SUBTOTAL_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.DESCUENTO_SUBTOTAL_LONGITUD;
                            plusMensaje = " DESCUENTO SUBTOTAL";
                            break;
                        case LongitudError.DOCUMENTO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.DOCUMENTO_LONGITUD;
                            plusMensaje = " DNI";
                            break;
                        case LongitudError.DOMICILIO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.DOMICILIO_LONGITUD;
                            plusMensaje = " DOMICILIO";
                            break;
                        case LongitudError.DOMICILIO_ALT_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.DOMICILIO_ALT_LONGITUD;
                            plusMensaje = " DOMICILIO ALT";
                            break;
                        case LongitudError.DOMINIO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.DOMINIO_LONGITUD;
                            plusMensaje = " DOMINIO";
                            break;
                        case LongitudError.FLETE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FLETE_LONGITUD;
                            plusMensaje = " FLETE";
                            break;
                        case LongitudError.FOLIO_LIBRO_SUELDOS_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FOLIO_LIBRO_SUELDOS_LONGITUD;
                            plusMensaje = " FOLIO LIBRO SUELDOS";
                            break;
                        case LongitudError.IMPORTE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_LONGITUD;
                            plusMensaje = " IMPORTE";
                            break;
                        case LongitudError.IMPORTE_CONTRA_REEMBOLSO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_CONTRA_REEMBOLSO_LONGITUD;
                            plusMensaje = " IMPORTE CONTRA REEMBOLSO";
                            break;
                        case LongitudError.IMPORTE_ENTREGA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_ENTREGA_LONGITUD;
                            plusMensaje = " IMPORTE ENTREGA";
                            break;
                        case LongitudError.IMPORTE_EXENTO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_EXENTO_LONGITUD;
                            plusMensaje = " IMPORTE EXENTO";
                            break;
                        case LongitudError.IMPORTE_FIJO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_FIJO_LONGITUD;
                            plusMensaje = " IMPORTE FIJO";
                            break;
                        case LongitudError.IMPORTE_FIJO_REF_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_FIJO_REF_LONGITUD;
                            plusMensaje = " IMPORTE FIJO REF";
                            break;
                        case LongitudError.IMPORTE_FIJO_SECO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_FIJO_SECO_LONGITUD;
                            plusMensaje = " IMPORTE FIJO SECO";
                            break;
                        case LongitudError.IMPORTE_IMPUESTO_INTERNO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_IMPUESTO_INTERNO_LONGITUD;
                            plusMensaje = " IMPORTE IMPUESTO INTERNO";
                            break;
                        case LongitudError.IMPORTE_ITC_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_ITC_LONGITUD;
                            plusMensaje = " IMPORTE ITC";
                            break;
                        case LongitudError.IMPORTE_IVA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_IVA_LONGITUD;
                            plusMensaje = " IMPORTE IVA";
                            break;
                        case LongitudError.IMPORTE_NETO_GRAVADO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_NETO_GRAVADO_LONGITUD;
                            plusMensaje = " IMPORTE NETO GRAVADO";
                            break;
                        case LongitudError.IMPORTE_NO_GRAVADO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_NO_GRAVADO_LONGITUD;
                            plusMensaje = " IMPORTE NO GRAVADO";
                            break;
                        case LongitudError.IMPORTE_OTROS_TRIBUTOS_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_OTROS_TRIBUTOS_LONGITUD;
                            plusMensaje = " IMPORTE OTROS TRIBUTOS";
                            break;
                        case LongitudError.IMPORTE_PERCEPCION_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_PERCEPCION_LONGITUD;
                            plusMensaje = " IMPORTE PERCEPCION";
                            break;
                        case LongitudError.IMPORTE_RETIRO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_RETIRO_LONGITUD;
                            plusMensaje = " IMPORTE RETIRO";
                            break;
                        case LongitudError.IMPORTE_SALDO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_SALDO_LONGITUD;
                            plusMensaje = " IMPORTE SALDO";
                            break;
                        case LongitudError.IMPORTE_SEGURO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_SEGURO_LONGITUD;
                            plusMensaje = " IMPORTE SEGURO";
                            break;
                        case LongitudError.IMPORTE_TOTAL_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_TOTAL_LONGITUD;
                            plusMensaje = " IMPORTE TOTAL";
                            break;
                        case LongitudError.IMPORTE_VENTA_ITEM_CONCEPTO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_VENTA_ITEM_CONCEPTO_LONGITUD;
                            plusMensaje = " IMPORTE VENTA ITEM CONCEPTO";
                            break;
                        case LongitudError.ITC_NETO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.ITC_NETO_LONGITUD;
                            plusMensaje = " ITC NETO";
                            break;
                        case LongitudError.ITC_POR_LITRO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.ITC_POR_LITRO_LONGITUD;
                            plusMensaje = " ITC POR LITRO";
                            break;
                        case LongitudError.KILOS_AFORADO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.KILOS_AFORADO_LONGITUD;
                            plusMensaje = " KILOS AFORADO";
                            break;
                        case LongitudError.KILOS_EFECTIVO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.KILOS_EFECTIVO_LONGITUD;
                            plusMensaje = " KILOS EFECTIVO";
                            break;
                        case LongitudError.LARGO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.LARGO_LONGITUD;
                            plusMensaje = " LARGO";
                            break;
                        case LongitudError.LETRA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.LETRA_LONGITUD;
                            plusMensaje = " LETRA";
                            break;
                        case LongitudError.M3_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.M3_LONGITUD;
                            plusMensaje = " M3";
                            break;
                        case LongitudError.MODELO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.MODELO_LONGITUD;
                            plusMensaje = " MODELO";
                            break;
                        case LongitudError.MONEDA_COTIZACION_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.MONEDA_COTIZACION_LONGITUD;
                            plusMensaje = " MONEDA COTIZACION";
                            break;
                        case LongitudError.NOMBRE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NOMBRE_LONGITUD;
                            plusMensaje = " NOMBRE";
                            break;
                        case LongitudError.NOMBRE_COMPLETO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NOMBRE_COMPLETO_LONGITUD;
                            plusMensaje = " NOMBRE COMPLETO";
                            break;
                        case LongitudError.NOMBRE_FANTASIA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NOMBRE_FANTASIA_LONGITUD;
                            plusMensaje = " NOMBRE FANTASIA";
                            break;
                        case LongitudError.NOTA_EMISION_COMPROBANTE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NOTA_EMISION_COMPROBANTE_LONGITUD;
                            plusMensaje = " NOTA EMISION COMPROBANTE";
                            break;
                        case LongitudError.NOTA_INGRESAR_COMPROBANTE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NOTA_INGRESAR_COMPROBANTE_LONGITUD;
                            plusMensaje = " NOTA INGRESAR COMPROBANTE";
                            break;
                        case LongitudError.NOTA_IMPRESION_COMPROBANTE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NOTA_IMPRESION_COMPROBANTE_LONGITUD;
                            plusMensaje = " NOTA IMPRESION COMPROBANTE";
                            break;
                        case LongitudError.NOTA_IMPRESION_COMPROBANTE_GRAL_1_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NOTA_IMPRESION_COMPROBANTE_GRAL_1_LONGITUD;
                            plusMensaje = " NOTA IMPRESION COMPROBANTE GENERAL 1";
                            break;
                        case LongitudError.NOTA_IMPRESION_COMPROBANTE_GRAL_2_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NOTA_IMPRESION_COMPROBANTE_GRAL_2_LONGITUD;
                            plusMensaje = " NOTA IMPRESION COMPROBANTE GENERAL 2";
                            break;
                        case LongitudError.NOTA_IMPRESION_ORDEN_PAGO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NOTA_IMPRESION_ORDEN_PAGO_LONGITUD;
                            plusMensaje = " NOTA IMPRESION ORDEN PAGO";
                            break;
                        case LongitudError.NOTA_IMPRESION_REMITO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NOTA_IMPRESION_REMITO_LONGITUD;
                            plusMensaje = " NOTA IMPRESION REMITO";
                            break;
                        case LongitudError.NUMERO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NUMERO_LONGITUD;
                            plusMensaje = " NUMERO";
                            break;
                        case LongitudError.NUMERO_CAEA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NUMERO_CAEA_LONGITUD;
                            plusMensaje = " NUMERO CAEA";
                            break;
                        case LongitudError.NUMERO_CBU_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CBU_LONGITUD;
                            plusMensaje = " NUMERO CBU";
                            break;
                        case LongitudError.NUMERO_CHASIS_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NUMERO_CHASIS_LONGITUD;
                            plusMensaje = " NUMERO CHASIS";
                            break;
                        case LongitudError.NUMERO_CUENTA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NUMERO_CUENTA_LONGITUD;
                            plusMensaje = " NUMERO CUENTA";
                            break;
                        case LongitudError.NUMERO_DOCUMENTO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.DOCUMENTO_LONGITUD;
                            plusMensaje = " DNI";
                            break;
                        case LongitudError.NUMERO_IIBB_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NUMERO_IIBB_LONGITUD;
                            plusMensaje = " NUMERO IIBB";
                            break;
                        case LongitudError.NUMERO_INTERNO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NUMERO_INTERNO_LONGITUD;
                            plusMensaje = " NUMERO INTERNO";
                            break;
                        case LongitudError.NUMERO_MOTOR_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NUMERO_MOTOR_LONGITUD;
                            plusMensaje = " NUMERO MOTOR";
                            break;
                        case LongitudError.NUMERO_POLIZA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NUMERO_POLIZA_LONGITUD;
                            plusMensaje = " NUMERO POLIZA";
                            break;
                        case LongitudError.NUMERO_POLIZA_SEGURO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NUMERO_POLIZA_SEGURO_LONGITUD;
                            plusMensaje = " NUMERO POLIZA SEGURO";
                            break;
                        case LongitudError.NUMERO_REMITO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NUMERO_REMITO_LONGITUD;
                            plusMensaje = " NUMERO REMITO";
                            break;
                        case LongitudError.NUMERO_RUTA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NUMERO_RUTA_LONGITUD;
                            plusMensaje = " NUMERO RUTA";
                            break;
                        case LongitudError.OBSERVACION_CHOFER_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.OBSERVACION_CHOFER_LONGITUD;
                            plusMensaje = " OBSERVACION CHOFER";
                            break;
                        case LongitudError.OBSERVACION_VEHICULO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.OBSERVACION_VEHICULO_LONGITUD;
                            plusMensaje = " OBSERVACION VEHIHCULO";
                            break;
                        case LongitudError.OBSERVACION_VEHICULO_REMOLQUE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.OBSERVACION_VEHICULO_REMOLQUE_LONGITUD;
                            plusMensaje = " OBSERVACION VEHICULO REMOLQUE";
                            break;
                        case LongitudError.OBSERVACIONES_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.OBSERVACIONES_LONGITUD;
                            plusMensaje = " OBSERVACIONES";
                            break;
                        case LongitudError.OBSERVACIONES_ANULADO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.OBSERVACIONES_ANULADO_LONGITUD;
                            plusMensaje = " OBSERVACIONES ANULADO";
                            break;
                        case LongitudError.PASSWORD_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.PASSWORD_LONGITUD;
                            plusMensaje = " PASSWORD";
                            break;
                        case LongitudError.P_COMISION_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.P_COMISION_LONGITUD;
                            plusMensaje = " P. COMISIÓN";
                            break;
                        case LongitudError.P_SEGURO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.P_SEGURO_LONGITUD;
                            plusMensaje = " P. SEGURO";
                            break;
                        case LongitudError.PRECIO_UNITARIO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.PRECIO_UNITARIO_LONGITUD;
                            plusMensaje = " PRECIO UNITARIO";
                            break;
                        case LongitudError.PRECIO_UNITARIO_VENTA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.PRECIO_UNITARIO_VENTA_LONGITUD;
                            plusMensaje = " PRECIO UNITARIO VENTA";
                            break;
                        case LongitudError.PRECIO_UNITARIO_VIAJE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.PRECIO_UNITARIO_VIAJE_LONGITUD;
                            plusMensaje = " PRECIO UNITARIO VIAJE";
                            break;
                        case LongitudError.PRECIO_UNITARIO_REF_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.PRECIO_UNITARIO_REF_LONGITUD;
                            plusMensaje = " PRECIO UNITARIO REF";
                            break;
                        case LongitudError.PRECIO_UNITARIO_SECO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.PRECIO_UNITARIO_SECO_LONGITUD;
                            plusMensaje = " PRECIO UNITARIO SECO";
                            break;
                        case LongitudError.PUNTO_VENTA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.PUNTO_VENTA_LONGITUD;
                            plusMensaje = " PUNTO VENTA";
                            break;
                        case LongitudError.RAZON_SOCIAL_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.RAZON_SOCIAL_LONGITUD;
                            plusMensaje = " RAZON SOCIAL";
                            break;
                        case LongitudError.RUTA_ALTERNATIVA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.RUTA_ALTERNATIVA_LONGITUD;
                            plusMensaje = " RUTA ALTERNATIVA";
                            break;
                        case LongitudError.SEGURO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.SEGURO_LONGITUD;
                            plusMensaje = " SEGURO";
                            break;
                        case LongitudError.SIMBOLO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.SIMBOLO_LONGITUD;
                            plusMensaje = " SIMBOLO";
                            break;
                        case LongitudError.SITIO_WEB_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.SITIO_WEB_LONGITUD;
                            plusMensaje = " SITIO WEB";
                            break;
                        case LongitudError.SMVM_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.SMVM_LONGITUD;
                            plusMensaje = " SMVM";
                            break;
                        case LongitudError.TALLE_CAMISA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TALLE_CAMISA_LONGITUD;
                            plusMensaje = " TALLE CAMISA";
                            break;
                        case LongitudError.TALLE_CALZADO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TALLE_CALZADO_LONGITUD;
                            plusMensaje = " TALLE CAlZADO";
                            break;
                        case LongitudError.TALLE_PANTALON_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TALLE_PANTALON_LONGITUD;
                            plusMensaje = " TALLE PANTALON";
                            break;
                        case LongitudError.TARA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TARA_LONGITUD;
                            plusMensaje = " TARA";
                            break;
                        case LongitudError.TELEFONO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TELEFONO_LONGITUD;
                            plusMensaje = " TELEFONO";
                            break;
                        case LongitudError.TELEFONO_ALT_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TELEFONO_ALT_LONGITUD;
                            plusMensaje = " TELEFONO ALTERNATIVO";
                            break;
                        case LongitudError.TELEFONO_FIJO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TELEFONO_FIJO_LONGITUD;
                            plusMensaje = " TELEFONO FIJO";
                            break;
                        case LongitudError.TELEFONO_MOVIL_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TELEFONO_MOVIL_LONGITUD;
                            plusMensaje = " TELEFONO MOVIL";
                            break;
                        case LongitudError.TELEFONO_MOVIL_EMPRESA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TELEFONO_MOVIL_EMPRESA_LONGITUD;
                            plusMensaje = " TELEFONO MOVIL EMPRESA";
                            break;
                        case LongitudError.TELEFONO_MOVIL_OBSERVACION_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TELEFONO_MOVIL_OBSERVACION_LONGITUD;
                            plusMensaje = " TELEFONO MOVIL OBSERVACION";
                            break;
                        case LongitudError.TITULAR_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TITULAR_LONGITUD;
                            plusMensaje = " TITULAR";
                            break;
                        case LongitudError.TOPE_BASICO_ADELANTOS_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TOPE_BASICO_ADELANTOS_LONGITUD;
                            plusMensaje = " TOPE BASICO ADELANTOS";
                            break;
                        case LongitudError.USERNAME_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.USERNAME_LONGITUD;
                            plusMensaje = " USUARIO";
                            break;
                        case LongitudError.URL_PRUEBA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.URL_PRUEBA_LONGITUD;
                            plusMensaje = " URL PRUEBA";
                            break;
                        case LongitudError.URL_REAL_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.URL_REAL_LONGITUD;
                            plusMensaje = " URL REAL";
                            break;
                        case LongitudError.VALOR_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VALOR_LONGITUD;
                            plusMensaje = " VALOR";
                            break;
                        case LongitudError.VALOR_DECLARADO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VALOR_DECLARADO_LONGITUD;
                            plusMensaje = " VALOR DECLARADO";
                            break;
                        default:
                            codigoRespuesta = CodigoRespuesta.LONGITUD;
                            plusMensaje = "";
                    }
                } else if(partes[0]=="Column") {
                     switch (partes[1]) {
                        case LongitudError.FECHA_ALTA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_ALTA_LONGITUD;
                            plusMensaje = " FECHA ALTA";
                            break;
                        case LongitudError.FECHA_BAJA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_BAJA_LONGITUD;
                            plusMensaje = " FECHA BAJA";
                            break;
                        case LongitudError.FECHA_CAJA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_CAJA_LONGITUD;
                            plusMensaje = " FECHA CAJA";
                            break;
                        case LongitudError.FECHA_CIERRE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_CIERRE_LONGITUD;
                            plusMensaje = " FECHA CIERRE";
                            break;
                        case LongitudError.FECHA_COBRO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_COBRO_LONGITUD;
                            plusMensaje = " FECHA COBRO";
                            break;
                        case LongitudError.FECHA_CONTABLE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_CONTABLE_LONGITUD;
                            plusMensaje = " FECHA CONTABLE";
                            break;
                        case LongitudError.FECHA_DESDE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_DESDE_LONGITUD;
                            plusMensaje = " FECHA DESDE";
                            break;
                        case LongitudError.FECHA_EMISION_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_EMISION_LONGITUD;
                            plusMensaje = " FECHA EMISION";
                            break;
                        case LongitudError.FECHA_FIN_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_FIN_LONGITUD;
                            plusMensaje = " FECHA FIN";
                            break;
                        case LongitudError.FECHA_HASTA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_HASTA_LONGITUD;
                            plusMensaje = " FECHA HASTA";
                            break;
                        case LongitudError.FECHA_INICIO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_INICIO_LONGITUD;
                            plusMensaje = " FECHA INICIO";
                            break;
                        case LongitudError.FECHA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_LONGITUD;
                            plusMensaje = " FECHA";
                            break;
                        case LongitudError.FECHA_MOD_CURSO_CP_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_MOD_CURSO_CP_LONGITUD;
                            plusMensaje = " FECHA MOD CURSO CP";
                            break;
                        case LongitudError.FECHA_MOD_CURSO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_MOD_CURSO_LONGITUD;
                            plusMensaje = " FECHA MOD CURSO";
                            break;
                        case LongitudError.FECHA_MOD_LC_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_MOD_LC_LONGITUD;
                            plusMensaje = " FECHA MOD LIC. CONDUCIR";
                            break;
                        case LongitudError.FECHA_MOD_LINTI_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_MOD_LINTI_LONGITUD;
                            plusMensaje = " FECHA MOD LINTI";
                            break;
                        case LongitudError.FECHA_MOD_LS_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_MOD_LS_LONGITUD;
                            plusMensaje = " FECHA MOD LS";
                            break;
                        case LongitudError.FECHA_NACIMIENTO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_NACIMIENTO_LONGITUD;
                            plusMensaje = " FECHA NACIMIENTO";
                            break;
                        case LongitudError.FECHA_PAGO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_PAGO_LONGITUD;
                            plusMensaje = " FECHA PAGO";
                            break;
                        case LongitudError.FECHA_REGRESO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_REGRESO_LONGITUD;
                            plusMensaje = " FECHA REGRESO";
                            break;
                        case LongitudError.FECHA_SALIDA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_SALIDA_LONGITUD;
                            plusMensaje = " FECHA SALIDA";
                            break;
                        case LongitudError.FECHA_TOPE_INFORMAR_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_TOPE_INFORMAR_LONGITUD;
                            plusMensaje = " FECHA TOPE INFORMAR";
                            break;
                        case LongitudError.FECHA_TRAMO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_TRAMO_LONGITUD;
                            plusMensaje = " FECHA TRAMO";
                            break;
                        case LongitudError.FECHA_ULTIMA_MOD_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_ULTIMA_MOD_LONGITUD;
                            plusMensaje = " FECHA ULTIMA MOD.";
                            break;
                        case LongitudError.FECHA_VTO_PAGO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FECHA_VTO_PAGO_LONGITUD;
                            plusMensaje = " FECHA VTO. PAGO";
                            break;
                        case LongitudError.INICIO_ACTIVIDAD_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.INICIO_ACTIVIDAD_LONGITUD;
                            plusMensaje = " INICIO ACTIVIDAD";
                            break;
                        case LongitudError.PRECIOS_DESDE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.PRECIOS_DESDE_LONGITUD;
                            plusMensaje = " PRECIOS DESDE";
                            break;
                        case LongitudError.TELEFONO_MOVIL_FECHA_DEVOLUCION_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TELEFONO_MOVIL_FECHA_DEVOLUCION_LONGITUD;
                            plusMensaje = " TELEFONO MOVIL FECHA DEVOLUCION";
                            break;
                        case LongitudError.TELEFONO_MOVIL_FECHA_ENTREGA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TELEFONO_MOVIL_FECHA_ENTREGA_LONGITUD;
                            plusMensaje = " TELEFONO MOVIL FECHA ENTREGA";
                            break;
                        case LongitudError.VTO_CURSO_CARGA_PELIGROSA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_CURSO_CARGA_PELIGROSA_LONGITUD;
                            plusMensaje = " VTO. CURSO CARGA PELIGROSA";
                            break;
                        case LongitudError.VTO_CURSO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_CURSO_LONGITUD;
                            plusMensaje = " VTO. CURSO";
                            break;
                        case LongitudError.VTO_HAB_BROMATOLOGICA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_HAB_BROMATOLOGICA_LONGITUD;
                            plusMensaje = " VTO. HAB. BROMATOLÓGICA";
                            break;
                        case LongitudError.VTO_LIB_SANIDAD_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_LIB_SANIDAD_LONGITUD;
                            plusMensaje = " VTO. LIB. SANIDAD";
                            break;
                        case LongitudError.VTO_LIC_CONDUCIR_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_LIC_CONDUCIR_LONGITUD;
                            plusMensaje = " VTO. LIC. CONDUCIR";
                            break;
                        case LongitudError.VTO_LINTI_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_LINTI_LONGITUD;
                            plusMensaje = " VTO. LINTI";
                            break;
                        case LongitudError.VTO_POLIZA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_POLIZA_LONGITUD;
                            plusMensaje = " VTO. POLIZA";
                            break;
                        case LongitudError.VTO_POLIZA_SEGURO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_POLIZA_SEGURO_LONGITUD;
                            plusMensaje = " VTO. POLIZA SEGURO";
                            break;
                        case LongitudError.VTO_PSICOFISICO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_PSICOFISICO_LONGITUD;
                            plusMensaje = " VTO. PSICO-FÍSICO";
                            break;
                        case LongitudError.VTO_RTO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_RTO_LONGITUD;
                            plusMensaje = " VTO. RTO";
                            break;
                        case LongitudError.VTO_RUTA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_RUTA_LONGITUD;
                            plusMensaje = " VTO. RUTA";
                            break;
                        case LongitudError.VTO_SENASA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VTO_SENASA_LONGITUD;
                            plusMensaje = " VTO. SENASA";
                            break;
                        case LongitudError.ABREVIATURA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.ABREVIATURA_LONGITUD;
                            plusMensaje = " ABREVIATURA";
                            break;
                        case LongitudError.ADICIONAL_BASICO_VACACIONES_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.ADICIONAL_BASICO_VACACIONES_LONGITUD;
                            plusMensaje = " ADICIONAL BASICO VACACIONES";
                            break;
                        case LongitudError.ANCHO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.ANCHO_LONGITUD;
                            plusMensaje = " ANCHO";
                            break;
                        case LongitudError.ALIAS_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.ALIAS_LONGITUD;
                            plusMensaje = " ALIAS";
                            break;
                        case LongitudError.ALIAS_CBU_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.ALIAS_CBU_LONGITUD;
                            plusMensaje = " ALIAS CBU";
                            break;
                        case LongitudError.ALICUOTA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.ALICUOTA_LONGITUD;
                            plusMensaje = " ALICUOTA";
                            break;
                        case LongitudError.ALICUOTA_IVA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.ALICUOTA_IVA_LONGITUD;
                            plusMensaje = " ALICUOTA IVA";
                            break;
                        case LongitudError.ALTURA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.ALTURA_LONGITUD;
                            plusMensaje = " ALTURA";
                            break;
                        case LongitudError.APELLIDO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.APELLIDO_LONGITUD;
                            plusMensaje = " APELLIDO";
                            break;
                        case LongitudError.APORTE_ADIC_OBRA_SOCIAL_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.APORTE_ADIC_OBRA_SOCIAL_LONGITUD;
                            plusMensaje = " APORTE ADIC. OBRA SOCIAL";
                            break;
                        case LongitudError.APORTE_ADIC_SEG_SOCIAL_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.APORTE_ADIC_SEG_SOCIAL_LONGITUD;
                            plusMensaje = " APORTE ADIC. SEGURIDAD SOCIAL";
                            break;
                        case LongitudError.APORTE_DIF_SEG_SOCIAL_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.APORTE_DIF_SEG_SOCIAL_LONGITUD;
                            plusMensaje = " APORTE DIF. SEGURIDAD SOCIAL";
                            break;
                        case LongitudError.BASICO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.BASICO_LONGITUD;
                            plusMensaje = " BASICO";
                            break;
                        case LongitudError.CANTIDAD_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CANTIDAD_LONGITUD;
                            plusMensaje = " CANTIDAD";
                            break;
                        case LongitudError.CAPACIDAD_CARGA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CAPACIDAD_CARGA_LONGITUD;
                            plusMensaje = " CAPACIDAD CARGA";
                            break;
                        case LongitudError.CODIGO_AFIP_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CODIGO_AFIP_LONGITUD;
                            plusMensaje = " CODIGO AFIP";
                            break;
                        case LongitudError.CODIGO_AREA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CODIGO_AREA_LONGITUD;
                            plusMensaje = " CODIGO DE AREA";
                            break;
                        case LongitudError.CODIGO_AREA_ALT_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CODIGO_AREA_ALT_LONGITUD;
                            plusMensaje = " CODIGO DE AREA ALT";
                            break;
                        case LongitudError.COMISION_CR_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.COMISION_CR_LONGITUD;
                            plusMensaje = " COMISION CR";
                            break;
                        case LongitudError.CONTRIB_ADIC_OBRA_SOCIAL_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CONTRIB_ADIC_OBRA_SOCIAL_LONGITUD;
                            plusMensaje = " CONTRIB. ADIC. OBRA SOCIAL";
                            break;
                        case LongitudError.CONTRIB_TAREA_DIF_SEG_SOCIAL_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CONTRIB_TAREA_DIF_SEG_SOCIAL_LONGITUD;
                            plusMensaje = " CONTRIB. TAREA DIF. SEGURIDAD SOCIAL";
                            break;
                        case LongitudError.CORREO_ELECTRONICO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CORREO_ELECTRONICO_LONGITUD;
                            plusMensaje = " CORREO ELECTRONICO";
                            break;
                        case LongitudError.COSTO_POR_KM_PROPIO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.COSTO_POR_KM_PROPIO_LONGITUD;
                            plusMensaje = " COSTO POR KM. PROPIO";
                            break;
                        case LongitudError.COSTO_POR_KM_TERCERO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.COSTO_POR_KM_TERCERO_LONGITUD;
                            plusMensaje = " COSTO POR KM. TERCERO";
                            break;
                        case LongitudError.COSTO_PROPIO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.COSTO_PROPIO_LONGITUD;
                            plusMensaje = " COSTO PROPIO";
                            break;
                        case LongitudError.COSTO_TERCERO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.COSTO_TERCERO_LONGITUD;
                            plusMensaje = " COSTO TERCERO";
                            break;
                        case LongitudError.CREDITO_LIMITE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CREDITO_LIMITE_LONGITUD;
                            plusMensaje = " CRÉDITO LÍMITE";
                            break;
                        case LongitudError.CUIL_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CUIL_LONGITUD;
                            plusMensaje = " CUIL";
                            break;
                        case LongitudError.CUIT_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CUIT_LONGITUD;
                            plusMensaje = " CUIT";
                            break;
                        case LongitudError.DESCRIPCION_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.DESCRIPCION_LONGITUD;
                            plusMensaje = " DESCRIPCION";
                            break;
                        case LongitudError.DESCUENTO_FLETE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.DESCUENTO_FLETE_LONGITUD;
                            plusMensaje = " DESCUENTO FLETE";
                            break;
                        case LongitudError.DESCUENTO_SUBTOTAL_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.DESCUENTO_SUBTOTAL_LONGITUD;
                            plusMensaje = " DESCUENTO SUBTOTAL";
                            break;
                        case LongitudError.DOCUMENTO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.DOCUMENTO_LONGITUD;
                            plusMensaje = " DNI";
                            break;
                        case LongitudError.DOMICILIO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.DOMICILIO_LONGITUD;
                            plusMensaje = " DOMICILIO";
                            break;
                        case LongitudError.DOMICILIO_ALT_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.DOMICILIO_ALT_LONGITUD;
                            plusMensaje = " DOMICILIO ALT";
                            break;
                        case LongitudError.DOMINIO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.DOMINIO_LONGITUD;
                            plusMensaje = " DOMINIO";
                            break;
                        case LongitudError.FLETE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FLETE_LONGITUD;
                            plusMensaje = " FLETE";
                            break;
                        case LongitudError.FOLIO_LIBRO_SUELDOS_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.FOLIO_LIBRO_SUELDOS_LONGITUD;
                            plusMensaje = " FOLIO LIBRO SUELDOS";
                            break;
                        case LongitudError.IMPORTE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_LONGITUD;
                            plusMensaje = " IMPORTE";
                            break;
                        case LongitudError.IMPORTE_CONTRA_REEMBOLSO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_CONTRA_REEMBOLSO_LONGITUD;
                            plusMensaje = " IMPORTE CONTRA REEMBOLSO";
                            break;
                        case LongitudError.IMPORTE_ENTREGA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_ENTREGA_LONGITUD;
                            plusMensaje = " IMPORTE ENTREGA";
                            break;
                        case LongitudError.IMPORTE_EXENTO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_EXENTO_LONGITUD;
                            plusMensaje = " IMPORTE EXENTO";
                            break;
                        case LongitudError.IMPORTE_FIJO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_FIJO_LONGITUD;
                            plusMensaje = " IMPORTE FIJO";
                            break;
                        case LongitudError.IMPORTE_FIJO_REF_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_FIJO_REF_LONGITUD;
                            plusMensaje = " IMPORTE FIJO REF";
                            break;
                        case LongitudError.IMPORTE_FIJO_SECO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_FIJO_SECO_LONGITUD;
                            plusMensaje = " IMPORTE FIJO SECO";
                            break;
                        case LongitudError.IMPORTE_IMPUESTO_INTERNO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_IMPUESTO_INTERNO_LONGITUD;
                            plusMensaje = " IMPORTE IMPUESTO INTERNO";
                            break;
                        case LongitudError.IMPORTE_ITC_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_ITC_LONGITUD;
                            plusMensaje = " IMPORTE ITC";
                            break;
                        case LongitudError.IMPORTE_IVA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_IVA_LONGITUD;
                            plusMensaje = " IMPORTE IVA";
                            break;
                        case LongitudError.IMPORTE_NETO_GRAVADO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_NETO_GRAVADO_LONGITUD;
                            plusMensaje = " IMPORTE NETO GRAVADO";
                            break;
                        case LongitudError.IMPORTE_NO_GRAVADO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_NO_GRAVADO_LONGITUD;
                            plusMensaje = " IMPORTE NO GRAVADO";
                            break;
                        case LongitudError.IMPORTE_OTROS_TRIBUTOS_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_OTROS_TRIBUTOS_LONGITUD;
                            plusMensaje = " IMPORTE OTROS TRIBUTOS";
                            break;
                        case LongitudError.IMPORTE_PERCEPCION_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_PERCEPCION_LONGITUD;
                            plusMensaje = " IMPORTE PERCEPCION";
                            break;
                        case LongitudError.IMPORTE_RETIRO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_RETIRO_LONGITUD;
                            plusMensaje = " IMPORTE RETIRO";
                            break;
                        case LongitudError.IMPORTE_SALDO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_SALDO_LONGITUD;
                            plusMensaje = " IMPORTE SALDO";
                            break;
                        case LongitudError.IMPORTE_SEGURO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_SEGURO_LONGITUD;
                            plusMensaje = " IMPORTE SEGURO";
                            break;
                        case LongitudError.IMPORTE_TOTAL_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_TOTAL_LONGITUD;
                            plusMensaje = " IMPORTE TOTAL";
                            break;
                        case LongitudError.IMPORTE_VENTA_ITEM_CONCEPTO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.IMPORTE_VENTA_ITEM_CONCEPTO_LONGITUD;
                            plusMensaje = " IMPORTE VENTA ITEM CONCEPTO";
                            break;
                        case LongitudError.ITC_NETO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.ITC_NETO_LONGITUD;
                            plusMensaje = " ITC NETO";
                            break;
                        case LongitudError.ITC_POR_LITRO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.ITC_POR_LITRO_LONGITUD;
                            plusMensaje = " ITC POR LITRO";
                            break;
                        case LongitudError.KILOS_AFORADO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.KILOS_AFORADO_LONGITUD;
                            plusMensaje = " KILOS AFORADO";
                            break;
                        case LongitudError.KILOS_EFECTIVO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.KILOS_EFECTIVO_LONGITUD;
                            plusMensaje = " KILOS EFECTIVO";
                            break;
                        case LongitudError.LARGO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.LARGO_LONGITUD;
                            plusMensaje = " LARGO";
                            break;
                        case LongitudError.LETRA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.LETRA_LONGITUD;
                            plusMensaje = " LETRA";
                            break;
                        case LongitudError.M3_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.M3_LONGITUD;
                            plusMensaje = " M3";
                            break;
                        case LongitudError.MODELO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.MODELO_LONGITUD;
                            plusMensaje = " MODELO";
                            break;
                        case LongitudError.MONEDA_COTIZACION_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.MONEDA_COTIZACION_LONGITUD;
                            plusMensaje = " MONEDA COTIZACION";
                            break;
                        case LongitudError.NOMBRE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NOMBRE_LONGITUD;
                            plusMensaje = " NOMBRE";
                            break;
                        case LongitudError.NOMBRE_COMPLETO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NOMBRE_COMPLETO_LONGITUD;
                            plusMensaje = " NOMBRE COMPLETO";
                            break;
                        case LongitudError.NOMBRE_FANTASIA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NOMBRE_FANTASIA_LONGITUD;
                            plusMensaje = " NOMBRE FANTASIA";
                            break;
                        case LongitudError.NOTA_EMISION_COMPROBANTE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NOTA_EMISION_COMPROBANTE_LONGITUD;
                            plusMensaje = " NOTA EMISION COMPROBANTE";
                            break;
                        case LongitudError.NOTA_INGRESAR_COMPROBANTE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NOTA_INGRESAR_COMPROBANTE_LONGITUD;
                            plusMensaje = " NOTA INGRESAR COMPROBANTE";
                            break;
                        case LongitudError.NOTA_IMPRESION_COMPROBANTE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NOTA_IMPRESION_COMPROBANTE_LONGITUD;
                            plusMensaje = " NOTA IMPRESION COMPROBANTE";
                            break;
                        case LongitudError.NOTA_IMPRESION_COMPROBANTE_GRAL_1_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NOTA_IMPRESION_COMPROBANTE_GRAL_1_LONGITUD;
                            plusMensaje = " NOTA IMPRESION COMPROBANTE GENERAL 1";
                            break;
                        case LongitudError.NOTA_IMPRESION_COMPROBANTE_GRAL_2_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NOTA_IMPRESION_COMPROBANTE_GRAL_2_LONGITUD;
                            plusMensaje = " NOTA IMPRESION COMPROBANTE GENERAL 2";
                            break;
                        case LongitudError.NOTA_IMPRESION_ORDEN_PAGO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NOTA_IMPRESION_ORDEN_PAGO_LONGITUD;
                            plusMensaje = " NOTA IMPRESION ORDEN PAGO";
                            break;
                        case LongitudError.NOTA_IMPRESION_REMITO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NOTA_IMPRESION_REMITO_LONGITUD;
                            plusMensaje = " NOTA IMPRESION REMITO";
                            break;
                        case LongitudError.NUMERO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NUMERO_LONGITUD;
                            plusMensaje = " NUMERO";
                            break;
                        case LongitudError.NUMERO_CAEA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NUMERO_CAEA_LONGITUD;
                            plusMensaje = " NUMERO CAEA";
                            break;
                        case LongitudError.NUMERO_CBU_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.CBU_LONGITUD;
                            plusMensaje = " NUMERO CBU";
                            break;
                        case LongitudError.NUMERO_CHASIS_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NUMERO_CHASIS_LONGITUD;
                            plusMensaje = " NUMERO CHASIS";
                            break;
                        case LongitudError.NUMERO_CUENTA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NUMERO_CUENTA_LONGITUD;
                            plusMensaje = " NUMERO CUENTA";
                            break;
                        case LongitudError.NUMERO_DOCUMENTO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.DOCUMENTO_LONGITUD;
                            plusMensaje = " DNI";
                            break;
                        case LongitudError.NUMERO_IIBB_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NUMERO_IIBB_LONGITUD;
                            plusMensaje = " NUMERO IIBB";
                            break;
                        case LongitudError.NUMERO_INTERNO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NUMERO_INTERNO_LONGITUD;
                            plusMensaje = " NUMERO INTERNO";
                            break;
                        case LongitudError.NUMERO_MOTOR_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NUMERO_MOTOR_LONGITUD;
                            plusMensaje = " NUMERO MOTOR";
                            break;
                        case LongitudError.NUMERO_POLIZA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NUMERO_POLIZA_LONGITUD;
                            plusMensaje = " NUMERO POLIZA";
                            break;
                        case LongitudError.NUMERO_POLIZA_SEGURO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NUMERO_POLIZA_SEGURO_LONGITUD;
                            plusMensaje = " NUMERO POLIZA SEGURO";
                            break;
                        case LongitudError.NUMERO_REMITO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NUMERO_REMITO_LONGITUD;
                            plusMensaje = " NUMERO REMITO";
                            break;
                        case LongitudError.NUMERO_RUTA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.NUMERO_RUTA_LONGITUD;
                            plusMensaje = " NUMERO RUTA";
                            break;
                        case LongitudError.OBSERVACION_CHOFER_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.OBSERVACION_CHOFER_LONGITUD;
                            plusMensaje = " OBSERVACION CHOFER";
                            break;
                        case LongitudError.OBSERVACION_VEHICULO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.OBSERVACION_VEHICULO_LONGITUD;
                            plusMensaje = " OBSERVACION VEHIHCULO";
                            break;
                        case LongitudError.OBSERVACION_VEHICULO_REMOLQUE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.OBSERVACION_VEHICULO_REMOLQUE_LONGITUD;
                            plusMensaje = " OBSERVACION VEHICULO REMOLQUE";
                            break;
                        case LongitudError.OBSERVACIONES_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.OBSERVACIONES_LONGITUD;
                            plusMensaje = " OBSERVACIONES";
                            break;
                        case LongitudError.OBSERVACIONES_ANULADO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.OBSERVACIONES_ANULADO_LONGITUD;
                            plusMensaje = " OBSERVACIONES ANULADO";
                            break;
                        case LongitudError.PASSWORD_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.PASSWORD_LONGITUD;
                            plusMensaje = " PASSWORD";
                            break;
                        case LongitudError.P_COMISION_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.P_COMISION_LONGITUD;
                            plusMensaje = " P. COMISIÓN";
                            break;
                        case LongitudError.P_SEGURO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.P_SEGURO_LONGITUD;
                            plusMensaje = " P. SEGURO";
                            break;
                        case LongitudError.PRECIO_UNITARIO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.PRECIO_UNITARIO_LONGITUD;
                            plusMensaje = " PRECIO UNITARIO";
                            break;
                        case LongitudError.PRECIO_UNITARIO_VENTA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.PRECIO_UNITARIO_VENTA_LONGITUD;
                            plusMensaje = " PRECIO UNITARIO VENTA";
                            break;
                        case LongitudError.PRECIO_UNITARIO_VIAJE_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.PRECIO_UNITARIO_VIAJE_LONGITUD;
                            plusMensaje = " PRECIO UNITARIO VIAJE";
                            break;
                        case LongitudError.PRECIO_UNITARIO_REF_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.PRECIO_UNITARIO_REF_LONGITUD;
                            plusMensaje = " PRECIO UNITARIO REF";
                            break;
                        case LongitudError.PRECIO_UNITARIO_SECO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.PRECIO_UNITARIO_SECO_LONGITUD;
                            plusMensaje = " PRECIO UNITARIO SECO";
                            break;
                        case LongitudError.PUNTO_VENTA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.PUNTO_VENTA_LONGITUD;
                            plusMensaje = " PUNTO VENTA";
                            break;
                        case LongitudError.RAZON_SOCIAL_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.RAZON_SOCIAL_LONGITUD;
                            plusMensaje = " RAZON SOCIAL";
                            break;
                        case LongitudError.RUTA_ALTERNATIVA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.RUTA_ALTERNATIVA_LONGITUD;
                            plusMensaje = " RUTA ALTERNATIVA";
                            break;
                        case LongitudError.SEGURO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.SEGURO_LONGITUD;
                            plusMensaje = " SEGURO";
                            break;
                        case LongitudError.SIMBOLO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.SIMBOLO_LONGITUD;
                            plusMensaje = " SIMBOLO";
                            break;
                        case LongitudError.SITIO_WEB_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.SITIO_WEB_LONGITUD;
                            plusMensaje = " SITIO WEB";
                            break;
                        case LongitudError.SMVM_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.SMVM_LONGITUD;
                            plusMensaje = " SMVM";
                            break;
                        case LongitudError.TALLE_CAMISA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TALLE_CAMISA_LONGITUD;
                            plusMensaje = " TALLE CAMISA";
                            break;
                        case LongitudError.TALLE_CALZADO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TALLE_CALZADO_LONGITUD;
                            plusMensaje = " TALLE CAlZADO";
                            break;
                        case LongitudError.TALLE_PANTALON_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TALLE_PANTALON_LONGITUD;
                            plusMensaje = " TALLE PANTALON";
                            break;
                        case LongitudError.TARA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TARA_LONGITUD;
                            plusMensaje = " TARA";
                            break;
                        case LongitudError.TELEFONO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TELEFONO_LONGITUD;
                            plusMensaje = " TELEFONO";
                            break;
                        case LongitudError.TELEFONO_ALT_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TELEFONO_ALT_LONGITUD;
                            plusMensaje = " TELEFONO ALTERNATIVO";
                            break;
                        case LongitudError.TELEFONO_FIJO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TELEFONO_FIJO_LONGITUD;
                            plusMensaje = " TELEFONO FIJO";
                            break;
                        case LongitudError.TELEFONO_MOVIL_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TELEFONO_MOVIL_LONGITUD;
                            plusMensaje = " TELEFONO MOVIL";
                            break;
                        case LongitudError.TELEFONO_MOVIL_EMPRESA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TELEFONO_MOVIL_EMPRESA_LONGITUD;
                            plusMensaje = " TELEFONO MOVIL EMPRESA";
                            break;
                        case LongitudError.TELEFONO_MOVIL_OBSERVACION_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TELEFONO_MOVIL_OBSERVACION_LONGITUD;
                            plusMensaje = " TELEFONO MOVIL OBSERVACION";
                            break;
                        case LongitudError.TITULAR_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TITULAR_LONGITUD;
                            plusMensaje = " TITULAR";
                            break;
                        case LongitudError.TOPE_BASICO_ADELANTOS_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.TOPE_BASICO_ADELANTOS_LONGITUD;
                            plusMensaje = " TOPE BASICO ADELANTOS";
                            break;
                        case LongitudError.USERNAME_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.USERNAME_LONGITUD;
                            plusMensaje = " USUARIO";
                            break;
                        case LongitudError.URL_PRUEBA_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.URL_PRUEBA_LONGITUD;
                            plusMensaje = " URL PRUEBA";
                            break;
                        case LongitudError.URL_REAL_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.URL_REAL_LONGITUD;
                            plusMensaje = " URL REAL";
                            break;
                        case LongitudError.VALOR_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VALOR_LONGITUD;
                            plusMensaje = " VALOR";
                            break;
                        case LongitudError.VALOR_DECLARADO_LONGITUD:
                            codigoRespuesta = CodigoRespuesta.VALOR_DECLARADO_LONGITUD;
                            plusMensaje = " VALOR DECLARADO";
                            break;
                        default:
                            codigoRespuesta = CodigoRespuesta.LONGITUD;
                            plusMensaje = "";
                     }
                }else {
                    codigoRespuesta = CodigoRespuesta.ERROR_INTERNO_SERVIDOR;
                            plusMensaje = "";
                }
                break;
        }
        return new ResponseEntity<>(new EstadoRespuesta(codigoRespuesta,
                mensajeRespuesta + plusMensaje, 0), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<?> datoInexistente(String a, String b) {
        String[] partes2 = b.split(" ");
        String[] partes = b.split("`");
        String s;
        String mensajeRespuesta = "";
        String[] partes3 = partes2[3].split(".");
        int codigoRespuesta = 0;
        String plusMensaje = "";
        if (a.equals("delete")) {
            mensajeRespuesta = MensajeRespuesta.ELEMENTO_ASIGNADO;
            s = partes[7];
        } else if (a.equals("a")) {
            s = partes2[3].substring(26);
            s = "id" + s;
            mensajeRespuesta = MensajeRespuesta.NO_EXISTENTE;
        } else {
            mensajeRespuesta = MensajeRespuesta.NO_EXISTENTE;
            s = partes[7];
        }
        switch (s) {
            case InexistenciaError.AFIP_ACTIVIDAD_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.AFIP_ACTIVIDAD_INEXISTENTE;
                plusMensaje = ": AFIP ACTIVIDAD";
                break;
            case InexistenciaError.AFIP_ALICUOTA_GANANCIA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.AFIP_ALICUOTA_GANANCIA_INEXISTENTE;
                plusMensaje = ": AFIP ALICUOTA GANANCIA";
                break;
            case InexistenciaError.AFIP_ALICUOTA_IVA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.AFIP_ALICUOTA_IVA_INEXISTENTE;
                plusMensaje = ": AFIP ALICUOTA IVA";
                break;
            case InexistenciaError.AFIP_COMPROBANTE_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.AFIP_COMPROBANTE_INEXISTENTE;
                plusMensaje = ": AFIP COMPROBANTE";
                break;
            case InexistenciaError.AFIP_CONCEPTO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.AFIP_CONCEPTO_INEXISTENTE;
                plusMensaje = ": AFIP CONCEPTO";
                break;
            case InexistenciaError.AFIP_CONDICION_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.AFIP_CONDICION_INEXISTENTE;
                plusMensaje = ": AFIP CONDICION";
                break;
            case InexistenciaError.AFIP_CONDICION_IVA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.AFIP_CONDICION_IVA_INEXISTENTE;
                plusMensaje = ": AFIP CONDICION IVA";
                break;
            case InexistenciaError.AFIP_CONDICION_IVA_PROVEEDOR_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.AFIP_CONDICION_IVA_PROVEEDOR_INEXISTENTE;
                plusMensaje = ": AFIP CONDICION IVA PROVEEDOR";
                break;
            case InexistenciaError.AFIP_DEDUCCION_GENERAL_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.AFIP_DEDUCCION_GENERAL_INEXISTENTE;
                plusMensaje = ": AFIP DEDUCCION GENERAL";
                break;
            case InexistenciaError.AFIP_DEDUCCION_PERSONAL_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.AFIP_DEDUCCION_PERSONAL_INEXISTENTE;
                plusMensaje = ": AFIP DEDUCCION PERSONAL";
                break;
            case InexistenciaError.AFIP_LOCALIDAD_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.AFIP_LOCALIDAD_INEXISTENTE;
                plusMensaje = ": AFIP LOCALIDAD";
                break;
            case InexistenciaError.AFIP_MOD_CONTRATACION_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.AFIP_MOD_CONTRATACION_INEXISTENTE;
                plusMensaje = ": AFIP MODALIDAD CONTRATACIÓN";
                break;
            case InexistenciaError.AFIP_SINIESTRADO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.AFIP_SINIESTRADO_INEXISTENTE;
                plusMensaje = ": AFIP SINIESTRADO";
                break;
            case InexistenciaError.AFIP_SITUACION_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.AFIP_SITUACION_INEXISTENTE;
                plusMensaje = ": AFIP SITUACION";
                break;
            case InexistenciaError.AFIP_TIPO_BENEFICIO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.AFIP_TIPO_BENEFICIO_INEXISTENTE;
                plusMensaje = ": AFIP TIPO BENEFICIO";
                break;
            case InexistenciaError.AREA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.AREA_INEXISTENTE;
                plusMensaje = ": AREA";
                break;
            case InexistenciaError.BANCO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.BANCO_INEXISTENTE;
                plusMensaje = ": BANCO";
                break;
            case InexistenciaError.BARRIO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.BARRIO_INEXISTENTE;
                plusMensaje = ": BARRIO";
                break;
            case InexistenciaError.BUG_IMAGEN_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.BUG_IMAGEN_INEXISTENTE;
                plusMensaje = ": IMAGEN DEL BUG";
                break;
            case InexistenciaError.CATEGORIA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.CATEGORIA_INEXISTENTE;
                plusMensaje = ": CATEGORIA";
                break;
            case InexistenciaError.CHEQUE_CARTERA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.CHEQUE_CARTERA_INEXISTENTE;
                plusMensaje = ": CHEQUE CARTERA";
                break;
            case InexistenciaError.CHOFER_PROVEEDOR_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.CHOFER_PROVEEDOR_INEXISTENTE;
                plusMensaje = ": CHOFER PROVEEDOR";
                break;
            case InexistenciaError.CLIENTE_DESTINATARIO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.CLIENTE_DESTINATARIO_INEXISTENTE;
                plusMensaje = ": CLIENTE DESTINATARIO";
                break;
            case InexistenciaError.CLIENTE_DESTINATARIO_SUC_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.CLIENTE_DESTINATARIO_SUC_INEXISTENTE;
                plusMensaje = ": CLIENTE DESTINATARIO SUCURSAL";
                break;
            case InexistenciaError.CLIENTE_GRUPO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.CLIENTE_GRUPO_INEXISTENTE;
                plusMensaje = ": CLIENTE GRUPO";
                break;
            case InexistenciaError.CLIENTE_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.CLIENTE_INEXISTENTE;
                plusMensaje = ": CLIENTE";
                break;
            case InexistenciaError.CLIENTE_REMITENTE_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.CLIENTE_REMITENTE_INEXISTENTE;
                plusMensaje = ": CLIENTE REMITENTE";
                break;
            case InexistenciaError.COBRADOR_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.COBRADOR_INEXISTENTE;
                plusMensaje = ": COBRADOR";
                break;
            case InexistenciaError.COMPANIA_SEGURO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.COMPANIA_SEGURO_INEXISTENTE;
                plusMensaje = ": COMPAÑIA SEGURO";
                break;
            case InexistenciaError.COMPANIA_SEGURO_POLIZA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.COMPANIA_SEGURO_POLIZA_INEXISTENTE;
                plusMensaje = ": COMPAÑIA SEGURO POLIZA";
                break;
            case InexistenciaError.COMPRA_COMPROBANTE_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.COMPRA_COMPROBANTE_INEXISTENTE;
                plusMensaje = ": COMPRA COMPROBANTE";
                break;
            case InexistenciaError.COMPRA_COMPROBANTE_PERCEPCION_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.COMPRA_COMPROBANTE_PERCEPCION_INEXISTENTE;
                plusMensaje = ": COMPRA COMPROBANTE PERCEPCION";
                break;
            case InexistenciaError.CONDICION_COMPRA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.CONDICION_COMPRA_INEXISTENTE;
                plusMensaje = ": CONDICION COMPRA";
                break;
            case InexistenciaError.CONDICION_VENTA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.CONDICION_VENTA_INEXISTENTE;
                plusMensaje = ": CONDICION VENTA";
                break;
            case InexistenciaError.CONFIGURACION_VEHICULO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.CONFIGURACION_VEHICULO_INEXISTENTE;
                plusMensaje = ": CONFIGURACIÓN VEHÍCULO";
                break;
            case InexistenciaError.CUENTA_BANCARIA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.CUENTA_BANCARIA_INEXISTENTE;
                plusMensaje = ": CUENTA BANCARIA";
                break;
            case InexistenciaError.CUENTA_CONTABLE_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.CUENTA_CONTABLE_INEXISTENTE;
                plusMensaje = ": CUENTA CONTABLE";
                break;
            case InexistenciaError.CUENTA_GRUPO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.CUENTA_GRUPO_INEXISTENTE;
                plusMensaje = ": CUENTA GRUPO";
                break;
            case InexistenciaError.DEPOSITO_INSUMO_PRODUCTO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.DEPOSITO_INSUMO_PRODUCTO_INEXISTENTE;
                plusMensaje = ": DEPOSITO INSUMO PRODUCTO";
                break;
            case InexistenciaError.DESTINO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.DESTINO_INEXISTENTE;
                plusMensaje = ": DESTINO";
                break;
            case InexistenciaError.EMPRESA_C_FISCAL_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.EMPRESA_C_FISCAL_INEXISTENTE;
                plusMensaje = ": EMPRESA C FISCAL";
                break;
            case InexistenciaError.EMPRESA_EMISION_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.EMPRESA_EMISION_INEXISTENTE;
                plusMensaje = ": EMPRESA EMISIÓN";
                break;
            case InexistenciaError.EMPRESA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.EMPRESA_INEXISTENTE;
                plusMensaje = ": EMPRESA";
                break;
            case InexistenciaError.ESCALA_TARIFA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.ESCALA_TARIFA_INEXISTENTE;
                plusMensaje = ": ESCALA TARIFA";
                break;
            case InexistenciaError.ESTADO_CIVIL_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.ESTADO_CIVIL_INEXISTENTE;
                plusMensaje = ": ESTADO CIVIL";
                break;
            case InexistenciaError.FOTO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.FOTO_INEXISTENTE;
                plusMensaje = ": FOTO";
                break;
            case InexistenciaError.GRUPO_CUENTA_CONTABLE_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.GRUPO_CUENTA_CONTABLE_INEXISTENTE;
                plusMensaje = ": GRUPO CUENTA CONTABLE";
                break;
            case InexistenciaError.INSUMO_PRODUCTO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.INSUMO_PRODUCTO_INEXISTENTE;
                plusMensaje = ": INSUMO PRODUCTO";
                break;
            case InexistenciaError.LOCALIDAD_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.LOCALIDAD_INEXISTENTE;
                plusMensaje = ": LOCALIDAD";
                break;
            case InexistenciaError.LOCALIDAD_NACIMIENTO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.LOCALIDAD_NACIMIENTO_INEXISTENTE;
                plusMensaje = ": LOCALIDAD NACIMIENTO";
                break;
            case InexistenciaError.MARCA_PRODUCTO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.MARCA_PRODUCTO_INEXISTENTE;
                plusMensaje = ": MARCA PRODUCTO";
                break;
            case InexistenciaError.MARCA_VEHICULO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.MARCA_VEHICULO_INEXISTENTE;
                plusMensaje = ": MARCA VEHÍCULO";
                break;
            case InexistenciaError.MES_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.MES_INEXISTENTE;
                plusMensaje = ": MES";
                break;
            case InexistenciaError.MES_INICIO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.MES_INICIO_INEXISTENTE;
                plusMensaje = ": MES INICIO";
                break;
            case InexistenciaError.MODULO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.MODULO_INEXISTENTE;
                plusMensaje = ": MÓDULO";
                break;
            case InexistenciaError.MONEDA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.MONEDA_INEXISTENTE;
                plusMensaje = ": MONEDA";
                break;
            case InexistenciaError.OBRA_SOCIAL_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.OBRA_SOCIAL_INEXISTENTE;
                plusMensaje = ": OBRA SOCIAL";
                break;
            case InexistenciaError.OPCION_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.OPCION_INEXISTENTE;
                plusMensaje = ": OPCIÓN";
                break;
            case InexistenciaError.ORDEN_RECOLECCION_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.ORDEN_RECOLECCION_INEXISTENTE;
                plusMensaje = ": ORDEN RECOLECCCIÓN";
                break;
            case InexistenciaError.ORDEN_VENTA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.ORDEN_VENTA_INEXISTENTE;
                plusMensaje = ": ORDEN VENTA";
                break;
            case InexistenciaError.ORDEN_VENTA_TARIFA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.ORDEN_VENTA_TARIFA_INEXISTENTE;
                plusMensaje = ": ORDEN VENTA TARIFA";
                break;
            case InexistenciaError.ORIGEN_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.ORIGEN_INEXISTENTE;
                plusMensaje = ": ORIGEN";
            case InexistenciaError.ORIGEN_DESTINO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.ORIGEN_DESTINO_INEXISTENTE;
                plusMensaje = ": ORIGEN - DESTINO";
                break;
            case InexistenciaError.PADRE_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.PADRE_INEXISTENTE;
                plusMensaje = ": PADRE";
                break;
            case InexistenciaError.PAIS_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.PAIS_INEXISTENTE;
                plusMensaje = ": PAÍS";
                break;
            case InexistenciaError.PDF_ALTA_TEMPRANA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.PDF_ALTA_TEMPRANA_INEXISTENTE;
                plusMensaje = ": PDF ALTA TEMPRANA";
                break;
            case InexistenciaError.PDF_CEDULA_IDENT_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.PDF_CEDULA_IDENT_INEXISTENTE;
                plusMensaje = ": PDF CÉDULA IDENTIFICACIÓN";
                break;
            case InexistenciaError.PDF_DNI_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.PDF_DNI_INEXISTENTE;
                plusMensaje = ": PDF DNI";
                break;
            case InexistenciaError.PDF_HAB_BROMAT_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.PDF_HAB_BROMAT_INEXISTENTE;
                plusMensaje = ": PDF HABILITACIÓN BROMATOLÓGICA";
                break;
            case InexistenciaError.PDF_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.PDF_INEXISTENTE;
                plusMensaje = ": PDF";
                break;
            case InexistenciaError.PDF_LIB_SANIDAD_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.PDF_LIB_SANIDAD_INEXISTENTE;
                plusMensaje = ": PDF LIBRETA SANIDAD";
                break;
            case InexistenciaError.PDF_LIC_CONDUCIR_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.PDF_LIC_CONDUCIR_INEXISTENTE;
                plusMensaje = ": PDF LICENCIA CONDUCIR";
                break;
            case InexistenciaError.PDF_LINTI_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.PDF_LINTI_INEXISTENTE;
                plusMensaje = ": PDF LINTI";
                break;
            case InexistenciaError.PDF_SENASA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.PDF_SENASA_INEXISTENTE;
                plusMensaje = ": PDF SENASA";
                break;
            case InexistenciaError.PDF_TITULO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.PDF_TITULO_INEXISTENTE;
                plusMensaje = ": PDF TÍTULO";
                break;
            case InexistenciaError.PDF_VTO_INSP_TECNICA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.PDF_VTO_INSP_TECNICA_INEXISTENTE;
                plusMensaje = ": PDF INSPECCIÓN TÉCNICA";
                break;
            case InexistenciaError.PDF_VTO_RUTA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.PDF_VTO_RUTA_INEXISTENTE;
                plusMensaje = ": PDF VENCIMIENTO RUTA";
                break;
            case InexistenciaError.PERSONAL_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.PERSONAL_INEXISTENTE;
                plusMensaje = ": PERSONAL";
                break;
            case InexistenciaError.PESTANIA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.PESTANIA_INEXISTENTE;
                plusMensaje = ": PESTANIA";
                break;
            case InexistenciaError.PLAN_DE_CUENTA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.PLAN_DE_CUENTA_INEXISTENTE;
                plusMensaje = ": PLAN DE CUENTA";
                break;
            case InexistenciaError.PROVEEDOR_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.PROVEEDOR_INEXISTENTE;
                plusMensaje = ": PROVEEDOR";
                break;
            case InexistenciaError.PROVINCIA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.PROVINCIA_INEXISTENTE;
                plusMensaje = ": PROVINCIA";
                break;
            case InexistenciaError.REGISTRO_C_FISCAL_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.REGISTRO_C_FISCAL_INEXISTENTE;
                plusMensaje = ": REGISTRO C FISCAL";
                break;
            case InexistenciaError.REPARTO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.REPARTO_INEXISTENTE;
                plusMensaje = ": REPARTO";
                break;
            case InexistenciaError.RESUMEN_CLIENTE_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.RESUMEN_CLIENTE_INEXISTENTE;
                plusMensaje = ": RESUMEN CLIENTE";
                break;
            case InexistenciaError.RETIRO_DEPOSITO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.RETIRO_DEPOSITO_INEXISTENTE;
                plusMensaje = ": RETIRO DEPOSITO";
                break;
            case InexistenciaError.ROL_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.ROL_INEXISTENTE;
                plusMensaje = ": ROL";
                break;
            case InexistenciaError.RUBRO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.RUBRO_INEXISTENTE;
                plusMensaje = ": RUBRO";
                break;
            case InexistenciaError.RUBRO_PRODUCTO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.RUBRO_PRODUCTO_INEXISTENTE;
                plusMensaje = ": RUBRO PRODUCTO";
                break;
            case InexistenciaError.ROL_SECUNDARIO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.ROL_SECUNDARIO_INEXISTENTE;
                plusMensaje = ": ROL SECUNDARIO";
                break;
            case InexistenciaError.SEGUIMIENTO_ESTADO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.SEGUIMIENTO_ESTADO_INEXISTENTE;
                plusMensaje = ": SEGUIMIENTO ESTADO";
                break;
            case InexistenciaError.SEGUIMIENTO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.SEGUIMIENTO_INEXISTENTE;
                plusMensaje = ": SEGUIMIENTO";
                break;
            case InexistenciaError.SEGUIMIENTO_SITUACION_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.SEGUIMIENTO_SITUACION_INEXISTENTE;
                plusMensaje = ": SEGUIMIENTO SITUACIÓN";
                break;
            case InexistenciaError.SEGURIDAD_SOCIAL_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.SEGURIDAD_SOCIAL_INEXISTENTE;
                plusMensaje = ": SEGURIDAD SOCIAL";
                break;
            case InexistenciaError.SEXO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.SEXO_INEXISTENTE;
                plusMensaje = ": SEXO";
                break;
            case InexistenciaError.SINDICATO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.SINDICATO_INEXISTENTE;
                plusMensaje = ": SINDICATO";
                break;
            case InexistenciaError.SITUACION_CLIENTE_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.SITUACION_CLIENTE_INEXISTENTE;
                plusMensaje = ": SITUACION CLIENTE";
                break;
            case InexistenciaError.SOPORTE_ESTADO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.SOPORTE_ESTADO_INEXISTENTE;
                plusMensaje = ": SOPORTE ESTADO";
                break;
            case InexistenciaError.SUBMODULO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.SUBMODULO_INEXISTENTE;
                plusMensaje = ": SUBMÓDULO";
                break;
            case InexistenciaError.SUBOPCION_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.SUBOPCION_INEXISTENTE;
                plusMensaje = ": SUBOPCIÓN";
                break;
            case InexistenciaError.SUCURSAL_BANCO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.SUCURSAL_BANCO_INEXISTENTE;
                plusMensaje = ": SUCURSAL BANCO";
                break;
            case InexistenciaError.SUCURSAL_CLIENTE_DES_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.SUCURSAL_CLIENTE_DES_INEXISTENTE;
                plusMensaje = ": SUCURSAL CLIENTE DESTINATARIO";
                break;
            case InexistenciaError.SUCURSAL_CLIENTE_REM_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.SUCURSAL_CLIENTE_REM_INEXISTENTE;
                plusMensaje = ": SUCURSAL CLIENTE REMITENTE";
                break;
            case InexistenciaError.SUCURSAL_DESTINO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.SUCURSAL_DESTINO_INEXISTENTE;
                plusMensaje = ": SUCURSAL DESTINO";
                break;
            case InexistenciaError.SUCURSAL_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.SUCURSAL_INEXISTENTE;
                plusMensaje = ": SUCURSAL";
                break;
            case InexistenciaError.SUCURSAL_INGRESO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.SUCURSAL_INGRESO_INEXISTENTE;
                plusMensaje = ": SUCURSAL INGRESO";
                break;
            case InexistenciaError.SUCURSAL_LUGAR_PAGO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.SUCURSAL_LUGAR_PAGO_INEXISTENTE;
                plusMensaje = ": SUCURSAL LUGAR PAGO";
                break;
            case InexistenciaError.TALONARIO_RECIBO_LOTE_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.TALONARIO_RECIBO_LOTE_INEXISTENTE;
                plusMensaje = ": TALONARIO RECIBO LOTE";
                break;
            case InexistenciaError.TIPO_CHEQUERA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.TIPO_CHEQUERA_INEXISTENTE;
                plusMensaje = ": TIPO CHEQUERA";
                break;
            case InexistenciaError.TIPO_COMPROBANTE_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.TIPO_COMPROBANTE_INEXISTENTE;
                plusMensaje = ": TIPO COMPROBANTE";
                break;
            case InexistenciaError.TIPO_CONTACTO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.TIPO_CONTACTO_INEXISTENTE;
                plusMensaje = ": TIPO CONTACTO";
                break;
            case InexistenciaError.TIPO_CUENTA_BANCARIA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.TIPO_CUENTA_BANCARIA_INEXISTENTE;
                plusMensaje = ": TIPO CUENTA BANCARIA";
                break;
            case InexistenciaError.TIPO_CUENTA_CONTABLE_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.TIPO_CUENTA_CONTABLE_INEXISTENTE;
                plusMensaje = ": TIPO CUENTA CONTABLE";
                break;
            case InexistenciaError.TIPO_DOCUMENTO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.TIPO_DOCUMENTO_INEXISTENTE;
                plusMensaje = ": TIPO DOCUMENTO";
                break;
            case InexistenciaError.TIPO_PROVEEDOR_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.TIPO_PROVEEDOR_INEXISTENTE;
                plusMensaje = ": TIPO PROVEEDOR";
                break;
            case InexistenciaError.TIPO_PERCEPCION_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.TIPO_PERCEPCION_INEXISTENTE;
                plusMensaje = ": TIPO PERCEPCIÓN";
                break;
            case InexistenciaError.TIPO_TARIFA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.TIPO_TARIFA_INEXISTENTE;
                plusMensaje = ": TIPO TARIFA";
                break;
            case InexistenciaError.TIPO_VEHICULO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.TIPO_VEHICULO_INEXISTENTE;
                plusMensaje = ": TIPO VEHÍCULO";
                break;
            case InexistenciaError.TRAMO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.TRAMO_INEXISTENTE;
                plusMensaje = ": TRAMO";
                break;
            case InexistenciaError.UNIDAD_MEDIDA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.UNIDAD_MEDIDA_INEXISTENTE;
                plusMensaje = ": UNIDAD MEDIDA";
                break;
            case InexistenciaError.USUARIO_ALTA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.USUARIO_ALTA_INEXISTENTE;
                plusMensaje = ": USUARIO ALTA";
                break;
            case InexistenciaError.USUARIO_BAJA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.USUARIO_BAJA_INEXISTENTE;
                plusMensaje = ": USUARIO BAJA";
                break;
            case InexistenciaError.USUARIO_CHOFER_AUTORIZADO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.USUARIO_CHOFER_AUTORIZADO_INEXISTENTE;
                plusMensaje = ": USUARIO CHOFER AUTORIZADO";
                break;
            case InexistenciaError.USUARIO_DOCUMENTACION_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.USUARIO_DOCUMENTACION_INEXISTENTE;
                plusMensaje = ": USUARIO DOCUMENTACIÓN";
                break;
            case InexistenciaError.USUARIO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.USUARIO_INEXISTENTE;
                plusMensaje = ": USUARIO";
                break;
            case InexistenciaError.USUARIO_LIQUIDACION_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.USUARIO_LIQUIDACION_INEXISTENTE;
                plusMensaje = ": USUARIO LIQUIDACIÓN";
                break;
            case InexistenciaError.USUARIO_MOD_CURSO_CP_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.USUARIO_MOD_CURSO_CP_INEXISTENTE;
                plusMensaje = ": USUARIO MODIFICACIÓN CURSO CARGA PELIGROSA";
                break;
            case InexistenciaError.USUARIO_MOD_CURSO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.USUARIO_MOD_CURSO_INEXISTENTE;
                plusMensaje = ": USUARIO MODIFICACIÓN CURSO";
                break;
            case InexistenciaError.USUARIO_MOD_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.USUARIO_MOD_INEXISTENTE;
                plusMensaje = ": USUARIO MODIFICACIÓN";
                break;
            case InexistenciaError.USUARIO_MOD_LC_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.USUARIO_MOD_LC_INEXISTENTE;
                plusMensaje = ": USUARIO MODIFICACIÓN LICENCIA CONDUCIR";
                break;
            case InexistenciaError.USUARIO_MOD_LINTI_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.USUARIO_MOD_LINTI_INEXISTENTE;
                plusMensaje = ": USUARIO MODIFICACIÓN LINTI";
                break;
            case InexistenciaError.USUARIO_MOD_LS_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.USUARIO_MOD_LS_INEXISTENTE;
                plusMensaje = ": USUARIO MODIFICACIÓN LIB. SANIDAD";
                break;
            case InexistenciaError.VEHICULO_AUTORIZADO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.VEHICULO_AUTORIZADO_INEXISTENTE;
                plusMensaje = ": VEHÍCULO AUTORIZADO";
                break;
            case InexistenciaError.VEHICULO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.VEHICULO_INEXISTENTE;
                plusMensaje = ": VEHÍCULO";
                break;
            case InexistenciaError.VEHICULO_PROVEEDOR_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.VEHICULO_PROVEEDOR_INEXISTENTE;
                plusMensaje = ": VEHÍCULO PROVEEDOR";
                break;
            case InexistenciaError.VEHICULO_REMOLQUE_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.VEHICULO_REMOLQUE_INEXISTENTE;
                plusMensaje = ": VEHÍCULO REMOLQUE";
                break;
            case InexistenciaError.VEHICULO_REMOLQUE_PROVEEDOR_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.VEHICULO_REMOLQUE_PROVEEDOR_INEXISTENTE;
                plusMensaje = ": VEHÍCULO REMOLQUE PROVEEDOR";
                break;
            case InexistenciaError.VEHICULO_REM_AUTORIZADO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.VEHICULO_REM_AUTORIZADO_INEXISTENTE;
                plusMensaje = ": VEHÍCULO REMOLQUE AUTORIZADO";
                break;
            case InexistenciaError.VENDEDOR_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.VENDEDOR_INEXISTENTE;
                plusMensaje = ": VENDEDOR";
                break;
            case InexistenciaError.VENTA_COMPROBANTE_APLICADO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.VENTA_COMPROBANTE_APLICADO_INEXISTENTE;
                plusMensaje = ": VENTA COMPROBANTE APLICADO";
                break;
            case InexistenciaError.VENTA_COMPROBANTE_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.VENTA_COMPROBANTE_INEXISTENTE;
                plusMensaje = ": VENTA COMPROBANTE";
                break;
            case InexistenciaError.VENTA_TIPO_ITEM_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.VENTA_TIPO_ITEM_INEXISTENTE;
                plusMensaje = ": VENTA TIPO ITEM";
                break;
            case InexistenciaError.VENTA_ITEM_CONCEPTO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.VENTA_ITEM_CONCEPTO_INEXISTENTE;
                plusMensaje = ": VENTA ITEM CONCEPTO";
                break;
            case InexistenciaError.VIAJE_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.VIAJE_INEXISTENTE;
                plusMensaje = ": VIAJE";
                break;
            case InexistenciaError.VIAJE_REMITO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.VIAJE_REMITO_INEXISTENTE;
                plusMensaje = ": VIAJE REMITO";
                break;
            case InexistenciaError.VIAJE_TARIFA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.VIAJE_TARIFA_INEXISTENTE;
                plusMensaje = ": VIAJE TARIFA";
                break;
            case InexistenciaError.VIAJE_TIPO_CARGA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.VIAJE_TIPO_CARGA_INEXISTENTE;
                plusMensaje = ": VIAJE TIPO CARGA";
                break;
            case InexistenciaError.VIAJE_TIPO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.VIAJE_TIPO_INEXISTENTE;
                plusMensaje = ": VIAJE TIPO";
                break;
            case InexistenciaError.VIAJE_TRAMO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.VIAJE_TRAMO_INEXISTENTE;
                plusMensaje = ": VIAJE TRAMO";
                break;
            case InexistenciaError.VIAJE_UNIDAD_NEGOCIO_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.VIAJE_UNIDAD_NEGOCIO_INEXISTENTE;
                plusMensaje = ": VIAJE UNIDAD NEGOCIO";
                break;
            case InexistenciaError.ZONA_INEXISTENTE:
                codigoRespuesta = CodigoRespuesta.ZONA_INEXISTENTE;
                plusMensaje = ": ZONA";
                break;
            default:
                mensajeRespuesta = MensajeRespuesta.NO_EXISTENTE;
                codigoRespuesta = CodigoRespuesta.ERROR_INTERNO_SERVIDOR;
                plusMensaje = " ";

        }
        return new ResponseEntity<>(new EstadoRespuesta(codigoRespuesta,
                mensajeRespuesta + plusMensaje, 0), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
