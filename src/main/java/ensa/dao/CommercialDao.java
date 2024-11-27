package ensa.dao;

import ensa.model.commercial;

import java.util.List;

public interface CommercialDao {
    public boolean addCommercial(commercial cm);
    public List<String> getAllCommercials();
}
