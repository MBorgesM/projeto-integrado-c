package comunicacao;

public class PedidoRemocao extends PedidoLabirinto {

	public PedidoRemocao(String emailCliente, String nomeLabirinto) throws Exception {
		super(emailCliente, nomeLabirinto);
	}
	
	public PedidoRemocao(PedidoRemocao modelo) throws Exception {
		super(modelo);
	}
    
}
