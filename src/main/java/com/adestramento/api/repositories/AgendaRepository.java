package com.adestramento.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
 
import com.adestramento.api.entities.Agenda;

@Transactional(readOnly = true)
public interface AgendaRepository extends JpaRepository<Agenda, Integer> {

	@Query("SELECT ca FROM Agenda ca WHERE ca.usuario.id = :usuarioId")
   	List<Agenda> findByUsuarioId(@Param("usuarioId") int usuarioId);
	
	@Query("SELECT ca FROM Agenda ca WHERE ca.adestrador.id = :adestradorId")
   	List<Agenda> findByAdestradorId(@Param("adestradorId") int adestradorId);
	
	@Transactional
   	@Modifying(clearAutomatically = true)
   	@Query("UPDATE Agenda SET observacao = :observacao, usuario.id = :usuarioId WHERE id = :id")
   	void cadastrar(@Param("observacao") String observacao, @Param("id") int id, @Param("usuarioId") int usuarioId);
}
