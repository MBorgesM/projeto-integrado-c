package cliente;

import java.net.*;
import java.io.*;
import comunicacao.*;

public class Cliente {
	public static final String HOST_PADRAO = "localhost";
	public static final int PORTA_PADRAO = 3000;
	
	public static void main(String[] args) throws Exception {
		if (args.length > 2) {
			throw new Exception ("Uso esperado: java Cliente [HOST [ PORTA]]\n");
		}
		
		Socket conexao = null;
		try {
			String host = Cliente.HOST_PADRAO;
			int porta = Cliente.PORTA_PADRAO;
			
			if (args.length > 0) {
				host = args[0];
			}
			if (args.length == 2) {
				porta = Integer.parseInt(args[1]);
			}
			conexao = new Socket(host, porta);
		} catch (Exception erro) {
			erro.printStackTrace();
			throw new Exception (erro.getMessage());
		}
		
		ObjectOutputStream transmissor = null;
		try {
			transmissor = new ObjectOutputStream(conexao.getOutputStream());
			transmissor.flush();
		} catch (Exception erro) {
			System.out.println(erro.getMessage());
		}
		
		ObjectInputStream receptor = null;
		try {
			InputStream inputStream = conexao.getInputStream();
			receptor = new ObjectInputStream(inputStream);
		} catch (Exception erro) {
			erro.printStackTrace();
			System.out.println(erro.getMessage());
		}
		
		Parceiro servidor = null;
		try {
			servidor = new Parceiro(conexao, receptor, transmissor);
		} catch (Exception erro) {
			System.out.println(erro.getMessage());
		}
		
		System.out.println("Conectado");
	}
}
