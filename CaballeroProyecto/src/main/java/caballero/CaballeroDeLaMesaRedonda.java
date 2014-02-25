package caballero;

public class CaballeroDeLaMesaRedonda implements Caballero{

	private String nombre;
	private Aventura aventura;
	
	public CaballeroDeLaMesaRedonda(String nombre){
		this.nombre = nombre;
		
	}

	
	public Object embarcarEnAventura() 
				throws AventuraFailedException {
		System.out.println("a embarcarse en la aventura");
		return aventura.embarcar();
	}


	public void setAventura(Aventura aventura) {
		this.aventura = aventura;
	}


	public String getNombre() {
		return this.nombre;
	}
	
	
}
