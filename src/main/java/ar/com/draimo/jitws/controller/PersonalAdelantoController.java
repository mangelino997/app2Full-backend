//Paquete al que pertenece el controlador
package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.dto.InitPersonalAdelantoDTO;
import ar.com.draimo.jitws.dto.PersonalAdelantoLoteDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.PersonalAdelanto;
import ar.com.draimo.jitws.service.PersonalAdelantoService;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
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
 * Clase PersonalAdelanto Controller
 *
 * @author blas
 */
@RestController
public class PersonalAdelantoController {

    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/personaladelanto";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/personaladelanto";

    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;

    //Crea una instancia del servicio
    @Autowired
    PersonalAdelantoService elementoService;

    //Obtiene listas necesarias para inicializar el componente (front)
    @GetMapping(value = URL + "/inicializar/{idRol}/{idSubopcion}")
    @ResponseBody
    public InitPersonalAdelantoDTO inicializar(@PathVariable int idRol, @PathVariable int idSubopcion) {
        return elementoService.inicializar(idRol, idSubopcion);
    }


    //Obtiene el siguiente id
    @GetMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }

    //Obtiene la diferencia de importes para la lista de cuotas
    @PutMapping(value = URL + "/obtenerDiferenciaImportes")
    @ResponseBody
    public BigDecimal obtenerDiferenciaImportes(@RequestBody List<PersonalAdelantoLoteDTO> listaCuotas) throws IOException {
        return elementoService.obtenerDiferenciaImportes(listaCuotas);
    }

    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public Object listar() throws IOException {
        return elementoService.listar();
    }

    //Obtiene la lista de cuotas
    @PostMapping(value = URL + "/listarCuotas")
    @ResponseBody
    public Object listarCuotas(@RequestBody PersonalAdelanto personalAdelanto) throws IOException {
        return elementoService.listarCoutas(personalAdelanto);
    }

    //Obtiene una lista por lote o fecha emision o empresa
    @GetMapping(value = URL + "/listarLotes/{fechaDesde}/{fechaHasta}/{idEmpresa}")
    @ResponseBody
    public List<PersonalAdelantoLoteDTO> listarLotes(@PathVariable Date fechaDesde, @PathVariable Date fechaHasta,
            @PathVariable int idEmpresa) throws IOException {
        return elementoService.listarLotes(fechaDesde, fechaHasta, idEmpresa);
    }

    //Obtiene una lista por filtros
    @PostMapping(value = URL + "/listarPorFiltros")
    @ResponseBody
    public Object listarPorFiltros(@RequestBody PersonalAdelantoLoteDTO personalAdelanto) throws IOException, Exception {
        return elementoService.listarPorFiltros(personalAdelanto);
    }

    //Actualiza un registro
    @PutMapping(value = URL + "/anularLote")
    public ResponseEntity<?> anularLote(@RequestBody PersonalAdelanto elemento) {
        try {
            //Actualiza el registro
            elementoService.anularLote(elemento);
            //Envia la nueva lista a los usuarios subscripto
//            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de actualizado con exito
            return MensajeRespuesta.anulado();
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

    //Anula un registro - un adelanto personal
    @PutMapping(value = URL + "/anular")
    public ResponseEntity<?> anular(@RequestBody PersonalAdelanto elemento) {
        try {
            //Actualiza el registro
            elementoService.anular(elemento);
            //Envia la nueva lista a los usuarios subscripto
//            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de actualizado con exito
            return MensajeRespuesta.anulado();
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

    //Agrega un lote
    @PostMapping(value = URL + "/agregarLote")
    @ResponseBody
    public Object agregarLote(@RequestBody PersonalAdelantoLoteDTO personalAdelanto) throws IOException {
        try {
            //Retorna mensaje de agregado con exito
            Object o = elementoService.agregarLote(personalAdelanto);
            //controla la cantidad de adelantos fallidos
            boolean b = elementoService.controlAdelantosFallidos(o, personalAdelanto);
            return new ResponseEntity(o, b ? HttpStatus.CONFLICT : HttpStatus.CREATED);
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

    //Agrega un registro con prestamo
    @PostMapping(value = URL + "/agregarPrestamo")
    public ResponseEntity<?> agregarPrestamo(@RequestBody List<PersonalAdelanto> elementos) {
        try {
            List<PersonalAdelanto> a = elementoService.agregarPrestamo(elementos);
            //Envia la nueva lista a los usuarios subscriptos
            //template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de agregado con exito
            return MensajeRespuesta.agregado(a.get(a.size() - 1).getId());
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

    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody PersonalAdelanto elemento) {
        try {
            PersonalAdelanto a = elementoService.agregar(elemento);
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
    public ResponseEntity<?> actualizar(@RequestBody PersonalAdelanto elemento) {
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
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch (Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }

}