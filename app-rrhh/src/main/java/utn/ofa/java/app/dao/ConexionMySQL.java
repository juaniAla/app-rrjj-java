package utn.ofa.java.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    private static Connection connection = null;
    private static String host = "172.16.64.197";
    private static String port = "3306";
    private static String db = "app-rrhh";
    private static String user = "cursojava";
    private static String pass  = "cursojava";

    public static Connection getConexion() throws SQLException {
        if (connection == null) {
            connection = crearConexion(host, port, db, user, pass);
        }
        return connection;
    }
    
    public static void liberarConexion() throws SQLException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
            connection = null;
        }
    }

    // Construct default
    public ConexionMySQL() {
    }

    /**
     * Crea la conexión a MYSQL
     *
     * @param host
     * @param port
     * @param db
     * @param user
     * @param pass
     * @return Connection
     */
    private static Connection crearConexion(String host, String port, String db, String user, String pass) throws SQLException {


        String conexion = "jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=false";

        try {
            connection = DriverManager.getConnection(conexion, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }


        return connection;
    }

    /**
     * Determina si la conexión a la BD esta abierta
     *
     * @return boolean
     */
    public static boolean isConexionAbierta() {
        return connection != null;
    }
}
