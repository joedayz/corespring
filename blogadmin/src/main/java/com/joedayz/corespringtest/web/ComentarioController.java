package com.joedayz.corespringtest.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.joedayz.corespringtest.domain.Comentario;
import com.joedayz.corespringtest.domain.Post;
import com.joedayz.corespringtest.service.ComentarioService;
import com.joedayz.corespringtest.service.PostService;

@Controller
public class ComentarioController {

	private static final String POST = "post";
	private static final String COMENTARIOS = "comentarios";
	private static final String COMENTARIO = "comentario";
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private ComentarioService comentarioService;
	
	@InitBinder
	public void init(WebDataBinder dataBinder){
		dataBinder.setRequiredFields("nombre", "correoElectronico", "comentario");
	}
	
	@RequestMapping(value = "/comentarios.htm", method = RequestMethod.GET)
	public @ModelAttribute(COMENTARIO) Comentario 
			verPost(@RequestParam(value = "idPost", required = false) Long id, 
					Model model){
		
		Comentario comentario = new Comentario();
		if(id != null){
			
			List<Comentario> comentarios = comentarioService.listarTodosLosComentariosPorPost(id);
			Post post = postService.obtenerPost(id);
			comentario.setIdPost(post.getId());
			
			model.addAttribute(COMENTARIOS, comentarios);
			model.addAttribute(POST, post);			
		}		
		return comentario;
	}
	
	@RequestMapping(value = "/comentarios.htm", method = RequestMethod.POST)
	public String guardarComentario(@ModelAttribute("comentario") Comentario comentario,
			BindingResult result, Model model){
		
		if(result.hasErrors()){
			
			List<Comentario> comentarios = comentarioService.listarTodosLosComentariosPorPost(comentario.getIdPost());
			Post post = postService.obtenerPost(comentario.getIdPost());
			
			model.addAttribute(COMENTARIOS, comentarios);
			model.addAttribute(POST, post);			
			
			return "comentarios";
		}
		comentarioService.guardarComentario(comentario);
		return "redirect:comentarios.htm?idPost="+comentario.getIdPost();
	}
}
