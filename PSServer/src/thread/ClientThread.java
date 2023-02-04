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
import domain.Client;
import domain.Vehicle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Somika
 */
public class ClientThread extends Thread {
    
    private final Socket socket;
    private final Sender sender;
    private final Recеiver recеiver;
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
        if (!socket.isClosed()) {
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
                                break;
                            case GET_ALL_VEHICLES:
                                response.setResult(Controller.getInstance().getAllVehicles());
                                break;
                            case GET_ALL_TYPES:
                                response.setResult(Controller.getInstance().getAllTypes());
                                break;
                            case DELETE_VEHICLE:
                                Controller.getInstance().deleteVehicle((Vehicle) request.getData());
                                break;
                            case ADD_VEHICLE:
                                Controller.getInstance().saveVehicle((Vehicle) request.getData());
                                break;
                            case UPDATE_VEHICLE:
                                Controller.getInstance().updateVehicle((Vehicle) request.getData());
                                break;
                            case GET_ALL_CLIENTS:
                                response.setResult(Controller.getInstance().getAllClients());
                                break;
                            case UPDATE_CLIENT:
                                Controller.getInstance().updateClient((Client) request.getData());
                                break;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        response.setException(ex);
                    }
                    sender.send(response);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                }
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
