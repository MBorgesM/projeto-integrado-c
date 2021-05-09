package comunicacao;

public class PedidoLabirinto {
    private String idCliente;

    public PedidoLabirinto(String ip) throws Exception{
        if (ip == null) {
            throw new Exception ("IP inválido");
        }
        this.idCliente = ip;
    }

    public String getIdCliente() {
        return idCliente;
    }
}
