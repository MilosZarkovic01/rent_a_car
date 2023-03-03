/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import controller.Controller;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import settings.ConnectionConfig;

/**
 *
 * @author Somika
 */
public class ServerThread extends Thread {

    private ServerSocket serverSocket;
    private List<ClientThread> clients;
    boolean signal;

    public ServerThread() {
        this.signal = true;
        clients = new ArrayList<>();
        start();
    }

    @Override
    public void run() {
        int port = Integer.parseInt(ConnectionConfig.getInstance().getProperty("port"));
        try {
            serverSocket = new ServerSocket(port);
            Controller.getInstance().getMainForm().setStatus("Server is running on port: " + port, false);
            Socket socket;
            while (signal) {
                socket = serverSocket.accept();
                ClientThread client = new ClientThread(socket);
                clients.add(client);
                
            }
        } catch (IOException ex) {

        }
    }

    public void stopServer() {
        try {
            for (ClientThread client : clients) {
                client.setSignal(false);
                client.getSocket().close();
            }
            
            clients = new ArrayList<>();
            Controller.getInstance().setActiveAdmins(new ArrayList<>());
            signal = false;
            serverSocket.close();
            Controller.getInstance().getMainForm().prepareTable();
            Controller.getInstance().getMainForm().setStatus("Server stopped!", true);
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
