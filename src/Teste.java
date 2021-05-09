import java.net.InetAddress;
import java.time.LocalDateTime;

import comunicacao.*;

public class Teste {
    public static void main(String[] args) {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            String ip = addr.getHostAddress();
            Labirinto teste = new Labirinto("Labirinto 1", "####\nE  S\n####", ip);

            teste.setNome("Labirinto");

            System.out.println(teste);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
