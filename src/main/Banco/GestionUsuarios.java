package Banco;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class GestionUsuarios {
    public static void menu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != -1) {
            System.out.println("\nMenu de usuarios");
            System.out.println("-------------------------------");
            System.out.println("1 - Crear usuario");
            System.out.println("2 - Iniciar sesión");
            System.out.println("3 - Eliminar usuario");
            System.out.println("-1 - Volver");

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

    }
}
