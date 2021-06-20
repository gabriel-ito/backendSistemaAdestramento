package com.adestramento.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
 
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "adestrador")
public class Adestrador implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
   	@GeneratedValue(strategy = GenerationType.AUTO)
   	private int id;
	
	@Column(name = "nome", nullable = false, length = 100)
   	private String nome;

	@Column(name = "email", nullable = false, length = 100)
   	private String email;

	@Column(name = "senha", nullable = false, length = 100)
   	private String senha;
	
	@Column(name = "crmv", nullable = false, length = 8)
   	private String crmv;
	
	@Column(name = "telefone", nullable = false, length = 20)
   	private String telefone;

	@Column(name = "data_Cadastro", nullable = false)
   	private Date dataCadastro;

	@Column(name = "ativo", nullable = false, length = 1)
	private int ativo;
	
	@JsonManagedReference(value = "agenda-adestrador")
   	@OneToMany(mappedBy = "adestrador", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  	private List<Agenda> agendas;
	
	@JsonManagedReference(value = "duvida-adestrador")
	@OneToMany(mappedBy = "adestrador", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   	private List<Duvida> duvidas;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
	public String getCrmv() {
		return crmv;
	}

	public void setCrmv(String crmv) {
		this.crmv = crmv;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public List<Agenda> getAgendas() {
		return agendas;
	}

	public void setAgendas(List<Agenda> agendas) {
		this.agendas = agendas;
	}

	public List<Duvida> getDuvidas() {
		return duvidas;
	}

	public void setPerguntas(List<Duvida> duvidas) {
		this.duvidas = duvidas;
	}
	
	@PreUpdate
	public void preUpdate() {
		dataCadastro = new Date();
	}

	@PrePersist
	public void prePersist() {
		dataCadastro = new Date();
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", crmv=" + crmv + ", telefone="
				+ telefone + ", dataCadastro=" + dataCadastro + ", ativo=" + ativo + "]";
	}
	
}
