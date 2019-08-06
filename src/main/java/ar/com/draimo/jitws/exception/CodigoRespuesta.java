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
    
    public static final int AFIP_ACTIVIDAD_INEXISTENTE = 13001;
    public static final int AFIP_ALICUOTA_IVA_INEXISTENTE = 13002;
    public static final int AFIP_COMPROBANTE_INEXISTENTE = 13003;
    public static final int AFIP_CONCEPTO_INEXISTENTE = 13004;
    public static final int AFIP_CONDICION_INEXISTENTE = 13005;
    public static final int AFIP_CONDICION_IVA_INEXISTENTE = 13006;
    public static final int AFIP_CONDICION_IVA_PROVEEDOR_INEXISTENTE = 13007;
    public static final int AFIP_LOCALIDAD_INEXISTENTE = 13008;
    public static final int AFIP_MOD_CONTRATACION_INEXISTENTE = 13009;
    public static final int AFIP_SINIESTRADO_INEXISTENTE = 13010;
    public static final int AFIP_SITUACION_INEXISTENTE = 13011;
    public static final int AREA_INEXISTENTE = 13012;
    public static final int BANCO_INEXISTENTE = 13013;
    public static final int BARRIO_INEXISTENTE = 13014;
    public static final int BUG_IMAGEN_INEXISTENTE = 13015;
    public static final int CATEGORIA_INEXISTENTE = 13016;
    public static final int CHEQUE_CARTERA_INEXISTENTE = 13017;
    public static final int CHOFER_PROVEEDOR_INEXISTENTE = 13018;
    public static final int CLIENTE_INEXISTENTE = 13019;
    public static final int CLIENTE_DESTINATARIO_INEXISTENTE = 13020;
    public static final int CLIENTE_DESTINATARIO_SUC_INEXISTENTE = 13021;
    public static final int CLIENTE_GRUPO_INEXISTENTE = 13022;
    public static final int CLIENTE_REMITENTE_INEXISTENTE = 13023;
    public static final int COBRADOR_INEXISTENTE = 13024;
    public static final int COMPANIA_SEGURO_INEXISTENTE = 13025;
    public static final int COMPANIA_SEGURO_POLIZA_INEXISTENTE = 13026;
    public static final int COMPRA_COMPROBANTE_INEXISTENTE = 13027;
    public static final int COMPRA_COMPROBANTE_PERCEPCION_INEXISTENTE = 13028;
    public static final int CONDICION_COMPRA_INEXISTENTE = 13029;
    public static final int CONDICION_VENTA_INEXISTENTE = 13030;
    public static final int CONFIGURACION_VEHICULO_INEXISTENTE = 13031;
    public static final int CUENTA_BANCARIA_INEXISTENTE = 13032;
    public static final int CUENTA_CONTABLE_INEXISTENTE = 13033;
    public static final int CUENTA_GRUPO_INEXISTENTE = 13034;
    public static final int DEPOSITO_INSUMO_PRODUCTO_INEXISTENTE = 13035;
    public static final int DESTINO_INEXISTENTE = 13036;
    public static final int EMPRESA_INEXISTENTE = 13037;
    public static final int EMPRESA_C_FISCAL_INEXISTENTE = 13038;
    public static final int EMPRESA_EMISION_INEXISTENTE = 13039;
    public static final int ESCALA_TARIFA_INEXISTENTE = 13040;
    public static final int ESTADO_CIVIL_INEXISTENTE = 13041;
    public static final int FOTO_INEXISTENTE = 13042;
    public static final int GRUPO_CUENTA_CONTABLE_INEXISTENTE = 13043;
    public static final int LOCALIDAD_INEXISTENTE = 13044;
    public static final int LOCALIDAD_NACIMIENTO_INEXISTENTE = 13045;
    public static final int INSUMO_PRODUCTO_INEXISTENTE = 13046;
    public static final int MARCA_PRODUCTO_INEXISTENTE = 13047;
    public static final int MARCA_VEHICULO_INEXISTENTE = 13048;
    public static final int MES_INEXISTENTE = 13049;
    public static final int MES_INICIO_INEXISTENTE = 13050;
    public static final int MODULO_INEXISTENTE = 13051;
    public static final int MONEDA_INEXISTENTE = 13052;
    public static final int OBRA_SOCIAL_INEXISTENTE = 13053;
    public static final int OPCION_INEXISTENTE = 13054;
    public static final int ORDEN_VENTA_INEXISTENTE = 13055;
    public static final int ORDEN_VENTA_TARIFA_INEXISTENTE = 13056;
    public static final int ORDEN_RECOLECCION_INEXISTENTE = 13057;
    public static final int ORIGEN_INEXISTENTE = 13058;
    public static final int PADRE_INEXISTENTE = 13059;
    public static final int PAIS_INEXISTENTE = 13060;
    public static final int PDF_INEXISTENTE = 13061;
    public static final int PDF_ALTA_TEMPRANA_INEXISTENTE = 13062;
    public static final int PDF_CEDULA_IDENT_INEXISTENTE = 13063;
    public static final int PDF_DNI_INEXISTENTE = 13064;
    public static final int PDF_HAB_BROMAT_INEXISTENTE = 13065;
    public static final int PDF_LIB_SANIDAD_INEXISTENTE = 13066;
    public static final int PDF_LIC_CONDUCIR_INEXISTENTE = 13067;
    public static final int PDF_LINTI_INEXISTENTE = 13068;
    public static final int PDF_TITULO_INEXISTENTE = 13069;
    public static final int PDF_VTO_INSP_TECNICA_INEXISTENTE = 13070;
    public static final int PDF_VTO_RUTA_INEXISTENTE = 13071;
    public static final int PDF_SENASA_INEXISTENTE = 13072;
    public static final int PERSONAL_INEXISTENTE = 13073;
    public static final int PESTANIA_INEXISTENTE = 13074;
    public static final int PLAN_DE_CUENTA_INEXISTENTE = 13075;
    public static final int PROVEEDOR_INEXISTENTE = 13076;
    public static final int PROVINCIA_INEXISTENTE = 13077;
    public static final int REGISTRO_C_FISCAL_INEXISTENTE = 13078;
    public static final int REPARTO_INEXISTENTE = 13079;
    public static final int RESUMEN_CLIENTE_INEXISTENTE = 13080;
    public static final int RETIRO_DEPOSITO_INEXISTENTE = 13081;
    public static final int ROL_INEXISTENTE = 13082;
    public static final int ROL_SECUNDARIO_INEXISTENTE = 13083;
    public static final int RUBRO_INEXISTENTE = 13084;
    public static final int RUBRO_PRODUCTO_INEXISTENTE = 13085;
    public static final int SEGUIMIENTO_INEXISTENTE = 13086;
    public static final int SEGUIMIENTO_ESTADO_INEXISTENTE = 13087;
    public static final int SEGUIMIENTO_SITUACION_INEXISTENTE = 13088;
    public static final int SEGURIDAD_SOCIAL_INEXISTENTE = 13089;
    public static final int SEXO_INEXISTENTE = 13090;
    public static final int SINDICATO_INEXISTENTE = 13091;
    public static final int SITUACION_CLIENTE_INEXISTENTE = 13092;
    public static final int SOPORTE_ESTADO_INEXISTENTE = 13093;
    public static final int SUBMODULO_INEXISTENTE = 13094;
    public static final int SUBOPCION_INEXISTENTE = 13095;
    public static final int SUCURSAL_INEXISTENTE = 13096;
    public static final int SUCURSAL_BANCO_INEXISTENTE = 13097;
    public static final int SUCURSAL_CLIENTE_DES_INEXISTENTE = 13098;
    public static final int SUCURSAL_CLIENTE_REM_INEXISTENTE = 13099;
    public static final int SUCURSAL_DESTINO_INEXISTENTE = 13100;
    public static final int SUCURSAL_INGRESO_INEXISTENTE = 13101;
    public static final int SUCURSAL_LUGAR_PAGO_INEXISTENTE = 13102;
    public static final int TALONARIO_RECIBO_LOTE_INEXISTENTE = 13103;
    public static final int TIPO_CHEQUERA_INEXISTENTE = 13104;
    public static final int TIPO_COMPROBANTE_INEXISTENTE = 13105;
    public static final int TIPO_CONTACTO_INEXISTENTE = 13106;
    public static final int TIPO_CUENTA_BANCARIA_INEXISTENTE = 13107;
    public static final int TIPO_CUENTA_CONTABLE_INEXISTENTE = 13108;
    public static final int TIPO_DOCUMENTO_INEXISTENTE = 13109;
    public static final int TIPO_PERCEPCION_INEXISTENTE = 13110;
    public static final int TIPO_PROVEEDOR_INEXISTENTE = 13111;
    public static final int TIPO_TARIFA_INEXISTENTE = 13112;
    public static final int TIPO_VEHICULO_INEXISTENTE = 13113;
    public static final int TRAMO_INEXISTENTE = 13114;
    public static final int UNIDAD_MEDIDA_INEXISTENTE = 13115;
    public static final int USUARIO_INEXISTENTE = 13116;
    public static final int USUARIO_ALTA_INEXISTENTE = 13117;
    public static final int USUARIO_BAJA_INEXISTENTE = 13118;
    public static final int USUARIO_CHOFER_AUTORIZADO_INEXISTENTE = 13119;
    public static final int USUARIO_DOCUMENTACION_INEXISTENTE = 13120;
    public static final int USUARIO_LIQUIDACION_INEXISTENTE = 13121;
    public static final int USUARIO_MOD_INEXISTENTE = 13122;
    public static final int USUARIO_MOD_CURSO_INEXISTENTE = 13123;
    public static final int USUARIO_MOD_CURSO_CP_INEXISTENTE = 13124;
    public static final int USUARIO_MOD_LC_INEXISTENTE = 13125;
    public static final int USUARIO_MOD_LINTI_INEXISTENTE = 13126;
    public static final int USUARIO_MOD_LS_INEXISTENTE = 13127;
    public static final int VEHICULO_INEXISTENTE = 13128;
    public static final int VEHICULO_AUTORIZADO_INEXISTENTE = 13129;
    public static final int VEHICULO_PROVEEDOR_INEXISTENTE = 13130;
    public static final int VEHICULO_REM_AUTORIZADO_INEXISTENTE = 13131;
    public static final int VEHICULO_REMOLQUE_INEXISTENTE = 13132;
    public static final int VEHICULO_REMOLQUE_PROVEEDOR_INEXISTENTE = 13133;
    public static final int VENDEDOR_INEXISTENTE = 13134;
    public static final int VENTA_COMPROBANTE_INEXISTENTE = 13135;
    public static final int VENTA_COMPROBANTE_APLICADO_INEXISTENTE = 13136;
    public static final int VENTA_ITEM_CONCEPTO_INEXISTENTE = 13137;
    public static final int VENTA_TIPO_ITEM_INEXISTENTE = 13138;
    public static final int VIAJE_INEXISTENTE = 13139;
    public static final int VIAJE_REMITO_INEXISTENTE = 13140;
    public static final int VIAJE_TARIFA_INEXISTENTE = 13141;
    public static final int VIAJE_TIPO_INEXISTENTE = 13142;
    public static final int VIAJE_TIPO_CARGA_INEXISTENTE = 13143;
    public static final int VIAJE_TRAMO_INEXISTENTE = 13144;
    public static final int VIAJE_UNIDAD_NEGOCIO_INEXISTENTE = 13145;
    public static final int ZONA_INEXISTENTE = 13146;
}
