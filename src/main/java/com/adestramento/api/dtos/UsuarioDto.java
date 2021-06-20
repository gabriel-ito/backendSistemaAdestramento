package com.adestramento.api.dtos;

import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class UsuarioDto {

	
	private String id;
	
	@NotEmpty(message = "Nome não pode ser vazio.")
   	@Length(min = 5, max = 50,
   	message = "Nome deve conter entre 5 e 50 caracteres.")
   	private String nome;
	
	@NotEmpty(message = "Email não pode ser vazio.")
   	@Length(min = 5, max = 50,
   	message = "Email deve conter entre 5 e 50 caracteres.")
   	private String email;
	
	@NotEmpty(message = "Email não pode ser vazio.")
   	@Length(min = 5, max = 10,
   	message = "Senha deve conter entre 5 e 10 caracteres.")
   	private String senha;
	
	@NotEmpty(message = "Email não pode ser vazio.")
   	@Length(min = 5, max = 60,
   	message = "Endereco deve conter entre 5 e 60 caracteres.")
   	private String endereco;
	
	@NotEmpty(message = "Telefone não pode ser vazio.")
	@Length(min = 8, max = 20,
   	message = "Telefone deve conter no mínimo 8 caracteres.")
   	private String telefone;
   	
	private String ativo;
	
	private String adestrador;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	public String getAdestrador() {
		return adestrador;
	}
	public void setAdestrador(String adestrador) {
		this.adestrador = adestrador;
	}
	
	@Override
	public String toString() {
		return "UsuarioDto [id=" + id + ", nome=" + nome + ", email=" + email + ", endereco=" + endereco + ", senha=" + senha + ", telefone="
				+ telefone + ", ativo=" + ativo + ", adestrador=" + adestrador + "]";
	}
	
}
