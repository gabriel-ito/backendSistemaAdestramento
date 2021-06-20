package com.adestramento.api.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.adestramento.api.entities.Duvida;
import com.adestramento.api.repositories.DuvidaRepository;
import com.adestramento.api.repositories.UsuarioRepository;
import com.adestramento.api.utils.ConsistenciaException;

@Service
public class DuvidaService {

	private static final Logger log = LoggerFactory.getLogger(DuvidaService.class);

	@Autowired
	private DuvidaRepository duvidaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Optional<Duvida> buscarPorId(int id) throws ConsistenciaException {

		log.info("Service: buscando as perguntas de id: {}", id);

		Optional<Duvida> duvida = duvidaRepository.findById(id);

		if (!duvida.isPresent()) {

			log.info("Service: Nenhuma pergunta com id: {} foi encontrado", id);
			throw new ConsistenciaException("Nenhuma pergunta com id: {} foi encontrado", id);

		}

		return duvida;

	}

	public Optional<List<Duvida>> buscarPorAtivo(int ativo) throws ConsistenciaException {

		log.info("Service: buscando todas as perguntas: {}", ativo);

		Optional<List<Duvida>> duvidas = Optional.ofNullable(duvidaRepository.findByAtivo(ativo));

		if (!duvidas.isPresent() || duvidas.get().size() < 1) {

			log.info("Service: Não foi possível retornar as perguntas {}", ativo);
			throw new ConsistenciaException("Não foi possível retornar as perguntas {}", ativo);

		}

		return duvidas;

	}

	public Optional<List<Duvida>> buscarPorUsuarioId(int usuarioId) throws ConsistenciaException {

		log.info("Service: buscando as perguntas do usuario de id: {}", usuarioId);

		Optional<List<Duvida>> duvidas = Optional.ofNullable(duvidaRepository.findByUsuarioId(usuarioId));

		if (!duvidas.isPresent() || duvidas.get().size() < 1) {

			log.info("Service: Nenhuma pergunta encontrada para o usuário de id: {}", usuarioId);
			throw new ConsistenciaException("Nenhuma pergunta encontrada para o usuário de id: {}", usuarioId);

		}

		return duvidas;

	}
	
	public Optional<List<Duvida>> buscarPorAdestradorId(int adestradorId) throws ConsistenciaException {

		log.info("Service: buscando as respostas do usuario de id: {}", adestradorId);

		Optional<List<Duvida>> duvidas = Optional.ofNullable(duvidaRepository.findByAdestradorId(adestradorId));

		if (!duvidas.isPresent() || duvidas.get().size() < 1) {

			log.info("Service: Nenhuma resposta encontrada para o usuário de id: {}", adestradorId);
			throw new ConsistenciaException("Nenhuma resposta encontrada para o usuário de id: {}", adestradorId);

		}

		return duvidas;

	}
	
	public Duvida salvar(Duvida duvida) throws ConsistenciaException {

		log.info("Service: salvando a duvida: {}", duvida);

		if (!usuarioRepository.findById(duvida.getUsuario().getId()).isPresent()) {

			log.info("Service: Nenhum usuário com id: {} foi encontrado", duvida.getUsuario().getId());
			throw new ConsistenciaException("Nenhum usuário com id: {} foi encontrado", duvida.getUsuario().getId());

		}

		if (duvida.getId() > 0)
			buscarPorId(duvida.getId());

		try {

			return duvidaRepository.save(duvida);

		} catch (DataIntegrityViolationException e) {

			log.info("Service: Já existe uma pergunta de número {} cadastrado", duvida.getId());
			throw new ConsistenciaException("Já existe uma pergunta de número {} cadastrado", duvida.getId());

		}

	}
	
	public void inserirResposta(String resposta, int id, int adestradorId) throws ConsistenciaException {
		 
      	log.info("Service: alterando a resposta do usuário: {}", id);

      	duvidaRepository.inserirResposta(resposta, id, adestradorId);


	}

	public void excluirPorId(int id) throws ConsistenciaException {

		log.info("Service: excluíndo a pergunta de id: {}", id);

		buscarPorId(id);

		duvidaRepository.deleteById(id);

	}

}
