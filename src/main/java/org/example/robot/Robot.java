package org.example.robot;
import org.example.Correo.enviarCorreo;
import org.example.controller.DBlocal;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Properties;

public class Robot {
    Timer timer = new Timer();
    String horaLocal = String.valueOf(java.time.LocalDate.now());
    DBlocal db = new DBlocal();
    enviarCorreo correo = new enviarCorreo();
    public void timerRoboto(int timerRoboto){
        int intervalosEnMili = timerRoboto;

        try{
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {

                    String ano = horaLocal.split("-")[0];
                    String mes = horaLocal.split("-")[1];
                    String dia = horaLocal.split("-")[2];

                    String correoAdondeVa = "danielarango990@gmail.com";

                    if(dia.equals("10")){
                        String fecha = mes + "/" + dia +"/" + ano;
                        db.baseDatos(correoAdondeVa,"primer notificacion",fecha,"este es un mensaje de prueba: "+fecha+" / "+correoAdondeVa);
                    }else{
                        System.out.println("no es el rango de la fecha ");
                    }
                }
            },0,intervalosEnMili);

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
