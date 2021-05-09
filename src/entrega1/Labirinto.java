package entrega1;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Labirinto {
	private FileReader arquivo;
	private char[][] matriz;
    private int nLinhas;
    private int nColunas;
    private int lEntrada;
    private int cEntrada;
    private int lSaida;
    private int cSaida;
    
    /**
     * Construtor da classe labirinto
     * @param arquivo Endereço do arquivo txt onde o labirinto está armazenado
     * @throws FileNotFoundException Caso o endereço passado não exista
     */
    public Labirinto(String arquivo) throws FileNotFoundException {
    	this.nColunas = 0;
        this.lEntrada = -1;
        this.cEntrada = -1;
        this.lSaida = -1;
        this.cSaida = -1;
        this.arquivo = new FileReader(arquivo);

		criaLabirinto();
    }

	public void criaLabirinto() {
		Scanner reader = new Scanner(arquivo);
	    nLinhas = Integer.parseInt(reader.nextLine());	
	    System.out.println(nLinhas);
	    matriz = new char[nLinhas][];
	    
	    //Leitura e construcao da matriz
	    for (int i = 0; i < nLinhas; i++) {
	    	String linha = reader.nextLine();
	        if (i == 0) {
	        	nColunas = linha.length();	    	        
	    	    matriz = new char[nLinhas][nColunas];
	        }
	        if (linha.length() != nColunas) {
	        	System.out.println("Numero inconsistente de colunas, encerrando execucao...");
	        	System.exit(1);
	        }
	        char[] c = linha.toCharArray();
	        for (int j = 0; j < nColunas; j++) {
	        	if (c[j] != '#' &&
	        		c[j] != 'E' &&
	        		c[j] != 'S' &&
	        		c[j] != ' ') {
	        		System.out.println("Caractere invalido, encerrando execucao...");
	        		System.exit(1);
	        	}
	        	if (c[j] == 'E') {
	        		lEntrada = i;
	        		cEntrada = j;
	        	} else if (c[j] == 'S') {
	        		lSaida = i;
	        		cSaida = j;
	        	}
	        	matriz[i][j] = c[j];
	        }
	    }
	    reader.close();
	    
	    //Validacao da entrada e saida do labirinto
	    if (lEntrada == -1 ||
	    	cEntrada == -1 ||
	    	lSaida == -1 ||
	    	cSaida == -1) {
	    	System.out.println("O labirinto precisa conter uma entrada e uma saida valida");
    		System.exit(1);
	    }
	}
    
    public void executaLabirinto() {
		Pilha<Integer> caminho = new Pilha<Integer>(nLinhas, nColunas);
		Pilha<Integer> adjacentes = new Pilha<Integer>(nLinhas, nColunas);
		Pilha<Integer> possibilidades = new Pilha<Integer>(nLinhas, nColunas);
		encontraCaminho(lEntrada, cEntrada, nLinhas, nColunas, caminho, possibilidades, adjacentes);
			
		System.out.println(this);
			
		try {
			System.out.println(caminho);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Modo progressivo do algoritmo de resolução de labirintos
	 * @param matriz Matriz que armazena todos os caracteres do labirinto
	 * @param lEntrada Posição no eixo X da entrada
	 * @param cEntrada Posição no eixo Y da entrada
	 * @param nLinhas Número de linhas do labirinto
	 * @param nColunas Número de colunas do labirinto
	 * @param caminho Pilha que armazena todas as coordenadas percorridas
	 * @param possibilidades Pilha que armazena todas as coordenadas que possuem caminhos mas não forma seguidas
	 * @param adjacentes Pilha que armazena todos os possíveis caminhos
	 */
	private void encontraCaminho(int lEntrada, int cEntrada, int nLinhas, int nColunas, Pilha<Integer> caminho, Pilha<Integer> possibilidades, Pilha<Integer> adjacentes) {
		int lAtual = lEntrada;
		int cAtual = cEntrada;
		int qtdAdjacentes = -1;
		boolean verificador = false;
		
		while (!verificador) {
			//Modo progressivo
			while (qtdAdjacentes != 0) {
				qtdAdjacentes = existeAdjacentes(matriz, lAtual, cAtual, nLinhas, nColunas, adjacentes);
				if (qtdAdjacentes != 0) {
					try {
						lAtual = adjacentes.topX();
						cAtual = adjacentes.topY();
						caminho.push(lAtual, cAtual);
						matriz[lAtual][cAtual] = '*';
						adjacentes.pop();
						while (!adjacentes.isVazia()) {
							possibilidades.push(adjacentes.topX(), adjacentes.topY());
							adjacentes.pop();
						}
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
				if (saida(matriz, lAtual, cAtual)) {
					verificador = true;
					qtdAdjacentes = 0;
				} 
			}
			//Verifica se o programa ja chegou ao fim do labirinto
			//Modo regressivo
			if(!verificador) {
				regressivo(matriz, caminho, possibilidades);
				try {
					lAtual = caminho.topX();
					cAtual = caminho.topY();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
			qtdAdjacentes = -1;
		}
		
	}
	
	/**
	 * Verifica quais os caminhos disponíveis para serem percorridos
	 * @param matriz Matriz que armazena todos os caracteres do labirinto
	 * @param lAtual Linha atual
	 * @param cAtual Coluna atual
	 * @param nLinhas Número de linhas do labirinto
	 * @param nColunas Número de colunas do labirinto
	 * @param adjacentes Quantidade de caminhos disponíveis para serem percorridos
	 * @return qtdAdjacentes Quantidade de caminhos disponíveis para serem percorridos
	 */
	private int existeAdjacentes(char[][] matriz, int lAtual, int cAtual, int nLinhas, int nColunas, Pilha<Integer> adjacentes) {
		int qtdAdjacentes = 0;

		if (lAtual == 0) {
			if (matriz[lAtual + 1][cAtual] == ' ') {
				qtdAdjacentes++;
				try {
					adjacentes.push(lAtual + 1, cAtual);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		} else if (lAtual + 1 == nLinhas) {
			if (matriz[lAtual - 1][cAtual] == ' ') {
				qtdAdjacentes++;
				try {
					adjacentes.push(lAtual - 1, cAtual);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		} else if (cAtual == 0) {
			if (matriz[lAtual][cAtual + 1] == ' ') {
				qtdAdjacentes++;
				try {
					adjacentes.push(lAtual, cAtual + 1);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		} else if (cAtual + 1 == nColunas) {
			if (matriz[lAtual][cAtual - 1] == ' ') {
				qtdAdjacentes++;
				try {
					adjacentes.push(lAtual, cAtual - 1);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		} else {
			if (matriz[lAtual][cAtual - 1] == ' ') {
				qtdAdjacentes++;
				try {
					adjacentes.push(lAtual, cAtual - 1);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
			if (matriz[lAtual - 1][cAtual] == ' ') {
				qtdAdjacentes++;
				try {
					adjacentes.push(lAtual - 1, cAtual);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
			if (matriz[lAtual + 1][cAtual] == ' ') {
				qtdAdjacentes++;
				try {
					adjacentes.push(lAtual + 1, cAtual);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
			if (matriz[lAtual][cAtual + 1] == ' ') {
				qtdAdjacentes++;
				try {
					adjacentes.push(lAtual, cAtual + 1);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}
		
		return qtdAdjacentes;
	}
	
	/**
	 * Verifica se o labirinto chegou ao fim
	 * @param matriz Matriz que armazena todos os caracteres do labirinto
	 * @param lAtual Linha atual
	 * @param cAtual Coluna atual
	 * @return True se a saída está adjacente ao caminho percorrido, senão retorna false
	 */
	private boolean saida(char[][] matriz, int lAtual, int cAtual) {
		if (matriz[lAtual][cAtual + 1] == 'S' || 
			matriz[lAtual][cAtual - 1] == 'S' ||
			matriz[lAtual + 1][cAtual] == 'S' ||
			matriz[lAtual - 1][cAtual] == 'S') {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Implementa o modo regressivo
	 * @param matriz Matriz que armazena todos os caracteres do labirinto
	 * @param caminho Pilha que armazena todas as coordenadas percorridas
	 * @param possibilidades Pilha que armazena todas as coordenadas que possuem caminhos mas não forma seguidas
	 */
	private void regressivo(char[][] matriz, Pilha<Integer> caminho, Pilha<Integer> possibilidades) {
		int metaX;
		int metaY;
		boolean verificador = false;
		
		try {
			metaX = possibilidades.topX();
			metaY = possibilidades.topY();
			possibilidades.pop();
			while (verificador == false) {
				if ((caminho.topX() == metaX && caminho.topY() + 1 == metaY) ||
					(caminho.topX() == metaX && caminho.topY() - 1 == metaY) ||
					(caminho.topX() + 1 == metaX && caminho.topY() == metaY) ||
					(caminho.topX() - 1 == metaX && caminho.topY() == metaY)) {
					
					caminho.push(metaX, metaY);
					matriz[caminho.topX()][caminho.topY()] = '*';
					verificador = true;
				} else {
					matriz[caminho.topX()][caminho.topY()] = ' ';
					caminho.pop();
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public String toString() {
		String ret = "";

		for (int i = 0; i < nLinhas; i++) {
			for (int j = 0; j < nColunas; j++) {
				ret += matriz[i][j];
			}
			ret += "\n";
		}

		return ret;
	}
}
