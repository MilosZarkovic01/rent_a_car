/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Administrator;
import communication.Request;
/**
 *
 * @author Somika
 */
public class Controller {

    private static Controller instance;
    
    public Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

   /* public Administrator login(String username, String password) throws Exception {
        Administrator administrator = new Administrator();
        administrator.setUsername(username);
        administrator.setPassword(password);
        
        Request request = new Request(administrator);
        CommunicationWithServer.getInstance().sendRequest(request);
        
        Response response = CommunicationWithServer.getInstance().reciveResponse();
        if(response.getStatus() == ResponseStatus.OK){
            return (Administrator) response.getData();
        }

        return null;
    }  */
}
