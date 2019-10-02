//Paquete al que pertenece el controlador
package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.AfipTipoBeneficioDeduccion;
import ar.com.draimo.jitws.service.AfipTipoBeneficioDeduccionService;
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
 * Clase AfipTipoBeneficioDeduccion Controller
 *
 * @author blas
 */
@RestController
public class AfipTipoBeneficioDeduccionController {

    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/afiptipobeneficiodeduccion";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/afiptipobeneficiodeduccion";

    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;

    //Crea una instancia del servicio
    @Autowired
    AfipTipoBeneficioDeduccionService elementoService;

    //Obtiene el siguiente id
    @GetMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }

    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public List<AfipTipoBeneficioDeduccion> listar() {
        return elementoService.listar();
    }

    //Obtiene una lista por filtros(anio, id de tipo de beneficio y id del mes)
    @GetMapping(value = URL + "/listarPorFiltros/{anio}/{idTipoBeneficio}/{idMes}")
    @ResponseBody
    public List<AfipTipoBeneficioDeduccion> listarPorFiltros(@PathVariable short anio, @PathVariable int idTipoBeneficio, @PathVariable int idMes) throws Exception {
        return elementoService.listarPorFiltros(anio, idTipoBeneficio, idMes);
    }

    //Obtiene una lista por anio y tipo de beneficio
    @GetMapping(value = URL + "/listarPorAnioYTipoBeneficio/{anio}/{idTipoBeneficio}")
    @ResponseBody
    public List<AfipTipoBeneficioDeduccion> listarPorAnioYTipoBeneficio(@PathVariable short anio, @PathVariable int idTipoBeneficio) {
        return elementoService.listarPorAnioYBeneficio(anio, idTipoBeneficio);
    }

    //Obtiene una lista por id de Deduccion personal
    @GetMapping(value = URL + "/listarPorDeduccionPersonal/{idDeduccion}")
    @ResponseBody
    public List<AfipTipoBeneficioDeduccion> listarPorDeduccionPersonal(@PathVariable int idDeduccion) {
        return elementoService.listarPorDeduccionPersonal(idDeduccion);
    }

    //Obtiene una lista por id de TipoBeneficio
    @GetMapping(value = URL + "/listarPorTipoBeneficio/{idTipoBeneficio}")
    @ResponseBody
    public List<AfipTipoBeneficioDeduccion> listarPorTipoBeneficio(@PathVariable int idTipoBeneficio) {
        return elementoService.listarPorBeneficio(idTipoBeneficio);
    }

    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody AfipTipoBeneficioDeduccion elemento) {
        try {
            AfipTipoBeneficioDeduccion a = elementoService.agregar(elemento);
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
    public ResponseEntity<?> actualizar(@RequestBody AfipTipoBeneficioDeduccion elemento) {
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