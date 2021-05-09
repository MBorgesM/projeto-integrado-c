package entrega1;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Programa {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		String arquivo;
		
		System.out.printf("Insira o endereço completo do arquivo: ");
		arquivo = teclado.nextLine();
		teclado.close();
		
		try {
			Labirinto labirinto = new Labirinto(arquivo);
			labirinto.executaLabirinto();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não existe");
		}
	}
}
