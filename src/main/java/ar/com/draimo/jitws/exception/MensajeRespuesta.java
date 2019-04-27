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
        if (partes.length == 4) {
            //Determina que columna tiene el dato duplicado
            switch (partes[3]) {
                case DuplicidadError.ABREVIATURA_UNICO:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_ABREVIATURA,
                            MensajeRespuesta.DATO_DUPLICADO + " ABREVIATURA", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.CODIGO_AFIP_UNICO:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_CODIGO_AFIP ,
                            MensajeRespuesta.DATO_DUPLICADO + " CODIGO AFIP", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.CORREOELECTRONICO_UNICO:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_CORREOELECTRONICO,
                            MensajeRespuesta.DATO_DUPLICADO + " CORREO ELECTRONICO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.CUIL_UNICO:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_CUIL,
                            MensajeRespuesta.DATO_DUPLICADO + " CUIL", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.CUIT_UNICO:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_CUIT,
                            MensajeRespuesta.DATO_DUPLICADO + " CUIT", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.DOCUMENTO_UNICO:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_DOCUMENTO,
                            MensajeRespuesta.DATO_DUPLICADO + " DNI", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.ID_UNICO:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_ID,
                            MensajeRespuesta.DATO_DUPLICADO + " ID", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.NOMBRE_UNICO:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_NOMBRE,
                            MensajeRespuesta.DATO_DUPLICADO + " NOMBRE", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.REPARTO_ORDENRECOLECCION_UNICOS:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_REPARTO_ORECOLECCION,
                            MensajeRespuesta.DATO_DUPLICADO + " REPARTO Y ORDEN RECOLECCION", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.REPARTO_VENTACOMPROBANTE_UNICOS:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_REPARTO_VCOMPROBANTE,
                            MensajeRespuesta.DATO_DUPLICADO + " REPARTO Y VENTA COMPROBANTE", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.REPARTO_VIAJEREMITO_UNICOS:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_REPARTO_VREMITO,
                            MensajeRespuesta.DATO_DUPLICADO + " REPARTO Y VIAJE REMITO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.USERNAME_UNICO:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_USERNAME,
                            MensajeRespuesta.DATO_DUPLICADO + " USUARIO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.UNICIDAD_TABLA:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_TRAMO,
                            MensajeRespuesta.DATO_DUPLICADO, 0), HttpStatus.INTERNAL_SERVER_ERROR);
                default:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.ERROR_INTERNO_SERVIDOR,
                            MensajeRespuesta.ERROR_INTERNO_SERVIDOR, 0), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            //Determina la longitud de la columna 
            switch (partes[1]) {
                case LongitudError.ABREVIATURA_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.ABREVIATURA_LONGITUD,
                            MensajeRespuesta.LONGITUD + " ABREVIATURA", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.ALIAS_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.ALIAS_LONGITUD,
                            MensajeRespuesta.LONGITUD + " ALIAS", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.ALIAS_CBU_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.ALIAS_CBU_LONGITUD,
                            MensajeRespuesta.LONGITUD + " ALIAS CBU", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.APELLIDO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.APELLIDO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " APELLIDO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.CODIGO_AFIP_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.CODIGO_AFIP_LONGITUD,
                            MensajeRespuesta.LONGITUD + " CODIGO AFIP", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.CODIGO_AREA_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.CODIGO_AREA_LONGITUD,
                            MensajeRespuesta.LONGITUD + " CODIGO DE AREA", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.CODIGO_AREA_ALT_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.CODIGO_AREA_ALT_LONGITUD,
                            MensajeRespuesta.LONGITUD + " CODIGO DE AREA ALT", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.CORREO_ELECTRONICO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.CORREO_ELECTRONICO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " CORREO ELECTRONICO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.CUIL_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.CUIL_LONGITUD,
                            MensajeRespuesta.LONGITUD + " CUIL", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.CUIT_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.CUIT_LONGITUD,
                            MensajeRespuesta.LONGITUD + " CUIT", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.DESCRIPCION_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DESCRIPCION_LONGITUD,
                            MensajeRespuesta.LONGITUD + " DESCRIPCION", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.DOCUMENTO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DOCUMENTO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " DNI", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.DOMICILIO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DOMICILIO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " DOMICILIO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.DOMICILIO_ALT_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DOMICILIO_ALT_LONGITUD,
                            MensajeRespuesta.LONGITUD + " DOMICILIO ALT", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.DOMINIO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DOMINIO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " DOMINIO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.LETRA_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.LETRA_LONGITUD,
                            MensajeRespuesta.LONGITUD + " LETRA", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.MODELO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.MODELO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " MODELO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NOMBRE_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NOMBRE_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NOMBRE", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NOMBRE_COMPLETO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NOMBRE_COMPLETO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NOMBRE COMPLETO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NOMBRE_FANTASIA_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NOMBRE_FANTASIA_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NOMBRE FANTASIA", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NOTA_EMISION_COMPROBANTE_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NOTA_EMISION_COMPROBANTE_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NOTA EMISION COMPROBANTE", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NOTA_INGRESAR_COMPROBANTE_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NOTA_INGRESAR_COMPROBANTE_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NOTA INGRESAR COMPROBANTE", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NOTA_IMPRESION_COMPROBANTE_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NOTA_IMPRESION_COMPROBANTE_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NOTA IMPRESION COMPROBANTE", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NOTA_IMPRESION_COMPROBANTE_GRAL_1_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NOTA_IMPRESION_COMPROBANTE_GRAL_1_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NOTA IMPRESION COMPROBANTE GENERAL 1", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NOTA_IMPRESION_COMPROBANTE_GRAL_2_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NOTA_IMPRESION_COMPROBANTE_GRAL_2_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NOTA IMPRESION COMPROBANTE GENERAL 2", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NOTA_IMPRESION_ORDEN_PAGO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NOTA_IMPRESION_ORDEN_PAGO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NOTA IMPRESION ORDEN PAGO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NOTA_IMPRESION_REMITO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NOTA_IMPRESION_REMITO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NOTA IMPRESION REMITO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NUMERO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NUMERO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NUMERO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NUMERO_CAEA_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NUMERO_CAEA_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NUMERO CAEA", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NUMERO_CBU_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.CBU_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NUMERO CBU", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NUMERO_CHASIS_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NUMERO_CHASIS_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NUMERO CHASIS", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NUMERO_CUENTA_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NUMERO_CUENTA_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NUMERO CUENTA", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NUMERO_DOCUMENTO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DOCUMENTO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " DNI", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NUMERO_IIBB_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NUMERO_IIBB_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NUMERO IIBB", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NUMERO_INTERNO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NUMERO_INTERNO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NUMERO INTERNO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NUMERO_MOTOR_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NUMERO_MOTOR_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NUMERO MOTOR", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NUMERO_POLIZA_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NUMERO_POLIZA_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NUMERO POLIZA", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NUMERO_POLIZA_SEGURO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NUMERO_POLIZA_SEGURO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NUMERO POLIZA SEGURO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NUMERO_REMITO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NUMERO_REMITO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NUMERO REMITO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NUMERO_RUTA_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NUMERO_RUTA_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NUMERO RUTA", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.OBSERVACION_CHOFER_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.OBSERVACION_CHOFER_LONGITUD,
                            MensajeRespuesta.LONGITUD + " OBSERVACION CHOFER", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.OBSERVACION_VEHICULO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.OBSERVACION_VEHICULO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " OBSERVACION VEHIHCULO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.OBSERVACION_VEHICULO_REMOLQUE_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.OBSERVACION_VEHICULO_REMOLQUE_LONGITUD,
                            MensajeRespuesta.LONGITUD + " OBSERVACION VEHICULO REMOLQUE", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.OBSERVACIONES_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.OBSERVACIONES_LONGITUD,
                            MensajeRespuesta.LONGITUD + " OBSERVACIONES", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.OBSERVACIONES_ANULADO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.OBSERVACIONES_ANULADO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " OBSERVACIONES ANULADO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.PASSWORD_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.PASSWORD_LONGITUD,
                            MensajeRespuesta.LONGITUD + " PASSWORD", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.RAZON_SOCIAL_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.RAZON_SOCIAL_LONGITUD,
                            MensajeRespuesta.LONGITUD + " RAZON SOCIAL", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.RUTA_ALTERNATIVA_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.RUTA_ALTERNATIVA_LONGITUD,
                            MensajeRespuesta.LONGITUD + " RUTA ALTERNATIVA", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.SITIO_WEB_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.SITIO_WEB_LONGITUD,
                            MensajeRespuesta.LONGITUD + " SITIO WEB", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.TALLE_CAMISA_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.TALLE_CAMISA_LONGITUD,
                            MensajeRespuesta.LONGITUD + " TALLE CAMISA", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.TALLE_CALZADO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.TALLE_CALZADO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " TALLE CAlZADO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.TALLE_PANTALON_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.TALLE_PANTALON_LONGITUD,
                            MensajeRespuesta.LONGITUD + " TALLE PANTALON", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.TELEFONO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.TELEFONO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " TELEFONO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.TELEFONO_ALT_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.TELEFONO_ALT_LONGITUD,
                            MensajeRespuesta.LONGITUD + " TELEFONO ALT", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.TELEFONO_FIJO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.TELEFONO_FIJO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " TELEFONO FIJO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.TELEFONO_MOVIL_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.TELEFONO_MOVIL_LONGITUD,
                            MensajeRespuesta.LONGITUD + " TELEFONO MOVIL", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.TELEFONO_MOVIL_EMPRESA_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.TELEFONO_MOVIL_EMPRESA_LONGITUD,
                            MensajeRespuesta.LONGITUD + " TELEFONO MOVIL EMPRESA", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.TELEFONO_MOVIL_OBSERVACION_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.TELEFONO_MOVIL_OBSERVACION_LONGITUD,
                            MensajeRespuesta.LONGITUD + " TELEFONO MOVIL OBSERVACION", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.TITULAR_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.TITULAR_LONGITUD,
                            MensajeRespuesta.LONGITUD + " TITULAR", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.USERNAME_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.USERNAME_LONGITUD,
                            MensajeRespuesta.LONGITUD + " USUARIO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.URL_PRUEBA_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.URL_PRUEBA_LONGITUD,
                            MensajeRespuesta.LONGITUD + " URL PRUEBA", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.URL_REAL_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.URL_REAL_LONGITUD,
                            MensajeRespuesta.LONGITUD + " URL REAL", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                default:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.ERROR_INTERNO_SERVIDOR,
                            MensajeRespuesta.ERROR_INTERNO_SERVIDOR, 0), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}
