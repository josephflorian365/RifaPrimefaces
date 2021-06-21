/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jesus
 */
public class Conexion {

    private static Connection cnx = null;

//    public static Connection conectar() throws Exception {
//        String url = "jdbc:postgresql://localhost/rifa";
//        String user = "postgres";
//        String password = "sa";
//
//        try {
//            cnx = DriverManager.getConnection(url, user, password);
////            System.out.println("Connection completed.");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        } finally {
//        }
//        return cnx;
//    }
    
        public static Connection conectar() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            cnx = DriverManager.getConnection("jdbc:postgresql://localhost/rifa","postgres","sa");
//            Class.forName("oracle.jdbc.pool.OracleDataSource");
//            cnx = DriverManager.getConnection("jdbc:oracle:thin:@kalidaoracle1_tp?TNS_ADMIN=./wallet_KALIDAORACLE1", "ADMIN", "KalidaOracle1");
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            cnx = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName= KALIDA", "sa", "sa");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e);
        }
        return cnx;
    }

    public static void cerrarCnx() throws Exception {
        if (Conexion.cnx != null) {
            cnx.close();
        }
    }

    public static Connection getCnx() {
        return cnx;
    }

    public static void setCnx(Connection aCnx) {
        cnx = aCnx;
    }

    public static void main(String[] args) throws Exception {
        conectar();
        if (cnx != null) {
            System.out.println("Conectado");
        } else {
            System.out.println("Sin Conexión");
        }
    }

}
