package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Vehiculo;
import ar.com.draimo.jitws.service.VehiculoService;
import java.io.IOException;
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
 * Clase Vehiculo Controller
 * @author blas
 */

@RestController
public class VehiculoController {
    
    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/vehiculo";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/vehiculo";
    
    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;
    
    //Crea una instancia del servicio
    @Autowired
    VehiculoService elementoService;
    
    //Obtiene el siguiente id
    @GetMapping(value = URL + "/obtenerSiguienteId")
    @ResponseBody
    public int obtenerSiguienteId() {
        return elementoService.obtenerSiguienteId();
    }
    
    //Obtiene un registro por id
    @GetMapping(value = URL + "/obtenerPorId/{id}")
    @ResponseBody
    public Object obtenerPorId(@PathVariable int id) throws IOException {
        return elementoService.obtenerPorId(id);
    }
    
    //Obtiene un re
    @GetMapping(value = URL)
    @ResponseBody
    public Object listar() throws IOException {
        return elementoService.listar();
    }
    
    //Obtiene una lista por alias
    @GetMapping(value = URL + "/listarPorAlias/{alias}")
    @ResponseBody
    public Object listarPorAlias(@PathVariable String alias) throws IOException {
        return elementoService.listarPorAlias(alias);
    }
    
    //Obtiene una lista por alias y empresa
    @GetMapping(value = URL + "/listarPorAliasYEmpresa/{alias}/{idEmpresa}")
    @ResponseBody
    public Object listarPorAliasYEmpresa(@PathVariable String alias, @PathVariable int idEmpresa) throws IOException {
        return elementoService.listarPorAliasYEmpresa(alias,idEmpresa);
    }
    
    //Obtiene una lista por alias filtro no remolque
    @GetMapping(value = URL + "/listarPorAliasYRemolqueFalse/{alias}")
    @ResponseBody
    public Object listarPorAliasYRemolqueFalse(@PathVariable String alias) throws IOException {
        return elementoService.listarPorAliasYRemolqueFalse(alias);
    }
    
    //Obtiene una lista por alias filtro remolque
    @GetMapping(value = URL + "/listarPorAliasYRemolqueTrue/{alias}")
    @ResponseBody
    public Object listarPorAliasYRemolqueTrue(@PathVariable String alias) throws IOException {
        return elementoService.listarPorAliasYRemolqueTrue(alias);
    }
    
    //Obtiene una lista por alias filtro remolque
    @GetMapping(value = URL + "/listarPorAliasYEmpresaFiltroRemolque/{alias}/{idEmpresa}")
    @ResponseBody
    public Object listarPorAliasYEmpresaFiltroRemolque(@PathVariable String alias, @PathVariable int idEmpresa) throws IOException {
        return elementoService.listarPorAliasYEmpresaFiltroRemolque(alias, idEmpresa);
    }
    
    //Obtiene una lista por empresa, tipo de vehiculo y marca de vehiculo
    @GetMapping(value = URL + "/listarFiltro/{idEmpresa}/{idTipoVehiculo}/{idMarcaVehiculo}")
    @ResponseBody
    public Object listarFiltro(@PathVariable int idEmpresa, @PathVariable int idTipoVehiculo,
            @PathVariable int idMarcaVehiculo) throws IOException {
        return elementoService.listarFiltro(idEmpresa, idTipoVehiculo, idMarcaVehiculo);
    }
    
    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestPart("vehiculo") String elementoString,
            @RequestPart("titulo") MultipartFile titulo,@RequestPart("cedulaIdent")
                    MultipartFile cedulaIdent,@RequestPart("vtoRuta") MultipartFile vtoRuta,
                    @RequestPart("vtoInspTecnica") MultipartFile vtoInspTecnica,
                    @RequestPart("vtoSenasa") MultipartFile vtoSenasa,
                    @RequestPart("habBromat") MultipartFile habBromat) {
        try {
            Vehiculo a = elementoService.agregar(elementoString,titulo, cedulaIdent,
                    vtoRuta, vtoInspTecnica, vtoSenasa, habBromat);
            //Actualiza inmediatamente el registro para establecer el alias
            elementoService.establecerAlias(a);
            //Envia la nueva lista a los usuarios subscriptos
            //template.convertAndSend(TOPIC + "/lista", elementoService.listar());
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
    public ResponseEntity<?> actualizar(@RequestPart("vehiculo") String elementoString,
            @RequestPart("titulo") MultipartFile titulo,@RequestPart("cedulaIdent")
                    MultipartFile cedulaIdent,@RequestPart("vtoRuta") MultipartFile vtoRuta,
                    @RequestPart("vtoInspTecnica") MultipartFile vtoInspTecnica,
                    @RequestPart("vtoSenasa") MultipartFile vtoSenasa,
                    @RequestPart("habBromat") MultipartFile habBromat) {
        try {
            //Actualiza el registro
            Vehiculo vehiculo = elementoService.actualizar(elementoString, titulo, cedulaIdent, vtoRuta,
                    vtoInspTecnica, vtoSenasa, habBromat);
            //Actualiza inmediatamente el registro para establecer el alias
            elementoService.establecerAlias(vehiculo);
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
