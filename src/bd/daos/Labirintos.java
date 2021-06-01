package bd.daos;

import java.sql.SQLException;
import bd.BDSQLServer;
import bd.core.MeuResultSet;
import comunicacao.Labirinto;

public class Labirintos {
	/**
	 * Verifica se um labirinto existe no banco de dados
	 * @param nomeArquivo Nome do labirinto que será buscado
	 * @return true se o labirinto existe, senão false
	 * @throws Exception Se houver algum erro na busca do labirinto
	 */
    private static boolean buscar (String nomeArquivo) throws Exception {
        boolean ret = false;

        try {
            String sql = "SELECT * " +
                         "FROM Labirintos " +
                         "WHERE Nome = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setString(1, nomeArquivo);
            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            ret = resultado.first();
        } catch (SQLException e) {
            throw new Exception (e.getMessage());
        }

        return ret;
    }

    /**
     * Cadastra um labirinto no banco de dados
     * @param labirinto
     * @throws Exception
     */
    public static void cadastrar (Labirinto labirinto) throws Exception {
        if (labirinto == null) {
            throw new Exception ("Labirinto não fornecido");
        }

        if (!buscar(labirinto.getNome())) {
	        try {
	            String sql = "INSERT INTO Labirintos " +
	                         "(Nome, DataCriacao, DataUltimaModificacao, Conteudo, Criador)" +
	                         "VALUES " +
	                         "(?, ?, ?, ?, ?)";
	            
	            BDSQLServer.COMANDO.prepareStatement(sql);
	
	            BDSQLServer.COMANDO.setString(1, labirinto.getNome());
	            BDSQLServer.COMANDO.setString(2, labirinto.getDataCriacao());
	            BDSQLServer.COMANDO.setString(3, labirinto.getDataUltimaModificacao());
	            BDSQLServer.COMANDO.setString(4, labirinto.getConteudo());
	            BDSQLServer.COMANDO.setString(5, labirinto.getCriador());
	
	            BDSQLServer.COMANDO.executeUpdate ();
	            BDSQLServer.COMANDO.commit        ();
	            System.out.println("Labirinto cadastrado com sucesso!");
	        } catch(SQLException e) {
	            throw new Exception (e.getMessage());
	        }
        } else {
        	atualizar(labirinto);
        }
    }
    
    /**
     * Atualiza um labirinto cadastrado no banco de dados
     * @param labirinto
     * @throws Exception
     */
    private static void atualizar(Labirinto labirinto) throws Exception {
    	if (!buscar(labirinto.getNome())) {
    		throw new Exception ("Esse labirinto não existe");
    	}
    	
    	try {
    		String sql = "UPDATE Labirintos " + 
    					 "SET Conteudo = '" + labirinto.getConteudo() + "' " + 
    					 "WHERE Nome = '" + labirinto.getNome() + "'";
    		
    		BDSQLServer.COMANDO.prepareStatement(sql);
    		BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
            
            labirinto.setDataModificacao();
            
            String sql2 = "UPDATE Labirintos " + 
					 "SET DataUltimaModificacao = '" + labirinto.getDataUltimaModificacao() + "' " + 
					 "WHERE Nome = '" + labirinto.getNome() + "'";
		
            BDSQLServer.COMANDO.prepareStatement(sql2);
			BDSQLServer.COMANDO.executeUpdate ();
			BDSQLServer.COMANDO.commit        ();
			System.out.println("Labirinto atualizado com sucesso!");
    	} catch(SQLException e) {
            throw new Exception (e.getMessage());
        }
    }
    
    public static String recuperar(String nome, String criador) throws Exception {
    	String ret = "";
    	try {
            String sql = "SELECT * " +
                         "FROM Labirintos " +
                         "WHERE Nome = ? " +
                         "AND Criador = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setString(1, nome);
            BDSQLServer.COMANDO.setString(2, criador);
            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();
            
            if (resultado.first()) {
            	ret = resultado.getString("Conteudo");
            }
        } catch (SQLException e) {
            throw new Exception (e.getMessage());
        }
    	
    	return ret;
    }
    
    public static String listar(String criador) throws Exception {
    	String ret = "";
    	try {
    		String sql = "SELECT * " +
                    "FROM Labirintos " +
                    "WHERE Criador = ?";
    		
    		BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setString(1, criador);
            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();
            
            while (resultado.next()) {
            	ret += resultado.getString("Nome") + "\n";
            }
    	} catch (SQLException e) {
            throw new Exception (e.getMessage());
        }
    	
    	return ret;
    }
    
    public static void deletar(String nome, String email) throws Exception {
    	try {
    		String sql = "DELETE " +
                    "FROM Labirintos " +
                    "WHERE Nome = ? " +
                    "AND Criador = ?";
    		
    		BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setString(1, nome);
            BDSQLServer.COMANDO.setString(2, email);
            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
            System.out.println("Labirinto deletado com sucesso!");
    	} catch (SQLException e) {
            throw new Exception (e.getMessage());
        }
    }
}
