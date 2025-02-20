import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class GestionPosts {
    public static void gestionMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 2){
            System.out.println("1 - Nuevo post");
            System.out.println("2 - Salir");

            opcion = sc.nextInt();
            if (opcion == 1) {
                //Llamada a metodo para logearase
                addPost();
            }
        }

    }

    public static void addPost() throws SQLException {
        Scanner sc = new Scanner(System.in);
        String texto;
        java.sql.Date fecha = new java.sql.Date(new Date().getTime());
        java.sql.Connection con = Main.connection;

        System.out.println("Ingrese el texto del post: ");
        texto = sc.nextLine();

        PreparedStatement st = con.prepareStatement
                ("INSERT INTO posts (texto, likes, fecha, id_usuario) VALUES (?,0,?,?)");

        st.setString(1, texto);
        st.setDate(2, fecha);
        st.setInt(3, Main.idUsuario);
        st.executeUpdate();
        st.close();
    }
}