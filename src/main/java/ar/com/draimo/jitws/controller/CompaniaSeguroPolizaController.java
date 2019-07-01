package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.CompaniaSeguroPoliza;
import ar.com.draimo.jitws.service.CompaniaSeguroPolizaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Clase CompaniaSeguroPoliza Controller
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
    
    //Obtiene el siguiente id
    @GetMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    @GetMapping(value = URL)
    @ResponseBody
    public List<CompaniaSeguroPoliza> listar() {
        return elementoService.listar();
    }
    
    //Obtiene una lista por empresa
    @GetMapping(value = URL + "/listarPorEmpresa/{id}")
    @ResponseBody
    public List<CompaniaSeguroPoliza> listarPorEmpresa(@PathVariable int id) {
        return elementoService.listarPorEmpresa(id);
    }
    
    //Obtiene una lista por compania seguro
    @GetMapping(value = URL + "/listarPorCompaniaSeguro/{id}")
    @ResponseBody
    public List<CompaniaSeguroPoliza> listarPorCompaniaSeguro(@PathVariable int id) {
        return elementoService.listarPorCompaniaSeguro(id);
    }
    
    //Obtiene por compania de seguro y empresa
    @GetMapping(value = URL + "/listarPorCompaniaSeguroYEmpresa/{idCompaniaSeguro}/{idEmpresa}")
    @ResponseBody
    public List<CompaniaSeguroPoliza> listarPorCompaniaSeguroYEmpresa(@PathVariable int idCompaniaSeguro, @PathVariable int idEmpresa) {
        return elementoService.listarPorCompaniaSeguroYEmpresa(idCompaniaSeguro, idEmpresa);
    }
    
    //Obtiene una lista por nombre de compania de seguro
    @GetMapping(value = URL + "/listarPorCompaniaSeguroNombre/{nombre}")
    @ResponseBody
    public List<CompaniaSeguroPoliza> listarPorCompaniaSeguroNombre(@PathVariable String nombre) {
        return elementoService.listarPorCompaniaSeguroNombre(nombre);
    }
    
    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestPart("formulario") String soporteString,
            @RequestPart("archivo") MultipartFile archivo) {
        try {
            CompaniaSeguroPoliza a = elementoService.agregar(soporteString, archivo);
            //Envia la nueva lista a los usuarios subscriptos
//            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
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
    public ResponseEntity<?> actualizar(@RequestPart("formulario") String soporteString,
            @RequestPart("archivo") MultipartFile archivo) {
        try {
            //Actualiza el registro
            elementoService.actualizar(soporteString, archivo);
            //Envia la nueva lista a los usuarios subscripto
            template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de actualizado con exito
            return MensajeRespuesta.actualizado();
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
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
    @DeleteMapping(value = URL)
    public ResponseEntity<?> eliminar(@RequestBody CompaniaSeguroPoliza elemento) {
        try {
            elementoService.eliminar(elemento);
            //Retorna mensaje de eliminado con exito
            return MensajeRespuesta.eliminado();
        } catch(Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }
    
}
