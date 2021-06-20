package com.adestramento.api.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
 
import com.adestramento.api.entities.Adestrador;

@Transactional(readOnly = true)
public interface AdestradorRepository extends JpaRepository<Adestrador, Integer> {

	@Transactional(readOnly = true)
   	List<Adestrador> findByDataCadastro(Date dataCadastro);
	
	@Transactional(readOnly = true)
	Optional<Adestrador> findByEmailAndSenha(String email, String senha);
	
	@Query("SELECT ca FROM Adestrador ca WHERE ca.ativo = :ativo")
   	List<Adestrador> findByAdestradoresAtivos(@Param("ativo") int ativo);
	
}
