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

        while (opcion != -1) {

            if (!usuario.isEmpty()) {
                System.out.println("Bienvenido " + usuario + "\n");
                System.out.println("0 - Cerrar Sesion");
            }

            System.out.println("1 - Gestión de usuarios");
            System.out.println("2 - Gestión de Cuenta Corriente");
            System.out.println("3 - Gestión de prestamos");
            System.out.println("4 - Gestión de sucursales");
            System.out.println("5 - Gestión de movimientos");
            System.out.println("-1 - Salir");

            opcion = sc.nextInt();

            if (opcion == 0 && !usuario.isEmpty()) {
                usuario = "";
                System.out.println("Cerrando sesión...");
            } else if (opcion == 1) {

            }
        }
    }
}