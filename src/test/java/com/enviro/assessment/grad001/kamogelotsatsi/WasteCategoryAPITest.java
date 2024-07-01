package com.enviro.assessment.grad001.kamogelotsatsi;

import com.enviro.assessment.grad001.kamogelotsatsi.controller.WasteCategoryController;
import com.enviro.assessment.grad001.kamogelotsatsi.model.WasteCategory;
import com.enviro.assessment.grad001.kamogelotsatsi.service.WasteCategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(WasteCategoryController.class)
public class WasteCategoryAPITest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WasteCategoryService wasteCategoryService;

    @Test
    public void testGetWasteCategories() throws Exception {
        WasteCategory agricultural = new WasteCategory(1L, "Agricultural");
        WasteCategory chemical = new WasteCategory(2L, "Chemical");
        List<WasteCategory> categoryList = List.of(agricultural, chemical);

        Mockito.when(wasteCategoryService.getWasteCategories()).thenReturn(categoryList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/waste")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].category").value("Agricultural"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].category").value("Chemical"));
    }

    @Test
    public void testGetWasteCategoryById() throws Exception {
        WasteCategory agricultural = new WasteCategory(1L, "Agricultural");

        Mockito.when(wasteCategoryService.getWasteCategoryById(1L)).thenReturn(agricultural);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/waste/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("Agricultural"));
    }

    @Test
    public void testAddNewCategory() throws Exception {
        WasteCategory agricultural = new WasteCategory("Agricultural");

        Mockito.doNothing().when(wasteCategoryService).addNewCategory(Mockito.any(WasteCategory.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/waste")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"category\": \"Agricultural\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteCategory() throws Exception {
        Mockito.doNothing().when(wasteCategoryService).deleteCategory(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/waste/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateCategory() throws Exception {
        WasteCategory updatedCategory = new WasteCategory(1L, "Updated Category");

        Mockito.when(wasteCategoryService.updateCategory(Mockito.eq(1L), Mockito.any(WasteCategory.class)))
                .thenReturn(updatedCategory);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/waste/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"category\": \"Updated Category\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("Updated Category"));
    }
}
