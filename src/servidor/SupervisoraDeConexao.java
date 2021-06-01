package servidor;

import java.io.*;
import java.net.*;
import java.util.*;
import comunicacao.*;
import bd.daos.*;

public class SupervisoraDeConexao extends Thread {
    private Parceiro usuario;
    private Socket conexao;
    private ArrayList<Parceiro> usuarios;

    public SupervisoraDeConexao(Socket conexao, ArrayList<Parceiro> usuarios) throws Exception {
        if (conexao==null) {
            throw new Exception ("Conexao ausente");
        }

        if (usuarios==null) {
            throw new Exception ("Usuarios ausentes");
        }

        this.conexao  = conexao;
        this.usuarios = usuarios;
    }

    public void run() {

        ObjectOutputStream transmissor;
        try {
            transmissor = new ObjectOutputStream(this.conexao.getOutputStream());
            transmissor.flush();
        }
        catch (Exception erro) {
            return;
        }
        
        ObjectInputStream receptor=null;
        try {
            receptor = new ObjectInputStream(this.conexao.getInputStream());
        }
        catch (Exception erro) {
            try {
                transmissor.close();
            }
            catch (Exception falha) {} // so tentando fechar antes de acabar a thread
            
            return;
        }

        try {
            this.usuario = new Parceiro(this.conexao, receptor, transmissor);
        }
        catch (Exception erro) {} // sei que passei os parametros corretos

        try {
            synchronized(this.usuarios) {
                this.usuarios.add(this.usuario);
            }

            for(;;) {
                Comunicado comunicado = this.usuario.envie();

                if (comunicado==null) {
                	return;                	
                } else if (comunicado instanceof PedidoSalvamento) {
                	Labirinto labirinto = ((PedidoSalvamento) comunicado).getLabirinto();
                	
                	Labirintos.cadastrar(labirinto);
		        } else if (comunicado instanceof PedidoListagem) {
		        	String email = ((PedidoListagem) comunicado).getEmailCliente();
		        	
		        	String lista = Labirintos.listar(email);
		        	Resultado retorno = new Resultado(lista);
		        	
		        	this.usuario.receba(retorno);
                } else if (comunicado instanceof PedidoLabirinto) {
                	String nome = ((PedidoLabirinto) comunicado).getNomeLabirinto();
                	String email = ((PedidoLabirinto) comunicado).getEmailCliente();
                	
                	String labirinto = Labirintos.recuperar(nome, email);
                	Resultado retorno = new Resultado(labirinto);
                	
                	this.usuario.receba(retorno);
                } else if (comunicado instanceof PedidoRemocao) {
                	String nome = ((PedidoRemocao) comunicado).getNomeLabirinto();
                	String email = ((PedidoRemocao) comunicado).getEmailCliente();
                	
                	Labirintos.deletar(nome, email);
                } else if (comunicado instanceof PedidoDesligamento) {
                	synchronized (this.usuarios) {
                		this.usuarios.remove(this.usuario);
                	}
                	this.usuario.finalizaConexao();
                }
            }
        }
        catch (Exception erro) {
            try {
                transmissor.close();
                receptor.close();
            }
            catch (Exception falha) {} // so tentando fechar antes de acabar a thread

            return;
        }
    }
}
