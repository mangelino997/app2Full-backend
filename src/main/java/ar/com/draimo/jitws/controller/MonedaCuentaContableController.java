//Paquete al que pertenece el controlador
package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.dto.InitMonedaCuentaContableDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.MonedaCuentaContable;
import ar.com.draimo.jitws.service.MonedaCuentaContableService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase monedaCuentaContable Controller
 *
 * @author blas
 */
@RestController
public class MonedaCuentaContableController {

    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/monedacuentacontable";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/monedacuentacontable";

    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;

    //Crea una instancia del servicio
    @Autowired
    MonedaCuentaContableService elementoService;

    //Obtiene listas necesarias para inicializar el componente (front)
    @GetMapping(value = URL + "/inicializar/{idEmpresa}/{idRol}/{idSubopcion}")
    @ResponseBody
    public Object inicializar(@PathVariable int idEmpresa, @PathVariable int idRol, @PathVariable int idSubopcion) throws IOException {
        return elementoService.inicializar(idEmpresa, idRol, idSubopcion);
    }

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

    //Obtiene una lista por moneda
    @GetMapping(value = URL + "/listarPorMoneda/{id}")
    @ResponseBody
    public List<MonedaCuentaContable> listarPorMoneda(@PathVariable int id) {
        return elementoService.listarPorMoneda(id);
    }

    //Obtiene una lista por nombre de moneda y idEmpresa
    @GetMapping(value = URL + "/listarPorNombreMoneda/{nombre}/{idEmpresa}")
    @ResponseBody
    public Object listarPorNombreMoneda(@PathVariable String nombre, @PathVariable int idEmpresa) throws IOException {
        return elementoService.listarPorNombreMoneda(nombre, idEmpresa);
    }

    //Obtiene una lista por empresa
    @GetMapping(value = URL + "/listarPorEmpresa/{idEmpresa}")
    @ResponseBody
    public Object listarPorEmpresa(@PathVariable int idEmpresa) throws IOException {
        return elementoService.listarPorEmpresa(idEmpresa);
    }

    //Obtiene un registro por moneda y empresa
    @GetMapping(value = URL + "/obtenerPorMonedaYEmpresa/{idMoneda}/{idEmpresa}")
    @ResponseBody
    public Object obtenerPorMonedaYEmpresa(@PathVariable int idMoneda, @PathVariable int idEmpresa) throws IOException {
        return elementoService.obtenerPorMonedaYEmpresa(idMoneda, idEmpresa);
    }

    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody MonedaCuentaContable elemento) {
        try {
            MonedaCuentaContable a = elementoService.agregar(elemento);
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
    public ResponseEntity<?> actualizar(@RequestBody MonedaCuentaContable elemento) {
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
        } catch (Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }

}