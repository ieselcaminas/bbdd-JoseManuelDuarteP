import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestionUsuarios {
    public static void gestionMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 3){
            System.out.println("1 - Logearse");
            System.out.println("2 - Nuevo usuario");
            System.out.println("3 - Salir");

            opcion = sc.nextInt();
            if (opcion == 1) {
                //Llamada a método para logearase
                if (!loggearse().isEmpty()) {
                    System.out.println("Usuario " + Main.usuario + " Logeado\n");
                    break;
                } else {
                    System.out.println("Usuario no encontrado\n");
                }
            } else if (opcion == 2){
                addUser();
            }
        }

    }

    public static String loggearse() throws SQLException {
        String usuario;
        String password;
        Scanner sc = new Scanner(System.in);
        java.sql.Connection con = Main.connection;

        System.out.println("Introduzca el nombre de usuario: ");
        usuario = sc.nextLine();
        System.out.println("Introduzca la contraseña de usuario: ");
        password = sc.nextLine();

        PreparedStatement st = null;
        String buscarUser =
                "SELECT nombre, contrasenya FROM usuarios WHERE nombre = ? AND contrasenya = ?";
        st = con.prepareStatement(buscarUser);
        st.setString(1, usuario);
        st.setString(2, password);
        ResultSet rs = st.executeQuery();

        if(rs.next()) {
            Main.usuario = rs.getString(2);
            return ".";
        } else {
            return "";
        }
    }

    public static void addUser() throws SQLException {
        String usuario;
        String apellidos;
        String password;
        Scanner sc = new Scanner(System.in);
        java.sql.Connection con = Main.connection;

        System.out.println("Introduzca el nombre de usuario: ");
        usuario = sc.nextLine();
        System.out.println("Introduzca el apellidos de usuario: ");
        apellidos = sc.nextLine();
        System.out.println("Introduzca la contraseña de usuario: ");
        password = sc.nextLine();

        PreparedStatement st = null;
        String anyadirUser =
                "INSERT INTO usuarios (nombre, apellidos, contrasenya) VALUES (?, ?, ?)";
        st = con.prepareStatement(anyadirUser);
        st.setString(1, usuario);
        st.setString(2, apellidos);
        st.setString(3, password);
        st.executeUpdate();
        st.close();

        Main.usuario = usuario;
    }
}