package br.com.itexto.springforum.controladoras;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HolaMundoController {

	
	@RequestMapping("/hola")  //  http://spring-forum/hola
	public ModelAndView holaMundo(){
		ModelAndView resultado = new ModelAndView("mundo");
		resultado.addObject("data", new Date());
		return resultado;
	}
}
