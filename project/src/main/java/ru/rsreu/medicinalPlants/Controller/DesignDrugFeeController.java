package ru.rsreu.medicinalPlants.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ru.rsreu.medicinalPlants.Gears;
import ru.rsreu.medicinalPlants.Gears.Type;
import ru.rsreu.medicinalPlants.Ticket;
import ru.rsreu.medicinalPlants.Order;
import ru.rsreu.medicinalPlants.User;
import ru.rsreu.medicinalPlants.data.GearsRepository;
import ru.rsreu.medicinalPlants.data.TicketRepository;
import ru.rsreu.medicinalPlants.data.UserRepository;

import javax.validation.Valid;

//@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignDrugFeeController {

    private final GearsRepository constituentsRepo;

    private TicketRepository drugFeeRepository;

    private UserRepository userRepo;

    @Autowired
    public DesignDrugFeeController(
            GearsRepository constituentsRepo,
            TicketRepository drugFeeRepo,
            UserRepository userRepo){
        this.constituentsRepo = constituentsRepo;
        this.drugFeeRepository = drugFeeRepo;
        this.userRepo = userRepo;
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "design")
    public Ticket design(){
        return new Ticket();
    }

    @GetMapping
    public String showDesignForm(Model model, Principal principal) {
        List<Gears> constituents = new ArrayList<>();
        constituentsRepo.findAll().forEach(i -> constituents.add(i));

        Type[] types = Gears.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(constituents, type));
        }

        String username = principal.getName();
        User user = userRepo.findByUsername(username);
        model.addAttribute("user", user);

        return "design";
    }

    @PostMapping
    public String processDesign(
            @Valid Ticket drugFee, Errors errors,
            @ModelAttribute Order order) {

        if (errors.hasErrors()) {
            return "design";
        }

        Ticket saved = drugFeeRepository.save(drugFee);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }

    private List<Gears> filterByType(
            List<Gears> constituents, Type type) {
        return constituents
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
