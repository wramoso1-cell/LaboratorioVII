package com.laboratorio.empleados;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laboratorio.empleados.model.Employee;
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
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String API_KEY = "MI_CLAVE_SECRETA_123";

    @Test
    public void testCreateEmployee() throws Exception {
        Employee employee = new Employee(null, "Mario", "Lopez", "mario@empresa.com", "Desarrollador", true);

        mockMvc.perform(post("/api/empleados")
                .header("X-API-KEY", API_KEY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nombre").value("Mario"));
    }

    @Test
    public void testGetExistingEmployee() throws Exception {
        Employee employee = new Employee(null, "Lucia", "Rios", "lucia@empresa.com", "Analista", true);

        mockMvc.perform(post("/api/empleados")
                .header("X-API-KEY", API_KEY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        mockMvc.perform(get("/api/empleados/1")
                .header("X-API-KEY", API_KEY))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetNonExistingEmployee() throws Exception {
        mockMvc.perform(get("/api/empleados/999")
                .header("X-API-KEY", API_KEY))
                .andExpect(status().isNotFound());
    }
}
