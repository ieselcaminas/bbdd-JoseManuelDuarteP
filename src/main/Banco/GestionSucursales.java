package Banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestionSucursales {
    public static void menu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != -1) {
            System.out.print(Color.AMARILLO.getColor());
            System.out.println("\nMenu de sucursales -- Usuario: " + MainBanco.usuario);
            System.out.println("-------------------------------");
            System.out.print(Color.RESET.getColor());
            System.out.println("1 - Crear sucursal");
            System.out.println("2 - Actualizar localización de sucursal");
            System.out.println("3 - Eliminar sucursal");
            System.out.println("4 - Listar CCs de una sucursal");
            System.out.println("5 - Listar sucursales");
            System.out.print(Color.ROJO.getColor());
            System.out.println("-1 - Volver");
            System.out.print(Color.RESET.getColor());

            opcion = sc.nextInt();

            if (opcion == 1) {
                crearSucursal();
            } else if (opcion == 2) {
                cambiarLoca();
            } else if (opcion == 3) {
                eliminarSucursal();
            } else if (opcion == 4) {

            } else if (opcion == 5) {
                listarSucursales();
            }
        }
    }

    public static void crearSucursal() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection con = MainBanco.getConnection();
        String loca;

        System.out.println("Ingrese localización de la sucursal: ");
        loca = sc.nextLine();

        PreparedStatement pt;
        String crearSucursal = "INSERT INTO sucursal (localizacion) VALUES (?)";
        pt = con.prepareStatement(crearSucursal);
        pt.setString(1, loca);
        pt.executeUpdate();
        pt.close();
    }

    public static void cambiarLoca() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection con = MainBanco.getConnection();
        String loca;
        int idSucursal;

        listarSucursales();

        System.out.println("¿Que sucursal desea cambiar? Introduza ID: ");
        idSucursal = sc.nextInt();
        sc.nextLine(); //Ponemos esto porque sc.nextInt pone automáticamente un \n que el siguiente nextLine se come
        System.out.println("Introduzca nueva localización: ");
        loca = sc.nextLine();

        PreparedStatement pt;
        String cambiarLoca = "UPDATE sucursal SET localizacion = ? WHERE id = ?";
        pt = con.prepareStatement(cambiarLoca);
        pt.setString(1, loca);
        pt.setInt(2, idSucursal);
        pt.executeUpdate();
        pt.close();
    }

    public static void eliminarSucursal() throws SQLException {
        Connection con = MainBanco.getConnection();
        int idSucursal;
        String opcion;
        Scanner sc = new Scanner(System.in);

        listarSucursales();

        System.out.println("¿Que sucursal desea eliminar? Introduza ID: ");
        idSucursal = sc.nextInt();
        sc.nextLine();

        PreparedStatement pt, pt2;
        String eliminarSucursal = "DELETE FROM sucursal WHERE id = ?";
        String cual = "SELECT localizacion FROM sucursal WHERE id = ?";
        pt = con.prepareStatement(eliminarSucursal);
        pt2 = con.prepareStatement(cual);
        pt.setInt(1, idSucursal);
        pt2.setInt(1, idSucursal);
        ResultSet rs = pt2.executeQuery();

        System.out.print(Color.ROJO.getColor());
        System.out.println("ESTO ELIMINARÁ LA SUCURSAL CON ID: " + idSucursal
        + " EN: " + rs.getString("localizacion") + "\n"
        + "¿ESTÁ SEGURO? (S/N)");
        System.out.print(Color.RESET.getColor());
        opcion = sc.nextLine();

        do {
            if (opcion.equalsIgnoreCase("S")) {
                pt.executeUpdate();
                return;
            }
        } while (!opcion.equalsIgnoreCase("N"));
        pt.close();
        pt2.close();
        rs.close();
    }

    public static void listarSucursales() throws SQLException {
        Connection con = MainBanco.getConnection();
        PreparedStatement pt;
        String listarSucursales = "SELECT * FROM sucursal";
        pt = con.prepareStatement(listarSucursales);
        ResultSet rs = pt.executeQuery();

        while (rs.next()) {
            System.out.println("ID: " + rs.getString("id")
            + " -- Localizacion: " + rs.getString("localizacion"));
        }
        rs.close();
        pt.close();
    }
}
