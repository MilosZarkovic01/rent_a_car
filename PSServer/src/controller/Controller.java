/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Administrator;
import domain.Client;
import domain.PDV;
import domain.PriceList;
import domain.PriceListItem;
import domain.Renting;
import domain.TypeOfVehicle;
import domain.Vehicle;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import persistence.dao.impl.AdministratorDaoImpl;
import persistence.dao.impl.ClientDaoImpl;
import persistence.dao.impl.PdvDaoImpl;
import persistence.dao.impl.PriceListDaoImpl;
import persistence.dao.impl.PriceListItemDaoImpl;
import persistence.dao.impl.RentingDaoImpl;
import persistence.dao.impl.TypeOfVehicleDaoImpl;
import persistence.dao.impl.VehicleDaoImpl;
import thread.ClientThread;
import ui.form.MainForm;
import persistence.dao.AdministratorDao;
import persistence.dao.ClientDao;
import persistence.dao.PdvDao;
import persistence.dao.PriceListDao;
import persistence.dao.PriceListItemDao;
import persistence.dao.RentingDao;
import persistence.dao.TypeOfVehicleDao;
import persistence.dao.VehicleDao;

/**
 *
 * @author Somika
 */
public class Controller {

    private static Controller instance;
    private MainForm mainForm;
    private List<ClientThread> activeClients;

    private final AdministratorDao repositoryAdministrator;
    private final VehicleDao repositoryVehicle;
    private final TypeOfVehicleDao repositoryTypeOfVehicle;
    private final ClientDao repositoryClient;
    private final RentingDao repositoryRenting;
    private final PriceListItemDao repositoryPriceListItem;
    private final PdvDao repositoryPDV;
    private final PriceListDao repositoryPriceList;

    private Controller() {
        this.activeClients = new ArrayList<>();
        this.repositoryAdministrator = new AdministratorDaoImpl();
        this.repositoryVehicle = new VehicleDaoImpl();
        this.repositoryTypeOfVehicle = new TypeOfVehicleDaoImpl();
        this.repositoryClient = new ClientDaoImpl();
        this.repositoryRenting = new RentingDaoImpl();
        this.repositoryPriceListItem = new PriceListItemDaoImpl();
        this.repositoryPDV = new PdvDaoImpl();
        this.repositoryPriceList = new PriceListDaoImpl();
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

    public void logout(Administrator admin) {
        for (ClientThread client : activeClients) {
            if (admin.equals(client.getAdministrator())) {
                mainForm.logout(admin);
            }
        }
    }

    public List<Vehicle> getAllVehicles() {
        return repositoryVehicle.getAll();
    }

    public List<Vehicle> getAvailableVehicles() {
        return repositoryVehicle.getAvailable();
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

    public void addClient(Client client) throws Exception {
        repositoryClient.add(client);
    }

    public List<Renting> getClientRentings(Client client) throws Exception {
        return repositoryClient.getClientRentings(client);
    }

    public List<Renting> getAllRentings() throws Exception {
        return repositoryRenting.getAll();
    }

    public List<PriceListItem> getPriceListItems(TypeOfVehicle tov, Date dateFrom) throws Exception {
        return repositoryPriceListItem.getPriceListItems(tov, dateFrom);
    }

    public void addRenting(Renting renting) throws Exception {
        repositoryRenting.add(renting);
    }

    public void deleteRenting(Renting renting) throws Exception {
        repositoryRenting.delete(renting);
    }

    public List<PDV> getAllPDVs() throws Exception {
        return repositoryPDV.getAll();
    }

    public void savePriceList(PriceList pl) throws Exception {
        Long priceListId = repositoryPriceList.save(pl);
        pl.setId(priceListId);

        List<PriceListItem> priceListItems = pl.getPriceListItems();

        for (PriceListItem item : priceListItems) {
            item.setPriceList(pl);
            savePriceListItem(item);
        }
    }

    public void savePriceListItem(PriceListItem item) throws Exception {
        repositoryPriceListItem.savePriceListItem(item);
    }

    public void updateRenting(Long id, BigDecimal newPrice) throws Exception {
        repositoryRenting.updatePrice(id, newPrice);
    }
}
