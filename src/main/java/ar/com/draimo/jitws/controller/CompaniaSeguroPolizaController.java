//Paquete al que pertenece el controlador
package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.dto.InitCompaniaSeguroPolizaDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.CompaniaSeguroPoliza;
import ar.com.draimo.jitws.service.CompaniaSeguroPolizaService;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Clase CompaniaSeguroPoliza Controller
 *
 * @author blas
 */
@RestController
public class CompaniaSeguroPolizaController {

    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/companiaseguropoliza";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/companiaseguropoliza";

    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;

    //Crea una instancia del servicio
    @Autowired
    CompaniaSeguroPolizaService elementoService;

    //Obtiene listas necesarias para inicializar el componente (front)
    @GetMapping(value = URL + "/inicializar/{idRol}/{idSubopcion}")
    @ResponseBody
    public InitCompaniaSeguroPolizaDTO inicializar(@PathVariable int idRol, @PathVariable int idSubopcion) {
        return elementoService.inicializar(idRol, idSubopcion);
    }

    //Obtiene el siguiente id
    @GetMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }

    //Obtiene la poliza por id
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

    //Obtiene una lista por empresa
    @GetMapping(value = URL + "/listarPorEmpresa/{id}")
    @ResponseBody
    public Object listarPorEmpresa(@PathVariable int id) throws IOException {
        return elementoService.listarPorEmpresa(id);
    }

    //Obtiene una lista por compania seguro
    @GetMapping(value = URL + "/listarPorCompaniaSeguro/{id}")
    @ResponseBody
    public Object listarPorCompaniaSeguro(@PathVariable int id) throws IOException {
        return elementoService.listarPorCompaniaSeguro(id);
    }

    //Obtiene por compania de seguro y empresa
    @GetMapping(value = URL + "/listarPorCompaniaSeguroYEmpresa/{idCompaniaSeguro}/{idEmpresa}")
    @ResponseBody
    public Object listarPorCompaniaSeguroYEmpresa(@PathVariable int idCompaniaSeguro,
            @PathVariable int idEmpresa) throws IOException {
        return elementoService.listarPorCompaniaSeguroYEmpresa(idCompaniaSeguro, idEmpresa);
    }

    //Obtiene una lista por nombre de compania de seguro
    @GetMapping(value = URL + "/listarPorCompaniaSeguroNombre/{nombre}")
    @ResponseBody
    public Object listarPorCompaniaSeguroNombre(@PathVariable String nombre) throws IOException {
        return elementoService.listarPorCompaniaSeguroNombre(nombre);
    }

    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestPart("formulario") String polizaString,
            @RequestPart("archivo") MultipartFile archivo) {
        try {
            CompaniaSeguroPoliza a = elementoService.agregar(polizaString, archivo);
            //Envia la nueva lista a los usuarios subscriptos
//            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
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
    public ResponseEntity<?> actualizar(@RequestPart("formulario") String polizaString,
            @RequestPart("archivo") MultipartFile archivo) {
        try {
            //Actualiza el registro
            elementoService.actualizar(polizaString, archivo);
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