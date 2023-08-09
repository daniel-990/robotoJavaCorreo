package org.example.robot;

public class miProcesoRoboto extends Thread{
    private volatile boolean running  = true;

    @Override
    public void run(){
        while(running){
            System.out.println("se detiene la operacion");
            detener();
        }
    }
    public void detener(){
        running = false;
    }
}
