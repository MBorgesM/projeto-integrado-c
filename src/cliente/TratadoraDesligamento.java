package cliente;

import comunicacao.*;

public class TratadoraDesligamento extends Thread {
	private Parceiro servidor;
	
	public TratadoraDesligamento(Parceiro servidor) throws Exception {
		if (servidor == null) {
			throw new Exception("Porta inv�lida");
		}
		
		this.servidor = servidor;
	}
	
	public void run() {
		for (;;) {
			try {
				if (this.servidor.espie() instanceof PedidoDesligamento) {
					System.exit(0);
				}
			} catch (Exception erro) {}
		}
	}
}
