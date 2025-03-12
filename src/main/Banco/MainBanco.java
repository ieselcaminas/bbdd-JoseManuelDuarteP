package Banco;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MainBanco {
    static java.sql.Connection connection;
    static String usuario = "";
    static int idUsuario = -1;

    public static java.sql.Connection getConnection(){
        String host = "jdbc:sqlite:src/main/resources/banco.sqlite";
        if (connection == null) {
            try {
                connection = java.sql.DriverManager.getConnection(host);
            }catch (SQLException sql){
                System.out.println(sql.getMessage());
                System.exit(0);
            }
        }
        return connection;
    }

    public static void main(String[] args) throws SQLException {
        connection = getConnection();
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        sacarBanner();
        while (opcion != -1) {
            System.out.print(Color.AMARILLO.getColor());
            System.out.println("Menu principal -- Usuario: " + MainBanco.usuario);
            System.out.println("-------------------------------");
            System.out.print(Color.RESET.getColor());
            if (usuario.isEmpty()) {
                System.out.println("1 - Iniciar sesión");
            } else {
                System.out.println("0 - Cerrar Sesion");
                System.out.println("1 - Gestión de usuarios");
                System.out.println("2 - Gestión de Cuenta Corriente");
                System.out.println("3 - Gestión de prestamos");
                System.out.println("4 - Gestión de sucursales");
                System.out.println("5 - Gestión de movimientos");
            }
            System.out.print(Color.ROJO.getColor());
            System.out.println("-1 - Salir");
            System.out.print(Color.RESET.getColor());

            opcion = sc.nextInt();

            if (opcion == 0 && !usuario.isEmpty()) {
                usuario = "";
                idUsuario = -1;
                System.out.println(Color.AMARILLO.getColor());
                System.out.println("Cerrando sesión...");
                System.out.println(Color.RESET.getColor());
            } else if (opcion == 1 && usuario.isEmpty()) {
                GestionUsuarios.iniciarSesion();
            } else if (opcion == 1) {
                GestionUsuarios.menu();
            } else if (opcion == 2 && !usuario.isEmpty()) {
                GestionCCs.menu();
            } else if (opcion == 4 && !usuario.isEmpty()) {
                GestionSucursales.menu();
            }
        }
    }

    public static void sacarBanner() {
        System.out.print(Color.VERDE.getColor());
        System.out.println("'########::'####:'########:'##::: ##:'##::::'##:'########:'##::: ##:'####:'########:::'#######::");
        System.out.println(" ##.... ##:. ##:: ##.....:: ###:: ##: ##:::: ##: ##.....:: ###:: ##:. ##:: ##.... ##:'##.... ##:");
        System.out.println(" ##:::: ##:: ##:: ##::::::: ####: ##: ##:::: ##: ##::::::: ####: ##:: ##:: ##:::: ##: ##:::: ##:");
        System.out.println(" ########::: ##:: ######::: ## ## ##: ##:::: ##: ######::: ## ## ##:: ##:: ##:::: ##: ##:::: ##:");
        System.out.println(" ##.... ##:: ##:: ##...:::: ##. ####:. ##:: ##:: ##...:::: ##. ####:: ##:: ##:::: ##: ##:::: ##:");
        System.out.println(" ##:::: ##:: ##:: ##::::::: ##:. ###::. ## ##::: ##::::::: ##:. ###:: ##:: ##:::: ##: ##:::: ##:");
        System.out.println(" ########::'####: ########: ##::. ##:::. ###:::: ########: ##::. ##:'####: ########::. #######::");
        System.out.println("........:::....::........::..::::..:::::...:::::........::..::::..::....::........::::.......:::");
        System.out.println(":::'###:::::::'########:'##::::'##:                                                              ");
        System.out.println("::'## ##::::::... ##..:: ##:::: ##:                                                              ");
        System.out.println(":'##:. ##:::::::: ##:::: ##:::: ##:                                                              ");
        System.out.println("'##:::. ##::::::: ##:::: ##:::: ##:                                                              ");
        System.out.println(" #########::::::: ##:::: ##:::: ##:                                                              ");
        System.out.println(" ##.... ##::::::: ##:::: ##:::: ##:                                                              ");
        System.out.println(" ##:::: ##::::::: ##::::. #######::                                                              ");
        System.out.println("..:::::..::::::::..::::::.......:::                                                              ");
        System.out.println("'########:::::'###::::'##::: ##::'######:::'#######::                                            ");
        System.out.println(" ##.... ##:::'## ##::: ###:: ##:'##... ##:'##.... ##:                                            ");
        System.out.println(" ##:::: ##::'##:. ##:: ####: ##: ##:::..:: ##:::: ##:                                            ");
        System.out.println(" ########::'##:::. ##: ## ## ##: ##::::::: ##:::: ##:                                            ");
        System.out.println(" ##.... ##: #########: ##. ####: ##::::::: ##:::: ##:                                            ");
        System.out.println(" ##:::: ##: ##.... ##: ##:. ###: ##::: ##: ##:::: ##:                                            ");
        System.out.println(" ########:: ##:::: ##: ##::. ##:. ######::. #######::                                            ");
        System.out.println("........:::..:::::..::..::::..:::......::::.......:::                                            ");
        System.out.println(Color.RESET.getColor());
    }
}