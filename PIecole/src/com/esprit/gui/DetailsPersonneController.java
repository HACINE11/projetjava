/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class DetailsPersonneController implements Initializable {

    @FXML
    private Label lbNom;
    @FXML
    private Label lbPrenom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setLbNom(String nom) {
        lbNom.setText(nom);
    }
    
    public void setLbPrenom(String prenom) {
        lbPrenom.setText(prenom);
    }
    
}
