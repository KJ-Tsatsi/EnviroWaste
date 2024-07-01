package com.enviro.assessment.grad001.kamogelotsatsi;

import com.enviro.assessment.grad001.kamogelotsatsi.controller.DisposalGuidelineController;
import com.enviro.assessment.grad001.kamogelotsatsi.model.DisposalGuideline;
import com.enviro.assessment.grad001.kamogelotsatsi.service.DisposalGuidelineService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(DisposalGuidelineController.class)
public class DisposalGuidelineAPITest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DisposalGuidelineService guidelineService;

    @Autowired
    private ObjectMapper objectMapper;

    private DisposalGuideline guideline1;
    private DisposalGuideline guideline2;

    @BeforeEach
    void setUp() {
        guideline1 = new DisposalGuideline(1L, "Guideline 1", "Category 1");
        guideline2 = new DisposalGuideline(2L, "Guideline 2", "Category 2");
    }

    @Test
    void getAllGuidelines() throws Exception {
        List<DisposalGuideline> guidelines = List.of(guideline1, guideline2);
        when(guidelineService.getAllGuidelines()).thenReturn(guidelines);

        mockMvc.perform(get("/api/v1/waste/guidelines"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(guidelines.size()))
                .andExpect(jsonPath("$[0].guideline").value(guideline1.getGuideline()))
                .andExpect(jsonPath("$[1].guideline").value(guideline2.getGuideline()));
    }

    @Test
    void getGuidelineById() throws Exception {
        when(guidelineService.getGuidelineById(guideline1.getId())).thenReturn(guideline1);

        mockMvc.perform(get("/api/v1/waste/guidelines/id/{id}", guideline1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.guideline").value(guideline1.getGuideline()));
    }

    @Test
    void getGuidelineByCategory() throws Exception {
        List<DisposalGuideline> guidelines = List.of(guideline1);
        when(guidelineService.getGuidelinesByCategory(guideline1.getWasteCategory())).thenReturn(guidelines);

        mockMvc.perform(get("/api/v1/waste/guidelines/category/{category}", guideline1.getWasteCategory()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(guidelines.size()))
                .andExpect(jsonPath("$[0].guideline").value(guideline1.getGuideline()));
    }

    @Test
    void addNewGuideline() throws Exception {
        mockMvc.perform(post("/api/v1/waste/guidelines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(guideline1)))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteGuideline() throws Exception {
        mockMvc.perform(delete("/api/v1/waste/guidelines/{id}", guideline1.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void updateGuideline() throws Exception {
        when(guidelineService.updateGuideline(any(Long.class), any(DisposalGuideline.class))).thenReturn(guideline1);

        mockMvc.perform(put("/api/v1/waste/guidelines/{id}", guideline1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(guideline1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.guideline").value(guideline1.getGuideline()));
    }
}
