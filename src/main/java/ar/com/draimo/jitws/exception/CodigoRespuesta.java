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
    
    //Define una respuesta de Cerrado con exito
    public static final int CERRADO_CON_EXITO = 5002;
    
    //Define una respuesta de registro no existente
    public static final int NO_EXISTENTE = 5003;
    
    
    //Define una respuesta de datos duplicados
    //Los codigos estan ordenados alfabeticamente.
    //Ultimo codigo datoDuplicado = 11023
    public static final int DATO_DUPLICADO = 11020;
    public static final int DATO_DUPLICADO_ABREVIATURA = 11004;
    public static final int DATO_DUPLICADO_CBU = 11011;
    public static final int DATO_DUPLICADO_CODIGO_AFIP = 11019;
    public static final int DATO_DUPLICADO_CORREOELECTRONICO = 11003;
    public static final int DATO_DUPLICADO_CUIL = 11012;
    public static final int DATO_DUPLICADO_CUIT = 11007;
    public static final int DATO_DUPLICADO_DOCUMENTO = 11010;
    public static final int DATO_DUPLICADO_DOMINIO = 11017;
    public static final int DATO_DUPLICADO_ID = 11001;
    public static final int DATO_DUPLICADO_NOMBRE = 11002;
    public static final int DATO_DUPLICADO_NUMERO_INTERNO = 11018;
    public static final int DATO_DUPLICADO_RAZONSOCIAL = 11006;
    public static final int DATO_DUPLICADO_REPARTO_VCOMPROBANTE = 11021;
    public static final int DATO_DUPLICADO_REPARTO_ORECOLECCION = 11022;
    public static final int DATO_DUPLICADO_REPARTO_VREMITO = 11023;
    public static final int DATO_DUPLICADO_SITIOWEB = 11008;
    public static final int DATO_DUPLICADO_TELEFONO = 11009;
    public static final int DATO_DUPLICADO_TELEFONO_FIJO = 11013;
    public static final int DATO_DUPLICADO_TELEFONO_MOVIL = 11014;
    public static final int DATO_DUPLICADO_TELEFONO_MOVIL_EMPRESA = 11015;
    public static final int DATO_DUPLICADO_USERNAME = 11005;
    public static final int DATO_DUPLICADO_VALOR = 11016;
    
    //Ultimo codigo Longitud = 12066
    public static final int ABREVIATURA_LONGITUD = 12015;
    public static final int ALIAS_LONGITUD = 12026;
    public static final int ALIAS_CBU_LONGITUD = 12052;
    public static final int APELLIDO_LONGITUD = 12002;
    public static final int CBU_LONGITUD = 12051;
    public static final int CODIGO_AFIP_LONGITUD = 12018;
    public static final int CODIGO_AREA_LONGITUD = 12004;
    public static final int CODIGO_AREA_ALT_LONGITUD = 12005;
    public static final int CORREO_ELECTRONICO_LONGITUD = 12013;
    public static final int CUIL_LONGITUD = 12010;
    public static final int CUIT_LONGITUD = 12011;
    public static final int DESCRIPCION_LONGITUD = 12021;
    public static final int DESCRIPCION_CARGA_LONGITUD = 12040;
    public static final int DOCUMENTO_LONGITUD = 12003;
    public static final int DOMICILIO_LONGITUD = 12008;
    public static final int DOMICILIO_ALT_LONGITUD = 12009;
    public static final int DOMINIO_LONGITUD = 12055;
    public static final int LETRA_LONGITUD = 12020;
    public static final int MODELO_LONGITUD = 12036;
    public static final int NOMBRE_LONGITUD = 12001;
    public static final int NOMBRE_COMPLETO_LONGITUD = 12041;
    public static final int NOMBRE_FANTASIA_LONGITUD = 12028;
    public static final int NOTA_EMISION_COMPROBANTE_LONGITUD = 12032;
    public static final int NOTA_IMPRESION_COMPROBANTE_LONGITUD = 12033;
    public static final int NOTA_IMPRESION_COMPROBANTE_GRAL_1_LONGITUD = 12060;
    public static final int NOTA_IMPRESION_COMPROBANTE_GRAL_2_LONGITUD = 12061;
    public static final int NOTA_IMPRESION_ORDEN_PAGO_LONGITUD = 12048;
    public static final int NOTA_IMPRESION_REMITO_LONGITUD = 12034;
    public static final int NOTA_INGRESAR_COMPROBANTE_LONGITUD = 12047;
    public static final int NUMERO_LONGITUD = 12014;
    public static final int NUMERO_CAEA_LONGITUD = 12019;
    public static final int NUMERO_CHASIS_LONGITUD = 12058;
    public static final int NUMERO_CUENTA_LONGITUD = 12049;
    public static final int NUMERO_IIBB_LONGITUD = 12029;
    public static final int NUMERO_INTERNO_LONGITUD = 12056;
    public static final int NUMERO_MOTOR_LONGITUD = 12057;
    public static final int NUMERO_POLIZA_LONGITUD = 12035;
    public static final int NUMERO_POLIZA_SEGURO_LONGITUD = 12030;
    public static final int NUMERO_REMITO_LONGITUD = 12066;
    public static final int NUMERO_RUTA_LONGITUD = 12059;
    public static final int OBSERVACION_CHOFER_LONGITUD = 12064;
    public static final int OBSERVACION_VEHICULO_LONGITUD = 12062;
    public static final int OBSERVACION_VEHICULO_REMOLQUE_LONGITUD = 12063;
    public static final int OBSERVACIONES_LONGITUD = 12031;
    public static final int OBSERVACIONES_ANULADO_LONGITUD = 12065;
    public static final int PASSWORD_LONGITUD = 12054;
    public static final int RAZON_SOCIAL_LONGITUD = 12027;
    public static final int RUTA_ALTERNATIVA_LONGITUD = 12053;
    public static final int SITIO_WEB_LONGITUD = 12016;
    public static final int SIMBOLO_LONGITUD = 12037;
    public static final int SOLICITADO_POR_LONGITUD = 12038;
    public static final int TALLE_CAMISA_LONGITUD = 12042;
    public static final int TALLE_CALZADO_LONGITUD = 12044;
    public static final int TALLE_PANTALON_LONGITUD = 12043;
    public static final int TELEFONO_LONGITUD = 12006;
    public static final int TELEFONO_ALT_LONGITUD = 12007;
    public static final int TELEFONO_CONTACTO_LONGITUD = 12039;
    public static final int TELEFONO_FIJO_LONGITUD = 12024;
    public static final int TELEFONO_MOVIL_LONGITUD = 12025;
    public static final int TELEFONO_MOVIL_EMPRESA_LONGITUD = 12045;
    public static final int TELEFONO_MOVIL_OBSERVACION_LONGITUD = 12046;
    public static final int TITULAR_LONGITUD = 12050;
    public static final int URL_PRUEBA_LONGITUD = 12023;
    public static final int URL_REAL_LONGITUD = 12022;
    public static final int USERNAME_LONGITUD = 12017;
    
}
