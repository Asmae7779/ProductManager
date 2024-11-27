package ensa.dao.imp;

import ensa.model.product;
import ensa.util.DbConnexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
public class ProductImp {
    public boolean Add(product pd){
         String query="INSERT INTO product (nom,description,prix,commercial_matricule) VALUES (?,?,?,?)";
        PreparedStatement pr = null ;
         try {
             DbConnexion db = new DbConnexion();
             Connection con = db.getConnection();
             pr = con.prepareStatement(query);
             pr.setString(1,pd.getNom());
             pr.setString(2,pd.getDescription());
             pr.setDouble(3,pd.getPrix());
             pr.setString(4,pd.getCommercial().getMatricule());
             int rows = pr.executeUpdate() ;
             return rows>0 ;

         }catch (SQLException e) {
             e.printStackTrace();
             return false; // Retourner false en cas d'erreur

         }




    }
}
