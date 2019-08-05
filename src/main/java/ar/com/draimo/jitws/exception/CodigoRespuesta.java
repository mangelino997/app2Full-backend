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
    
    //Define una respuesta de rol asignado a usuario
    public static final int ROL_ASIGNADO = 5004;
    
    //Define una respuesta de datos duplicados
    //Los codigos estan ordenados alfabeticamente.
    //Ultimo codigo datoDuplicado = 11027
    public static final int DATO_DUPLICADO = 11020;
    public static final int DATO_DUPLICADO_ABREVIATURA = 11004;
    public static final int DATO_DUPLICADO_CBU = 11011;
    public static final int DATO_DUPLICADO_CODIGO_AFIP = 11019;
    public static final int DATO_DUPLICADO_CORREOELECTRONICO = 11003;
    public static final int DATO_DUPLICADO_CUIL = 11012;
    public static final int DATO_DUPLICADO_CUIT = 11007;
    public static final int DATO_DUPLICADO_DESDE_HASTA = 11024;
    public static final int DATO_DUPLICADO_DOCUMENTO = 11010;
    public static final int DATO_DUPLICADO_DOMINIO = 11017;
    public static final int DATO_DUPLICADO_ESCALA_TARIFA = 11025;
    public static final int DATO_DUPLICADO_ID = 11001;
    public static final int DATO_DUPLICADO_NOMBRE = 11002;
    public static final int DATO_DUPLICADO_NUMERO_INTERNO = 11018;
    public static final int DATO_DUPLICADO_ORDEN_VENTA = 11027;
    public static final int DATO_DUPLICADO_RAZONSOCIAL = 11006;
    public static final int DATO_DUPLICADO_REPARTO_VCOMPROBANTE = 11021;
    public static final int DATO_DUPLICADO_REPARTO_ORECOLECCION = 11022;
    public static final int DATO_DUPLICADO_REPARTO_VREMITO = 11023;
    public static final int DATO_DUPLICADO_SITIOWEB = 11008;
    public static final int DATO_DUPLICADO_TELEFONO = 11009;
    public static final int DATO_DUPLICADO_TELEFONO_FIJO = 11013;
    public static final int DATO_DUPLICADO_TELEFONO_MOVIL = 11014;
    public static final int DATO_DUPLICADO_TELEFONO_MOVIL_EMPRESA = 11015;
    public static final int DATO_DUPLICADO_TIPO_TARIFA = 11025;
    public static final int DATO_DUPLICADO_USERNAME = 11005;
    public static final int DATO_DUPLICADO_VALOR = 11016;
    public static final int DATO_DUPLICADO_TRAMO = 11017;
    
    //Ultimo codigo Longitud = 12067
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
    public static final int PUNTO_VENTA_LONGITUD = 12066;
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
    public static final int ARCHIVO_LONGITUD = 12067;
    
    public static final int AFIP_ACTIVIDAD_INEXISTENTE = 1;
    public static final int AFIP_ALICUOTA_IVA_INEXISTENTE = 2;
    public static final int AFIP_COMPROBANTE_INEXISTENTE = 3;
    public static final int AFIP_CONCEPTO_INEXISTENTE = 4;
    public static final int AFIP_CONDICION_INEXISTENTE = 5;
    public static final int AFIP_CONDICION_IVA_INEXISTENTE = 6;
    public static final int AFIP_CONDICION_IVA_PROVEEDOR_INEXISTENTE = 7;
    public static final int AFIP_LOCALIDAD_INEXISTENTE = 8;
    public static final int AFIP_MOD_CONTRATACION_INEXISTENTE = 9;
    public static final int AFIP_SINIESTRADO_INEXISTENTE = 10;
    public static final int AFIP_SITUACION_INEXISTENTE = 11;
    public static final int AREA_INEXISTENTE = 12;
    public static final int BANCO_INEXISTENTE = 13;
    public static final int BARRIO_INEXISTENTE = 14;
    public static final int BUG_IMAGEN_INEXISTENTE = 15;
    public static final int CATEGORIA_INEXISTENTE = 16;
    public static final int CHEQUE_CARTERA_INEXISTENTE = 17;
    public static final int CHOFER_PROVEEDOR_INEXISTENTE = 18;
    public static final int CLIENTE_INEXISTENTE = 19;
    public static final int CLIENTE_DESTINATARIO_INEXISTENTE = 20;
    public static final int CLIENTE_DESTINATARIO_SUC_INEXISTENTE = 21;
    public static final int CLIENTE_GRUPO_INEXISTENTE = 22;
    public static final int CLIENTE_REMITENTE_INEXISTENTE = 23;
    public static final int COBRADOR_INEXISTENTE = 24;
    public static final int COMPANIA_SEGURO_INEXISTENTE = 25;
    public static final int COMPANIA_SEGURO_POLIZA_INEXISTENTE = 26;
    public static final int COMPRA_COMPROBANTE_INEXISTENTE = 27;
    public static final int COMPRA_COMPROBANTE_PERCEPCION_INEXISTENTE = 28;
    public static final int CONDICION_COMPRA_INEXISTENTE = 29;
    public static final int CONDICION_VENTA_INEXISTENTE = 30;
    public static final int CONFIGURACION_VEHICULO_INEXISTENTE = 31;
    public static final int CUENTA_BANCARIA_INEXISTENTE = 32;
    public static final int CUENTA_CONTABLE_INEXISTENTE = 33;
    public static final int CUENTA_GRUPO_INEXISTENTE = 34;
    public static final int DEPOSITO_INSUMO_PRODUCTO_INEXISTENTE = 35;
    public static final int DESTINO_INEXISTENTE = 36;
    public static final int EMPRESA_INEXISTENTE = 37;
    public static final int EMPRESA_C_FISCAL_INEXISTENTE = 38;
    public static final int EMPRESA_EMISION_INEXISTENTE = 39;
    public static final int ESCALA_TARIFA_INEXISTENTE = 40;
    public static final int ESTADO_CIVIL_INEXISTENTE = 41;
    public static final int FOTO_INEXISTENTE = 42;
    public static final int GRUPO_CUENTA_CONTABLE_INEXISTENTE = 43;
    public static final int LOCALIDAD_INEXISTENTE = 44;
    public static final int LOCALIDAD_NACIMIENTO_INEXISTENTE = 45;
    public static final int INSUMO_PRODUCTO_INEXISTENTE = 46;
    public static final int MARCA_PRODUCTO_INEXISTENTE = 47;
    public static final int MARCA_VEHICULO_INEXISTENTE = 48;
    public static final int MES_INEXISTENTE = 49;
    public static final int MES_INICIO_INEXISTENTE = 50;
    public static final int MODULO_INEXISTENTE = 51;
    public static final int MONEDA_INEXISTENTE = 52;
    public static final int OBRA_SOCIAL_INEXISTENTE = 53;
    public static final int OPCION_INEXISTENTE = 54;
    public static final int ORDEN_VENTA_INEXISTENTE = 55;
    public static final int ORDEN_VENTA_TARIFA_INEXISTENTE = 56;
    public static final int ORDEN_RECOLECCION_INEXISTENTE = 57;
    public static final int ORIGEN_INEXISTENTE = 58;
    public static final int PADRE_INEXISTENTE = 59;
    public static final int PAIS_INEXISTENTE = 60;
    public static final int PDF_INEXISTENTE = 61;
    public static final int PDF_ALTA_TEMPRANA_INEXISTENTE = 62;
    public static final int PDF_CEDULA_IDENT_INEXISTENTE = 63;
    public static final int PDF_DNI_INEXISTENTE = 64;
    public static final int PDF_HAB_BROMAT_INEXISTENTE = 65;
    public static final int PDF_LIB_SANIDAD_INEXISTENTE = 66;
    public static final int PDF_LIC_CONDUCIR_INEXISTENTE = 67;
    public static final int PDF_LINTI_INEXISTENTE = 68;
    public static final int PDF_TITULO_INEXISTENTE = 69;
    public static final int PDF_VTO_INSP_TECNICA_INEXISTENTE = 70;
    public static final int PDF_VTO_RUTA_INEXISTENTE = 71;
    public static final int PDF_SENASA_INEXISTENTE = 72;
    public static final int PERSONAL_INEXISTENTE = 73;
    public static final int PESTANIA_INEXISTENTE = 74;
    public static final int PLAN_DE_CUENTA_INEXISTENTE = 75;
    public static final int PROVEEDOR_INEXISTENTE = 76;
    public static final int PROVINCIA_INEXISTENTE = 77;
    public static final int REGISTRO_C_FISCAL_INEXISTENTE = 78;
    public static final int REPARTO_INEXISTENTE = 79;
    public static final int RESUMEN_CLIENTE_INEXISTENTE = 80;
    public static final int RETIRO_DEPOSITO_INEXISTENTE = 81;
    public static final int ROL_INEXISTENTE = 82;
    public static final int ROL_SECUNDARIO_INEXISTENTE = 83;
    public static final int RUBRO_INEXISTENTE = 84;
    public static final int RUBRO_PRODUCTO_INEXISTENTE = 85;
    public static final int SEGUIMIENTO_INEXISTENTE = 86;
    public static final int SEGUIMIENTO_ESTADO_INEXISTENTE = 87;
    public static final int SEGUIMIENTO_SITUACION_INEXISTENTE = 88;
    public static final int SEGURIDAD_SOCIAL_INEXISTENTE = 89;
    public static final int SEXO_INEXISTENTE = 90;
    public static final int SINDICATO_INEXISTENTE = 91;
    public static final int SITUACION_CLIENTE_INEXISTENTE = 92;
    public static final int SOPORTE_ESTADO_INEXISTENTE = 93;
    public static final int SUBMODULO_INEXISTENTE = 94;
    public static final int SUBOPCION_INEXISTENTE = 95;
    public static final int SUCURSAL_INEXISTENTE = 96;
    public static final int SUCURSAL_BANCO_INEXISTENTE = 97;
    public static final int SUCURSAL_CLIENTE_DES_INEXISTENTE = 98;
    public static final int SUCURSAL_CLIENTE_REM_INEXISTENTE = 99;
    public static final int SUCURSAL_DESTINO_INEXISTENTE = 100;
    public static final int SUCURSAL_INGRESO_INEXISTENTE = 101;
    public static final int SUCURSAL_LUGAR_PAGO_INEXISTENTE = 102;
    public static final int TALONARIO_RECIBO_LOTE_INEXISTENTE = 103;
    public static final int TIPO_CHEQUERA_INEXISTENTE = 104;
    public static final int TIPO_COMPROBANTE_INEXISTENTE = 105;
    public static final int TIPO_CONTACTO_INEXISTENTE = 106;
    public static final int TIPO_CUENTA_BANCARIA_INEXISTENTE = 107;
    public static final int TIPO_CUENTA_CONTABLE_INEXISTENTE = 108;
    public static final int TIPO_DOCUMENTO_INEXISTENTE = 109;
    public static final int TIPO_PERCEPCION_INEXISTENTE = 110;
    public static final int TIPO_PROVEEDOR_INEXISTENTE = 111;
    public static final int TIPO_TARIFA_INEXISTENTE = 112;
    public static final int TIPO_VEHICULO_INEXISTENTE = 113;
    public static final int TRAMO_INEXISTENTE = 114;
    public static final int UNIDAD_MEDIDA_INEXISTENTE = 115;
    public static final int USUARIO_INEXISTENTE = 116;
    public static final int USUARIO_ALTA_INEXISTENTE = 117;
    public static final int USUARIO_BAJA_INEXISTENTE = 118;
    public static final int USUARIO_CHOFER_AUTORIZADO_INEXISTENTE = 119;
    public static final int USUARIO_DOCUMENTACION_INEXISTENTE = 120;
    public static final int USUARIO_LIQUIDACION_INEXISTENTE = 121;
    public static final int USUARIO_MOD_INEXISTENTE = 122;
    public static final int USUARIO_MOD_CURSO_INEXISTENTE = 123;
    public static final int USUARIO_MOD_CURSO_CP_INEXISTENTE = 124;
    public static final int USUARIO_MOD_LC_INEXISTENTE = 125;
    public static final int USUARIO_MOD_LINTI_INEXISTENTE = 126;
    public static final int USUARIO_MOD_LS_INEXISTENTE = 127;
    public static final int VEHICULO_INEXISTENTE = 128;
    public static final int VEHICULO_AUTORIZADO_INEXISTENTE = 129;
    public static final int VEHICULO_PROVEEDOR_INEXISTENTE = 130;
    public static final int VEHICULO_REM_AUTORIZADO_INEXISTENTE = 131;
    public static final int VEHICULO_REMOLQUE_INEXISTENTE = 132;
    public static final int VEHICULO_REMOLQUE_PROVEEDOR_INEXISTENTE = 133;
    public static final int VENDEDOR_INEXISTENTE = 134;
    public static final int VENTA_COMPROBANTE_INEXISTENTE = 135;
    public static final int VENTA_COMPROBANTE_APLICADO_INEXISTENTE = 136;
    public static final int VENTA_ITEM_CONCEPTO_INEXISTENTE = 137;
    public static final int VENTA_TIPO_ITEM_INEXISTENTE = 138;
    public static final int VIAJE_INEXISTENTE = 139;
    public static final int VIAJE_REMITO_INEXISTENTE = 140;
    public static final int VIAJE_TARIFA_INEXISTENTE = 141;
    public static final int VIAJE_TIPO_INEXISTENTE = 142;
    public static final int VIAJE_TIPO_CARGA_INEXISTENTE = 143;
    public static final int VIAJE_TRAMO_INEXISTENTE = 144;
    public static final int VIAJE_UNIDAD_NEGOCIO_INEXISTENTE = 145;
    public static final int ZONA_INEXISTENTE = 146;
}
