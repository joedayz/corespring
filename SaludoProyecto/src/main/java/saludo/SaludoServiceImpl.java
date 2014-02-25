package saludo;

public class SaludoServiceImpl implements SaludoService {

	public String saludo;
	
	public SaludoServiceImpl(){
		
	}
	
	public SaludoServiceImpl(String saludo) {
		super();
		this.saludo = saludo;
	}



	public void saludar() {
		System.out.println(saludo);
	}

	public void setSaludo(String saludo) {
		this.saludo = saludo;
	}
	
	

}
