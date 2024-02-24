package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.Iterator;
import java.util.List;

public interface ProductRepositoryIntf {
    public Product create(Product product);
    public Iterator<Product> findAll();
    public void delete(String id);
    Product findId(String id);
    Product update(Product updatedProduct);
}