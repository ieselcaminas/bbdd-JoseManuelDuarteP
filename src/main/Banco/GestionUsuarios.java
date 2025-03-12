package Banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestionUsuarios {
    public static void menu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != -1) {
            System.out.print(Color.AMARILLO.getColor());
            System.out.println("\nMenu de usuarios -- Usuario: " + MainBanco.usuario);
            System.out.println("-------------------------------");
            System.out.print(Color.RESET.getColor());
            System.out.println("1 - Crear usuario");
            System.out.println("2 - Cambiar sesión");
            System.out.println("3 - Eliminar usuario");
            System.out.print(Color.ROJO.getColor());
            System.out.println("-1 - Volver");
            System.out.print(Color.RESET.getColor());

            opcion = sc.nextInt();

            if (opcion == 1) {
                crearUsuario();
            } else if (opcion == 2) {
                iniciarSesion();
            } else if (opcion == 3) {
                eliminarUsuario();
            }
        }
    }

    public static void crearUsuario() throws SQLException {
        Connection con = MainBanco.connection;
        Scanner sc = new Scanner(System.in);
        String nombre;
        String contrasenya;
        String dni;

        System.out.println("Introduzca el nombre de usuario: ");
        nombre = sc.nextLine();
        System.out.println("Introduzca la contraseña: ");
        contrasenya = sc.nextLine();
        System.out.println("Introduzca el dni del usuario: ");
        dni = sc.nextLine();

        PreparedStatement pt;
        String nuevoUsu = "INSERT INTO usuario (dni, nombre, contrasenya) VALUES(?,?,?)";
        pt = con.prepareStatement(nuevoUsu);
        pt.setString(1, dni);
        pt.setString(2, nombre);
        pt.setString(3, contrasenya);
        pt.executeUpdate();
        pt.close();
    }

    public static void iniciarSesion() throws SQLException {
        Connection con = MainBanco.connection;
        Scanner sc = new Scanner(System.in);
        String nombre;
        String contrasenya;

        System.out.println("Introduzca el nombre de usuario: ");
        nombre = sc.nextLine();
        System.out.println("Introduzca la contraseña: ");
        contrasenya = sc.nextLine();

        PreparedStatement pt;
        String iniciarSesion = "SELECT * FROM usuario WHERE nombre = ? AND contrasenya = ?";
        pt = con.prepareStatement(iniciarSesion);
        pt.setString(1, nombre);
        pt.setString(2, contrasenya);
        ResultSet rs = pt.executeQuery();

        if (rs.next()) {
            MainBanco.usuario = rs.getString("nombre");
            MainBanco.idUsuario = rs.getInt("id");
            System.out.println("Bienvenido, " + nombre);
        } else {
            System.out.print(Color.ROJO.getColor());
            System.out.println("Error de inicio de sesión");
            System.out.print(Color.RESET.getColor());
        }
    }

    public static void eliminarUsuario() throws SQLException {
        if (MainBanco.usuario.isEmpty()) {
            System.out.print(Color.ROJO.getColor());
            System.out.println("Error, necesita iniciar sesión para borrar usuarios");
            System.out.print(Color.RESET.getColor());
            return;
        }
        Scanner sc = new Scanner(System.in);
        String opcion;

        do {
            System.out.print(Color.ROJO.getColor());
            System.out.println("AVISO: ESTO ELIMINARÁ SU USUARIO ACTUAL, ¿ESTÁ SEGURO? (S/N)");
            System.out.println(Color.RESET.getColor());
            opcion = sc.nextLine();

            if (opcion.equalsIgnoreCase("S")) {
                Connection con = MainBanco.connection;
                PreparedStatement pt;
                String eliminarUsuario = "DELETE FROM usuario WHERE id = ?";

                pt = con.prepareStatement(eliminarUsuario);
                pt.setInt(1, MainBanco.idUsuario);
                pt.executeUpdate();
                pt.close();
                System.out.print(Color.AMARILLO.getColor());
                System.out.println("Usuario " + MainBanco.usuario + " eliminado");
                System.out.print(Color.RESET.getColor());
                MainBanco.idUsuario = -1;
                MainBanco.usuario = "";
                return;
            }

        } while (!opcion.equalsIgnoreCase("N"));
    }
}
