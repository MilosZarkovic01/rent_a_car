/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import databasebroker.AdministratorDBBroker;
import databasebroker.ClientDBBroker;
import databasebroker.TypeOfVehicleDBBroker;
import databasebroker.VehicleDBBroker;
import domain.Administrator;
import domain.Client;
import domain.Renting;
import domain.TypeOfVehicle;
import domain.Vehicle;
import java.util.ArrayList;
import java.util.List;
import repository.impl.RepositoryDBAdministrator;
import repository.impl.RepositoryDBClient;
import repository.impl.RepositoryDBTypeOfVehicle;
import repository.impl.RepositoryDBVehicle;
import thread.ClientThread;
import ui.form.MainForm;

/**
 *
 * @author Somika
 */
public class Controller {

    private static Controller instance;
    private MainForm mainForm;
    private List<ClientThread> activeClients;

    private final AdministratorDBBroker repositoryAdministrator;
    private final VehicleDBBroker repositoryVehicle;
    private final TypeOfVehicleDBBroker repositoryTypeOfVehicle;
    private final ClientDBBroker repositoryClient;

    private Controller() {
        this.activeClients = new ArrayList<>();
        this.repositoryAdministrator = new RepositoryDBAdministrator();
        this.repositoryVehicle = new RepositoryDBVehicle();
        this.repositoryTypeOfVehicle = new RepositoryDBTypeOfVehicle();
        this.repositoryClient = new RepositoryDBClient();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public MainForm getMainForm() {
        return mainForm;
    }

    public void setMainForm(MainForm mainForm) {
        this.mainForm = mainForm;
    }

    public boolean isLogged(Administrator administrator) {
        for (ClientThread activeClient : activeClients) {
            if (activeClient.getAdministrator().equals(administrator)) {
                System.out.println("Administrator is already logged");
                return true;
            }
        }
        return false;
    }

    public void addActiveAdministrator(ClientThread client) {
        activeClients.add(client);
    }

    public Administrator login(String username, String password) throws Exception {
        List<Administrator> administrators = repositoryAdministrator.getAll();
        for (Administrator administrator : administrators) {
            if (administrator.getUsername().equals(username) && administrator.getPassword().equals(password)) {
                return administrator;
            }
        }
        throw new Exception("Unknown user!");
    }

    public List<ClientThread> getActiveAdmins() {
        return activeClients;
    }

    public void setActiveAdmins(List<ClientThread> activeClients) {
        this.activeClients = activeClients;
    }

    public List<Vehicle> getAllVehicles() {
        return repositoryVehicle.getAll();
    }

    public List<TypeOfVehicle> getAllTypes() {
        return repositoryTypeOfVehicle.getAll();
    }

    public void deleteVehicle(Vehicle vehicle) throws Exception {
        repositoryVehicle.delete(vehicle);
    }

    public void saveVehicle(Vehicle vehicle) throws Exception {
        repositoryVehicle.add(vehicle);
    }

    public void updateVehicle(Vehicle vehicle) throws Exception {
        repositoryVehicle.update(vehicle);
    }

    public List<Client> getAllClients() {
        return repositoryClient.getAllClients();
    }

    public void updateClient(Client client) throws Exception {
        repositoryClient.update(client);
    }
    
    public void addClient(Client client) throws Exception{
        repositoryClient.add(client);
    }
    public List<Renting> getClientRentings(Client client) throws Exception{
        return repositoryClient.getClientRentings(client);
    }
}
