package com.laboratorio.profesores.controller;

import com.laboratorio.profesores.model.Teacher;
import com.laboratorio.profesores.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@Valid @RequestBody Teacher teacher) {
        Teacher created = teacherService.create(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getTeachers(@RequestParam(required = false) String especialidad) {
        return ResponseEntity.ok(teacherService.findAll(especialidad));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.findById(id));
    }
}
