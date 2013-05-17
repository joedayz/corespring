package br.com.itexto.springforum.controladoras;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.itexto.springforum.dao.DAOTema;
import br.com.itexto.springforum.dao.DAOUsuario;


@Controller
public class HomeController {

	@Autowired
	private DAOUsuario daoUsuario;
	
	@Autowired
	private DAOTema daoTema;
	
	
	@RequestMapping("/")
	public String index(Map<String, Object> model){
		model.put("temas", getDaoTema().list(0,100));
		model.put("usuarios", getDaoUsuario().list(0, 100));
		return "index";
	}


	public DAOUsuario getDaoUsuario() {
		return daoUsuario;
	}


	public void setDaoUsuario(DAOUsuario daoUsuario) {
		this.daoUsuario = daoUsuario;
	}


	public DAOTema getDaoTema() {
		return daoTema;
	}


	public void setDaoTema(DAOTema daoTema) {
		this.daoTema = daoTema;
	}
	
	
	
}
