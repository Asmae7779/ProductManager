package ensa.dao.imp;

import ensa.model.commercial;
import ensa.util.DbConnexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class CommercialDaoImp {
    public boolean addCommercial(commercial cm) {
        String query = "INSERT INTO commercial (nom, prenom, matricule) VALUES (?, ?, ?)";
        PreparedStatement pr = null ;
        try {
            DbConnexion db = new DbConnexion();

            Connection con = db.getConnection();
            pr = con.prepareStatement(query);

            pr.setString(1, cm.getNom());
            pr.setString(2, cm.getPrenom());
            pr.setString(3, cm.getMatricule());
            int rows = pr.executeUpdate();
            return rows > 0;

        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<commercial> getAllCommercials(){
        List<commercial> commercials = new ArrayList<>();
        String query="SELECT * FROM commercial";
        PreparedStatement pr = null ;
        try{
            DbConnexion db = new DbConnexion();

            Connection con = db.getConnection();
            pr = con.prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                commercials.add(new commercial(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("matricule")
                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commercials ;



    }

}
