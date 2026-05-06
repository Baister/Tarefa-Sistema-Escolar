package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class sqlConn {
    public static final String url = "jdbc:mysql://localhost:3306/sistema_escolar";
    private static final String user = "root";
    private static final String password = "Senai@123";

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(url, user, password);
    }
    public static void testeConnection(){
        try (Connection conn = getConnection()){
            System.out.println("A conexão foi bem sucedida!" + conn);
        }catch (SQLException e){
            System.out.println("Falha na conexão " + e.getMessage());
            System.out.println("Verifique: ");
            System.out.println("1. MySQL está rodando ?");
            System.out.println("2. O banco " + url + " realmente existe?");
            System.out.println("3. O usuário ou senha estão corretos? ");
        }
    }
}
