package com.adestramento.api.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.adestramento.api.entities.Agenda;
import com.adestramento.api.repositories.AgendaRepository;
import com.adestramento.api.repositories.UsuarioRepository;
import com.adestramento.api.utils.ConsistenciaException;

@Service
public class AgendaService {

	private static final Logger log = LoggerFactory.getLogger(AgendaService.class);

	@Autowired
	private AgendaRepository agendaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Optional<Agenda> buscarPorId(int id) throws ConsistenciaException {

		log.info("Service: buscando as agendas de id: {}", id);

		Optional<Agenda> agenda = agendaRepository.findById(id);

		if (!agenda.isPresent()) {

			log.info("Service: Nenhuma agenda com id: {} foi encontrado", id);
			throw new ConsistenciaException("Nenhuma agenda com id: {} foi encontrado", id);

		}

		return agenda;

	}

	public Optional<List<Agenda>> buscarPorUsuarioId(int usuarioId) throws ConsistenciaException {

		log.info("Service: buscando as agendas do usuario de id: {}", usuarioId);

		Optional<List<Agenda>> agendas = Optional.ofNullable(agendaRepository.findByUsuarioId(usuarioId));

		if (!agendas.isPresent() || agendas.get().size() < 1) {

			log.info("Service: Nenhuma agenda encontrada para o usuário de id: {}", usuarioId);
			throw new ConsistenciaException("Nenhuma agenda encontrada para o usuário de id: {}", usuarioId);

		}

		return agendas;

	}
	
	public Optional<List<Agenda>> buscarPorAdestradorId(int adestradorId) throws ConsistenciaException {

		log.info("Service: buscando as agendas do adestrador de id: {}", adestradorId);

		Optional<List<Agenda>> agendas = Optional.ofNullable(agendaRepository.findByAdestradorId(adestradorId));

		if (!agendas.isPresent() || agendas.get().size() < 1) {

			log.info("Service: Nenhuma agenda encontrada para o usuário de id: {}", adestradorId);
			throw new ConsistenciaException("Nenhuma agenda encontrada para o usuário de id: {}", adestradorId);

		}

		return agendas;

	}

	public Agenda salvar(Agenda agenda) throws ConsistenciaException {

		log.info("Service: salvando a agenda: {}", agenda);

		if (!usuarioRepository.findById(agenda.getAdestrador().getId()).isPresent()) {

			log.info("Service: Nenhum usuário com id: {} foi encontrado", agenda.getAdestrador().getId());
			throw new ConsistenciaException("Nenhum usuário com id: {} foi encontrado", agenda.getAdestrador().getId());

		}

		if (agenda.getId() > 0)
			buscarPorId(agenda.getId());

		try {

			return agendaRepository.save(agenda);

		} catch (DataIntegrityViolationException e) {

			log.info("Service: Já existe uma agenda de número {} cadastrado", agenda.getId());
			throw new ConsistenciaException("Já existe uma agenda de número {} cadastrado", agenda.getId());

		}

	}
	
	public void cadastrarAgenda(String observacao, int id, int usuarioId) throws ConsistenciaException {
		 
      	log.info("Service: cadastrando agenda do usuário: {}", usuarioId);

      	agendaRepository.cadastrar(observacao, id, usuarioId);


	}

	public void excluirPorId(int id) throws ConsistenciaException {

		log.info("Service: excluíndo a agenda de id: {}", id);

		buscarPorId(id);

		agendaRepository.deleteById(id);

	}

}
