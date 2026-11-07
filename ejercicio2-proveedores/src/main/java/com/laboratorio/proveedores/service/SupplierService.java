package com.laboratorio.proveedores.service;

import com.laboratorio.proveedores.exception.ResourceNotFoundException;
import com.laboratorio.proveedores.model.Supplier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class SupplierService {
    private final List<Supplier> suppliers = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Supplier create(Supplier supplier) {
        supplier.setId(idGenerator.getAndIncrement());
        if (supplier.getEstado() == null) {
            supplier.setEstado(true);
        }
        suppliers.add(supplier);
        return supplier;
    }

    public List<Supplier> findAll(String categoria) {
        if (categoria != null && !categoria.isBlank()) {
            return suppliers.stream()
                    .filter(s -> s.getCategoria().equalsIgnoreCase(categoria))
                    .collect(Collectors.toList());
        }
        return suppliers;
    }

    public Supplier findById(Long id) {
        return suppliers.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con el ID: " + id));
    }
}
