package com.adestramento.api.controllers;
 
import java.util.List;
import java.util.Optional;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.adestramento.api.entities.Agenda;
import com.adestramento.api.services.AgendaService;
import com.adestramento.api.utils.ConsistenciaException;
import com.adestramento.api.response.Response;
import com.adestramento.api.dtos.AgendaDto;
import com.adestramento.api.utils.ConversaoUtils;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
 
@RestController
@RequestMapping("/api/agenda")
@CrossOrigin(origins = "*")
public class AgendaController {
 
   	private static final Logger log = LoggerFactory.getLogger(AgendaController.class);
 
   	@Autowired
   	private AgendaService agendaService;
 
   	/**
   	 * Retorna as agendas do informado no parâmetro
   	 *
   	 * @param Id do usuário a ser consultado
   	 * @return Lista de agendas que o usuário possui
   	 */
   	@GetMapping(value = "/usuario/{usuarioId}")
   	public ResponseEntity<Response<List<AgendaDto>>> buscarPorUsuarioId(@PathVariable("usuarioId") int usuarioId) {
 
   		Response<List<AgendaDto>> response = new Response<List<AgendaDto>>();
   		
         	try {
 
                	log.info("Controller: buscando agendas do usuário de ID: {}", usuarioId);
 
                	Optional<List<Agenda>> listaAgendas = agendaService.buscarPorUsuarioId(usuarioId);
 
                	response.setDados(ConversaoUtils.ConverterLista(listaAgendas.get()));
                	 
                	return ResponseEntity.ok(response);

 
         	} catch (ConsistenciaException e) {
                	log.info("Controller: Inconsistência de dados: {}", e.getMessage());
                	response.adicionarErro(e.getMensagem());
                	return ResponseEntity.badRequest().body(response);
         	} catch (Exception e) {
                	log.error("Controller: Ocorreu um erro na aplicação: {}", e.getMessage());
                	response.adicionarErro("Ocorreu um erro na aplicação: {}", e.getMessage());
                	return ResponseEntity.status(500).body(response);
         	}
 
   	}
   	
   	@GetMapping(value = "/adestrador/{adestradorId}")
   	public ResponseEntity<Response<List<AgendaDto>>> buscarPorAdestradorId(@PathVariable("adestradorId") int adestradorId) {
 
   		Response<List<AgendaDto>> response = new Response<List<AgendaDto>>();
   		
         	try {
 
                	log.info("Controller: buscando agendas do usuário de ID: {}", adestradorId);
 
                	Optional<List<Agenda>> listaAgendas = agendaService.buscarPorAdestradorId(adestradorId);
 
                	response.setDados(ConversaoUtils.ConverterLista(listaAgendas.get()));
                	 
                	return ResponseEntity.ok(response);

 
         	} catch (ConsistenciaException e) {
                	log.info("Controller: Inconsistência de dados: {}", e.getMessage());
                	response.adicionarErro(e.getMensagem());
                	return ResponseEntity.badRequest().body(response);
         	} catch (Exception e) {
                	log.error("Controller: Ocorreu um erro na aplicação: {}", e.getMessage());
                	response.adicionarErro("Ocorreu um erro na aplicação: {}", e.getMessage());
                	return ResponseEntity.status(500).body(response);
         	}
 
   	}
 
   	/**
   	 * Persiste um usuário na base.
   	 *
   	 * @param Dados de entrada da agenda
   	 * @return Dados da agenda persistido
   	 */
   	@PostMapping
   	public ResponseEntity<Response<AgendaDto>> salvar(@Valid @RequestBody AgendaDto agendaDto, BindingResult result) {
   		
   		Response<AgendaDto> response = new Response<AgendaDto>();
   		
         	try {
 
                	log.info("Controller: salvando a reunião: {}", agendaDto.toString());
                	
                	if (result.hasErrors()) {
                		 
                       	for (int i = 0; i < result.getErrorCount(); i++) {
                       	   	response.adicionarErro(result.getAllErrors().get(i).getDefaultMessage());
                       	}
                       	
                       	log.info("Controller: Os campos obrigatórios não foram preenchidos");
                       	return ResponseEntity.badRequest().body(response);
 
                	}
                	
                	Agenda agenda = this.agendaService.salvar(ConversaoUtils.Converter(agendaDto));
                	response.setDados(ConversaoUtils.Converter(agenda));
                	 
                	return ResponseEntity.ok(response);
 
         	} catch (ConsistenciaException e) {
                	log.info("Controller: Inconsistência de dados: {}", e.getMessage());
                	response.adicionarErro(e.getMensagem());
                	return ResponseEntity.badRequest().body(response);
         	} catch (Exception e) {
                	log.error("Controller: Ocorreu um erro na aplicação: {}", e.getMessage());
                	response.adicionarErro("Ocorreu um erro na aplicação: {}", e.getMessage());
                	return ResponseEntity.status(500).body(response);
         	}
 
   	}
   	
   	@PostMapping(value = "/cadastrar")
   	public ResponseEntity<Response<AgendaDto>> cadastrarAgenda(@Valid @RequestBody AgendaDto agendaDto, BindingResult result) {
 
   		Response<AgendaDto> response = new Response<AgendaDto>();
 
         	try {
         				//respostaDto.getIdUsuario()
                	log.info("Controller: salvando o cadastro: {}", agendaDto.getObservacao());
 
                	// Verificando se todos os campos da DTO foram preenchidos
                	if (result.hasErrors()) {
 
                       	for (int i = 0; i < result.getErrorCount(); i++) {
                       	   	response.adicionarErro(result.getAllErrors().get(i).getDefaultMessage());
                       	}
 
                       	log.info("Controller: Os campos obrigatórios não foram preenchidos");
                       	return ResponseEntity.badRequest().body(response);
 
                	}
 
                	// Inserir cadastro.
                	this.agendaService.cadastrarAgenda(agendaDto.getObservacao(),Integer.parseInt(agendaDto.getId()),Integer.parseInt(agendaDto.getUsuarioId()));
 
                	response.setDados(agendaDto);
                	return ResponseEntity.ok(response);
 
         	} catch (ConsistenciaException e) {
 
                	log.info("Controller: Inconsistência de dados: {}", e.getMessage());
                	response.adicionarErro(e.getMensagem());
                	return ResponseEntity.badRequest().body(response);
 
         	} catch (Exception e) {
 
                	log.error("Controller: Ocorreu um erro na aplicação: {}", e.getMessage());
                	response.adicionarErro("Ocorreu um erro na aplicação: {}", e.getMessage());
                	return ResponseEntity.status(500).body(response);
 
         	}
 
   	}
   	
   	/**
   	 * Exclui uma agenda a partir do id informado no parâmtero
   	 * @param id da agenda a ser excluído
   	 * @return Sucesso/erro
   	 */
   	@DeleteMapping(value = "excluir/{id}")
   	public ResponseEntity<Response<String>> excluirPorId(@PathVariable("id") int id){
         	
   		Response<String> response = new Response<String>();
   		
         	try {
 
                	log.info("Controller: excluíndo reunião de ID: {}", id);
 
                	agendaService.excluirPorId(id);
 
                	response.setDados("Agenda de id: " + id + " excluído com sucesso");
                	 
                	return ResponseEntity.ok(response);

 
         	} catch (ConsistenciaException e) {
                	log.info("Controller: Inconsistência de dados: {}", e.getMessage());
                	response.adicionarErro(e.getMensagem());
                	return ResponseEntity.badRequest().body(response);
         	} catch (Exception e) {
                	log.error("Controller: Ocorreu um erro na aplicação: {}", e.getMessage());
                	response.adicionarErro("Ocorreu um erro na aplicação: {}", e.getMessage());
                	return ResponseEntity.status(500).body(response);
         	}
         	
   	}
 
}
