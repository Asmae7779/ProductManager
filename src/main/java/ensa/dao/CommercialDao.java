package ensa.dao;

import ensa.model.commercial;

import java.util.List;

public interface CommercialDao {
    public boolean addCommercial(commercial cm);
    public void deleteCommercial(commercial com);
    public List<String> getAllCommercials();
    public void UpdateCommercial(commercial com, String nom , String prenom , String matricule);
}
