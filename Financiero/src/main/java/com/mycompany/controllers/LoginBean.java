package com.mycompany.controllers;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.mycompany.model.Usuario;

@Named
@RequestScoped
public class LoginBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Usuario usuario;

	private String nombreUsuario;
	private String password;

	public String login(){
		FacesContext context = 
				FacesContext.getCurrentInstance();
		
		if("admin".equals(this.nombreUsuario)
		 && "123".equals(this.password)){
			 this.usuario.setNombre(this.nombreUsuario);
			 this.usuario.setFechaLogin(new Date());
			 
			 return "/ConsultaMovimientos?faces-redirect=true";
			 
		 }else{
			 FacesMessage mensaje = new FacesMessage(
					 "Usuario/Password invalidos!");
			 mensaje.setSeverity(FacesMessage.
					 SEVERITY_ERROR);
			 context.addMessage(null, mensaje);
		 }
		 return null;
	}
	
	
	public String logout(){
		FacesContext.getCurrentInstance().
		getExternalContext().invalidateSession();
		return "/Login?faces-redirect=true";
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

}
