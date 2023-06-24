
package com.esprit.services;

import com.esprit.entities.Emploi;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmploiService implements IService <Emploi> {
    
    private Connection cnx = DataSource.getInstance().getCnx();
    


    @Override
    public void ajouter(Emploi p) {

    try {
        // Retrieve id_classe based on nom_classe
        String classeReq = "SELECT id_classe FROM classe WHERE nom_classe = ?";
        PreparedStatement classePst = cnx.prepareStatement(classeReq);
        classePst.setString(1, p.getNom_classe());
        ResultSet classeRs = classePst.executeQuery();
        if (classeRs.next()) {
            int id_classe = classeRs.getInt("id_classe");

            // Retrieve id_salle based on numero_salle
            String salleReq = "SELECT id_salle FROM salle WHERE numero_salle = ?";
            PreparedStatement sallePst = cnx.prepareStatement(salleReq);
            sallePst.setInt(1, p.getNumero_salle());
            ResultSet salleRs = sallePst.executeQuery();
            if (salleRs.next()) {
                int id_salle = salleRs.getInt("id_salle");

                // Retrieve id_matiere based on nom_matiere
                String matiereReq = "SELECT id_matiere FROM matiere WHERE nom_matiere = ?";
                PreparedStatement matierePst = cnx.prepareStatement(matiereReq);
                matierePst.setString(1, p.getNom_matiere());
                ResultSet matiereRs = matierePst.executeQuery();
                if (matiereRs.next()) {
                    int id_matiere = matiereRs.getInt("id_matiere");

               // Insert emploi record with id_classe, id_salle, id_matiere, and other values
                String req = "INSERT INTO emploi (id_classe, id_salle, id_matiere, heure_debut, heure_fin, jour) VALUES (?,?,?,?,?,?);";
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setInt(1, id_classe);
                pst.setInt(2, id_salle);
                pst.setInt(3, id_matiere);

////                Time localTimeDebut = p.getHeure_debut();
//////                 java.sql.Timestamp timestampDebut = java.sql.Timestamp.valueOf(localTimeDebut.atDate(LocalDate.of(1970, 1, 1)));
////                 Time time_heure_debut = new Time(timestampDebut.getTime());
////
////                 Time localTimeFin = p.getHeure_fin();
////            //     java.sql.Timestamp timestampFin = java.sql.Timestamp.valueOf(localTimeFin.atDate(LocalDate.of(1970, 1, 1)));
////                 Time time_heure_fin = new Time(timestampFin.getTime());
//
//                 // Set the time values in the prepared statement
//                 pst.setTime(4, time_heure_debut);
//                 pst.setTime(5, time_heure_fin);

                pst.setDate(6, new java.sql.Date(p.getJour().getTime()));

                pst.executeUpdate();

               
                    System.out.println("Emploi ajouté !");
                } else {
                    System.out.println("Matiere introuvable !");
                }
            } else {
                System.out.println("Salle introuvable !");
            }
        } else {
            System.out.println("Classe introuvable !");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}









    @Override
    public void modifier(Emploi p) {
         try {
            String req = "UPDATE emploi SET id_classe=?, id_salle=?, id_user=?, id_matiere=?, heure_debut=?, heure_fin=?, jour=? WHERE id_emploi=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getId_classe());
            pst.setInt(2, p.getId_salle());
        //  pst.setInt(3, p.getId_user());
            pst.setInt(3, p.getId_matiere());
            pst.setDate(4, new java.sql.Date(p.getHeure_debut().getTime()));
            pst.setDate(5, new java.sql.Date(p.getHeure_fin().getTime()));
            pst.setDate(6, new java.sql.Date(p.getJour().getTime()));
            pst.setInt(7, p.getId_emploi());

            pst.executeUpdate();
            System.out.println("Emploi modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Emploi p) {
        try {
            String req = "DELETE FROM emploi WHERE id_emploi=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getId_emploi());

            pst.executeUpdate();
            System.out.println("Emploi supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Emploi> afficher() {
        List<Emploi> list = new ArrayList<>();
        String req = "SELECT e.id_emploi, c.nom_classe, s.numero_salle, m.nom_matiere, e.heure_debut, e.heure_fin, e.jour " +
                 "FROM emploi e " +
                 "JOIN classe c ON e.id_classe = c.id_classe " +
                 "JOIN salle s ON e.id_salle = s.id_salle " +
                 "JOIN matiere m ON e.id_matiere = m.id_matiere";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                int id_emploi = rs.getInt("id_emploi");
                String nom_classe = rs.getString("nom_classe");
                int numero_salle = rs.getInt("numero_salle");
               
                String nom_matiere = rs.getString("nom_matiere");
                Time heure_debut = rs.getTime("heure_debut");
               Time heure_fin = rs.getTime("heure_fin");
                Date jour = rs.getDate("jour");

                Emploi emploi = new Emploi(id_emploi, nom_classe, numero_salle, nom_matiere, heure_debut, heure_fin, jour);
                list.add(emploi);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }



}

    

