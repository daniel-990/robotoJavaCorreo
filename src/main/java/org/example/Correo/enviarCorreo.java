package org.example.Correo;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class enviarCorreo {

    public void correo (String username, String password, String mensaje, String destinatario, String asunto){


        Properties prop = new Properties();

        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp-mail.outlook.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp-mail.outlook.com");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });


        try {

            // Crear un objeto MimeMessage
            Message message = new MimeMessage(session);

            // Establecer remitente
            message.setFrom(new InternetAddress(username));

            // Establecer destinatario
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));

            // Asunto y contenido del mensaje
            message.setSubject(asunto);
            message.setText(mensaje);

            // Enviar el mensaje
            Transport.send(message);

            System.out.println("correo enviado " + mensaje) ;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
