import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class GestionPosts {
    public static void gestionMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 3){
            System.out.println("1 - Nuevo post");
            System.out.println("2 - Mostrar posts de Usuario");
            System.out.println("3 - Salir");

            opcion = sc.nextInt();
            if (opcion == 1) {
                //Llamada a metodo para logearase
                addPost();
            } else if (opcion == 2) {
                mostrarPosts();
            }
        }

    }

    public static void addPost() throws SQLException {
        if (Main.idUsuario == -1) {
            return;
        }
        Scanner sc = new Scanner(System.in);
        String texto;
        java.sql.Date fecha = new java.sql.Date(new Date().getTime());
        Connection con = Main.connection;

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

    public static void mostrarPosts() throws SQLException {
        Scanner sc = new Scanner(System.in);
        String nombreConsulta;
        Connection con = Main.connection;

        System.out.println("Ingrese el nombre del usuario: ");
        nombreConsulta = sc.nextLine();

        PreparedStatement st =
                con.prepareStatement("SELECT posts.texto " +
                        "FROM posts INNER JOIN usuarios ON posts.id_usuario=usuarios.id " +
                        "WHERE usuarios.nombre = ?");

        st.setString(1, nombreConsulta);

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("texto") + "\n");
        }
        rs.close();
    }
}