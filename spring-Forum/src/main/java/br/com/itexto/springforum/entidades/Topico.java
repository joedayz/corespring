package br.com.itexto.springforum.entidades;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity @Table(name="topico")
public class Topico implements java.io.Serializable {
	
	@Id @Generated(GenerationTime.INSERT) 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true) 
	protected long id;
	
	public long getId() {return id;}
	public void setId(long valor) {this.id = valor;}
	
	@ManyToOne @JoinColumn(name="tema_id", nullable=false)
	private Tema tema;
	@ManyToOne @JoinColumn(name="autor_id", nullable=false)
	private Usuario autor;
	@Column(name="fecha_publicacion", nullable=false) @Temporal(TemporalType.TIMESTAMP)
	private Date fechaPublicacion = new Date();
	@Column(name="titulo", nullable=false, length=128)
	private String titulo;


	
	
	public Tema getTema() {
		return tema;
	}
	public void setTema(Tema tema) {
		this.tema = tema;
	}
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}



	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
}
