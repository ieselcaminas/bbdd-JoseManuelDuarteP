package Banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlBanco {

    static java.sql.Connection connection;

    public static java.sql.Connection getConnection() {
        String host = "jdbc:sqlite:src/main/resources/banco.sqlite";
        if (connection == null) {
            try {
                connection = java.sql.DriverManager.getConnection(host);
            } catch (SQLException sql) {
                System.out.println(sql.getMessage());
                System.exit(0);
            }
        }
        return connection;
    }

    public static void main(String[] args) throws SQLException {
        java.sql.Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        //stmt.executeUpdate("CREATE TABLE T1 (c1 varchar(50))");
        ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
        while (rs.next()) {
            System.out.println(rs.getInt(1));
            System.out.println("\t" + rs.getString(2));
            System.out.println("\t" + rs.getString(3));
        }
        stmt.close();
    }

    public static void insertUser() throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, apellidos) VALUES ('Pedro', 'Peterson')";
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    public static void insertUserPreparedStatement() throws SQLException {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO usuarios (nombre, apellidos) VALUES (?, ?)";
        stmt = connection.prepareStatement(sql);
        stmt.setString(1, "Manolo");
        stmt.setString(2, "García");
        stmt.executeUpdate();
        stmt.close();
    }
}
