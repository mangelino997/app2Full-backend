package ar.com.draimo.jitws.exception;

/*
 * Clase LongitudError:
 * Define nombres para las longitudes
 * @author blas
 */

public class InexistenciaError {

    public static final String AFIP_ACTIVIDAD_INEXISTENTE = "idAfipActividad";
    public static final String AFIP_ALICUOTA_IVA_INEXISTENTE = "idAfipAlicuotaIva";
    public static final String AFIP_COMPROBANTE_INEXISTENTE = "idAfipComprobante";
    public static final String AFIP_CONCEPTO_INEXISTENTE = "idAfipConcepto";
    public static final String AFIP_CONDICION_INEXISTENTE = "idAfipCondicion";
    public static final String AFIP_CONDICION_IVA_INEXISTENTE = "idAfipCondicionIva";
    public static final String AFIP_CONDICION_IVA_PROVEEDOR_INEXISTENTE = "idAfipCondicionIvaProveedor";
    public static final String AFIP_LOCALIDAD_INEXISTENTE = "idAfipLocalidad";
    public static final String AFIP_MOD_CONTRATACION_INEXISTENTE = "idAfipModContratacion";
    public static final String AFIP_SINIESTRADO_INEXISTENTE = "idAfipSiniestrado";
    public static final String AFIP_SITUACION_INEXISTENTE = "idAfipSituacion";
    public static final String AREA_INEXISTENTE = "idArea";
    public static final String BANCO_INEXISTENTE = "idBanco";
    public static final String BARRIO_INEXISTENTE = "idBarrio";
    public static final String BUG_IMAGEN_INEXISTENTE = "idBugImagen";
    public static final String CATEGORIA_INEXISTENTE = "idCategoria";
    public static final String CHEQUE_CARTERA_INEXISTENTE = "idChequeCartera";
    public static final String CHOFER_PROVEEDOR_INEXISTENTE = "idChoferProveedor";
    public static final String CLIENTE_INEXISTENTE = "idCliente";
    public static final String CLIENTE_DESTINATARIO_INEXISTENTE = "idClienteDestinatario";
    public static final String CLIENTE_DESTINATARIO_SUC_INEXISTENTE = "idClienteDestinatarioSuc";
    public static final String CLIENTE_GRUPO_INEXISTENTE = "idClienteGrupo";
    public static final String CLIENTE_REMITENTE_INEXISTENTE = "idClienteRemitente";
    public static final String COBRADOR_INEXISTENTE = "idCobrador";
    public static final String COMPANIA_SEGURO_INEXISTENTE = "idCompaniaSeguro";
    public static final String COMPANIA_SEGURO_POLIZA_INEXISTENTE = "idCompaniaSeguroPoliza";
    public static final String COMPRA_COMPROBANTE_INEXISTENTE = "idCompraComprobante";
    public static final String COMPRA_COMPROBANTE_PERCEPCION_INEXISTENTE = "idCompraComprobantePercepcion";
    public static final String CONDICION_COMPRA_INEXISTENTE = "idCondicionCompra";
    public static final String CONDICION_VENTA_INEXISTENTE = "idCondicionVenta";
    public static final String CONFIGURACION_VEHICULO_INEXISTENTE = "idConfiguracionVehiculo";
    public static final String CUENTA_BANCARIA_INEXISTENTE = "idCuentaBancaria";
    public static final String CUENTA_CONTABLE_INEXISTENTE = "idCuentaContable";
    public static final String CUENTA_GRUPO_INEXISTENTE = "idCuentaGrupo";
    public static final String DEPOSITO_INSUMO_PRODUCTO_INEXISTENTE = "idDepositoInsumoProducto";
    public static final String DESTINO_INEXISTENTE = "idDestino";
    public static final String EMPRESA_INEXISTENTE = "idEmpresa";
    public static final String EMPRESA_C_FISCAL_INEXISTENTE = "idEmpresaCFiscal";
    public static final String EMPRESA_EMISION_INEXISTENTE = "idEmpresaEmision";
    public static final String ESCALA_TARIFA_INEXISTENTE = "idEscalaTarifa";
    public static final String ESTADO_CIVIL_INEXISTENTE = "idEstadoCivil";
    public static final String FOTO_INEXISTENTE = "idFoto";
    public static final String GRUPO_CUENTA_CONTABLE_INEXISTENTE = "idGrupoCuentaContable";
    public static final String LOCALIDAD_INEXISTENTE = "idLocalidad";
    public static final String LOCALIDAD_NACIMIENTO_INEXISTENTE = "idLocalidadNacimiento";
    public static final String INSUMO_PRODUCTO_INEXISTENTE = "idInsumoProducto";
    public static final String MARCA_PRODUCTO_INEXISTENTE = "idMarcaProducto";
    public static final String MARCA_VEHICULO_INEXISTENTE = "idMarcaVehiculo";
    public static final String MES_INEXISTENTE = "idMes";
    public static final String MES_INICIO_INEXISTENTE = "idMesInicio";
    public static final String MODULO_INEXISTENTE = "idModulo";
    public static final String MONEDA_INEXISTENTE = "idMoneda";
    public static final String OBRA_SOCIAL_INEXISTENTE = "idObraSocial";
    public static final String OPCION_INEXISTENTE = "idOpcion";
    public static final String ORDEN_VENTA_INEXISTENTE = "idOrdenVenta";
    public static final String ORDEN_VENTA_TARIFA_INEXISTENTE = "idOrdenVentaTarifa";
    public static final String ORDEN_RECOLECCION_INEXISTENTE = "idOrdenRecoleccion";
    public static final String ORIGEN_INEXISTENTE = "idOrigen";
    public static final String PADRE_INEXISTENTE = "idPadre";
    public static final String PAIS_INEXISTENTE = "idPais";
    public static final String PDF_INEXISTENTE = "idPdf";
    public static final String PDF_ALTA_TEMPRANA_INEXISTENTE = "idPdfAltaTemprana";
    public static final String PDF_CEDULA_IDENT_INEXISTENTE = "idPdfCedulaIdent";
    public static final String PDF_DNI_INEXISTENTE = "idPdfDni";
    public static final String PDF_HAB_BROMAT_INEXISTENTE = "idPdfHabBromat";
    public static final String PDF_LIB_SANIDAD_INEXISTENTE = "idPdfLibSanidad";
    public static final String PDF_LIC_CONDUCIR_INEXISTENTE = "idPdfLicConducir";
    public static final String PDF_LINTI_INEXISTENTE = "idPdfLinti";
    public static final String PDF_TITULO_INEXISTENTE = "idPdfTitulo";
    public static final String PDF_VTO_INSP_TECNICA_INEXISTENTE = "idPdfVtoInspTecnica";
    public static final String PDF_VTO_RUTA_INEXISTENTE = "idPdfVtoRuta";
    public static final String PDF_SENASA_INEXISTENTE = "idPdfVtoSenasa";
    public static final String PERSONAL_INEXISTENTE = "idPersonal";
    public static final String PESTANIA_INEXISTENTE = "idPestania";
    public static final String PLAN_DE_CUENTA_INEXISTENTE = "idPlandeCuenta";
    public static final String PROVEEDOR_INEXISTENTE = "idProveedor";
    public static final String PROVINCIA_INEXISTENTE = "idProvincia";
    public static final String REGISTRO_C_FISCAL_INEXISTENTE = "idRegistroCFiscal";
    public static final String REPARTO_INEXISTENTE = "idReparto";
    public static final String RESUMEN_CLIENTE_INEXISTENTE = "idResumenCliente";
    public static final String RETIRO_DEPOSITO_INEXISTENTE = "idRetiroDeposito";
    public static final String ROL_INEXISTENTE = "idRol";
    public static final String ROL_SECUNDARIO_INEXISTENTE = "idRolSecundario";
    public static final String RUBRO_INEXISTENTE = "idRubro";
    public static final String RUBRO_PRODUCTO_INEXISTENTE = "idRubroProducto";
    public static final String SEGUIMIENTO_INEXISTENTE = "idSeguimiento";
    public static final String SEGUIMIENTO_ESTADO_INEXISTENTE = "idSeguimientoEstado";
    public static final String SEGUIMIENTO_SITUACION_INEXISTENTE = "idSeguimientoSituacion";
    public static final String SEGURIDAD_SOCIAL_INEXISTENTE = "idSeguridadSocial";
    public static final String SEXO_INEXISTENTE = "idSexo";
    public static final String SINDICATO_INEXISTENTE = "idSindicato";
    public static final String SITUACION_CLIENTE_INEXISTENTE = "idSituacionCliente";
    public static final String SOPORTE_ESTADO_INEXISTENTE = "idSoporteEstado";
    public static final String SUBMODULO_INEXISTENTE = "idSubmodulo";
    public static final String SUBOPCION_INEXISTENTE = "idSubopcion";
    public static final String SUCURSAL_INEXISTENTE = "idSucursal";
    public static final String SUCURSAL_BANCO_INEXISTENTE = "idSucursalBanco";
    public static final String SUCURSAL_CLIENTE_DES_INEXISTENTE = "idSucursalClienteDes";
    public static final String SUCURSAL_CLIENTE_REM_INEXISTENTE = "idSucursalClienteRem";
    public static final String SUCURSAL_DESTINO_INEXISTENTE = "idSucursalDestino";
    public static final String SUCURSAL_INGRESO_INEXISTENTE = "idSucursalIngreso";
    public static final String SUCURSAL_LUGAR_PAGO_INEXISTENTE = "idSucursalLugarPago";
    public static final String TALONARIO_RECIBO_LOTE_INEXISTENTE = "idTalonarioReciboLote";
    public static final String TIPO_CHEQUERA_INEXISTENTE = "idTipoChequera";
    public static final String TIPO_COMPROBANTE_INEXISTENTE = "idTipoComprobante";
    public static final String TIPO_CONTACTO_INEXISTENTE = "idTipoContacto";
    public static final String TIPO_CUENTA_BANCARIA_INEXISTENTE = "idTipoCuentaBancaria";
    public static final String TIPO_CUENTA_CONTABLE_INEXISTENTE = "idTipoCuentaContable";
    public static final String TIPO_DOCUMENTO_INEXISTENTE = "idTipoDocumento";
    public static final String TIPO_PERCEPCION_INEXISTENTE = "idTipoPercepcion";
    public static final String TIPO_PROVEEDOR_INEXISTENTE = "idTipoProveedor";
    public static final String TIPO_TARIFA_INEXISTENTE = "idTipoTarifa";
    public static final String TIPO_VEHICULO_INEXISTENTE = "idTipoVehiculo";
    public static final String TRAMO_INEXISTENTE = "idTramo";
    public static final String UNIDAD_MEDIDA_INEXISTENTE = "idUnidadMedida";
    public static final String USUARIO_INEXISTENTE = "idUsuario";
    public static final String USUARIO_ALTA_INEXISTENTE = "idUsuarioAlta";
    public static final String USUARIO_BAJA_INEXISTENTE = "idUsuarioBaja";
    public static final String USUARIO_CHOFER_AUTORIZADO_INEXISTENTE = "idUsuarioChoferAutorizado";
    public static final String USUARIO_DOCUMENTACION_INEXISTENTE = "idUsuarioDocumentacion";
    public static final String USUARIO_LIQUIDACION_INEXISTENTE = "idUsuarioLiquidacion";
    public static final String USUARIO_MOD_INEXISTENTE = "idUsuarioMod";
    public static final String USUARIO_MOD_CURSO_INEXISTENTE = "idUsuarioModCurso";
    public static final String USUARIO_MOD_CURSO_CP_INEXISTENTE = "idUsuarioModCursoCP";
    public static final String USUARIO_MOD_LC_INEXISTENTE = "idUsuarioModLC";
    public static final String USUARIO_MOD_LINTI_INEXISTENTE = "idUsuarioModLINTI";
    public static final String USUARIO_MOD_LS_INEXISTENTE = "idUsuarioModLS";
    public static final String VEHICULO_INEXISTENTE = "idVehiculo";
    public static final String VEHICULO_AUTORIZADO_INEXISTENTE = "idVehiculoAutorizado";
    public static final String VEHICULO_PROVEEDOR_INEXISTENTE = "idVehiculoProveedor";
    public static final String VEHICULO_REM_AUTORIZADO_INEXISTENTE = "idVehiculoRemAutorizado";
    public static final String VEHICULO_REMOLQUE_INEXISTENTE = "idVehiculoRemolque";
    public static final String VEHICULO_REMOLQUE_PROVEEDOR_INEXISTENTE = "idVehiculoRemolqueProveedor";
    public static final String VENDEDOR_INEXISTENTE = "idVendedor";
    public static final String VENTA_COMPROBANTE_INEXISTENTE = "idVentaComprobante";
    public static final String VENTA_COMPROBANTE_APLICADO_INEXISTENTE = "idVentaComprobanteAplicado";
    public static final String VENTA_ITEM_CONCEPTO_INEXISTENTE = "idVentaItemConcepto";
    public static final String VENTA_TIPO_ITEM_INEXISTENTE = "idVentaTipoItem";
    public static final String VIAJE_INEXISTENTE = "idViaje";
    public static final String VIAJE_REMITO_INEXISTENTE = "idViajeRemito";
    public static final String VIAJE_TARIFA_INEXISTENTE = "idViajeTarifa";
    public static final String VIAJE_TIPO_INEXISTENTE = "idViajeTipo";
    public static final String VIAJE_TIPO_CARGA_INEXISTENTE = "idViajeTipoCarga";
    public static final String VIAJE_TRAMO_INEXISTENTE = "idViajeTramo";
    public static final String VIAJE_UNIDAD_NEGOCIO_INEXISTENTE = "idViajeUnidadNegocio";
    public static final String ZONA_INEXISTENTE = "idZona";
    
}