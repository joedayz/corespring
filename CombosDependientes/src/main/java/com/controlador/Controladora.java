package com.controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bean.City;
import com.bean.Country;
import com.servicio.Servicio;

/**
 * 
 * @author josediaz
 *
 */
@Controller
@SessionAttributes({ "menu", "perfilspring", "user" })
public class Controladora {

	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	@Qualifier("servicio")
	private Servicio service;

	@RequestMapping(value = "/contacto", method = RequestMethod.GET)
	public String onlog(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {

		logger.info("entro a controladora");

		List<Country> countries = new ArrayList<Country>();
		countries.add(new Country("IND", "INDIA"));
		countries.add(new Country("US", "USA"));
		countries.add(new Country("AU", "AUSTRALIA"));
		countries.add(new Country("MX", "MEXICO"));
		countries.add(new Country("COL", "COLOMBIA"));

		model.addAttribute("listCountries", countries);

		return "contacto";
	}

	/*
	 * para submit de form
	 */
	@RequestMapping(value = "/tabs", method = RequestMethod.POST)
	public String tabs(@RequestParam("name") String text,
			@RequestParam("paises") String idpais,
			@RequestParam("states") String states) {
		logger.info("entro tabs");
		logger.info("parametro caja ::" + text + "combocountry eleg :" + idpais
				+ " combocities eleg:" + states);

		return "tabs";
	}


	@RequestMapping(value = "/loadcities")
	public @ResponseBody
	Map<String, Object> getCiudades(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "idpais", required = true) String idcity)
			throws Exception {
		logger.info("cargando lista ciudades..");
		logger.info("parametro combo ::" + idcity);
		logger.info("idp >>" + request.getParameter("idpais"));
		Map<String, Object> map = new HashMap<String, Object>();

		List<City> cities = new ArrayList<City>();
		cities = service.getEstados(idcity);

		map.put("lstStates", cities);

		return map;
	}

}
