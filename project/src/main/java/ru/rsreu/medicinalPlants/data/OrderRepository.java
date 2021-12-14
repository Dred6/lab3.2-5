package ru.rsreu.medicinalPlants.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.rsreu.medicinalPlants.Order;
import ru.rsreu.medicinalPlants.User;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUserOrderByPlacedAtDesc(
            User user, Pageable pageable);

}
