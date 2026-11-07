package com.laboratorio.proveedores;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laboratorio.proveedores.model.Supplier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SupplierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String API_KEY = "MI_CLAVE_SECRETA_123";

    @Test
    public void testCreateSupplier() throws Exception {
        Supplier supplier = new Supplier(null, "Distribuidora", "Nacional", "contacto@distribuidora.com", "Tecnologia", true);

        mockMvc.perform(post("/api/proveedores")
                .header("X-API-KEY", API_KEY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(supplier)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nombre").value("Distribuidora"));
    }

    @Test
    public void testGetExistingSupplier() throws Exception {
        Supplier supplier = new Supplier(null, "Comercial", "Sur", "ventas@comercial.com", "Libreria", true);

        mockMvc.perform(post("/api/proveedores")
                .header("X-API-KEY", API_KEY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(supplier)));

        mockMvc.perform(get("/api/proveedores/1")
                .header("X-API-KEY", API_KEY))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetNonExistingSupplier() throws Exception {
        mockMvc.perform(get("/api/proveedores/999")
                .header("X-API-KEY", API_KEY))
                .andExpect(status().isNotFound());
    }
}
