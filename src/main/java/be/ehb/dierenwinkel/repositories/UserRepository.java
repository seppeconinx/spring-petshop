package be.ehb.dierenwinkel.repositories;

import be.ehb.dierenwinkel.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User getByEmail(String email);
}
