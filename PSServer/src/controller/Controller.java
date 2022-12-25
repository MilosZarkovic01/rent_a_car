/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import databasebroker.AdministratorDBBroker;
import domain.Administrator;
import java.util.ArrayList;
import java.util.List;
import repository.impl.RepositoryDBAdministrator;
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

    private Controller() {
        this.activeClients = new ArrayList<>();
        this.repositoryAdministrator = new RepositoryDBAdministrator();
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

    public void addActiveAdministrator(ClientThread client){
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

    public List<ClientThread> getActiveClients() {
        return activeClients;
    }

    public void setActiveClients(List<ClientThread> activeClients) {
        this.activeClients = activeClients;
    }

}
