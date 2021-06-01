package servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import comunicacao.Parceiro;
import comunicacao.PedidoDesligamento;

public class Servidor {
	public static final String PORTA_PADRAO = "3000";
	private static BufferedReader teclado = new BufferedReader (new InputStreamReader (System.in));

	public static void main(String[] args) {
		if (args.length>1) { 
            System.err.println ("Uso esperado: java Servidor [PORTA]\n");
            return;
        }
		
		String porta = Servidor.PORTA_PADRAO;
		
		if (args.length == 1) {
			porta = args[0];
		}
		
		ArrayList<Parceiro> usuarios = new ArrayList<Parceiro> ();
		
		AceitadoraDeConexao aceitadoraDeConexao = null;
		try {
			aceitadoraDeConexao = new AceitadoraDeConexao(porta, usuarios);
			aceitadoraDeConexao.start();
		} catch (Exception erro) {
			System.err.println("Escolha uma porta apropriada");
			return;
		}
		
		for (;;) {
			System.out.println("O servidor esta ativo! Para desativa-lo,");
            System.out.println("use o comando \"desativar\"\n");
            System.out.print("> ");
            
            String comando = null;
            try {
            	comando = teclado.readLine();
            } catch (Exception erro) {}
            
            if (comando.toLowerCase().equals("desativar")) {
                synchronized (usuarios) {
					PedidoDesligamento comunicadoDeDesligamento = new PedidoDesligamento();
                    
                    for (Parceiro usuario : usuarios) {
                        try {
                            usuario.receba(comunicadoDeDesligamento);
                            usuario.finalizaConexao();
                        }
                        catch (Exception erro) {}
                    }
                }
                
                System.out.println("O servidor foi desativado!\n");
                System.exit(0);
            }
            else {
            	System.out.println("Comando inválido!\n");
            }
		}
	}
}
