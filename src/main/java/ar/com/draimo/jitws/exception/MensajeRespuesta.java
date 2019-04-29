package ar.com.draimo.jitws.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public static final String NO_EXISTENTE = "Registro no existente";

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

    //Retorna mensaje con ReponseEntity de error interno en el servidor
    public static ResponseEntity<?> error() {
        return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.ERROR_INTERNO_SERVIDOR,
                MensajeRespuesta.ERROR_INTERNO_SERVIDOR, 0), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Retorna mensaje con ReponseEntity de error de sincronizacion
    public static ResponseEntity<?> errorSincSocket() {
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
    public static ResponseEntity<?> sinContenido() {
        return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.SIN_CONTENIDO,
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

    //Envia mensaje de dato repetido
    public static ResponseEntity<?> datoDuplicado(DataIntegrityViolationException dive) {
        //Obtiene el mensaje de duplicidad de datos
        String[] partes = dive.getMostSpecificCause().getMessage().split("'");
        String mensajeRespuesta;
        String plusMensaje;
        int codigoRespuesta;
        if (partes.length == 4) {
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
                case DuplicidadError.ID_UNICO:
                    codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_ID;
                    plusMensaje = " ID";
                    break;
                case DuplicidadError.NOMBRE_UNICO:
                    codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_NOMBRE;
                    plusMensaje = " NOMBRE";
                    break;
                case DuplicidadError.REPARTO_ORDENRECOLECCION_UNICOS:
                    codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_REPARTO_ORECOLECCION;
                    plusMensaje = " REPARTO Y ORDEN RECOLECCION";
                    break;
                case DuplicidadError.REPARTO_VENTACOMPROBANTE_UNICOS:
                    codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_REPARTO_VCOMPROBANTE;
                    plusMensaje = " REPARTO Y VENTA COMPROBANTE";
                    break;
                case DuplicidadError.REPARTO_VIAJEREMITO_UNICOS:
                    codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_REPARTO_VREMITO;
                    plusMensaje = " REPARTO Y VIAJE REMITO";
                    break;
                case DuplicidadError.USERNAME_UNICO:
                    codigoRespuesta = CodigoRespuesta.DATO_DUPLICADO_USERNAME;
                    plusMensaje = " USUARIO";
                    break;
                default:
                    codigoRespuesta = CodigoRespuesta.ERROR_INTERNO_SERVIDOR;
                    plusMensaje = " ";
                    break;
            }
        } else {
            //Determina la longitud de la columna 
            mensajeRespuesta = MensajeRespuesta.LONGITUD;
            switch (partes[1]) {
                case LongitudError.ABREVIATURA_LONGITUD:
                    codigoRespuesta = CodigoRespuesta.ABREVIATURA_LONGITUD;
                    plusMensaje = " ABREVIATURA";
                    break;
                case LongitudError.ALIAS_LONGITUD:
                    codigoRespuesta = CodigoRespuesta.ALIAS_LONGITUD;
                    plusMensaje = " ALIAS";
                    break;
                case LongitudError.ALIAS_CBU_LONGITUD:
                    codigoRespuesta = CodigoRespuesta.ALIAS_CBU_LONGITUD;
                    plusMensaje = " ALIAS CBU";
                    break;
                case LongitudError.APELLIDO_LONGITUD:
                    codigoRespuesta = CodigoRespuesta.APELLIDO_LONGITUD;
                    plusMensaje = " APELLIDO";
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
                case LongitudError.CORREO_ELECTRONICO_LONGITUD:
                    codigoRespuesta = CodigoRespuesta.CORREO_ELECTRONICO_LONGITUD;
                    plusMensaje = " CORREO ELECTRONICO";
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
                case LongitudError.LETRA_LONGITUD:
                    codigoRespuesta = CodigoRespuesta.LETRA_LONGITUD;
                    plusMensaje = " LETRA";
                    break;
                case LongitudError.MODELO_LONGITUD:
                    codigoRespuesta = CodigoRespuesta.MODELO_LONGITUD;
                    plusMensaje = " MODELO";
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
                case LongitudError.RAZON_SOCIAL_LONGITUD:
                    codigoRespuesta = CodigoRespuesta.RAZON_SOCIAL_LONGITUD;
                    plusMensaje = " RAZON SOCIAL";
                    break;
                case LongitudError.RUTA_ALTERNATIVA_LONGITUD:
                    codigoRespuesta = CodigoRespuesta.RUTA_ALTERNATIVA_LONGITUD;
                    plusMensaje = " RUTA ALTERNATIVA";
                    break;
                case LongitudError.SITIO_WEB_LONGITUD:
                    codigoRespuesta = CodigoRespuesta.SITIO_WEB_LONGITUD;
                    plusMensaje = " SITIO WEB";
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
                default:
                    codigoRespuesta = CodigoRespuesta.ERROR_INTERNO_SERVIDOR;
                    plusMensaje = "";
            }
        }
            return new ResponseEntity<>(new EstadoRespuesta(codigoRespuesta, 
                    mensajeRespuesta + plusMensaje, 0), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
