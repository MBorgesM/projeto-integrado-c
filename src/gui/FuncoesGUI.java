package gui;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import entrega1.Pilha;

public class FuncoesGUI {
	GUI gui;                      //Objeto da Classe GUI, utilizado para alterar os atributos da janela 
	String nomeArquivo = null;    //Nome do arquivo
	String enderecoArquivo = null;       //Diretório do arquivo
	String erro;                  //Mensagem de erro que é retornada no Log
	boolean novoLabirinto = true; //Verdadeiro se o labirinto foi criado através da GUI, falso se foi aberto externamente
    int nLinhas;
    int nColunas = 0;
    int lEntrada = -1;
    int cEntrada = -1;
    int lSaida = -1;
    int cSaida = -1;
    //Todos os inteiros são utilizados para resolver o labirinto
	
    /**
     * Construtor padrão
     * @param gui Objeto da Classe GUI que possui todos os atributos da janela
     */
	public FuncoesGUI(GUI gui) {
		this.gui = gui;
	}
	
	/**
	 * Evento do botão "Novo", limpa a janela e as variáveis de endereços
	 */
	public void novoLabirinto() {
		novoLabirinto = true;
		gui.editor.setText("");
		gui.janela.setTitle("Novo Labirinto");
		gui.console.setText("========================================"
        		+ "=================================================="
        		+ "=================================================="
        		+ "=================================================="
        		+ "=================================================="
        		+ "======================================\nLog:\n");
		
		nomeArquivo = null;
		enderecoArquivo = null;
	}
	
	/**
	 * Evento do botão "Editar", abre uma janela para que o usuário possa abrir um arquivo .txt e inserir seu conteúdo no editor
	 */
	public void editarLabirinto() {
		FileDialog fd = new FileDialog(gui.janela, "Editar", FileDialog.LOAD);
		fd.setFile("*.txt");
		fd.setVisible(true);
		
		if (fd.getFile() != null) {
			novoLabirinto = false;
			nomeArquivo = fd.getFile();
			enderecoArquivo = fd.getDirectory();
			gui.janela.setTitle(nomeArquivo);
			
			try {
				BufferedReader leitor = new BufferedReader(new FileReader(enderecoArquivo + nomeArquivo));
				leitor.readLine(); //Pula uma linha
				
				gui.editor.setText("");
				
				gui.console.setText("======================================="
		        		+ "=================================================="
		        		+ "=================================================="
		        		+ "=================================================="
		        		+ "=================================================="
		        		+ "======================================\nLog:\n");
				
				String linha = null;
				
				while((linha = leitor.readLine()) != null) {
					gui.editor.append(linha + "\n");
				}
				leitor.close();
			} catch(Exception e) {
				gui.console.append("Arquivo inválido\n");
			}
		} 
	}
	
	/**
	 * Evento do botão "Resolver"
	 * @throws IOException Caso não seja encontrado o arquivo
	 */
	public void resolverLabirinto() throws IOException {
		nLinhas = gui.editor.getLineCount();
		char[][] matriz = new char[nLinhas][];
		int i = 0;
		
		for (String linha : gui.editor.getText().split("\\n")) {
	        if (i == 0) {
	        	nColunas = linha.length();	
	    	    matriz = new char[nLinhas][nColunas];
	        }
	        char[] c = new char[linha.length()];
	        c = linha.toCharArray();
	        for (int j = 0; j < nColunas; j++) {
	        	if (c[j] == 'E') {
	        		lEntrada = i;
	        		cEntrada = j;
	        	} else if (c[j] == 'S') {
	        		lSaida = i;
	        		cSaida = j;
	        	}
	        	matriz[i][j] = c[j];
	        }
	        i++;
	    }
	    
		if (!validaLabirinto(matriz)) {
			gui.console.append(erro + "\n");
			return;
		}
		
		Pilha<Integer> caminho = new Pilha<Integer>(nLinhas, nColunas);
	    Pilha<Integer> adjacentes = new Pilha<Integer>(nLinhas, nColunas);
	    Pilha<Integer> possibilidades = new Pilha<Integer>(nLinhas, nColunas);
	    
	    encontraCaminho(matriz, caminho, possibilidades, adjacentes);
	    
	    gui.editor.setText("");
	    
	    //Se o labirinto foi aberto externamente, uma linha será descontada por conta da primeira linha com a quantidade de linhas do labirinto
	    if (novoLabirinto) {
	    	for (i = 0; i < nLinhas; i++) {
		        for (int j = 0; j < nColunas; j++) {
		        	gui.editor.append(String.valueOf(matriz[i][j]));
		        }
		        gui.editor.append("\n");
	 	    }
	    } else {
	    	for (i = 0; i < nLinhas - 1; i++) {
		        for (int j = 0; j < nColunas; j++) {
		        	gui.editor.append(String.valueOf(matriz[i][j]));
		        }
		        gui.editor.append("\n");
	 	    }
	    }
	    
	    while (!caminho.isVazia()) {
	    	try {
				gui.console.append(caminho.topX() + ", " + caminho.topY() + "\n");
				caminho.pop();
			} catch (Exception e) {
				gui.console.append(e.getMessage());
			}
	    }
	}

	/**
	 * Evento do botão "Salvar", abre uma janela para que o usuário possa salvar um labirinto válido em um arquivo .txt
	 * @throws IOException Caso não seja encontrado o arquivo
	 */
	public void salvarLabirinto() throws IOException {
		nLinhas = gui.editor.getLineCount();
		char[][] matriz = new char[nLinhas][];
		int i = 0;
		
		for (String linha : gui.editor.getText().split("\\n")) {
	        if (i == 0) {
	        	nColunas = linha.length();	
	    	    matriz = new char[nLinhas][nColunas];
	        }
	        char[] c = new char[linha.length()];
	        c = linha.toCharArray();
	        for (int j = 0; j < nColunas; j++) {
	        	if (c[j] == 'E') {
	        		lEntrada = i;
	        		cEntrada = j;
	        	} else if (c[j] == 'S') {
	        		lSaida = i;
	        		cSaida = j;
	        	}
	        	matriz[i][j] = c[j];
	        }
	        i++;
	    }
		
		if (!validaLabirinto(matriz)) {
			gui.console.append(erro + "\n");
			return;
		}
		
		FileDialog fd = new FileDialog(gui.janela, "Salvar", FileDialog.SAVE);
		fd.setFile("*.txt");
		fd.setVisible(true);
		
		if (fd.getFile() != null) {
			nomeArquivo = fd.getFile();
			enderecoArquivo = fd.getDirectory();
			gui.janela.setTitle(nomeArquivo);
		}
		
		try {
			FileWriter fw;
			
			//Insere a extensão correta no final do arquivo
			if (nomeArquivo.endsWith(".txt")) {
				fw = new FileWriter(enderecoArquivo + nomeArquivo);
			} else {
				fw = new FileWriter(enderecoArquivo + nomeArquivo + ".txt");
			}
			
			//Insere o número de linhas do labirinto na primeira linha
			if (novoLabirinto) {
				fw.write(nLinhas + "\n" + gui.editor.getText());
			} else {
				fw.write(nLinhas-1 + "\n" + gui.editor.getText());
			}
			
			fw.close();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Verifica se o labirinto está condizente com as regras do sistema
	 * @param matriz Matriz que armazena todos os caracteres do labirinto
	 * @return True se o labirinto está correto, senão retorna false
	 */
	public boolean validaLabirinto(char[][] matriz) {
		nLinhas = gui.editor.getLineCount();
		
		for (String linha : gui.editor.getText().split("\\n")) {
			char[] c = new char[linha.length()];
			if (linha.length() != nColunas) {
	        	erro = "Todas as linhas devem possuir o mesmo número de colunas";
	        	return false;
	        }
			c = linha.toCharArray();
			for (int j = 0; j < nColunas; j++) {
				if (c[j] != '#' &&
		        	c[j] != 'E' &&
		        	c[j] != 'S' &&
		        	c[j] != ' ') {
		        	erro = "Caractere \"" + c[j] + "\" inválido";
		        	return false;
		        }
			}
		}
		
		if (lEntrada == -1 ||
		    cEntrada == -1 ||
		    lSaida == -1 ||
		    cSaida == -1) {
		    erro = "O labirinto precisa conter uma entrada e uma saída válida";
	    	return false;
		}
		return true;
	}
	
	/**
	 * Modo progressivo do algoritmo de resolução de labirintos
	 * @param matriz Matriz que armazena todos os caracteres do labirinto
	 * @param caminho
	 * @param possibilidades
	 * @param adjacentes
	 */
	private void encontraCaminho(char[][] matriz, Pilha<Integer> caminho, Pilha<Integer> possibilidades, Pilha<Integer> adjacentes) {
		int lAtual = lEntrada;
		int cAtual = cEntrada;
		int qtdAdjacentes = -1;
		boolean verificador = false;
		
		while (verificador == false) {
			//Modo progressivo
			while (qtdAdjacentes != 0) {
				qtdAdjacentes = existeAdjacentes(matriz, lAtual, cAtual, adjacentes);
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
			if(verificador == false) {
				regressivo(matriz, caminho, possibilidades);
				try {
					lAtual = caminho.topX();
					cAtual = caminho.topY();
				} catch (Exception e) {}
			}
			qtdAdjacentes = -1;
		}
		
	}

	/**
	 * Modo regressivo do algoritmo de resolução de labirintos
	 * @param matriz
	 * @param caminho
	 * @param possibilidades
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

	/**
	 * Verifica se o labirinto chegou ao fim
	 * @param matriz
	 * @param lAtual
	 * @param cAtual
	 * @return true se a saída está adjacente ao caminho percorrido, senão retorna false
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
	 * Verifica quais os caminhos disponíveis para serem percorridos
	 * @param matriz
	 * @param lAtual
	 * @param cAtual
	 * @param adjacentes
	 * @return Quantidade de adjacentes
	 */
	private int existeAdjacentes(char[][] matriz, int lAtual, int cAtual, Pilha<Integer> adjacentes) {
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
}
