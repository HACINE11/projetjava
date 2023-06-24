/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.tests;

import com.esprit.entities.Personne;
import com.esprit.services.ServicePersonne;
import com.esprit.services.ServicePersonne2;
import com.esprit.utils.DataSource;

/**
 *
 * @author abdel
 */
public class MainProg {
    
    public static void main(String[] args) {
//        ServicePersonne sp = new ServicePersonne();
//        System.out.println(sp.afficher());
        ServicePersonne2 sp2 = new ServicePersonne2();
        sp2.ajouter(new Personne("Salah", "Hamza"));
    }
}
