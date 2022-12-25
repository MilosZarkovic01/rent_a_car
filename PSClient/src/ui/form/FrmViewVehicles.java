/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui.form;

import consts.VehicleFormmModes;
import domain.TypeOfVehicle;
import domain.Vehicle;
import ui.table.model.VehicleTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import repository.impl.RepositoryDBTypeOfVehicle;
import repository.impl.RepositoryDBVehicle;

/**
 *
 * @author Somika
 */
public class FrmViewVehicles extends javax.swing.JFrame {

    private RepositoryDBVehicle repositoryDBVehicle;
    private List<Vehicle> vehicles;

    public FrmViewVehicles() {
        initComponents();
        repositoryDBVehicle = new RepositoryDBVehicle();
        vehicles = new ArrayList<>();
        populateForm();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblVehicles = new javax.swing.JTable();
        btnDetail = new javax.swing.JButton();
        jcbTypeOfVehicles = new javax.swing.JComboBox<>();
        btnFilter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblVehicles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblVehicles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblVehicles);

        btnDetail.setText("Detail");
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
            }
        });

        btnFilter.setText("Filter:");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDetail)
                        .addGap(66, 66, 66)
                        .addComponent(btnFilter)
                        .addGap(18, 18, 18)
                        .addComponent(jcbTypeOfVehicles, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDetail)
                    .addComponent(jcbTypeOfVehicles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFilter))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        int selectedRow = tblVehicles.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Morate selektovati red", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Vehicle> vehicles = repositoryDBVehicle.getAll();
        new FrmVehicle(vehicles.get(selectedRow), this, VehicleFormmModes.VIEW).setVisible(true);
    }//GEN-LAST:event_btnDetailActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        vehicles = repositoryDBVehicle.getAll();
        tblVehicles.setModel(new VehicleTableModel(vehicles));
        TypeOfVehicle tov = (TypeOfVehicle) jcbTypeOfVehicles.getSelectedItem();

        List<Vehicle> filters = ((VehicleTableModel) tblVehicles.getModel()).filter(tov);
        tblVehicles.setModel(new VehicleTableModel(filters));
    }//GEN-LAST:event_btnFilterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnFilter;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<TypeOfVehicle> jcbTypeOfVehicles;
    private javax.swing.JTable tblVehicles;
    // End of variables declaration//GEN-END:variables

    private void populateForm() {
        vehicles = repositoryDBVehicle.getAll();
        tblVehicles.setModel(new VehicleTableModel(vehicles));

        jcbTypeOfVehicles.setModel(new DefaultComboBoxModel((new RepositoryDBTypeOfVehicle().getAll()).toArray()));
        jcbTypeOfVehicles.setSelectedIndex(-1);
    }

    public void remove(Vehicle vehicle) {
        ((VehicleTableModel) tblVehicles.getModel()).removeVehicle(vehicle);
    }

    public void update() {
        ((VehicleTableModel) tblVehicles.getModel()).setVehicles(repositoryDBVehicle.getAll());
        ((VehicleTableModel) tblVehicles.getModel()).update();
    }
}
