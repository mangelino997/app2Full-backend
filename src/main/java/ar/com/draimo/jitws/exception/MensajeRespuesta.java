package ar.com.draimo.jitws.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author blas
 */
public class MensajeRespuesta {
    
    /**** MENSAJES ****/
    
    public static final String AGREGADO = "Registro agregado con éxito";
    public static final String ACTUALIZADO = "Registro actualizado con éxito";
    public static final String ELIMINADO = "Registro eliminado con éxito";
    public static final String REASIGNADO = "Registro reasignado con éxito";
    public static final String SIN_CONTENIDO = "Sin contenido";
    public static final String LISTA_SIN_CONTENIDO = "Lista sin contenido";
    public static final String NO_AUTORIZADO = "Usuario o contraseña incorrecto";
    public static final String DATO_DUPLICADO = "Dato duplicado";
    public static final String ERROR_INTERNO_SERVIDOR = "Error interno en el servidor";
    public static final String ASIGNADOS = "Registros asignados con éxito";
    public static final String QUITADOS = "Registros quitados con éxito";
    public static final String TRANSACCION_NO_ACTUALIZADA = "Registro actualizado por otra transacción";
    public static final String ERROR_SINC_SOCKET = "Error de sincronización de datos";
    public static final String TABLA_REESTABLECIDA = "Tabla reestablecida con éxito";
    public static final String LONGITUD = "Cantidad caracteres excedida en";

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
    //Envia mensaje de dato repetido
    public static ResponseEntity<?> datoDuplicado(DataIntegrityViolationException dive) {
        //Obtiene el mensaje de duplicidad de datos
        String[] partes = dive.getMostSpecificCause().getMessage().split("'");
        if (partes.length == 4) {
            //Determina que columna tiene el dato duplicado
            switch (partes[3]) {
                case DuplicidadError.ID_UNICO:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_ID,
                            MensajeRespuesta.DATO_DUPLICADO + " ID", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.DOCUMENTO_UNICO:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_DOCUMENTO,
                            MensajeRespuesta.DATO_DUPLICADO + " DNI", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.NOMBRE_UNICO:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_NOMBRE,
                            MensajeRespuesta.DATO_DUPLICADO + " NOMBRE", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.CUIL_UNICO:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_CUIL,
                            MensajeRespuesta.DATO_DUPLICADO + " CUIL", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.CUIT_UNICO:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_CUIT,
                            MensajeRespuesta.DATO_DUPLICADO + " CUIT", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.CORREOELECTRONICO_UNICO:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_CORREOELECTRONICO,
                            MensajeRespuesta.DATO_DUPLICADO + " CORREO ELECTRONICO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.ABREVIATURA_UNICO:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_ABREVIATURA,
                            MensajeRespuesta.DATO_DUPLICADO + " ABREVIATURA", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case DuplicidadError.USERNAME_UNICO:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DATO_DUPLICADO_USERNAME,
                            MensajeRespuesta.DATO_DUPLICADO + " USUARIO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                default:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.ERROR_INTERNO_SERVIDOR,
                            MensajeRespuesta.ERROR_INTERNO_SERVIDOR, 0), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            //Determina la longitud de la columna 
            switch (partes[1]) {
                case LongitudError.NOMBRE_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NOMBRE_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NOMBRE", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.APELLIDO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.APELLIDO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " APELLIDO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.DOCUMENTO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DOCUMENTO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " DNI", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.CODIGO_AREA_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.CODIGO_AREA_LONGITUD,
                            MensajeRespuesta.LONGITUD + " CODIGO DE AREA", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.CODIGO_AREA_ALT_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.CODIGO_AREA_ALT_LONGITUD,
                            MensajeRespuesta.LONGITUD + " CODIGO DE AREA ALT", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.TELEFONO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.TELEFONO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " TELEFONO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.TELEFONO_ALT_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.TELEFONO_ALT_LONGITUD,
                            MensajeRespuesta.LONGITUD + " TELEFONO ALT", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.DIRECCION_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DIRECCION_LONGITUD,
                            MensajeRespuesta.LONGITUD + " DIRECCION", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.DIRECCION_ALT_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.DIRECCION_ALT_LONGITUD,
                            MensajeRespuesta.LONGITUD + " DIRECCION ALT", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.CUIL_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.CUIL_LONGITUD,
                            MensajeRespuesta.LONGITUD + " CUIL", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.CUIT_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.CUIT_LONGITUD,
                            MensajeRespuesta.LONGITUD + " CUIT", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.CORREO_ELECTRONICO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.CORREO_ELECTRONICO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " CORREO ELECTRONICO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.NUMERO_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.NUMERO_LONGITUD,
                            MensajeRespuesta.LONGITUD + " NUMERO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.ABREVIATURA_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.ABREVIATURA_LONGITUD,
                            MensajeRespuesta.LONGITUD + " ABREVIATURA", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.PAGINA_WEB_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.PAGINA_WEB_LONGITUD,
                            MensajeRespuesta.LONGITUD + " PAGINA WEB", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                case LongitudError.USERNAME_LONGITUD:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.USERNAME_LONGITUD,
                            MensajeRespuesta.LONGITUD + " USUARIO", 0), HttpStatus.INTERNAL_SERVER_ERROR);
                default:
                    return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.ERROR_INTERNO_SERVIDOR,
                            MensajeRespuesta.ERROR_INTERNO_SERVIDOR, 0), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}
