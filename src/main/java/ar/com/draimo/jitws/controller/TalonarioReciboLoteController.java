package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.exception.CodigoRespuesta;
import ar.com.draimo.jitws.exception.EstadoRespuesta;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.TalonarioReciboLote;
import ar.com.draimo.jitws.service.TalonarioReciboLoteService;
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
 * Clase TalonarioReciboLote Controller
 * @author blas
 */

@RestController
public class TalonarioReciboLoteController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/talonariorecibolote";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/talonariorecibolote";
    
    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;
    
    //Crea una instancia del servicio
    @Autowired
    TalonarioReciboLoteService elementoService;
    
    //Obtiene el siguiente id
    @GetMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public List<TalonarioReciboLote> listar() {
        return elementoService.listar();
    }
    
    //Obtiene la lista por empresa y lote entregado false
    @GetMapping(value = URL + "/listarPorEmpresaYLoteEntregadoFalse/{idEmpresa}")
    @ResponseBody
    public List<TalonarioReciboLote> listarPorEmpresaYLoteEntregadoFalse(@PathVariable int idEmpresa) {
        return elementoService.listarPorEmpresaYLoteEntregadoFalse(idEmpresa);
    }
    
    //Obtiene la lista por empresa 
    @GetMapping(value = URL + "/listarPorEmpresa/{idEmpresa}")
    @ResponseBody
    public List<TalonarioReciboLote> listarPorEmpresa(@PathVariable int idEmpresa) {
        return elementoService.listarPorEmpresa(idEmpresa);
    }
    
    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody TalonarioReciboLote elemento) {
        try {
            TalonarioReciboLote a = elementoService.agregar(elemento);
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
            //Retorna codigo y mensaje de error
            return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.ERROR_INTERNO_SERVIDOR,
                e.getMessage(), 0), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Actualiza un registro
    @PutMapping(value = URL)
    public ResponseEntity<?> actualizar(@RequestBody TalonarioReciboLote elemento) {
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
        } catch (JpaObjectRetrievalFailureException jorfe) {
            //Retorna mensaje de dato inexistente
            return MensajeRespuesta.datoInexistente("a", jorfe.getMessage());
        } catch(ObjectOptimisticLockingFailureException oolfe) {
            //Retorna mensaje de transaccion no actualizada
            return MensajeRespuesta.transaccionNoActualizada();
        }catch(MessagingException e) {
            //Retorna codigo y mensaje de error de sicronizacion mediante socket
            return MensajeRespuesta.errorSincSocket();
        } catch(Exception e) {
            //Retorna codigo y mensaje de error
            return new ResponseEntity<>(new EstadoRespuesta(CodigoRespuesta.ERROR_INTERNO_SERVIDOR,
                e.getMessage(), 0), HttpStatus.INTERNAL_SERVER_ERROR);
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
        } catch(Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }
    
}
