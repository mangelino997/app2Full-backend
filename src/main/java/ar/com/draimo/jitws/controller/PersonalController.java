//Paquete al que pertenece el controlador
package ar.com.draimo.jitws.controller;

import ar.com.draimo.jitws.constant.RutaConstant;
import ar.com.draimo.jitws.dto.ChoferDTO;
import ar.com.draimo.jitws.dto.InitPersonalDTO;
import ar.com.draimo.jitws.dto.PersonalDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Personal;
import ar.com.draimo.jitws.service.PersonalService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Clase Personal Controller
 * @author blas
 */
@RestController
public class PersonalController {

    //Define la url
    private final String URL = RutaConstant.URL_BASE + "/personal";
    //Define la url de subcripciones a sockets
    private final String TOPIC = RutaConstant.URL_TOPIC + "/personal";

    //Define el template para el envio de datos por socket
    @Autowired
    private SimpMessagingTemplate template;

    //Crea una instancia del servicio
    @Autowired
    PersonalService elementoService;

    //Obtiene las listas para inicializar
    @GetMapping(value = URL + "/inicializar/{usuario}/{rol}/{subopcion}")
    @ResponseBody
    public InitPersonalDTO inicializar(@PathVariable int usuario, @PathVariable int rol, @PathVariable int subopcion) {
        return elementoService.inicializar(usuario, rol, subopcion);
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
    public Object listar() throws IOException, Exception {
        return elementoService.listar();
    }

    //Obtiene un registro por id
    @GetMapping(value = URL + "/obtenerPorId/{id}")
    @ResponseBody
    public Object obtenerPorId(@PathVariable int id) throws IOException, Exception {
        return elementoService.obtenerPorId(id);
    }

    //Obtiene la lista de choferes de corta distancia por alias
    @GetMapping(value = URL + "/listarChoferesPorDistanciaPorAlias/{alias}/{largaDistancia}")
    @ResponseBody
    public Object listarChoferesPorDistanciaPorAliasOrdenados(@PathVariable String alias, @PathVariable boolean largaDistancia) throws IOException {
        int distancia = largaDistancia ? 1 : 0;
        return elementoService.listarChoferesPorDistanciaPorAliasOrdenadoPorNombre(
                alias, distancia, 0);
    }

    //Obtiene la lista de choferespor empresa
    @GetMapping(value = URL + "/listarChoferesPorEmpresa/{idEmpresa}")
    @ResponseBody
    public Object listarChoferesPorEmpresa(@PathVariable int idEmpresa) throws IOException, Exception {
        return elementoService.listarChoferesPorAliasOrdenadoPorNombre("*", idEmpresa);
    }

    //Obtiene la lista de choferespor empresa
    @GetMapping(value = URL + "/listarChoferesPorAliasYEmpresa/{alias}/{idEmpresa}")
    @ResponseBody
    public Object listarChoferesPorAliasYEmpresa(@PathVariable String alias, @PathVariable int idEmpresa) throws IOException, Exception {
        return elementoService.listarChoferesPorAliasOrdenadoPorNombre(alias, idEmpresa);
    }

    //Obtiene la lista completa
    @GetMapping(value = URL + "/listarAcompaniantesPorAlias/{alias}")
    @ResponseBody
    public Object listarAcompaniantesPorAliasOrdenadosPorNombre(@PathVariable String alias) throws IOException {
        return elementoService.listarAcompaniantesPorAliasOrdenadosPorNombre(alias);
    }

    //Obtiene la lista de acompaniantes por empresa ordenados por nombre
    @GetMapping(value = URL + "/listarAcompaniantes")
    @ResponseBody
    public Object listarAcompaniantes() throws IOException {
        return elementoService.listarAcompaniantesPorAliasOrdenadosPorNombre("*");
    }

    //Obtiene una lista por alias
    @GetMapping(value = URL + "/listarPorAlias/{alias}")
    @ResponseBody
    public Object listarPorAlias(@PathVariable String alias) throws IOException, Exception {
        return elementoService.listarPorAlias(alias, false, 0, 0);
    }

    //Obtiene una lista de activos por alias
    @GetMapping(value = URL + "/listarActivosPorAlias/{alias}")
    @ResponseBody
    public Object listarActivosPorAlias(@PathVariable String alias) throws IOException, Exception {
        return elementoService.listarPorAlias(alias, true, 0, 0);
    }

    //Obtiene una lista por alias y empresa
    @GetMapping(value = URL + "/listarPorAliasYEmpresa/{alias}/{idEmpresa}")
    @ResponseBody
    public Object listarPorAliasyEmpresa(@PathVariable String alias, @PathVariable int idEmpresa) throws IOException, Exception {
        return elementoService.listarPorAlias(alias, true, idEmpresa, 0);
    }

    //Obtiene una lista de activos por alias y empresa
    @GetMapping(value = URL + "/listarPorActivosAliasYEmpresa/{alias}/{idEmpresa}")
    @ResponseBody
    public Object listarActivosPorAliasyEmpresa(@PathVariable String alias,
            @PathVariable int idEmpresa) throws IOException, Exception {
        return elementoService.listarPorAlias(alias, true, idEmpresa, 0);
    }

    //Obtiene una lista por alias, sucursal y empresa
    @GetMapping(value = URL + "/listarActivosPorAliasEmpresaYSucursal/{alias}/{idEmpresa}/{idSucursal}")
    @ResponseBody
    public Object listarActivosPorAliasEmpresaYSucursal(@PathVariable String alias,
            @PathVariable int idEmpresa, @PathVariable int idSucursal) throws IOException, Exception {
        return elementoService.listarPorAlias(alias, true, idEmpresa, idSucursal);
    }

    //Obtiene una lista de choferes por alias
    @GetMapping(value = URL + "/listarChoferActivoPorAlias/{alias}")
    @ResponseBody
    public Object listarChoferPorAlias(@PathVariable String alias) throws IOException, Exception {
        return elementoService.listarChoferesPorDistanciaPorAliasOrdenadoPorNombre(alias, 2, 0);
    }

    //Obtiene una lista por filtros
    @PostMapping(value = URL + "/listarPorFiltros")
    @ResponseBody
    public Object listarPorFiltros(@RequestBody PersonalDTO personalDTO) throws IOException, Exception {
        return elementoService.listarPorFiltros(personalDTO);
    }

    //Obtiene una lista de choferes por filtros
    @PostMapping(value = URL + "/listarChoferesPorFiltros")
    @ResponseBody
    public Object listarPorFiltros(@RequestBody ChoferDTO elemento) throws IOException, Exception {
        return elementoService.listarChoferesPorFiltros(elemento);
    }

    //Agrega un registro
    @PostMapping(value = URL)
    public ResponseEntity<?> agregar(@RequestPart("personal") String elementoString,
            @RequestPart("foto") MultipartFile foto, @RequestPart("licConducir") MultipartFile licConducir,
            @RequestPart("linti") MultipartFile linti, @RequestPart("libSanidad") MultipartFile libSanidad,
            @RequestPart("dni") MultipartFile dni, @RequestPart("altaTemprana") MultipartFile altaTemprana) {
        try {
            //Agrega el registro
            Personal personal = elementoService.agregar(elementoString, foto,
                    licConducir, linti, libSanidad, dni, altaTemprana);
            //Actualiza inmediatamente el registro para establecer el alias
            elementoService.establecerAlias(personal);
            //Envia la nueva lista a los usuarios subscriptos
            //template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de agregado con exito
            return MensajeRespuesta.agregado(personal.getId());
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
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

    //Actualiza un registro
    @PutMapping(value = URL)
    public ResponseEntity<?> actualizar(@RequestBody Personal elemento) {
        try {
            //Actualiza el registro
            Personal personal = elementoService.actualizar(elemento);
            //Establece el alias y actualiza
            elementoService.establecerAlias(personal);
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
    
    //Actualiza un pdf
    @PutMapping(value = URL + "/actualizarPdf")
    public ResponseEntity<?> actualizarPDF(@RequestParam("idPersonal") int idPersonal, 
            @RequestParam("idPdf") int idPdf, @RequestParam("tipoPdf") String tipoPdf, 
            @RequestParam("archivo") MultipartFile archivo) {
        try {
            //Actualiza el registro
            Object personal = elementoService.actualizarPDF(idPersonal, idPdf, tipoPdf, archivo);
            //Envia la nueva lista a los usuarios subscripto
            ////template.convertAndSend(TOPIC + "/lista", elementoService.listar());
            //Retorna mensaje de actualizado con exito
            return new ResponseEntity(personal, HttpStatus.OK);
        } catch (Exception e) {
            //Retorna mensaje de error interno en el servidor
            return MensajeRespuesta.error();
        }
    }
    
    //Elimina un pdf
    @DeleteMapping(value = URL + "/eliminarPdf/{idPersonal}/{idPdf}/{tipoPdf}")
    public ResponseEntity<?> eliminarPDF(@PathVariable int idPersonal, @PathVariable int idPdf,
            @PathVariable String tipoPdf) {
        try {
            Personal personal = elementoService.eliminarPDF(idPersonal, idPdf, tipoPdf);
            //Retorna mensaje de eliminado con exito
            return new ResponseEntity(personal.getVersion(), HttpStatus.OK);
        } catch (DataIntegrityViolationException dive) {
            //Retorna mensaje de dato duplicado
            return MensajeRespuesta.datoDuplicado(dive);
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