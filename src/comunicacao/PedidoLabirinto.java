package comunicacao;

import java.net.InetAddress;

public class PedidoLabirinto implements Cloneable {
    private String idCliente;

    /**
     * Construtor padrão da classe PedidoLabirinto
     * @param ip IP do Cliente
     * @throws Exception Se o ip estiver vazio
     */
    public PedidoLabirinto(InetAddress ip) throws Exception{
        if (ip == null) {
            throw new Exception ("IP inválido");
        }
        this.idCliente = ip.getHostAddress();
    }

    /**
     * Getter do IP do cliente
     * @return IP do cliente
     */
    public String getIdCliente() {
        return this.idCliente;
    }

    /**
     * Método obrigatório toString
     */
    @Override
    public String toString() {
        return "IP do Cliente: " + this.idCliente;
    }

    /**
     * Método obrigatório equals
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;

        if (obj.getClass() != PedidoLabirinto.class) return false;

        PedidoLabirinto pedido = (PedidoLabirinto) obj;

        if (!(this.idCliente.equals(pedido.idCliente))) return false;

        return true;
    }

    /**
     * Método obrigatório hashCode
     */
    @Override
    public int hashCode() {
        int ret = 2000;

        ret = 13*ret + this.idCliente.hashCode();

        return ret;
    }

    /**
     * Construtor de cópias da classe PedidoLabirinto
     * @param modelo PedidoLabirinto que servirá de modelo para a cópia
     * @throws Exception Se o modelo for nulo
     */
    public PedidoLabirinto(PedidoLabirinto modelo) throws Exception {
        if (modelo == null) {
            throw new Exception ("Modelo ausente");
        }
        this.idCliente = modelo.idCliente;
    }

    /**
     * Método obrigatório clone
     */
    public Object clone() {
        PedidoLabirinto ret=null;
        
        try
        {
            ret = new PedidoLabirinto(this);
        } catch (Exception erro) {}
        
        return ret;
    }
}
