package com.joedayz.corespringtest.web;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joedayz.corespringtest.domain.Post;
import com.joedayz.corespringtest.domain.Tema;
import com.joedayz.corespringtest.service.PostService;
import com.joedayz.corespringtest.service.TemaService;

@Controller
public class BlogController {

	private static final String TEMAS = "temas";
	private static final String TEMA = "tema";
	private static final String POSTS = "posts";
	
	@Autowired
	private TemaService temaService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping("/temas.htm")
	public @ModelAttribute(TEMAS) List<Tema> mostrarTemas(Model model){
		return temaService.listarTodosLosTemas();
	}
	
	@RequestMapping("/posts.htm")
	public void mostrarPosts(@RequestParam("id")Long id, Model model){
		List<Post> posts = postService.listarTodosLosPostPorTema(id);
		Tema tema = temaService.obtenerTema(id);
		
		model.addAttribute(POSTS, posts);
		model.addAttribute(TEMA, tema);
	}
	
	@RequestMapping("/iplocationform.htm")
	public void mostrarDatosIp(HttpServletRequest request,
			Model model, @RequestHeader("Host") String host) throws UnknownHostException{

		String remoteAddr = request.getRemoteAddr().concat(" ").concat(request.getRemoteHost());
		InetAddress thisIp = InetAddress.getLocalHost();
		model.addAttribute("datos", remoteAddr.concat(" ").concat(thisIp.toString()));
	}
	

	
}
