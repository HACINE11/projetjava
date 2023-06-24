package com.esprit.gui;

import com.esprit.entities.*;
import com.esprit.services.EmploiService;
import com.esprit.utils.DataSource;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class AjouterEmploiController implements Initializable {


    @FXML
    private TableView<Emploi> Table_emploi;
    @FXML
    private TextField tfheurDebut;
    @FXML
    private TextField tfheureFin;
    @FXML
    private DatePicker tfDate;
    @FXML
    private TableColumn<Emploi, String> Cclasse;
    @FXML
    private TableColumn<Emploi, Integer> Csalle;
 
    @FXML
    private TableColumn<Emploi, String> Cmatiere;
    @FXML
    private TableColumn<Emploi, Date> Cheur_debut;
    @FXML
    private TableColumn<Emploi, Date> Cheur_fin;
    @FXML
    private TableColumn<Emploi, Date> Cdate;
    @FXML
    private ComboBox<String> cbClasse;

    @FXML
    private ComboBox<String> cbMatiere;
    
    @FXML
    private ComboBox<Integer> cbSalle;
 



  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        Cclasse.setCellValueFactory(new PropertyValueFactory<>("nom_classe"));
        Csalle.setCellValueFactory(new PropertyValueFactory<>("numero_salle"));
        Cmatiere.setCellValueFactory(new PropertyValueFactory<>("nom_matiere"));
        Cheur_debut.setCellValueFactory(new PropertyValueFactory<>("heure_debut"));
        Cheur_fin.setCellValueFactory(new PropertyValueFactory<>("heure_fin"));
        Cdate.setCellValueFactory(new PropertyValueFactory<>("jour"));

        EmploiService emploiService = new EmploiService();
        List<Emploi> emplois = emploiService.afficher();
        ObservableList<Emploi> observableList = FXCollections.observableArrayList(emplois);
        Table_emploi.setItems(observableList);
       
        populateClassComboBox();
        populateSalleComboBox();
       populateMatiereComboBox();
    }
//    //chercher le nom salle a partir id_salle
//    private String getNumeroSalle(int id_salle) {
//        String query = "SELECT numero_salle FROM salle WHERE id_salle = " + id_salle;
//
//        try (Connection connection = DataSource.getInstance().getCnx();
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(query)) 
//        {
//            if (resultSet.next()) {
//                return resultSet.getString("nom_salle");
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
//
//            return ""; // Retourne une chaîne vide si le nom de la salle n'est pas trouvé ou s'il y a une erreur
//        }
//    private String getNomMatiere(int id_salle) {
//            String query = "SELECT numero_salle FROM salle WHERE id_salle = " + id_salle;
//
//            try (Connection connection = DataSource.getInstance().getCnx();
//                 Statement statement = connection.createStatement();
//                 ResultSet resultSet = statement.executeQuery(query)) 
//            {
//                if (resultSet.next()) {
//                    return resultSet.getString("nom_salle");
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//            return ""; // Retourne une chaîne vide si le nom de la salle n'est pas trouvé ou s'il y a une erreur
//        }
        
        private void populateClassComboBox() {
            String queryClasses = "SELECT nom_classe FROM classe";
            Connection connection = null;
            try {
                connection = DataSource.getInstance().getCnx();
                Statement statement = connection.createStatement();
                ResultSet resultSetClasses = statement.executeQuery(queryClasses);

                // Create an ObservableList to hold the class names
                ObservableList<String> classNames = FXCollections.observableArrayList();

                // Iterate over the result set and add class names to the list
                while (resultSetClasses.next()) {
                    String className = resultSetClasses.getString("nom_classe");
                    classNames.add(className);
                }

                // Set the class names as the data source for the class ComboBox
                cbClasse.setItems(classNames);

            } catch (SQLException e) {
                // Handle any potential database errors
                e.printStackTrace();
            } finally {
                // Close the connection in the finally block to ensure it's always closed
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
    private void populateSalleComboBox() {
            String querySalles = "SELECT numero_salle FROM salle";
            Connection connection = null;
            try {
                connection = DataSource.getInstance().getCnx();
                Statement statement = connection.createStatement();
                 ResultSet resultSetSalles = statement.executeQuery(querySalles);
                // Create an ObservableList to hold the salle numbers
                ObservableList<Integer> salleNumbers = FXCollections.observableArrayList();
 
                // Iterate over the result set and add salle numbers to the list
                while (resultSetSalles.next()) {
                    int salleNumber = resultSetSalles.getInt("numero_salle");
                     salleNumbers.add(salleNumber);
                }

                // Set the salle numbers as the data source for the salle ComboBox
                cbSalle.setItems(salleNumbers);

            } catch (SQLException e) {
                // Handle any potential database errors
                e.printStackTrace();
            }
            finally {
                // Close the connection in the finally block to ensure it's always closed
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
           private void populateMatiereComboBox() {
                String queryMatieres = "SELECT nom_matiere FROM matiere";
                Connection connection = null;
                try {
                    connection = DataSource.getInstance().getCnx();
                    Statement statement = connection.createStatement();
                     ResultSet resultSetMatieres = statement.executeQuery(queryMatieres);
                
    
                    // Create an ObservableList to hold the salle numbers
                    ObservableList<String> NomMatieres = FXCollections.observableArrayList();
    
                    // Iterate over the result set and add salle numbers to the list
                    while (resultSetMatieres.next()) {
                       String matiereName = resultSetMatieres.getString("nom_matiere");
                        NomMatieres.add(matiereName);
                    }
    
                    // Set the salle numbers as the data source for the salle ComboBox
                    cbMatiere.setItems(NomMatieres);
    
                } catch (SQLException e) {
                    // Handle any potential database errors
                    e.printStackTrace();
                }
                finally {
                // Close the connection in the finally block to ensure it's always closed
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            }
           

        
        
    

    @FXML
    private void ajouterEmploi(ActionEvent event) {
        
        String nom_classe = cbClasse.getValue();
        int numero_salle = cbSalle.getValue();
        String nom_matiere =cbMatiere.getValue();

        String heureDebutText = tfheurDebut.getText().trim();
        String heureFinText = tfheureFin.getText().trim();
        LocalDate date = tfDate.getValue();
    
//      Check if any of the fields is empty
//    if (cbClasse.isEmpty() || tfSalle.isEmpty() || tfMatiere.isEmpty() || heureDebutText.isEmpty() || heureFinText.isEmpty() || date == null) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Erreur");
//        alert.setHeaderText(null);
//        alert.setContentText("Veuillez remplir tous les champs !");
//        alert.showAndWait();
//        return;
//    }
    
//    Time heure_debut = Time.valueOf(heureDebutText);
//    Time heure_fin = Time.valueOf(heureFinText);
//    Time heure_debut = Time.valueOf(heureDebutText + ":00"); // Add ":00" to represent seconds
//    Time heure_fin = Time.valueOf(heureFinText + ":00");
//   
//     LocalTime heure_debut = LocalTime.parse(heureDebutText);
//    LocalTime heure_fin = LocalTime.parse(heureFinText);
       
    LocalTime heure_debut = LocalTime.parse(heureDebutText, DateTimeFormatter.ofPattern("HH:mm"));
      LocalTime heure_fin = LocalTime.parse(heureFinText, DateTimeFormatter.ofPattern("HH:mm"));

      Time time_heure_debut = Time.valueOf(heure_debut);
      Time time_heure_fin = Time.valueOf(heure_fin);
    
    
    
    
    
    Date jour = java.sql.Date.valueOf(date);
  
    Emploi emploi = new Emploi(nom_classe, numero_salle, nom_matiere, Time.valueOf(heure_debut), Time.valueOf(heure_fin), jour);
    //Connection cnx = DataSource.getInstance().getCnx();
    EmploiService emploiService = new EmploiService();
    emploiService.ajouter(emploi);

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Ajout réussi");
    alert.setHeaderText(null);
    alert.setContentText("L'emploi du temps a été ajouté avec succès !");
    alert.showAndWait();

    //clearFields();
}
    


//    private void ajouterEmploi(ActionEvent event) {
//                    String classe = cbClasse.getValue();
//                    String salle = tfSalle.getText();
//                    String matiere = tfMatiere.getText();
//                    
//                    String heureDebutText = tfheurDebut.getText().trim();
//                    String heureFinText = tfheureFin.getText().trim();
//                    LocalDate date = tfDate.getValue();
//
////                    if (cbClasse.getSelectionModel().isEmpty() || cbSalle.getSelectionModel().isEmpty() || cbMatiere.getSelectionModel().isEmpty() || heureDebutText.isEmpty() || heureFinText.isEmpty() || date==null) {
////                        Alert alert = new Alert(Alert.AlertType.ERROR);
////                        alert.setTitle("Erreur");
////                        alert.setHeaderText(null);
////                        alert.setContentText("Veuillez remplir tous les champs !");
////                        alert.showAndWait();
////                        return;
////                    }
//
//                    // Conversion des valeurs textuelles en types appropriés
//                    int id_classe = Integer.parseInt(classe);
//                    int id_salle = Integer.parseInt(salle);
//                    int id_matiere = Integer.parseInt(matiere);
//
//                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//                    Date heure_debut = null;
//                    Date heure_fin = null;
//                    Date jour = null;
//
//                    try {
//                        heure_debut = dateFormat.parse(heureDebutText);
//                        heure_fin = dateFormat.parse(heureFinText);
//                        jour = dateFormat.parse(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//            EmploiService emploiService = new EmploiService();
//            Emploi emploi = new Emploi(id_classe, id_salle, id_matiere, heure_debut, heure_fin, jour);
//                   emploiService.ajouter(emploi);
//        
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Ajout réussi");
//            alert.setHeaderText(null);
//            alert.setContentText("L'emploi du temps a été ajouté avec succès !");
//            alert.showAndWait();
//
//            //clearFields();
//        } 
////            catch (IllegalArgumentException e) {
////            Alert alert = new Alert(Alert.AlertType.ERROR);
////            alert.setTitle("Erreur");
////            alert.setHeaderText(null);
////            alert.setContentText("Veuillez saisir des dates valides au format yyyy-MM-dd !");
////            alert.showAndWait();
////        }


   


    @FXML
    private void modifierEmploi(ActionEvent event) {
    }

    @FXML
    private void supprimerEmploi(ActionEvent event) {
    }
//     
//     private void clearFields() {
//        tfClasse.clear();
//        tfSalle.clear();
//        tfMatiere.clear();
//        tfheurDebut.clear();
//        tfheureFin.clear();
//        tfDate.clear();
//    }
}
