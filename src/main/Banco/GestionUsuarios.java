package Banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class GestionUsuarios {
    public static void menu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != -1) {
            System.out.print(Color.AMARILLO.getColor());
            System.out.println("\nMenu de usuarios");
            System.out.println("-------------------------------");
            System.out.print(Color.RESET.getColor());
            System.out.println("1 - Crear usuario");
            System.out.println("2 - Iniciar sesión");
            System.out.println("3 - Eliminar usuario");
            System.out.print(Color.ROJO.getColor());
            System.out.println("-1 - Volver");
            System.out.print(Color.RESET.getColor());

            opcion = sc.nextInt();

            if (opcion == 1) {
                crearUsuario();
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
        String usuario = sc.nextLine();
        System.out.println("Introduzca la contraseña: ");
        String contraseny = sc.nextLine();
        System.out.println("Introduzca el dni del usuario: ");
        dni = sc.nextLine();

        PreparedStatement pt;
        String nuevoUsu = "INSERT INTO usuario (dni, nombre, contrasenya) VALUES(?,?,?)";
        pt = con.prepareStatement(nuevoUsu);
        pt.setString(1, dni);
        pt.setString(2, usuario);
        pt.setString(3, contraseny);
        pt.executeUpdate();
        pt.close();
    }
}
