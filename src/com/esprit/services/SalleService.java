package com.esprit.services;
import com.esprit.entities.Classe;
import com.esprit.services.SalleService;
import com.esprit.utils.DataSource;
import com.esprit.entities.Salle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class SalleService implements IService <Salle> {
       private Connection cnx = DataSource.getInstance().getCnx();
       
    public void ajouter(Salle p) {
        try {
            String req = "INSERT INTO Salle (numero_salle, capacite, type_salle) VALUES (?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            //pst.setInt(1, p.getId_salle());
            pst.setInt(1, p.getNumero_salle());
            pst.setInt(2, p.getCapacite());
            pst.setString(3, p.getType_salle());
            
            pst.executeUpdate();
            System.out.println("Salle Ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifier(Salle p) {
            try {
                String req = "UPDATE salle SET capacite=?, type_salle=? WHERE numero_salle=?";
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setInt(1, p.getCapacite());
                pst.setString(2, p.getType_salle());
                pst.setInt(3, p.getNumero_salle());
                pst.executeUpdate();
                System.out.println("Salle modifiée !");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    
    public void supprimer(Salle p) {
        try {
            String req = "DELETE from Salle WHERE id_salle=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getId_salle());
            pst.executeUpdate();
            System.out.println("salle supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Salle> afficher() {
        List<Salle> list = new ArrayList<>();
        
        String req = "SELECT * FROM Salle";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Salle(rs.getInt("id_salle"), rs.getInt("numero_salle"), rs.getInt("capacite"),rs.getString("type_salle")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
    
    public List<Salle> rechercherParNumero(Integer numero_salle) 
          {
            List<Salle> resultats = new ArrayList<>();
            try {

                String req = "SELECT * FROM salle WHERE numero_salle = ?";
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setInt(1,numero_salle );
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    int idSalle = rs.getInt("id_salle");
                    int numeroSalle = rs.getInt("numero_salle");
                    int capacite = rs.getInt("capacite");
                    String typeSalle = rs.getString("type_salle");
                    Salle salle = new Salle(idSalle, numeroSalle, capacite, typeSalle);
                    resultats.add(salle);
                }
            } catch (SQLException ex) {
                System.out.println("Erreur lors de la recherche des salles par numéro : " + ex.getMessage());
            }
            return resultats;
        }

 
}
