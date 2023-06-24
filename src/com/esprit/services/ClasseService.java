package com.esprit.services;

import com.esprit.entities.Classe;
import com.esprit.utils.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class ClasseService implements IService <Classe> {
    
    private Connection cnx = DataSource.getInstance().getCnx();
    
    @Override
    public void ajouter(Classe c) 
    {
        try {
            String req = "INSERT INTO classe (id_classe, nom_classe, nombre_eleve, niveau) VALUES ('"
                    +c.getId_classe()+"','"+ c.getNom_classe()+"','"+c.getNombre_eleve()+"','"+c.getNiveau()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Classe ajoutée !");
            } 
         catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }
    //modifier 
    public void modifier(Classe p) {
        try {
            String req = "UPDATE classe SET nombre_eleve='"+p.getNombre_eleve()+"', niveau='"+p.getNiveau()
                    +"' WHERE nom_classe='"+ p.getNom_classe()+"'";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Classe modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void supprimer(Classe p) {
        try {
            String req = "DELETE from classe WHERE nom_classe='"+p.getNom_classe()+"'";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Classe supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Classe> afficher() 
        {
        List<Classe> list = new ArrayList<>();
        
        String req = "SELECT * FROM classe";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                list.add(new Classe(
                        rs.getInt("id_classe"),rs.getString("nom_classe"),
                        rs.getInt("nombre_eleve"),
                        rs.getString("niveau")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
   
        }

    public List<Classe> rechercherParNom(String nom_classe) 
      {
        List<Classe> resultats = new ArrayList<>();
            try {
                String req = "SELECT * FROM classe WHERE nom_classe LIKE ?";
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setString(1, "%" + nom_classe + "%");
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    resultats.add(new Classe(rs.getInt("id_classe"), 
                            rs.getString("nom_classe"), 
                            rs.getInt("nombre_eleve"),
                            rs.getString("niveau")));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return resultats;
        }
      
}
      
      
