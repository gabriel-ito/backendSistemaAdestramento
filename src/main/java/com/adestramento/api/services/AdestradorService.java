package com.adestramento.api.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.adestramento.api.entities.Adestrador;
import com.adestramento.api.repositories.AdestradorRepository;
import com.adestramento.api.utils.ConsistenciaException;

@Service
public class AdestradorService {

	private static final Logger log = LoggerFactory.getLogger(AdestradorService.class);

	@Autowired
	private AdestradorRepository adestradorRepository;

	public Optional<Adestrador> buscarPorId(int id) throws ConsistenciaException {

		log.info("Service: buscando um adestrador com o id: {}", id);

		Optional<Adestrador> adestrador = adestradorRepository.findById(id);

		if (!adestrador.isPresent()) {

			log.info("Service: Nenhum adestrador com id: {} foi encontrado", id);
			throw new ConsistenciaException("Nenhum adestrador com id: {} foi encontrado", id);

		}

		return adestrador;

	}
	
	public Optional<List<Adestrador>> buscarPorAdestradores(int ativo) throws ConsistenciaException {

		log.info("Service: buscando todas as perguntas: {}", ativo);

		Optional<List<Adestrador>> adestradores = Optional.ofNullable(adestradorRepository.findByAdestradoresAtivos(ativo));

		if (!adestradores.isPresent() || adestradores.get().size() < 1) {

			log.info("Service: Não foi possível retornar as perguntas {}", ativo);
			throw new ConsistenciaException("Não foi possível retornar as perguntas {}", ativo);

		}

		return adestradores;

	}
	
	
	public Optional<Adestrador> buscarPorEmailESenha(String email, String senha) throws ConsistenciaException {

		log.info("Service: buscando um usuário com o email e senha: {}", email, senha);

		Optional<Adestrador> adestrador = adestradorRepository.findByEmailAndSenha(email, senha);

		if (!adestrador.isPresent()) {

			log.info("Service: E-mail ou senha incorreto");
			throw new ConsistenciaException("E-mail ou senha incorreto");

		}

		return adestrador;

	}

	public Adestrador salvar(Adestrador adestrador) throws ConsistenciaException {

		log.info("Service: salvando o usuário: {}", adestrador);

		if (adestrador.getId() > 0)
			buscarPorId(adestrador.getId());

		try {
			return adestradorRepository.save(adestrador);
		} catch (DataIntegrityViolationException e) {

			log.info("Service: O id: {} já está cadastrado para outro usuário", adestrador.getId());
			throw new ConsistenciaException("O id: {} já está cadastrado para outro usuário", adestrador.getId());

		}

	}

}
