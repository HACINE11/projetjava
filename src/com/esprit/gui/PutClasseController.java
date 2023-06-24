/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.gui;

import com.esprit.entities.Classe;
import com.esprit.services.ClasseService;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

public class PutClasseController implements Initializable {

    @FXML
    private TextField tfNomClasse;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfNiveau;
    @FXML
    private TableView<Classe> TableClasse;
    @FXML
    private TableColumn<Classe, String> C_nomclasse;
    @FXML
    private TableColumn<Classe, Integer> c_nombre;
    @FXML
    private TableColumn<Classe, String> c_niveau;
    @FXML
    private TextField tfsearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeTableView();
    }    
    
    private void initializeTableView() {
     
        C_nomclasse.setCellValueFactory(new PropertyValueFactory<>("nom_classe"));
        c_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre_eleve"));
        c_niveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));

        ClasseService classeService = new ClasseService();
        List<Classe> classes = classeService.afficher();
        ObservableList<Classe> observableList = FXCollections.observableArrayList(classes);
        TableClasse.setItems(observableList);
        }
    @FXML
    private void modifierClasse(ActionEvent event) {
                String nom_classe = tfNomClasse.getText();
        String nombre_eleveText = tfNombre.getText();
        String niveau = tfNiveau.getText();

        if (nom_classe.isEmpty() || nombre_eleveText.isEmpty() || niveau.isEmpty()) {
            // Show an error message indicating that all fields must be filled
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }

        int nombre_eleve;
        try {
            nombre_eleve = Integer.parseInt(nombre_eleveText);
             } 
        catch (NumberFormatException e) {
                    // Show an error message indicating that the input is not a valid integer
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Le champ 'nombre d'élèves' doit être un entier valide.");
                    alert.showAndWait();
                    return;
             }

        ClasseService classeService = new ClasseService();
        Classe classeAModifier = new Classe(nom_classe, nombre_eleve, niveau);
        classeService.modifier(classeAModifier);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification réussie");
        alert.setHeaderText(null);
        alert.setContentText("La classe a été modifiée avec succès !");
        alert.showAndWait();

        rafraichirTableView();
        clearFields();
    }

    @FXML
    private void ajouterClasse(ActionEvent event) {
        String nomClasse = tfNomClasse.getText().trim();
        String nombreText = tfNombre.getText().trim();
        String niveau = tfNiveau.getText().trim();

        if (nomClasse.isEmpty() || nombreText.isEmpty() || niveau.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
        } else {
            int nombre = 0;
            try {
                nombre = Integer.parseInt(nombreText);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Le champ 'Nombre' doit être un nombre entier !");
                return;
            }

            ClasseService sp = new ClasseService();
            sp.ajouter(new Classe(nomClasse, nombre, niveau));
            JOptionPane.showMessageDialog(null, "Classe ajoutée !");
            rafraichirTableView();
            clearFields();
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Classe selectedClasse = TableClasse.getSelectionModel().getSelectedItem();
        ClasseService classeService = new ClasseService();
        if (selectedClasse != null) {

            classeService.supprimer(selectedClasse);// Remove the selected item from the data source

            rafraichirTableView();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suppression réussie");
            alert.setHeaderText(null);
            alert.setContentText("La classe a été supprimée avec succès !");
            alert.showAndWait();
        } else {// No item selected, show an error message

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une classe à supprimer.");
            alert.showAndWait();
        }
    }

    @FXML
    private void chercher(ActionEvent event) {
        String nom_classe = tfsearch.getText();
        ClasseService classeService = new ClasseService();
        List<Classe> resultats = classeService.rechercherParNom(nom_classe);
        TableClasse.setItems(FXCollections.observableArrayList(resultats));
    }

    @FXML
    private void convertToPDF(ActionEvent event) {
        try {
            String dest = "D:\\ESPRIT 2022 2026\\projet JAVAMOBILE\\PIECOLE\\ClasseReport.pdf";
            File file = new File(dest);
            file.getParentFile().mkdirs();
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(dest));
            document.open();
            
            PdfPTable table = new PdfPTable(3);
            table.addCell("Nom de Classe");
            table.addCell("Nombre d'élèves");
            table.addCell("Niveau");
         

            ObservableList<Classe> classes = TableClasse.getItems();
              
            for (Classe classe : classes) {
                
                table.addCell(classe.getNom_classe());
                table.addCell(String.valueOf(classe.getNombre_eleve()));
                table.addCell(classe.getNiveau());
                
                    }
            document.add(table);
            document.close();
            System.out.println("Le rapport au format PDF a été généré avec succès !");
             } catch (Exception ex) {
                 System.out.println("Erreur lors de la génération du rapport PDF : " + ex.getMessage());
             }
    }
    
        private void rafraichirTableView() {
        C_nomclasse.setCellValueFactory(new PropertyValueFactory<>("nom_classe"));
        c_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre_eleve"));
        c_niveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));

        ClasseService classeService = new ClasseService();
        List<Classe> classes = classeService.afficher();

        TableClasse.getItems().clear(); // Supprimer les éléments existants dans la TableView
        TableClasse.getItems().addAll(classes);
    }

    private void clearFields() {
        tfNomClasse.setText("");
        tfNombre.setText("");
        tfNiveau.setText("");
    }
    
}
