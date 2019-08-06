package ar.com.draimo.jitws.exception;

/*
 * Clase LongitudError:
 * Define nombres para las longitudes
 * @author blas
 */

public class LongitudError {

    public static final String ABREVIATURA_LONGITUD = "abreviatura";
    public static final String ALIAS_LONGITUD = "alias";
    public static final String ALIAS_CBU_LONGITUD = "aliasCBU";
    public static final String APELLIDO_LONGITUD = "apellido";
    public static final String CAE_LONGITUD = "CAE";
    public static final String CAE_ESTADO_LONGITUD = "CAEEstado";
    public static final String CAI_LONGITUD = "cai";
    public static final String CBU_LONGITUD = "cbu";
    public static final String CHEQUE_CARTERA_LONGITUD = "chequeCartera";
    public static final String CODIGO_LONGITUD = "codigoA";
    public static final String CODIGO_AFIP_LONGITUD = "codigoAfip";
    public static final String CODIGO_AREA_LONGITUD = "codigoArea";
    public static final String CODIGO_AREA_ALT_LONGITUD = "codigoAreaAlt";
    public static final String CODIGO_POSTAL_LONGITUD = "codigoPostal";
    public static final String COMPROBANTE_APLICADO_LONGITUD = "comprobanteAplicado";
    public static final String CORREO_ELECTRONICO_LONGITUD = "correoelectronico";
    public static final String CUIL_LONGITUD = "cuil";
    public static final String CUIT_LONGITUD = "cuit";
    public static final String DESCRIPCION_LONGITUD = "descripcion";
    public static final String DESDE_LONGITUD = "desde";
    public static final String DESCRIPCION_CARGA_LONGITUD = "descripcionCarga";
    public static final String DOCUMENTO_LONGITUD = "documento";
    public static final String DOMICILIO_LONGITUD = "domicilio";
    public static final String DOMICILIO_ALT_LONGITUD = "domicilioAlt";
    public static final String DOMINIO_LONGITUD = "dominio";
    public static final String FOLIO_LIBRO_SUELDOS_LONGITUD = "folioLibroSueldos";
    public static final String HASTA_LONGITUD = "hasta";
    public static final String LETRA_LONGITUD = "letra";
    public static final String MENSAJE_LONGITUD = "mensaje";
    public static final String MODELO_LONGITUD = "modelo";
    public static final String NOMBRE_LONGITUD = "nombre";
    public static final String NOMBRE_COMPLETO_LONGITUD = "nombreCompleto";
    public static final String NOMBRE_FANTASIA_LONGITUD = "nombreFantasia";
    public static final String NOTA_EMISION_COMPROBANTE_LONGITUD = "notaEmisionComprobante";
    public static final String NOTA_IMPRESION_COMPROBANTE_LONGITUD = "notaImpresionComprobante";
    public static final String NOTA_IMPRESION_COMPROBANTE_GRAL_1_LONGITUD = "NotaImpresionComprobanteGral1";
    public static final String NOTA_IMPRESION_COMPROBANTE_GRAL_2_LONGITUD = "NotaImpresionComprobanteGral2";
    public static final String NOTA_IMPRESION_ORDEN_PAGO_LONGITUD = "notaImpresionOrdenPago";
    public static final String NOTA_IMPRESION_REMITO_LONGITUD = "notaImpresionRemito";
    public static final String NOTA_INGRESAR_COMPROBANTE_LONGITUD = "notaIngresarComprobante";
    public static final String NUMERO_LONGITUD = "numero";
    public static final String NUMERO_CAEA_LONGITUD = "numeroCAEA";
    public static final String NUMERO_CBU_LONGITUD = "numeroCBU";
    public static final String NUMERO_CHASIS_LONGITUD = "numeroChasis";
    public static final String NUMERO_COMPROBANTE_LONGITUD = "numeroComprobante";
    public static final String NUMERO_CUENTA_LONGITUD = "numeroCuenta";
    public static final String NUMERO_DOCUMENTO_LONGITUD = "numeroDocumento";
    public static final String NUMERO_DOCUMENTACION_LONGITUD = "numeroDocumentacion";
    public static final String NUMERO_IIBB_LONGITUD = "numeroIIBB";
    public static final String NUMERO_INTERNO_LONGITUD = "numeroInterno";
    public static final String NUMERO_LIQUIDACION_LONGITUD = "numeroLiquidacion";
    public static final String NUMERO_MOTOR_LONGITUD = "numeroMotor";
    public static final String NUMERO_POLIZA_SEGURO_LONGITUD = "numeroPolizaSeguro";
    public static final String NUMERO_POLIZA_LONGITUD = "numeroPoliza";
    public static final String NUMERO_REMITO_LONGITUD = "numeroRemito";
    public static final String NUMERO_RUTA_LONGITUD = "numeroRuta";
    public static final String OBSERVACION_CHOFER_LONGITUD = "observacionChofer";
    public static final String OBSERVACION_VEHICULO_LONGITUD = "observacionVehiculo";
    public static final String OBSERVACION_VEHICULO_REMOLQUE_LONGITUD = "observacionVehiculoRemolque";
    public static final String OBSERVACIONES_LONGITUD = "observaciones";
    public static final String OBSERVACIONES_ANULADO_LONGITUD = "observacionesAnulado";
    public static final String PASSWORD_LONGITUD = "password";
    public static final String PUNTO_VENTA_LONGITUD = "puntoVenta";
    public static final String RAZON_SOCIAL_LONGITUD = "razonSocial";
    public static final String RUTA_ALTERNATIVA_LONGITUD = "rutaAlternativa";
    public static final String SIMBOLO_LONGITUD = "simbolo";
    public static final String SITIO_WEB_LONGITUD = "sitioWeb";
    public static final String SOLICITADO_POR_LONGITUD = "solicitadoPor";
    public static final String STOCK_MINIMO_LONGITUD = "stockMinimo";
    public static final String TABLA_LONGITUD = "tabla";
    public static final String TALLE_CAMISA_LONGITUD = "talleCamisa";
    public static final String TALLE_CALZADO_LONGITUD = "talleCalzado";
    public static final String TALLE_PANTALON_LONGITUD = "tallePantalon";
    public static final String TELEFONO_LONGITUD = "telefono";
    public static final String TELEFONO_ALT_LONGITUD = "telefonoAlt";
    public static final String TELEFONO_CONTACTO_LONGITUD = "telefonoContacto";
    public static final String TELEFONO_FIJO_LONGITUD = "telefonoFijo";
    public static final String TELEFONO_MOVIL_LONGITUD = "telefonoMovil";
    public static final String TELEFONO_MOVIL_EMPRESA_LONGITUD = "telefonoMovilEmpresa";
    public static final String TELEFONO_MOVIL_OBSERVACION_LONGITUD = "telefonoMovilObservacion";
    public static final String TIPO_LONGITUD = "tipo";
    public static final String TITULAR_LONGITUD = "titular";
    public static final String ULTIMO_NUMERO_LONGITUD = "ultimoNumero";
    public static final String URL_REAL_LONGITUD = "urlReal";
    public static final String URL_PRUEBA_LONGITUD = "urlPrueba";
    public static final String USERNAME_LONGITUD = "username";
    public static final String VENTA_COMPROBANTE_APLICADO_LONGITUD = "VentaComprobanteAplicado";
    
}
