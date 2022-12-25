/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui.form;

import consts.VehicleFormmModes;
import domain.TypeOfVehicle;
import domain.Vehicle;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import repository.impl.RepositoryDBTypeOfVehicle;
import repository.impl.RepositoryDBVehicle;

/**
 *
 * @author Somika
 */
public class FrmVehicle extends javax.swing.JFrame {

    private Vehicle vehicle;
    private int mode;
    private FrmViewVehicles frmViewVehicles;

    public FrmVehicle() {
        initComponents();
    }

    public FrmVehicle(Vehicle vehicle, FrmViewVehicles frmViewVehicles, int mode) {
        initComponents();
        this.vehicle = vehicle;
        this.frmViewVehicles = frmViewVehicles;
        this.mode = mode;
        prepareForm();
        populateForm();
    }

    FrmVehicle(int modul) {
        initComponents();
        this.mode = modul;
        prepareForm();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        txtModel = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMileage = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnCancle = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtBrand = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        jcbTypeOfVehicle = new javax.swing.JComboBox<>();
        jrbYes = new javax.swing.JRadioButton();
        jrbNo = new javax.swing.JRadioButton();
        btnUpdate = new javax.swing.JButton();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("Vehicle");

        jLabel2.setText("ID:");

        jLabel3.setText("Brand:");

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel4.setText("Model:");

        btnEdit.setText("Enable edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        jLabel5.setText("Mileage (in km):");

        btnCancle.setText("Cancel");
        btnCancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancleActionPerformed(evt);
            }
        });

        jLabel6.setText("Availability");

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel7.setText("Type of vehicle:");

        jrbYes.setText("Yes");
        jrbYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbYesActionPerformed(evt);
            }
        });

        jrbNo.setText("No");

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jrbYes)
                                        .addGap(29, 29, 29)
                                        .addComponent(jrbNo))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jcbTypeOfVehicle, 0, 164, Short.MAX_VALUE)
                                        .addComponent(txtMileage)
                                        .addComponent(txtId)
                                        .addComponent(txtBrand)
                                        .addComponent(txtModel))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel7)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDelete)
                    .addComponent(btnSave))
                .addGap(33, 33, 33)
                .addComponent(btnUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMileage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jrbYes)
                    .addComponent(jrbNo))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jcbTypeOfVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnUpdate)
                    .addComponent(btnEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancle))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jrbYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbYesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbYesActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        txtBrand.setEditable(true);
        txtModel.setEditable(true);
        txtMileage.setEditable(true);
        jrbYes.setEnabled(true);
        jrbNo.setEnabled(true);
        jcbTypeOfVehicle.setEnabled(true);
     }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int choice = JOptionPane.showConfirmDialog(this, "Da li ste sigurni?", "Brisanje vozila", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION) {
            return;
        } else {
            
            try {
                new RepositoryDBVehicle().delete(vehicle);
                frmViewVehicles.remove(vehicle);
            } catch (Exception ex) {
                Logger.getLogger(FrmVehicle.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.dispose();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCancleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancleActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancleActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            //validate
            new RepositoryDBVehicle().add(getInsertedData());
            JOptionPane.showMessageDialog(this, "Uspesno cuvanje!", "", JOptionPane.PLAIN_MESSAGE);
            
        } catch (Exception ex) {
            Logger.getLogger(FrmVehicle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            //validate
            new RepositoryDBVehicle().update(getInsertedData());
            JOptionPane.showMessageDialog(this, "Uspesno azuriranje!", "", JOptionPane.PLAIN_MESSAGE);
            frmViewVehicles.update();
        } catch (Exception ex) {
            Logger.getLogger(FrmVehicle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancle;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JComboBox<TypeOfVehicle> jcbTypeOfVehicle;
    private javax.swing.JRadioButton jrbNo;
    private javax.swing.JRadioButton jrbYes;
    private javax.swing.JTextField txtBrand;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMileage;
    private javax.swing.JTextField txtModel;
    // End of variables declaration//GEN-END:variables

    private void prepareForm() {

        buttonGroup1.add(jrbYes);
        buttonGroup1.add(jrbNo);

        if (mode == VehicleFormmModes.VIEW) {
            btnSave.setVisible(false);
            
            txtId.setEditable(false);
            txtBrand.setEditable(false);
            txtModel.setEditable(false);
            txtMileage.setEditable(false);
            jrbYes.setEnabled(false);
            jrbNo.setEnabled(false);
            jcbTypeOfVehicle.setEnabled(false);
        }

        if (mode == VehicleFormmModes.ADD) {
            btnDelete.setVisible(false);
            btnUpdate.setVisible(false);
            btnEdit.setVisible(false);
            
            jLabel2.setVisible(false);
            txtId.setVisible(false);
        }
        jcbTypeOfVehicle.setModel(new DefaultComboBoxModel((new RepositoryDBTypeOfVehicle().getAll()).toArray()));
    }

    private void populateForm() {

        if (mode == VehicleFormmModes.VIEW) {
            txtId.setText(vehicle.getId().toString());
            txtBrand.setText(vehicle.getBrand());
            txtModel.setText(vehicle.getModel());
            txtMileage.setText(vehicle.getMileage() + "");
            if (vehicle.isAvailability()) {
                jrbYes.setSelected(true);
            } else {
                jrbNo.setSelected(true);
            }
            jcbTypeOfVehicle.setSelectedItem(vehicle.getTypeOfVehicle());
        }

    }

    private Vehicle getInsertedData() {
        Vehicle vehicle = new Vehicle();
        if(mode == VehicleFormmModes.VIEW){
        Long id = Long.parseLong(txtId.getText());
        vehicle.setId(id);
        }
        vehicle.setBrand(txtBrand.getText().trim());
        vehicle.setModel(txtModel.getText().trim());
        vehicle.setMileage(Integer.parseInt(txtMileage.getText().trim()));

        boolean availability;
        if (jrbYes.isSelected()) {
            availability = true;
        } else {
            availability = false;
        }
        vehicle.setAvailability(availability);

        TypeOfVehicle tov = (TypeOfVehicle) jcbTypeOfVehicle.getSelectedItem();
        vehicle.setTypeOfVehicle(tov);
        return vehicle;
    }
}
