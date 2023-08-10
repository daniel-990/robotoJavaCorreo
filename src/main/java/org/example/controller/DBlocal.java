package org.example.controller;

import org.example.Correo.enviarCorreo;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Random;

public class DBlocal {

    enviarCorreo correo = new enviarCorreo();
    Properties prop = new Properties();
    String configArchivo = "config/config.properties";
    Connection connection = null;
    public void baseDatos (String correoAquienSeEnvia,String asunto,String dataFecha,String mensaje){
        Random random = new Random();
        int numeroEntero = random.nextInt(5000) + 1;

        try{
            prop.load(new FileInputStream(configArchivo));
            String dbUrl_ = prop.getProperty("dbUrl");
            String user_ = prop.getProperty("user");
            String pass_ = prop.getProperty("password");
            String dbUrl = dbUrl_;
            String user = user_;
            String password = pass_;

            // Establecer la conexión
            connection = DriverManager.getConnection(dbUrl, user, password);

            String sql = "INSERT INTO robotomail (idrobotomail,correo,asunto,mensaje,fecha) VALUES ("+numeroEntero+",'"+correoAquienSeEnvia+"','"+asunto+"','"+mensaje+"','"+dataFecha+"')";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //java.sql.Date.valueOf(dataFecha);
            preparedStatement.executeUpdate();
            //se ejecuta el envio de correos
            obtenerDatosDb();
        }catch(Exception e){
            System.out.println("error: "+ e);
        }
    }

    public void obtenerDatosDb (){

        try {

            String dbUrl_ = prop.getProperty("dbUrl");
            String user_ = prop.getProperty("user");
            String pass_ = prop.getProperty("password");
            String dbUrl = dbUrl_;
            String user = user_;
            String password = pass_;

            // Establecer la conexión
            connection = DriverManager.getConnection(dbUrl, user, password);

            String sqlQuery = "SELECT idrobotomail, correo, asunto, mensaje, fecha FROM robotomail";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                int id = resultSet.getInt("idrobotomail");
                String correoDB = resultSet.getString("correo");
                String asunto = resultSet.getString("asunto");
                String mensaje = resultSet.getString("mensaje");
                System.out.println(id+" / "+correoDB);
                correo.correo(mensaje,correoDB,asunto);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
