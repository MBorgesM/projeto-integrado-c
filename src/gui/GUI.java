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
	 * Construtor padr�o da interface gr�fica
	 */
	public GUI() {
		
		criaJanela();
		criaEditor();
		criaConsole();
		criaMenu();
		
		janela.setVisible(true);
	}
	
	/**
	 * Cria a janela que ser� exibida na execu��o do programa
	 */
	public void criaJanela() {
		janela = new JFrame("Editor de Labirintos");
		
		//Define o tamanho e formato da janela
		janela.getContentPane().setLayout(new BorderLayout());
		janela.setSize(800, 600);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Cria a �rea de edi��o dos labirintos
	 */
	public void criaEditor() {
		editor = new JTextArea();
		
		//Altera a fonte padr�o da janela para melhorar a visibilidade do labirinto
		Font font = new Font("Courier New", Font.PLAIN, 16);
		editor.setFont(font);
		
		//Cria e adiciona um Scroll din�mico para melhorar a visibilidade da janela
		scrollEditor = new JScrollPane(editor, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
										 JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		janela.add(scrollEditor);
	}
	
	/**
	 * Cria a �rea de Log e a insere na parte inferior da janela
	 */
	public void criaConsole() {
		console = new JTextArea(8, 200);
		
		console.setEditable(false); //Impede o usu�rio de digitar no Log
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
	 * Cria os bot�es de fun��es e insere-os na parte superior da janela
	 */
	public void criaMenu() {
		String texto[] = {"Novo", "Editar", "Resolver", "Salvar" };
		
		//Adiciona o texto e os eventos para cada botão
		for (int i=0; i<this.botao.length; i++) {
            this.botao[i] = new JButton(texto[i]);
            this.botao[i].addActionListener(this);
            this.botao[i].setActionCommand(texto[i]);
            botoes.add(this.botao[i]);
        }
		
		janela.add(botoes, BorderLayout.NORTH);
	}

	/**
	 * Executa as fun��es baseando-se no bot�o pressionado
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
					this.console.append("Labirinto inv�lido" + "\n");
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
