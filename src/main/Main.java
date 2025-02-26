import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static java.sql.Connection connection;
    static String usuario = "";
    static int idUsuario = -1;

    public static java.sql.Connection getConnection(){
        String host = "jdbc:sqlite:src/main/resources/network.sqlite";
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

        while (opcion != 4) {
            if (!usuario.isEmpty()) {
                System.out.println("Usuario: " + usuario);
                System.out.println("0 - Cerrar Sesion");
            }
            System.out.println("1 - Usuarios");
            System.out.println("2 - Posts");
            System.out.println("3 - Comentarios");
            System.out.println("4 - Salir");
            opcion = sc.nextInt();
            if (opcion == 0 && !usuario.isEmpty()) {
                System.out.println("Cerrando sesión...\n");
                usuario = "";
            }else if (opcion == 1) {
                GestionUsuarios.gestionMenu();
            }else if (opcion == 2) {
                GestionPosts.gestionMenu();
            }
        }
    }
}