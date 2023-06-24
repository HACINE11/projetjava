/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.gui;

import com.esprit.entities.Personne;
import com.esprit.services.ServicePersonne;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class AjoutPersonneController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterPersonne(ActionEvent event) throws IOException {
        ServicePersonne sp = new ServicePersonne();
        sp.ajouter(new Personne(tfNom.getText(), tfPrenom.getText()));
        JOptionPane.showMessageDialog(null, "Personne ajoutée !");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsPersonne.fxml"));
        Parent root = loader.load();
        tfNom.getScene().setRoot(root);
        
        DetailsPersonneController dpc = loader.getController();
        dpc.setLbNom(tfNom.getText());
        dpc.setLbPrenom(tfPrenom.getText());
    }
    
}
