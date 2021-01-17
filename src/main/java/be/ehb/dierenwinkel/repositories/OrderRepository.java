package be.ehb.dierenwinkel.repositories;

import be.ehb.dierenwinkel.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
