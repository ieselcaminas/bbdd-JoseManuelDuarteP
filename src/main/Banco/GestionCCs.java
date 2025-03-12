package Banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestionCCs {
    public static void menu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != -1) {
            System.out.print(Color.AMARILLO.getColor());
            System.out.println("\nMenu de Cuentas Corrientes -- Usuario: " + MainBanco.usuario);
            System.out.println("-------------------------------");
            System.out.print(Color.RESET.getColor());
            System.out.println("1 - Crear CC");
            System.out.println("2 - Ver mis CCs");
            System.out.println("3 - Eliminar CC");
            System.out.print(Color.ROJO.getColor());
            System.out.println("-1 - Volver");
            System.out.print(Color.RESET.getColor());

            opcion = sc.nextInt();

            if (opcion == 1) {
                crearCC();
            } else if (opcion == 2) {
                verMisCC();
            }
        }
    }

    public static void crearCC() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection con = MainBanco.getConnection();
        double dinero;
        int idSucursal;

        GestionSucursales.listarSucursales();

        System.out.println("¿En que sucursal desea crear la CC? (Introduzca ID)");
        idSucursal = sc.nextInt();
        do {
            System.out.print(Color.CIAN.getColor());
            System.out.println("¿Desea hacer un ingreso inicial? (Introduzca cantidad o 0)");
            System.out.print(Color.RESET.getColor());
            dinero = sc.nextDouble();
            if (dinero < 0) {
                System.out.println(Color.AMARILLO.getColor());
                System.out.println("Introduzca un valor mayor que 0");
                System.out.println(Color.RESET.getColor());
            }
        } while (dinero < 0);

        PreparedStatement pt;
        String crearCC = "INSERT INTO CC (sucursal, propietario, dinero) VALUES (?,?,?)";
        pt = con.prepareStatement(crearCC);
        pt.setInt(1, idSucursal);
        pt.setInt(2, MainBanco.idUsuario);
        pt.setDouble(3, dinero);
        pt.executeUpdate();
        pt.close();
    }

    public static void verMisCC() throws SQLException {
        Connection con = MainBanco.getConnection();
        PreparedStatement pt;
        String verMisCC = "SELECT * FROM CC WHERE propietario = ?";
        pt = con.prepareStatement(verMisCC);
        pt.setInt(1, MainBanco.idUsuario);
        ResultSet rs = pt.executeQuery();

        while (rs.next()) {
            System.out.println("ID: " + rs.getString("id") +
                    " -- Sucursal: " + rs.getString("sucursal") +
                    " -- Propietario: " + rs.getString("propietario") +
                    " -- Dinero: " + rs.getDouble("dinero") + "€");

            if (rs.getDouble("dinero") < -0.0001) { //Esto evita errores con dobles imprecisos
                System.out.print(Color.ROJO.getColor());
                System.out.println("Esta cuenta está embargada");
                System.out.print(Color.RESET.getColor());
            }
        }
        rs.close();
        pt.close();
    }
}
