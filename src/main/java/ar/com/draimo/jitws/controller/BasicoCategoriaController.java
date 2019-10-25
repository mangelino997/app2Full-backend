//Paquete al que pertenece el controlador
package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.BasicoCategoria;
import ar.com.draimo.jitws.service.BasicoCategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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
 * Clase BasicoCategoria Controller
 *
 * @author blas
 */
@RestController
public class BasicoCategoriaController {

    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/basicocategoria";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/basicocategoria";

    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;

    //Crea una instancia del servicio
    @Autowired
    BasicoCategoriaService elementoService;

    //Obtiene el siguiente id
    @GetMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }

    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public List<BasicoCategoria> listar() {
        return elementoService.listar();
    }

    //Obtiene una lista por nombre el nombre de categoria
    @GetMapping(value = URL + "/listarPorCategoriaNombre/{nombre}")
    @ResponseBody
    public List<BasicoCategoria> listarPorCategoriaNombre(@PathVariable String nombre) {
        return elementoService.listarPorCategoriaNombre(nombre);
    }

    //Obtiene el ultimo registro por id categoria
    @GetMapping(value = URL + "/obtenerPorCategoria/{idCategoria}")
    @ResponseBody
    public ResponseEntity<?> obtenerPorCategoria(@PathVariable int idCategoria) {
        BasicoCategoria bc = elementoService.obtenerPorCategoria(idCategoria);
        return new ResponseEntity(bc, (bc!=null ? HttpStatus.OK: HttpStatus.NO_CONTENT));
    }

    //Obtiene una lista por id categoria
    @GetMapping(value = URL + "/listarPorCategoria/{idCategoria}")
    @ResponseBody
    public List<BasicoCategoria> listarPorCategoria(@PathVariable int idCategoria) {
        return elementoService.listarPorCategoria(idCategoria);
    }

    //Obtiene una lista por id categoria y anio
    @GetMapping(value = URL + "/listarPorCategoriaYAnio/{idCategoria}/{anio}")
    @ResponseBody
    public List<BasicoCategoria> listarPorCategoria(@PathVariable int idCategoria, @PathVariable short anio) {
        return elementoService.listarPorCategoriaYAnio(idCategoria, anio);
    }

    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody BasicoCategoria elemento) {
        try {
            BasicoCategoria a = elementoService.agregar(elemento);
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
    public ResponseEntity<?> actualizar(@RequestBody BasicoCategoria elemento) {
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