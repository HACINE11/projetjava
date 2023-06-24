package com.esprit.tests;

import com.esprit.entities.*;
import com.esprit.services.*;

import com.esprit.utils.DataSource;


public class MainProg {
    
    public static void main(String[] args) {
       ClasseService Sc = new ClasseService();
       Sc.ajouter(new Classe("1C125",25,"7"));
             
               
        //SalleService Ss =new SalleService();
       // Ss.ajouter( new Salle (3,10,"TP"));
        
        //ServiceEmploi Se =new ServiceEmploi();
        //Se.ajouter( new emploi (1,2,3,4,5,6-21-2023,12-02-2131));
    }
}
