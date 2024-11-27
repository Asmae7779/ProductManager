package ensa.dao;

import ensa.model.product;
import java.util.List;

public interface ProductDao {
    boolean Add(product pd);
    List<product> GetAllProducts();
    product GetProductbyId(int id);
    void UpdateProduct(product pd);
    void DeleteProduct(product pd);
}
