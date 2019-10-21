//Paquete al que pertenece el controlador
package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.dto.elementoDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Reparto;
import ar.com.draimo.jitws.service.RepartoService;
import java.io.IOException;
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
 *
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

    //Obtiene por id
    @GetMapping(value = URL + "/obtenerPorId/{id}")
    @ResponseBody
    public Object obtenerPorId(@PathVariable int id) throws IOException {
        return elementoService.obtenerPorId(id);
    }
    
    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public Object listar() throws IOException {
        return elementoService.listar();
    }

    //Obtiene la lista de registros propios abiertos
    @GetMapping(value = URL + "/listarAbiertosPropios")
    @ResponseBody
    public Object listarAbiertosPropios() throws IOException {
        return elementoService.listarAbiertosPropios();
    }

    //Obtiene la lista de registros terceros abiertos
    @GetMapping(value = URL + "/listarAbiertosTerceros")
    @ResponseBody
    public Object listarAbiertosTerceros() throws IOException {
        return elementoService.listarAbiertosTerceros();
    }

    //Obtiene la lista por estaCerrada
    @GetMapping(value = URL + "/listarPorEstaCerrada/{estaCerrada}")
    @ResponseBody
    public Object listarPorEstaCerrada(@PathVariable boolean estaCerrada) throws IOException {
        return elementoService.listarPorEstaCerrada(estaCerrada);
    }

    //Obtiene la lista por estaCerrada y empresa
    @GetMapping(value = URL + "/listarPorEstaCerradaYEmpresa/{estaCerrada}/{idEmpresa}")
    @ResponseBody
    public Object listarPorEstaCerradaYEmpresa(@PathVariable boolean estaCerrada, @PathVariable int idEmpresa) throws IOException {
        return elementoService.listarPorEstaCerradaYEmpresa(estaCerrada, idEmpresa);
    }

    //Obtiene la lista por filtros(empresa, viaje propio o tercero, periodo de fecha, chofer y si esta abierto o cerrado)
    @PutMapping(value = URL + "/listarPorFiltros")
    public Object listarPorFiltros(@RequestBody elementoDTO repartoDTO) throws IOException {
        return elementoService.listarPorFiltros(repartoDTO);
    }

    //Cierra un reparto
    @PutMapping(value = URL + "/cerrarReparto")
    public ResponseEntity<?> cerrarReparto(@RequestBody Reparto reparto) throws IOException {
        try {
            boolean r = elementoService.cerrarReparto(reparto);
            /*template.convertAndSend(TOPIC + "/lista", 
                    elementoService.listarPorEstaCerrada(false));*/
            return (r ? MensajeRespuesta.cerrado() : MensajeRespuesta.sinComprobantes());
        } catch (Exception e) {
            return MensajeRespuesta.error();
        }
    }

    //Abre un reparto
    @GetMapping(value = URL + "/abrirReparto/{idReparto}")
    public ResponseEntity<?> abrirReparto(@PathVariable int idReparto) throws IOException {
        try {
            boolean r = elementoService.abrirReparto(idReparto);
            /*template.convertAndSend(TOPIC + "/lista", 
                    elementoService.listarPorEstaCerrada(false));*/
            return MensajeRespuesta.abierto();
        } catch (Exception e) {
            return MensajeRespuesta.error();
        }
    }

    //recibe un reparto
    @PutMapping(value = URL + "/recibirReparto")
    public ResponseEntity<?> recibirReparto(@RequestBody Reparto elemento) throws IOException {
        Reparto r = elementoService.recibirReparto(elemento);
        if (r != null) {
            return MensajeRespuesta.abierto();
        } else {
            return MensajeRespuesta.error();
        }
    }

    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody Reparto elemento) {
        try {
            int a = elementoService.agregar(elemento);
            //Envia la nueva lista a los usuarios subscriptos
            /*template.convertAndSend(TOPIC + "/listarPorEstaCerrada",
                    elementoService.listarPorEstaCerrada(false));*/
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
    public ResponseEntity<?> actualizar(@RequestBody Reparto elemento) {
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
            boolean a = elementoService.eliminar(id);
            if (a == true) {
                /*template.convertAndSend(TOPIC + "/listaPorEstaCerrada", 
                        elementoService.listarPorEstaCerrada(a));*/
                return MensajeRespuesta.eliminado();
            } else {
                return MensajeRespuesta.error();
            }
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch (Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }

}