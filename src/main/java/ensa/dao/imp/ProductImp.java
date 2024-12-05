package ensa.dao.imp;

import ensa.model.commercial;
import ensa.model.product;
import ensa.util.DbConnexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class ProductImp {

    // Méthode pour ajouter un produit
    public boolean Add(product pd) throws SQLException {
        String query = "INSERT INTO product (nom,description,prix,commercial_matricule) VALUES (?,?,?,?)";
        PreparedStatement pr = null;
        try {
            DbConnexion db = new DbConnexion();
            Connection con = db.getConnection();
            pr = con.prepareStatement(query);
            pr.setString(1, pd.getNom());
            pr.setString(2, pd.getDescription());
            pr.setDouble(3, pd.getPrix());
            pr.setString(4, pd.getCommercial().getMatricule());
            int rows = pr.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retourner false en cas d'erreur
        }
    }

    // Méthode pour récupérer tous les produits
    public List<product> getAllProducts() {
        String query = "SELECT * FROM product";
        List<product> products = new ArrayList<>();

        try (
                DbConnexion db = new DbConnexion();
                Connection con = db.getConnection();
                PreparedStatement st = con.prepareStatement(query);
                ResultSet rs = st.executeQuery()
        ) {
            CommercialDaoImp commercialDao = new CommercialDaoImp();
            List<commercial> commercials = commercialDao.getAllCommercials();
            System.out.println("Liste des commerciaux récupérée : " + commercials);

            while (rs.next()) {
                String nom = rs.getString("nom");
                String description = rs.getString("description");
                double prix = rs.getDouble("prix");
                String commercialMatricule = rs.getString("commercial_matricule");

                System.out.println("Produit trouvé : " + nom + ", " + description + ", " + prix);

                commercial selectedCommercial = null;
                for (commercial com : commercials) {
                    if (com.getMatricule().equals(commercialMatricule)) {
                        selectedCommercial = com;
                        break;
                    }
                }

                if (selectedCommercial == null) {
                    System.out.println("Aucun commercial trouvé pour le matricule : " + commercialMatricule);
                }

                product pr = new product(nom, description, prix, selectedCommercial);
                products.add(pr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Produits récupérés : " + products);
        return products;
    }

    // Méthode pour mettre à jour un produit
    public void UpdateProduct(product pd, String nm, String desc, double prixx, commercial com) {
        String nomvalue = pd.getNom();
        String descriptionvalue = pd.getDescription();
        Double prixvalue = pd.getPrix();
        String matriculevalue = pd.getCommercial().getMatricule();
        String mat = com.getMatricule();

        String sql = "UPDATE PRODUCT SET nom=?, description=?, prix=?, commercial_matricule=? WHERE nom=? AND description=? AND prix=? AND commercial_matricule=?";
        try (DbConnexion db = new DbConnexion();
             Connection con = db.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, nm);
            pst.setString(2, desc);
            pst.setDouble(3, prixx);
            pst.setString(4, mat);
            pst.setString(5, nomvalue);
            pst.setString(6, descriptionvalue);
            pst.setDouble(7, prixvalue);
            pst.setString(8, matriculevalue);

            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Update réussi!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update échouée!");
        }
    }
    public void DeleteProduct(product pd){
        String sql = "DELETE FROM product WHERE id=?";
        try (
            DbConnexion db = new DbConnexion();
            Connection con = db.getConnection()){
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1,pd.getId());
            int row = pr.executeUpdate();
            if(row>0){
                System.out.println("Product deleted");
            }else {
                System.out.println("Product not deleted");
            }

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Product not deleted");
        }
    }


}
