package comunicacao;

import java.io.Serializable;
import java.net.InetAddress;

public class PedidoSalvamento extends Comunicado {
    private String idCliente;
    private Labirinto labirinto;

    /**
     * Construtor padr�o da classe PedidoSalvamento
     * @param idCliente IP do cliente
     * @param labirinto Labirinto que ser� salvo
     * @throws Exception Se o IP ou o labirinto forem nulos
     */
    public PedidoSalvamento(InetAddress idCliente, Labirinto labirinto) throws Exception {
        if (idCliente == null || labirinto == null) {
            throw new Exception ("IP inv�lido");
        }
        this.idCliente = idCliente.getHostAddress();
        this.labirinto = labirinto;
    }

    /**
     * Getter do ip do cliente
     * @return Ip do cliente
     */
    public String getIdCliente() {
        return this.idCliente;
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

        ret += "IP do Cliente: " + this.idCliente + "\n" + this.labirinto;

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

        if (!(this.idCliente.equals(pedido.idCliente))) return false;
        if (!(this.labirinto.equals(pedido.labirinto))) return false;

        return true;
    }

    /**
     * M�todo obrigat�rio hashCode
     */
    @Override
    public int hashCode() {
        int ret = 2000;

        ret = 13*ret + this.idCliente.hashCode();
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
        
        this.idCliente = modelo.idCliente;
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
