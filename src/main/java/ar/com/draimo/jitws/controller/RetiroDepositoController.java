package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.RetiroDeposito;
import ar.com.draimo.jitws.service.RetiroDepositoService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Clase RetiroDeposito Controller
 * @author blas
 */

@RestController
public class RetiroDepositoController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/retirodeposito";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/retirodeposito";
    
    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;
    
    //Crea una instancia del servicio
    @Autowired
    RetiroDepositoService elementoService;
    
    //Obtiene el siguiente id
    @GetMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public List<RetiroDeposito> listar() {
        return elementoService.listar();
    }
    
    //Obtiene la lista de planillas abiertas
    @GetMapping(value = URL + "/listarPorEstaCerrada/{estaCerrada}")
    @ResponseBody
    public List<RetiroDeposito> listarPorEstaCerrada(@PathVariable boolean estaCerrada) {
        return elementoService.listarPorEstaCerrada(estaCerrada);
    }
    
    //Cierra un repartopropio
    @PutMapping(value = URL + "/cerrarReparto/{idRetiroDeposito}")
    @ResponseBody
    public ResponseEntity<?> cerrarReparto(@PathVariable int idRetiroDeposito) {
        boolean r = elementoService.cerrarRetiro(idRetiroDeposito);
        if (r == true) {
            //template.convertAndSend(TOPIC + "/lista", elementoService.listarPorEstaCerrada(false));
            return MensajeRespuesta.cerrado();
        } else{
            return MensajeRespuesta.error();
        }
    }
    
    //Obtiene una lista por numeroDocumento
    @GetMapping(value = URL + "/listarPorNumeroDocumento/{numeroDocumento}")
    @ResponseBody
    public List<RetiroDeposito> listarPorNumeroDocumento(@PathVariable String numeroDocumento) {
        return elementoService.obtenerPorNumeroDocumento(numeroDocumento);
    }
    
    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestPart("formulario") String retiroString,
            @RequestPart("archivo") MultipartFile archivo) {
        try {
            RetiroDeposito a = elementoService.agregar(retiroString,archivo);
            //Envia la nueva lista a los usuarios subscriptos
            //template.convertAndSend(TOPIC + "/listarPorEstaCerrada", elementoService.listarPorEstaCerrada(false));
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
    public ResponseEntity<?> actualizar(@RequestPart("formulario") String retiroString,
            @RequestPart("archivo") MultipartFile archivo) {
        try {
            //Actualiza el registro
            elementoService.actualizar(retiroString,archivo);
            //Envia la nueva lista a los usuarios subscripto
            //template.convertAndSend(TOPIC + "/listarPorEstaCerrada", elementoService.listarPorEstaCerrada(false));
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
        } catch(Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }
    
}
