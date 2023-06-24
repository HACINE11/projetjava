/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

/**
 *
 * @author Admin
 */
public class Salle {
     private int id_salle;
    private int numero_salle;
    private int capacite;
    private String type_salle; 

    /**
     *
     * @param id_salle
     * @param numero_salle
     * @param capacite
     * @param type_salle
     */
    public Salle(int id_salle, int numero_salle, int capacite, String type_salle) {
        this.id_salle = id_salle;
        this.numero_salle = numero_salle;
        this.capacite = capacite;
        this.type_salle = type_salle;
    }

    public Salle(int numero_salle, int capacite, String type_salle) {
        this.numero_salle = numero_salle;
        this.capacite = capacite;
        this.type_salle = type_salle;
    }

    public Salle() {
    }
    

    public int getId_salle() {
        return id_salle;
    }

    public int getNumero_salle() {
        return numero_salle;
    }

    public int getCapacite() {
        return capacite;
    }

    public String getType_salle() {
        return type_salle;
    }

    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

    public void setNumero_salle(int numero_salle) {
        this.numero_salle = numero_salle;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setType_salle(String type_salle) {
        this.type_salle = type_salle;
    }

    @Override
    public String toString() {
        return "Salle{" + "id_salle=" + id_salle + ", numero_salle=" + numero_salle + ", capacite=" + capacite + ", type_salle=" + type_salle + '}';
    }
    
    
}
