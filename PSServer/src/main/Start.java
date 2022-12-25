/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import ui.form.MainForm;

/**
 *
 * @author Somika
 */
public class Start {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.simple2d.Synthetica2DLookAndFeel");
        new MainForm().setVisible(true);
    }
}
