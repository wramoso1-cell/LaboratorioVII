package com.laboratorio.proveedores.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class Supplier {
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacio")
    private String apellido;

    @Email(message = "El correo debe tener un formato valido")
    @NotBlank(message = "El correo no puede estar vacio")
    private String correo;

    @NotBlank(message = "La categoria no puede estar vacia")
    private String categoria;

    private Boolean estado;

    public Supplier() {}

    public Supplier(Long id, String nombre, String apellido, String correo, String categoria, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.categoria = categoria;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }
}
