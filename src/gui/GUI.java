package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUI implements ActionListener {
	public JFrame janela;
	public JTextArea editor;
	public JTextArea console;
	public JScrollPane scrollEditor;
	public JScrollPane scrollConsole;
	public JButton botao[] = new JButton[4];
	public JPanel botoes = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT)); 
	FuncoesGUI funcoes = new FuncoesGUI(this);
	
	public static void main(String[] args) {
		new GUI();
	}
	
	/**
	 * Construtor padrão da interface gráfica
	 */
	public GUI() {
		
		criaJanela();
		criaEditor();
		criaConsole();
		criaMenu();
		
		janela.setVisible(true);
	}
	
	/**
	 * Cria a janela que será exibida na execução do programa
	 */
	public void criaJanela() {
		janela = new JFrame("Editor de Labirintos");
		
		//Define o tamanho e formato da janela
		janela.getContentPane().setLayout(new BorderLayout());
		janela.setSize(800, 600);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Cria a área de edição dos labirintos
	 */
	public void criaEditor() {
		editor = new JTextArea();
		
		//Altera a fonte padrão da janela para melhorar a visibilidade do labirinto
		Font font = new Font("Courier New", Font.PLAIN, 16);
		editor.setFont(font);
		
		//Cria e adiciona um Scroll dinâmico para melhorar a visibilidade da janela
		scrollEditor = new JScrollPane(editor, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
										 JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		janela.add(scrollEditor);
	}
	
	/**
	 * Cria a área de Log e a insere na parte inferior da janela
	 */
	public void criaConsole() {
		console = new JTextArea(8, 200);
		
		console.setEditable(false); //Impede o usuário de digitar no Log
        console.setText("======================================="
        		+ "=================================================="
        		+ "=================================================="
        		+ "=================================================="
        		+ "=================================================="
        		+ "======================================\nLog:\n");
        
        scrollConsole = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				 JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        janela.add(scrollConsole, BorderLayout.SOUTH);
	}
	
	/**
	 * Cria os botões de funções e insere-os na parte superior da janela
	 */
	public void criaMenu() {
		String texto[] = {"Novo", "Editar", "Resolver", "Salvar" };
		
		//Adiciona o texto e os eventos para cada botÃ£o
		for (int i=0; i<this.botao.length; i++) {
            this.botao[i] = new JButton(texto[i]);
            this.botao[i].addActionListener(this);
            this.botao[i].setActionCommand(texto[i]);
            botoes.add(this.botao[i]);
        }
		
		janela.add(botoes, BorderLayout.NORTH);
	}

	/**
	 * Executa as funções baseando-se no botão pressionado
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		switch (comando) {
			case "Novo":
				funcoes.novoLabirinto();
				break;
			case "Editar":
				funcoes.editarLabirinto();
				break;
			case "Resolver":
				try {
					funcoes.resolverLabirinto();
				} catch (Exception f) {
					this.console.append("Labirinto inválido" + "\n");
				}
				break;
			case "Salvar": 
				try {
					funcoes.salvarLabirinto();
				} catch (Exception f) {
					this.console.append(f.getMessage() + "\n");
				}
				break;
		}
	}
}
