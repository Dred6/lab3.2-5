package ru.rsreu.medicinalPlants.data;

import org.springframework.data.repository.CrudRepository;
import ru.rsreu.medicinalPlants.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
