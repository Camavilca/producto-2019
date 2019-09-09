package com.camavilca.dao;

import com.camavilca.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoDAO extends JpaRepository<Producto, Long> {

    @Query(value = "SELECT * FROM producto p WHERE p.nombre = ?1 OR p.id = ?1", nativeQuery = true)
    Producto findProducto(String nombre);
}
