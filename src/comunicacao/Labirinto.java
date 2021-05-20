package comunicacao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Labirinto extends Comunicado {
	private String nome;
    private String criador;
    private String conteudo;
    private String dataCriacao;
    private String dataUltimaModificacao;

    /**
     * Construtor padr�o do Labirinto que ser� usado na comunica��o entre cliente e servidor
     * @param nome Nome do Labirinto
     * @param labirinto Labirinto completo em formato de String
     * @param email Email do criador do labirinto
     * @throws Exception Se algum campo estiver vazio
     */
    public Labirinto(String nome, String email, String labirinto) throws Exception {
        setNome(nome);
        setCriador(email);
        setConteudo(labirinto);
        setDataCriacao();
        this.dataUltimaModificacao = this.dataCriacao;
    }

    /**
     * Setter do nome do labirinto
     * @param nome Nome do Labirinto que ser� salvo
     * @throws Exception Se o nome for nulo
     */
    public void setNome(String nome) throws Exception {
        if (nome == null || nome.length() > 40) {
            throw new Exception("Nome inv�lido");
        }

        this.nome = nome;
        setDataModificacao();
    }

    /**
     * Setter do email do criador do labirinto, restrito ao construtor da classe
     * @param email Email do criador do labirinto
     * @throws Exception Se a string n�o terminar com "@gmail.com"
     */
    private void setCriador(String email) throws Exception {
        if (!email.endsWith("@gmail.com") || email.length() > 60) {
            throw new Exception("Email inv�lido");
        }

        this.criador = email;
    }

    /**
     * Setter do conteudo
     * @param labirinto Labirinto completo em formato de String
     * @param mod Data em que a modifica��o do labirinto est� sendo feita
     * @throws Exception Se o labirinto for nulo
     */
    public void setConteudo(String labirinto) throws Exception {
    	if (labirinto == null) {
    		throw new Exception("Labirinto Inv�lido");
    	}
        this.conteudo = labirinto;
        setDataModificacao();
    }

    /**
     * Setter da data de cria��o, restrito ao construtor
     */
    private void setDataCriacao() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime dataAtual = LocalDateTime.now();  
		this.dataCriacao = formatador.format(dataAtual);
    }

    /**
     * Setter da data de modifica��o, restrito a altera��es dos outros atributos da Classe
     */
    public void setDataModificacao() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime dataAtual = LocalDateTime.now();  
		this.dataUltimaModificacao = formatador.format(dataAtual);
	}

    /**
     * Getter do nome do labirinto
     * @return Nome do labirinto
     */
    public String getNome() {
        return this.nome;
    }
    
    /**
     * Getter do Email do criador do labirinto
     * @return Email do criador do labirinto
     */
    public String getCriador() {
        return this.criador;
    }
    
    /**
     * Getter do conte�do do labirinto
     * @return Conte�do do labirinto
     */
    public String getConteudo() {
        return this.conteudo;
    }

    /**
     * Getter da data de cria��o do labirinto
     * @return Data de cria��o
     */
    public String getDataCriacao() {
        return this.dataCriacao;
    }

    /**
     * Getter da data da ultima modifica��o do labirinto
     * @return Data de modifica��o
     */
    public String getDataUltimaModificacao() {
        return this.dataUltimaModificacao;
    }

    /**
     * M�todo obrigat�rio toString
     */
    @Override
    public String toString() {
        String ret = "";

        ret += "Email do Criador do Labirinto: " + this.criador + "\n" +
               "Nome do labirinto: " + this.nome + "\n" +
               "Data da cria��o do labirinto: " + this.dataCriacao + "\n" +
               "Data da �ltima modifica��o do labirinto: " + this.dataUltimaModificacao + "\n" +
               "Labirinto: " + "\n" + this.conteudo + "\n";

        return ret;
    }

    /**
     * M�todo obrigat�rio equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;

        if (obj.getClass() != Labirinto.class) return false;

        Labirinto lab = (Labirinto) obj;

        if (!(this.nome.equals(lab.nome))) return false;
        if (!(this.criador.equals(lab.criador))) return false;
        if (!(this.dataCriacao.equals(lab.dataCriacao))) return false;
        if (!(this.dataUltimaModificacao.equals(lab.dataUltimaModificacao))) return false;
        if (!(this.conteudo.equals(lab.conteudo))) return false;

        return true;
    }

    /**
     * M�todo obrigat�rio hashCode
     */
    @Override
    public int hashCode() {
        int ret = 2000;

        ret = 13*ret + this.nome.hashCode();
        ret = 13*ret + this.criador.hashCode();
        ret = 13*ret + this.dataCriacao.hashCode();
        ret = 13*ret + this.dataUltimaModificacao.hashCode();
        ret = 13*ret + this.conteudo.hashCode();

        return ret;
    }

    /**
     * Construtor de c�pias da classe Labirinto
     * @param modelo Labirinto que servir� de modelo para a c�pia
     * @throws Exception Se o modelo for nulo
     */
    public Labirinto (Labirinto modelo) throws Exception {
        if (modelo == null) {
            throw new Exception ("Modelo ausente");
        }
        
        this.nome = modelo.nome;
        this.criador = modelo.criador;
        this.dataCriacao = modelo.dataCriacao;
        this.dataUltimaModificacao = modelo.dataUltimaModificacao;
        this.conteudo = modelo.conteudo;
    }

    /**
     * M�todo obrigat�rio clone
     */
    public Object clone() {
        Labirinto ret=null;
        
        try
        {
            ret = new Labirinto(this);
        } catch (Exception erro) {}
        
        return ret;
    }
}
