package comunicacao;

public class PedidoListagem extends Comunicado {
    private String emailCliente;

    /**
     * Construtor padr�o da classe PedidoListagem
     * @param emailCliente Email do Cliente
     * @throws Exception Se o email estiver vazio
     */
    public PedidoListagem(String emailCliente) throws Exception{
        if (emailCliente == null) {
            throw new Exception ("Email inv�lido");
        }
        
        this.emailCliente = emailCliente;
    }

    /**
     * Getter do email do cliente
     * @return email do cliente
     */
    public String getEmailCliente() {
        return this.emailCliente;
    }

    /**
     * M�todo obrigat�rio toString
     */
    @Override
    public String toString() {
        return "Email do Cliente: " + this.emailCliente;
    }

    /**
     * M�todo obrigat�rio equals
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;

        if (obj.getClass() != PedidoListagem.class) return false;

        PedidoListagem pedido = (PedidoListagem) obj;

        if (!(this.emailCliente.equals(pedido.emailCliente))) return false;

        return true;
    }

    /**
     * M�todo obrigat�rio hashCode
     */
    @Override
    public int hashCode() {
        int ret = 2000;

        ret = 13*ret + this.emailCliente.hashCode();

        return ret;
    }

    /**
     * Construtor de c�pias da classe PedidoListagem
     * @param modelo PedidoListagem que servir� de modelo para a c�pia
     * @throws Exception Se o modelo for nulo
     */
    public PedidoListagem(PedidoListagem modelo) throws Exception {
        if (modelo == null) {
            throw new Exception ("Modelo ausente");
        }
        this.emailCliente = modelo.emailCliente;
    }

    /**
     * M�todo obrigat�rio clone
     */
    public Object clone() {
        PedidoListagem ret=null;
        
        try {
            ret = new PedidoListagem(this);
        } catch (Exception erro) {}
        
        return ret;
    }
}
