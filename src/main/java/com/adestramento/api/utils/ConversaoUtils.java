package com.adestramento.api.utils;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.adestramento.api.dtos.AgendaDto;
import com.adestramento.api.dtos.DuvidaDto;
import com.adestramento.api.dtos.UsuarioDto;
import com.adestramento.api.dtos.AdestradorDto;
import com.adestramento.api.entities.Agenda;
import com.adestramento.api.entities.Duvida;
import com.adestramento.api.entities.Usuario;
import com.adestramento.api.entities.Adestrador;

public class ConversaoUtils {

	public static Agenda Converter(AgendaDto agendaDto) throws ParseException {
		 
     	Agenda agenda = new Agenda();

     	if (agendaDto.getId() != null && agendaDto.getId() != "")
            	agenda.setId(Integer.parseInt(agendaDto.getId()));

     	agenda.setObservacao(agendaDto.getObservacao());
     	agenda.setDataAgenda(new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(agendaDto.getDataAgenda()));
     	agenda.setAtivo(1);
     	//Usuario usuario = new Usuario();
     	//usuario.setId(1);
     	//agenda.setUsuario(usuario);
     	Adestrador adestrador = new Adestrador();
     	adestrador.setId(Integer.parseInt(agendaDto.getAdestradorId()));
     	agenda.setAdestrador(adestrador);
     	return agenda;

	}
	
	public static AgendaDto Converter(Agenda agenda) {

     	AgendaDto agendaDto = new AgendaDto();
     	
     	agendaDto.setId(String.valueOf(agenda.getId()));
     	agendaDto.setObservacao(agenda.getObservacao());
     	agendaDto.setDataAgenda(agenda.getDataAgenda().toString());
     	//agendaDto.setAtivo(String.valueOf(agenda.getAtivo()));
     	//agendaDto.setUsuarioId(String.valueOf(agenda.getUsuario().getId()));
     	agendaDto.setAdestradorId(String.valueOf(agenda.getAdestrador().getId()));
     	
     	return agendaDto;

	}
	
	public static List<AgendaDto> ConverterLista(List<Agenda> lista){
     	
     	List<AgendaDto> lst = new ArrayList<AgendaDto>(lista.size());
     	
     	for (Agenda agenda : lista) {
            	lst.add(Converter(agenda));
     	}
     	
     	return lst;
     	
	}

	public static Duvida Converter(DuvidaDto duvidaDto) throws ParseException {
		 
     	Duvida duvida = new Duvida();

     	if (duvidaDto.getId() != null && duvidaDto.getId() != "")
            	duvida.setId(Integer.parseInt(duvidaDto.getId()));

     	duvida.setPergunta(duvidaDto.getPergunta());
     	duvida.setResposta(duvidaDto.getResposta());
     	duvida.setAtivo(1);
     	Usuario usuario = new Usuario();
     	usuario.setId(Integer.parseInt(duvidaDto.getUsuarioId()));
     	duvida.setUsuario(usuario);
     	//Adestrador adestrador = new Adestrador();
     	//adestrador.setId(Integer.parseInt(duvidaDto.getUsuarioId()));
     	//duvida.setAdestrador(adestrador);
     	
     	return duvida;

	}
	
	public static DuvidaDto Converter(Duvida duvida) {

     	DuvidaDto duvidaDto = new DuvidaDto();
     	
     	duvidaDto.setId(String.valueOf(duvida.getId()));
     	duvidaDto.setPergunta(duvida.getPergunta());
     	duvidaDto.setResposta(duvida.getResposta());
     	//duvidaDto.setAtivo(String.valueOf(duvida.getAtivo()));
     	duvidaDto.setUsuarioId(String.valueOf(duvida.getUsuario().getId()));
     	//duvidaDto.setAdestradorId(String.valueOf(duvida.getAdestrador().getId()));

     	return duvidaDto;

	}
	
	public static List<DuvidaDto> ConverterLista2(List<Duvida> lista){
     	
     	List<DuvidaDto> lst = new ArrayList<DuvidaDto>(lista.size());
     	
     	for (Duvida duvida : lista) {
            	lst.add(Converter(duvida));
     	}
     	
     	return lst;
     	
	}
	
	public static Usuario Converter(UsuarioDto usuarioDto) throws ParseException {

     	Usuario usuario = new Usuario();

     	if (usuarioDto.getId() != null && usuarioDto.getId() != "")
            	usuario.setId(Integer.parseInt(usuarioDto.getId()));

     	usuario.setNome(usuarioDto.getNome());
     	usuario.setEmail(usuarioDto.getEmail());
     	usuario.setSenha(usuarioDto.getSenha());
     	usuario.setEndereco(usuarioDto.getEndereco());
     	usuario.setTelefone(usuarioDto.getTelefone());
     	usuario.setAtivo(1);
     	
     	return usuario;

	}
	
	public static UsuarioDto Converter(Usuario usuario) {

     	UsuarioDto usuarioDto = new UsuarioDto();

     	usuarioDto.setId(String.valueOf(usuario.getId()));
     	usuarioDto.setNome(usuario.getNome());
     	usuarioDto.setEmail(usuario.getEmail());
     	usuarioDto.setSenha(usuario.getSenha());
     	usuarioDto.setEndereco(usuario.getEndereco());
     	usuarioDto.setTelefone(usuario.getTelefone());
     	//usuarioDto.setAtivo(1);
     	//usuarioDto.setAdestrador(String.valueOf(usuario.getAdestrador()));

     	return usuarioDto;

	}

	public static List<UsuarioDto> ConverterListaUsuario(List<Usuario> lista){
     	
     	List<UsuarioDto> lst = new ArrayList<UsuarioDto>(lista.size());
     	
     	for (Usuario usuario : lista) {
            	lst.add(Converter(usuario));
     	}
     	
     	return lst;
     	
	}
	
	public static Adestrador Converter(AdestradorDto adestradorDto) throws ParseException {

     	Adestrador adestrador = new Adestrador();

     	if (adestradorDto.getId() != null && adestradorDto.getId() != "")
            	adestrador.setId(Integer.parseInt(adestradorDto.getId()));

     	adestrador.setNome(adestradorDto.getNome());
     	adestrador.setEmail(adestradorDto.getEmail());
     	adestrador.setSenha(adestradorDto.getSenha());
     	adestrador.setCrmv(adestradorDto.getCrmv());
     	adestrador.setTelefone(adestradorDto.getTelefone());
     	adestrador.setAtivo(1);
     	
     	return adestrador;

	}
	
	public static AdestradorDto Converter(Adestrador adestrador) {

     	AdestradorDto adestradorDto = new AdestradorDto();

     	adestradorDto.setId(String.valueOf(adestrador.getId()));
     	adestradorDto.setNome(adestrador.getNome());
     	adestradorDto.setEmail(adestrador.getEmail());
     	adestradorDto.setSenha(adestrador.getSenha());
     	adestradorDto.setCrmv(adestrador.getCrmv());
     	adestradorDto.setTelefone(adestrador.getTelefone());
     	//usuarioDto.setAtivo(1);
     	//usuarioDto.setAdestrador(String.valueOf(usuario.getAdestrador()));

     	return adestradorDto;

	}

	public static List<AdestradorDto> ConverterListaAdestrador(List<Adestrador> lista){
     	
     	List<AdestradorDto> lst = new ArrayList<AdestradorDto>(lista.size());
     	
     	for (Adestrador adestrador : lista) {
            	lst.add(Converter(adestrador));
     	}
     	
     	return lst;
     	
	}
}
