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
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "agenda")
public class Agenda implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
   	@GeneratedValue(strategy = GenerationType.AUTO)
   	private int id;
	
	@Column(name = "observacao", nullable = true, length = 100)
   	private String observacao;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Column(name = "data_Agenda", nullable = false)
   	private Date dataAgenda;

	@Column(name = "ativo", nullable = false, length = 1)
	private int ativo;
	
	@JsonBackReference(value = "agenda-usuario")
   	@ManyToOne(fetch = FetchType.EAGER)
   	private Usuario usuario;

	@JsonBackReference(value = "agenda-adestrador")
   	@ManyToOne(fetch = FetchType.EAGER)
   	private Adestrador adestrador;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDataAgenda() {
		return dataAgenda;
	}

	public void setDataAgenda(Date dataAgenda) {
		this.dataAgenda = dataAgenda;
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

	@Override
	public String toString() {
		return "Agenda [id=" + id + ", observacao=" + observacao + ", dataAgenda=" + dataAgenda + ", ativo=" + ativo
				+ ", usuario=" + usuario + ", adestrador=" + adestrador + "]";
	}
	
}