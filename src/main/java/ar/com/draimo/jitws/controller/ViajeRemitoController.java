package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.dto.ViajeRemitoDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.ViajeRemito;
import ar.com.draimo.jitws.service.ViajeRemitoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase ViajeRemito Controller
 * @author blas
 */

@RestController
public class ViajeRemitoController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/viajeremito";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/viajeremito";
    
    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;
    
    //Crea una instancia del servicio
    @Autowired
    ViajeRemitoService elementoService;
    
    //Obtiene el siguiente id
    @GetMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public List<ViajeRemito> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por alias
    @GetMapping(value = URL + "/listarPorAlias/{alias}")
    @ResponseBody
    public List<ViajeRemito> listarPorAlias(@PathVariable String alias) {
        return elementoService.listarPorAlias(alias);
    }
    
    //Obtiene una lista por numero
    @GetMapping(value = URL + "/listarPorNumero/{numero}")
    @ResponseBody
    public List<ViajeRemito> listarPorNumero(@PathVariable int numero) {
        return elementoService.listarPorNumero(numero);
    }
    
    //Obtiene una lista de remitos pendientes por sucursal
    @GetMapping(value = URL + "/listarPendientesPorSucursal/{idSucursal}")
    @ResponseBody
    public List<ViajeRemito> listarPendientesPorSucursal(@PathVariable int idSucursal) {
        return elementoService.listarPendientesPorSucursal(idSucursal);
    }
    
    //Obtiene una lista de remitos pendientes por filtro
    @GetMapping(value = URL + "/listarPendientesPorFiltro/{idSucursal}/{idSucursalDestino}/{numeroCamion}")
    @ResponseBody
    public List<ViajeRemito> listarPendientesPorFiltro(@PathVariable int idSucursal, 
            @PathVariable int idSucursalDestino, @PathVariable short numeroCamion) {
        return elementoService.listarPendientesPorFiltro(idSucursal, idSucursalDestino, numeroCamion);
    }
    
    //Obtiene una lista de remitos asignados por filtro
    @GetMapping(value = URL + "/listarAsignadosPorFiltro/{idSucursal}/{idSucursalDestino}"
            + "/{numeroCamion}/{idViajePropioTramo}")
    @ResponseBody
    public List<ViajeRemito> listarAsignadosPorFiltro(@PathVariable int idSucursal, 
            @PathVariable int idSucursalDestino, @PathVariable short numeroCamion, 
            @PathVariable int idViajePropioTramo) {
        return elementoService.listarAsignadosPorFiltro(idSucursal, idSucursalDestino, 
                numeroCamion, idViajePropioTramo);
    }
    
    //Obtiene una lista de remitos por filtro
    @PostMapping(value = URL + "/listarPorFiltros")
    public List<ViajeRemito> listarPorFiltros(@RequestBody ViajeRemitoDTO viajeRemitoDTO) {
        return elementoService.listarPorFiltros(viajeRemitoDTO);
    }
    
    //Obtiene una lista de remito por viaje propio o viaje tercero
    @GetMapping(value = URL + "/listarRemitos/{idViajeTramo}/{item}")
    @ResponseBody
    public List<ViajeRemito> listarRemitos(@PathVariable int idViajeTramo, @PathVariable int item) {
        return elementoService.listarRemitos(idViajeTramo, item);
    }
    
    //Obtiene un registro por puntoventa letra y numero
    @GetMapping(value = URL + "/obtener/{puntoVenta}/{letra}/{numero}")
    @ResponseBody
    public ViajeRemito obtener(int puntoVenta, String letra, int numero) {
        return elementoService.obtener(puntoVenta, letra, numero);
    }
    
    //Asigna remitos
    @PutMapping(value = URL + "/asignar")
    public ResponseEntity<?> asignar(@RequestBody List<ViajeRemito> elementos) {
        try {
            elementoService.asignar(elementos);
            //Envia la nueva lista a los usuarios subscripto
            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de actualizado con exito
            return MensajeRespuesta.asignado();
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch(ObjectOptimisticLockingFailureException oolfe) {
            //Retorna mensaje de transaccion no actualizada
            return MensajeRespuesta.transaccionNoActualizada();
        }catch(MessagingException e) {
            //Retorna codigo y mensaje de error de sicronizacion mediante socket
            return MensajeRespuesta.errorSincSocket();
        } catch(Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }
    
    //Quita remitos
    @PutMapping(value = URL + "/quitar")
    public ResponseEntity<?> quitar(@RequestBody List<ViajeRemito> elementos) {
        try {
            //Quita el registro
            elementoService.quitar(elementos);
            //Envia la nueva lista a los usuarios subscripto
            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de actualizado con exito
            return MensajeRespuesta.quitado();
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch(ObjectOptimisticLockingFailureException oolfe) {
            //Retorna mensaje de transaccion no actualizada
            return MensajeRespuesta.transaccionNoActualizada();
        }catch(MessagingException e) {
            //Retorna codigo y mensaje de error de sicronizacion mediante socket
            return MensajeRespuesta.errorSincSocket();
        } catch(Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }
    
    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody ViajeRemito elemento) {
        try {
            ViajeRemito a = elementoService.agregar(elemento);
            //Actualiza inmediatamente el registro para establecer el alias
            elementoService.establecerAlias(elemento);
            //Envia la nueva lista a los usuarios subscriptos
            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de agregado con exito
            return MensajeRespuesta.agregado(a.getId());
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch(MessagingException e) {
            //Retorna codigo y mensaje de error de sicronizacion mediante socket
            return MensajeRespuesta.errorSincSocket();
        } catch (Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }
    
    //Actualiza un registro
    @PutMapping(value = URL)
    public ResponseEntity<?> actualizar(@RequestBody ViajeRemito elemento) {
        try {
            //Actualiza el registro
            elementoService.actualizar(elemento);
            //Envia la nueva lista a los usuarios subscripto
            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de actualizado con exito
            return MensajeRespuesta.actualizado();
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch(ObjectOptimisticLockingFailureException oolfe) {
            //Retorna mensaje de transaccion no actualizada
            return MensajeRespuesta.transaccionNoActualizada();
        }catch(MessagingException e) {
            //Retorna codigo y mensaje de error de sicronizacion mediante socket
            return MensajeRespuesta.errorSincSocket();
        } catch(Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }
    
    //Elimina un registro
    @DeleteMapping(value = URL)
    public ResponseEntity<?> eliminar(@RequestBody ViajeRemito elemento) {
        try {
            elementoService.eliminar(elemento);
            //Retorna mensaje de eliminado con exito
            return MensajeRespuesta.eliminado();
        } catch(Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }
    
}
