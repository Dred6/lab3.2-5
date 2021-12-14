package ru.rsreu.medicinalPlants.data;

import org.springframework.data.repository.CrudRepository;
import ru.rsreu.medicinalPlants.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

}
