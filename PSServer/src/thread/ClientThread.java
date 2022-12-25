/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import communication.Sender;
import domain.Administrator;
import java.net.Socket;
import communication.Recеiver;
import communication.Request;
import communication.Response;
import controller.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Somika
 */
public class ClientThread extends Thread {

    private Socket socket;
    private Sender sender;
    private Recеiver recеiver;
    private boolean signal;
    private Administrator administrator;

    public ClientThread(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        recеiver = new Recеiver(socket);
        this.signal = true;
        start();
    }

    @Override
    public void run() {
        while (signal) {
            try {
                Request request = (Request) recеiver.receive();
                Response response = new Response();
                try {
                    switch (request.getOperation()) {
                        case LOG_IN:
                            Administrator admin = (Administrator) request.getData();
                            response.setResult(Controller.getInstance().login(admin.getUsername(), admin.getPassword()));
                            administrator = (Administrator) response.getResult();

                            boolean active = Controller.getInstance().isLogged(this.administrator);
                            
                            if (active) {
                                response.setException(new Exception("Administrator is already logged"));
                            } else {
                                Controller.getInstance().addActiveAdministrator(this);
                                Controller.getInstance().getMainForm().addLoggedAdministrator(administrator);
                            }
                            sender.send(response);
                            break;

                        default:
                            throw new AssertionError();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    response.setException(ex);
                }
            } catch (Exception ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Socket getSocket() {
        return socket;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setSignal(boolean signal) {
        this.signal = signal;
    }

}
