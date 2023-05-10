package cz.osu.project_todoholecekp_hrtonm.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.osu.project_todoholecekp_hrtonm.Model.Category;
import cz.osu.project_todoholecekp_hrtonm.Service.CategoryService;
import cz.osu.project_todoholecekp_hrtonm.Service.CategoryServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class CategoriesControllerTest {
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Mock
    private CategoryServiceImpl categoryService;

    @InjectMocks
    private CategoriesController categoriesController;
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoriesController).build();
        objectMapper = new ObjectMapper();
    }
    @Test
    void create() throws Exception {
        Category category = new Category();
        category.setTitle("Test Category");

        when(categoryService.create(any(Category.class))).thenReturn(category);

        mockMvc.perform(MockMvcRequestBuilders.post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(category.getTitle()));

        verify(categoryService, times(1)).create(any(Category.class));
    }

}