/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.draimo.jitws.exception;

/**
 * Clase Codigos de respuesta
 * Define las constantes utilizadas para cada codigo de respuesta
 * @author blas
 */

/*

*/
public class CodigoRespuesta {
    
    //Define una respuesta OK
    public static final int OK = 200;
    
    //Define una respuesta de CREADO
    public static final int CREADO = 201;
    
    //Define una respuesta de SIN CONTENIDO
    public static final int SIN_CONTENIDO = 204;
    
    //Define una respuesta de NO AUTORIZADO
    public static final int NO_AUTORIZADO = 401;
    
    //Define una respuesta de NO ENCONTRADO
    public static final int NO_ENCONTRADO = 404;
    
    //Define una respuesta de CONFLICTO
    public static final int CONFLICTO = 409;
    
    //Define una respuesta de ERROR INTERNO EN EL SERVIDOR
    public static final int ERROR_INTERNO_SERVIDOR = 500;
    
    //Define una respuesta de TRANSACCION NO ACTUALIZADA
    public static final int TRANSACCION_NO_ACTUALIZADA = 600;
    
    //Define una respuesta de sincronizacion mediante socket
    public static final int ERROR_SINC_SOCKET = 5001;
    
    
    //Define una respuesta de datos duplicados
    public static final int DATO_DUPLICADO_ID = 11001;
    public static final int DATO_DUPLICADO_NOMBRE = 11002;
    public static final int DATO_DUPLICADO_CORREOELECTRONICO = 11003;
    public static final int DATO_DUPLICADO_ABREVIATURA = 11004;
    public static final int DATO_DUPLICADO_USERNAME = 11005;
    public static final int DATO_DUPLICADO_RAZONSOCIAL = 11006;
    public static final int DATO_DUPLICADO_CUIT = 11007;
    public static final int DATO_DUPLICADO_SITIOWEB = 11008;
    public static final int DATO_DUPLICADO_TELEFONO = 11009;
    public static final int DATO_DUPLICADO_DOCUMENTO = 11010;
    public static final int DATO_DUPLICADO_CBU = 11011;
    public static final int DATO_DUPLICADO_CUIL = 11012;
    public static final int DATO_DUPLICADO_TELEFONO_FIJO = 11013;
    public static final int DATO_DUPLICADO_TELEFONO_MOVIL = 11014;
    public static final int DATO_DUPLICADO_TELEFONO_MOVIL_EMPRESA = 11015;
    public static final int DATO_DUPLICADO_VALOR = 11016;
    public static final int DATO_DUPLICADO_DOMINIO = 11017;
    public static final int DATO_DUPLICADO_NUMERO_INTERNO = 11018;
    
     public static final int NOMBRE_LONGITUD = 12001;
    public static final int APELLIDO_LONGITUD = 12002;
    public static final int DOCUMENTO_LONGITUD = 12003;
    public static final int CODIGO_AREA_LONGITUD = 12004;
    public static final int CODIGO_AREA_ALT_LONGITUD = 12005;
    public static final int TELEFONO_LONGITUD = 12006;
    public static final int TELEFONO_ALT_LONGITUD = 12007;
    public static final int DIRECCION_LONGITUD = 12008;
    public static final int DIRECCION_ALT_LONGITUD = 12009;
    public static final int CUIL_LONGITUD = 12010;
    public static final int CUIT_LONGITUD = 12011;
    public static final int CORREO_ELECTRONICO_LONGITUD = 12013;
    public static final int NUMERO_LONGITUD = 12014;
    public static final int ABREVIATURA_LONGITUD = 12015;
    public static final int PAGINA_WEB_LONGITUD = 12016;
    public static final int USERNAME_LONGITUD = 12017;
    
}
