package com.adestramento.api.controllers;
 
import java.util.List;
import java.util.Optional;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adestramento.api.dtos.AdestradorDto;
import com.adestramento.api.entities.Adestrador;
import com.adestramento.api.services.AdestradorService;
import com.adestramento.api.utils.ConsistenciaException;
import com.adestramento.api.utils.ConversaoUtils;
import com.adestramento.api.response.Response;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

@RestController
@RequestMapping("/api/adestrador")
@CrossOrigin(origins = "*")
public class AdestradorController {
 
   	private static final Logger log = LoggerFactory.getLogger(AdestradorController.class);
 
   	@Autowired
   	private AdestradorService adestradorService;
 
   	/**
   	 * Retorna os dados de um usuário a partir do seu id
   	 *
   	 * @param Id do usuário
   	 * @return Dados do usuário
   	 */
   	@GetMapping(value = "/{id}")
   	public ResponseEntity<Response<Adestrador>> buscarPorId(@PathVariable("id") int id) {
   		
   		Response<Adestrador> response = new Response<Adestrador>();

         	try {
 
                	log.info("Controller: buscando adestrador com id: {}", id);
                	
                	Optional<Adestrador> adestrador = adestradorService.buscarPorId(id);
 
                	response.setDados(adestrador.get());
                	 
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
   	
   	@GetMapping(value = "/ativo/{ativo}")
	public ResponseEntity<Response<List<Adestrador>>> buscarPorAdestradores(@PathVariable("ativo") int ativo) {

		Response<List<Adestrador>> response = new Response<List<Adestrador>>();

		try {

			log.info("Controller: buscando todas as perguntas: {}", ativo);

			Optional<List<Adestrador>> listaAdestradores = adestradorService.buscarPorAdestradores(ativo);

			response.setDados(listaAdestradores.get());

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
   	 * Retorna os dados de um usuário a partir do seu email e senha
   	 *
   	 * @param Email e senha do usuário
   	 * @return Dados do usuário
   	 */
   	@GetMapping(value = "/{email}/{senha}")
   	public ResponseEntity<Response<Adestrador>> buscarPorEmailESenha (@PathVariable("email") String email, @PathVariable("senha") String senha) {
   		
   		Response<Adestrador> response = new Response<Adestrador>();
   		
         	try {
 
                	log.info("Controller: buscando usuário com email e senha: {}", email, senha);
                	
                	Optional<Adestrador> adestrador = adestradorService.buscarPorEmailESenha(email, senha);
                	 
                	response.setDados(adestrador.get());
                	 
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
   	 * @param Dados de entrada do usuário
   	 * @return Dados do usuário persistido
   	 */
   	@PostMapping
   	public ResponseEntity<Response<AdestradorDto>> salvar(@Valid @RequestBody AdestradorDto adestradorDto, BindingResult result) {
 
   		Response<AdestradorDto> response = new Response<AdestradorDto>();
   		
         	try {
 
                	log.info("Controller: salvando o adestrador: {}", adestradorDto.toString());
                	
                	if (result.hasErrors()) {
                		 
                       	for (int i = 0; i < result.getErrorCount(); i++) {
                       	   	response.adicionarErro(result.getAllErrors().get(i).getDefaultMessage());
                       	}
                       	
                       	log.info("Controller: Os campos obrigatórios não foram preenchidos");
                       	return ResponseEntity.badRequest().body(response);
 
                	}

 
                	Adestrador adestrador = this.adestradorService.salvar(ConversaoUtils.Converter(adestradorDto));
                	response.setDados(ConversaoUtils.Converter(adestrador));
                	 
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
