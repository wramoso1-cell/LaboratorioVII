package com.laboratorio.profesores.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;

    @Email(message = "El correo debe tener un formato válido")
    @NotBlank(message = "El correo no puede estar vacío")
    private String correo;

    @NotBlank(message = "La especialidad no puede estar vacía")
    private String especialidad;

    private Boolean estado;
}
