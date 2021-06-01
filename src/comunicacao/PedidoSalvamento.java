package comunicacao;

public class PedidoSalvamento extends Comunicado {
    private String emailCliente;
    private Labirinto labirinto;

    /**
     * Construtor padr�o da classe PedidoSalvamento
     * @param emailCliente Email do cliente
     * @param labirinto Labirinto que ser� salvo
     * @throws Exception Se o email ou o labirinto forem nulos
     */
    public PedidoSalvamento(String emailCliente, Labirinto labirinto) throws Exception {
        if (emailCliente == null) {
            throw new Exception("Email inv�lido");
        }
        if (labirinto == null) {
        	throw new Exception("Labirinto inv�lido");
        }
        this.emailCliente = emailCliente;
        this.labirinto = labirinto;
    }

    /**
     * Getter do email do cliente
     * @return email do cliente
     */
    public String getEmailCliente() {
        return this.emailCliente;
    }

    /** 
     * Getter do labirinto
     * @return Labirinto
    */
    public Labirinto getLabirinto() {
        return this.labirinto;
    }

    /**
     * M�todo obrigat�rio toString
     */
    @Override
    public String toString() {
        String ret = "";

        ret += "Email do Cliente: " + this.emailCliente + "\n" + this.labirinto;

        return ret;
    }

    /**
     * M�todo obrigat�rio equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;

        if (obj.getClass() != PedidoSalvamento.class) return false;

        PedidoSalvamento pedido = (PedidoSalvamento) obj;

        if (!(this.emailCliente.equals(pedido.emailCliente))) return false;
        if (!(this.labirinto.equals(pedido.labirinto))) return false;

        return true;
    }

    /**
     * M�todo obrigat�rio hashCode
     */
    @Override
    public int hashCode() {
        int ret = 2000;

        ret = 13*ret + this.emailCliente.hashCode();
        ret = 13*ret + this.labirinto.hashCode();

        return ret;
    }

    /**
     * Construtor de c�pias da classe PedidoSalvamento
     * @param modelo PedidoSalvamento que servir� de modelo para a c�pia
     * @throws Exception Se o modelo for nulo
     */
    public PedidoSalvamento(PedidoSalvamento modelo) throws Exception {
        if (modelo == null) {
            throw new Exception ("Modelo ausente");
        }
        
        this.emailCliente = modelo.emailCliente;
        this.labirinto = modelo.labirinto;
    }

    /**
     * M�todo obrigat�rio clone
     */
    public Object clone() {
        PedidoSalvamento ret=null;
        
        try
        {
            ret = new PedidoSalvamento(this);
        } catch (Exception erro) {}
        
        return ret;
    }
}
