package be.ehb.dierenwinkel.repositories;

import be.ehb.dierenwinkel.models.Product;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> getAllByCategory(String category);
}
