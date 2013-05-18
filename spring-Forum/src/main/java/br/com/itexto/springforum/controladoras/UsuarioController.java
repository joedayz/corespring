package br.com.itexto.springforum.controladoras;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import br.com.itexto.springforum.dao.DAOTopico;
import br.com.itexto.springforum.dao.DAOUsuario;
import br.com.itexto.springforum.entidades.Topico;
import br.com.itexto.springforum.entidades.Usuario;


@Controller("usuario")
@SessionAttributes("usuario")
public class UsuarioController {

	
	@Autowired
	private DAOUsuario daoUsuario;
	public DAOUsuario getDaoUsuario() {return daoUsuario;}
	public void setDaoUsuario(DAOUsuario dao) {daoUsuario = dao;}
	
	@Autowired
	private DAOTopico daoTopico;
	public DAOTopico getDaoTopico() {return daoTopico;}
	public void setDaoTopico(DAOTopico dao) {daoTopico = dao;}
	
	@RequestMapping("/usuario/avatar/{login}")
	@ResponseBody
	public byte[] avatar(@PathVariable("login") String login) throws IOException {
		File archivo = new File("springForum/avatares/" + login + ".png");
		
		if (! archivo.exists()) {
			archivo = new File("springForum/avatares/avatar.png");
		}
		
		byte[] resultado = new byte[(int)archivo.length()];
		FileInputStream input = new FileInputStream(archivo);
		input.read(resultado);
		input.close();
		
		return resultado;
	}
	
	
	@RequestMapping("/usuario/show/{id}")
	public ModelAndView usuario(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView();
		
		Usuario usuario = getDaoUsuario().get(id);
		
		mav.getModel().put("usuario", usuario);
		mav.setViewName("usuario/show");
		return mav;
	}
	
	
	@RequestMapping("/usuario/posts/{login}")
	public String topicosUsuario(@PathVariable("login") String login, Map<String, Object> model) {
		model.put("topicos", getDaoTopico().getTopicosPorAutor(getDaoUsuario()
				.getUsuario(login)));
		
		return "usuario/posts";
	}	
	
	@RequestMapping("/usuario/postsJSON/{login}")
	@ResponseBody
	public List<Topico> topicosUsuarioJson(@PathVariable("login") String login) {
		return getDaoTopico().getTopicosPorAutor(getDaoUsuario().getUsuario(login));
	}
	
}
