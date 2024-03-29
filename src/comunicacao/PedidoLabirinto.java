package comunicacao;

public class PedidoLabirinto extends Comunicado {
    private String emailCliente;
    private String nomeLabirinto;

    /**
     * Construtor padr�o da classe PedidoLabirinto
     * @param emailCliente Email do Cliente
     * @throws Exception Se o email estiver vazio
     */
    public PedidoLabirinto(String emailCliente, String nomeLabirinto) throws Exception{
        if (emailCliente == null) {
            throw new Exception ("Email inv�lido");
        }
        
        if (nomeLabirinto == null) {
        	throw new Exception ("Nome do Labirinto inv�lido");
        }
        this.emailCliente = emailCliente;
        this.nomeLabirinto = nomeLabirinto;
    }

    /**
     * Getter do email do cliente
     * @return email do cliente
     */
    public String getEmailCliente() {
        return this.emailCliente;
    }
    
    /**
     * Getter do nome do Labirinto
     * @return nome do labirinto
     */
    public String getNomeLabirinto() {
    	return this.nomeLabirinto;
    }

    /**
     * M�todo obrigat�rio toString
     */
    @Override
    public String toString() {
        return "Email do Cliente: " + this.emailCliente + "\nNome do Labirinto: " + this.nomeLabirinto;
    }

    /**
     * M�todo obrigat�rio equals
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;

        if (obj.getClass() != PedidoLabirinto.class) return false;

        PedidoLabirinto pedido = (PedidoLabirinto) obj;

        if (!(this.emailCliente.equals(pedido.emailCliente))) return false;
        if (!(this.nomeLabirinto.equals(pedido.emailCliente))) return false;

        return true;
    }

    /**
     * M�todo obrigat�rio hashCode
     */
    @Override
    public int hashCode() {
        int ret = 2000;

        ret = 13*ret + this.emailCliente.hashCode();
        ret = 13*ret + this.nomeLabirinto.hashCode();

        return ret;
    }

    /**
     * Construtor de c�pias da classe PedidoLabirinto
     * @param modelo PedidoLabirinto que servir� de modelo para a c�pia
     * @throws Exception Se o modelo for nulo
     */
    public PedidoLabirinto(PedidoLabirinto modelo) throws Exception {
        if (modelo == null) {
            throw new Exception ("Modelo ausente");
        }
        this.emailCliente = modelo.emailCliente;
        this.nomeLabirinto = modelo.nomeLabirinto;
    }

    /**
     * M�todo obrigat�rio clone
     */
    public Object clone() {
        PedidoLabirinto ret=null;
        
        try {
            ret = new PedidoLabirinto(this);
        } catch (Exception erro) {}
        
        return ret;
    }
}
