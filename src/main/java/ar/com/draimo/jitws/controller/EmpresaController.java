//Paquete al que pertenece el controlador
package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.dto.InitEmpresaDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.service.EmpresaService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase Empresa Controller
 *
 * @author blas
 */
@RestController
public class EmpresaController {

    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/empresa";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/empresa";

    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;

    //Crea una instancia del servicio
    @Autowired
    EmpresaService elementoService;

    //Obtiene listas necesarias para inicializar el componente (front)
    @GetMapping(value = URL + "/inicializar/{idRol}/{idSubopcion}")
    @ResponseBody
    public InitEmpresaDTO inicializar(@PathVariable int idRol, @PathVariable int idSubopcion) {
        return elementoService.inicializar(idRol, idSubopcion);
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
    public List<Empresa> listar() {
        return elementoService.listar();
    }

    //Obtiene la lista de empresas activas
    @GetMapping(value = URL + "/listarActivas")
    @ResponseBody
    public List<Empresa> listarActivas() {
        return elementoService.listarPorFiltros("", 0);
    }

    //Obtiene una lista por razon social
    @GetMapping(value = URL + "/listarPorRazonSocial/{razonSocial}")
    @ResponseBody
    public List<Empresa> listarPorRazonSocial(@PathVariable String razonSocial) {
        return elementoService.listarPorFiltros(razonSocial, 1);
    }

    //Obtiene una lista por nombre y feCAEA habilitado
    @GetMapping(value = URL + "/listarPorRazonSocialYCAEAHabilitado/{razonSocial}")
    @ResponseBody
    public List<Empresa> listarPorRazonSocialYCAEAHabilitado(@PathVariable String razonSocial) {
        return elementoService.listarPorFiltros(razonSocial, 2);
    }

    //Obtiene una lista por nombre y esta activa
    @GetMapping(value = URL + "/listarPorRazonSocialYActiva/{razonSocial}")
    @ResponseBody
    public List<Empresa> listarPorRazonSocialYActiva(@PathVariable String razonSocial) {
        return elementoService.listarPorFiltros(razonSocial, 3);
    }

    //Obtiene una lista por nombre, esta activa y fe
    @GetMapping(value = URL + "/listarPorRazonSocialYActivaYFe/{razonSocial}")
    @ResponseBody
    public List<Empresa> listarPorRazonSocialYActivaYFe(@PathVariable String razonSocial) {
        return elementoService.listarPorFiltros(razonSocial, 4);
    }

    //Obtiene una lista de empresas por usuario
    @GetMapping(value = URL + "/listarEmpresasPorUsuario/{idUsuario}")
    @ResponseBody
    public List<Empresa> listarEmpresasPorUsuario(@PathVariable int idUsuario) {
        return elementoService.listarEmpresasPorUsuario(idUsuario);
    }

    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestBody Empresa elemento) {
        try {
            Empresa a = elementoService.agregar(elemento);
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
    public ResponseEntity<?> actualizar(@RequestBody Empresa elemento) {
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
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
        } catch (Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }

}