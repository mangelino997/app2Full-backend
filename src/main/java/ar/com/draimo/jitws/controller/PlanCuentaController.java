//Paquete al que pertenece el controlador
package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.PlanCuenta;
import ar.com.draimo.jitws.service.PlanCuentaService;
import java.io.IOException;
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
 * Clase PlandeCuenta Controller
 *
 * @author blas
 */
@RestController
public class PlanCuentaController {

    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/plancuenta";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/plancuenta";

    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;

    //Crea una instancia del servicio
    @Autowired
    PlanCuentaService elementoService;

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

    //Obtiene una lista por nombre
    @GetMapping(value = URL + "/listarPorNombre/{nombre}")
    @ResponseBody
    public Object listarPorNombre(@PathVariable String nombre) throws IOException {
        return elementoService.listarPorNombre(nombre);
    }

    //Obtiene un listado de grupoActivo por idEmpresa
    @GetMapping(value = URL + "/listarGrupoActivo/{idEmpresa}")
    @ResponseBody
    public Object listarGrupoActivo(@PathVariable int idEmpresa) throws IOException {
        return elementoService.listarGrupoActivo(idEmpresa);
    }

    //Obtiene un listado de estado resultado por idEmpresa
    @GetMapping(value = URL + "/listarGrupoEstadoResultados/{idEmpresa}")
    @ResponseBody
    public Object listarGrupoEstadoResultados(@PathVariable int idEmpresa) throws IOException {
        return elementoService.listarGrupoEstadoResultados(idEmpresa);
    }

    //Obtiene el plan de cuenta por empresa
    @GetMapping(value = URL + "/obtenerPlanCuentaPorEmpresa/{idEmpresa}")
    @ResponseBody
    public Object obtenerPlanCuentaPorEmpresa(@PathVariable int idEmpresa) throws IOException {
        return elementoService.obtenerPlanCuenta(idEmpresa);
    }

    //Lista por empresa y grupo cuenta contable
    @GetMapping(value = URL + "/listarPorEmpresaYGrupoCuentaContable/{idEmpresa}/{idGrupoCuentaContable}")
    @ResponseBody
    public Object listarPorEmpresaYGrupoCuentaContable(@PathVariable int idEmpresa, @PathVariable int idGrupoCuentaContable)
            throws IOException {
        return elementoService.listarPorEmpresaYGrupoCuentaContable(idEmpresa, idGrupoCuentaContable);
    }

    //Obtiene por empresa, grupo cuenta contable y nivel 2
    @GetMapping(value = URL + "/obtenerPorEmpresaYGrupoCuentaContable/{idEmpresa}/{idGrupoCuentaContable}")
    @ResponseBody
    public Object obtenerPorEmpresaYGrupoCuentaContable(@PathVariable int idEmpresa,
            @PathVariable int idGrupoCuentaContable) throws IOException {
        return elementoService.obtenerPorEmpresaYGrupoCuentaContable(idEmpresa, idGrupoCuentaContable);
    }

    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody PlanCuenta elemento) {
        try {
            PlanCuenta a = elementoService.agregar(elemento);
            //Retorna mensaje de agregado con exito
            return MensajeRespuesta.agregado(a.getId() - 1);
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
    public ResponseEntity<?> actualizar(@RequestBody PlanCuenta elemento) {
        try {
            //Actualiza el registro
            Object planCuenta = elementoService.actualizar(elemento);
            //Envia la nueva lista a los usuarios subscripto
            //template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de actualizado con exito
            return new ResponseEntity(planCuenta, HttpStatus.OK);
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
        } catch (IOException e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
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