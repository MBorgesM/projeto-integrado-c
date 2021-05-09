package comunicacao;

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
     * @param nome Nome do Labirinto que será salvo
     * @param data Data da criação do labirinto
     * @param labirinto Labirinto completo em formato de String
     * @param ip IP do criador do labirinto
     */
    public Labirinto (String nome, String labirinto, String ip) {
        this.nome = nome;
        this.conteudo = labirinto;
        this.criador = ip;
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
     * Setter da data de criação
     */
    private void setDataCriacao() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime dataAtual = LocalDateTime.now();  
		this.dataCriacao = formatador.format(dataAtual);
    }

    /**
     * Setter da data de modificação
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

    public String toString() {
        String ret = "";

        ret += "Criador do Labirinto: " + this.criador + "\n" +
               "Nome do labirinto: " + this.nome + "\n" +
               "Data da criação do labirinto: " + this.dataCriacao + "\n" +
               "Data da última modificação do labirinto: " + this.dataUltimaModificacao + "\n" +
               "Labirinto: " + "\n" + this.conteudo + "\n";

        return ret;
    }
}
