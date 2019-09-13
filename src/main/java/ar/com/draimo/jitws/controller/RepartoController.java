package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Reparto;
import ar.com.draimo.jitws.service.RepartoService;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase Reparto Controller
 * @author blas
 */

@RestController
public class RepartoController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/reparto";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/reparto";
    
    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;
    
    //Crea una instancia del servicio
    @Autowired
    RepartoService elementoService;
    
    //Obtiene el siguiente id
    @GetMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public List<Reparto> listar() {
        return elementoService.listar();
    }
    
    //Obtiene la lista de registros propios abiertos
    @GetMapping(value = URL + "/listarAbiertosPropios")
    @ResponseBody
    public List<Reparto> listarAbiertosPropios() {
        return elementoService.listarAbiertosPropios();
    }
    
    //Obtiene la lista de registros terceros abiertos
    @GetMapping(value = URL + "/listarAbiertosTerceros")
    @ResponseBody
    public List<Reparto> listarAbiertosTerceros() {
        return elementoService.listarAbiertosTerceros();
    }
    
    //Obtiene la lista por estaCerrada
    @GetMapping(value = URL + "/listarPorEstaCerrada/{estaCerrada}")
    @ResponseBody
    public List<Reparto> listarPorEstaCerrada(@PathVariable boolean estaCerrada) {
        return elementoService.listarPorEstaCerrada(estaCerrada);
    }
    
    //Obtiene la lista por filtros
    @GetMapping(value = URL + "/listarPorFiltros/{idEmpresa}/{tipoViaje}/{fechaDesde}/{fechaHasta}/{idChofer}/{estaCerrada}")
    @ResponseBody
    public List<Reparto> listarPorFiltros(@PathVariable int idEmpresa, @PathVariable boolean tipoViaje,
            @PathVariable Date fechaDesde, @PathVariable Date fechaHasta, @PathVariable 
                    int idChofer,@PathVariable boolean estaCerrada) {
        return elementoService.listarPorFiltros(idEmpresa,tipoViaje,fechaDesde,fechaHasta,idChofer,estaCerrada);
    }
    
    //Cierra un repartopropio
    @PutMapping(value = URL + "/cerrarReparto/{idReparto}")
    public ResponseEntity<?> cerrarReparto(@PathVariable int idReparto) {
        boolean r = elementoService.cerrarReparto(idReparto);
        if (r == true) {
            template.convertAndSend(TOPIC + "/lista", 
                    elementoService.listarPorEstaCerrada(false));
            return MensajeRespuesta.cerrado();
        } else{
            return MensajeRespuesta.error();
        }
    }
    
    //Abre un repartopropio
    @PutMapping(value = URL + "/abrirReparto/{idReparto}")
    public ResponseEntity<?> abrirReparto(@PathVariable int idReparto) {
        boolean r = elementoService.abrirReparto(idReparto);
        if (r == true) {
            template.convertAndSend(TOPIC + "/lista", 
                    elementoService.listarPorEstaCerrada(true));
            return MensajeRespuesta.abierto();
        } else{
            return MensajeRespuesta.error();
        }
    }
    
    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody Reparto elemento) {
        try {
            Reparto a = elementoService.agregar(elemento);
            //Envia la nueva lista a los usuarios subscriptos
//            template.convertAndSend(TOPIC + "/listarPorEstaCerrada",
//                    elementoService.listarPorEstaCerrada(false));
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
    public ResponseEntity<?> actualizar(@RequestBody Reparto elemento) {
        try {
            //Actualiza el registro
            elementoService.actualizar(elemento);
            //Envia la nueva lista a los usuarios subscripto
//            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
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
            boolean a = elementoService.eliminar(id);
            if(a==true){
                template.convertAndSend(TOPIC + "/listaPorEstaCerrada", 
                        elementoService.listarPorEstaCerrada(a));
                return MensajeRespuesta.eliminado();
            } else {
                return MensajeRespuesta.error();
            }
        }catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch(Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }
    
}
