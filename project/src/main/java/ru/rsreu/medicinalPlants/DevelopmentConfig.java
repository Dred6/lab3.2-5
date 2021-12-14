package ru.rsreu.medicinalPlants;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.rsreu.medicinalPlants.data.GearsRepository;
import ru.rsreu.medicinalPlants.data.UserRepository;

@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(GearsRepository repo,
                                        UserRepository userRepo, PasswordEncoder encoder) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                repo.save(new Gears("'melissa'","'Плетенка'", Gears.Type.CARDIOVASCULAR_DISEASES));
                repo.save(new Gears("'motherwort'","'Монофли'", Gears.Type.CARDIOVASCULAR_DISEASES));
                repo.save(new Gears("'Licorice Root'","'Черви'", Gears.Type.EXPECTORANTS));
                repo.save(new Gears("'Raspberry'","'Опарыши'", Gears.Type.EXPECTORANTS));
                repo.save(new Gears("'aloe'","'Донная'", Gears.Type.ANTI_INFLAMMATORY));
                repo.save(new Gears("'chamomile'","'Спиннинговая'", Gears.Type.ANTI_INFLAMMATORY));

                userRepo.save(new User("fff", encoder.encode("fff"),
                        "Craig Walls",  "123-123-1234"));
            }
        };
    }
}
