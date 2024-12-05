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

    public void deleteCommercial(commercial com){
        String sql = "DELETE FROM commercial WHERE matricule=?";
        try (
                DbConnexion db = new DbConnexion();
                Connection con = db.getConnection()){
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1,com.getMatricule());
            int row = pr.executeUpdate();
            if(row>0){
                System.out.println("Commercial deleted");
            }else {
                System.out.println("Commercial not deleted");
            }

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Commercial not deleted");
        }

    }
    public void UpdateCommercial(commercial com, String nom , String prenom , String matricule){
        String sql = "UPDATE commercial SET nom=?, prenom=?, matricule=? WHERE nom=? AND prenom=? AND matricule=?";
        try(
            DbConnexion db = new DbConnexion();
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)){

                pst.setString(1, nom);
                pst.setString(2, prenom);
                pst.setString(3, matricule);
                pst.setString(4, com.getNom());
                pst.setString(5, com.getPrenom());
                pst.setString(6, com.getMatricule());

                int result = pst.executeUpdate();
                if (result > 0) {
                    System.out.println("Update réussi!");
                }

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Update échouée!");
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
