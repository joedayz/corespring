package br.com.itexto.springforum.controladoras;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String index(Map<String, Object> model) {
		model.put("temas", getDaoTema().list(0, 100));
		model.put("usuarios", getDaoUsuario().list(0, 100));
		return "index";
	}

	@RequestMapping("/registro")
	public String registro(Map<String, Object> model) {

		if (model.get("usuario") == null) {
			Usuario usr = new Usuario();
			model.put("usuario", usr);
		}
		return "registro";
	}

	@RequestMapping(value = "/executarRegistro", method = RequestMethod.POST)
	public String executarRegistro(
			@Valid Usuario usuario,
			BindingResult bindingResult,
			HttpSession session,
			@RequestParam(value = "avatar", required = false) MultipartFile avatar) {
		if (bindingResult.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("usuario", usuario);
			return registro(model);
		}
		getDaoUsuario().persist(usuario);

		if (!avatar.isEmpty()) {
			processarAvatar(usuario, avatar);
		}

		session.setAttribute("usuario", usuario);
		return "redirect:/";
	}

	private void processarAvatar(Usuario usuario, MultipartFile avatar) {
		File diretorio = new File("springForum/avatares");
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}
		try {
			FileOutputStream archivo = new FileOutputStream(
					diretorio.getAbsolutePath() + "/" + usuario.getLogin()
							+ ".png");
			archivo.write(avatar.getBytes());
			archivo.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

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
