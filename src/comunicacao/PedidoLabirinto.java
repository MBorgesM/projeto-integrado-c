package comunicacao;

import java.io.Serializable;
import java.net.InetAddress;

public class PedidoLabirinto extends Comunicado {
    private String idCliente;

    /**
     * Construtor padr�o da classe PedidoLabirinto
     * @param ip IP do Cliente
     * @throws Exception Se o ip estiver vazio
     */
    public PedidoLabirinto(InetAddress ip) throws Exception{
        if (ip == null) {
            throw new Exception ("IP inv�lido");
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
     * M�todo obrigat�rio toString
     */
    @Override
    public String toString() {
        return "IP do Cliente: " + this.idCliente;
    }

    /**
     * M�todo obrigat�rio equals
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
     * M�todo obrigat�rio hashCode
     */
    @Override
    public int hashCode() {
        int ret = 2000;

        ret = 13*ret + this.idCliente.hashCode();

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
        this.idCliente = modelo.idCliente;
    }

    /**
     * M�todo obrigat�rio clone
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
