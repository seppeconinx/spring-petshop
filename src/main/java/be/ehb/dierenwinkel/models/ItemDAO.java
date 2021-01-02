package be.ehb.dierenwinkel.models;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemDAO extends CrudRepository<Item, Integer> {
    List<Item> findAllByCategory(String categorie);
}
