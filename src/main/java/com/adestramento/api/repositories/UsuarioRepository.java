package com.adestramento.api.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
 
import com.adestramento.api.entities.Usuario;

@Transactional(readOnly = true)
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Transactional(readOnly = true)
   	List<Usuario> findByDataCadastro(Date dataCadastro);
	
	@Transactional(readOnly = true)
	Optional<Usuario> findByEmailAndSenha(String email, String senha);
	
}
