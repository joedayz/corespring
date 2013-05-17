package br.com.itexto.springforum.controladoras;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import br.com.itexto.springforum.dao.DAOTema;
import br.com.itexto.springforum.dao.DAOUsuario;
import br.com.itexto.springforum.entidades.Usuario;


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
	
	
	@RequestMapping("/registro")
	public String registro(Map<String, Object> model){
		
		if(model.get("usuario") == null){
			Usuario usr = new Usuario();
			model.put("usuario", usr);
		}
		return "registro";
	}

	
	@RequestMapping(value="/executarRegistro", method=RequestMethod.POST)
	public String executarRegistro(Usuario usuario, 
							HttpSession session) {
							
		getDaoUsuario().persist(usuario);
		session.setAttribute("usuario", usuario);
		return "redirect:/";
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
