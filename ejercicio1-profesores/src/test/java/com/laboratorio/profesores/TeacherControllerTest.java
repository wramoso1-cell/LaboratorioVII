package com.laboratorio.profesores;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laboratorio.profesores.model.Teacher;
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
public class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String API_KEY = "MI_CLAVE_SECRETA_123";

    @Test
    public void testCreateTeacher() throws Exception {
        Teacher teacher = new Teacher(null, "Carlos", "Mendoza", "carlos@correo.com", "Matematicas", true);

        mockMvc.perform(post("/api/profesores")
                .header("X-API-KEY", API_KEY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(teacher)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nombre").value("Carlos"));
    }

    @Test
    public void testGetExistingTeacher() throws Exception {
        Teacher teacher = new Teacher(null, "Ana", "Gomez", "ana@correo.com", "Fisica", true);
        
        mockMvc.perform(post("/api/profesores")
                .header("X-API-KEY", API_KEY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(teacher)));

        mockMvc.perform(get("/api/profesores/1")
                .header("X-API-KEY", API_KEY))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetNonExistingTeacher() throws Exception {
        mockMvc.perform(get("/api/profesores/999")
                .header("X-API-KEY", API_KEY))
                .andExpect(status().isNotFound());
    }
}
