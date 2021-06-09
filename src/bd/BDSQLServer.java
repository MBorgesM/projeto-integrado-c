package bd;

import bd.core.*;

public class BDSQLServer
{
    public static final MeuPreparedStatement COMANDO;

    static
    {
    	MeuPreparedStatement comando = null;

    	try
        {	
            comando =
            new MeuPreparedStatement (
            "com.microsoft.sqlserver.jdbc.SQLServerDriver",
            "jdbc:sqlserver://localhost:1433;databasename=Pathfinder",
            "admin", "admin");
        }
        catch (Exception erro)
        {
            erro.printStackTrace();
            System.exit(0);
        }
        
        COMANDO = comando;
    }
}