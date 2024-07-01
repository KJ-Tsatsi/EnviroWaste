package com.enviro.assessment.grad001.kamogelotsatsi;

import com.enviro.assessment.grad001.kamogelotsatsi.controller.RecyclingTipController;
import com.enviro.assessment.grad001.kamogelotsatsi.model.RecyclingTip;
import com.enviro.assessment.grad001.kamogelotsatsi.service.RecyclingTipService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecyclingTipController.class)
public class RecyclingTipAPITest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecyclingTipService tipService;

    @Autowired
    private ObjectMapper objectMapper;

    private RecyclingTip tip1;
    private RecyclingTip tip2;

    @BeforeEach
    void setUp() {
        tip1 = new RecyclingTip(1L, "Know Your Local Recycling Rules: Different municipalities have varying guidelines for what can and cannot be recycled.");
        tip2 = new RecyclingTip(2L, "Clean and Dry Items: Rinse food and drink containers to remove any residue before placing them in the recycling bin.");
    }

    @Test
    void shouldGetAllRecyclingTips() throws Exception {
        given(tipService.getRecyclingTips()).willReturn(Arrays.asList(tip1, tip2));

        mockMvc.perform(get("/api/v1/waste/tip"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].tip").value(tip1.getTip()))
                .andExpect(jsonPath("$[1].tip").value(tip2.getTip()));
    }

    @Test
    void shouldGetRecyclingTipById() throws Exception {
        given(tipService.getRecyclingTipById(tip1.getId())).willReturn(tip1);

        mockMvc.perform(get("/api/v1/waste/tip/{tipId}", tip1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tip").value(tip1.getTip()));
    }

    @Test
    void shouldAddNewRecyclingTip() throws Exception {
        RecyclingTip newTip = new RecyclingTip("Reduce and Reuse: Before recycling, consider if items can be reused or repurposed.");
        mockMvc.perform(post("/api/v1/waste/tip")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTip)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteRecyclingTip() throws Exception {
        willDoNothing().given(tipService).deleteTip(tip1.getId());

        mockMvc.perform(delete("/api/v1/waste/tip/{tipId}", tip1.getId()))
                .andExpect(status().isOk());

        then(tipService).should().deleteTip(tip1.getId());
    }

    @Test
    void shouldUpdateRecyclingTip() throws Exception {
        RecyclingTip updatedTip = new RecyclingTip("Updated Tip");
        given(tipService.updateTip(eq(tip1.getId()), any(RecyclingTip.class))).willReturn(updatedTip);

        mockMvc.perform(put("/api/v1/waste/tip/{tipId}", tip1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedTip)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tip").value(updatedTip.getTip()));

        then(tipService).should().updateTip(eq(tip1.getId()), any(RecyclingTip.class));
    }
}
