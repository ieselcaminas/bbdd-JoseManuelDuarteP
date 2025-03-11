import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class GestionComentarios {
    public static void gestionMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 2) {
            System.out.println("1 - Nuevo comentario");
            System.out.println("2 - Salir");
            opcion = sc.nextInt();

            if (opcion == 1) {
                addComentario();
            }
        }
    }

    private static void addComentario() throws SQLException {
        if(Main.idUsuario == -1) {
            return;
        }

        Connection con = Main.connection;

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese ID del post:");
        int idPost = getPost();
        System.out.println("Ingrese el comentario:");
        String comentario = sc.nextLine();

        java.sql.Date fecha = new java.sql.Date(new Date().getTime());
        PreparedStatement st = con.prepareStatement
                ("INSERT INTO comentarios (texto, fecha, id_usuario, id_post) VALUES (? , ?, ?, ?)");


        st.setString(1, comentario);
        st.setDate(2, fecha);
        st.setInt(3, Main.idUsuario);
        st.setInt(4, idPost);

        st.executeUpdate();
    }

    private static int getPost() throws SQLException {
        GestionPosts.mostrarTodosLosPosts();
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static void printComment(ResultSet rs) throws SQLException {
        System.out.println("\t\t\t" + rs.getString(2) + " - " +
                rs.getDate(3 ) + " - " + rs.getString(4));
    }
}
