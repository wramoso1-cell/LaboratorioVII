package com.laboratorio.empleados.service;

import com.laboratorio.empleados.exception.ResourceNotFoundException;
import com.laboratorio.empleados.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Employee create(Employee employee) {
        employee.setId(idGenerator.getAndIncrement());
        if (employee.getEstado() == null) {
            employee.setEstado(true);
        }
        employees.add(employee);
        return employee;
    }

    public List<Employee> findAll(String especialidad) {
        if (especialidad != null && !especialidad.isBlank()) {
            return employees.stream()
                    .filter(e -> e.getEspecialidad().equalsIgnoreCase(especialidad))
                    .collect(Collectors.toList());
        }
        return employees;
    }

    public Employee findById(Long id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con el ID: " + id));
    }
}
