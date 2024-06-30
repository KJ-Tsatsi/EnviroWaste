package com.enviro.assessment.grad001.kamogelotsatsi.configs;

import com.enviro.assessment.grad001.kamogelotsatsi.model.RecyclingTip;
import com.enviro.assessment.grad001.kamogelotsatsi.repository.RecylingTipRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RecyclingTipConfig {

    @Bean
    CommandLineRunner tipRunner(RecylingTipRepository repository) {
        return args -> {
            RecyclingTip tip1 = new RecyclingTip("Know Your Local Recycling Rules: " +
            "Different municipalities have varying guidelines for what can and cannot be recycled. " +
            "Familiarize yourself with local recycling rules to ensure you are sorting and disposing " +
            "of recyclable materials correctly.");

            RecyclingTip tip2 = new RecyclingTip("Clean and Dry Items: Rinse food and drink containers to remove " +
            "any residue before placing them in the recycling bin. Contaminants like food waste can spoil " +
            "an entire batch of recyclables.");

            RecyclingTip tip3 = new RecyclingTip("Reduce and Reuse: Before recycling, consider if items can be reused " +
            "or repurposed. Reducing the amount of waste you generate and reusing materials helps conserve resources and energy.");

            RecyclingTip tip4 = new RecyclingTip("Avoid Plastic Bags: Do not place recyclables in plastic bags " +
            "unless specified by your local recycling program. Plastic bags can jam machinery at recycling facilities. " +
            "Use reusable bins or containers instead.");

            repository.saveAll(List.of(tip1, tip2, tip3, tip4));
        };
    }
}
