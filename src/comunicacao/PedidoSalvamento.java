package comunicacao;

import java.io.Serializable;
import java.net.InetAddress;

public class PedidoSalvamento extends Comunicado {
    private String idCliente;
    private Labirinto labirinto;

    /**
     * Construtor padrão da classe PedidoSalvamento
     * @param idCliente IP do cliente
     * @param labirinto Labirinto que será salvo
     * @throws Exception Se o IP ou o labirinto forem nulos
     */
    public PedidoSalvamento(InetAddress idCliente, Labirinto labirinto) throws Exception {
        if (idCliente == null || labirinto == null) {
            throw new Exception ("IP inválido");
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
     * Método obrigatório toString
     */
    @Override
    public String toString() {
        String ret = "";

        ret += "IP do Cliente: " + this.idCliente + "\n" + this.labirinto;

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

        if (!(this.idCliente.equals(pedido.idCliente))) return false;
        if (!(this.labirinto.equals(pedido.labirinto))) return false;

        return true;
    }

    /**
     * Método obrigatório hashCode
     */
    @Override
    public int hashCode() {
        int ret = 2000;

        ret = 13*ret + this.idCliente.hashCode();
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
        
        this.idCliente = modelo.idCliente;
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
