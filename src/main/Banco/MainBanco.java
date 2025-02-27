package Banco;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MainBanco {
    static java.sql.Connection connection;
    static String usuario = "";
    static int idUsuario = -1;

    public static java.sql.Connection getConnection(){
        String host = "jdbc:sqlite:src/main/resources/banco.sqlite";
        if (connection == null) {
            try {
                connection = java.sql.DriverManager.getConnection(host);
            }catch (SQLException sql){
                System.out.println(sql.getMessage());
                System.exit(0);
            }
        }
        return connection;
    }

    public static void main(String[] args) throws SQLException {
        connection = getConnection();
        Scanner sc = new Scanner(System.in);
        int opcion = 0;


    }
}