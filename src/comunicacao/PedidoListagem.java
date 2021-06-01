package comunicacao;

public class PedidoListagem extends Comunicado {
    private String emailCliente;

    /**
     * Construtor padrão da classe PedidoListagem
     * @param emailCliente Email do Cliente
     * @throws Exception Se o email estiver vazio
     */
    public PedidoListagem(String emailCliente) throws Exception{
        if (emailCliente == null) {
            throw new Exception ("Email inválido");
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
     * Método obrigatório toString
     */
    @Override
    public String toString() {
        return "Email do Cliente: " + this.emailCliente;
    }

    /**
     * Método obrigatório equals
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
     * Método obrigatório hashCode
     */
    @Override
    public int hashCode() {
        int ret = 2000;

        ret = 13*ret + this.emailCliente.hashCode();

        return ret;
    }

    /**
     * Construtor de cópias da classe PedidoListagem
     * @param modelo PedidoListagem que servirá de modelo para a cópia
     * @throws Exception Se o modelo for nulo
     */
    public PedidoListagem(PedidoListagem modelo) throws Exception {
        if (modelo == null) {
            throw new Exception ("Modelo ausente");
        }
        this.emailCliente = modelo.emailCliente;
    }

    /**
     * Método obrigatório clone
     */
    public Object clone() {
        PedidoListagem ret=null;
        
        try {
            ret = new PedidoListagem(this);
        } catch (Exception erro) {}
        
        return ret;
    }
}
