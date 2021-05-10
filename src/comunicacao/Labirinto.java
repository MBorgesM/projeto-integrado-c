package comunicacao;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Labirinto {
    private String nome;
    private String dataCriacao;
    private String dataUltimaModificacao;
    private String conteudo;
    private String criador;

    /**
     * Construtor padrão do Labirinto que será usado na comunicação entre cliente e servidor
     * @param nome Nome do Labirinto
     * @param labirinto Labirinto completo em formato de String
     * @param ip IP do criador do labirinto
     * @throws Exception Se algum campo estiver vazio
     */
    public Labirinto (String nome, String labirinto, InetAddress ip) throws Exception {
        if (nome == null || labirinto == null || ip == null) {
            throw new Exception ("Todos os campos devem ser válidos");
        }
        this.nome = nome;
        this.conteudo = labirinto;
        this.criador = ip.getHostAddress();
        setDataCriacao();
        this.dataUltimaModificacao = this.dataCriacao;
    }

    /**
     * Setter do nome do labirinto
     * @param nome Nome do Labirinto que será salvo
     */
    public void setNome(String nome) {
        this.nome = nome;
        setDataModificacao();
    }

    /**
     * Setter do conteudo
     * @param labirinto Labirinto completo em formato de String
     * @param mod Data em que a modificação do labirinto está sendo feita
     */
    public void setConteudo(String labirinto) {
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
    private void setDataModificacao() {
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
     * Getter do conteúdo do labirinto
     * @return Conteúdo do labirinto
     */
    public String getConteudo() {
        return this.conteudo;
    }

    /**
     * Getter do IP do criador do labirinto
     * @return IP do criador do labirinto
     */
    public String getCriador() {
        return this.criador;
    }

    /**
     * Método obrigatório toString
     */
    @Override
    public String toString() {
        String ret = "";

        ret += "IP do Criador do Labirinto: " + this.criador + "\n" +
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
        if (!(this.dataCriacao.equals(lab.dataCriacao))) return false;
        if (!(this.dataUltimaModificacao.equals(lab.dataUltimaModificacao))) return false;
        if (!(this.conteudo.equals(lab.conteudo))) return false;
        if (!(this.criador.equals(lab.criador))) return false;

        return true;
    }

    /**
     * Método obrigatório hashCode
     */
    @Override
    public int hashCode() {
        int ret = 2000;

        ret = 13*ret + this.nome.hashCode();
        ret = 13*ret + this.dataCriacao.hashCode();
        ret = 13*ret + this.dataUltimaModificacao.hashCode();
        ret = 13*ret + this.conteudo.hashCode();
        ret = 13*ret + this.criador.hashCode();

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
        this.dataCriacao = modelo.dataCriacao;
        this.dataUltimaModificacao = modelo.dataUltimaModificacao;
        this.conteudo = modelo.conteudo;
        this.criador = modelo.criador;
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
