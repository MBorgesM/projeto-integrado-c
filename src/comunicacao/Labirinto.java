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
     * Construtor padrão do Labirinto que será usado na comunicação entre cliente e servidor
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
     * @param nome Nome do Labirinto que será salvo
     * @throws Exception Se o nome for nulo
     */
    public void setNome(String nome) throws Exception {
        if (nome == null || nome.length() > 40) {
            throw new Exception("Nome inválido");
        }

        this.nome = nome;
        setDataModificacao();
    }

    /**
     * Setter do email do criador do labirinto, restrito ao construtor da classe
     * @param email Email do criador do labirinto
     * @throws Exception Se a string não terminar com "@gmail.com"
     */
    private void setCriador(String email) throws Exception {
        if (!email.endsWith("@gmail.com") || email.length() > 60) {
            throw new Exception("Email inválido");
        }

        this.criador = email;
    }

    /**
     * Setter do conteudo
     * @param labirinto Labirinto completo em formato de String
     * @param mod Data em que a modificação do labirinto está sendo feita
     * @throws Exception Se o labirinto for nulo
     */
    public void setConteudo(String labirinto) throws Exception {
    	if (labirinto == null) {
    		throw new Exception("Labirinto Inválido");
    	}
        this.conteudo = labirinto;
        setDataModificacao();
    }

    /**
     * Setter da data de criação, restrito ao construtor
     */
    private void setDataCriacao() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime dataAtual = LocalDateTime.now();  
		this.dataCriacao = formatador.format(dataAtual);
    }

    /**
     * Setter da data de modificação, restrito a alterações dos outros atributos da Classe
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
     * Getter do conteúdo do labirinto
     * @return Conteúdo do labirinto
     */
    public String getConteudo() {
        return this.conteudo;
    }

    /**
     * Getter da data de criação do labirinto
     * @return Data de criação
     */
    public String getDataCriacao() {
        return this.dataCriacao;
    }

    /**
     * Getter da data da ultima modificação do labirinto
     * @return Data de modificação
     */
    public String getDataUltimaModificacao() {
        return this.dataUltimaModificacao;
    }

    /**
     * Método obrigatório toString
     */
    @Override
    public String toString() {
        String ret = "";

        ret += "Email do Criador do Labirinto: " + this.criador + "\n" +
               "Nome do labirinto: " + this.nome + "\n" +
               "Data da criação do labirinto: " + this.dataCriacao + "\n" +
               "Data da última modificação do labirinto: " + this.dataUltimaModificacao + "\n" +
               "Labirinto: " + "\n" + this.conteudo + "\n";

        return ret;
    }

    /**
     * Método obrigatório equals
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
     * Método obrigatório hashCode
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
     * Construtor de cópias da classe Labirinto
     * @param modelo Labirinto que servirá de modelo para a cópia
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
     * Método obrigatório clone
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
