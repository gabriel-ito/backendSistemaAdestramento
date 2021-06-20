package com.adestramento.api.dtos;

//import org.hibernate.validator.constraints.Length;

public class AgendaDto {

	private String id;
	
   	private String observacao;
   	
   	//@NotEmpty(message = "Data não pode ser vazio.")
   	private String dataAgenda;
   	
	private String ativo;
	
   	private String usuarioId;
   	
   	private String adestradorId;
   	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getDataAgenda() {
		return dataAgenda;
	}
	public void setDataAgenda(String dataAgenda) {
		this.dataAgenda = dataAgenda;
	}
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	public String getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getAdestradorId() {
		return adestradorId;
	}
	public void setAdestradorId(String adestradorId) {
		this.adestradorId = adestradorId;
	}
	
	@Override
	public String toString() {
		return "AgendaDto [id=" + id + ", observacao=" + observacao + ", dataAgenda=" + dataAgenda + ", ativo=" + ativo
				+ ", usuarioId=" + usuarioId + ", adestradorId=" + adestradorId + "]";
	}
	
}
