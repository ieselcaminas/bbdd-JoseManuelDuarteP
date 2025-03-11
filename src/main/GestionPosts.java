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

        while (opcion != 4){
            System.out.println("1 - Nuevo post");
            System.out.println("2 - Mostrar posts de un usuario");
            System.out.println("3 - Mostrar todos los posts");
            System.out.println("4 - Salir");

            opcion = sc.nextInt();
            if (opcion == 1) {
                //Llamada a metodo para logearase
                addPost();
            } else if (opcion == 2) {
                mostrarPosts();
            } else if (opcion == 3) {
                listarTodosLosPostsConComentarios();
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
                con.prepareStatement("SELECT * " +
                        "FROM posts INNER JOIN usuarios ON posts.id_usuario=usuarios.id " +
                        "WHERE usuarios.nombre = ?");

        st.setString(1, nombreConsulta);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            imprimirPosts(
                    rs.getInt("id"),rs.getString("texto"),
                    rs.getInt("likes"), rs.getDate("fecha"), nombreConsulta);

            if (!Main.usuario.isEmpty()) {
                char opcion;
                do {
                    System.out.println("¿Dar like? (s/n)");
                    opcion = sc.next().charAt(0);

                    if (opcion == 's') {
                        darLike(rs.getInt("id"));
                    } else if (opcion != 'n') {
                        System.out.println("Opción no válida.");
                    }

                } while (opcion != 's' && opcion != 'n');
            }
        }

        st.close();
        rs.close();
    }

    public static void imprimirPosts
            (int id, String texto, int likes, java.sql.Date fecha, String nombreUsu) {
        System.out.println("ID: " + id);
        System.out.println("Post: " + texto);
        System.out.println("Likes: " + likes);
        System.out.println("Fecha: " + fecha);
        System.out.println("Usuario: " + nombreUsu);
        System.out.println("-----------------------------------------");
    }

    public static void darLike(int idPost) throws SQLException {
        Connection con = Main.connection;

        PreparedStatement st =
                con.prepareStatement("UPDATE posts SET likes = likes + 1 " +
                                    "WHERE id = ?");

        st.setInt(1, idPost);
        st.executeUpdate();
        st.close();
    }

    public static void mostrarTodosLosPosts() throws SQLException {
        Connection con = Main.connection;

        PreparedStatement st = con.prepareStatement("SELECT p.id, p.texto, p.likes, p.fecha, u.nombre" +
                " FROM posts as p " +
                " INNER JOIN usuarios as u ON p.id_usuario = u.id");

        ResultSet rs = st.executeQuery();
        while (rs.next()){
            printPost(rs);
        }
    }

    public static void printPost(ResultSet rs) throws SQLException {
        System.out.println(rs.getInt(1) + " " +
                rs.getString(2) + " likes:" +
                rs.getInt(3) + " " + rs.getDate(4) +
                " " + rs.getString(5));

    }

    public static void listarTodosLosPostsConComentarios() throws SQLException {
        Connection con = Main.connection;

        PreparedStatement st = con.prepareStatement("SELECT p.id, p.texto, p.likes, p.fecha, u.nombre FROM posts as p " +
                "INNER JOIN usuarios as u ON p.id_usuario = u.id");

        ResultSet rs = st.executeQuery();
        while (rs.next()){

            printPost(rs);
            printComments(rs.getInt(1));
        }
    }

    private static void printComments(int idPost) throws SQLException {
        // Recogemos la conexión
        Connection con = Main.connection;
        //Creamos sl SQL
        PreparedStatement st = con.prepareStatement("SELECT c.id, c.texto, c.fecha, u.nombre" +
                " FROM comentarios as c " +
                " INNER JOIN usuarios as u ON c.id_usuario = u.id " +
                " INNER JOIN posts as p ON c.id_post = p.id"+
                " WHERE p.id = ?");
        //Y recogemos los datos
        st.setInt(1, idPost);
        ResultSet rs = st.executeQuery();
        while (rs.next()){//Mientras haya datos, vamos de 1 en 1
            //Aquí va a ir el código para imprimir un comentario
            //Pero lo haremos en otro método llamado GestionComentarios.printComment() al que
            //se le pasa la fila actual del rs
            GestionComentarios.printComment(rs);
        }
    }
}