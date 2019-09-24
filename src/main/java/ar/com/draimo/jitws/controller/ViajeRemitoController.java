package ar.com.draimo.jitws.controller;
import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.dto.ViajeRemitoDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.ViajeRemito;
import ar.com.draimo.jitws.service.ViajeRemitoService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase ViajeRemito Controller
 *
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
    public Object listar() throws IOException {
        return elementoService.listar();
    }

    //Obtiene la lista por remitos disponibles
    @GetMapping(value = URL + "/listarRemitosDisponibles")
    @ResponseBody
    public Object listarRemitosDisponibles() throws IOException {
        return elementoService.listarRemitosDisponibles();
    }

    //Obtiene una lista por alias
    @GetMapping(value = URL + "/listarPorAlias/{alias}")
    @ResponseBody
    public Object listarPorAlias(@PathVariable String alias) throws IOException {
        return elementoService.listarPorAlias(alias);
    }

    //Obtiene una lista por numero
    @GetMapping(value = URL + "/listarPorNumero/{numero}")
    @ResponseBody
    public Object listarPorNumero(@PathVariable int numero) throws IOException {
        return elementoService.listarPorNumero(numero);
    }

    //Obtiene una lista de remitos no pendientes por viajeTramo
    @GetMapping(value = URL + "/listarAsignadosPorViajeTramo/{idViajeTramo}")
    @ResponseBody
    public Object listarAsignadosPorViajeTramo(@PathVariable int idViajeTramo) throws IOException {
        return elementoService.listarAsignadosPorViajeTramo(idViajeTramo);
    }

    //Obtiene una lista de remitos pendientes por sucursal
    @GetMapping(value = URL + "/listarPendientesPorSucursal/{idSucursal}")
    @ResponseBody
    public Object listarPendientesPorSucursal(@PathVariable int idSucursal) throws IOException {
        return elementoService.listarPendientesPorSucursal(idSucursal);
    }

    //Obtiene una lista de remitos pendientes por filtro
    @GetMapping(value = URL + "/listarPendientesPorFiltro/{idSucursal}/{idSucursalDestino}/{numeroCamion}")
    @ResponseBody
    public Object listarPendientesPorFiltro(@PathVariable int idSucursal,
            @PathVariable int idSucursalDestino, @PathVariable short numeroCamion) throws IOException {
        return elementoService.listarPendientesPorFiltro(idSucursal, idSucursalDestino, numeroCamion);
    }
    
    //Obtiene una lista de remitos asignados por filtro
    @GetMapping(value = URL + "/listarAsignadosPorFiltro/{idSucursal}/{idSucursalDestino}/{numeroCamion}")
    @ResponseBody
    public Object listarAsignadosPorFiltro(@PathVariable int idSucursal,
            @PathVariable int idSucursalDestino, @PathVariable short numeroCamion) throws IOException {
        return elementoService.listarAsignadosPorFiltro(idSucursal, idSucursalDestino, numeroCamion);
    }

    //Obtiene una lista de remitos por filtro
    @PostMapping(value = URL + "/listarPorFiltros")
    public Object listarPorFiltros(@RequestBody ViajeRemitoDTO viajeRemitoDTO) throws IOException {
        return elementoService.listarPorFiltros(viajeRemitoDTO);
    }

    //Obtiene un registro por puntoventa letra y numero
    @GetMapping(value = URL + "/obtener/{puntoVenta}/{letra}/{numero}")
    @ResponseBody
    public Object obtener(@PathVariable int puntoVenta,@PathVariable String letra,
            @PathVariable int numero) throws IOException {
        return elementoService.obtener(puntoVenta, letra, numero);
    }
    
    //Obtiene la lista de letras
    @GetMapping(value = URL + "/listarLetras")
    @ResponseBody
    public List<String> listarLetras() throws IOException {
        return elementoService.listarLetras();
    }

    //Asigna remitos
    @PutMapping(value = URL + "/asignar")
    public ResponseEntity<?> asignar(@RequestPart("elementos") String elementos, @RequestPart("viajeTramo") String idViajeTramo) {
        try {
            elementoService.asignar(elementos, idViajeTramo);
            //Envia la nueva lista a los usuarios subscripto
//            //stemplate.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de actualizado con exito
            return MensajeRespuesta.asignado();
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch (ObjectOptimisticLockingFailureException oolfe) {
            //Retorna mensaje de transaccion no actualizada
            return MensajeRespuesta.transaccionNoActualizada();
        } catch (MessagingException e) {
            //Retorna codigo y mensaje de error de sicronizacion mediante socket
            return MensajeRespuesta.errorSincSocket();
        } catch (Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }

    //Quita remitos
    @PutMapping(value = URL + "/quitar")
    public ResponseEntity<?> quitar(@RequestPart("elementos") String elementos, @RequestPart("viajeTramo") String idViajeTramo) {
        try {
            //Quita el registro
            elementoService.quitar(elementos, idViajeTramo);
            //Envia la nueva lista a los usuarios subscripto
//            //stemplate.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de actualizado con exito
            return MensajeRespuesta.quitado();
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch (ObjectOptimisticLockingFailureException oolfe) {
            //Retorna mensaje de transaccion no actualizada
            return MensajeRespuesta.transaccionNoActualizada();
        } catch (MessagingException e) {
            //Retorna codigo y mensaje de error de sicronizacion mediante socket
            return MensajeRespuesta.errorSincSocket();
        } catch (Exception e) {
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
            ////stemplate.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de agregado con exito
            return MensajeRespuesta.agregado(a.getId());
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch (MessagingException e) {
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
            //stemplate.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de actualizado con exito
            return MensajeRespuesta.actualizado();
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch (JpaObjectRetrievalFailureException jorfe) {
            //Retorna mensaje de dato inexistente
            return MensajeRespuesta.datoInexistente("a", jorfe.getMessage());
        } catch (ObjectOptimisticLockingFailureException oolfe) {
            //Retorna mensaje de transaccion no actualizada
            return MensajeRespuesta.transaccionNoActualizada();
        } catch (MessagingException e) {
            //Retorna codigo y mensaje de error de sicronizacion mediante socket
            return MensajeRespuesta.errorSincSocket();
        } catch (Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }

    //Elimina un registro
    @DeleteMapping(value = URL + "/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        try {
            elementoService.eliminar(id);
            //Retorna mensaje de eliminado con exito
            return MensajeRespuesta.eliminado();
        }catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch (Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }

}