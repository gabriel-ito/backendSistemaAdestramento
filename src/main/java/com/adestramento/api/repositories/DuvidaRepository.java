package com.adestramento.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import com.adestramento.api.entities.Duvida;

@Transactional(readOnly = true)
public interface DuvidaRepository extends JpaRepository<Duvida, Integer> {

	@Query("SELECT ca FROM Duvida ca WHERE ca.usuario.id = :usuarioId")
   	List<Duvida> findByUsuarioId(@Param("usuarioId") int usuarioId);
	
	@Query("SELECT ca FROM Duvida ca WHERE ca.adestrador.id = :adestradorId")
   	List<Duvida> findByAdestradorId(@Param("adestradorId") int adestradorId);
	
	@Query("SELECT ca FROM Duvida ca WHERE ca.ativo = :ativo")
   	List<Duvida> findByAtivo(@Param("ativo") int ativo);
	
	@Transactional
   	@Modifying(clearAutomatically = true)
   	@Query("UPDATE Duvida SET resposta = :resposta, adestrador.id = :adestradorId WHERE id = :id")
   	void inserirResposta(@Param("resposta") String resposta, @Param("id") int id, @Param("adestradorId") int adestradorId);
	
}
