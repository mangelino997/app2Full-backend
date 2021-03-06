//Paquete al que pertenece el controlador
package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.AfipWS;
import ar.com.draimo.jitws.service.AfipWSService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase afipws Controller
 *
 * @author blas
 */
@RestController
public class AfipWSController {

    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/afipws";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/afipws";

    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;

    //Crea una instancia del servicio
    @Autowired
    AfipWSService elementoService;

    //Obtiene el siguiente id
    @GetMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }

    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public List<AfipWS> listar() {
        return elementoService.listar();
    }

    //Obtiene una lista por nombre
    @GetMapping(value = URL + "/listarPorNombre/{nombre}")
    @ResponseBody
    public List<AfipWS> listarPorNombre(@PathVariable String nombre) {
        return elementoService.listarPorNombre(nombre);
    }

    //Solicita FECAE anticipado(anio, mes y quincena cotrola la unicidad)
    @GetMapping(value = URL + "/solicitarFECAEA/{anio}/{mes}/{quincena}")
    @ResponseBody
    public void solicitarFECAEA(@PathVariable int anio, @PathVariable int mes, @PathVariable short quincena) {
        elementoService.solicitarFECAEA(anio, mes, quincena);
    }

    //Consulta FECAE anticipado (obtiene por anio, mes y quincena)
    @GetMapping(value = URL + "/consultarFECAEA/{anio}/{mes}/{quincena}")
    @ResponseBody
    public void consultarFECAEA(@PathVariable int anio, @PathVariable int mes, @PathVariable short quincena) {
        elementoService.consultarFECAEA(anio, mes, quincena);
    }

    //Consulta estado de infraestructura
    @GetMapping(value = URL + "/dummyFE")
    @ResponseBody
    public void dummyFE() {
        elementoService.dummyFE();
    }

    //Consulta vencimiento del certificado digital
    @GetMapping(value = URL + "/archivoCertificadoVtoFE")
    @ResponseBody
    public void archivoCertificadoVtoFE() {
        elementoService.archivoCertificadoVtoFE();
    }

    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody AfipWS elemento) {
        try {
            AfipWS a = elementoService.agregar(elemento);
            //Envia la nueva lista a los usuarios subscriptos
            //template.convertAndSend(TOPIC + "/lista", elementoService.listar());
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
    public ResponseEntity<?> actualizar(@RequestBody AfipWS elemento) {
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
    @DeleteMapping(value = URL + "/{elemento}")
    public ResponseEntity<?> eliminar(@PathVariable int elemento) {
        try {
            elementoService.eliminar(elemento);
            //Retorna mensaje de eliminado con exito
            return MensajeRespuesta.eliminado();
        } catch (Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }

}