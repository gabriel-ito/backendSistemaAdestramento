package com.adestramento.api.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.adestramento.api.entities.Usuario;
import com.adestramento.api.repositories.UsuarioRepository;
import com.adestramento.api.utils.ConsistenciaException;

@Service
public class UsuarioService {

	private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Optional<Usuario> buscarPorId(int id) throws ConsistenciaException {

		log.info("Service: buscando um usuário com o id: {}", id);

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (!usuario.isPresent()) {

			log.info("Service: Nenhum usuário com id: {} foi encontrado", id);
			throw new ConsistenciaException("Nenhum usuário com id: {} foi encontrado", id);

		}

		return usuario;

	}
	
	
	public Optional<Usuario> buscarPorEmailESenha(String email, String senha) throws ConsistenciaException {

		log.info("Service: buscando um usuário com o email e senha: {}", email, senha);

		Optional<Usuario> usuario = usuarioRepository.findByEmailAndSenha(email, senha);

		if (!usuario.isPresent()) {

			log.info("Service: E-mail ou senha incorreto");
			throw new ConsistenciaException("E-mail ou senha incorreto");

		}

		return usuario;

	}

	public Usuario salvar(Usuario usuario) throws ConsistenciaException {

		log.info("Service: salvando o usuário: {}", usuario);

		if (usuario.getId() > 0)
			buscarPorId(usuario.getId());

		try {
			return usuarioRepository.save(usuario);
		} catch (DataIntegrityViolationException e) {

			log.info("Service: O id: {} já está cadastrado para outro usuário", usuario.getId());
			throw new ConsistenciaException("O id: {} já está cadastrado para outro usuário", usuario.getId());

		}

	}

}
