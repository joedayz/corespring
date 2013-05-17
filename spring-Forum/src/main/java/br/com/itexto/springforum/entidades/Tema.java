package br.com.itexto.springforum.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;


@Entity @Table(name="tema")
public class Tema  implements Serializable, Comparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6538970689060453620L;
	
	
	@Id @Generated(GenerationTime.INSERT)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true)
	protected long id;
	
	
	
	
	@Column(name="nombre", unique=true, length=128)
	private String nombre;
	
	
	public Tema() {}
	

	public Tema(long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}





	public int compareTo(Object o) {
		if (o instanceof Tema){
			return getNombre().compareTo(((Tema) o).getNombre());
		}else{
			return 0;
		}
	}





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
	
	
	

}
