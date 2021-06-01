package servidor;

import java.net.*;
import java.util.*;
import comunicacao.*;

public class AceitadoraDeConexao extends Thread {
	private ServerSocket pedido;
	private ArrayList<Parceiro> usuarios;
	
	public AceitadoraDeConexao(String porta, ArrayList<Parceiro> usuarios) throws Exception {
		if (usuarios==null) {
			throw new Exception ("Usuarios ausentes");
		}
		this.usuarios = usuarios;
		
		if (porta == null) {
			throw new Exception ("Porta ausente");
		}
		try {
			this.pedido = new ServerSocket (Integer.parseInt(porta));
		} catch (Exception  erro) {
			throw new Exception ("Porta invalida");
		}
	}

	public void run() {
		for (;;) {
			Socket conexao = null;
			try {
				conexao = this.pedido.accept();
			} catch (Exception erro) {
				continue;
			}
			
			SupervisoraDeConexao supervisoraDeConexao = null;
			try {
				supervisoraDeConexao = new SupervisoraDeConexao(conexao, usuarios);
			} catch (Exception erro) {}
			supervisoraDeConexao.start();
		}
	}
}
