//Paquete al que pertenece el controlador
package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.AfipCaea;
import ar.com.draimo.jitws.service.AfipCaeaService;
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
 * Clase AfipCaea Controller
 *
 * @author blas
 */
@RestController
public class AfipCaeaController {

    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/afipcaea";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/afipcaea";

    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;

    //Crea una instancia del servicio
    @Autowired
    AfipCaeaService elementoService;

    //Crea una instancia del controlador de fecha
    @Autowired
    FechaController fechaController;

    //Obtiene el siguiente id
    @GetMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }

    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public List<AfipCaea> listar() {
        return elementoService.listar();
    }

    //Obtiene un listado de anios (actual+15)
    @GetMapping(value = URL + "/listarAnios")
    @ResponseBody
    public List<Short> listarAnios() {
        return fechaController.listarAnios();
    }

    //Obtiene un registro por empresa, anio, mes y quincena
    @GetMapping(value = URL + "/obtenerPorEmpresaYPeriodoOrden/{idEmpresa}/{anio}/{idMes}/{idQuincena}")
    @ResponseBody
    public AfipCaea obtenerPorEmpresaYPeriodoOrden(@PathVariable int idEmpresa,
            @PathVariable short anio, @PathVariable int idMes, @PathVariable int idQuincena) {
        return elementoService.obtenerPorEmpresaYPeriodoOrden(idEmpresa, anio, idMes, idQuincena);
    }

    //Obtiene un listado por empresa y anio
    @GetMapping(value = URL + "/listarPorEmpresaYAnio/{idEmpresa}/{anio}")
    @ResponseBody
    public List<AfipCaea> listarPorEmpresaYAnio(@PathVariable int idEmpresa, @PathVariable short anio) {
        return elementoService.listarPorEmpresaYAnio(idEmpresa, anio);
    }

    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody AfipCaea elemento) {
        try {
            AfipCaea a = elementoService.agregar(elemento);
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
    public ResponseEntity<?> actualizar(@RequestBody AfipCaea elemento) {
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
    @DeleteMapping(value = URL + "/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        try {
            elementoService.eliminar(id);
            //Retorna mensaje de eliminado con exito
            return MensajeRespuesta.eliminado();
        } catch (Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }

}