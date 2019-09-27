package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.InsumoProducto;
import ar.com.draimo.jitws.service.InsumoProductoService;
import java.io.IOException;
import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase InsumoProducto Controller
 *
 * @author blas
 */
@RestController
public class InsumoProductoController {

    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/insumoproducto";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/insumoproducto";

    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;

    //Crea una instancia del servicio
    @Autowired
    InsumoProductoService elementoService;

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
    
    //Obtiene una lista de Rubro y Marca
    @GetMapping(value = URL + "/listarPorRubroYMarca/{idRubro}/{idMarca}")
    @ResponseBody
    public Object listarPorRubroYMarca(@PathVariable int idRubro, @PathVariable int idMarca) throws IOException {
        return elementoService.listarPorRubroYMarca(idRubro, idMarca);
    }

    //Obtiene una lista por nombre
    @GetMapping(value = URL + "/listarPorAlias/{alias}")
    @ResponseBody
    public Object listarPorAlias(@PathVariable String alias) throws IOException {
        return elementoService.listarPorAlias(alias);
    }

    //Obtiene una lista de combustibles
    @GetMapping(value = URL + "/listarCombustibles")
    @ResponseBody
    public Object listarCombustibles() throws IOException {
        return elementoService.listarCombustibles();
    }
    
    //Obtiene una lista de insumos
    @GetMapping(value = URL + "/listarInsumos")
    @ResponseBody
    public Object listarInsumos() throws IOException {
        return elementoService.listarInsumos();
    }
    
    //Obtiene precio unitario por insumo
    @GetMapping(value = URL + "/obtenerPrecioUnitario/{idInsumoProducto}")
    @ResponseBody
    public BigDecimal obtenerPrecioUnitario(@PathVariable int idInsumoProducto) {
        return elementoService.obtenerPrecioUnitario(idInsumoProducto);
    }

    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody InsumoProducto elemento) {
        try {
            int a = elementoService.agregar(elemento);
            //Establece el alias despues de agregar
            elementoService.establecerAlias(a, elemento);
            //Envia la nueva lista a los usuarios subscriptos
            ////template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de agregado con exito
            return MensajeRespuesta.agregado(a);
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
    public ResponseEntity<?> actualizar(@RequestBody InsumoProducto elemento) {
        try {
            //Actualiza el registro
            elementoService.actualizar(elemento);
            //Envia la nueva lista a los usuarios subscripto
            //template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de actualizado con exito
            return MensajeRespuesta.actualizado();
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch (JpaObjectRetrievalFailureException jorfe) {
            //Retorna mensaje de dato Inexistente
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
