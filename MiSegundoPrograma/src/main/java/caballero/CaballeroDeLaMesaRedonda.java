package caballero;

public class CaballeroDeLaMesaRedonda implements Caballero{
	
	private String nombre;
	private Aventura aventura;
	
	//Inyeccion por constructor para nombre
	public CaballeroDeLaMesaRedonda(String nombre){
		this.nombre = nombre;		
	}
	
	//Inyeccion por setter para aventura
	public void setAventura(Aventura aventura){
		this.aventura = aventura;
	}
	
	public Object embarcarEnAventura(){
		return aventura.embarcar();
	}

}
