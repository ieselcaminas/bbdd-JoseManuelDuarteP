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
        sacarBanner();
        while (opcion != 4) {


            if (!usuario.isEmpty()) {
                System.out.println("Usuario: " + usuario);
                System.out.println("0 - Cerrar Sesion");
            }
            System.out.println("1 - Usuarios");
            System.out.println("2 - Posts");
            System.out.println("3 - Comentarios");
            System.out.print(AnsiColor.RED.getCode());
            System.out.println("4 - Salir");
            System.out.print(AnsiColor.RESET.getCode());
            opcion = sc.nextInt();
            if (opcion == 0 && !usuario.isEmpty()) {
                System.out.println("Cerrando sesión...\n");
                usuario = "";
            }else if (opcion == 1) {
                GestionUsuarios.gestionMenu();
            }else if (opcion == 2) {
                GestionPosts.gestionMenu();
            } else if (opcion == 3) {
                GestionComentarios.gestionMenu();
            }
        }
    }

    public static void sacarBanner() {
        System.out.println(AnsiColor.GREEN.getCode());
        System.out.println("  _____  ____  _      __ __  ___     ___   _____");
        System.out.println(" / ___/ /    || |    |  |  ||   \\   /   \\ / ___/");
        System.out.println("(   \\_ |  o  || |    |  |  ||    \\ |     (   \\_ ");
        System.out.println(" \\__  ||     || |___ |  |  ||  D  ||  O  |\\__  |");
        System.out.println(" /  \\ ||  _  ||     ||  :  ||     ||     |/  \\ |");
        System.out.println(" \\    ||  |  ||     ||     ||     ||     |\\    |");
        System.out.println("  \\___||__|__||_____| \\__,_||_____| \\___/  \\___|");
        System.out.println(AnsiColor.RESET.getCode());
    }
}