package com.laboratorio.profesores.service;

import com.laboratorio.profesores.exception.ResourceNotFoundException;
import com.laboratorio.profesores.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    private final List<Teacher> teachers = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Teacher create(Teacher teacher) {
        teacher.setId(idGenerator.getAndIncrement());
        if (teacher.getEstado() == null) {
            teacher.setEstado(true);
        }
        teachers.add(teacher);
        return teacher;
    }

    public List<Teacher> findAll(String especialidad) {
        if (especialidad != null && !especialidad.isBlank()) {
            return teachers.stream()
                    .filter(t -> t.getEspecialidad().equalsIgnoreCase(especialidad))
                    .collect(Collectors.toList());
        }
        return teachers;
    }

    public Teacher findById(Long id) {
        return teachers.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado con el ID: " + id));
    }
}
