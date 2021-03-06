package com.adestramento.api.controllers;
 
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

import com.adestramento.api.dtos.UsuarioDto;
import com.adestramento.api.entities.Usuario;
import com.adestramento.api.services.UsuarioService;
import com.adestramento.api.utils.ConsistenciaException;
import com.adestramento.api.utils.ConversaoUtils;
import com.adestramento.api.response.Response;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {
 
   	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
 
   	@Autowired
   	private UsuarioService usuarioService;
 
   	/**
   	 * Retorna os dados de um usuário a partir do seu id
   	 *
   	 * @param Id do usuário
   	 * @return Dados do usuário
   	 */
   	@GetMapping(value = "/{id}")
   	public ResponseEntity<Response<Usuario>> buscarPorId(@PathVariable("id") int id) {
   		
   		Response<Usuario> response = new Response<Usuario>();

         	try {
 
                	log.info("Controller: buscando usuário com id: {}", id);
                	
                	Optional<Usuario> usuario = usuarioService.buscarPorId(id);
 
                	response.setDados(usuario.get());
                	 
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
   	public ResponseEntity<Response<Usuario>> buscarPorEmailESenha (@PathVariable("email") String email, @PathVariable("senha") String senha) {
   		
   		Response<Usuario> response = new Response<Usuario>();
   		
         	try {
 
                	log.info("Controller: buscando usuário com email e senha: {}", email, senha);
                	
                	Optional<Usuario> usuario = usuarioService.buscarPorEmailESenha(email, senha);
                	 
                	response.setDados(usuario.get());
                	 
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
   	public ResponseEntity<Response<UsuarioDto>> salvar(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result) {
 
   		Response<UsuarioDto> response = new Response<UsuarioDto>();
   		
         	try {
 
                	log.info("Controller: salvando o usuário: {}", usuarioDto.toString());
                	
                	if (result.hasErrors()) {
                		 
                       	for (int i = 0; i < result.getErrorCount(); i++) {
                       	   	response.adicionarErro(result.getAllErrors().get(i).getDefaultMessage());
                       	}
                       	
                       	log.info("Controller: Os campos obrigatórios não foram preenchidos");
                       	return ResponseEntity.badRequest().body(response);
 
                	}

 
                	Usuario usuario = this.usuarioService.salvar(ConversaoUtils.Converter(usuarioDto));
                	response.setDados(ConversaoUtils.Converter(usuario));
                	 
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
