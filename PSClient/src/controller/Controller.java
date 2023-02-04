/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Administrator;
import communication.Request;
import communication.Response;
import communication.Sender;
import domain.Vehicle;
import enumeration.Operation;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import communication.Recеiver;
import domain.Client;
import domain.TypeOfVehicle;
import java.io.IOException;
import settings.ConnectionConfig;

/**
 *
 * @author Somika
 */
public class Controller {

    private Socket socket;
    private Sender sender;
    private Recеiver receiver;
    private static Controller instance;

    private Controller() {
        try {
            socket = new Socket(ConnectionConfig.getInstance().getProperty("address"), Integer.parseInt(ConnectionConfig.getInstance().getProperty("port")));
            sender = new Sender(socket);
            receiver = new Recеiver(socket);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Administrator login(String username, String password) throws Exception {
        Administrator administrator = new Administrator();
        administrator.setUsername(username);
        administrator.setPassword(password);

        Request request = new Request(Operation.LOG_IN, administrator);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return administrator;
        } else if (response.getException().getMessage().equals("Administrator is already logged")) {
            administrator.setUsername("logged");
            return administrator;
        } else {
            return null;
        }
    }

    public List<Vehicle> getAllVehicles() throws Exception {
        Request request = new Request(Operation.GET_ALL_VEHICLES);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Vehicle>) response.getResult();
        } else {
            return null;
        }
    }

    public List<TypeOfVehicle> getAllTypes() throws Exception {
        Request request = new Request(Operation.GET_ALL_TYPES);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<TypeOfVehicle>) response.getResult();
        } else {
            return null;
        }

    }

    public boolean updateVehicle(Vehicle inputData) throws Exception {
        Request request = new Request(Operation.UPDATE_VEHICLE, inputData);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        } else {
            return true;
        }
    }

    public boolean deleteVehicle(Vehicle vehicle) throws Exception {
        Request request = new Request(Operation.DELETE_VEHICLE, vehicle);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        } else {
            return true;
        }
    }

    public boolean saveVehicle(Vehicle inputData) throws Exception {
        Request request = new Request(Operation.ADD_VEHICLE, inputData);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        } else {
            return true;
        }
    }

    public List<Client> getAllClients() throws Exception {
        Request request = new Request(Operation.GET_ALL_CLIENTS);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Client>) response.getResult();
        } else {
            return null;
        }
    }

    public boolean updateClient(Client client) throws Exception {
        Request request = new Request(Operation.UPDATE_CLIENT, client);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        } else {
            return true;
        }
    }
}
