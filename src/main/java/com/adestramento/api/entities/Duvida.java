package com.adestramento.api.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "duvida")
public class Duvida implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
   	@GeneratedValue(strategy = GenerationType.AUTO)
   	private int id;
	
	@Column(name = "pergunta", nullable = true, length = 100)
   	private String pergunta;
	
	@Column(name = "resposta", nullable = true, length = 100)
   	private String resposta;

	@Column(name = "data_Cadastro", nullable = false)
   	private Date dataCadastro;

	@Column(name = "ativo", nullable = false, length = 1)
	private int ativo;
	
	@JsonBackReference(value = "duvida-usuario")
   	@ManyToOne(fetch = FetchType.EAGER)
   	private Usuario usuario;
	
	@JsonBackReference(value = "duvida-adestrador")
   	@ManyToOne(fetch = FetchType.EAGER)
   	private Adestrador adestrador;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Adestrador getAdestrador() {
		return adestrador;
	}

	public void setAdestrador(Adestrador adestrador) {
		this.adestrador = adestrador;
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
		return "Pergunta [id=" + id + ", pergunta=" + pergunta + ", resposta=" + resposta + ", dataCadastro="
				+ dataCadastro + ", ativo=" + ativo + ", usuario=" + usuario + ", adestrador=" + adestrador + "]";
	}
	
}
