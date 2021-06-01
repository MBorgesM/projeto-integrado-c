package comunicacao;

public class PedidoSalvamento extends Comunicado {
    private String emailCliente;
    private Labirinto labirinto;

    /**
     * Construtor padrão da classe PedidoSalvamento
     * @param emailCliente Email do cliente
     * @param labirinto Labirinto que será salvo
     * @throws Exception Se o email ou o labirinto forem nulos
     */
    public PedidoSalvamento(String emailCliente, Labirinto labirinto) throws Exception {
        if (emailCliente == null) {
            throw new Exception("Email inválido");
        }
        if (labirinto == null) {
        	throw new Exception("Labirinto inválido");
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
     * Método obrigatório toString
     */
    @Override
    public String toString() {
        String ret = "";

        ret += "Email do Cliente: " + this.emailCliente + "\n" + this.labirinto;

        return ret;
    }

    /**
     * Método obrigatório equals
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
     * Método obrigatório hashCode
     */
    @Override
    public int hashCode() {
        int ret = 2000;

        ret = 13*ret + this.emailCliente.hashCode();
        ret = 13*ret + this.labirinto.hashCode();

        return ret;
    }

    /**
     * Construtor de cópias da classe PedidoSalvamento
     * @param modelo PedidoSalvamento que servirá de modelo para a cópia
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
     * Método obrigatório clone
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
