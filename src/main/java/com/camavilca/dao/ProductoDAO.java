package com.camavilca.dao;

import com.camavilca.model.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoDAO extends JpaRepository<Producto, Long> {

    @Query(value = "SELECT * FROM producto p WHERE p.nombre like ?1", nativeQuery = true)
    List<Producto> findProducto(String nombre);
    

     
    

}
