package be.ehb.dierenwinkel.repositories;

import be.ehb.dierenwinkel.models.Cart;
import be.ehb.dierenwinkel.models.User;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart,Integer> {
    Cart getByUser(User user);
}
