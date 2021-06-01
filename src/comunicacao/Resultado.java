package comunicacao;

public class Resultado extends Comunicado{
	private String retorno;
	
	public Resultado(String retorno) {
		this.retorno = retorno;
	}
	
	public String getRetorno() {
		return this.retorno;
	}
	
	public String toString() {
		return this.retorno;
	}
}
