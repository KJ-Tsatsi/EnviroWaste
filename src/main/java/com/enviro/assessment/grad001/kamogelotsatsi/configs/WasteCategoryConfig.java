package com.enviro.assessment.grad001.kamogelotsatsi.configs;

import com.enviro.assessment.grad001.kamogelotsatsi.model.WasteCategory;
import com.enviro.assessment.grad001.kamogelotsatsi.repository.WasteCategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class WasteCategoryConfig {

    @Bean
    CommandLineRunner commandLineRunner(WasteCategoryRepository repository) {
        return args -> {
            WasteCategory agricultural = new WasteCategory("Agricultural");
            WasteCategory chemical = new WasteCategory("Chemical");
            WasteCategory health = new WasteCategory("Medical");
            WasteCategory demolition = new WasteCategory("Demolition");

            repository.saveAll(List.of(agricultural, chemical, health, demolition));
        };
    }
}
