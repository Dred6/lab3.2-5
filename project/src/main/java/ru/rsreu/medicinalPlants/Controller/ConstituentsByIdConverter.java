package ru.rsreu.medicinalPlants.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.rsreu.medicinalPlants.Gears;
import ru.rsreu.medicinalPlants.data.GearsRepository;

import java.util.Optional;

@Component
public class ConstituentsByIdConverter implements Converter<String, Gears>  {

    private GearsRepository constituentsRepo;

    @Autowired
    public ConstituentsByIdConverter(GearsRepository constituentsRepo){
        this.constituentsRepo = constituentsRepo;
    }

    @Override
    public Gears convert(String id)
    {
        Optional<Gears> optionalConstituents = constituentsRepo.findById(id);
        return optionalConstituents.isPresent() ?
                optionalConstituents.get() : null;
    }
}
