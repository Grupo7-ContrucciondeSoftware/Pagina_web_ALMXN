package Project.ALMXN.Controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.jupiter.api.BeforeEach;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoriaControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    // ==================== TEST CREAR ====================
    @Test
    @Order(1)
    @DisplayName("✅ CREAR: Debería crear una categoría con datos válidos")
    void deberiaCrearCategoriaConDatosValidos() throws Exception {

        String categoriaJson = """
            {
                "nombreCategoria": "Tecnologia",
                "descripcionCategoria": "Computadoras y más"
            }
            """;

        mockMvc.perform(
                        post("/api/categorias/guardar")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(categoriaJson)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreCategoria").value("Tecnologia"))
                .andExpect(jsonPath("$.descripcionCategoria").value("Computadoras y más"))
                .andExpect(jsonPath("$.estadoCategoria").value("Activo"));
    }

    // ==================== TEST LISTAR ====================
    @Test
    @Order(2)
    @DisplayName("📋 LISTAR: Debería listar todas las categorías activas")
    void deberiaListarTodasLasCategorias() throws Exception {

        mockMvc.perform(
                        get("/api/categorias/mostrar")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    // ==================== TEST ACTUALIZAR ====================
    @Test
    @Order(3)
    @DisplayName("✏️ ACTUALIZAR: Debería actualizar una categoría existente")
    void deberiaActualizarCategoria() throws Exception {

        String categoriaActualizadaJson = """
            {
                "nombreCategoria": "Tecnologia Actualizada",
                "descripcionCategoria": "Computadoras, tablets y más"
            }
            """;

        mockMvc.perform(
                        put("/api/categorias/actualizar/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(categoriaActualizadaJson)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCategoria").value(1))
                .andExpect(jsonPath("$.nombreCategoria").value("Tecnologia Actualizada"))
                .andExpect(jsonPath("$.descripcionCategoria").value("Computadoras, tablets y más"));
    }

    // ==================== TEST ELIMINAR (Soft Delete) ====================
    @Test
    @Order(4)
    @DisplayName("🗑️ ELIMINAR: Debería desactivar (soft delete) una categoría")
    void deberiaEliminarCategoriaSoftDelete() throws Exception {

        mockMvc.perform(
                        patch("/api/categorias/eliminar/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Categoría desactivada correctamente"));
    }
}