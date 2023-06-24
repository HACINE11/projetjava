/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Emploi {
    
    private int id_emploi;
    private int id_classe;
    private String nom_classe; 
    private int id_salle;
    private int numero_salle; 
    //private int id_user;
    private int id_matiere;
    private String nom_matiere;
    private Time heure_debut;
    private Time heure_fin;
    private Date jour;

    public Emploi(String nom_classe, int numero_salle, String nom_matiere, Time heure_debut, Time heure_fin, Date jour) {
        this.nom_classe = nom_classe;
        this.numero_salle = numero_salle;
        this.nom_matiere = nom_matiere;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.jour = jour;
    }
    
    

    public Emploi(int id_emploi, int id_classe, String nom_classe, int id_salle, int numero_salle, int id_matiere, String nom_matiere, Time heure_debut, Time heure_fin, Date jour) {
        this.id_emploi = id_emploi;
        this.id_classe = id_classe;
        this.nom_classe = nom_classe;
        this.id_salle = id_salle;
        this.numero_salle = numero_salle;
        this.id_matiere = id_matiere;
        this.nom_matiere = nom_matiere;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.jour = jour;
    }

    public Emploi(int id_emploi, String nom_classe, int numero_salle, String nom_matiere, Time heure_debut, Time heure_fin, Date jour) {
        this.id_emploi = id_emploi;
        this.nom_classe = nom_classe;
        this.numero_salle = numero_salle;
        this.nom_matiere = nom_matiere;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.jour = jour;
    }

    public int getId_emploi() {
        return id_emploi;
    }

    public int getId_classe() {
        return id_classe;
    }

    public String getNom_classe() {
        return nom_classe;
    }

    public int getId_salle() {
        return id_salle;
    }

    public int getNumero_salle() {
        return numero_salle;
    }

    public int getId_matiere() {
        return id_matiere;
    }

    public String getNom_matiere() {
        return nom_matiere;
    }

    public Time getHeure_debut() {
        return heure_debut;
    }

    public Time getHeure_fin() {
        return heure_fin;
    }

    public Date getJour() {
        return jour;
    }

    public void setId_emploi(int id_emploi) {
        this.id_emploi = id_emploi;
    }

    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }

    public void setNom_classe(String nom_classe) {
        this.nom_classe = nom_classe;
    }

    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

    public void setNumero_salle(int numero_salle) {
        this.numero_salle = numero_salle;
    }

    public void setId_matiere(int id_matiere) {
        this.id_matiere = id_matiere;
    }

    public void setNom_matiere(String nom_matiere) {
        this.nom_matiere = nom_matiere;
    }

    public void setHeure_debut(Time heure_debut) {
        this.heure_debut = heure_debut;
    }

    public void setHeure_fin(Time heure_fin) {
        this.heure_fin = heure_fin;
    }

    public void setJour(Date jour) {
        this.jour = jour;
    }

    @Override
    public String toString() {
        return "Emploi{" + "id_emploi=" + id_emploi + ", id_classe=" + id_classe + ", nom_classe=" + nom_classe + ", id_salle=" + id_salle + ", numero_salle=" + numero_salle + ", id_matiere=" + id_matiere + ", nom_matiere=" + nom_matiere + ", heure_debut=" + heure_debut + ", heure_fin=" + heure_fin + ", jour=" + jour + '}';
    }
    
    

}