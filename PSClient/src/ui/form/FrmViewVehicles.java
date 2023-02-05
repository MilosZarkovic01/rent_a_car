/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui.form;

import consts.VehicleFormModes;
import controller.Controller;
import domain.TypeOfVehicle;
import domain.Vehicle;
import java.awt.Color;
import ui.table.model.VehicleTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Somika
 */
public class FrmViewVehicles extends javax.swing.JFrame {

    private List<Vehicle> vehicles;
    private List<Vehicle> filters;

    public FrmViewVehicles() {
        initComponents();
        setLocationRelativeTo(null);
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
        btnClose = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

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

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDetail)
                        .addGap(66, 66, 66)
                        .addComponent(btnFilter)
                        .addGap(18, 18, 18)
                        .addComponent(jcbTypeOfVehicles, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReset)
                        .addGap(18, 18, 18)
                        .addComponent(btnClose)))
                .addContainerGap(17, Short.MAX_VALUE))
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
                    .addComponent(btnFilter)
                    .addComponent(btnClose)
                    .addComponent(btnReset))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        int selectedRow = tblVehicles.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "You must select the row!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (filters != null) {
            new FrmVehicle(filters.get(selectedRow), this, VehicleFormModes.VIEW).setVisible(true);
        } else {
            new FrmVehicle(vehicles.get(selectedRow), this, VehicleFormModes.VIEW).setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_btnDetailActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        try {
            vehicles = Controller.getInstance().getAllVehicles();

            ((VehicleTableModel) tblVehicles.getModel()).setVehicles(vehicles);
            TypeOfVehicle tov = (TypeOfVehicle) jcbTypeOfVehicles.getSelectedItem();

            filters = ((VehicleTableModel) tblVehicles.getModel()).filter(tov);
            ((VehicleTableModel) tblVehicles.getModel()).setVehicles(filters);
            ((VehicleTableModel) tblVehicles.getModel()).update();
        } catch (Exception ex) {
            Logger.getLogger(FrmViewVehicles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        ((VehicleTableModel) tblVehicles.getModel()).setVehicles(vehicles);
        ((VehicleTableModel) tblVehicles.getModel()).update();
        filters = null;
    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnReset;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<TypeOfVehicle> jcbTypeOfVehicles;
    private javax.swing.JTable tblVehicles;
    // End of variables declaration//GEN-END:variables

    private void populateForm() {
        try {
            vehicles = Controller.getInstance().getAllVehicles();
            tblVehicles.setModel(new VehicleTableModel(vehicles));

            List<TypeOfVehicle> types = Controller.getInstance().getAllTypes();
            jcbTypeOfVehicles.setModel(new DefaultComboBoxModel(types.toArray()));
            jcbTypeOfVehicles.setSelectedIndex(-1);
        } catch (Exception ex) {
            Logger.getLogger(FrmViewVehicles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove(Vehicle vehicle) {
        ((VehicleTableModel) tblVehicles.getModel()).removeVehicle(vehicle);
    }

    public void update() throws Exception {
        ((VehicleTableModel) tblVehicles.getModel()).setVehicles(Controller.getInstance().getAllVehicles());
        ((VehicleTableModel) tblVehicles.getModel()).update();
    }
}
