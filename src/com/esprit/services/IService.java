
package com.esprit.services;

import com.esprit.entities.*;
import java.util.List;

public interface IService<T> {
    
    public void ajouter(T p);
    public void modifier(T p);
    public void supprimer(T p);
    public List<T> afficher();
}
