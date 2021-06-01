import bd.daos.Labirintos;
import comunicacao.*;

public class Teste {
	public static void main(String[] args) {
		String nome = "aaaaaaa";
		String email = "email2@gmail.com";
		String conteudo = "###\nE S\n###";
		
		try {
			Labirinto teste = new Labirinto(nome, email, conteudo);
			Labirintos.cadastrar(teste);
		} catch (Exception erro) {
			System.out.println(erro.getMessage());
		}
	}
}
