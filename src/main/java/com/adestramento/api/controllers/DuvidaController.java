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
import com.adestramento.api.dtos.DuvidaDto;
import com.adestramento.api.entities.Duvida;
import com.adestramento.api.services.DuvidaService;
import com.adestramento.api.utils.ConsistenciaException;
import com.adestramento.api.utils.ConversaoUtils;
import com.adestramento.api.response.Response;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

@RestController
@RequestMapping("/api/duvida")
@CrossOrigin(origins = "*")
public class DuvidaController {

	private static final Logger log = LoggerFactory.getLogger(DuvidaController.class);

	@Autowired
	private DuvidaService duvidaService;

	/**
	 * Retorna as perguntas do informado no parâmetro
	 *
	 * @param Id do usuário a ser consultado
	 * @return Lista de perguntas que o usuário possui
	 */
	@GetMapping(value = "/usuario/{usuarioId}")
	public ResponseEntity<Response<List<Duvida>>> buscarPorUsuarioId(@PathVariable("usuarioId") int usuarioId) {

		Response<List<Duvida>> response = new Response<List<Duvida>>();

		try {

			log.info("Controller: buscando perguntas do usuário de ID: {}", usuarioId);

			Optional<List<Duvida>> listaDuvidas = duvidaService.buscarPorUsuarioId(usuarioId);

			response.setDados(listaDuvidas.get());

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
	public ResponseEntity<Response<List<Duvida>>> buscarPorAdestradorId(@PathVariable("adestradorId") int adestradorId) {

		Response<List<Duvida>> response = new Response<List<Duvida>>();

		try {

			log.info("Controller: buscando perguntas do usuário de ID: {}", adestradorId);

			Optional<List<Duvida>> listaDuvidas = duvidaService.buscarPorAdestradorId(adestradorId);

			response.setDados(listaDuvidas.get());

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
	public ResponseEntity<Response<List<Duvida>>> buscarPorAtivo(@PathVariable("ativo") int ativo) {

		Response<List<Duvida>> response = new Response<List<Duvida>>();

		try {

			log.info("Controller: buscando todas as perguntas: {}", ativo);

			Optional<List<Duvida>> listaDuvidas = duvidaService.buscarPorAtivo(ativo);

			response.setDados(listaDuvidas.get());

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

	@GetMapping(value = "/{id}")
   	public ResponseEntity<Response<Duvida>> buscarPorId(@PathVariable("id") int id) {
   		
   		Response<Duvida> response = new Response<Duvida>();

         	try {
 
                	log.info("Controller: buscando usuário com id: {}", id);
                	
                	Optional<Duvida> duvida = duvidaService.buscarPorId(id);
 
                	response.setDados(duvida.get());
                	 
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
	 * @param Dados de entrada da pergunta
	 * @return Dados da pergunta persistido
	 */
	@PostMapping
	public ResponseEntity<Response<DuvidaDto>> salvar(@Valid @RequestBody DuvidaDto duvidaDto, BindingResult result) {

		Response<DuvidaDto> response = new Response<DuvidaDto>();

		try {

			log.info("Controller: salvando a pergunta: {}", duvidaDto.toString());

			if (result.hasErrors()) {

				for (int i = 0; i < result.getErrorCount(); i++) {
					response.adicionarErro(result.getAllErrors().get(i).getDefaultMessage());
				}

				log.info("Controller: Os campos obrigatórios não foram preenchidos");
				return ResponseEntity.badRequest().body(response);

			}

			Duvida duvida = this.duvidaService.salvar(ConversaoUtils.Converter(duvidaDto));
			response.setDados(ConversaoUtils.Converter(duvida));

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
   	 * Altera a senha do usuário, verificando o próprio usuário e a senha atual.
   	 *
   	 * @param Dados de entrada do usuário
   	 * @return Dados do usuario persistido
   	 */
   	@PostMapping(value = "/resposta")
   	public ResponseEntity<Response<DuvidaDto>> inserirResposta(@Valid @RequestBody DuvidaDto duvidaDto, BindingResult result) {
 
   		Response<DuvidaDto> response = new Response<DuvidaDto>();
 
         	try {
         				//respostaDto.getIdUsuario()
                	log.info("Controller: adicionando a resposta da pergunta: {}", duvidaDto.getResposta());
 
                	// Verificando se todos os campos da DTO foram preenchidos
                	if (result.hasErrors()) {
 
                       	for (int i = 0; i < result.getErrorCount(); i++) {
                       	   	response.adicionarErro(result.getAllErrors().get(i).getDefaultMessage());
                       	}
 
                       	log.info("Controller: Os campos obrigatórios não foram preenchidos");
                       	return ResponseEntity.badRequest().body(response);
 
                	}
 
                	// Inserir resposta.
                	this.duvidaService.inserirResposta(duvidaDto.getResposta(),Integer.parseInt(duvidaDto.getId()),Integer.parseInt(duvidaDto.getAdestradorId()));
 
                	response.setDados(duvidaDto);
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
	 * Exclui uma pergunta a partir do id informado no parâmtero
	 * 
	 * @param id da pergunta a ser excluído
	 * @return Sucesso/erro
	 */
	@DeleteMapping(value = "excluir/{id}")
	public ResponseEntity<Response<String>> excluirPorId(@PathVariable("id") int id) {

		Response<String> response = new Response<String>();

		try {

			log.info("Controller: excluíndo pergunta de ID: {}", id);

			duvidaService.excluirPorId(id);

			response.setDados("Pergunta de id: " + id + " excluído com sucesso");

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
