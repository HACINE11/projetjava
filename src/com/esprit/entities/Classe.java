/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

/**
 *
 * @author Admin
 */
public class Classe {
     private int id_classe;
    private String nom_classe;
    private int nombre_eleve;	
    private String niveau;

    public Classe(int id_classe, String nom_classe, int nombre_eleve, String niveau) {
        this.id_classe = id_classe;
        this.nom_classe = nom_classe;
        this.nombre_eleve = nombre_eleve;
        this.niveau = niveau;
    }

    public Classe(String nom_classe, int nombre_eleve, String niveau) {
        this.nom_classe = nom_classe;
        this.nombre_eleve = nombre_eleve;
        this.niveau = niveau;
    }

    public Classe() {
    }

    public int getId_classe() {
        return id_classe;
    }

    public String getNom_classe() {
        return nom_classe;
    }

    public int getNombre_eleve() {
        return nombre_eleve;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }

    public void setNom_classe(String nom_classe) {
        this.nom_classe = nom_classe;
    }

    public void setNombre_eleve(int nombre_eleve) {
        this.nombre_eleve = nombre_eleve;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return "Classe{" + "id_classe=" + id_classe + ", nom_classe=" + nom_classe + ", nombre_eleve=" + nombre_eleve + ", niveau=" + niveau + '}';
    }
    
    
    
}
