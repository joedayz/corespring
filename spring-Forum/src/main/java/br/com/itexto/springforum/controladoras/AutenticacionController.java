package br.com.itexto.springforum.controladoras;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.itexto.springforum.dao.DAOUsuario;
import br.com.itexto.springforum.entidades.Usuario;


@Controller
public class AutenticacionController {

	@Autowired
	private DAOUsuario daoUsuario;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login (@RequestParam("login") String login, 
			@RequestParam("password") String password, HttpSession session){
		Usuario usuario = daoUsuario.getUsuario(login, password);
		
		if(usuario==null){
			return "loginFallo";
		}else{
			usuario.setUltimoLogin(new Date());
			daoUsuario.persist(usuario);
			session.setAttribute("usuario", usuario);
			
			return "redirect:/";
		}

	}
	@RequestMapping(value="/logout") 
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
	
}
