package org.example.controller;

import org.example.Correo.enviarCorreo;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Random;

public class DBlocal {

    public void baseDatos (String dataFecha){
        Connection connection = null;
        Properties prop = new Properties();
        String configArchivo = "config/config.properties";

        Random random = new Random();

        int numeroEntero = random.nextInt(5000) + 1;
        enviarCorreo correo = new enviarCorreo();

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

            String sql = "INSERT INTO usuario (id,nombreUser,correo,pass) VALUES ("+numeroEntero+",'info','info@music.com','123')";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //java.sql.Date.valueOf(dataFecha);
            preparedStatement.executeUpdate();
            correo.correo("","","","","");
            System.out.println("se conecto a la base de datos "+numeroEntero);

        }catch(Exception e){
            System.out.println("error: "+ e);
        }
    }
}
