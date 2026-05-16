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
class ProveedorControllerTest {

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
    @DisplayName("CREAR: Debería crear un proveedor con datos válidos")
    void deberiaCrearProveedorConDatosValidos() throws Exception {

        String proveedorJSON = """
            {
                "rucProveedor":"10986532121",
                "razonSocialProveedor": "ProveedorTest",
                "telefonoProveedor":"987456123",
                "correoProveedor":"prov123@gmail.com"
            }
            """;

        mockMvc.perform(
                        post("/api/proveedores/guardar")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(proveedorJSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.razonSocialProveedor").value("ProveedorTest"))
                .andExpect(jsonPath("$.rucProveedor").value("10986532121"))
                .andExpect(jsonPath("$.correoProveedor").value("prov123@gmail.com"))
                .andExpect(jsonPath("$.estadoProveedor").value("Activo"));
    }

    // ==================== TEST LISTAR ====================
    @Test
    @Order(2)
    @DisplayName("LISTAR: Debería listar todos los proveedores activos")
    void deberiaListarTodosLosProveedores() throws Exception {

        mockMvc.perform(
                        get("/api/proveedores/mostrar")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    // ==================== TEST ACTUALIZAR ====================
    @Test
    @Order(3)
    @DisplayName("ACTUALIZAR: Debería actualizar un proveedor existente")
    void deberiaActualizarProveedor() throws Exception {

        String proveedorActualizadoJSON = """
            {
                "rucProveedor":"10986532121",
                "razonSocialProveedor": "ProveedorActualizado",
                "telefonoProveedor":"999888777",
                "correoProveedor":"actualizado@gmail.com"
            }
            """;

        mockMvc.perform(
                        put("/api/proveedores/actualizar/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(proveedorActualizadoJSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProveedor").value(1))
                .andExpect(jsonPath("$.razonSocialProveedor").value("ProveedorActualizado"))
                .andExpect(jsonPath("$.correoProveedor").value("actualizado@gmail.com"));
    }

    // ==================== TEST ELIMINAR (Soft Delete) ====================
    @Test
    @Order(4)
    @DisplayName("ELIMINAR: Debería desactivar (soft delete) un proveedor")
    void deberiaEliminarProveedorSoftDelete() throws Exception {

        mockMvc.perform(
                        patch("/api/proveedores/eliminar/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Proveedor desactivado correctamente"));
    }
}
