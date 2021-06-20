package com.adestramento.api.dtos;

//import javax.validation.constraints.NotEmpty;
//import org.hibernate.validator.constraints.Length;


public class DuvidaDto {

	
	private String id;	
	
	//@NotEmpty(message = "Pergunta não pode ser vazia.")
   	//@Length(min = 5, max = 100,
   	//message = "Pergunta deve conter entre 5 e 100 caracteres.")
   	private String pergunta;
	
	//@NotEmpty(message = "Resposta não pode ser vazia.")
   	//@Length(min = 5, max = 100,
   	//message = "Resposta deve conter entre 5 e 100 caracteres.")
   	private String resposta;
   	
	private String ativo;
	
   	private String usuarioId;
   	
   	private String adestradorId;
   	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
		return "DuvidaDto [id=" + id + ", pergunta=" + pergunta + ", resposta=" + resposta + 
				", ativo=" + ativo + ", usuarioId=" + usuarioId + ", adestradorId=" + adestradorId + "]";
	}
   	
}
