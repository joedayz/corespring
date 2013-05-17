package br.com.itexto.springforum.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity @Table(name="usuario")
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6511225584196613549L;

	@Id @Generated(GenerationTime.INSERT)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true)
	protected long id;
	
	@NotNull  @NotEmpty   // JSR 303
	@Column(name="nombre", nullable=false, length=128)
	private String nombre;
	
	@Email(message="Esto no es un email") @NotNull @NotEmpty
	@Column(name="email", nullable=false, length=128, unique=true)
	private String email;
	
	@NotNull
	@Column(name="fecha_publicacion", nullable=false) 
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPublicacion = new Date();
	
	
	@NotNull @NotEmpty
	@Size(min=8, max=32, message="Login debe ser entre 8 y 32 caracteres")
	@Column( name="login", nullable=false, unique=true, length=64)
	private String login;
	
	@Column( name="twitter", nullable=true, unique=true, length=64)
	private String twitter;
	
	private transient String password;
	
	@Column(name="ultimo_login", nullable=true) 
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimoLogin;

	@Column(name="hash_password", nullable=false, length=128) 
	private String hashPassword;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		setHashPassword(org.apache.commons.codec.digest.DigestUtils.sha256Hex(password));
		this.password = password;
	}

	public Date getUltimoLogin() {
		return ultimoLogin;
	}

	public void setUltimoLogin(Date ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}

	public String getHashPassword() {
		return hashPassword;
	}

	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", email=" + email
				+ ", fechaPublicacion=" + fechaPublicacion + ", login=" + login
				+ ", twitter=" + twitter + ", ultimoLogin=" + ultimoLogin
				+ ", hashPassword=" + hashPassword + "]";
	}
	
	
	
	
}
