package com.enviro.assessment.grad001.kamogelotsatsi.configs;

import com.enviro.assessment.grad001.kamogelotsatsi.model.DisposalGuideline;
import com.enviro.assessment.grad001.kamogelotsatsi.model.WasteCategory;
import com.enviro.assessment.grad001.kamogelotsatsi.repository.DisposalGuidelineRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DisposalGuidelineConfig {

    @Bean
    CommandLineRunner guidelineRunner(DisposalGuidelineRepository guidelineRepository) {
        return args -> {
            DisposalGuideline composting = new DisposalGuideline(
            "Mulching and composting: Use crop residues like straw, stalks, and leaves" +
                    " for mulching or composting to improve soil health and fertility.",
                    "Agricultural");

            DisposalGuideline manure = new DisposalGuideline(
                    "Animal manure management: Compost manure to produce nutrient-rich soil " +
                    "amendments or use anaerobic digestion to generate biogas and digestate.",
                    "Agricultural");


            DisposalGuideline incineration = new DisposalGuideline(
            "Incineration: Dispose of organic solvents and other suitable wastes " +
                    "through licensed incineration facilities to ensure complete destruction.",
                    "Chemical");


            DisposalGuideline autoclaving = new DisposalGuideline(
            "Autoclaving: Sterilize infectious waste like sharps and cultures using" +
                    " autoclaves before disposal to render them non-infectious.",
                    "Medical");

            DisposalGuideline pharmaceutical = new DisposalGuideline(
            "Pharmaceutical waste disposal: Segregate and dispose of expired or unused pharmaceuticals " +
                    "through approved methods such as incineration or pharmaceutical waste collection programs.",
                    "Medical");


            DisposalGuideline hazards = new DisposalGuideline(
            "Hazardous materials handling: Remove and dispose of hazardous materials such as " +
                    "asbestos and lead-based paint according to specific regulations and guidelines.",
                    "Demolition");

            DisposalGuideline landfill = new DisposalGuideline(
            "Landfill disposal: Dispose of non-recyclable demolition waste in permitted landfills " +
                    "that accept construction and demolition debris.",
                    "Demolition");

            guidelineRepository.saveAll(List.of(composting, manure, incineration, autoclaving,
                    pharmaceutical, hazards, landfill));
        };
    }
}
